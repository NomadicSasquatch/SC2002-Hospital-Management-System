package views;

import java.util.List;
import models.Appointment;
import models.DoctorSchedule;
import models.InventoryItem;
import models.MedicalRecord;
import models.User;

/**
 * The DoctorView class represents the view component for doctor-related
 * operations in the system. It extends the View class and provides various
 * display methods for doctor functionalities.
 *
 * This class is responsible for displaying: - Doctor's main menu options -
 * Schedule management interface - Available time slots - Appointments - Patient
 * medical records - Patient lists
 *
 * The class follows the View component pattern in the MVC architecture,
 * handling only the presentation logic for doctor-related features.
 *
 * @see View
 * @see DoctorSchedule
 * @see Appointment
 * @see MedicalRecord
 * @see User
 */
public class DoctorView extends View {

    /**
     * Displays the menu options available for the doctor interface.
     */
    @Override
    public void displayMenu() {
        System.out.println("Doctor Menu:");
        System.out.println("1. View Appointments");
        System.out.println("2. Update Appointment Status");
        System.out.println("3. Access Patient Medical Records");
        System.out.println("4. Write Prescription");
        System.out.println("5. Manage Schedule");
        System.out.println("6. Logout");
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays the schedule management menu options to the console. The method
     * prints the menu and prompts user for input.
     */
    public void displayScheduleManagementMenu() {
        System.out.println("Schedule Management:");
        System.out.println("1. Add Available Slot");
        System.out.println("2. Remove Available Slot");
        System.out.println("3. View Available Slots");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    /**
     * Format and show available slots to the console.
     *
     * @param slots List of available slots within doctor's schedule
     */
    public void displayAvailableSlots(List<DoctorSchedule> slots) {
        System.out.println("Your Available Slots:");
        for (DoctorSchedule slot : slots) {
            System.out.println("Date: " + slot.getDate()
                    + ", Start Time: " + slot.getStartTime()
                    + ", End Time: " + slot.getEndTime());
        }
    }

    /**
     * Displays a list of appointments.
     *
     * @param appointments the list of appointments to display
     */
    public void displayAppointments(List<Appointment> appointments) {
        System.out.println("Your Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println("Appointment ID: " + appointment.getAppointmentId()
                    + ", Patient ID: " + appointment.getPatientId()
                    + ", Date: " + appointment.getDate()
                    + ", Time: " + appointment.getTime()
                    + ", Status: " + appointment.getStatus());
        }
    }

    /**
     * Displays the medical record details of a patient.
     *
     * @param record the MedicalRecord object containing the patient's medical
     * information
     */
    public void displayMedicalRecord(MedicalRecord record) {
        System.out.println("Medical Record for Patient ID: " + record.getPatientId());
        System.out.println("Name: " + record.getName());
        System.out.println("Date of Birth: " + record.getDateOfBirth());
        System.out.println("Gender: " + record.getGender());
        System.out.println("Blood Type: " + record.getBloodType());
        System.out.println("Past Diagnoses: " + String.join(", ", record.getPastDiagnoses()));
        System.out.println("Treatments: " + String.join(", ", record.getTreatments()));
    }

    /**
     * Displays a list of patients with their details.
     *
     * @param patients the list of patients to be displayed
     */
    public void displayPatientList(List<User> patients) {
        System.out.println("Your Patients:");
        for (User patient : patients) {
            System.out.println("Patient ID: " + patient.getUserId()
                    + ", Name: " + patient.getName()
                    + ", Date of Birth: " + patient.getDateOfBirth()
                    + ", Gender: " + patient.getGender()
                    + ", Contact Info: " + patient.getContactInfo());
        }
    }

    public void displayInventoryItems(List<InventoryItem> allInventoryItems) {
        System.out.println("Inventory Items:");
        for (InventoryItem item : allInventoryItems) {
            System.out.println("Medicine ID: " + item.getMedicationId()
                    + ", Name: " + item.getMedicationName()
                    + ", Quantity: " + item.getQuantity());
        }
    }
}
