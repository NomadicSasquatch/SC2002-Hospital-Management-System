package com.utils;

import java.util.Arrays;

import com.hms.Medication;

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
public class Inventory {
    private static final String INVENTORY_FILE = "hms/src/main/java/com/data/inventory.csv";
    private static final int MEDICATION = 0;
    private static final int QUANTITY = 1;
    private static final int DOSAGE = 2;

    private CSVFile stock;

    private static Inventory instance;

    private Inventory() {
        File file = new File(INVENTORY_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
                stock = new CSVFile(INVENTORY_FILE);
                // The first row of the CSV file is the header, with "Item" being the ID
                stock.add(Arrays.asList("Medication", "Quantity", "Dosage"));
                stock.updateCSVFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        stock = new CSVFile(INVENTORY_FILE);
    }

    public static Inventory getInstance() {
        if (instance == null) {
            instance = new Inventory();
        }
        return instance;
    }

    /**
     * Retrieves the stock quantity of a specified medication item.
     *
     * @param item The medication item for which the stock quantity is to be retrieved.
     * @return The quantity of the specified medication item in stock. 
     *         Returns 0 if the item is not found in the stock records.
     */
    public int getStock(Medication item) {
        return stock.getRecord(item.name) != null ? Integer.parseInt(stock.getRecord(item.name).get(QUANTITY)) : 0;
    }

    /**
     * Updates the stock quantity of a given medication item.
     *
     * @param item     The medication item to update.
     * @param quantity The new quantity to set for the medication item.
     * @return         True if the stock record was successfully updated, false otherwise.
     */
    public boolean setStock(Medication item, int quantity) {
        return stock.updateRecord(item.name, Arrays.asList(item.name, Integer.toString(quantity), item.dosage));
    }

    /**
     * Adds the specified quantity of the given medication item to the stock.
     *
     * @param item The medication item to be added to the stock.
     * @param quantity The quantity of the medication item to be added.
     * @return true if the stock was successfully updated, false otherwise.
     */
    public boolean addStock(Medication item, int quantity) {
        return stock.updateRecord(item.name, Arrays.asList(item.name, Integer.toString(this.getStock(item) + quantity), item.dosage));
    }

    /**
     * Removes a specified quantity of a given medication from the stock.
     *
     * @param item The medication item to be removed from the stock.
     * @param quantity The quantity of the medication to be removed.
     * @return true if the stock was successfully updated, false otherwise.
     */
    public boolean removeStock(Medication item, int quantity) {
        return stock.updateRecord(item.name, Arrays.asList(item.name, Integer.toString(this.getStock(item) - quantity), item.dosage));
    }
}
