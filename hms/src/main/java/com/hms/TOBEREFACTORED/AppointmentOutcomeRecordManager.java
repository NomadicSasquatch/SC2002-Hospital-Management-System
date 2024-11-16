package com.hms.TOBEREFACTORED;

import java.util.List;
import java.util.Map;

import com.enumclass.Services;
import com.hms.Medication;
import com.hms.Users;
import com.hms.impl.IDataServices;

import java.util.HashMap;

public class AppointmentOutcomeRecordManager {
    private IDataServices<AppointmentOutcomeRecord> AORServices;

    public AppointmentOutcomeRecordManager(IDataServices<AppointmentOutcomeRecord> AORServices) {
        this.AORServices = AORServices;
    }

    public void appointmentToRecord(Appointment appointment, List<Medication> prescribedMedications, String consultationNotes, Services typeOfService) {
        AppointmentOutcomeRecord appointmentRecord = new AppointmentOutcomeRecord(appointment, prescribedMedications, consultationNotes, typeOfService);
        AORServices.add(appointmentRecord);
    }

    public List<AppointmentOutcomeRecord> viewPatientRecord(Users user) {
        return AORServices.view(user);
    }

}
