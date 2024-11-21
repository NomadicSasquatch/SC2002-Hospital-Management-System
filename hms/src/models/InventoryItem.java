package models;

/**
 * Represents an item in the medical inventory system. This class manages
 * individual medication items, tracking their unique identifiers, names,
 * quantities, and low stock thresholds.
 * <p>
 * Each InventoryItem maintains:
 * <ul>
 * <li>A unique medication ID</li>
 * <li>The medication name</li>
 * <li>Current quantity in stock</li>
 * <li>Low stock level threshold for alerts</li>
 * </ul>
 * <p>
 * The class provides functionality for:
 * <ul>
 * <li>Converting to and from CSV format for data persistence</li>
 * <li>Managing stock levels</li>
 * <li>Tracking inventory status</li>
 * </ul>
 */
public class InventoryItem {

    private String medicationId;
    private String medicationName;
    private int quantity;
    private int lowStockLevel;

    // Constructors, getters, and setters
    public InventoryItem() {
    }

    public InventoryItem(String medicationId, String medicationName, int quantity, int lowStockLevel) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.quantity = quantity;
        this.lowStockLevel = lowStockLevel;
    }

    /**
     * Converts the inventory item to a CSV record.
     *
     * @return An array of strings representing the CSV record.
     */
    public String[] toCSV() {
        return new String[]{
            medicationId,
            medicationName,
            String.valueOf(quantity),
            String.valueOf(lowStockLevel)
        };
    }

    /**
     * Creates an InventoryItem object from a CSV record.
     *
     * @param record An array of strings representing the CSV record.
     * @return An InventoryItem object.
     */
    public static InventoryItem fromCSV(String[] record) {
        return new InventoryItem(
                record[0],
                record[1],
                Integer.parseInt(record[2]),
                Integer.parseInt(record[3])
        );
    }

    /**
     * Retrieves the medication ID.
     *
     * @return the medication ID
     */
    public String getMedicationId() {
        return medicationId;
    }

    /**
     * Returns the quantity of the item.
     *
     * @return the quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Retrieves the low stock level for this inventory item.
     *
     * @return the low stock level
     */
    public int getLowStockLevel() {
        return lowStockLevel;
    }

    /**
     * Sets the low stock threshold level for this inventory item. When the
     * current quantity falls below this level, the item is considered low in
     * stock.
     *
     * @param newLowStockLevel the new low stock threshold level to set
     */
    public void setLowStockLevel(int newLowStockLevel) {
        lowStockLevel = newLowStockLevel;
    }

    /**
     * Sets the quantity of the inventory item to the specified value.
     *
     * @param i The new quantity value to set
     */
    public void setQuantity(int i) {
        quantity = i;
    }

    /**
     * Gets the name of the medication.
     *
     * @return The name of the medication as a String.
     */
    public String getMedicationName() {
        return medicationName;
    }
}
