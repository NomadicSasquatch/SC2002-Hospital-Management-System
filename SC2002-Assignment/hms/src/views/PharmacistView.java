package views;

import java.util.List;
import models.InventoryItem;
import models.Prescription;

public class PharmacistView extends View {

    public void displayMenu() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║                Pharmacist Menu                 ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║ 1. View Pending Prescriptions                  ║");
        System.out.println("║ 2. Dispense Prescription                       ║");
        System.out.println("║ 3. Manage Inventory                            ║");
        System.out.println("║ 4. Submit Replenishment Request                ║");
        System.out.println("║ 5. Logout                                      ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays the inventory management menu to the user.
     * Prompts the user to enter their choice.
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

    
    /** 
     * @param prescriptions
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

    public void displayInventoryList(List<InventoryItem> items) {
        System.out.println("Inventory Items:");
        for (InventoryItem item : items) {
            System.out.println("Medication ID: " + item.getMedicationId()
                    + ", Name: " + item.getMedicationName()
                    + ", Quantity: " + item.getQuantity()
                    + ", Low Stock Level: " + item.getLowStockLevel());
        }
    }
}
