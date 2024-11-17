package com.hms;

import java.time.LocalDate;

import com.enumclass.UserRole;
import com.hms.Users;

public class Pharmacist extends Users {

    public Pharmacist(String userid, String email, String name, String password, LocalDate dob, boolean gender) {
        super(userid, email, name, password, dob, UserRole.PHARMACIST, gender);
    }

}
