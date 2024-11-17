package com.hms.Manager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.hms.Appointment;
import com.hms.Doctor;
import com.hms.Patient;
import com.hms.Users;
import com.utils.CSVFile;

public class AppointmentManager extends DataManager {
    //changed scheduleid to appointmentid
    private static final String[] APPOINTMENT_HEADER = { "appointmentid", "doctorid", "patientid", "date", "status" };
    private CSVFile appointmentCSV;
    //from patient to users since more than just users can access appointments aka doctor, patients etc
    public AppointmentManager(Users user) {
        //check usage of tostring
        this.appointmentCSV = FileManager.loadFile(DATA_ROOT + user.getRole().toString() + user.getUserid() + "/appointment.csv", Arrays.asList(APPOINTMENT_HEADER));
    }

    public void createAppointment(Appointment appointment) {
        add(appointmentCSV, appointment);

        // DoctorScheduleManager doctorScheduleManager = new DoctorScheduleManager(appointment.getDoctor());
        // doctorScheduleManager.remove(Arrays.asList(appointmentId, doctor.getUserid(), date.toString(), status));
    }

    public void removeAppointment(Appointment appointment) {
        remove(appointmentCSV, appointment);
    }
    //originally viewAppointment;
    @Override
    public CSVFile view() {
        return appointmentCSV;
    }
}