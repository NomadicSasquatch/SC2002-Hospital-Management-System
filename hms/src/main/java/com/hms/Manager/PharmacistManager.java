package com.hms.Manager;

import java.util.Arrays;

import com.enumclass.UserRole;
import com.hms.Users;
import com.utils.CSVFile;

public class PharmacistManager extends UserManager {

    private static final String PHARMACIST_ROOT = "hms/src/main/java/com/data/PHARMACIST";

    private CSVFile pharmacistCSV;

    public PharmacistManager() {
        this.pharmacistCSV = FileManager.loadFile(PHARMACIST_ROOT + "/pharmacist.csv", Arrays.asList(USER_HEADER));
    }

    @Override
    public void add(Users data) {
        addUser(pharmacistCSV, data, PHARMACIST_ROOT);
    }

    @Override
    public void remove(Users data) {
        removeUser(pharmacistCSV, data, PHARMACIST_ROOT);
    }

    @Override
    public CSVFile view() {
        return pharmacistCSV;
    }

    @Override
    public UserRole getRole() {
        return UserRole.PHARMACIST;
    }
}