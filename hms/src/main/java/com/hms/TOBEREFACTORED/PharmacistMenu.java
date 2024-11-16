package com.hms.TOBEREFACTORED;


import java.util.Scanner;

public class PharmacistMenu implements Menu {

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        
        while (!exit) {
            System.out.println("");
            System.out.println("------------------ Pharmacist Menu ------------------");
            System.out.println("[1] View Appointment Outcome Record");
            System.out.println("[2] Update Prescription Status");
            System.out.println("[3] View Medication Inventory");
            System.out.println("[4] Submit Replenishment Request");
            System.out.println("[5] Logout");

            System.out.print("\nPlease select an option (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // View Appointment Outcome Record
                    // Method not implemented yet, will call pharmacistController.viewAppointmentOutcome();
                    break;

                case 2:
                    // Update Prescription Status
                    // Method not implemented yet, will call pharmacistController.updatePrescriptionStatus();
                    break;

                case 3:
                    // View Medication Inventory
                    // Method not implemented yet, will call pharmacistController.viewMedicationInventory();
                    break;

                case 4:
                    // Submit Replenishment Request
                    // Method not implemented yet, will call pharmacistController.submitReplenishmentRequest();
                    break;

                case 5:
                    // Logout
                    System.out.println("Logging out...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
