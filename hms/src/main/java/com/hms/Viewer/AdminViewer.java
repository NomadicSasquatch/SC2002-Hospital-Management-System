package com.hms.Viewer;

import com.enumclass.UserRole;
import com.hms.Controller.AuthController;
import com.hms.Manager.AdminManager;

public class AdminViewer extends UserViewer {

    private AdminManager adminManager = new AdminManager();

    @Override
    public void showMenu() {
        System.out.println();
    }

    @Override
    public String getUserInput() {
        
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
        return null;
    }

    @Override
    public UserRole getRole() {
        return UserRole.ADMIN;
    }
}
