package com.utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

/**
 * The CSVFile class provides functionality to read, store, and manipulate
 * records from a CSV file.
 * Each record is represented as a list of strings.
 * 
 * <p>
 * It supports reading from a CSV file, retrieving records, adding new records,
 * and removing existing records.
 * </p>
 * 
 * <p>
 * Example usage:
 * </p>
 * 
 * <pre>
 * {@code
 * CSVFile csvFile = new CSVFile("path/to/csvfile.csv");
 * List<List<String>> records = csvFile.getRecords();
 * csvFile.addRecord(Arrays.asList("field1", "field2", "field3"));
 * csvFile.removeRecord(0);
 * }
 * </pre>
 * 
 * <p>
 * Note: This class uses the CSVReader from the opencsv library to read CSV
 * files.
 * </p>
 * 
 * @see com.opencsv.CSVReader
 */
public class CSVFile {

    private List<List<String>> records = new ArrayList<>();
    private HashMap<String, Integer> recordIndex = new HashMap<>();
    private String filename;
    private String[] headers;

    /**
     * Constructs a CSVFile object and reads the contents of the specified CSV file.
     * Each row of the CSV file is stored as a list of strings
     *
     * @param filename relative directory of the CSV file to be read
     */
    public CSVFile(String filename) {
        this.filename = filename;
        try (CSVReader csvReader = new CSVReader(new FileReader(filename));) {
            String[] values;
            headers = csvReader.readNext();
            while ((values = csvReader.readNext()) != null) {
                this.add(Arrays.asList(values));
            }
        } catch (Exception ex) {
            // TODO: handle exception
        }
    }

    /**
     * Updates the CSV file with the current records.
     * 
     * @return void
     */
    public void updateCSVFile() {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(filename))) {
            csvWriter.writeNext(headers);
            String[] values = new String[headers.length];
            for (int i = 0; i < recordIndex.size(); i++) {
                csvWriter.writeNext(records.get(i).toArray(values));
            }
        } catch (Exception ex) {
            // TODO: handle exception
        }
    }

    /**
     * Retrieves all records from the CSV file.
     *
     * @return a list of lists, where each inner list represents a record with its
     *         fields as strings.
     */
    public List<List<String>> getAllRecords() {
        return records;
    }

    /**
     * Retrieves a record from the CSV file at the specified index.
     *
     * @param index the index of the record to retrieve
     * @return a list of strings representing the record at the specified index
     */
    public List<String> getRecord(int index) {
        return records.get(index);
    }

    /**
     * Retrieves a record from the CSV file based on the provided ID.
     *
     * @param ID The unique identifier of the record to be retrieved.
     * @return A list of strings representing the record associated with the given
     *         ID.
     *         Returns null if the ID does not exist in the record index.
     */
    public List<String> getRecord(String ID) {
        return records.get(recordIndex.get(ID));
    }

    /**
     * Adds a new record to the CSV file. If a record with the same key already
     * exists,
     * it updates the existing record instead.
     *
     * @param record A list of strings representing the record to be added.
     *               The first element of the list is considered as the key.
     * @return {true} if the record was added successfully, {false} if the record
     *         was updated.
     */
    public boolean add(List<String> record) {
        if (recordIndex.containsKey(record.get(0))) {
            this.updateRecord(record.get(0), record);
            return false;
        } else {
            records.add(record);
            recordIndex.put(record.get(0), recordIndex.size());
            return true;
        }
    }

    /**
     * Updates the record at the specified index with the provided record.
     *
     * @param index  the index of the record to update
     * @param record the new record to set at the specified index
     */
    public void updateRecord(int index, List<String> record) {
        records.set(index, record);
    }

    /**
     * Updates an existing record with the given ID or adds a new record if the ID
     * does not exist.
     *
     * @param ID     the unique identifier of the record to be updated or added
     * @param record the list of strings representing the record data
     * @return {true} if the record was updated, {false} if a new record was added
     */
    public boolean updateRecord(String ID, List<String> record) {
        if (recordIndex.containsKey(record.get(0))) {
            records.set(recordIndex.get(ID), record);
            return true;
        } else {
            records.add(record);
            recordIndex.put(record.get(0), recordIndex.size());
            return false;
        }
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public boolean hasRecord(String ID) {
        return recordIndex.containsKey(ID);
    }

    /**
     * Removes a record with the specified ID from the records.
     *
     * @param ID the ID of the record to be removed
     * @return {true} if the record was successfully removed, {false} if the record with the specified ID does not exist
     */
    public boolean remove(String ID) {
        if (recordIndex.containsKey(ID)) {
            records.remove((recordIndex.remove(ID)).intValue());
            return true;
        } else {
            return false;
        }
    }
}
