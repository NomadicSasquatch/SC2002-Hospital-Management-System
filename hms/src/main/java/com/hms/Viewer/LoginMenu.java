package com.hms.Viewer;

import com.enumclass.UserRole;

public class LoginMenu extends UserViewer {

    private UserViewer loginUser;

    public LoginMenu() {
        // Adding sample users for demonstration purposes
        // users.put("P123", new Patient("P123", "John Doe", UserRole.PATIENT,
        // "john.doe@example.com", true, LocalDate.of(1990, 5, 10), "password123"));
        // users.put("D123", new Doctor("D456", "Dr. Smith", UserRole.DOCTOR,
        // "dr.smith@example.com", true, LocalDate.of(1980, 6, 15), "password123"));
        // users.put("PM123", new Pharmacist("P123", "John Doe", UserRole.PHARMACIST,
        // "john.doe@example.com", true, LocalDate.of(1990, 5, 10), "password123"));
        // users.put("A123", new Admin("D456", "Dr. Smith", UserRole.ADMIN,
        // "dr.smith@example.com", true, LocalDate.of(1980, 6, 15), "password123"));
    }

    @Override
    public void showMenu() {
        String asciiTitle = "\n" +
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++\n" +
                "+                H   H  M   M  SSSSS                 +\n" +
                "+                H   H  MM MM  S                     +\n" +
                "+                HHHHH  M M M  SSSSS                 +\n" +
                "+                H   H  M   M      S                 +\n" +
                "+                H   H  M   M  SSSSS                 +\n" +
                "++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
        // Improved compatibility with different JRE versions

        System.out.println(asciiTitle); // Print the large border and title

        // Welcome message
        System.out.println("\t[ Welcome to the Hospital Management System ]\n");

        // Loop for unlimited login attempts

        System.out.println("[***] Login to Your Hospital Account [***]");
        System.out.println("---------------------------------------------------------");

        // Get user input for Hospital ID and Password
        System.out.print(
                "Please select user group:\n1. Patient\n2. Doctor\n3. Pharmacist\n4. Admin\n5. Exit\nEnter choice: ");

        while (true) {
            this.getUserInput();
        }

    }

    @Override
    public String getUserInput() {
        String choice = super.getUserInput();
        switch (choice) {
            case "0":
                loginUser = new PatientViewer();
                break;
            case "1":
                loginUser = new DoctorViewer();
                break;
            case "2":
                loginUser = new PharmacistViewer();
                break;
            case "3":
                loginUser = new AdminViewer();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        loginUser.showMenu();
        return choice;
    }
}
