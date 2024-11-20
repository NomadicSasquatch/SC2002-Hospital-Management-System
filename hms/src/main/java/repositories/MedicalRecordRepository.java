package repositories;

import models.MedicalRecord;
import utils.CSVReader;
import utils.CSVWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import abstract_class.CrudRepository;

/**
 * MedicalRecordRepository handles CRUD operations for MedicalRecord entities.
 */
public class MedicalRecordRepository extends CrudRepository<MedicalRecord, String> {

    private static final String MEDICAL_RECORD_CSV_FILE = "src/data/medical_records.csv";

    public MedicalRecordRepository() {
        super(MEDICAL_RECORD_CSV_FILE);
    }
    /**
     * Updates an existing medical record.
     *
     * @param medicalRecord The medical record with updated information.
     */
    public void updateMedicalRecord(MedicalRecord medicalRecord) {
        List<MedicalRecord> existingRecords = getDataById(medicalRecord.getPatientId());
    
        if (!existingRecords.isEmpty()) {
            // Assuming you handle the first matching medical record
            MedicalRecord existingRecord = existingRecords.get(0);
            removeItem(existingRecord.getPatientId());
            addItem(medicalRecord);
            saveRepository();
        }
    }
    

    /**
     * Retrieves a medical record by patient ID.
     *
     * @param patientId The patient's ID.
     * @return An Optional containing the medical record if found.
     */
    public List<MedicalRecord> getMedicalRecordByPatientId(String patientId) {
        return items.stream()
                    .filter(mr -> mr.getPatientId().equals(patientId))
                    .collect(Collectors.toList()); // Collect all matching records into a list
    }

    @Override
    protected MedicalRecord fromCSV(String[] record) {
        return MedicalRecord.fromCSV(record);
    }

    @Override
    protected String[] toCSV(MedicalRecord item) {
        return item.toCSV();
    }
    @Override
    protected String getId(MedicalRecord item) {
        // Construct a unique ID for DoctorSchedule
        // Assuming combination of doctorId, date, and startTime is unique
        return item.getPatientId();
    }
}
