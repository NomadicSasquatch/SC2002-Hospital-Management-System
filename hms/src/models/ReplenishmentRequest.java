package models;

public class ReplenishmentRequest {
    private String requestId;
    private String medicationId;
    private int quantity;
    private String pharmacistId;
    private String status; // e.g., "pending", "approved", "declined"

    // Constructors, getters, and setters

    public ReplenishmentRequest() {
    }

    public ReplenishmentRequest(String requestId, String medicationId, int quantity, String pharmacistId, String status) {
        this.requestId = requestId;
        this.medicationId = medicationId;
        this.quantity = quantity;
        this.pharmacistId = pharmacistId;
        this.status = status;
    }

    // Getters and Setters for all fields...

    /**
     * Converts the replenishment request to a CSV record.
     *
     * @return An array of strings representing the CSV record.
     */
    public String[] toCSV() {
        return new String[]{
                requestId,
                medicationId,
                String.valueOf(quantity),
                pharmacistId,
                status
        };
    }

    /**
     * Creates a ReplenishmentRequest object from a CSV record.
     *
     * @param record An array of strings representing the CSV record.
     * @return A ReplenishmentRequest object.
     */
    public static ReplenishmentRequest fromCSV(String[] record) {
        return new ReplenishmentRequest(
                record[0],
                record[1],
                Integer.parseInt(record[2]),
                record[3],
                record[4]
        );
    }

    
    /** 
     * @param string
     */
    public void setStatus(String string) {
        status = string;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getPharmacistId() {
        return pharmacistId;
    }
}
