package com.hms;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class DoctorAppointmentServices implements IDataServices<Appointment> {
    private Map<Doctor, List<Appointment>> doctorAppointments;

    public DoctorAppointmentServices() {
        doctorAppointments = new HashMap<>();
    }

    @Override
    public void add(Appointment appointment) {
        //add restrictions on availability (which is an attribute as of now)
        Doctor doctor = appointment.getDoctor();

        doctorAppointments.computeIfAbsent(doctor, k -> new ArrayList<>()).add(appointment);
    }
    @Override
    public void remove(Appointment appointment) {
        Doctor doctor = appointment.getDoctor();
        List<Appointment> appointments = doctorAppointments.get(doctor);

        if(appointments != null) {
            //safeguard against appointment that doesnt exist
            appointments.remove(appointment);
        }
    }
    @Override
    public List<Appointment> view(Users user) {
        return doctorAppointments.get(user);
    }
}
