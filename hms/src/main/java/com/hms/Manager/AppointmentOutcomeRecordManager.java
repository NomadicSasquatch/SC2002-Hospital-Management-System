package com.hms.Manager;

import java.util.Arrays;

import com.hms.AppointmentOutcomeRecord;
import com.hms.Users;
import com.utils.CSVFile;

public class AppointmentOutcomeRecordManager extends DataManager {
    //changed scheduleid to appointmentid
    private static final String[] APPOINTMENT_OUTCOME_RECORD_HEADER = { "appointmentId", "date", "serviceType", "prescriptions", "consultationNotes" };
    private CSVFile appointmentOutcomeRecordCSV;
    //from patient to users since more than just users can access appointmentoutcomerecords aka doctor, patients etc
    public AppointmentOutcomeRecordManager(Users user) {
        //check usage of tostring
        this.appointmentOutcomeRecordCSV = FileManager.loadFile(DATA_ROOT + user.getRole().toString() + user.getUserid() + "/appointmentoutcomerecord.csv", Arrays.asList(APPOINTMENT_OUTCOME_RECORD_HEADER));
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
