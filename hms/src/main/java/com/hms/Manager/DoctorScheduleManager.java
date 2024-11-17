package com.hms.Manager;

import java.util.Arrays;
import java.util.List;

import com.hms.Users;
import com.hms.impl.IDataServices;
import com.utils.CSVFile;

// Doctors should be able to update this to show their available timeslots, 
// and patients should be able to view this to book appointments.
public class DoctorScheduleManager implements IDataServices<List<String>, CSVFile> {
    private static final String DOCTOR_ROOT = "hms/src/main/java/com/data/DOCTOR";
    private static final String[] DOCTOR_SCHEDULE_HEADER = { "scheduleid", "doctorid", "date", "appointmentstatus" };

    private CSVFile doctorCSV;
    String doctorid;

    public DoctorScheduleManager(Users user) {
        doctorid = user.getUserid();
        this.doctorCSV = FileManager.loadFile(DOCTOR_ROOT + "/" + doctorid + "/schedule.csv",
                Arrays.asList(DOCTOR_SCHEDULE_HEADER));
    }

    @Override
    public void add(List<String> data) {
        doctorCSV.add(data);
        doctorCSV.updateCSVFile();
    }

    @Override
    public void remove(List<String> data) {
        if (doctorid.equals(data.get(1))) {
            doctorCSV.remove(data.get(0));
            doctorCSV.updateCSVFile();
        }
    }

    @Override
    public CSVFile view() {
        return doctorCSV;
    }
}
