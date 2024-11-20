package services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import models.InventoryItem;
import models.ReplenishmentRequest;
import repositories.InventoryRepository;
import repositories.ReplenishmentRequestRepository;

/**
 * InventoryService handles business logic related to inventory management.
 */
public class InventoryService {

    private InventoryRepository inventoryRepository;
    private ReplenishmentRequestRepository replenishmentRequestRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            ReplenishmentRequestRepository replenishmentRequestRepository) {
        this.inventoryRepository = inventoryRepository;
        this.replenishmentRequestRepository = replenishmentRequestRepository;
    }

    /**
     * Retrieves all inventory items.
     *
     * @return List of all inventory items.
     */
    public List<InventoryItem> getAllInventoryItems() {
        return inventoryRepository.getAllData();
    }
    /**
     * Adds a new inventory item.
     *
     * @param item The inventory item to add.
     * @return True if the item was added successfully.
     */
    public boolean addInventoryItem(InventoryItem item) {
        List<InventoryItem> existingItem = inventoryRepository.getDataById(item.getMedicationId());
        if (!existingItem.isEmpty()) {
            System.out.println("Item with the same ID already exists.");
            return false;
        }
        inventoryRepository.addItem(item);
        System.out.println("Inventory item added successfully.");
        return true;
    }

    /**
     * Updates an existing inventory item.
     *
     * @param item The inventory item with updated information.
     * @return True if the item was updated successfully.
     */
    public boolean updateInventoryItem(InventoryItem item) {
        List<InventoryItem> existingItem = inventoryRepository.getDataById(item.getMedicationId());
        if (existingItem.isEmpty()) {
            System.out.println("Item not found.");
            return false;
        }
        inventoryRepository.updateInventoryItem(item);
        System.out.println("Inventory item updated successfully.");
        return true;
    }

    /**
     * Removes an inventory item by its medication ID.
     *
     * @param medicationId The ID of the medication to remove.
     * @return True if the item was removed successfully.
     */
    public boolean removeInventoryItem(String medicationId) {
        List<InventoryItem> existingItem = inventoryRepository.getDataById(medicationId);
        if (existingItem.isEmpty()) {
            System.out.println("Item not found.");
            return false;
        }
        inventoryRepository.removeItem(medicationId);
        System.out.println("Inventory item removed successfully.");
        return true;
    }

    /**
     * Retrieves inventory items that are below the low stock level.
     *
     * @return List of inventory items needing replenishment.
     */
    public List<InventoryItem> getItemsBelowStockLevel() {
        return inventoryRepository.getItemsBelowStockLevel();
    }

    /**
     * Submits a replenishment request for a medication.
     *
     * @param medicationId The ID of the medication.
     * @param quantity     The quantity requested.
     * @param pharmacistId The ID of the pharmacist submitting the request.
     * @return True if the request was submitted successfully.
     */
    public boolean submitReplenishmentRequest(String medicationId, int quantity, String pharmacistId) {
        List<InventoryItem> existingItem = inventoryRepository.getDataById(medicationId);
        if (existingItem.isEmpty()) {
            System.out.println("Medication not found.");
            return false;
        }

        // Create a new replenishment request
        String requestId = generateRequestId();
        ReplenishmentRequest request = new ReplenishmentRequest(
                requestId,
                medicationId,
                quantity,
                pharmacistId,
                "pending"
        );

        replenishmentRequestRepository.addItem(request);
        System.out.println("Replenishment request submitted successfully.");
        return true;
    }

   /**
     * Approves a replenishment request.
     *
     * @param requestId The ID of the request to approve.
     * @return True if the request was approved successfully.
     */
    public boolean approveReplenishmentRequest(String requestId) {
        // Retrieve requests matching the given requestId
        List<ReplenishmentRequest> requests = replenishmentRequestRepository.getDataById(requestId);
        if (requests.isEmpty()) {
            System.out.println("Replenishment request not found.");
            return false;
        }
    
        // Process the first matching request
        ReplenishmentRequest request = requests.get(0);
        if (!request.getStatus().equalsIgnoreCase("pending")) {
            System.out.println(request.getStatus());
            System.out.println("Replenishment request is not pending.");
            return false;
        }
    
        // Retrieve inventory items matching the medication ID
        List<InventoryItem> inventoryItems = inventoryRepository.getDataById(request.getMedicationId());
        if (inventoryItems.isEmpty()) {
            System.out.println("Medication not found in inventory.");
            return false;
        }
    
        // Update the first matching inventory item
        InventoryItem item = inventoryItems.get(0);
        item.setQuantity(item.getQuantity() + request.getQuantity());
        inventoryRepository.updateInventoryItem(item);
    
        // Update the request status
        request.setStatus("approved");
        replenishmentRequestRepository.updateReplenishmentRequest(request);
    
        System.out.println("Replenishment request approved and inventory updated.");
        return true;
    }

    /**
     * Retrieves all replenishment requests.
     *
     * @return List of all replenishment requests.
     */
    public List<ReplenishmentRequest> getAllReplenishmentRequests() {
        return replenishmentRequestRepository.getAllData();
    }


    /**
     * Updates the low stock level alert for a medication.
     *
     * @param medicationId The ID of the medication.
     * @param newLowStockLevel The new low stock level.
     * @return True if the low stock level was updated successfully.
     */
    public boolean updateLowStockLevel(String medicationId, int newLowStockLevel) {
        List<InventoryItem> items = inventoryRepository.getDataById(medicationId);
        if (items.isEmpty()) {
            System.out.println("Medication not found.");
            return false;
        }
    
        // Assuming only the first matching item needs to be updated
        InventoryItem item = items.get(0);
        item.setLowStockLevel(newLowStockLevel);
        inventoryRepository.updateInventoryItem(item);
        System.out.println("Low stock level updated successfully.");
        return true;
    }

    /**
     * Generates a unique replenishment request ID.
     *
     * @return A unique request ID.
     */
    private String generateRequestId() {
        // Simple ID generation logic; ensure uniqueness in production
        return "R" + (replenishmentRequestRepository.getAllData().size() + 1);
    }
    
    /**
     * Retrieves pending replenishment requests.
     *
     * @return List of pending replenishment requests.
     */
    public List<ReplenishmentRequest> getPendingReplenishmentRequests() {
        List<ReplenishmentRequest> allRequests = replenishmentRequestRepository.getAllData();
        return allRequests.stream()
            .filter(request -> request.getStatus().equalsIgnoreCase("pending"))
            .collect(Collectors.toList());
    }


    /**
     * Retrieves an inventory item by its medication ID.
     *
     * @param medicationId the ID of the medication to retrieve
     * @return the inventory item with the specified medication ID, or {@code null} if not found
     */
    public InventoryItem getInventoryItemById(String medicationId) {
        List<InventoryItem> items = inventoryRepository.getDataById(medicationId);
        return items.isEmpty() ? null : items.get(0); // Return the first match or null if empty
    }
}
