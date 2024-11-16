package com.hms.TOBEREFACTORED;

import java.util.List;
import java.util.Map;

import com.hms.Users;
import com.hms.impl.IDataServices;

import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentManager {

    private IDataServices<Appointment> patientManager;
    private IDataServices<Appointment> doctorManager;

    public AppointmentManager(IDataServices<Appointment> patientManager, IDataServices<Appointment> doctorManager) {
        this.patientManager = patientManager;
        this.doctorManager = doctorManager;
    }

    public void addAppointment(Appointment appointment) {
        patientManager.add(appointment);
        doctorManager.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        patientManager.remove(appointment);
        doctorManager.remove(appointment);
    }

    public List<Appointment> viewPatientAppointment(Users user) {
        return patientManager.view(user);
    }

    public List<Appointment> viewDoctorAppointment(Users user) {
        return doctorManager.view(user);
    }

    public void rescheduleAppointment(Users user) {
        rescheduleAppointment(user);
    }

    public IDataServices<Appointment> getPatientManager() {
        return this.patientManager;
    }

    public void setPatientManager(PatientAppointmentServices patientManager) {
        this.patientManager = patientManager;
    }

    public IDataServices<Appointment> getDoctorManager() {
        return this.doctorManager;
    }

    public void setDoctorManager(DoctorAppointmentServices doctorManager) {
        this.doctorManager = doctorManager;
    }

}
