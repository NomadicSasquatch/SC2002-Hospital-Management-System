package views;

import java.util.List;
import models.Appointment;
import models.InventoryItem;
import models.User;

public class AdminView extends View {

    private static final String BORDER = "==========================================";

    /**
     * Displays the administrator menu with options. Prompts the user to enter
     * their choice.
     */
    @Override
    public void displayMenu() {
        System.out.println(BORDER);
        System.out.println("        *** ADMINISTRATOR MENU ***");
        System.out.println(BORDER);
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointment Details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Logout");
        System.out.println(BORDER);
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays the staff management menu with options. Prompts the user to enter
     * their choice.
     */
    public void displayStaffManagementMenu() {
        System.out.println(BORDER);
        System.out.println("        *** STAFF MANAGEMENT ***");
        System.out.println(BORDER);
        System.out.println("1. Add Staff Member");
        System.out.println("2. Update Staff Member");
        System.out.println("3. Remove Staff Member");
        System.out.println("4. List Staff Members");
        System.out.println("5. Back to Main Menu");
        System.out.println(BORDER);
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays the inventory management menu with options. Prompts the user to
     * enter their choice.
     */
    public void displayInventoryManagementMenu() {
        System.out.println(BORDER);
        System.out.println("        *** INVENTORY MANAGEMENT ***");
        System.out.println(BORDER);
        System.out.println("1. Add Inventory Item");
        System.out.println("2. Update Inventory Item");
        System.out.println("3. Remove Inventory Item");
        System.out.println("4. List Inventory Items");
        System.out.println("5. Update Low Stock Level");
        System.out.println("6. Back to Main Menu");
        System.out.println(BORDER);
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays the given list of staff members to the console.
     *
     * @param staffMembers List of staff members
     */
    public void displayStaffList(List<User> staffMembers) {
        System.out.println(BORDER);
        System.out.println("        *** STAFF MEMBERS LIST ***");
        System.out.println(BORDER);
        if (staffMembers.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            for (User staff : staffMembers) {
                System.out.println(String.format("ID: %-5s | Name: %-20s | Role: %-15s | Contact: %-15s", 
                        staff.getUserId(), staff.getName(), staff.getRole(), staff.getContactInfo()));
            }
        }
        System.out.println(BORDER);
    }

    /**
     * Displays the list of inventory items.
     *
     * @param items the list of InventoryItem objects to be displayed
     */
    public void displayInventoryList(List<InventoryItem> items) {
        System.out.println(BORDER);
        System.out.println("        *** INVENTORY ITEMS LIST ***");
        System.out.println(BORDER);
        if (items.isEmpty()) {
            System.out.println("No inventory items found.");
        } else {
            for (InventoryItem item : items) {
                System.out.println(String.format("ID: %-5s | Name: %-20s | Quantity: %-5d | Low Stock: %-5d", 
                        item.getMedicationId(), item.getMedicationName(), item.getQuantity(), item.getLowStockLevel()));
            }
        }
        System.out.println(BORDER);
    }

    /**
     * Displays a list of appointments with their details.
     *
     * @param appointments the list of appointments to display
     */
    public void displayAppointments(List<Appointment> appointments) {
        System.out.println(BORDER);
        System.out.println("        *** APPOINTMENTS LIST ***");
        System.out.println(BORDER);
        if (appointments.isEmpty()) {
            System.out.println("No appointments found.");
        } else {
            for (Appointment appointment : appointments) {
                System.out.println(String.format("Appoint. ID: %-5s | Patient ID: %-5s | Doctor ID: %-5s | Date: %-10s | Time: %-8s | Status: %-10s", 
                        appointment.getAppointmentId(), appointment.getPatientId(), appointment.getDoctorId(), 
                        appointment.getDate(), appointment.getTime(), appointment.getStatus()));
            }
        }
        System.out.println(BORDER);
    }
}
