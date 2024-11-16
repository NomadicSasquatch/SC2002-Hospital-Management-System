package com.hms.Viewer;

import java.util.Scanner;

public abstract class UserViewer {
    public abstract void showMenu();
    public String getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextLine();
        } catch (Exception e) {
            return null;
        }
    };
}
