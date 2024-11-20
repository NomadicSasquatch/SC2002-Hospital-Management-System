package controllers;

import enums.UserRole;
import java.util.List;
import java.util.Scanner;
import models.InventoryItem;
import models.ReplenishmentRequest;
import models.User;
import services.AppointmentService;
import services.InventoryService;
import services.UserService;
import utils.PasswordUtil;
import views.AdminView;

/**
 * AdminController handles administrator interactions.
 */
public class AdminController extends Controller {

    private User adminUser;
    private UserService userService;
    private InventoryService inventoryService;
    private AppointmentService appointmentService;
    private AdminView adminView;
    private Scanner scanner;

    /**
     * Constructs an AdminController with the specified services and admin user.
     *
     * @param userService        the service for managing users
     * @param inventoryService   the service for managing inventory
     * @param appointmentService the service for managing appointments
     */
    public AdminController(User adminUser, UserService userService, InventoryService inventoryService,
                           AppointmentService appointmentService) {
        this.adminUser = adminUser;
        this.userService = userService;
        this.inventoryService = inventoryService;
        this.appointmentService = appointmentService;
        this.adminView = new AdminView();
        this.scanner = new Scanner(System.in);
    }

    /**
     *  Overloaded function that starts the admin controller with the specified view.
     *
     */
    public void start() {
        super.start(adminView, "5");
    }

    /**
     * Handles the user's menu choice and performs the corresponding action.
     *
     * @param choice the menu option selected by the user
     */
    @Override
    public void handleMenuChoice(String choice) {
        switch (choice) {
            case "1":
                manageHospitalStaff();
                break;
            case "2":
                viewAppointmentDetails();
                break;
            case "3":
                manageMedicationInventory();
                break;
            case "4":
                approveReplenishmentRequests();
                break;
            case "5":
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please select a valid option.");
        }
    }

