package com.hms;
import java.util.List;

public class AppointmentOutcomeRecord {
    private Appointment appointment;
    private List<Medication> prescribedMedications;
    private String consultationNotes;

    public AppointmentOutcomeRecord(Appointment appointment, List<Medication> prescribedMedications, String consultationNotes) {
        this.appointment = appointment;
        //declaration of list is awkward
        this.prescribedMedications = prescribedMedications;
        this.consultationNotes = consultationNotes;
    }

    public void updateMedication(Medication medication) {
        //is there no deletion of medication
        prescribedMedications.add(medication);
    }
    //wtf is going on here
    public List<Medication> getMedication() {
        return prescribedMedications;
    }
    //UML says to return the string
    public void updateNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }
    //check
    public void createAppointmentOutcomeRecord() {

    }

}
