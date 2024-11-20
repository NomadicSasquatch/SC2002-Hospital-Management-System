package repositories;

import models.Prescription;
import models.ReplenishmentRequest;
import utils.CSVReader;
import utils.CSVWriter;

import java.util.ArrayList;
import java.util.List;

import abstract_class.CrudRepository;

/**
 * PrescriptionRepository handles CRUD operations for Prescription entities.
 */
public class PrescriptionRepository extends CrudRepository<Prescription, String> {

    private static final String PRESCRIPTION_CSV_FILE = "src/data/prescriptions.csv";

    public PrescriptionRepository() {
        super(PRESCRIPTION_CSV_FILE);
    }
    /**
     * Updates an existing prescription.
     *
     * @param prescription The prescription with updated information.
     */
    public void updatePrescription(Prescription prescription) {
        List<Prescription> existingPrescriptions = getDataById(prescription.getPrescriptionId());
    
        if (!existingPrescriptions.isEmpty()) {
            // Assuming you handle the first matching prescription
            Prescription existingPrescription = existingPrescriptions.get(0);
            removeItem(existingPrescription.getPrescriptionId());
            addItem(prescription);
            saveRepository();
        }
    }
    
    /**
     * Retrieves prescriptions for a specific patient.
     *
     * @param patientId The patient's ID.
     * @return List of prescriptions for the patient.
     */
    public List<Prescription> getPrescriptionsByPatientId(String patientId) {
        List<Prescription> result = new ArrayList<>();
        for (Prescription p : items) {
            if (p.getPatientId().equals(patientId)) {
                result.add(p);
            }
        }
        return result;
    }

    /**
     * Retrieves prescriptions for a specific doctor.
     *
     * @param doctorId The doctor's ID.
     * @return List of prescriptions issued by the doctor.
     */
    public List<Prescription> getPrescriptionsByDoctorId(String doctorId) {
        List<Prescription> result = new ArrayList<>();
        for (Prescription p : items) {
            if (p.getDoctorId().equals(doctorId)) {
                result.add(p);
            }
        }
        return result;
    }

    public List<Prescription> getPrescriptionsByStatus(String status) {
        List<Prescription> result = new ArrayList<>();
        for (Prescription p : items) {
            if (p.getStatus().equals(status)) {
                result.add(p);
            }
        }
        return result;
    }
    @Override
    protected Prescription fromCSV(String[] record) {
        return Prescription.fromCSV(record);
    }
    @Override
    protected String[] toCSV(Prescription item) {
        return item.toCSV();
    }
    @Override
    protected String getId(Prescription item) {
        return item.getPrescriptionId();
    }
}
