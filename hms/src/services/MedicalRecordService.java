package services;

import enums.UserRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import models.MedicalRecord;
import models.User;
import repositories.MedicalRecordRepository;
import repositories.UserRepository;

/**
 * MedicalRecordService handles business logic related to medical records.
 */
public class MedicalRecordService {

    private MedicalRecordRepository medicalRecordRepository;
    private UserRepository userRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository, UserRepository userRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a medical record by patient ID.
     *
     * @param patientId The patient's ID.
     * @return An Optional containing the medical record if found.
     */
    public List<MedicalRecord> getMedicalRecordByPatientId(String patientId) {
        return medicalRecordRepository.getMedicalRecordByPatientId(patientId);
    }

    /**
     * Allows a doctor to add a diagnosis to a patient's medical record.
     *
     * @param doctorId   The doctor's ID.
     * @param patientId  The patient's ID.
     * @param diagnosis  The diagnosis to add.
     * @return True if the diagnosis was added successfully.
     */
    public boolean addDiagnosis(String doctorId, String patientId, String diagnosis) {
        // Verify doctor exists and has appropriate role
        List<User> doctors = userRepository.getDataById(doctorId);
        if (doctors.isEmpty() || !doctors.get(0).getRole().equals(UserRole.DOCTOR)) {
            System.out.println("Invalid doctor ID.");
            return false;
        }
    

        // Verify patient exists
        List<MedicalRecord> records = medicalRecordRepository.getMedicalRecordByPatientId(patientId);
        if (records.isEmpty()) {
            System.out.println("Medical record not found.");
            return false;
        }

      // Assuming we handle the first matching medical record
      MedicalRecord record = records.get(0);
      record.addDiagnosis(diagnosis);
      medicalRecordRepository.updateMedicalRecord(record);
      System.out.println("Diagnosis added to medical record.");
      return true;
    }

    /**
     * Allows a doctor to add a treatment to a patient's medical record.
     *
     * @param doctorId   The doctor's ID.
     * @param patientId  The patient's ID.
     * @param treatment  The treatment to add.
     * @return True if the treatment was added successfully.
     */
    public boolean addTreatment(String doctorId, String patientId, String treatment) {
        List<User> doctors = userRepository.getDataById(doctorId);
        if (doctors.isEmpty() || !doctors.get(0).getRole().equals(UserRole.DOCTOR)) {
            System.out.println("Invalid doctor ID.");
            return false;
        }
    
        // Verify patient exists
        List<MedicalRecord> records = medicalRecordRepository.getMedicalRecordByPatientId(patientId);
        if (records.isEmpty()) {
            System.out.println("Medical record not found.");
            return false;
        }
    
        // Assuming we handle the first matching medical record
        MedicalRecord record = records.get(0);
        record.addTreatment(treatment);
        medicalRecordRepository.updateMedicalRecord(record);
        System.out.println("Treatment added to medical record.");
        return true;
    }

    /**
     * Retrieves all medical records (for administrative purposes).
     *
     * @return List of all medical records.
     */
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordRepository.getAllData();
    }

     /**
     * Creates a new medical record for a patient.
     *
     * @param patientId   The patient's ID.
     * @param name        The patient's name.
     * @param dateOfBirth The patient's date of birth.
     * @param gender      The patient's gender.
     * @param contactInfo The patient's contact information.
     * @param bloodType   The patient's blood type.
     * @return True if the medical record was created successfully.
     */
    public boolean createMedicalRecord(String patientId, String name, String dateOfBirth,
                                       String gender, String contactInfo, String bloodType) {
        List<MedicalRecord> existingRecord = medicalRecordRepository.getMedicalRecordByPatientId(patientId);
        if (!existingRecord.isEmpty()) {
            System.out.println("Medical record already exists for this patient.");
            return false;
        }

        MedicalRecord newRecord = new MedicalRecord(
                patientId,
                name,
                dateOfBirth,
                gender,
                contactInfo,
                new ArrayList<>(),  
                new ArrayList<>()
        );
        medicalRecordRepository.addItem(newRecord);
        System.out.println("Medical record created successfully.");
        return true;
    }
}
