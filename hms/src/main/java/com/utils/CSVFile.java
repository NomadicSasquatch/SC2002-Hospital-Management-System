package com.utils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/**
 * The CSVFile class provides functionality to read, store, and manipulate records from a CSV file.
 * Each record is represented as a list of strings.
 * 
 * <p>It supports reading from a CSV file, retrieving records, adding new records, and removing existing records.</p>
 * 
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * CSVFile csvFile = new CSVFile("path/to/csvfile.csv");
 * List<List<String>> records = csvFile.getRecords();
 * csvFile.addRecord(Arrays.asList("field1", "field2", "field3"));
 * csvFile.removeRecord(0);
 * }
 * </pre>
 * 
 * <p>Note: This class uses the CSVReader from the opencsv library to read CSV files.</p>
 * 
 * @see com.opencsv.CSVReader
 */
public class CSVFile {

    List<List<String>> records = new ArrayList<>();

    /**
     * Constructs a CSVFile object and reads the contents of the specified CSV file.
     * Each row of the CSV file is stored as a list of strings
     *
     * @param filename relative directory of the CSV file to be read
     */
    public CSVFile(String filename) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filename));) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (Exception ex) {
            //TODO: handle exception
        }
    }

    /**
     * Retrieves the list of records from the CSV file.
     *
     * @return a list of lists, where each inner list represents a record with its fields as strings.
     */
    public List<List<String>> getRecords() {
        return records;
    }

    /**
     * Adds a new record to the CSV file.
     *
     * @param record a list of strings representing the fields of the new record
     */
    public void add(List<String> record) {
        records.add(record);
    }

    /**
     * Removes a record from the CSV file at the specified index.
     *
     * @param index the index of the record to be removed
     */
    public void remove(int index) {
        records.remove(index);
    }
}
