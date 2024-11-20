package services;

import models.Prescription;
import models.User;
import models.InventoryItem;
import repositories.PrescriptionRepository;
import repositories.UserRepository;
import repositories.InventoryRepository;

import java.util.List;
/**
 * PrescriptionService handles business logic related to prescriptions.
 */
public class PrescriptionService {

    private PrescriptionRepository prescriptionRepository;
    private UserRepository userRepository;
    private InventoryRepository inventoryRepository;

    public PrescriptionService(PrescriptionRepository prescriptionRepository,
                               UserRepository userRepository,
                               InventoryRepository inventoryRepository) {
        this.prescriptionRepository = prescriptionRepository;
        this.userRepository = userRepository;
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Generates a unique prescription ID.
     *
     * @return A unique prescription ID.
     */
    public String generatePrescriptionId() {
        // Simple ID generation logic; ensure uniqueness in production
        return "PR" + (prescriptionRepository.getAllData().size() + 1);
    }

    /**
     * Adds a new prescription to the system.
     *
     * @param prescription The prescription to add.
     * @return True if the prescription was added successfully.
     */
    public boolean addPrescription(Prescription prescription) {
        // Validate doctor
        List<User> doctors = userRepository.getDataById(prescription.getDoctorId())
                                        .stream()
                                        .filter(user -> user.getRole().equalsIgnoreCase("Doctor"))
                                        .toList();
        if (doctors.isEmpty()) {
            System.out.println("Invalid doctor ID.");
            return false;
        }

        // Validate patient
        List<User> patients = userRepository.getDataById(prescription.getPatientId())
                                            .stream()
                                            .filter(user -> user.getRole().equalsIgnoreCase("Patient"))
                                            .toList();
        if (patients.isEmpty()) {
            System.out.println("Invalid patient ID.");
            return false;
        }

        // Validate medication
        List<InventoryItem> inventoryItems = inventoryRepository.getDataById(prescription.getMedicationId());
        if (inventoryItems.isEmpty()) {
            System.out.println("Medication not found in inventory.");
            return false;
        }

        // Add prescription
        prescriptionRepository.addItem(prescription);
        System.out.println("Prescription added successfully.");
        return true;
    }


    /**
     * Updates the status of a prescription.
     *
     * @param prescriptionId The ID of the prescription to update.
     * @param newStatus      The new status (e.g., "dispensed").
     * @return True if the update was successful.
     */
    public boolean updatePrescriptionStatus(String prescriptionId, String newStatus) {
        List<Prescription> prescriptions = prescriptionRepository.getDataById(prescriptionId);
    
        if (prescriptions.isEmpty()) {
            System.out.println("Prescription not found.");
            return false;
        }
    
        // Assuming we handle the first matching prescription
        Prescription prescription = prescriptions.get(0);
        prescription.setStatus(newStatus);
        prescriptionRepository.updatePrescription(prescription);
        System.out.println("Prescription status updated successfully.");
        return true;
    }
    

    /**
     * Retrieves prescriptions for a specific patient.
     *
     * @param patientId The patient's ID.
     * @return List of prescriptions for the patient.
     */
    public List<Prescription> getPrescriptionsByPatientId(String patientId) {
        return prescriptionRepository.getPrescriptionsByPatientId(patientId);
    }

    /**
     * Retrieves prescriptions issued by a specific doctor.
     *
     * @param doctorId The doctor's ID.
     * @return List of prescriptions issued by the doctor.
     */
    public List<Prescription> getPrescriptionsByDoctorId(String doctorId) {
        return prescriptionRepository.getPrescriptionsByDoctorId(doctorId);
    }

    /**
     * Retrieves prescriptions with a specific status.
     *
     * @param status The status to filter by (e.g., "pending").
     * @return List of prescriptions with the specified status.
     */
    public List<Prescription> getPrescriptionsByStatus(String status) {
        return prescriptionRepository.getPrescriptionsByStatus(status);
    }

    /**
     * Dispenses a prescription, updating inventory and prescription status.
     *
     * @param prescriptionId The ID of the prescription to dispense.
     * @param pharmacistId   The ID of the pharmacist dispensing the medication.
     * @return True if the prescription was dispensed successfully.
     */
    public boolean dispensePrescription(String prescriptionId, String pharmacistId) {
        // Validate pharmacist
        List<User> pharmacists = userRepository.getDataById(pharmacistId)
                                               .stream()
                                               .filter(user -> user.getRole().equalsIgnoreCase("Pharmacist"))
                                               .toList();
        if (pharmacists.isEmpty()) {
            System.out.println("Invalid pharmacist ID.");
            return false;
        }
    
        // Validate prescription
        List<Prescription> prescriptions = prescriptionRepository.getDataById(prescriptionId);
        if (prescriptions.isEmpty()) {
            System.out.println("Prescription not found.");
            return false;
        }
    
        Prescription prescription = prescriptions.get(0);
    
        // Check if already dispensed
        if (prescription.getStatus().equalsIgnoreCase("dispensed")) {
            System.out.println("Prescription has already been dispensed.");
            return false;
        }
    
        // Validate medication availability
        List<InventoryItem> inventoryItems = inventoryRepository.getDataById(prescription.getMedicationId());
        if (inventoryItems.isEmpty()) {
            System.out.println("Medication not found in inventory.");
            return false;
        }
    
        InventoryItem item = inventoryItems.get(0);
    
        if (item.getQuantity() < prescription.getQuantity()) {
            System.out.println("Insufficient stock to dispense the prescription.");
            return false;
        }
    
        // Update inventory
        item.setQuantity(item.getQuantity() - prescription.getQuantity());
        inventoryRepository.updateInventoryItem(item);
    
        // Update prescription status
        prescription.setStatus("dispensed");
        prescriptionRepository.updatePrescription(prescription);
    
        System.out.println("Prescription dispensed successfully.");
        return true;
    }
    
}
