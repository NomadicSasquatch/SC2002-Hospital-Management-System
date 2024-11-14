import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginMenu {
    private Map<String, User> users = new HashMap<>();

    public LoginMenu() {
        // Adding sample users for demonstration purposes
        users.put("P123", new Patient("P123", "John Doe", UserRole.PATIENT, "john.doe@example.com", true, LocalDate.of(1990, 5, 10), "password123"));
        users.put("D123", new Doctor("D456", "Dr. Smith", UserRole.DOCTOR, "dr.smith@example.com", true, LocalDate.of(1980, 6, 15), "password123"));
        users.put("PM123", new Pharmacist("P123", "John Doe", UserRole.PHARMACIST, "john.doe@example.com", true, LocalDate.of(1990, 5, 10), "password123"));
        users.put("A123", new Admin("D456", "Dr. Smith", UserRole.ADMINISTRATOR, "dr.smith@example.com", true, LocalDate.of(1980, 6, 15), "password123"));
        
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        // ASCII Art for HMS (Hospital Management System) with a large "+" on both sides
        String asciiTitle = """
         ++++++++++++++++++++++++++++++++++++++++++++++++++++++
         +                H   H  M   M  SSSSS                 +
         +                H   H  MM MM  S                     +
         +                HHHHH  M M M  SSSSS                 +
         +                H   H  M   M      S                 +
         +                H   H  M   M  SSSSS                 +
         ++++++++++++++++++++++++++++++++++++++++++++++++++++++
        """;

        System.out.println(asciiTitle); // Print the large border and title

        // Welcome message
        System.out.println("\t[ Welcome to the Hospital Management System ]\n");

        // Loop for unlimited login attempts
        while (true) {
            System.out.println("[***] Login to Your Hospital Account [***]");
            System.out.println("---------------------------------------------------------");

            // Get user input for Hospital ID and Password
            System.out.print("Enter Hospital ID: ");
            String hospitalID = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            // Checking if the user exists and the password matches
            User user = users.get(hospitalID);

            if (user != null && user.password.equals(password)) {
                System.out.println("\n\tLogin successful. Welcome, " + user.name + "!");
                user.displayMenu(); // Show the appropriate menu for the user role
                break; // Exit the loop if login is successful
            } else {
                System.out.println("\n\t[ERROR] Invalid credentials. Please try again.");
            }
        }
    }

    // Simple main to test the LoginMenu class
    public static void main(String[] args) {
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.displayMenu();
    }
}
