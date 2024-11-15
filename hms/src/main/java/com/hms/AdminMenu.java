package com.hms;


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
                    viewAndManageHospitalStaff(); // Dummy method for UI reference
                    break;

                case 2:
                    // View Appointment Details
                    // Method not implemented yet, will call adminController.viewAppointmentDetails();
                    viewAppointmentDetails();
                    break;

                case 3:
                    // View and Manage Medication Inventory
                    // Method not implemented yet, will call adminController.manageMedicationInventory();
                    manageMedicationInventory();
                    break;

                case 4:
                    // Approve Replenishment Requests
                    // Method not implemented yet, will call adminController.approveReplenishmentRequests();
                    approveReplenishmentRequests();
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

    /* DUMMY METHODS FOR UI - TO DELETE ONCE IMPLEMENTATION IS COMPLETED */
    
    // Test Case 20: View and Manage Hospital Staff
    private void viewAndManageHospitalStaff() {
        System.out.println("\n====================================================");
        System.out.println("          VIEW AND MANAGE HOSPITAL STAFF            ");
        System.out.println("====================================================");

        // Sample list of hospital staff (for example purposes)
        String[] staffIds = {"S123", "S124", "S125"};
        String[] staffNames = {"Dr. Smith", "Nurse Jane", "Dr. Lee"};
        String[] roles = {"Doctor", "Nurse", "Doctor"};

        // Display staff list
        System.out.printf("%-12s %-20s %-10s\n", "Staff ID", "Name", "Role");
        System.out.println("----------------------------------------------------");

        for (int i = 0; i < staffIds.length; i++) {
            System.out.printf("%-12s %-20s %-10s\n", staffIds[i], staffNames[i], roles[i]);
        }

        System.out.print("\nEnter 'add', 'update', or 'remove' to manage staff or 'enter' to return: ");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();

        if (action.equalsIgnoreCase("add")) {
            // Function to add new staff (not implemented yet)
            System.out.println("Adding new staff...");
        } else if (action.equalsIgnoreCase("update")) {
            // Function to update staff (not implemented yet)
            System.out.println("Updating staff...");
        } else if (action.equalsIgnoreCase("remove")) {
            // Function to remove staff (not implemented yet)
            System.out.println("Removing staff...");
        } else if (action.equalsIgnoreCase("enter")) {
            returnToMenu();
        } 

    }

    // Test Case 21: View Appointment Details
    private void viewAppointmentDetails() {
        System.out.println("\n====================================================");
        System.out.println("              VIEW APPOINTMENT DETAILS               ");
        System.out.println("====================================================");

        // Sample appointment records (for example purposes)
        String[] appointmentIds = {"A123", "A124", "A125"};
        String[] patientIds = {"P123", "P124", "P125"};
        String[] doctorIds = {"D001", "D002", "D003"};
        String[] statuses = {"Confirmed", "Pending", "Completed"};
        String[] dates = {"2024-11-20 10:00", "2024-11-21 14:00", "2024-11-22 09:00"};

        // Display appointment details
        System.out.printf("%-12s %-12s %-12s %-10s %-20s\n", "Appointment ID", "Patient ID", "Doctor ID", "Status", "Date/Time");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < appointmentIds.length; i++) {
            System.out.printf("%-12s %-12s %-12s %-10s %-20s\n", appointmentIds[i], patientIds[i], doctorIds[i], statuses[i], dates[i]);
        }

        returnToMenu();
    }

    // Test Case 22: View and Manage Medication Inventory
    private void manageMedicationInventory() {
        System.out.println("\n====================================================");
        System.out.println("        VIEW AND MANAGE MEDICATION INVENTORY        ");
        System.out.println("====================================================");

        // Sample medication records (for example purposes)
        String[] medicationIds = {"M001", "M002", "M003"};
        String[] medicationNames = {"Amlodipine", "Metformin", "Paracetamol"};
        int[] stockLevels = {100, 50, 200};

        // Display medication inventory
        System.out.printf("%-12s %-20s %-10s\n", "Medication ID", "Name", "Stock Level");
        System.out.println("----------------------------------------------------");

        for (int i = 0; i < medicationIds.length; i++) {
            System.out.printf("%-12s %-20s %-10d\n", medicationIds[i], medicationNames[i], stockLevels[i]);
        }

        System.out.print("\nEnter 'update' to add stock or press [Enter] to return: ");
        Scanner scanner = new Scanner(System.in);
        String action = scanner.nextLine();

        if (action.equalsIgnoreCase("update")) {
            System.out.print("Enter Medication ID to update stock: ");
            String medicationId = scanner.nextLine();
            System.out.print("Enter stock amount: ");
            int newStockLevel = scanner.nextInt();
            System.out.println("Added " + newStockLevel + " stocks for " +  medicationId);
        } else if (action.equalsIgnoreCase("enter")) {
                returnToMenu();
        } 
    }

    // Test Case 23: Approve Replenishment Requests
