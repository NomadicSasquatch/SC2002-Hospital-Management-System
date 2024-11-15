package com.hms;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentOutcomeRecordServices implements IDataServices<AppointmentOutcomeRecord> {
    private Map<Patient, List<AppointmentOutcomeRecord>> appointmentOutcomeRecords;

    public AppointmentOutcomeRecordServices() {
        appointmentOutcomeRecords = new HashMap<>();
    }
    @Override
    public void add(AppointmentOutcomeRecord appointmentRecord) {
        Patient patient = appointmentRecord.getAppointment().getPatient();
        appointmentOutcomeRecords.computeIfAbsent(patient, k -> new ArrayList<>()).add(appointmentRecord);
    }
    @Override
    public void remove(AppointmentOutcomeRecord appointmentRecord) {
        //should records ever be removed
    }
    @Override
    public List<AppointmentOutcomeRecord> view(Users user) {
        return appointmentOutcomeRecords.get(user);
    }
}
