package com.hms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class PatientAppointmentServices implements IDataServices<Appointment> {
    private Map<Patient, List<Appointment>> patientAppointments;

    public PatientAppointmentServices() {
        patientAppointments = new HashMap<>();
    }
    @Override
    public void add(Appointment appointment) {
        //add restrictions on availability
        Patient patient = appointment.getPatient();

        patientAppointments.computeIfAbsent(patient, k -> new ArrayList<>()).add(appointment);
    }

    @Override
    public void remove(Appointment appointment) {
        Patient patient = appointment.getPatient();
        List<Appointment> appointments = patientAppointments.get(patient);

        if(appointments != null) {
            //safeguard against appointment that doesnt exist
            appointments.remove(appointment);
        }
    }
    @Override
    public List<Appointment> view(User user) {
        return patientAppointments.get(user);
    }

    public void rescheduleAppointment(Map<Patient, List<Appointment>> patientAppointments, Appointment oldAppointment, Appointment newAppointment) {
        remove(oldAppointment);
        add(newAppointment);
    }
}
