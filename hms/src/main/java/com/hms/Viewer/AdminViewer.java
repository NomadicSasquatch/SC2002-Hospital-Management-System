package com.hms.Viewer;

import com.hms.Controller.AuthController;
import com.hms.Manager.AdminManager;

public class AdminViewer extends UserViewer {

    private AdminManager adminManager = new AdminManager();

    @Override
    public void showMenu() {
        System.err.println();
    }

    @Override
    public String getUserInput() {
        System.err.println("Enter User ID: ");
        String userID = super.getUserInput();

        System.err.println("Enter Password: ");
        String password = super.getUserInput();
        
        if (new AuthController(adminManager).authenticate(userID, password)) {
            System.err.println("Login Successful");
            String choice = super.getUserInput();
            switch (choice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            return choice;

        } else {
            System.err.println("Login Failed");
        }
        return null;
    }
}
