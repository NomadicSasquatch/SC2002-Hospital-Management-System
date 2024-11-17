package com.hms.Manager;

import java.util.Arrays;

import com.enumclass.UserRole;
import com.hms.Users;
import com.utils.CSVFile;

public class AdminManager extends UserManager {

    private static final String ADMIN_ROOT = "hms/src/main/java/com/data/ADMIN";

    private CSVFile adminCSV;

    public AdminManager() {
        this.adminCSV = FileManager.loadFile(ADMIN_ROOT + "/admin.csv", Arrays.asList(USER_HEADER));
    }

    @Override
    public void add(Users data) {
        addUser(adminCSV, data, ADMIN_ROOT);
    }

    @Override
    public void remove(Users data) {
        removeUser(adminCSV, data, ADMIN_ROOT);
    }

    @Override
    public CSVFile view() {
        return adminCSV;
    }

    @Override
    public UserRole getRole() {
        return UserRole.ADMIN;
    }
}
