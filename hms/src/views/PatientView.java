package views;

import java.util.List;
import models.Appointment;
import models.AppointmentOutcomeRecord;
import models.DoctorSchedule;
import models.MedicalRecord;
import models.Prescription;

public class PatientView extends View {

    /**
     * Displays the patient menu with options to the console.
     * Prompts the user to enter their choice.
     */
    @Override
    public void displayMenu() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════╗");
        System.out.println("║                           Patient Menu                            ║");
        System.out.println("╠═══════════════════════════════════════════════════════════════════╣");
        System.out.println("║ 1. View Appointments                                              ║");
        System.out.println("║ 2. Schedule Appointment                                           ║");
        System.out.println("║ 3. Reschedule Appointment                                         ║");
        System.out.println("║ 4. Cancel Appointment                                             ║");
        System.out.println("║ 5. Access Personal Medical Record                                 ║");
        System.out.println("║ 6. View Prescriptions                                             ║");
        System.out.println("║ 7. View Appointment Outcome Records                               ║");
        System.out.println("║ 8. Update Personal Information                                    ║");
        System.out.println("║ 9. Logout                                                         ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════╝\n");
        System.out.print("Enter your choice: ");
    }
    
    /** 
     * @param appointments
     */
    public void displayAppointments(List<Appointment> appointments) {
        System.out.println("Your Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println("Appointment ID: " + appointment.getAppointmentId()
                    + ", Doctor ID: " + appointment.getDoctorId()
                    + ", Date: " + appointment.getDate()
                    + ", Time: " + appointment.getTime()
                    + ", Status: " + appointment.getStatus());
        }
    }

    /**
     * Displays the detailed medical record of a patient.
     *
     * @param record the MedicalRecord object containing the patient's medical information
     */
    public void displayMedicalRecord(MedicalRecord record) {
        System.out.println("Your Medical Record:");
        System.out.println("Patient ID: " + record.getPatientId());
        System.out.println("Name: " + record.getName());
        System.out.println("Date of Birth: " + record.getDateOfBirth());
        System.out.println("Gender: " + record.getGender());
        System.out.println("Blood Type: " + record.getBloodType());
        System.out.println("Past Diagnoses: " + String.join(", ", record.getPastDiagnoses()));
        System.out.println("Treatments: " + String.join(", ", record.getTreatments()));
    }

    /**
     * Displays a list of prescriptions.
     *
     * @param prescriptions the list of prescriptions to display
     */
    public void displayPrescriptions(List<Prescription> prescriptions) {
        System.out.println("Your Prescriptions:");
        for (Prescription prescription : prescriptions) {
            System.out.println("Prescription ID: " + prescription.getPrescriptionId()
                    + ", Medication: " + prescription.getMedicationName()
                    + ", Dosage: " + prescription.getDosage()
                    + ", Quantity: " + prescription.getQuantity()
                    + ", Status: " + prescription.getStatus());
        }
    }

    /**
     * Displays the available slots to the console.
     *
     * @param slots List of available slots within doctor's schedule
     */
    public void displayAvailableSlots(List<DoctorSchedule> slots) {
        System.out.println("Available Slots:");
        int index = 1;
        for (DoctorSchedule slot : slots) {
            System.out.println(index + ". Doctor ID: " + slot.getDoctorId()
                    + ", Date: " + slot.getDate()
                    + ", Start Time: " + slot.getStartTime()
                    + ", End Time: " + slot.getEndTime());
            index++;
        }
    }
    public void displayAppointmentOutcomeRecords(List<AppointmentOutcomeRecord> records) {
        System.out.println("Your Appointment Outcome Records:");
        for (AppointmentOutcomeRecord record : records) {
            System.out.println("Appointment Outcome ID: " + record.getAppointmentOutcomeId()
                    + ", Appointment ID: " + record.getAppointmentId()
                    + ", Doctor ID: " + record.getDoctorId()
                    + ", Date: " + record.getDate()
                    + "\nDiagnosis: " + record.getDiagnosis()
                    + "\nTreatment: " + record.getTreatment()
                    + "\nNotes: " + record.getNotes()
                    + "\n--------------------------------------");
        }
    }
}
