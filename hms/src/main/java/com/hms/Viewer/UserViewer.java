package com.hms.Viewer;

import java.util.Scanner;

import com.enumclass.UserRole;

public abstract class UserViewer {
    // Create a single Scanner instance
    private static final Scanner scanner = new Scanner(System.in);

    public abstract void showMenu();

    public UserRole getRole() {
        return null;
    }

    public String getUserInput() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            return null;
        }
    }
}