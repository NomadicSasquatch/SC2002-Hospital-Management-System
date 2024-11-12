package com.hms;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class AppointmentManager {
    private Map<Doctor, List<Appointment>> doctorAppointments;
    private Map<Patient, List<Appointment>> patientAppointments;
    
    public AppointmentManager() {

    }
    //should this be appointment input
    public void bookAppointment(Patient patient, Doctor doctor, LocalTime appointmentDate) {
        
    }
    //should this not be patient and doctor inputs 
    public void cancelAppointment(Appointment appointment) {

    }

    public void rescheduleAppointment(Appointment appointment) {

    }

    public void viewSchedule(Doctor doctor) {

    }

    public List<Appointment> viewDoctorAppointments(Doctor doctor) {

    }

    public List<Appointment> viewPatientAppointments(Patient patient) {

    }

    public void updateAppointmentStatus(Appointment appointment, AppointmentStatus status) {
        
    }
}
