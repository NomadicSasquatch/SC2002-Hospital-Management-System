package models;

import java.util.ArrayList;
import java.util.List;

/**
 * The MedicalRecord class represents a medical record for a patient. It
 * contains information such as patient ID, name, date of birth, gender, contact
 * information, blood type, past diagnoses, and treatments.
 *
 * <p>
 * This class provides methods to add diagnoses and treatments, as well as
 * methods to convert the medical record to and from a CSV format.</p>
 *
 * <p>
 * Example usage:</p>
 * <pre>
 * {@code
 * MedicalRecord record = new MedicalRecord();
 * record.setPatientId("12345");
 * record.setName("John Doe");
 * record.setDateOfBirth("1990-01-01");
 * record.setGender("Male");
 * record.setContactInfo("123-456-7890");
 * record.setBloodType("O+");
 * record.addDiagnosis("Hypertension");
 * record.addTreatment("Medication");
 *
 * String[] csvRecord = record.toCSV();
 * MedicalRecord newRecord = MedicalRecord.fromCSV(csvRecord);
 * }
 * </pre>
 *
 * <p>
 * Note: Consider using LocalDate for dateOfBirth instead of String.</p>
 *
 * @see java.util.List
 * @see java.util.ArrayList
 */
public class MedicalRecord {

    private String patientId;
    private String name;
    private String dateOfBirth; // Consider using LocalDate
    private String gender;
    private String bloodType;
    private List<String> pastDiagnoses;
    private List<String> treatments;

    // Constructors, getters, and setters
    public MedicalRecord() {
        this.pastDiagnoses = new ArrayList<>();
        this.treatments = new ArrayList<>();
    }

    public MedicalRecord(String patientId, String name, String dateOfBirth, String gender,
            String bloodType, List<String> pastDiagnoses, List<String> treatments) {
        this.patientId = patientId;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.pastDiagnoses = pastDiagnoses;
        this.treatments = treatments;
    }

    /**
     * Adds a diagnosis to the patient's medical record.
     *
     * @param diagnosis The diagnosis added by the Doctor on the patient's
     * record.
     */
    public void addDiagnosis(String diagnosis) {
        this.pastDiagnoses.add(diagnosis);
    }

    /**
     * Adds a treatment to the patient's treatment list.
     *
     * @param treatment The treatment to be added.
     */
    public void addTreatment(String treatment) {
        this.treatments.add(treatment);
    }

    /**
     * Gets the patient's unique identifier.
     *
     * @return The patient's unique identifier.
     */
    public String getPatientId() {
        return patientId;
    }


    /**
     * Sets the patient's unique identifier.
     *
     * @param patientId The patient's unique identifier.
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Gets the patient's name.
     *
     * @return The patient's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the patient's name.
     *
     * @param name The patient's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the patient's date of birth.
     *
     * @return The patient's date of birth.
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the patient's date of birth.
     *
     * @param dateOfBirth The patient's date of birth.
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the patient's gender.
     *
     * @return The patient's gender.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the patient's gender.
     *
     * @param gender The patient's gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the patient's blood type.
     *
     * @return The patient's blood type.
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * Sets the patient's blood type.
     *
     * @param bloodType The patient's blood type.
     */
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Gets the patient's past diagnoses.
     *
     * @return A list of the patient's past diagnoses.
     */
    public List<String> getPastDiagnoses() {
        return pastDiagnoses;
    }

    /**
     * Sets the patient's past diagnoses.
     *
     * @param pastDiagnoses A list of the patient's past diagnoses.
     */
    public void setPastDiagnoses(List<String> pastDiagnoses) {
        this.pastDiagnoses = pastDiagnoses;
    }

    /**
     * Gets the patient's treatments.
     *
     * @return A list of the patient's treatments.
     */
    public List<String> getTreatments() {
        return treatments;
    }

    /**
     * Sets the patient's treatments.
     *
     * @param treatments A list of the patient's treatments.
     */
    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }

    /**
     * Converts the medical record to a CSV record.
     *
     * @return An array of strings representing the CSV record.
     */
    public String[] toCSV() {
        return new String[]{
            patientId,
            name,
            dateOfBirth,
            gender,
            bloodType,
            String.join(";", pastDiagnoses == null ? List.of("") : pastDiagnoses),
            String.join(";", treatments == null ? List.of("") : treatments)
        };
    }

    /**
     * Creates a MedicalRecord object from a CSV record.
     *
     * @param record An array of strings representing the CSV record.
     * @return A MedicalRecord object.
     */
    public static MedicalRecord fromCSV(String[] record) {
        List<String> diagnoses = new ArrayList<>();
        if (record.length > 6 && !record[6].isEmpty()) {
            String[] diagArray = record[6].split(";");
            for (String diag : diagArray) {
                diagnoses.add(diag);
            }
        }

        List<String> treatments = new ArrayList<>();
        if (record.length > 7 && !record[7].isEmpty()) {
            String[] treatArray = record[7].split(";");
            for (String treat : treatArray) {
                treatments.add(treat);
            }
        }

        return new MedicalRecord(
                record[0],
                record[1],
                record[2],
                record[3],
                record[4],
                diagnoses,
                treatments);
    }
}
