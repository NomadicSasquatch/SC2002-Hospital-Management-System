package controllers;

import enums.PrescriptionStatus;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import models.Appointment;
import models.DoctorSchedule;
import models.MedicalRecord;
import models.Prescription;
import models.User;
import services.AppointmentService;
import services.DoctorScheduleService;
import services.InventoryService;
import services.MedicalRecordService;
import services.PrescriptionService;
import services.UserService;
import views.DoctorView;

/**
 * DoctorController handles doctor interactions.
 */
public class DoctorController extends Controller {

    private User doctorUser;
    private UserService userService;
    private DoctorScheduleService doctorScheduleService;
    private AppointmentService appointmentService;
    private MedicalRecordService medicalRecordService;
    private PrescriptionService prescriptionService;
    private InventoryService inventoryService;
    private DoctorView doctorView;
    private Scanner scanner;

    /**
     * Constructs a new `DoctorController` with the specified services and doctor user.
     *
     * @param doctorUser            the user representing the doctor
     * @param appointmentService    the service for managing appointments
     * @param medicalRecordService  the service for managing medical records
     * @param prescriptionService   the service for managing prescriptions
     * @param doctorScheduleService the service for managing doctor schedules
     * @param userService           the service for managing users
     */
    public DoctorController(User doctorUser, AppointmentService appointmentService,
                            MedicalRecordService medicalRecordService, PrescriptionService prescriptionService,
                            DoctorScheduleService doctorScheduleService, UserService userService, InventoryService inventoryService) {
        this.doctorUser = doctorUser;
        this.doctorScheduleService = doctorScheduleService;
        this.appointmentService = appointmentService;
        this.medicalRecordService = medicalRecordService;
        this.prescriptionService = prescriptionService;
        this.inventoryService = inventoryService;
        this.userService = userService;
        this.doctorView = new DoctorView();
        this.scanner = new Scanner(System.in);
    }

