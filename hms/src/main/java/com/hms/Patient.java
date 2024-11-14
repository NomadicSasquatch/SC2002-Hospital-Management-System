package com.hms;

import java.time.LocalDate;

public class Patient extends User {

    public Patient(String name, String userID, boolean isActive, int age) {
        super(name, userID, isActive, age);
    }

    /**
     * Displays the patient's dashboard.
     * TODO: Implement viewDashboard method.
     */
    public void viewDashboard() {
        System.out.println("TODO: Implement viewDashboard");
    }

    /**
     * Displays the patient's appointments.
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
