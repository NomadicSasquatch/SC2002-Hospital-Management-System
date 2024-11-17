package com.hms.Viewer;

import java.time.LocalDate;

import com.enumclass.UserRole;
import com.hms.Users;
import com.hms.Controller.AuthController;
import com.hms.Manager.AdminManager;
import com.hms.Manager.UserManager;

public class LoginMenu extends UserViewer {

    private UserViewer loginUser;
    private UserManager userManager;

    public LoginMenu() {
    }

    @Override
    public void showMenu() {
        clearScreen();
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

        while (true) {
            System.out.println("---------------------------------------------------------");

            // Get user input for Hospital ID and Password
            System.out.print(
                    "Please select user group:\n1. Patient\n2. Doctor\n3. Pharmacist\n4. Admin\n5. Exit\nEnter choice: ");
            this.getUserInput();
            clearScreen();
        }
    }

    @Override
    public String getUserInput() {
        String choice = super.getUserInput();
        switch (choice) {
            case "1":
                loginUser = new PatientViewer();
                // userManager = new PatientManager();
                break;
            case "2":
                loginUser = new DoctorViewer();
                // userManager = new DoctorManager();
                break;
            case "3":
                loginUser = new PharmacistViewer();
                // userManager = new PharmacistManager();
                break;
            case "4":
                loginUser = new AdminViewer();
                userManager = new AdminManager();
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        loginRegisterPrompt(loginUser, userManager);
        return choice;
    }

    private boolean loginRegisterPrompt(UserViewer view, UserManager userManager) {
        clearScreen();
        System.out.println("Would you like to login or register?\n1. Login\n2. Register\n3. Back");
        String userid = "";
        String password = "";
        switch (super.getUserInput()) {
            case "1":
                System.out.println("Enter User ID: ");
                userid = super.getUserInput();

                System.out.println("Enter Password: ");
                password = super.getUserInput();

                if (new AuthController(userManager).authenticate(userid, password)) {
                    System.out.println("Login Successful");
                    view.showMenu();
                } else {
                    System.out.println("Login Failed, please try again, Press Enter to continue");
                    super.getUserInput();
                }
                break;
            case "2":
                System.out.println("Enter User ID: ");
                userid = super.getUserInput();
                System.out.println("Enter Email: ");
                String email = super.getUserInput();
                System.out.println("Enter Name: ");
                String name = super.getUserInput();
                System.out.println("Enter Password: ");
                password = Users.hashPassword(super.getUserInput());
                System.out.println("Enter Date of Birth (YYYY-MM-DD): ");
                LocalDate dob = LocalDate.parse(super.getUserInput());
                UserRole role = view.getRole();
                System.out.println("Enter gender (M/F): ");
                boolean gender = super.getUserInput().equalsIgnoreCase("M") ? true : false;
                new AuthController(userManager).register(userid, email, name, password, dob, role, gender);
                break;
            case "3":
                return false;
            default:
                System.out.println("Invalid choice");
                break;
        }
        return false;
    }
}
