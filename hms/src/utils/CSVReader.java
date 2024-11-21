package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading CSV files.
 */
public class CSVReader {

    /**
     * Reads a CSV file from the specified file path and returns the contents as
     * a list of string arrays. Each string array represents a row in the CSV
     * file, with each element in the array representing a cell.
     *
     * @param filePath the path to the CSV file to be read
     * @return a list of string arrays, where each array represents a row in the
     * CSV file
     */
    public static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        String line;
        String delimiter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip header if there is one
            // br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                records.add(values);
            }
        } catch (IOException e) {
            // Handle exception, possibly log it
            e.printStackTrace();
        }

        return records;
    }
}
