package com.hms;

import java.util.List;

public class AppointmentOutcomeViewer {
    private List<AppointmentOutcomeRecord> appointmentOutcomeRecords; // Aggregation relationship

    public AppointmentOutcomeViewer(List<AppointmentOutcomeRecord> appointmentOutcomeRecords) {
        this.appointmentOutcomeRecords = appointmentOutcomeRecords;
    }

    public List<AppointmentOutcomeRecord> viewAppointmentsOutcome(Patient patient) {

    }

    public List<AppointmentOutcomeRecord> viewAppointmentsOutcome(Pharmacist pharmacist) {

    }
}
