package com.hms.Viewer;

import java.util.Scanner;

import com.enumclass.UserRole;

public abstract class UserViewer {
    public abstract void showMenu();
    public UserRole getRole() {
        return null;
    }
    public String getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        } catch (Exception e) {
            return null;
        }
    };
}
