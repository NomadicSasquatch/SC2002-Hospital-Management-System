package com.hms.Controller;

import java.util.Arrays;

import com.enumclass.UserRole;
import com.hms.Users;
import com.hms.Manager.FileManager;
import com.hms.Manager.UserManager;
import com.utils.CSVFile;

public class AdminController extends UserManager {

    private static final String ADMIN_ROOT = "hms/src/main/java/com/data/ADMIN";

    private CSVFile adminCSV;

    public AdminController() {
        this.adminCSV = FileManager.loadFile(ADMIN_ROOT + "/admin.csv", Arrays.asList(USER_HEADER));
    }

    @Override
    public void add(Users data) {
        addUser(adminCSV, data);
    }

    @Override
    public void remove(Users data) {
        removeUser(adminCSV, data);
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
