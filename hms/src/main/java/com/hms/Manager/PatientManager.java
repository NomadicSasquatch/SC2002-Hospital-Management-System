package com.hms.Manager;

import java.util.Arrays;

import com.hms.Users;
import com.utils.CSVFile;
import com.enumclass.UserRole;

public class PatientManager extends UserManager {

    private static final String PATIENT_ROOT = "hms/src/main/java/com/data/PATIENT";

    private CSVFile patientCSV;

    public PatientManager() {
        this.patientCSV = FileManager.loadFile(PATIENT_ROOT + "/patient.csv", Arrays.asList(USER_HEADER));
    }

    @Override
    public void add(Users data) {
        addUser(patientCSV, data, PATIENT_ROOT);
    }

    @Override
    public void remove(Users data) {
        removeUser(patientCSV, data, PATIENT_ROOT);
    }

    @Override
    public CSVFile view() {
        return patientCSV;
    }

    @Override
    public UserRole getRole() {
        return UserRole.PATIENT;
    }
}
