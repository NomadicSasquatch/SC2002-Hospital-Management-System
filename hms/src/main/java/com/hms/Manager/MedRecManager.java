package com.hms.Manager;

import java.util.List;

import com.utils.CSVFile;

import java.io.File;

@SuppressWarnings("unused")
public class MedRecManager {
    private static final String MEDREC_FILE = "hms/src/main/java/com/data/medrec.csv";
    private static final int RECORD_ID = 0;
    private static final int PATIENT = 1;
    private static final int DIAGNOSIS = 2;
    private static final int BLOOD_TYPE = 3;
    private static final int TREATMENT = 4;
    private static final int PRESCRIPTION = 5;

    private CSVFile stock;

    public MedRecManager() {
        stock = FileManager.loadFile(MEDREC_FILE,
                List.of("recordid", "patient", "diagnosis", "bloodtype", "treatments", "prescriptions"));
    }

    public List<String> getEntry(String item) {
        return stock.getRecord(item);
    }

    public boolean updateEntry(String recordid, String patient, String diagnosis, String bloodtype, String treatments,
            String prescriptions) {
        return stock.updateRecord(recordid,
                List.of(recordid, patient, diagnosis, bloodtype, treatments, prescriptions));
    }

    public void add(String recordid, String patient, String diagnosis, String bloodtype, String treatments,
            String prescriptions) {
        stock.add(List.of(recordid, patient, diagnosis, bloodtype, treatments, prescriptions));
    }

    public void remove(String recordid) {
        stock.remove(recordid);
    }

    public CSVFile view() {
        return stock;
    }

}
