package com.hms.Controller;

import java.time.LocalDate;
import java.util.List;

import com.enumclass.UserRole;
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

    public boolean register(String userid, String email, String name, String password, LocalDate dob,
            UserRole role, boolean gender) {
        try {
            csv.add(List.of(userid, email, name, Users.hashPassword(password), dob.toString(), role.toString(),
                    String.valueOf(gender)));
            csv.updateCSVFile();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean changePassword(String userID, String newPassword) {
        try {
            if (csv.hasRecord(userID)) {
                csv.updateRecord(userID,
                        List.of(userID, csv.getRecord(userID).get(1), csv.getRecord(userID).get(2),
                                Users.hashPassword(newPassword), csv.getRecord(userID).get(4),
                                csv.getRecord(userID).get(5), csv.getRecord(userID).get(6)));
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
