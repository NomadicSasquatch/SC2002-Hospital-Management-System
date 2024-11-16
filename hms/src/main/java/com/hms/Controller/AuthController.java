package com.hms.Controller;

import com.hms.Users;
import com.hms.Manager.UserManager;
import com.utils.CSVFile;

public class AuthController {
    private CSVFile csv;
    public AuthController(UserManager userManager) {
        this.csv = userManager.view();
    }
    public boolean authenticate(String userID, String password) {
        try {
            return (csv.getRecord(userID).get(3).equals(Users.hashPassword(password)));
        } catch (Exception e) {
            return false;
        }
    }
}