    /**
     * Manages hospital staff (add, update, remove, list staff).
     */
    private void manageHospitalStaff() {
        String subChoice;
        do {
            adminView.displayStaffManagementMenu();
            subChoice = scanner.nextLine();
            switch (subChoice) {
                case "1":
                    addStaffMember();
                    break;
                case "2":
                    updateStaffMember();
                    break;
                case "3":
                    removeStaffMember();
                    break;
                case "4":
                    listStaffMembers();
                    break;
                case "5":
                    // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (!subChoice.equals("5"));
    }

    /**
     * Adds a new staff member.
     */
    private void addStaffMember() {
        System.out.println("Enter Staff ID:");
        String userId = scanner.nextLine();
        System.out.println("Enter Password:");
        String password = PasswordUtil.hashPassword(scanner.nextLine());
        System.out.println("Enter Role (Doctor/Pharmacist):");
        UserRole role = UserRole.valueOf(scanner.nextLine().toUpperCase());
        System.out.println("Enter Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Date of Birth (YYYY-MM-DD):");
        String dob = scanner.nextLine();
        System.out.println("Enter Gender:");
        String gender = scanner.nextLine();
        System.out.println("Enter Contact Info (phone;email):");
        String contactInfo = scanner.nextLine();

        User newUser = new User(
                userId,
                password, // Default password
                role,
                name,
                dob,
                gender,
                contactInfo
        );

        boolean success = userService.addUser(newUser);
        if (success) {
            System.out.println("Staff member added successfully.");
        } else {
            System.out.println("Failed to add staff member.");
        }
    }

    /**
     * Updates an existing staff member's information.
     */
    private void updateStaffMember() {
        System.out.println("Enter Staff ID to update:");
        String userId = scanner.nextLine();

        User existingUser = userService.getUserById(userId);
        if (existingUser == null || (!existingUser.getRole().equals(UserRole.DOCTOR)
                && !existingUser.getRole().equals(UserRole.PHARMACIST))) {
            System.out.println("Staff member not found.");
            return;
        }

        System.out.println("Enter new Contact Info (phone;email):");
        String contactInfo = scanner.nextLine();

        existingUser.setContactInfo(contactInfo);

        boolean success = userService.updateUser(existingUser);
        if (success) {
            System.out.println("Staff member updated successfully.");
        } else {
            System.out.println("Failed to update staff member.");
        }
    }

    /**
     * Removes a staff member.
     */
    private void removeStaffMember() {
        System.out.println("Enter Staff ID to remove:");
        String userId = scanner.nextLine();

        boolean success = userService.removeUser(userId);
        if (success) {
            System.out.println("Staff member removed successfully.");
        } else {
            System.out.println("Failed to remove staff member.");
        }
    }

    /**
     * Lists staff members with filtering options.
     */
    private void listStaffMembers() {
        System.out.println("Filter by Role (Doctor/Pharmacist/All):");
        String roleFilter = scanner.nextLine().trim();

        List<User> staffMembers;

        if (roleFilter.equalsIgnoreCase("All")) {
            staffMembers = userService.getAllUsers(); // Fetch all users
        } else {
            try {
                UserRole role = UserRole.valueOf(roleFilter.toUpperCase());
                staffMembers = userService.getStaffByRole(role); // Fetch users by role
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role. Please enter 'Doctor', 'Pharmacist', or 'All'.");
                return;
            }
        }

        if (staffMembers.isEmpty()) {
            System.out.println("No staff members found.");
        } else {
            System.out.println("Staff Members:");
            for (User user : staffMembers) {
                System.out.println("ID: " + user.getUserId()
                        + ", Name: " + user.getName()
                        + ", Role: " + user.getRole()
                        + ", Date of Birth: " + user.getDateOfBirth()
                        + ", Gender: " + user.getGender()
                        + ", Contact: " + user.getContactInfo());
            }
        }
    }


    /**
     * Views appointment details.
     */
    private void viewAppointmentDetails() {
        System.out.println("Appointment Details:");
        adminView.displayAppointments(appointmentService.getAllAppointments());
    }

    /**
     * Manages medication inventory (add, update, remove, list items).
     */
    private void manageMedicationInventory() {
        String subChoice;
        do {
            adminView.displayInventoryManagementMenu();
            subChoice = scanner.nextLine();
            switch (subChoice) {
                case "1":
                    addInventoryItem();
                    break;
                case "2":
                    updateInventoryItem();
                    break;
                case "3":
                    removeInventoryItem();
                    break;
                case "4":
                    listInventoryItems();
                    break;
                case "5":
                    updateLowStockLevel();
                    break;
                case "6":
                    // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (!subChoice.equals("6"));
    }

    /**
     * Adds a new inventory item.
     */
    private void addInventoryItem() {
        System.out.println("Enter Medication ID:");
        String medicationId = scanner.nextLine();
        System.out.println("Enter Medication Name:");
        String medicationName = scanner.nextLine();
        System.out.println("Enter Quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Low Stock Level Alert:");
        int lowStockLevel = Integer.parseInt(scanner.nextLine());

        InventoryItem newItem = new InventoryItem(
                medicationId,
                medicationName,
                quantity,
                lowStockLevel
        );

        boolean success = inventoryService.addInventoryItem(newItem);
        if (success) {
            System.out.println("Inventory item added successfully.");
        } else {
            System.out.println("Failed to add inventory item.");
        }
    }

    /**
     * Updates an existing inventory item's information.
     */
    private void updateInventoryItem() {
        System.out.println("Enter Medication ID to update:");
        String medicationId = scanner.nextLine();

        InventoryItem existingItem = inventoryService.getInventoryItemById(medicationId);
        if (existingItem == null) {
            System.out.println("Inventory item not found.");
            return;
        }

        System.out.println("Enter new Quantity:");
        int quantity = Integer.parseInt(scanner.nextLine());

        existingItem.setQuantity(quantity);

        boolean success = inventoryService.updateInventoryItem(existingItem);
        if (success) {
            System.out.println("Inventory item updated successfully.");
        } else {
            System.out.println("Failed to update inventory item.");
        }
    }

    /**
     * Removes an inventory item.
     */
    private void removeInventoryItem() {
        System.out.println("Enter Medication ID to remove:");
        String medicationId = scanner.nextLine();

        boolean success = inventoryService.removeInventoryItem(medicationId);
        if (success) {
            System.out.println("Inventory item removed successfully.");
        } else {
            System.out.println("Failed to remove inventory item.");
        }
    }

    /**
     * Lists all inventory items.
     */
    private void listInventoryItems() {
        List<InventoryItem> items = inventoryService.getAllInventoryItems();
        adminView.displayInventoryList(items);
    }

    /**
     * Updates the low stock level alert for an inventory item.
     */
    private void updateLowStockLevel() {
        System.out.println("Enter Medication ID to update low stock level:");
        String medicationId = scanner.nextLine();

        InventoryItem existingItem = inventoryService.getInventoryItemById(medicationId);
        if (existingItem == null) {
            System.out.println("Inventory item not found.");
            return;
        }

        System.out.println("Enter new Low Stock Level Alert:");
        int lowStockLevel = Integer.parseInt(scanner.nextLine());

        boolean success = inventoryService.updateLowStockLevel(medicationId, lowStockLevel);
        if (success) {
            System.out.println("Low stock level updated successfully.");
        } else {
            System.out.println("Failed to update low stock level.");
        }
    }

    /**
     * Approves replenishment requests.
     */
    private void approveReplenishmentRequests() {
        List<ReplenishmentRequest> pendingRequests = inventoryService.getPendingReplenishmentRequests();
        if (pendingRequests.isEmpty()) {
            System.out.println("No pending replenishment requests.");
            return;
        }

        for (ReplenishmentRequest request : pendingRequests) {
            System.out.println("Request ID: " + request.getRequestId()
                    + ", Medication ID: " + request.getMedicationId()
                    + ", Quantity: " + request.getQuantity()
                    + ", Pharmacist ID: " + request.getPharmacistId());

            System.out.println("Approve this request? (yes/no):");
            String decision = scanner.nextLine();
            if (decision.equalsIgnoreCase("yes")) {
                boolean success = inventoryService.approveReplenishmentRequest(request.getRequestId());
                if (success) {
                    System.out.println("Replenishment request approved.");
                } else {
                    System.out.println("Failed to approve replenishment request.");
                }
            } else {
                System.out.println("Replenishment request not approved.");
            }
        }
    }
}