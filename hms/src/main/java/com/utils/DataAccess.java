package com.utils;

import java.util.List;

/**
 * The DataAccess interface provides methods for reading, writing, adding, and removing records in a CSV file.
 * Implementations of this interface should handle the specifics of file operations.
 * 
 * @implNote: Implementing classes should provide functionality for different file types as needed.
 */
public interface DataAccess {
    CSVFile readCSV();
    void writeCSV();
    void addRecord(List<String> record);
    void removeRecord(int index);
}
