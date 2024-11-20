package models;

public class AppointmentOutcomeRecord {
    private String appointmentOutcomeId;
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String date;
    private String diagnosis;
    private String treatment;
    private String notes;

    public AppointmentOutcomeRecord() {

    }

    public AppointmentOutcomeRecord(String appointmentOutcomeId, String appointmentId, String patientId,
                                    String doctorId, String date, String diagnosis, String treatment, String notes) {
        this.appointmentOutcomeId = appointmentOutcomeId;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
    }

    public String getAppointmentOutcomeId() {
        return appointmentOutcomeId;
    }

    public void setAppointmentOutcomeId(String appointmentOutcomeId) {
        this.appointmentOutcomeId = appointmentOutcomeId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }


    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String[] toCSV() {
        return new String[]{
                appointmentOutcomeId,
                appointmentId,
                patientId,
                doctorId,
                date,
                diagnosis,
                treatment,
                notes
        };
    }

    public static AppointmentOutcomeRecord fromCSV(String[] record) {
        return new AppointmentOutcomeRecord(
                record[0],
                record[1],
                record[2],
                record[3],
                record[4],
                record[5],
                record[6], 
                record[7]
        );
    }
}