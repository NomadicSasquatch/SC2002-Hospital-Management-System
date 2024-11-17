package com.hms.Manager;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.hms.Doctor;
import com.hms.Patient;
import com.utils.CSVFile;

public class AppointmentManager {
    private static final String PATIENT_ROOT = "hms/src/main/java/com/data/PATIENT";
    private static final String[] APPOINTMENT_HEADER = { "scheduleid", "doctorid", "patientid", "date", "status" };
    private CSVFile appointmentCSV;

    public AppointmentManager(Patient patient) {
        this.appointmentCSV = FileManager.loadFile(PATIENT_ROOT + patient.getUserid() + "/appointment.csv", Arrays.asList(APPOINTMENT_HEADER));
    }

    public void createAppointment(String appointmentId, Doctor doctor, String patient, LocalDate date, String status) {
        List<String> appointmentData = Arrays.asList(appointmentId, doctor.getUserid(), patient, date.toString(), status);
        appointmentCSV.add(appointmentData);
        appointmentCSV.updateCSVFile();

        DoctorScheduleManager doctorScheduleManager = new DoctorScheduleManager(doctor);
        doctorScheduleManager.remove(Arrays.asList(appointmentId, doctor.getUserid(), date.toString(), status));
    }

    public void removeAppointment(String appointmentId) {
        appointmentCSV.remove(appointmentId);
        appointmentCSV.updateCSVFile();
    }

    public CSVFile viewAppointments() {
        return appointmentCSV;
    }
}