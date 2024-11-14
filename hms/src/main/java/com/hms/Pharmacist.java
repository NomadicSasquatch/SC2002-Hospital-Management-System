package com.hms;


import java.time.LocalDate;

public class Pharmacist extends User {

    // Constructor with full parameters
    public Pharmacist(String hospitalID, String name, UserRole role, String email, boolean gender, LocalDate dob, String password) {
        super(hospitalID, name, role, email, gender, dob, password);
        this.menu = new PharmacistMenu(); 
    }


  
}
