package com.hms;

import java.time.LocalDate;
import java.util.List;

import com.enumclass.BloodType;
import com.hms.impl.IDataRepository;

public class MedicalRecord implements IDataRepository {
    private String medicalRecordId;
    private String patientId;
    private String patientName;
    private LocalDate dob;
    private boolean gender;
    private String phoneNumber;
    private String email;
    private BloodType bloodType;

    public MedicalRecord(String medicalRecordId, String patientId, String patientName, LocalDate dob, boolean gender, String phoneNumber, String email, BloodType bloodType) {
        this.medicalRecordId = medicalRecordId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.bloodType = bloodType;
    }


    @Override
    public String getDataID() {
        return getMedicalRecordId();
    }

    @Override
    public List<String> getAttributes() {
        return List.of(getMedicalRecordId(), 
        getPatientId(), 
        getPatientName(), 
        getDob().toString(), 
        String.valueOf(getGender()), 
        getPhoneNumber(), 
        getEmail(), 
        getBloodType().toString());
    }

    @Override
    public String getDataName() {
        return "medicalrecord";
    }

    public String getMedicalRecordId() {
        return this.medicalRecordId;
    }

    public void setMedicalRecordId(String medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }


    public String getPatientId() {
        return this.patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean getGender() {
        return this.gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BloodType getBloodType() {
        return this.bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

}
