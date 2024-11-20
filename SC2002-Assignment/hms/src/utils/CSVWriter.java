package utils;

import java.io.*;
import java.util.List;

/**
 * Utility class for writing CSV files.
 */
public class CSVWriter {

    
    /** 
     * @param filePath
     * @param data
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
