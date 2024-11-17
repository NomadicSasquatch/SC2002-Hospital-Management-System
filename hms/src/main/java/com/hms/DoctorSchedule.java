package com.hms;

import java.util.List;

import com.hms.impl.IDataRepository;

public class DoctorSchedule implements IDataRepository{
    private static final String DOCTOR_ROOT = "hms/src/main/java/com/data/DOCTOR";

    Doctor doctor;
    public DoctorSchedule(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String getDataID() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataID'");
    }

    @Override
    public List<String> getAttributes() {
        return null;
    }

    @Override
    public String getDataName() {
        return DOCTOR_ROOT + "/" + doctor.getUserid() + "/schedule.csv";
    }

    //TODO: im not sure how to implement this lol, since getAttributes() will need to access the csv file.

}
