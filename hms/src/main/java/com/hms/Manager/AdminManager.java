package com.hms.Manager;


import java.io.File;
import java.util.List;

import com.hms.Users;
import com.utils.CSVFile;

public class AdminManager extends UserManager {
    
    private static final String ADMIN_ROOT = "hms/src/main/java/com/data/admin";

    private CSVFile adminCSV;

    public AdminManager() {
        this.adminCSV = FileManager.loadFile(ADMIN_ROOT + "/admin.csv", List.of("userid", "email", "name", "hashedpassword", "dob", "role", "gender"));
    }

    @Override
    public void add(Users data) {
        FileManager.addFolders(ADMIN_ROOT + "/" + data.getUserid());
        adminCSV.add(List.of(data.getUserid(), data.getEmail(), data.getName(), data.gethashedPassword(), data.getDob().toString(), data.getRole().toString(), String.valueOf(data.getGender())));
    }

    @Override
    public void remove(Users data) {
        FileManager.removeFolders(new File(ADMIN_ROOT + "/" + data.getUserid()));
        adminCSV.remove(data.getUserid());
    }

    @Override
    public CSVFile view() {
        return adminCSV;
    }
}
