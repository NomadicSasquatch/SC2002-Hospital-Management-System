package views;

import java.util.List;
import models.Appointment;
import models.DoctorSchedule;
import models.MedicalRecord;
import models.Prescription;
import models.User;
import repositories.UserRepository;

/**
 * The PatientView class represents the view component for patient-related
 * operations in the system. It extends the View class and provides various
 * display methods for patient functionalities.
 *
 * This class is responsible for displaying: - Patient's main menu options - 
 * Appointments - Personal medical records - Prescriptions - Available time slots
 * with doctors' details
 *
 * The class follows the View component pattern in the MVC architecture,
 * handling only the presentation logic for patient-related features.
 *
 * @see View
 * @see Appointment
 * @see MedicalRecord
 * @see Prescription
 * @see DoctorSchedule
 * @see User
 */
public class PatientView extends View {

    /**
     * Displays the patient menu with options to the console.
     * Prompts the user to enter their choice.
     */
    @Override
    public void displayMenu() {
        System.out.println("+---------------------------------------------+");
        System.out.println("|               Patient Menu                 |");
        System.out.println("+---------------------------------------------+");
        System.out.println("| 1. View Appointments                        |");
        System.out.println("| 2. Schedule Appointment                    |");
        System.out.println("| 3. Access Personal Medical Record          |");
        System.out.println("| 4. View Prescriptions                      |");
        System.out.println("| 5. Update Personal Information             |");
        System.out.println("| 6. Logout                                  |");
        System.out.println("+---------------------------------------------+");
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays a list of appointments for the patient.
     *
     * @param appointments the list of appointments to display
     */
    public void displayAppointments(List<Appointment> appointments) {
        UserRepository userR = new UserRepository();
        System.out.println("\n+---------------------------------------------+");
        System.out.println("|             Your Appointments               |");
        System.out.println("+---------------------------------------------+");
        for (Appointment appointment : appointments) {
            System.out.println("| Appointment ID: " + String.format("%-10s", appointment.getAppointmentId())
                    + " | Doctor: " + String.format("%-20s", userR.getDataById(appointment.getDoctorId()).get(0).getName())
                    + " | Date: " + String.format("%-10s", appointment.getDate())
                    + " | Time: " + String.format("%-8s", appointment.getTime())
                    + " | Status: " + appointment.getStatus() + " |");
        }
        System.out.println("+---------------------------------------------+");
    }

    /**
     * Displays the detailed medical record of a patient.
     *
     * @param record the MedicalRecord object containing the patient's medical information
     */
    public void displayMedicalRecord(MedicalRecord record) {
        System.out.println("\n+---------------------------------------------+");
        System.out.println("|            Your Medical Record             |");
        System.out.println("+---------------------------------------------+");
        System.out.println("| Patient ID: " + record.getPatientId());
        System.out.println("| Name: " + record.getName());
        System.out.println("| Date of Birth: " + record.getDateOfBirth());
        System.out.println("| Gender: " + record.getGender());
        System.out.println("| Blood Type: " + record.getBloodType());
        System.out.println("| Past Diagnoses: " + String.join(", ", record.getPastDiagnoses()));
        System.out.println("| Treatments: " + String.join(", ", record.getTreatments()));
        System.out.println("+---------------------------------------------+");
    }

    /**
     * Displays a list of prescriptions for the patient.
     *
     * @param prescriptions the list of prescriptions to display
     */
    public void displayPrescriptions(List<Prescription> prescriptions) {
        System.out.println("\n+---------------------------------------------+");
        System.out.println("|             Your Prescriptions             |");
        System.out.println("+---------------------------------------------+");
        for (Prescription prescription : prescriptions) {
            System.out.println("| Prescription ID: " + String.format("%-10s", prescription.getPrescriptionId())
                    + " | Medication: " + String.format("%-20s", prescription.getMedicationName())
                    + " | Dosage: " + String.format("%-8s", prescription.getDosage())
                    + " | Quantity: " + String.format("%-8s", prescription.getQuantity())
                    + " | Status: " + prescription.getStatus() + " |");
        }
        System.out.println("+---------------------------------------------+");
    }

    /**
     * Displays the available slots to the console, including the doctor's details.
     *
     * @param slots List of available slots within doctor's schedule
     */
    public void displayAvailableSlots(List<DoctorSchedule> slots) {
        UserRepository userR = new UserRepository();
        System.out.println("\n+---------------------------------------------+");
        System.out.println("|             Available Slots                 |");
        System.out.println("+---------------------------------------------+");
        int index = 1;
        for (DoctorSchedule slot : slots) {
            System.out.println("| " + String.format("%-3d", index) + ". Doctor ID: " + String.format("%-8s", slot.getDoctorId())
                    + " | Doctor Name: " + String.format("%-20s", userR.getDataById(slot.getDoctorId()).get(0).getName())
                    + " | Date: " + String.format("%-10s", slot.getDate())
                    + " | Start Time: " + String.format("%-8s", slot.getStartTime())
                    + " | End Time: " + slot.getEndTime() + " |");
            index++;
        }
        System.out.println("+---------------------------------------------+");
    }
}
