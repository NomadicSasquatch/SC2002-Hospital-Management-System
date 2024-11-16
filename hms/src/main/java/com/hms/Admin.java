package com.hms;

import java.time.LocalDate;

import com.enumclass.UserRole;
import com.hms.TOBEREFACTORED.AdminMenu;

public class Admin extends Users {

    // Constructor with full parameters
    public Admin(String userid, String email, String name, String hashedpassword, LocalDate dob, UserRole role, boolean gender) {
        super(userid, email, name, hashedpassword, dob, role, gender);
        this.menu = new AdminMenu();  
    }


  
}
