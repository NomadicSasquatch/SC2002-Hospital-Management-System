package com.hms.Manager;

import java.util.Arrays;

import com.hms.AppointmentOutcomeRecord;
import com.hms.Patient;
import com.utils.CSVFile;

public class AppointmentOutcomeRecordManager extends DataManager {
    private static final String[] APPOINTMENT_OUTCOME_RECORD_HEADER = { "appointmentId", "date", "serviceType", "prescriptions", "consultationNotes" };
    private CSVFile appointmentOutcomeRecordCSV;
    //there should only be patients that have the record
    public AppointmentOutcomeRecordManager(Patient patient) {
        //check usage of tostring
        this.appointmentOutcomeRecordCSV = FileManager.loadFile(DATA_ROOT + patient.getRole().toString() + patient.getUserid() + "/appointmentoutcomerecord.csv", Arrays.asList(APPOINTMENT_OUTCOME_RECORD_HEADER));
    }

    public void createAppointmentOutcomeRecord(AppointmentOutcomeRecord record) {
        add(appointmentOutcomeRecordCSV, record);
    }

    public void removeAppointmentOutcomeRecord(AppointmentOutcomeRecord record) {
        remove(appointmentOutcomeRecordCSV, record);
    }
    @Override
    public CSVFile view() {
        return appointmentOutcomeRecordCSV;
    }
}
