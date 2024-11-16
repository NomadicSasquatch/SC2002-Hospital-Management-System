package com.hms.Manager;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.utils.CSVFile;

public class FileManager{
    public static CSVFile loadFile(String filename,List<String> headers) {
        File file = new File(filename);
        CSVFile csvFile = null;
        if (!file.exists()) {
            try {
                file.createNewFile();
                csvFile = new CSVFile(filename);
                // The first row of the CSV file is the header, with "Item" being the ID
                csvFile.add(headers);
                csvFile.updateCSVFile();
                return csvFile;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return (new CSVFile(filename));
    };

    public static boolean addFolders(String path) {
        File file = new File(path);
        if (!file.isDirectory() || !file.exists()) {
            try {
                return file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean removeFolders(File path) {
        File[] allContents = path.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                removeFolders(file);
            }
        }
        return path.delete();
    }
}
