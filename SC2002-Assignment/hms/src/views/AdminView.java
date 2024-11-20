package views;

import java.util.List;
import models.Appointment;
import models.InventoryItem;
import models.User;

public class AdminView extends View {

    /**
     * Displays the administrator menu with options Prompts the user to enter
     * their choice.
     */
    @Override
    public void displayMenu() {
        System.out.println("Administrator Menu:");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointment Details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Logout");
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays the staff management menu with options Prompts the user to enter
     * their choice.
     */
    public void displayStaffManagementMenu() {
        System.out.println("Staff Management:");
        System.out.println("1. Add Staff Member");
        System.out.println("2. Update Staff Member");
        System.out.println("3. Remove Staff Member");
        System.out.println("4. List Staff Members");
        System.out.println("5. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays the inventory management menu with options Prompts the user to
     * enter their choice.
     */
    public void displayInventoryManagementMenu() {
        System.out.println("Inventory Management:");
        System.out.println("1. Add Inventory Item");
        System.out.println("2. Update Inventory Item");
        System.out.println("3. Remove Inventory Item");
        System.out.println("4. List Inventory Items");
        System.out.println("5. Update Low Stock Level");
        System.out.println("6. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    /**
     * Displays the given list of staff members to the console.
     *
     * @param staffMembers List of staff members
     */
    public void displayStaffList(List<User> staffMembers) {
        System.out.println("Staff Members:");
        for (User staff : staffMembers) {
            System.out.println("ID: " + staff.getUserId() + ", Name: " + staff.getName()
                    + ", Role: " + staff.getRole() + ", Contact: " + staff.getContactInfo());
        }
    }

    /**
     * Displays the list of inventory items.
     *
     * @param items the list of InventoryItem objects to be displayed
     */
    public void displayInventoryList(List<InventoryItem> items) {
        System.out.println("Inventory Items:");
        for (InventoryItem item : items) {
            System.out.println("Medication ID: " + item.getMedicationId()
                    + ", Name: " + item.getMedicationName()
                    + ", Quantity: " + item.getQuantity()
                    + ", Low Stock Level: " + item.getLowStockLevel());
        }
    }

    /**
     * Displays a list of appointments with their details.
     *
     * @param appointments the list of appointments to display
     */
    public void displayAppointments(List<Appointment> appointments) {
        System.out.println("Appointments:");
        for (Appointment appointment : appointments) {
            System.out.println("Appointment ID: " + appointment.getAppointmentId()
                    + ", Patient ID: " + appointment.getPatientId()
                    + ", Doctor ID: " + appointment.getDoctorId()
                    + ", Date: " + appointment.getDate()
                    + ", Time: " + appointment.getTime()
                    + ", Status: " + appointment.getStatus());
        }
    }
}
