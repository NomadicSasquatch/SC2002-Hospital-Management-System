package com.hms.Manager;

import java.util.Arrays;
import java.util.List;

import com.hms.MedicalRecord;
import com.hms.Users;
import com.utils.CSVFile;

import java.io.File;

public class MedRecManager extends DataManager {
    private static final String[] MEDREC_HEADER = { "recordid", "patientId", "patientName", "dob", "gender", "phoneNhmber", "email", "bloodType" };
    private CSVFile record;

    public MedRecManager(Users user) {
        this.record = FileManager.loadFile(DATA_ROOT + user.getRole().toString() + user.getUserid() + "/medrec.csv", Arrays.asList(MEDREC_HEADER));
    }
    //check logic if it works
    public boolean updateEntry(MedicalRecord medicalRecord) {
        return record.updateRecord(medicalRecord.getDataID(), medicalRecord.getAttributes());
    }

    public void createMedicalRecord(MedicalRecord medicalRecord) {
        add(record, medicalRecord);
    }

    public void removeMedicalRecord(MedicalRecord medicalRecord) {
        remove(record, medicalRecord);
    }

    @Override
    public CSVFile view() {
        return record;
    }

}
