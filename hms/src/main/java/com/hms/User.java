package com.hms;


import java.time.LocalDate;

public abstract class User {
    String hospitalID;
    String name;
    UserRole role;
    String email;
    boolean gender;
    LocalDate dob;
    String password;
    Menu menu;  // Added the menu field

        // Constructor
        public User(String hospitalID, String name, UserRole role, String email, boolean gender, LocalDate dob, String password) {
            this.hospitalID = hospitalID;
            this.name = name;
            this.role = role;
            this.email = email;
            this.gender = gender;
            this.dob = dob;
            this.password = password;
        }
    
        // Abstract method to display the menu for each role
        public void displayMenu() {
            if (menu != null) {
                menu.displayMenu(); // Call the appropriate displayMenu() for the specific user role
            } else {
                System.out.println("Menu not available for this role.");
            }
        }
    }

