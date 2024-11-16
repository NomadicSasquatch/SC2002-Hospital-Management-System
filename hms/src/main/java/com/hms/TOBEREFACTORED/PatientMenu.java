package com.hms.TOBEREFACTORED;


import java.util.Scanner;

public class PatientMenu implements Menu {

    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu options
            System.out.println("");
            System.out.println("--------------------- Patient Menu ---------------------");
            System.out.println("[1] View Medical Record");
            System.out.println("[2] Update Personal Information");
            System.out.println("[3] View Available Appointment Slots");
            System.out.println("[4] Schedule an Appointment");
            System.out.println("[5] Reschedule an Appointment");
            System.out.println("[6] Cancel an Appointment");
            System.out.println("[7] View Scheduled Appointments");
            System.out.println("[8] View Past Appointment Outcome Records");
            System.out.println("[9] Logout");

            // Get the user's choice
            System.out.print("\nPlease select an option (1-9): ");
            int choice = scanner.nextInt();

            // Use a switch-case to handle different menu options
            switch (choice) {
                case 1:
                    // Call method for viewing medical record 
                    //viewMedicalRecord
                    break;
                case 2:
                    // Call method for updating personal information
                    //updateInfo
                    break;
                case 3:
                    // Call method for viewing available appointment slots
                    break;
                case 4:
                    // Call method for scheduling an appointment (to be implemented)
                    break;
                case 5:
                    // Call method for rescheduling an appointment (to be implemented)
                    break;
                case 6:
                    // Call method for canceling an appointment (to be implemented)
                    break;
                case 7:
                    // Call method for viewing scheduled appointments (to be implemented)
                    break;
                case 8:
                    // Call method for viewing past appointment outcomes (to be implemented)
                    break;
                case 9:
                    // Call method for logging out
                    System.out.println("Logging out...");
                    return; // Exit the menu
                default:
                    // Handle invalid input
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
