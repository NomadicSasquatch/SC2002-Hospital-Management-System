package com.hms.Manager;

import java.util.Arrays;
import java.util.List;

import com.hms.Users;
import com.utils.CSVFile;

import java.io.File;

@SuppressWarnings("unused")
public class MedRecManager {
    private static final String PATIENT_ROOT = "hms/src/main/java/com/data/PATIENT";
    private static final String[] MEDREC_HEADER = { "recordid", "patient", "diagnosis", "bloodtype", "treatments", "prescriptions" };
    private static final int RECORD_ID = 0;
    private static final int PATIENT = 1;
    private static final int DIAGNOSIS = 2;
    private static final int BLOOD_TYPE = 3;
    private static final int TREATMENT = 4;
    private static final int PRESCRIPTION = 5;

    private CSVFile stock;
    private final String MEDREC_FILE;

    public MedRecManager(Users user) {
        MEDREC_FILE = PATIENT_ROOT + "/" + user.getUserid() + "/medrec.csv";
        stock = FileManager.loadFile(MEDREC_FILE, Arrays.asList(MEDREC_HEADER));
    }

    public List<String> getEntry(String item) {
        return stock.getRecord(item);
    }

    public boolean updateEntry(String recordid, String patient, String diagnosis, String bloodtype, String treatments,
            String prescriptions) {
        return stock.updateRecord(recordid,
                Arrays.asList(recordid, patient, diagnosis, bloodtype, treatments, prescriptions));
    }

    public void add(String recordid, String patient, String diagnosis, String bloodtype, String treatments,
            String prescriptions) {
        stock.add(Arrays.asList(recordid, patient, diagnosis, bloodtype, treatments, prescriptions));
    }

    public void remove(String recordid) {
        stock.remove(recordid);
    }

    public CSVFile view() {
        return stock;
    }

}
