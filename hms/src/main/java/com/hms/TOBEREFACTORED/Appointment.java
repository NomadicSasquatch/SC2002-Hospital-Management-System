package com.hms.TOBEREFACTORED;
import java.time.LocalDate;

import com.enumclass.AppointmentStatus;
import com.hms.Doctor;
import com.hms.Patient;

public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private int appointmentID;
    private LocalDate appointmentDate; //check if tallies with UML intention
    private AppointmentStatus status = AppointmentStatus.PENDING;

    public Appointment(Doctor doctor, Patient patient, LocalDate appointmentDate) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        //doublecheck
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public int getAppointmentID() {
        return appointmentID;
    }
}
