package com.hms.Viewer;

import com.enumclass.UserRole;

public class PharmacistViewer extends UserViewer {

    @Override
    public void showMenu() {
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
            default:
                System.out.println("Invalid choice");
                break;
        }
        return choice;
    }

}
