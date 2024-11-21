package views;

import java.util.List;

import models.InventoryItem;
import models.Prescription;

public class PharmacistView extends View {

    /**
     * Displays the menu for the pharmacist with options to: Prompts the user to
     * enter their choice.
     */
    @Override
    public void displayMenu() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║              Inventory Management              ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║ 1. View Inventory Items                        ║");
        System.out.println("║ 2. Check Low Stock Items                       ║");
        System.out.println("║ 3. Back to Main Menu                           ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays the menu for the pharmacist with the list of prescriptions.
     *
     * @param prescriptions List of prescriptions to be displayed
     */
    public void displayPrescriptions(List<Prescription> prescriptions) {
        System.out.println("Pending Prescriptions:");
        for (Prescription prescription : prescriptions) {
            System.out.println("Prescription ID: " + prescription.getPrescriptionId()
                    + ", Patient ID: " + prescription.getPatientId()
                    + ", Medication: " + prescription.getMedicationName()
                    + ", Dosage: " + prescription.getDosage()
                    + ", Quantity: " + prescription.getQuantity()
                    + ", Status: " + prescription.getStatus());
        }
    }

    /**
     * Displays the list of inventory items.
     *
     * @param items the list of InventoryItem objects to be displayed
     */
    public void displayInventoryList(List<InventoryItem> items) {
        System.out.println("Inventory Items:");
        for (InventoryItem item : items) {
            System.out.println("Medication ID: " + item.getMedicationId()
                    + ", Name: " + item.getMedicationName()
                    + ", Quantity: " + item.getQuantity()
                    + ", Low Stock Level: " + item.getLowStockLevel());
        }
    }

    /**
     * Displays the inventory management menu to the user. Prompts the user to
     * enter their choice.
     */
    public void displayInventoryManagementMenu() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║              Inventory Management              ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║ 1. View Inventory Items                        ║");
        System.out.println("║ 2. Check Low Stock Items                       ║");
        System.out.println("║ 3. Back to Main Menu                           ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        System.out.print("Enter your choice: ");
    }
}
