package com.hms;


import java.util.Scanner;

public class DoctorMenu implements Menu {
 
    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("");
            System.out.println("--------------------- Doctor Menu ---------------------");
            System.out.println("[1] View Patient Medical Records");
            System.out.println("[2] Update Patient Medical Records");
            System.out.println("[3] View Personal Schedule");
            System.out.println("[4] Set Availability for Appointments");
            System.out.println("[5] Accept or Decline Appointment Requests");
            System.out.println("[6] View Upcoming Appointments");
            System.out.println("[7] Record Appointment Outcome");
            System.out.println("[8] Logout");

            System.out.print("\nPlease select an option (1-8): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // View Patient Medical Records
                    viewPatientMedicalRecords();
                    break;

                case 2:
                    // Update Patient Medical Records
                    updatePatientMedicalRecords();
                    break;

                case 3:
                    // View Personal Schedule
                    viewPersonalSchedule();
                    break;

                case 4:
                    // Set Availability for Appointments
                    setAvailabilityForAppointments();
                    break;

                case 5:
                    // Accept or Decline Appointment Requests
                    acceptOrDeclineAppointmentRequests();
                    break;

                case 6:
                    // View Upcoming Appointments
                    viewUpcomingAppointments();
                    break;

                case 7:
                    // Record Appointment Outcome
                    recordAppointmentOutcome();
                    break;

                case 8:
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

    // Test Case 9: View Patient Medical Records
private void viewPatientMedicalRecords() {
    System.out.println("\n========================================================");
    System.out.println("               VIEW PATIENTS MEDICAL RECORDS");
    System.out.println("========================================================");

    Scanner scanner = new Scanner(System.in);

    // Sample patient data
    String[][] patients = {
        {"P12345", "John Doe", "1990-05-14", "Male", "O+", "johndoe@example.com"},
        {"P67890", "Jane Smith", "1985-09-22", "Female", "A+", "janesmith@example.com"},
        {"P54321", "Alice Brown", "1992-07-30", "Female", "B-", "alicebrown@example.com"}
    };

    // Display patient list
    System.out.printf("%-10s %-20s %-12s %-8s\n", "Patient ID", "Name", "Date of Birth", "Gender");
    System.out.println("--------------------------------------------------------");
    for (String[] patient : patients) {
        System.out.printf("%-10s %-20s %-12s %-8s\n", patient[0], patient[1], patient[2], patient[3]);
    }

    // Input patient ID
    System.out.print("\nEnter Patient ID to view medical records: ");
    String patientId = scanner.nextLine();

    // Check if the Patient ID is valid and display the record
    boolean isValid = false;
    String[] selectedPatient = null;

    for (String[] patient : patients) {
        if (patient[0].equalsIgnoreCase(patientId)) {
            isValid = true;
            selectedPatient = patient;
            break;
        }
    }

    if (!isValid) {
        System.out.println("Invalid Patient ID. Returning to main menu...");
        return;
    }

    // Display selected patient's information
    System.out.println("┌──────────────────────────────────────────────────────┐");
    System.out.printf("│ %-52s │%n", "Patient ID: " + selectedPatient[0]);
    System.out.printf("│ %-52s │%n", "Name: " + selectedPatient[1]);
    System.out.printf("│ %-52s │%n", "Date of Birth: " + selectedPatient[2]);
    System.out.printf("│ %-52s │%n", "Gender: " + selectedPatient[3]);
    System.out.printf("│ %-52s │%n", "Blood Type: " + selectedPatient[4]);
    System.out.printf("│ %-52s │%n", "Email: " + selectedPatient[5]);
    System.out.println("└──────────────────────────────────────────────────────┘");

    // Sample medical records for demonstration with appointment IDs
    String[][] appointments = {
        {"A001", "2024-11-16, 10:00 AM", "Hypertension", "Medication prescribed", "Amlodipine 5mg - Take once daily", "Lisinopril 10mg - Take once daily"},
        {"A002", "2024-10-15, 2:30 PM", "Seasonal Allergy", "Antihistamines prescribed", "Cetirizine 10mg - Take once daily", "None"},
        {"A003", "2024-09-20, 11:00 AM", "Flu", "Rest and hydration", "Paracetamol 500mg - Take twice daily for 5 days", "None"}
    };

    // Display medical records with appointment IDs
    System.out.println("\nMedical Records:");
    for (String[] appointment : appointments) {
        System.out.println("------------------------------------------------------");
        System.out.printf("Appointment ID: %s%n", appointment[0]);
        System.out.printf("Appointment Date: %s%n", appointment[1]);
        System.out.printf("Diagnosis: %s%n", appointment[2]);
        System.out.printf("Treatment Plan: %s%n", appointment[3]);
        System.out.printf("Prescriptions:%n");
        System.out.printf("  %-50s%n", appointment[4]);
        if (!appointment[5].equals("None")) {
            System.out.printf("  %-50s%n", appointment[5]);
        }
        System.out.println("------------------------------------------------------");
    }

    returnToMenu();
}

// Test Case 10: Update Patient Medical Records
private void updatePatientMedicalRecords() {
    System.out.println("\n========================================================");
    System.out.println("              UPDATE PATIENT MEDICAL RECORDS");
    System.out.println("========================================================");

    Scanner scanner = new Scanner(System.in);

    // Sample patient and appointment data
    String[][] appointments = {
        {"A001", "2024-11-16", "P12345", "John Doe", "Hypertension"},
        {"A002", "2024-10-15", "P67890", "Jane Smith", "Seasonal Allergy"},
        {"A003", "2024-09-20", "P54321", "Alice Brown", "Flu"}
    };

    // Display appointment list
    System.out.printf("%-8s %-12s %-10s %-20s %-20s\n", "App. ID", "Date", "Patient ID", "Name", "Diagnosis");
    System.out.println("--------------------------------------------------------------------");
    for (String[] appointment : appointments) {
        System.out.printf("%-8s %-12s %-10s %-20s %-20s\n", appointment[0], appointment[1], appointment[2], appointment[3], appointment[4]);
    }

    // Input appointment ID
    System.out.print("\nEnter Appointment ID to update record: ");
    String appointmentId = scanner.nextLine();

    // Check if the Appointment ID is valid
    boolean isValid = false;
    String[] selectedAppointment = null;

    for (String[] appointment : appointments) {
        if (appointment[0].equalsIgnoreCase(appointmentId)) {
            isValid = true;
            selectedAppointment = appointment;
            break;
        }
    }

    if (!isValid) {
        System.out.println("Invalid Appointment ID. Returning to main menu...");
        return;
    }

    // Display selected appointment details
    System.out.println("\nSelected Appointment Details:");
    System.out.printf("Appointment ID: %s%n", selectedAppointment[0]);
    System.out.printf("Date: %s%n", selectedAppointment[1]);
    System.out.printf("Patient ID: %s%n", selectedAppointment[2]);
    System.out.printf("Name: %s%n", selectedAppointment[3]);
    System.out.printf("Current Diagnosis: %s%n", selectedAppointment[4]);

    // Update medical record
    System.out.print("\nEnter new diagnosis: ");
    String diagnosis = scanner.nextLine();
    System.out.print("Enter new treatment: ");
    String treatment = scanner.nextLine();

    // Update logic (mocked for demonstration purposes)
    System.out.println("\nUpdating medical record...");
    System.out.printf("New Diagnosis: %s%n", diagnosis);
    System.out.printf("New Treatment Plan: %s%n", treatment);
    System.out.println("Medical record updated successfully!");
}


    // Test Case 11: View Personal Schedule
    private void viewPersonalSchedule() {
        System.out.println("\n========================================================");
        System.out.println("                VIEW PERSONAL SCHEDULE              ");
        System.out.println("========================================================");

        // Hardcoded upcoming appointments
        String[][] schedule = {
            {"2024-11-18, 9:00 AM", "Patient: Sarah Lee", "Consultation"},
            {"2024-11-18, 2:00 PM", "Patient: Mark Smith", "Follow-up"}
        };

        for (String[] appointment : schedule) {
            System.out.println("\n------------------------------------------------------");
            System.out.printf("Appointment Date: %s%n", appointment[0]);
            System.out.printf("Patient: %s%n", appointment[1]);
            System.out.printf("Type: %s%n", appointment[2]);
            System.out.println("------------------------------------------------------");
        }
        returnToMenu();
    }

    // Test Case 12: Set Availability for Appointments
    private void setAvailabilityForAppointments() {
        System.out.println("\n========================================================");
        System.out.println("               SET AVAILABILITY FOR APPOINTMENTS        ");
        System.out.println("========================================================");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter availability start date (yyyy-MM-dd): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter availability end date (yyyy-MM-dd): ");
        String endDate = scanner.nextLine();

        System.out.println("Setting availability from " + startDate + " to " + endDate);
        // Hardcoded update (in a real scenario, this would be updated in the database)
        System.out.println("Availability updated successfully!");
    }

    // Test Case 13: Accept or Decline Appointment Requests
// Test Case 13: Accept or Decline Appointment Requests
private void acceptOrDeclineAppointmentRequests() {
    System.out.println("\n========================================================");
    System.out.println("         ACCEPT OR DECLINE APPOINTMENT REQUESTS         ");
    System.out.println("========================================================");

    Scanner scanner = new Scanner(System.in);

    // Hardcoded sample appointment data (for demonstration)
    String[][] appointments = {
        {"P12345", "Sarah Lee", "2024-11-18, 9:00 AM"},
        {"P67890", "Mark Smith", "2024-11-18, 2:00 PM"},
        {"P11223", "Alice Johnson", "2024-11-19, 10:00 AM"}
    };

    // Header for the table-like structure
    System.out.printf("%-15s %-20s %-30s%n", "Appointment ID", "Patient Name", "Appointment Date");
    System.out.println("--------------------------------------------------------------");

    // Display available appointments with patient name and appointment time
    for (int i = 0; i < appointments.length; i++) {
        System.out.printf("%-15s %-20s %-30s%n", appointments[i][0], appointments[i][1], appointments[i][2]);
    }

    // Ask user for the appointment ID to accept/decline
    System.out.print("\nEnter the appointment ID to accept/decline: ");
    String appointmentId = scanner.nextLine();

    // Find the appointment and show patient name
    String patientName = null;
    boolean appointmentFound = false;
    for (String[] appointment : appointments) {
        if (appointment[0].equals(appointmentId)) {
            patientName = appointment[1];
            appointmentFound = true;
            break;
        }
    }

    if (appointmentFound) {
        // Ask the user to accept or decline the appointment
        System.out.print("Enter 'accept' or 'decline': ");
        String action = scanner.nextLine();

        if (action.equalsIgnoreCase("accept")) {
            System.out.println("Appointment ID " + appointmentId + " with " + patientName + " accepted. Status updated to 'Confirmed'.");
        } else if (action.equalsIgnoreCase("decline")) {
            System.out.println("Appointment ID " + appointmentId + " with " + patientName + " declined. Status updated to 'Cancelled'.");
        } else {
            System.out.println("Invalid action.");
        }
    } else {
        System.out.println("Appointment ID not found.");
    }
}


    // Test Case 14: View Upcoming Appointments
    private void viewUpcomingAppointments() {
        System.out.println("\n========================================================");
        System.out.println("                  VIEW UPCOMING APPONTMENTS            ");
        System.out.println("========================================================");

        // Hardcoded list of upcoming confirmed appointments
        String[][] appointments = {
            {"2024-11-18, 9:00 AM", "Sarah Lee", "Consultation"},
            {"2024-11-18, 2:00 PM", "Mark Smith", "Follow-up"}
        };

        for (String[] appointment : appointments) {
            System.out.println("\n------------------------------------------------------");
            System.out.printf("Appointment Date: %s%n", appointment[0]);
            System.out.printf("Patient: %s%n", appointment[1]);
            System.out.printf("Type: %s%n", appointment[2]);
            System.out.println("------------------------------------------------------");
        }
        returnToMenu();
    }

// Test Case 15: Record Appointment Outcome
private void recordAppointmentOutcome() {
    System.out.println("\n========================================================");
    System.out.println("               RECORD APPOINTMENT OUTCOME");
    System.out.println("========================================================");

    Scanner scanner = new Scanner(System.in);

    // Sample completed appointments
    String[][] completedAppointments = {
        {"A001", "John Doe", "Completed"},
        {"A002", "Jane Smith", "Completed"},
        {"A003", "Alice Brown", "Completed"}
    };

    // Display completed appointments
    System.out.printf("%-12s %-20s %-15s\n", "Appointment ID", "Patient Name", "Status");
    System.out.println("--------------------------------------------------------");
    for (String[] appointment : completedAppointments) {
        System.out.printf("%-12s %-20s %-15s\n", appointment[0], appointment[1], appointment[2]);
    }

    // Input for Appointment ID
    System.out.print("\nEnter Appointment ID to record outcome: ");
    String appointmentId = scanner.nextLine();

    // Check if the appointment ID is valid
    boolean isValid = false;
    for (String[] appointment : completedAppointments) {
        if (appointment[0].equalsIgnoreCase(appointmentId)) {
            isValid = true;
            break;
        }
    }

    if (!isValid) {
        System.out.println("Invalid Appointment ID. Returning to main menu...");
        return;
    }

    // Input for Diagnosis
    System.out.print("Enter Diagnosis: ");
    String diagnosis = scanner.nextLine();

    // Input for Consultation Notes
    System.out.print("Enter Consultation Notes: ");
    String consultationNotes = scanner.nextLine();

    // Sample medication inventory
    String[][] medicationInventory = {
        {"M001", "Amlodipine", "100"},
        {"M002", "Metformin", "200"},
        {"M003", "Paracetamol", "150"},
        {"M004", "Atorvastatin", "80"}
    };

    // Display medication inventory
    System.out.println("\nAvailable Medications:");
    System.out.printf("%-10s %-20s %-10s\n", "Medicine ID", "Medicine Name", "Stock");
    System.out.println("-------------------------------------------------");
    for (String[] medicine : medicationInventory) {
        System.out.printf("%-10s %-20s %-10s\n", medicine[0], medicine[1], medicine[2]);
    }

    // Input for Prescribed Medications
    System.out.println("\nEnter Prescribed Medications (type 'done' to finish):");
    String[] prescribedMedicines = new String[5]; // Max 5 prescribed medicines
    int[] quantities = new int[5]; // Quantities for the prescribed medicines
    int count = 0;

    while (count < prescribedMedicines.length) {
        System.out.print("Medicine ID " + (count + 1) + ": ");
        String medicineId = scanner.nextLine();

        if (medicineId.equalsIgnoreCase("done")) {
            break;
        }

        boolean found = false;
        for (String[] medicine : medicationInventory) {
            if (medicine[0].equalsIgnoreCase(medicineId)) {
                found = true;
                System.out.print("Enter quantity for " + medicine[1] + ": ");
                quantities[count] = scanner.nextInt();
                scanner.nextLine(); // Clear the newline
                prescribedMedicines[count] = medicine[1];
                break;
            }
        }

        if (!found) {
            System.out.println("Invalid Medicine ID. Please try again.");
        } else {
            count++;
        }
    }

    // Display recorded outcome
    System.out.println("\n========================================================");
    System.out.println("               RECORDED OUTCOME SUMMARY");
    System.out.println("========================================================");
    System.out.printf("Appointment ID: %s%n", appointmentId);
    System.out.printf("Diagnosis: %s%n", diagnosis);
    System.out.printf("Consultation Notes: %s%n", consultationNotes);
    System.out.println("Prescribed Medications:");
    for (int i = 0; i < count; i++) {
        System.out.printf("- %s: %d units%n", prescribedMedicines[i], quantities[i]);
    }

    System.out.println("Outcome recorded successfully!");
}

    private void returnToMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPress [Enter] to return to the main menu...");
        scanner.nextLine();
    }

    public static void main(String[] args) {
        DoctorMenu doctorMenu = new DoctorMenu();
        doctorMenu.displayMenu();
    }
}