private void approveReplenishmentRequests() {
    System.out.println("\n====================================================");
    System.out.println("       APPROVE REPLENISHMENT REQUESTS                ");
    System.out.println("====================================================");

    // Sample replenishment requests (for example purposes)
    String[] requestIds = {"R001", "R002", "R003"};
    String[] medicationIds = {"M001", "M002", "M003"};
    String[] medicationNames = {"Amlodipine", "Metformin", "Paracetamol"};
    int[] requestedQuantities = {50, 30, 100};

    // Display replenishment requests with Medication ID
    System.out.printf("%-12s %-12s %-20s %-15s\n", "Request ID", "Medication ID", "Medication", "Requested Quantity");
    System.out.println("-----------------------------------------------------");

    for (int i = 0; i < requestIds.length; i++) {
        System.out.printf("%-12s %-12s %-20s %-15d\n", requestIds[i], medicationIds[i], medicationNames[i], requestedQuantities[i]);
    }

    System.out.print("\nEnter 'approve' to approve a request or 'back' to return: ");
    Scanner scanner = new Scanner(System.in);
    String action = scanner.nextLine();

    if (action.equalsIgnoreCase("approve")) {
        // Ask for the Request ID to approve
        System.out.print("Enter Request ID to approve: ");
        String requestId = scanner.nextLine();

        // Find the corresponding medication ID and quantity from the request
        int index = -1;
        for (int i = 0; i < requestIds.length; i++) {
            if (requestIds[i].equalsIgnoreCase(requestId)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            // Medicine ID and requested quantity
            String approvedMedicationId = medicationIds[index];
            int approvedQuantity = requestedQuantities[index];

            // Update the Medication Inventory
            updateMedicationInventory(approvedMedicationId, approvedQuantity);

            // Confirm approval and inventory update
            System.out.println("Replenishment request " + requestId + " for " + medicationNames[index] + " has been approved.");
        } else {
            System.out.println("Invalid Request ID.");
        }
    } else if (action.equalsIgnoreCase("back")) {
        System.out.println("Returning to the main menu...");
    } else {
        System.out.println("Invalid action.");
    }
}

// Method to update the Medication Inventory after approval
private void updateMedicationInventory(String medicationId, int quantityToAdd) {
    // Sample medication inventory (for example purposes)
    String[] medicationIds = {"M001", "M002", "M003"};
    String[] medicationNames = {"Amlodipine", "Metformin", "Paracetamol"};
    int[] stockLevels = {100, 50, 200}; // Current stock levels

    // Find the medication by its ID and update the stock
    for (int i = 0; i < medicationIds.length; i++) {
        if (medicationIds[i].equalsIgnoreCase(medicationId)) {
            stockLevels[i] += quantityToAdd;
            System.out.println("Updated stock for " + medicationNames[i] + " (ID: " + medicationId + ") to " + stockLevels[i]);
            break;
        }
    }
}


    private void returnToMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress [Enter] to return to the main menu...");
        scanner.nextLine();
    }
}
