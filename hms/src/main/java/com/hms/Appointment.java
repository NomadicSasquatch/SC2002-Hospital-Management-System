package com.hms;
import java.time.LocalDate;
import java.util.List;

import com.enumclass.AppointmentStatus;
import com.hms.Doctor;
import com.hms.Patient;
import com.hms.impl.IDataRepository;

public class Appointment implements IDataRepository {
    private Doctor doctor;
    private Patient patient;
    private String appointmentID;
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

    public String getAppointmentID() {
        return appointmentID;
    }
    @Override
    public String getDataID() {
        return getAppointmentID();
    }
    @Override
    public List<String> getAttributes() {
        //check formatting of appointmentdate to string and status to string
        return List.of(getAppointmentID(), doctor.getUserid(), patient.getUserid(), appointmentDate.toString(), status.toString());
    }
    @Override
    public String getDataName() {
        return "appointment";
    }
}
