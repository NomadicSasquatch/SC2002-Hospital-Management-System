package com.hms.Viewer;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.enumclass.UserRole;
import com.utils.CSVFile;

public abstract class UserViewer {
    // Create a single Scanner instance
    private static final Scanner scanner = new Scanner(System.in);

    public abstract void showMenu();

    public UserRole getRole() {
        return null;
    }

    public static void clearScreen() {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }

    public String getUserInput() {
        try {
            return scanner.nextLine();
        } catch (Exception e) {
            return null;
        }
    }

    public void displayTable(CSVFile csvFile) {
        List<List<String>> records = csvFile.getAllRecords();
        if (records.isEmpty()) {
            System.out.println("No records to display.");
            return;
        }
    
        int[] columnWidths = new int[records.get(0).size()];
        for (List<String> record : records) {
            for (int i = 0; i < record.size(); i++) {
                columnWidths[i] = Math.max(columnWidths[i], record.get(i).length());
            }
        }
    
        for (List<String> record : records) {
            for (int i = 0; i < record.size(); i++) {
                System.out.printf("%-" + (columnWidths[i] + 2) + "s", record.get(i));
            }
            System.out.println();
        }
    }
}