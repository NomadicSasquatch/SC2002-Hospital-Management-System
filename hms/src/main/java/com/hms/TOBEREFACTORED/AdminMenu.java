package com.hms.TOBEREFACTORED;


import java.util.Scanner;

public class AdminMenu implements Menu {

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("");
            System.out.println("----------------- Administrator Menu -----------------");
            System.out.println("[1] View and Manage Hospital Staff");
            System.out.println("[2] View Appointment Details");
            System.out.println("[3] View and Manage Medication Inventory");
            System.out.println("[4] Approve Replenishment Requests");
            System.out.println("[5] Logout");

            System.out.print("\nPlease select an option (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // View and Manage Hospital Staff
                    // Method not implemented yet, will call adminController.manageHospitalStaff();
                    break;

                case 2:
                    // View Appointment Details
                    // Method not implemented yet, will call adminController.viewAppointmentDetails();
                    break;

                case 3:
                    // View and Manage Medication Inventory
                    // Method not implemented yet, will call adminController.manageMedicationInventory();
                    break;

                case 4:
                    // Approve Replenishment Requests
                    // Method not implemented yet, will call adminController.approveReplenishmentRequests();
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
