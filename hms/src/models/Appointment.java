package models;

import enums.AppointmentStatus;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String date; // Ideally, use LocalDate
    private String time; // Ideally, use LocalTime
    private AppointmentStatus status; // e.g., "available", "pending", "confirmed"
    private String outcomeRecordId;

    public Appointment() {
    }

    public Appointment(String appointmentId, String patientId, String doctorId,
                       String date, String time, AppointmentStatus status) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = status;
        this.outcomeRecordId = "";
    }

    public Appointment(String appointmentId, String patientId, String doctorId,
                       String date, String time, AppointmentStatus status, String outcomeRecordId) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = status;
        this.outcomeRecordId = outcomeRecordId;
    }

    /**
     * Retrieves the unique identifier for the appointment.
     *
     * @return the appointment ID as a String
     */
    public String getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the unique identifier for the appointment.
     *
     * @param appointmentId the appointment ID as a String
     */
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Retrieves the unique identifier for the patient.
     *
     * @return the patient ID as a String
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the unique identifier for the patient.
     *
     * @param patientId the patient ID as a String
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Retrieves the unique identifier for the doctor.
     *
     * @return the doctor ID as a String
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Sets the unique identifier for the doctor.
     *
     * @param doctorId the doctor ID as a String
     */
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Retrieves the date of the appointment.
     * 
     * @return the date of the appointment as a String
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the appointment.
     * 
     * @param date the date of the appointment as a String
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Retrieves the time of the appointment.
     * 
     * @return the time of the appointment as a String
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of the appointment.
     * 
     * @param time the time of the appointment as a String
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Retrieves the status of the appointment.
     * 
     * @return the status of the appointment as an AppointmentStatus enum
     */
    public AppointmentStatus getStatus() {
        return status;
    }

    /**
     * Sets the status of the appointment.
     * 
     * @param status the status of the appointment as an AppointmentStatus enum
     */
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    /**
     * Retrieves the unique identifier for the outcome record.
     * 
     * @return the outcome record ID as a String
     */
    public String getOutcomeRecordId() {
        return outcomeRecordId;
    }

    /**
     * Sets the unique identifier for the outcome record.
     * 
     * @param outcomeRecordId the outcome record ID as a String
     */
    public void setOutcomeRecordId(String outcomeRecordId) {
        this.outcomeRecordId = outcomeRecordId;
    }

    /**
     * Converts the appointment to a CSV record.
     *
     * @return An array of strings representing the CSV record.
     */
    public String[] toCSV() {
        return new String[]{
                appointmentId,
                patientId,
                doctorId,
                date,
                time,
                status.toString(),
                outcomeRecordId
        };
    }

    /**
     * Creates an Appointment object from a CSV record.
     *
     * @param record An array of strings representing the CSV record.
     * @return An Appointment object.
     */
    public static Appointment fromCSV(String[] record) {
        return new Appointment(
                record[0],
                record[1],
                record[2],
                record[3],
                record[4],
                AppointmentStatus.valueOf((record[5]).toUpperCase()),
                record.length > 6 ? record[6] : ""
        );
    }
}
