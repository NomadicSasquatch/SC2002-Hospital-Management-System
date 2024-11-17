package com.hms;

import java.time.LocalDate;
import java.util.List;

import com.enumclass.Services;
import com.hms.impl.IDataRepository;

public class AppointmentOutcomeRecord implements IDataRepository {
    String recordId;
    private LocalDate appointmentDate;
    private Services serviceType;
    private List<Medication> prescriptions;
    private String consultationNotes;

    public AppointmentOutcomeRecord(String recordId, LocalDate appointmenDate, Services serviceType, List<Medication> presriptions, String consultationNotes) {
        this.recordId = recordId;
        this.appointmentDate = appointmenDate;
        this.serviceType = serviceType;
        this.prescriptions = presriptions;
        this.consultationNotes = consultationNotes;
    }
    @Override
    public String getDataID() {
        return getRecordId();
    }
    @Override
    public List<String> getAttributes() {
        //check formatting of appointmentdate to string and service type to string, conversion of prescription list
        return List.of(getRecordId(), appointmentDate.toString(), serviceType.toString() );
    }
    @Override
    public String getDataName() {
        return "appointmentoutcomerecord";
    }

    public String getRecordId() {
        return this.recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    public LocalDate getAppointmentDate() {
        return this.appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Services getServiceType() {
        return this.serviceType;
    }

    public void setServiceType(Services serviceType) {
        this.serviceType = serviceType;
    }

    public List<Medication> getPrescriptions() {
        return this.prescriptions;
    }

    public void setPrescriptions(List<Medication> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public String getConsultationNotes() {
        return this.consultationNotes;
    }

    public void setConsultationNotes(String consultationNotes) {
        this.consultationNotes = consultationNotes;
    }
    
}
