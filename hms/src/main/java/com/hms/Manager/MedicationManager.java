package com.hms.Manager;

import java.util.Arrays;

import com.hms.Medication;
import com.utils.CSVFile;

public class MedicationManager extends DataManager {
    private static final String[] MEDICATION_HEADER = { "medicationID", "name", "stockLevel", "lowStockAlertLevel", "status"};
    private CSVFile medicationCSV;

    public MedicationManager() {
        String filePath = DATA_ROOT + "medications.csv";
        this.medicationCSV = FileManager.loadFile(filePath, Arrays.asList(MEDICATION_HEADER));
    }

    public void addMedication(Medication medication) {
        add(medicationCSV, medication);
    }

    public void removeMedication(Medication medication) {
        remove(medicationCSV, medication);
    }

    @Override
    public CSVFile view() {
        return medicationCSV;
    }
}
