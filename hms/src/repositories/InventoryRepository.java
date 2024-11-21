package repositories;

import models.InventoryItem;
import utils.CSVReader;
import utils.CSVWriter;

import java.util.ArrayList;
import java.util.List;

import abstract_class.CrudRepository;

/**
 * InventoryRepository handles CRUD operations for InventoryItem entities.
 */
public class InventoryRepository extends CrudRepository<InventoryItem, String> {

    private static final String INVENTORY_CSV_FILE = "SC2002-Assignment/hms/src/data/inventory.csv";

    public InventoryRepository() {
        super(INVENTORY_CSV_FILE);
    }

    /**
     * Updates an existing inventory item.
     *
     * @param item The inventory item with updated information.
     */
    public void updateInventoryItem(InventoryItem item) {
        List<InventoryItem> matchingItems = getDataById(item.getMedicationId());
        if (!matchingItems.isEmpty()) {
            // If there are matching items, update the inventory
            InventoryItem existing = matchingItems.get(0); // Assuming you handle the first match
            removeItem(existing.getMedicationId());
            addItem(item);
            saveRepository();
        }
    }

    /**
     * Retrieves inventory items that are below the low stock level.
     *
     * @return List of inventory items needing replenishment.
     */
    public List<InventoryItem> getItemsBelowStockLevel() {
        List<InventoryItem> lowStockItems = new ArrayList<>();
        for (InventoryItem item : items) {
            if (item.getQuantity() <= item.getLowStockLevel()) {
                lowStockItems.add(item);
            }
        }
        return lowStockItems;
    }

    @Override
    protected InventoryItem fromCSV(String[] record) {
        return InventoryItem.fromCSV(record);
    }
    @Override
    protected String[] toCSV(InventoryItem item) {
        return item.toCSV();
    }
    @Override
    protected String getId(InventoryItem item) {
        return item.getMedicationId();
    }

}
