package controllers;

import java.util.List;
import java.util.Scanner;
import models.InventoryItem;
import models.Prescription;
import models.User;
import services.InventoryService;
import services.PrescriptionService;
import views.PharmacistView;

/**
 * PharmacistController handles pharmacist interactions.
 */
public class PharmacistController extends Controller {

    private User pharmacistUser;
    private InventoryService inventoryService;
    private PrescriptionService prescriptionService;
    private PharmacistView pharmacistView;
    private Scanner scanner;

    public PharmacistController(User pharmacistUser, InventoryService inventoryService,
                                PrescriptionService prescriptionService) {
        this.pharmacistUser = pharmacistUser;
        this.inventoryService = inventoryService;
        this.prescriptionService = prescriptionService;
        this.pharmacistView = new PharmacistView();
        this.scanner = new Scanner(System.in);
    }

    /**
     *  Function that starts the admin controller with the specified view.
     *
     */
    @Override
    public void start() {
        super.start(pharmacistView, "5");
    }

    /**
     * Handles the pharmacist's menu choice.
     *
     * @param choice The menu choice selected.
     */
    @Override
    public void handleMenuChoice(String choice) {
        switch (choice) {
            case "1":
            listPrescriptionItems();
                break;
            case "2":
                dispensePrescription();
                break;
            case "3":
                manageInventory();
                break;
            case "4":
                submitReplenishmentRequest();
                break;
            case "5":
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    /**
     * Views pending prescriptions.
     */
    private void viewPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByStatus("pending");
        if (prescriptions.isEmpty()) {
            System.out.println("No pending prescriptions.");
        } else {
            pharmacistView.displayPrescriptions(prescriptions);
        }
    }

    private void listPrescriptionItems() {
        if (prescriptionService.getAllPrescriptions().isEmpty()) {
            System.out.println("No items in inventory.");
            return;
        }
        pharmacistView.displayPrescriptions(prescriptionService.getAllPrescriptions());
    }

    

    

    /**
     * Dispenses a prescription.
     */
    private void dispensePrescription() {
        System.out.println("Enter Prescription ID to dispense:");
        String prescriptionId = scanner.nextLine();

        boolean success = prescriptionService.dispensePrescription(prescriptionId, pharmacistUser.getUserId());
        if (success) {
            System.out.println("Prescription dispensed successfully.");
        } else {
            System.out.println("Failed to dispense prescription.");
        }
    }

    /**
     * Manages inventory (view items, check low stock).
     */
    private void manageInventory() {
        String subChoice;
        do {
            pharmacistView.displayInventoryManagementMenu();
            subChoice = scanner.nextLine();
            switch (subChoice) {
                case "1":
                    viewInventoryItems();
                    break;
                case "2":
                    checkLowStockItems();
                    break;
                case "3":
                    // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (!subChoice.equals("3"));
    }

    /**
     * Views all inventory items.
     */
    private void viewInventoryItems() {
        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        pharmacistView.displayInventoryList(items);
    }

    /**
     * Checks for inventory items that are below the low stock level.
     */
    private void checkLowStockItems() {
        List<InventoryItem> lowStockItems = inventoryService.getItemsBelowStockLevel();
        if (lowStockItems.isEmpty()) {
            System.out.println("No items are below the low stock level.");
        } else {
            System.out.println("Items below low stock level:");
            pharmacistView.displayInventoryList(lowStockItems);
        }
    }

    /**
     * Submits a replenishment request for a medication.
     */
    private void submitReplenishmentRequest() {
        viewInventoryItems();
        System.out.println("Enter Medication ID to request replenishment:");
        String medicationId = scanner.nextLine();

        System.out.println("Enter Quantity to request:");
        int quantity;
        try {
            quantity = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity.");
            return;
        }

        boolean success = inventoryService.submitReplenishmentRequest(medicationId, quantity, pharmacistUser.getUserId());
        if (success) {
            System.out.println("Replenishment request submitted successfully.");
        } else {
            System.out.println("Failed to submit replenishment request.");
        }
    }
}
