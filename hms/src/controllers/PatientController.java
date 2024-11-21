package controllers;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import models.Appointment;
import models.AppointmentOutcomeRecord;
import models.DoctorSchedule;
import models.MedicalRecord;
import models.Prescription;
import models.User;
import services.AppointmentOutcomeService;
import services.AppointmentService;
import services.DoctorScheduleService;
import services.MedicalRecordService;
import services.PrescriptionService;
import services.UserService;
import views.PatientView;

/**
 * PatientController handles patient interactions.
 */
public class PatientController extends Controller {

    @SuppressWarnings("unused")

    private User patientUser;
    private AppointmentService appointmentService;
    private DoctorScheduleService doctorScheduleService;
    private MedicalRecordService medicalRecordService;
    private PrescriptionService prescriptionService;
    private UserService userService;
    private PatientView patientView;
    private AppointmentOutcomeService outcomeService;
    private Scanner scanner;

    /**
     * Constructs a new PatientController with the specified patient user and services.
     *
     * @param patientUser           The patient user associated with this controller.
     * @param appointmentService    Service for handling appointments.
     * @param medicalRecordService  Service for managing medical records.
     * @param prescriptionService   Service for processing prescriptions.
     * @param userService           Service for user management.
     * @param doctorScheduleService Service for managing doctor's schedules.
     */
    public PatientController(User patientUser, AppointmentService appointmentService,
                             MedicalRecordService medicalRecordService, PrescriptionService prescriptionService,
                             UserService userService, DoctorScheduleService doctorScheduleService, AppointmentOutcomeService outcomeService) {
        this.patientUser = patientUser;
        this.appointmentService = appointmentService;
        this.doctorScheduleService = doctorScheduleService;
        this.medicalRecordService = medicalRecordService;
        this.prescriptionService = prescriptionService;
        this.userService = userService;
        this.outcomeService = outcomeService;
        this.patientView = new PatientView();
        this.scanner = new Scanner(System.in);
    }

    /**
     *  Overloaded function that starts the admin controller with the specified view.
     *
     */
    @Override
    public void start() {
        super.start(patientView, "9");
    }

    /**
     * Handles the patient's menu choice.
     *
     * @param choice The menu choice selected.
     */
    @Override
    public void handleMenuChoice(String choice) {
        switch (choice) {
            case "1":
                viewAppointments();
                break;
            case "2":
                scheduleAppointment();
                break;
            case "3":
                updateAppointment();
                break;
            case "4":
                cancelAppointment();
                break;
            case "5":
                accessPersonalMedicalRecord();
                break;
            case "6":
                viewPrescriptions();
                break;
            case "7":
                displayAppointmentOutcomeRecords();
                break;
            case "8":
                updatePersonalInformation();
                break;
            case "9":
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    /**
     * Views appointments for the patient.
     */
    private void viewAppointments() {
        List<Appointment> appointments = appointmentService.getAppointmentsForPatient(patientUser.getUserId());
        if (appointments.isEmpty()) {
            System.out.println("You have no appointments.");
        } else {
            patientView.displayAppointments(appointments);
        }
    }

    private void scheduleAppointment() {
        // Retrieve all available slots from all doctors
        List<DoctorSchedule> availableSlots = doctorScheduleService.getAllAvailableSlots();

        if (availableSlots.isEmpty()) {
            System.out.println("No available slots at this time.");
            return;
        }

        // Display available slots to the patient
        patientView.displayAvailableSlots(availableSlots);

        // Prompt patient to select a slot
        System.out.println("Enter the number of the slot you wish to book:");
        String choiceStr = scanner.nextLine();
        int choice;
        try {
            choice = Integer.parseInt(choiceStr);
            if (choice < 1 || choice > availableSlots.size()) {
                System.out.println("Invalid choice.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
            return;
        }

        // Get the selected slot
        DoctorSchedule selectedSlot = availableSlots.get(choice - 1);

        // Prompt for the exact time within the slot
        System.out.println("Enter Appointment Time within the available slot (HH:MM):");
        String time = scanner.nextLine();

        // Validate that the time is within the selected slot's time range
        LocalTime appointmentTime;
        try {
            appointmentTime = LocalTime.parse(time);
            if (appointmentTime.isBefore(selectedSlot.getStartTime()) || appointmentTime.isAfter(selectedSlot.getEndTime().minusMinutes(1))) {
                System.out.println("The time is not within the selected slot's time range.");
                return;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format.");
            return;
        }

        // Schedule the appointment
        String dateStr = selectedSlot.getDate().toString();
        String timeStr = appointmentTime.toString();

        List<Appointment> optionalAppointment = appointmentService.scheduleAppointment(
                patientUser.getUserId(), selectedSlot.getDoctorId(), dateStr, timeStr);

                if (!optionalAppointment.isEmpty()) {
                    System.out.println("Appointment now Pending approval.");
                    System.out.println("Appointment ID: " + optionalAppointment.get(0).getAppointmentId());
                } else {
                    System.out.println("Failed to schedule appointment.");
                }
    }

    /**
     * Accesses the patient's personal medical record.
     */
    private void accessPersonalMedicalRecord() {
        List<MedicalRecord> optionalRecord = medicalRecordService.getMedicalRecordByPatientId(patientUser.getUserId());
        if (optionalRecord.isEmpty()) {
            System.out.println("Medical record not found.");
            return;
        }

        MedicalRecord record = optionalRecord.get(0);
        patientView.displayMedicalRecord(record);


    }

    /**
     * Updates the patient's personal contact information.
     */
    private void updatePersonalInformation() {
        System.out.println("Enter new Contact Info (phone;email):");
        String contactInfo = scanner.nextLine();

        boolean success = userService.updateContactInfo(patientUser.getUserId(), contactInfo);
        if (success) {
            System.out.println("Contact information updated successfully.");
        } else {
            System.out.println("Failed to update contact information.");
        }
    }

    /**
     * Views prescriptions for the patient.
     */
    private void viewPrescriptions() {
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPatientId(patientUser.getUserId());
        if (prescriptions.isEmpty()) {
            System.out.println("You have no prescriptions.");
        } else {
            patientView.displayPrescriptions(prescriptions);
        }
    }

    private void cancelAppointment() {
        viewAppointments();
        System.out.print("Enter AppointmentID to cancel: ");
        String appointmentId = scanner.nextLine();
        appointmentService.cancelAppointment(appointmentId);
    }

    private void updateAppointment() {
        patientView.displayAppointments(appointmentService.getAppointmentsForPatient(patientUser.getUserId()));
        System.out.print("Enter Appointment ID to Reschedule: ");
        String appointmentId = scanner.nextLine();
        appointmentService.rescheduleAppointment(appointmentId);
    }

    private void displayAppointmentOutcomeRecords() {
        List<AppointmentOutcomeRecord> records = outcomeService.getOutcomesByPatientId(patientUser.getUserId());
        if (records.isEmpty()) {
            System.out.println("You have no prescriptions.");
        } else {
            patientView.displayAppointmentOutcomeRecords(records);
        }
    }
}
