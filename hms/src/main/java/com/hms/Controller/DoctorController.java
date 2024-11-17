package com.hms.Controller;

import com.hms.Appointment;
import com.hms.AppointmentOutcomeRecord;
import com.hms.Manager.AppointmentManager;
import com.hms.Manager.AppointmentOutcomeRecordManager;

public class DoctorController {
    void addAppointment(AppointmentManager manager, Appointment appointment) {
        manager.createAppointment(appointment);
    }
    void removeAppointment(AppointmentManager manager, Appointment appointment) {
        manager.removeAppointment(appointment);
    }
    void createAppointmentOutcomeRecord(AppointmentManager appointmentManager, AppointmentOutcomeRecordManager recordManager, Appointment appointment) {
        //properly convert an appointment to the outcomerecord
        recordManager.createAppointmentOutcomeRecord(new AppointmentOutcomeRecord(null, null, null, null, null));
    }
}
