import controllers.*;
import repositories.*;
import enums.UserRole;
import java.util.List;
import java.util.Scanner;
import models.User;
import services.*;
import utils.PasswordUtil;

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
        AppointmentOutcomeRepository outcomeRepository = new AppointmentOutcomeRepository();

        // Instantiate services
        AuthenticationService authenticationService = new AuthenticationService(userRepository);
        DoctorScheduleService doctorScheduleService = new DoctorScheduleService(doctorScheduleRepository);
        AppointmentService appointmentService = new AppointmentService(appointmentRepository, userRepository,
                doctorScheduleService);
        AppointmentOutcomeService outcomeService = new AppointmentOutcomeService(outcomeRepository, appointmentRepository);
        MedicalRecordService medicalRecordService = new MedicalRecordService(medicalRecordRepository, userRepository);
        PrescriptionService prescriptionService = new PrescriptionService(
                prescriptionRepository, userRepository, inventoryRepository);
        InventoryService inventoryService = new InventoryService(inventoryRepository, replenishmentRequestRepository);
        UserService userService = new UserService(userRepository);

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;


        // Display the "HMS" logo after animation
        displayWelcomeBanner();

        while (!exit) {
            // Display main menu

            displayMainMenu();

            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    // Perform login
                    System.out.print("Enter UserID: ");
                    String userID = scanner.nextLine();
                    System.out.print("Enter Password: ");
                    String password = scanner.nextLine();
                    // Password for admin is "password"2
                    List<User> optionalUser = authenticationService.authenticate(userID, password);
                    if (!optionalUser.isEmpty()) {
                        User authenticatedUser = optionalUser.get(0);

                        // Check if the user is using the default password
                        if (authenticationService.isUsingDefaultPassword(authenticatedUser)) {
                            System.out.println("You are using the default password. Please change your password.");
                            boolean passwordChanged = false;

                            while (!passwordChanged) {
                                System.out.print("Enter new password: ");
                                String newPassword = scanner.nextLine();
                                System.out.print("Confirm new password: ");
                                String confirmPassword = scanner.nextLine();

                                if (newPassword.equals(confirmPassword)) {
                                    passwordChanged = authenticationService.changePassword(userID, "123", newPassword);
                                } else {
                                    System.out.println("Passwords do not match. Please try again.");
                                }
                            }

                            System.out.println("Password changed successfully. Please log in again.");
                            continue; // Restart the loop to allow the user to log in again
                        }

                        UserRole role = authenticatedUser.getRole();

                        // Route to the appropriate controller based on the user's role
                        Controller controller = null;
                        switch (role) {
                            case ADMIN:
                                controller = new AdminController(
                                        authenticatedUser, userService, inventoryService, appointmentService);
                                break;
                            case DOCTOR:
                                controller = new DoctorController(
                                    authenticatedUser, appointmentService, medicalRecordService,
                                    prescriptionService, doctorScheduleService, userService, inventoryService, outcomeService);
                                break;
                            case PATIENT:
                                controller = new PatientController(
                                        authenticatedUser, appointmentService, medicalRecordService,
                                        prescriptionService, userService, doctorScheduleService, outcomeService);
                                break;
                            case PHARMACIST:
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
     * Shows a loading animation using ASCII characters.
     */


 
    /**
     * Handles patient registration.
     *
     * @param scanner              Scanner for user input.
     * @param userService          Service for user management.
     * @param medicalRecordService Service for managing medical records.
     */
    private static void registerPatient(Scanner scanner, UserService userService,
            MedicalRecordService medicalRecordService) {
        System.out.println("=== User Registration ===");

        // Collect user information
        System.out.print("Enter userID: ");
        String userID = scanner.nextLine();

        // Check if username already exists
        if (userService.getUserById(userID) != null) {
            System.out.println("userID already exists. Please choose a different userID.");
            return;
        }

        System.out.println("Available roles: ADMIN, DOCTOR, PATIENT, PHARMACIST");
        System.out.print("Enter Role: ");
        String roleInput = scanner.nextLine();
        UserRole role;
        try {
            role = UserRole.valueOf(roleInput.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid role. Registration failed.");
            return;
        }
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
                PasswordUtil.hashPassword("12345"),
                role,
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
                System.out.println("Registration successful. You can now log in with your credentials. User registered successfully with default password '12345'.\"");
            } else {
                System.out.println("Failed to create medical record. Please contact the administrator.");
            }
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }


        private static void displayWelcomeBanner() {
            System.out.println("==================================================");
            System.out.println("  __        __   _                                  ");
            System.out.println("  \\ \\      / /__| | ___ ___  _ __ ___   ___   ");
            System.out.println("   \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\");
            System.out.println("    \\ V  V /  __/ | (_| (_) | | | | | |  __/ ");
            System.out.println("     \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___| ");
            System.out.println("                                                    ");
            System.out.println("==================================================\n");
        }
        

    /**
     * Displays the main menu options.
     */
    private static void displayMainMenu() {
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║        Hospital Management System (HMS)        ║");
        System.out.println("╠════════════════════════════════════════════════╣");
        System.out.println("║ 1. Login                                       ║");
        System.out.println("║ 2. Registeration                               ║");
        System.out.println("║ 3. Exit                                        ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
    }
}
