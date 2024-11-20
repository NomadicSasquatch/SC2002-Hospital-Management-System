
import controllers.*;
import repositories.*;

import enums.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import models.User;
import services.AppointmentService;
import services.AuthenticationService;
import services.DoctorScheduleService;
import services.InventoryService;
import services.MedicalRecordService;
import services.PrescriptionService;
import services.UserService;
import utils.PasswordUtil;


/**
 * Main is the entry point of the Hospital Management System application.
 */
public class Main {

    
    /**
     * Entry point of the application.
     * @param args startup arguments
     */
    public static void main(String[] args) {
        // Instantiate repositories
        UserRepository userRepository = new UserRepository();
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        PrescriptionRepository prescriptionRepository = new PrescriptionRepository();
        InventoryRepository inventoryRepository = new InventoryRepository();
        ReplenishmentRequestRepository replenishmentRequestRepository = new ReplenishmentRequestRepository();
        DoctorScheduleRepository doctorScheduleRepository = new DoctorScheduleRepository();

        // Instantiate services
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        DoctorScheduleService doctorScheduleService = new DoctorScheduleService(doctorScheduleRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository, userRepository,
                doctorScheduleService);
        MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecordRepository, userRepository);
        PrescriptionService prescriptionService = new PrescriptionService(
                prescriptionRepository, userRepository, inventoryRepository);
        InventoryService inventoryService = new InventoryService(inventoryRepository, replenishmentRequestRepository);
        UserService userService = new UserService(userRepository);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            // Display main menu
            System.out.println("Welcome to the Hospital Management System");
            System.out.println("1. Login");
            System.out.println("2. Register as a Patient");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // Perform login
                    System.out.print("Enter UserID: ");
                    String userID = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    // Password for admin is "password"
                    List<User> optionalUser = authenticationService.authenticate(userID, password);
                    if (!optionalUser.isEmpty()) {
                        User authenticatedUser = optionalUser.get(0);
                        UserRole role = authenticatedUser.getRole();

                        // Route to the appropriate controller based on the user's role
                        Controller controller = null;
                        switch (role) {
                            case UserRole.ADMIN:
                                controller = new AdminController(
                                        authenticatedUser, userService, inventoryService, appointmentService);
                                break;
                            case UserRole.DOCTOR:
                                controller = new DoctorController(
                                        authenticatedUser, appointmentService, medicalRecordService,
                                        prescriptionService, doctorScheduleService, userService, inventoryService);

                                break;
                            case UserRole.PATIENT:
                                controller = new PatientController(
                                        authenticatedUser, appointmentService, medicalRecordService,
                                        prescriptionService, userService, doctorScheduleService);

                                break;
                            case UserRole.PHARMACIST:
                                controller = new PharmacistController(
                                        authenticatedUser, inventoryService, prescriptionService);

                                break;
                            default:
                                System.out.println("Invalid role. Access denied.");
                        }
                        if (controller != null) controller.start();
                    } else {
                        System.out.println("Authentication failed. Please try again.");
                    }
                    break;
                case "2":
                    // Register a new patient
                    registerPatient(scanner, userService, medicalRecordService);
                    break;
                case "3":
                    // Exit the application
                    System.out.println("Exiting the system. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }

        scanner.close();
    }

    /**
     * Handles patient registration.
     *
     * @param scanner              Scanner for user input.
     * @param userService          Service for user management.
     * @param medicalRecordService Service for managing medical records.
     */
    private static void registerPatient(Scanner scanner, UserService userService,
            MedicalRecordService medicalRecordService) {
        System.out.println("=== Patient Registration ===");

        // Collect user information
        System.out.print("Enter userID: ");
        String userID = scanner.nextLine();

        // Check if username already exists
        if (userService.getUserById(userID) != null) {
            System.out.println("userID already exists. Please choose a different userID.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Date of Birth (YYYY-MM-DD): ");
        String dob = scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Contact Info (phone;email): ");
        String contactInfo = scanner.nextLine();
        System.out.print("Enter Blood Type: ");
        String bloodType = scanner.nextLine();

        // Create a new User object
        User newUser = new User(
                userID,
                PasswordUtil.hashPassword(password),
                UserRole.PATIENT,
                name,
                dob,
                gender,
                contactInfo);

        // Add the user to the system
        boolean userAdded = userService.addUser(newUser);
        if (userAdded) {
            // Create a medical record for the new patient
            boolean recordCreated = medicalRecordService.createMedicalRecord(
                    userID, name, dob, gender, contactInfo, bloodType);
            if (recordCreated) {
                System.out.println("Registration successful. You can now log in with your credentials.");
            } else {
                System.out.println("Failed to create medical record. Please contact the administrator.");
            }
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }
}
