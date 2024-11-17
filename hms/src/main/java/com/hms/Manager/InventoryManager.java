package com.hms.Manager;

import java.util.Arrays;

import com.hms.Medication;
import com.hms.impl.IDataServices;
import com.utils.CSVFile;

import java.io.File;
import java.io.IOException;

/**
 * The Inventory class is a singleton that manages the stock of medications.
 * It reads from and writes to a CSV file to keep track of the inventory.
 * 
 * The CSV file has the following columns:
 * - Medication: The name of the medication.
 * - Quantity: The quantity of the medication in stock.
 * - Dosage: The dosage of the medication.
 * 
 */
@SuppressWarnings("unused")
public class InventoryManager {
    private static final String INVENTORY_FILE = "hms/src/main/java/com/data/inventory.csv";
    private static final String[] INVENTORY_HEADER = { "medication", "quantity", "dosage" };
    private static final int MEDICATION = 0;
    private static final int QUANTITY = 1;
    private static final int DOSAGE = 2;

    private CSVFile stock;

    public InventoryManager() {
        stock = FileManager.loadFile(INVENTORY_FILE, Arrays.asList(INVENTORY_HEADER));
    }

    /**
     * Retrieves the stock quantity of a specified medication item.
     *
     * @param item The medication item for which the stock quantity is to be
     *             retrieved.
     * @return The quantity of the specified medication item in stock.
     *         Returns 0 if the item is not found in the stock records.
     */
    public int getStock(Medication item) {
        return stock.getRecord(item.name) != null ? Integer.parseInt(stock.getRecord(item.name).get(QUANTITY)) : 0;
    }

    public void setStock(Medication item, int quantity) {
        stock.updateRecord(item.name, Arrays.asList(item.name, Integer.toString(quantity), item.dosage));
        stock.updateCSVFile();
    }

    public void add(Medication ID, Integer quantity) {
        stock.updateRecord(ID.name, Arrays.asList(ID.name, Integer.toString(this.getStock(ID) + quantity), ID.dosage));
        stock.updateCSVFile();
    }

    public void remove(Medication ID, Integer quantity) {
        stock.updateRecord(ID.name, Arrays.asList(ID.name, Integer.toString(this.getStock(ID) - quantity), ID.dosage));
    }

    public CSVFile view(Medication ID) {
        return stock;
    }

}
