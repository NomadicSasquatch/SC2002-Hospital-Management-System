package com.hms;

import java.time.LocalDate;

import com.enumclass.UserRole;
import com.hms.Users;

public class Administrator extends Users {

    public Administrator(String userid, String email, String name, String password, LocalDate dob, boolean gender) {
        super(userid, email, name, password, dob, UserRole.ADMIN, gender);
    }

}
