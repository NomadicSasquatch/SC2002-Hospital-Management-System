package utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Utility class for writing CSV files.
 */
public class CSVWriter {

    
    
    /**
     * Writes the provided data to a CSV file at the specified file path.
     *
     * @param filePath the path of the file to write the CSV data to
     * @param data a list of string arrays, where each array represents a row of data to be written to the CSV file
     */
    public static void writeCSV(String filePath, List<String[]> data) {
        String delimiter = ",";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write header if needed
            // bw.write("appointmentId,patientId,doctorId,date,time,status,outcomeRecordId");
            // bw.newLine();

            for (String[] record : data) {
                String line = String.join(delimiter, record);
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            // Handle exception, possibly log it
            e.printStackTrace();
        }
    }
}
