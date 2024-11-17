package com.hms;


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
                    viewPatientMedicalRecords();
                    break;
                case 2:
                    // Call method for updating personal information
                    //updateInfo
                    updatePersonalInformation();
                    break;
                case 3:
                    // Call method for viewing available appointment slots
                    viewAvailableAppointmentSlots();
                    break;
                case 4:
                    // Call method for scheduling an appointment (to be implemented)
                    scheduleAppointment();
                    break;
                case 5:
                    // Call method for rescheduling an appointment (to be implemented)
                    rescheduleAppointment();
                    break;
                case 6:
                    // Call method for canceling an appointment (to be implemented)
                    cancelAppointment();
                    break;
                case 7:
                    // Call method for viewing scheduled appointments (to be implemented)
                    viewScheduledAppointments();
                    break;
                case 8:
                    // Call method for viewing past appointment outcomes (to be implemented)
                    viewPastAppointmentOutcomeRecords();
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

    /* DUMMY METHODS FOR UI - TO DELETE ONCE IMPLEMENTATION IS COMPLETED */

    private void viewPatientMedicalRecords() {
        System.out.println("\n========================================================");
        System.out.println("               VIEW PATIENTS MEDICAL RECORDS");
        System.out.println("========================================================");
    
        Scanner scanner = new Scanner(System.in);
    
        // Sample patient and appointment data
        String[][] patients = {
            {"P12345", "John Doe", "1990-05-14", "Male", "O+", "johndoe@example.com"},
            {"P67890", "Jane Smith", "1985-09-22", "Female", "A+", "janesmith@example.com"},
            {"P54321", "Alice Brown", "1992-07-30", "Female", "B-", "alicebrown@example.com"}
        };
    
        String[][] appointments = {
            {"A001", "2024-11-16, 10:00 AM", "P12345", "Hypertension", "Medication prescribed", 
             "Amlodipine 5mg - Take once daily", "Lisinopril 10mg - Take once daily"},
            {"A002", "2024-10-15, 2:30 PM", "P67890", "Seasonal Allergy", "Antihistamines prescribed", 
             "Cetirizine 10mg - Take once daily", "None"},
            {"A003", "2024-09-20, 11:00 AM", "P54321", "Flu", "Rest and hydration", 
             "Paracetamol 500mg - Take twice daily for 5 days", "None"}
        };
    
        // Display appointments
        System.out.printf("%-8s %-20s %-10s\n", "Appt ID", "Appointment Date", "Patient ID");
        System.out.println("---------------------------------------------");
        for (String[] appointment : appointments) {
            System.out.printf("%-8s %-20s %-10s\n", appointment[0], appointment[1], appointment[2]);
        }
    
        // Input appointment ID
        System.out.print("\nEnter Appointment ID to view details: ");
        String appointmentId = scanner.nextLine();
    
        // Search for the appointment
        boolean isValid = false;
        String[] selectedAppointment = null;
        String[] selectedPatient = null;
    
        for (String[] appointment : appointments) {
            if (appointment[0].equalsIgnoreCase(appointmentId)) {
                isValid = true;
                selectedAppointment = appointment;
                // Find patient details
                for (String[] patient : patients) {
                    if (patient[0].equalsIgnoreCase(appointment[2])) {
                        selectedPatient = patient;
                        break;
                    }
                }
                break;
            }
        }
    
        if (!isValid) {
            System.out.println("Invalid Appointment ID. Returning to main menu...");
            return;
        }
    
        // Display selected appointment and patient details
        System.out.println("\n========================================================");
        System.out.println("                 MEDICAL RECORD DETAILS                ");
        System.out.println("========================================================");
        System.out.println("┌──────────────────────────────────────────────────────┐");
        System.out.printf("│ %-52s │%n", "Appointment ID: " + selectedAppointment[0]);
        System.out.printf("│ %-52s │%n", "Appointment Date: " + selectedAppointment[1]);
        System.out.printf("│ %-52s │%n", "Patient ID: " + selectedPatient[0]);
        System.out.printf("│ %-52s │%n", "Name: " + selectedPatient[1]);
        System.out.printf("│ %-52s │%n", "Date of Birth: " + selectedPatient[2]);
        System.out.printf("│ %-52s │%n", "Gender: " + selectedPatient[3]);
        System.out.printf("│ %-52s │%n", "Blood Type: " + selectedPatient[4]);
        System.out.printf("│ %-52s │%n", "Email: " + selectedPatient[5]);
        System.out.println("└──────────────────────────────────────────────────────┘");
    
        System.out.println("\nConsultation Notes and Treatments:");
        System.out.println("------------------------------------------------------");
        System.out.printf("Diagnosis: %s%n", selectedAppointment[3]);
        System.out.printf("Treatment Plan: %s%n", selectedAppointment[4]);
    
        System.out.println("\nPrescriptions:");
        System.out.println("------------------------------------------------------");
        System.out.printf("  %-50s%n", selectedAppointment[5]);
        if (!selectedAppointment[6].equals("None")) {
            System.out.printf("  %-50s%n", selectedAppointment[6]);
        }
        System.out.println("------------------------------------------------------");
    
        returnToMenu();
    }
    

    private void updatePersonalInformation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n======================================================");
        System.out.println("             UPDATE PERSONAL INFORMATION               ");
        System.out.println("======================================================");
    
        // Example of current personal information
        String currentContactInfo = "555-123-4567";
        String currentEmail = "johndoe@example.com";
    
        // Display current personal information
        System.out.println("Current Contact Information: " + currentContactInfo);
        System.out.println("Current Email Address: " + currentEmail);
    
        // Update contact information
        System.out.print("\nEnter new contact information (leave blank to keep current): ");
        String newContactInfo = scanner.nextLine();
        if (!newContactInfo.isBlank()) {
            currentContactInfo = newContactInfo;
        }
    
        // Update email address
        System.out.print("Enter new email address (leave blank to keep current): ");
        String newEmail = scanner.nextLine();
        if (!newEmail.isBlank()) {
            currentEmail = newEmail;
        }
    
        // Confirmation message
        System.out.println("\nPersonal information updated successfully!");
        System.out.println("Updated Contact Information: " + currentContactInfo);
        System.out.println("Updated Email Address: " + currentEmail);
    
        returnToMenu();
    }
    

    private void viewAvailableAppointmentSlots() {
        System.out.println("\n======================================================");
        System.out.println("          AVAILABLE APPOINTMENT SLOTS                ");
        System.out.println("======================================================");
        System.out.println("   Slot 1: 2024-11-16, 10:00 AM");
        System.out.println("   Slot 2: 2024-11-17, 2:30 PM");
        System.out.println("   Slot 3: 2024-11-18, 11:15 AM");
        System.out.println("   Slot 4: 2024-11-20, 3:45 PM");
        System.out.println("   Slot 5: 2024-11-21, 9:30 AM");
        System.out.println("======================================================");
        returnToMenu();
    }

    private void scheduleAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n======================================================");
        System.out.println("          SCHEDULE AN APPOINTMENT                    ");
        System.out.println("======================================================");
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter appointment time (HH:MM): ");
        String time = scanner.nextLine();
        System.out.println("\nAppointment successfully scheduled for:");
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("======================================================");
        returnToMenu();
    }
    

    private void rescheduleAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n======================================================");
        System.out.println("          RESCHEDULE AN APPOINTMENT                  ");
        System.out.println("======================================================");
        System.out.print("Enter appointment ID to reschedule: ");
        int appointmentId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new date (YYYY-MM-DD): ");
        String newDate = scanner.nextLine();
        System.out.print("Enter new time (HH:MM): ");
        String newTime = scanner.nextLine();
        System.out.println("\nAppointment ID " + appointmentId + " successfully rescheduled to:");
        System.out.println("Date: " + newDate);
        System.out.println("Time: " + newTime);
        System.out.println("======================================================");
        returnToMenu();
    }
    

    private void cancelAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n======================================================");
        System.out.println("          CANCEL AN APPOINTMENT                      ");
        System.out.println("======================================================");
        System.out.print("Enter appointment ID to cancel: ");
        int appointmentId = scanner.nextInt();
        System.out.println("Appointment ID " + appointmentId + " has been canceled.");
        System.out.println("======================================================");
        returnToMenu();
    }

    private void viewScheduledAppointments() {
        System.out.println("\n======================================================");
        System.out.println("               SCHEDULED APPOINTMENTS                     ");
        System.out.println("======================================================");
        System.out.println("Appointment ID: A001");
        System.out.println("Date: 2024-11-18, 11:15 AM");
        System.out.println("Status: Waiting for doctor");
        System.out.println("------------------------------------------------------");
        System.out.println("Appointment ID: A002");
        System.out.println("Date: 2024-11-25, 2:30 PM");
        System.out.println("Doctor: Dr. John");
        System.out.println("Status: Awaiting doctor to accept");
        System.out.println("======================================================");
        returnToMenu();
    }

    private void viewPastAppointmentOutcomeRecords() {
        Scanner scanner = new Scanner(System.in);
    
        // Sample appointment data
        String[][] appointments = {
            {"A001", "2024-10-10", "Hypertension", "Dr. Green"},
            {"A002", "2024-09-20", "Flu", "Dr. Taylor"}
        };
    
        System.out.println("\n======================================================");
        System.out.println("          PAST APPOINTMENT OUTCOME RECORDS           ");
        System.out.println("======================================================");
    
        // Display appointment list
        System.out.printf("%-10s %-15s %-30s %-15s\n", "ID", "Date", "Diagnosis", "Seen by");
        System.out.println("------------------------------------------------------");
        for (String[] appointment : appointments) {
            System.out.printf("%-10s %-15s %-30s %-15s\n", 
                appointment[0], appointment[1], appointment[2], appointment[3]);
        }
        System.out.println("======================================================");
    
        // Ask user to select an appointment ID
        System.out.print("Enter Appointment ID to view medical records: ");
        String selectedId = scanner.nextLine();
    
        // Find and display the selected appointment's medical records
        boolean found = false;
        for (String[] appointment : appointments) {
            if (appointment[0].equalsIgnoreCase(selectedId)) {
                found = true;
                System.out.println("\n======================================================");
                System.out.println("             APPOINTMENT MEDICAL RECORD              ");
                System.out.println("======================================================");
                System.out.printf("Appointment ID: %s%n", appointment[0]);
                System.out.printf("Date: %s%n", appointment[1]);
                System.out.printf("Diagnosis: %s%n", appointment[2]);
                System.out.printf("Doctor: %s%n", appointment[3]);
                System.out.println("------------------------------------------------------");
                System.out.println("Diagnosis: Hypertension");
                System.out.println("Treatment: Medication Prescribed");
                System.out.println("Prescriptions:");
                System.out.println("  - Amlodipine 5mg - Take once daily");
                System.out.println("  - Lisinopril 10mg - Take once daily");
                System.out.println("======================================================");
                break;
            }
        }
    
        if (!found) {
            System.out.println("Invalid Appointment ID. Returning to menu...");
        }
    
        returnToMenu();
    }
    

    private void returnToMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress [Enter] to return to the main menu...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        PatientMenu patientMenu = new PatientMenu();
        patientMenu.displayMenu();
    }
}
