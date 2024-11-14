package com.hms;

import java.time.LocalDate;

/**
 * Represents a Doctor in the hospital management system.
 * Extends the People class.
 * 
 * @param name   the name of the doctor
 * @param userID the unique user ID of the doctor
 * @param gender the gender of the doctor
 * @param age    the age of the doctor
 * @param role   the role of the doctor
 */
public class Doctor extends User {

    private String role;

    /**
     * Constructs a new Doctor with the specified details.
     * 
     * @param name   the name of the doctor
     * @param userID the unique user ID of the doctor
     * @param gender the gender of the doctor
     * @param age    the age of the doctor
     * @param role   the role of the doctor
     */
    public Doctor(String name, String userID, boolean gender, int age, String role) {
        super(name, userID, gender, age);
        this.role = role;
    }

    /**
     * Displays the doctor's dashboard.
     * TODO: Implement viewDashboard method.
     */
    public void viewDashboard() {
        System.out.println("TODO: Implement viewDashboard");
    }

    /**
     * Displays the doctor's appointments.
     * TODO: Implement viewAppointments method.
     * This should likely be abstracted
     */
    public void viewAppointments() {
        System.out.println("TODO: Implement viewAppointments");
    }

    /**
     * Books an appointment for a patient on a specified date.
     * 
     * @param date    the date of the appointment
     * @param patient the patient for whom the appointment is to be booked
     * @return true if the appointment is successfully booked, false otherwise
     * TODO: Implement bookAppointment method.
     */
    public boolean bookAppointment(LocalDate date, Patient patient) {
        System.out.println("TODO: Implement bookAppointment");
        return false; // returns true if the appointment is successfully booked
    }
}
