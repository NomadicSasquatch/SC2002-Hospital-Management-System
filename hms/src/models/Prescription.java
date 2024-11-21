package models;

import java.time.LocalDate;

import enums.PrescriptionStatus;

/**
 * The Prescription class represents a prescription entity in the system.
 */
public class Prescription {
    private String prescriptionId;
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String medicationId;
    private String medicationName;
    private String dosage;
    private int quantity;
    private String instructions;
    private PrescriptionStatus status;          
    private LocalDate datePrescribed;

    // Constructors

    public Prescription() {
    }

    public Prescription(String prescriptionId, String appointmentId, String patientId, String doctorId,
                        String medicationId, String medicationName, String dosage, int quantity,
                        String instructions, PrescriptionStatus status, LocalDate datePrescribed) {
        this.prescriptionId = prescriptionId;
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.quantity = quantity;
        this.instructions = instructions;
        this.status = status;
        this.datePrescribed = datePrescribed;
    }

    
    /** 
     * @return String
     */
    // Getters and Setters

    public String getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(String prescriptionId) {
        this.prescriptionId = prescriptionId;
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

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    public PrescriptionStatus getStatus() {
        return status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

    public LocalDate getDatePrescribed() {
        return datePrescribed;
    }

    public void setDatePrescribed(LocalDate datePrescribed) {
        this.datePrescribed = datePrescribed;
    }

    // CSV conversion methods

    /**
     * Converts the prescription to a CSV record.
     *
     * @return An array of strings representing the CSV record.
     */
    public String[] toCSV() {
        return new String[]{
                prescriptionId,
                appointmentId,
                patientId,
                doctorId,
                medicationId,
                medicationName,
                dosage,
                String.valueOf(quantity),
                instructions,
                status.toString(),
                datePrescribed.toString()
        };
    }

    /**
     * Creates a Prescription object from a CSV record.
     *
     * @param record A string array containing prescription data in the following order:
     *               [0] - Prescription ID
     *               [1] - Appointment ID
     *               [2] - Patient ID
     *               [3] - Doctor ID
     *               [4] - Medication ID
     *               [5] - Medication name
     *               [6] - Dosage
     *               [7] - Quantity
     *               [8] - Instructions
     *               [9] - Status (@see {PrescriptionStatus} enum)
     *               [10] - Date (as LocalDate)
     * @return A new Prescription object populated with the CSV data
     * @throws NumberFormatException if the duration cannot be parsed as an integer
     * @throws IllegalArgumentException if the prescription status is invalid
     * @throws DateTimeParseException if the date cannot be parsed
     */
    public static Prescription fromCSV(String[] record) {
        return new Prescription(
                record[0],
                record[1],
                record[2],
                record[3],
                record[4],
                record[5],
                record[6],
                Integer.parseInt(record[7]),
                record[8],
                PrescriptionStatus.valueOf(record[9].toUpperCase()),
                LocalDate.parse(record[10])
        );
    }
}
