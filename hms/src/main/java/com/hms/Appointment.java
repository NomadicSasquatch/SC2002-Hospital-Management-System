package com.hms;
import java.time.LocalTime;

public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private LocalTime appointmentDate; //check if tallies with UML intention
    private AppointmentStatus status;

    public Appointment(Doctor doctor, Patient patient, LocalTime appointmentDate, AppointmentStatus status) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentDate = appointmentDate;
        //doublecheck
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Patient getPatient() {
        return patient;
    }
}
