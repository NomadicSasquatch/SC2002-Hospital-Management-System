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

        // Display loading animation
        showLoadingAnimation();

        // Display the "HMS" logo after animation
        displayHMSLogo();

        while (!exit) {
            // Display main menu
            System.out.println("Welcome to the Hospital Management System");
            System.out.println("1. Login");
            System.out.println("2. Register");
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
     * Shows a loading animation using ASCII characters.
     */
    private static void showLoadingAnimation() {
        System.out.print("Loading");
        try {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(1000); // Pause for 1 second
                System.out.print(".");
            }
            System.out.println(); // Move to the next line after the animation
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the HMS logo in ASCII characters.
     */
    private static void displayHMSLogo() {
        System.out.println("=====================================");
        System.out.println("      H   H  M   M  SSSS");
        System.out.println("      H   H  MM MM  S    ");
        System.out.println("      HHHHH  M M M  SSS  ");
        System.out.println("      H   H  M   M     S ");
        System.out.println("      H   H  M   M  SSSS ");
        System.out.println("=====================================");
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
}
