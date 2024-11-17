package com.hms.Manager;

import java.util.Arrays;

import com.enumclass.UserRole;
import com.hms.Users;
import com.utils.CSVFile;

public class DoctorManager extends UserManager {

    private static final String DOCTOR_ROOT = "hms/src/main/java/com/data/DOCTOR";

    private CSVFile doctorCSV;

    public DoctorManager() {
        this.doctorCSV = FileManager.loadFile(DOCTOR_ROOT + "/doctor.csv", Arrays.asList(USER_HEADER));
    }

    @Override
    public void add(Users data) {
        addUser(doctorCSV, data, DOCTOR_ROOT);
    }

    @Override
    public void remove(Users data) {
        removeUser(doctorCSV, data, DOCTOR_ROOT);
    }

    @Override
    public CSVFile view() {
        return doctorCSV;
    }

    @Override
    public UserRole getRole() {
        return UserRole.DOCTOR;
    }
}
