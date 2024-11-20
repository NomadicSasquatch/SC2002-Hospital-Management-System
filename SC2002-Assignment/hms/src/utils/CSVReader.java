package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading CSV files.
 */
public class CSVReader {

    
    /** 
     * @param filePath
     * @return List<String[]>
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
