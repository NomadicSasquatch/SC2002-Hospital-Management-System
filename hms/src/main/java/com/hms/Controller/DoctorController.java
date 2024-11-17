package com.hms.Controller;

import java.util.List;

import com.enumclass.Services;
import com.hms.Appointment;
import com.hms.AppointmentOutcomeRecord;
import com.hms.Medication;
import com.hms.Manager.AppointmentManager;
import com.hms.Manager.AppointmentOutcomeRecordManager;
import com.utils.CSVFile;

public class DoctorController {
    void addAppointment(AppointmentManager manager, Appointment appointment) {
        manager.createAppointment(appointment);
    }
    //implement removing from patient appointments logic here
    void removeAppointment(AppointmentManager manager, Appointment appointment) {
        manager.removeAppointment(appointment);
    }
    // probably a better way and place to implement this logic
    // improve implementation of getting the List<Medication>
    void createAppointmentOutcomeRecord(AppointmentManager appointmentManager, AppointmentOutcomeRecordManager recordManager, Appointment appointment, String recordId, Services serviceType, List<Medication> prescriptions, String consultationNotes) {
        AppointmentOutcomeRecord record = new AppointmentOutcomeRecord(recordId, appointment.getAppointmentDate(), serviceType, prescriptions, consultationNotes);
        recordManager.createAppointmentOutcomeRecord(record);
        appointmentManager.removeAppointment(appointment);
    }
    
    CSVFile viewAppointments(AppointmentManager manager) {
        return manager.view();
    }

    // TODO: check personal schedule and setting availability logic
}
