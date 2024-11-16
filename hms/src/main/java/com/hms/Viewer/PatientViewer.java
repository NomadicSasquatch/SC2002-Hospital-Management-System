package com.hms.Viewer;

import com.enumclass.UserRole;

public class PatientViewer extends UserViewer {

    @Override
    public void showMenu() {
        System.out.println("1. View Medication");
        System.out.println("2. View Appointments");
        System.out.println("3. Logout");
    }

    @Override
    public String getUserInput() {
        String choice = super.getUserInput();
        switch (choice) {
            case "1":
                System.out.println("View Medication");
                break;
            case "2":
                System.out.println("View Appointments");
                break;
            case "3":
                System.out.println("Logout");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        return choice;
    }

    @Override
    public UserRole getRole() {
        return UserRole.PATIENT;
    }

}
