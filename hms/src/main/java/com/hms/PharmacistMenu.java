package com.hms;


import java.util.Scanner;

import javax.swing.text.View;

public class PharmacistMenu implements Menu {

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        
        while (!exit) {
            System.out.println("");
            System.out.println("------------------ Pharmacist Menu ------------------");
            System.out.println("[1] View Appointment Outcome Record");
            System.out.println("[2] Update Prescription Status");
            System.out.println("[3] View Medication Inventory");
            System.out.println("[4] Submit Replenishment Request");
            System.out.println("[5] Logout");

            System.out.print("\nPlease select an option (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // View Appointment Outcome Record
                    // Method not implemented yet, will call pharmacistController.viewAppointmentOutcome();
                    viewAppointmentOutcomeRecord();
                    break;

                case 2:
                    // Update Prescription Status
                    // Method not implemented yet, will call pharmacistController.updatePrescriptionStatus();
                    updatePrescriptionStatus();
                    break;

                case 3:
                    // View Medication Inventory
                    // Method not implemented yet, will call pharmacistController.viewMedicationInventory();
                    viewMedicationInventory();
                    break;

                case 4:
                    // Submit Replenishment Request
                    // Method not implemented yet, will call pharmacistController.submitReplenishmentRequest();
                    submitReplenishmentRequest();
                    break;

                case 5:
                    // Logout
                    System.out.println("Logging out...");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    /* DUMMY METHODS FOR UI - TO DELETE ONCE IMPLEMENTATION IS COMPLETED */

    // Test Case 16: View Appointment Outcome Record
    private void viewAppointmentOutcomeRecord() {
        System.out.println("\n====================================================");
        System.out.println("            VIEW APPOINTMENT OUTCOME RECORD              ");
        System.out.println("====================================================");
    
        // Sample data for demonstration: each row corresponds to a patient's appointment
        String[][] appointments = {
            {"P12345", "John Doe", "Hypertension", "Amlodipine 5mg, Hydrochlorothiazide 12.5mg"},
            {"P67890", "Jane Smith", "Diabetes", "Metformin 500mg, Insulin 20 IU"},
            {"P11223", "Mark Lee", "Asthma", "Salbutamol 100mcg, Fluticasone 250mcg"}
        };
    
        // Loop through each appointment and display the details
        for (int i = 0; i < appointments.length; i++) {
            System.out.println("\n----------------------------------------------------");
            System.out.println("Appointment ID: " + appointments[i][0]);
            System.out.println("Patient Name: " + appointments[i][1]);
            System.out.println("Diagnosis: " + appointments[i][2]);
            System.out.println("Prescribed Medications: " + appointments[i][3]);
            System.out.println("----------------------------------------------------");
            
        }
        returnToMenu();
        System.out.println("\n====================================================");

    }
    
    

    // Test Case 17: Update Prescription Status
    private void updatePrescriptionStatus() {
        System.out.println("\n====================================================");
        System.out.println("             UPDATE PRESCRIPTION STATUS                  ");
        System.out.println("====================================================");
    
        Scanner scanner = new Scanner(System.in);
    
        // Ask for the appointment ID first
        System.out.print("Enter the Appointment ID to update prescription: ");
        String appointmentId = scanner.nextLine();
    
        // List to hold medications with their quantities
        String[] medications = new String[5]; // Max 5 medications for now, can be adjusted
        String[] quantities = new String[5]; // Store quantities separately
        int count = 0;
    
        // Allow pharmacist to enter medications and their quantities
        while (count < medications.length) {
            System.out.print("Enter medication " + (count + 1) + " name (or 'done' to finish): ");
            String medication = scanner.nextLine();
    
            if (medication.equalsIgnoreCase("done")) {
                break; // Exit the loop if 'done' is entered
            } else {
                medications[count] = medication; // Add medication name
                System.out.print("Enter quantity for " + medication + ": ");
                quantities[count] = scanner.nextLine(); // Add medication quantity
                count++;
            }
        }
    
        // Ask for status update
        System.out.print("\nEnter 'dispensed' to mark the prescription as dispensed: ");
        String action = scanner.nextLine();
    
        if (action.equalsIgnoreCase("dispensed")) {
            System.out.println("Prescription for Appointment ID: " + appointmentId + " is marked as 'dispensed'.");
            System.out.println("The following medications are updated to the patient's record:");
    
            // Display the medications and their quantities
            for (int i = 0; i < count; i++) {
                System.out.println("- " + medications[i] + " (Quantity: " + quantities[i] + ")");
            }
        } else {
            System.out.println("Invalid status. Please enter 'dispensed'.");
        }
    }
    

    // Test Case 18: View Medication Inventory
    private void viewMedicationInventory() {
        System.out.println("\n====================================================");
        System.out.println("             VIEW MEDICATION INVENTORY                   ");
        System.out.println("====================================================");

        // Dummy data for demonstration
        String[][] inventory = {
            {"Amlodipine 5mg", "100", "In Stock"},
            {"Metformin 500mg", "50", "In Stock"},
            {"Aspirin 75mg", "0", "Out of Stock"}
        };

        // Display the medication inventory in a table format
        System.out.printf("%-20s %-10s %-15s%n", "Medication Name", "Stock Level", "Status");
        System.out.println("-------------------------------------------------------");

        for (String[] med : inventory) {
            System.out.printf("%-20s %-10s %-15s%n", med[0], med[1], med[2]);
        }
        returnToMenu();
    }

    // Test Case 19: Submit Replenishment Request
    private void submitReplenishmentRequest() {
        System.out.println("\n====================================================");
        System.out.println("             SUBMIT REPLENISHMENT REQUEST                 ");
        System.out.println("====================================================");

        Scanner scanner = new Scanner(System.in);

        // User selects medication to replenish
        System.out.print("Enter the medication name to request replenishment: ");
        String medicationName = scanner.nextLine();
        System.out.print("Enter the quantity to replenish: ");
        int quantity = scanner.nextInt();

        System.out.println("Replenishment request for " + quantity + " units of " + medicationName + " submitted successfully.");
    }

    private void returnToMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress [Enter] to return to the main menu...");
        scanner.nextLine();
    }
}
