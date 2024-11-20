package views;

import java.util.List;
import models.Appointment;
import models.DoctorSchedule;
import models.MedicalRecord;
import models.Prescription;
import models.User;
import repositories.UserRepository;

public class PatientView extends View {

    /**
     * Displays the patient menu with options to the console.
     * Prompts the user to enter their choice.
     */
    @Override
    public void displayMenu() {
        System.out.println("Patient Menu:");
        System.out.println("1. View Appointments");
        System.out.println("2. Schedule Appointment");
        System.out.println("3. Access Personal Medical Record");
        System.out.println("4. View Prescriptions");
        System.out.println("5. Update Personal Information");
        System.out.println("6. Logout");
        System.out.print("Enter your choice: ");
    }

    
    /** 
     * @param appointments
     */
    public void displayAppointments(List<Appointment> appointments) {
        UserRepository userR = new UserRepository();
        System.out.println("Your Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println("Appointment ID: " + appointment.getAppointmentId()
                    + ", Doctor ID: " + userR.getDataById(appointment.getDoctorId()).get(0).getName()
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
        UserRepository userR = new UserRepository();
        System.out.println("Available Slots:");
        int index = 1;
        for (DoctorSchedule slot : slots) {
            System.out.println(index + ". Doctor ID: " + slot.getDoctorId()
                    + ", Doctor ID: " + userR.getDataById(slot.getDoctorId()).get(0).getName()
                    + ", Date: " + slot.getDate()
                    + ", Start Time: " + slot.getStartTime()
                    + ", End Time: " + slot.getEndTime());
            index++;
        }
    }
}
