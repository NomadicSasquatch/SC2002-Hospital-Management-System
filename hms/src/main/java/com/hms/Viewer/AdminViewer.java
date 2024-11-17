package com.hms.Viewer;

import java.time.LocalDate;

import com.enumclass.UserRole;
import com.hms.Manager.PatientManager;
import com.hms.Manager.PharmacistManager;
import com.hms.Manager.UserManager;
import com.hms.Controller.AdminController;
import com.hms.Controller.AuthController;
import com.hms.Controller.DoctorController;
import com.hms.Users;

public class AdminViewer extends UserViewer {

    @Override
    public void showMenu() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n====================================================");
            System.out.println("----------------- Administrator Menu -----------------");
            System.out.println("====================================================");
            System.out.println("[1] View and Manage Hospital Staff");
            System.out.println("[2] View Appointment Details");
            System.out.println("[3] View and Manage Medication Inventory");
            System.out.println("[4] Approve Replenishment Requests");
            System.out.println("[5] Logout");

            System.out.print("\nPlease select an option (1-5): ");
            int choice = Integer.parseInt(super.getUserInput());
            clearScreen();
            switch (choice) {
                case 1:
                    viewAndManageHospitalStaff();
                    break;

                case 2:
                    // View Appointment Details
                    // Method not implemented yet, will call
                    // adminController.viewAppointmentDetails();
                    viewAppointmentDetails();
                    break;

                case 3:
                    // View and Manage Medication Inventory
                    // Method not implemented yet, will call
                    // adminController.manageMedicationInventory();
                    manageMedicationInventory();
                    break;

                case 4:
                    // Approve Replenishment Requests
                    // Method not implemented yet, will call
                    // adminController.approveReplenishmentRequests();
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

    private void viewAndManageHospitalStaff() {
        System.out.println("\n====================================================");
        System.out.println("          VIEW AND MANAGE HOSPITAL STAFF            ");
        System.out.println("====================================================");
        System.out.println("------------------ View and Manage -----------------");
        System.out.println("[1] Patients");
        System.out.println("[2] Doctors");
        System.out.println("[3] Pharmacists");
        System.out.println("[4] Admins");
        System.out.println("[5] Back to previous menu");

        int choice = Integer.parseInt(super.getUserInput());
        UserManager userManager = null;
        String menuName = "";
        clearScreen();
        boolean escFlag = true;
        while (escFlag) {
            escFlag = false;
            switch (choice) {

                case 1:
                    userManager = new PatientManager();
                    menuName = "Patients";
                    break;

                case 2:
                    userManager = new DoctorController();
                    menuName = "Doctors";
                    break;

                case 3:
                    userManager = new PharmacistManager();
                    menuName = "Pharmacists";
                    break;

                case 4:
                    userManager = new AdminController();
                    menuName = "Admins";
                    break;

                case 5:
                    return;

                default:
                    escFlag = true;
                    System.out.println("Invalid choice, please try again.");
            }
        }
        escFlag = true;
        while (userManager != null && escFlag) {
            escFlag = false;
            displayTable(userManager.view());

            System.out.println("\n====================================================");
            System.out.println("          VIEW AND MANAGE HOSPITAL STAFF            ");
            System.out.println("====================================================");
            System.out.println("\tCurrently editing: " + menuName);
            System.out.println("[1] Add new user");
            System.out.println("[2] Remove user");
            System.out.println("[3] Change Password");
            System.out.println("[4] Back to previous menu");

            choice = Integer.parseInt(super.getUserInput());
            clearScreen();
            String userid = "";
            String password = "";
            AuthController authController = new AuthController(userManager);
            switch (choice) {
                case 1:
                    while (userid != "quit" || authController.isUserExist(userid)) {
                        System.out.println("Enter User ID: ");
                        if (userid != "quit") {
                            System.out.println("User ID already exists, please try again.\n Type 'quit' to exit.");
                        } else if (userid == "quit") {
                            break;
                        }
                        userid = super.getUserInput();
                    }
                    System.out.println("Enter Email: ");
                    String email = super.getUserInput();
                    System.out.println("Enter Name: ");
                    String name = super.getUserInput();
                    System.out.println("Enter Password: ");
                    password = Users.hashPassword(super.getUserInput());
                    System.out.println("Enter Date of Birth (YYYY-MM-DD): ");
                    LocalDate dob = LocalDate.parse(super.getUserInput());
                    UserRole role = userManager.getRole();
                    System.out.println("Enter gender (M/F): ");
                    boolean gender = super.getUserInput().equalsIgnoreCase("M") ? true : false;
                    clearScreen();

                    System.out.println(authController.register(userid, email, name, password, dob, role, gender)
                            ? "User added successfully"
                            : "Failed to add user");
                    break;
                case 2:
                    System.out.println("Enter User ID to be removed (Case-sensitive): ");
                    userid = super.getUserInput();
                    clearScreen();
                    System.out.println(
                            authController.removeUser(userid) ? "User removed successfully" : "Failed to remove user");
                    break;

                case 3:
                    System.out.println("Enter User ID for password change (Case-sensitive): ");
                    userid = super.getUserInput();
                    System.out.println("Enter new password: ");
                    password = Users.hashPassword(super.getUserInput());
                    clearScreen();
                    System.out.println(authController.changePassword(userid, password) ? "Password changed successfully"
                            : "Failed to change password");
                    break;

                case 4:
                    viewAndManageHospitalStaff();
                    // recursive call to return to the previous menu
                    break;

                default:
                    escFlag = true;
                    System.out.println("Invalid choice, please try again.");
            }
        }

    }

    // Test Case 21: View Appointment Details
    private void viewAppointmentDetails() {
        System.out.println("\n====================================================");
        System.out.println("              VIEW APPOINTMENT DETAILS               ");
        System.out.println("====================================================");
        System.out.println("------------------- View Details -------------------");
        

        returnToMenu();
    }

    // Test Case 22: View and Manage Medication Inventory
    private void manageMedicationInventory() {
        System.out.println("\n====================================================");
        System.out.println("        VIEW AND MANAGE MEDICATION INVENTORY        ");
        System.out.println("====================================================");

        // Sample medication records (for example purposes)
        String[] medicationIds = { "M001", "M002", "M003" };
        String[] medicationNames = { "Amlodipine", "Metformin", "Paracetamol" };
        int[] stockLevels = { 100, 50, 200 };

        // Display medication inventory
        System.out.printf("%-12s %-20s %-10s\n", "Medication ID", "Name", "Stock Level");
        System.out.println("----------------------------------------------------");

        for (int i = 0; i < medicationIds.length; i++) {
            System.out.printf("%-12s %-20s %-10d\n", medicationIds[i], medicationNames[i], stockLevels[i]);
        }

        System.out.print("\nEnter 'update' to add stock or press [Enter] to return: ");

        String action = super.getUserInput();

        if (action.equalsIgnoreCase("update")) {
            System.out.print("Enter Medication ID to update stock: ");
            String medicationId = super.getUserInput();
            System.out.print("Enter stock amount: ");
            int newStockLevel = Integer.parseInt(super.getUserInput());
            System.out.println("Added " + newStockLevel + " stocks for " + medicationId);
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
        String[] requestIds = { "R001", "R002", "R003" };
        String[] medicationIds = { "M001", "M002", "M003" };
        String[] medicationNames = { "Amlodipine", "Metformin", "Paracetamol" };
        int[] requestedQuantities = { 50, 30, 100 };

        // Display replenishment requests with Medication ID
        System.out.printf("%-12s %-12s %-20s %-15s\n", "Request ID", "Medication ID", "Medication",
                "Requested Quantity");
        System.out.println("-----------------------------------------------------");

        for (int i = 0; i < requestIds.length; i++) {
            System.out.printf("%-12s %-12s %-20s %-15d\n", requestIds[i], medicationIds[i], medicationNames[i],
                    requestedQuantities[i]);
        }

        System.out.print("\nEnter 'approve' to approve a request or 'back' to return: ");
        String action = super.getUserInput();

        if (action.equalsIgnoreCase("approve")) {
            // Ask for the Request ID to approve
            System.out.print("Enter Request ID to approve: ");
            String requestId = super.getUserInput();

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
                System.out.println("Replenishment request " + requestId + " for " + medicationNames[index]
                        + " has been approved.");
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
        String[] medicationIds = { "M001", "M002", "M003" };
        String[] medicationNames = { "Amlodipine", "Metformin", "Paracetamol" };
        int[] stockLevels = { 100, 50, 200 }; // Current stock levels

        // Find the medication by its ID and update the stock
        for (int i = 0; i < medicationIds.length; i++) {
            if (medicationIds[i].equalsIgnoreCase(medicationId)) {
                stockLevels[i] += quantityToAdd;
                System.out.println(
                        "Updated stock for " + medicationNames[i] + " (ID: " + medicationId + ") to " + stockLevels[i]);
                break;
            }
        }
    }

    private void returnToMenu() {
        System.out.print("\nPress [Enter] to return to the main menu...");
        super.getUserInput();
    }
}
