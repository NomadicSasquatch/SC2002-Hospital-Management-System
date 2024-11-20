package repositories;

import models.Medication;
import models.ReplenishmentRequest;
import utils.CSVReader;
import utils.CSVWriter;

import java.util.ArrayList;
import java.util.List;


import abstract_class.CrudRepository;

/**
 * MedicationRepository handles CRUD operations for Medication entities.
 */
public class MedicationRepository extends CrudRepository<Medication, String>{

    private static final String MEDICATION_CSV_FILE = "hms/src/data/medications.csv";

    public MedicationRepository() {
        super(MEDICATION_CSV_FILE);
    }
    /**
     * Updates an existing medication.
     *
     * @param medication The medication with updated information.
     */
    public void updateMedication(Medication medication) {
        List<Medication> existingMedications = getDataById(medication.getMedicationId());
    
        if (!existingMedications.isEmpty()) {
            // Assuming you handle the first matching medication
            Medication existingMedication = existingMedications.get(0);
            removeItem(existingMedication.getMedicationId());
            addItem(medication);
            saveRepository();
        }
    }
    @Override
    protected Medication fromCSV(String[] record) {
        return Medication.fromCSV(record);
    }
    @Override
    protected String[] toCSV(Medication item) {
        return item.toCSV();
    }
    @Override
    protected String getId(Medication item) {
        return item.getMedicationId();
    }
}