    /**
     *  Overloaded function that starts the admin controller with the specified view.
     *
     */
    @Override
    public void start() {
        super.start(doctorView, "6");
    }

    
    /** 
     * @param choice
     */
    @Override
    public void handleMenuChoice(String choice) {
        switch (choice) {
            case "1":
                viewAppointments();
                break;
            case "2":
                updateAppointmentStatus();
                break;
            case "3":
                accessPatientMedicalRecords();
                break;
            case "4":
                writePrescription();
                break;
            case "5":
                manageSchedule();
                break;
            case "6":
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    /**
     * Allows the doctor to manage their schedule.
     */
    private void manageSchedule() {
        String subChoice;
        do {
            doctorView.displayScheduleManagementMenu();
            subChoice = scanner.nextLine();
            switch (subChoice) {
                case "1":
                    addAvailableSlot();
                    break;
                case "2":
                    removeAvailableSlot();
                    break;
                case "3":
                    viewAvailableSlots();
                    break;
                case "4":
                    // Back to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (!subChoice.equals("4"));
    }

    private void addAvailableSlot() {
        System.out.println("Enter Date (YYYY-MM-DD):");
        String dateStr = scanner.nextLine();
        System.out.println("Enter Start Time (HH:MM):");
        String startTimeStr = scanner.nextLine();
        System.out.println("Enter End Time (HH:MM):");
        String endTimeStr = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateStr);
            LocalTime startTime = LocalTime.parse(startTimeStr);
            LocalTime endTime = LocalTime.parse(endTimeStr);

            boolean success = doctorScheduleService.addAvailableSlot(doctorUser.getUserId(), date, startTime, endTime);
            if (success) {
                System.out.println("Available slot added successfully.");
            } else {
                System.out.println("Failed to add available slot.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time format.");
        }
    }

    private void removeAvailableSlot() {
        System.out.println("Enter Date (YYYY-MM-DD) of the slot to remove:");
        String dateStr = scanner.nextLine();
        System.out.println("Enter Start Time (HH:MM) of the slot to remove:");
        String startTimeStr = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateStr);
            LocalTime startTime = LocalTime.parse(startTimeStr);

            boolean success = doctorScheduleService.removeAvailableSlot(doctorUser.getUserId(), date, startTime);
            if (success) {
                System.out.println("Available slot removed successfully.");
            } else {
                System.out.println("Failed to remove available slot.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time format.");
        }
    }

    private void viewAvailableSlots() {
        List<DoctorSchedule> slots = doctorScheduleService.getAvailableSlotsForDoctor(doctorUser.getUserId());
        doctorView.displayAvailableSlots(slots);
    }

    /**
     * Views appointments for the doctor.
     */
    private void viewAppointments() {
        List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorUser.getUserId());
        if (appointments.isEmpty()) {
            System.out.println("You have no appointments.");
        } else {
            doctorView.displayAppointments(appointments);
        }
    }

    /**
     * Updates the status of an appointment.
     */
    private void updateAppointmentStatus() {
        viewAppointments();
        System.out.println("Enter Appointment ID to update:");
        String appointmentId = scanner.nextLine();

        List<Appointment> appointments = appointmentService.getAppointmentById(appointmentId);
        if (appointments.isEmpty()) {
            System.out.println("Appointment not found.");
            return;
        }
    

        Appointment appointment = appointments.get(0);

        // Ensure the appointment belongs to the doctor
        if (!appointment.getDoctorId().equals(doctorUser.getUserId())) {
            System.out.println("You are not authorized to update this appointment.");
            return;
        }

        System.out.println("Enter new status (confirm/cancel):");
        String status = scanner.nextLine();

        if (!status.equalsIgnoreCase("confirm") && !status.equalsIgnoreCase("cancel")) {
            System.out.println("Invalid status.");
            return;
        }
        status = status.equalsIgnoreCase("confirm") ? "confirmed" : "available";

        boolean success = appointmentService.updateAppointmentStatus(appointmentId, status);
        if (success) {
            System.out.println("Appointment status is now: " + status);
        } else {
            System.out.println("Failed to update appointment status.");
        }
    }
    /**
     * Lists all patients who have had appointments with the doctor.
     */
    private void listMyPatients() {
        List<Appointment> appointments = appointmentService.getAppointmentsForDoctor(doctorUser.getUserId());
        if (appointments.isEmpty()) {
            System.out.println("You have no patients.");
            return;
        }

        // Get unique patient IDs
        Set<String> patientIds = appointments.stream()
                .map(Appointment::getPatientId)
                .collect(Collectors.toSet());

        // Retrieve patient details
        List<User> patients = new ArrayList<>();
        for (String patientId : patientIds) {
            User user = userService.getUserById(patientId);
            if (user != null) {
                patients.add(user);
            }
        }

        // Display the list of patients
        doctorView.displayPatientList(patients);
    }

    private void listInventoryItems() {
        if (inventoryService.getAllInventoryItems().isEmpty()) {
            System.out.println("No items in inventory.");
            return;
        }
        doctorView.displayInventoryItems(inventoryService.getAllInventoryItems());
    }

    /**
     * Accesses patient medical records.
     */
    private void accessPatientMedicalRecords() {
        listMyPatients();
        System.out.println("Enter Patient ID to access medical record:");
        String patientId = scanner.nextLine();

        List<MedicalRecord> records = medicalRecordService.getMedicalRecordByPatientId(patientId);
        if (records.isEmpty()) {
            System.out.println("Medical record not found.");
            return;
        }

        MedicalRecord record = records.get(0);
        doctorView.displayMedicalRecord(record);

        // Options to add diagnosis or treatment
        System.out.println("Would you like to add a diagnosis or treatment? (yes/no):");
        String decision = scanner.nextLine();
        if (decision.equalsIgnoreCase("yes")) {
            System.out.println("Enter '1' for Diagnosis, '2' for Treatment:");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    addDiagnosis(patientId);
                    break;
                case "2":
                    addTreatment(patientId);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    /**
     * Adds a diagnosis to a patient's medical record.
     *
     * @param patientId The patient's ID.
     */
    private void addDiagnosis(String patientId) {
        System.out.println("Enter Diagnosis to add:");
        String diagnosis = scanner.nextLine();

        boolean success = medicalRecordService.addDiagnosis(doctorUser.getUserId(), patientId, diagnosis);
        if (success) {
            System.out.println("Diagnosis added successfully.");
        } else {
            System.out.println("Failed to add diagnosis.");
        }
    }

    /**
     * Adds a treatment to a patient's medical record.
     *
     * @param patientId The patient's ID.
     */
    private void addTreatment(String patientId) {
        System.out.println("Enter Treatment to add:");
        String treatment = scanner.nextLine();

        boolean success = medicalRecordService.addTreatment(doctorUser.getUserId(), patientId, treatment);
        if (success) {
            System.out.println("Treatment added successfully.");
        } else {
            System.out.println("Failed to add treatment.");
        }
    }

    /**
     * Writes a prescription for a patient.
     */
    private void writePrescription() {
        listMyPatients();
        System.out.println("Enter Patient ID:");
        String patientId = scanner.nextLine();

        listInventoryItems();
        System.out.println("Enter Medication ID:");
        String medicationId = scanner.nextLine();

        System.out.println("Enter Dosage (e.g., '1 tablet twice a day'):");
        String dosage = scanner.nextLine();

        System.out.println("Enter Quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter Additional Instructions:");
        String instructions = scanner.nextLine();

        Prescription prescription = new Prescription(
                prescriptionService.generatePrescriptionId(),
                null, // Assuming appointment ID is not required here
                patientId,
                doctorUser.getUserId(),
                medicationId,
                inventoryService.getInventoryItemById(medicationId).getMedicationName(),
                dosage,
                quantity,
                instructions,
                PrescriptionStatus.PENDING,
                java.time.LocalDate.now()
        );

        boolean success = prescriptionService.addPrescription(prescription);
        if (success) {
            System.out.println("Prescription written successfully.");
        } else {
            System.out.println("Failed to write prescription.");
        }
    }
}
