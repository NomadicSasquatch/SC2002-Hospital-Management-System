package com.hms;
import java.util.List;

import com.enumclass.Services;

public class AppointmentOutcomeRecord {
    private Appointment appointment;
    private List<Medication> prescribedMedications;
    private String consultationNotes;
    private Services typeOfService;

    public AppointmentOutcomeRecord(Appointment appointment) {
        this.appointment = appointment;
    }

    public AppointmentOutcomeRecord(Appointment appointment, List<Medication> prescribedMedications, String consultationNotes, Services typeOfService) {
        this.appointment = appointment;
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
        this.typeOfService = typeOfService;
    }
    public Appointment getAppointment() {
        return this.appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public List<Medication> getPrescribedMedications() {
        return this.prescribedMedications;
    }

    public void setPrescribedMedications(List<Medication> prescribedMedications) {
        this.prescribedMedications = prescribedMedications;
    }

    public String getConsultationNotes() {
        return this.consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }

    public Services getTypeOfService() {
        return this.typeOfService;
    }

    public void setTypeOfService(Services typeOfService) {
        this.typeOfService = typeOfService;
    }

}
