package com.hms.Viewer;

import java.time.LocalDate;
import java.util.List;

import com.enumclass.UserRole;
import com.hms.Users;
import com.hms.Controller.AuthController;
import com.hms.Manager.AdminManager;
import com.hms.Manager.UserManager;

public class LoginMenu extends UserViewer {

    private UserViewer loginUser;
    private UserManager userManager;

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
                userManager = new PatientManager();
                break;
            case "1":
                loginUser = new DoctorViewer();
                userManager = new DoctorManager();
                break;
            case "2":
                loginUser = new PharmacistViewer();
                userManager = new PharmacistManager();
                break;
            case "3":
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
        System.out.println("Would you like to login or register?\n0. Login\n1. Register\n2. Back");
        String userid = "";
        String password = "";
        switch (view.getUserInput()) {
            case "0":
                System.out.println("Enter User ID: ");
                userid = super.getUserInput();

                System.out.println("Enter Password: ");
                password = super.getUserInput();

                if (new AuthController(userManager).authenticate(userid, password)) {
                    System.out.println("Login Successful");
                    view.showMenu();
                } else {
                    System.out.println("Login Failed, please try again");
                    return true;
                }
                break;
            case "1":
                System.out.println("Enter User ID: ");
                userid = view.getUserInput();
                System.out.println("Enter Email: ");
                String email = view.getUserInput();
                System.out.println("Enter Name: ");
                String name = view.getUserInput();
                System.out.println("Enter Password: ");
                password = view.getUserInput();
                System.out.println("Enter Date of Birth (YYYY-MM-DD): ");
                LocalDate dob = LocalDate.parse(view.getUserInput());
                UserRole role = view.getRole();
                System.out.println("Enter gender (M/F): ");
                boolean gender = view.getUserInput().equalsIgnoreCase("M") ? true : false;
                Users user = new Users(userid, email, name, Users.hashPassword(password), dob, role, gender);
                userManager.add(user);
                break;
            case "2":
                return false;
            default:
                System.out.println("Invalid choice");
                break;
        }
        return false;
    }
}
