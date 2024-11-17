package com.hms.Manager;

import java.util.Arrays;

import com.hms.Users;
import com.utils.CSVFile;

public class UserManager extends DataManager {
    private static final String[] USER_HEADER = { "userID", "email", "name", "password", "dob", "role", "gender" };
    private CSVFile userCSV;

    public UserManager() {
        String filePath = DATA_ROOT + "login_info.csv";
        this.userCSV = FileManager.loadFile(filePath, Arrays.asList(USER_HEADER));
    }

    public void addUser(Users user) {
        add(userCSV, user);
        
    }

    public void removeUser(Users user) {
        remove(userCSV, user);
    }

    @Override
    public CSVFile view() {
        return userCSV;
    }
}
