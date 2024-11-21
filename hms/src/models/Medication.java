package models;

/**
 * The Medication class represents a medication entity in the system.
 */
public class Medication {
    private String medicationId;
    private String medicationName;
    private String description;
    private String dosageForm;     // e.g., tablet, capsule, liquid
    private String strength;       // e.g., 500mg, 10mg/mL
    private String manufacturer;
    private String expirationDate; // Consider using LocalDate
    private String indications;    // Medical conditions the medication treats
    private String contraindications;
    private String sideEffects;

    // Constructors

    public Medication() {
    }

    public Medication(String medicationId, String medicationName, String description,
                      String dosageForm, String strength, String manufacturer,
                      String expirationDate, String indications, String contraindications,
                      String sideEffects) {
        this.medicationId = medicationId;
        this.medicationName = medicationName;
        this.description = description;
        this.dosageForm = dosageForm;
        this.strength = strength;
        this.manufacturer = manufacturer;
        this.expirationDate = expirationDate;
        this.indications = indications;
        this.contraindications = contraindications;
        this.sideEffects = sideEffects;
    }

    
    /** 
     * @return String
     */
    // Getters and Setters

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getContraindications() {
        return contraindications;
    }

    public void setContraindications(String contraindications) {
        this.contraindications = contraindications;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    // CSV conversion methods

    /**
     * Converts the medication to a CSV record.
     *
     * @return An array of strings representing the CSV record.
     */
    public String[] toCSV() {
        return new String[]{
                medicationId,
                medicationName,
                description,
                dosageForm,
                strength,
                manufacturer,
                expirationDate,
                indications,
                contraindications,
                sideEffects
        };
    }

    /**
     * Creates a Medication object from a CSV record.
     *
     * @param record An array of strings representing the CSV record.
     * @return A Medication object.
     */
    public static Medication fromCSV(String[] record) {
        return new Medication(
                record[0],
                record[1],
                record[2],
                record[3],
                record[4],
                record[5],
                record[6],
                record[7],
                record[8],
                record[9]
        );
    }
}
