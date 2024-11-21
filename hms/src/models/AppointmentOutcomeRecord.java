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

    /**
     * Constructs an AppointmentOutcomeRecord with the specified details.
     *
     * @param appointmentOutcomeId the unique identifier for the appointment outcome
     * @param appointmentId the unique identifier for the appointment
     * @param patientId the unique identifier for the patient
     * @param doctorId the unique identifier for the doctor
     * @param date the date of the appointment outcome
     * @param diagnosis the diagnosis given during the appointment
     * @param treatment the treatment prescribed during the appointment
     * @param notes any additional notes related to the appointment outcome
     */
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

    /**
     * Retrieves the appointment outcome ID.
     *
     * @return the appointment outcome ID
     */
    public String getAppointmentOutcomeId() {
        return appointmentOutcomeId;
    }

    /**
     * Sets the unique identifier for the appointment outcome record.
     *
     * @param appointmentOutcomeId The new appointment outcome ID to set
     */
    public void setAppointmentOutcomeId(String appointmentOutcomeId) {
        this.appointmentOutcomeId = appointmentOutcomeId;
    }

    /**
     * Gets the unique identifier of the appointment associated with this outcome record.
     * 
     * @return The appointment ID as a String
     */
    public String getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the unique identifier for the appointment associated with this outcome record.
     *
     * @param appointmentId The unique identifier string to be set for the appointment
     */
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    /**
     * Retrieves the patient ID associated with this appointment outcome record.
     * 
     * @return the unique identifier of the patient
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the patient ID for this appointment outcome record.
     * 
     * @param patientId The unique identifier of the patient to be set
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Gets the ID of the doctor associated with this appointment outcome.
     * 
     * @return The doctor's unique identifier as a String
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Sets the doctor ID for this appointment outcome record.
     * 
     * @param doctorId The unique identifier of the doctor to be set
     */
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Gets the date of the appointment outcome record.
     * @return the date string of when the appointment took place
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date for this appointment outcome record.
     * @param date The date to be set, represented as a String
     */
    public void setDate(String date) {
        this.date = date;
    }


    /**
     * Gets the diagnosis details from the appointment outcome record.
     * 
     * @return the diagnosis as a String
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the diagnosis description for this appointment outcome record.
     * @param diagnosis the diagnosis to be set
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }


    /**
     * Gets the treatment details prescribed during the appointment.
     * @return A String containing the treatment description.
     */
    public String getTreatment() {
        return treatment;
    }

    /**
     * Sets the treatment administered during the appointment.
     * @param treatment The treatment description to be set
     */
    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }


    /**
     * Gets the notes associated with the appointment outcome.
     * 
     * @return String containing the notes of the appointment outcome
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the notes or remarks for this appointment outcome record.
     * 
     * @param notes The notes to be set for this appointment outcome record
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Converts the appointment outcome record to a CSV format.
     * 
     * @return String array containing the appointment outcome record data in the following order:
     *         [appointmentOutcomeId, appointmentId, patientId, doctorId, date, diagnosis, treatment, notes]
     */
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

    /**
     * Creates an AppointmentOutcomeRecord object from a CSV record.
     * 
     * @param record String array containing the appointment outcome record data in CSV format
     *               where:
     *               record[0] = Appointment Outcome ID
     *               record[1] = Appointment ID
     *               record[2] = Patient ID
     *               record[3] = Doctor ID
     *               record[4] = Date
     *               record[5] = Diagnosis
     *               record[6] = Treatment
     *               record[7] = Notes
     * @return A new AppointmentOutcomeRecord object populated with the CSV data
     */
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