package services;

import java.util.List;

import models.Appointment;
import models.AppointmentOutcomeRecord;
import repositories.AppointmentOutcomeRepository;
import repositories.AppointmentRepository;

public class AppointmentOutcomeService {

    private AppointmentOutcomeRepository appointmentOutcomeRepository;
    private AppointmentRepository appointmentRepository;

    /**
     * Service class for managing appointment outcomes.
     * 
     * This service provides methods to handle the business logic related to appointment outcomes.
     * It interacts with the AppointmentOutcomeRepository and AppointmentRepository to perform
     * necessary operations.
     * 
     * @param appointmentOutcomeRepository the repository for appointment outcomes
     * @param appointmentRepository the repository for appointments
     */
    public AppointmentOutcomeService(AppointmentOutcomeRepository appointmentOutcomeRepository, AppointmentRepository appointmentRepository) {
        this.appointmentOutcomeRepository = appointmentOutcomeRepository;
        this.appointmentRepository = appointmentRepository;
    }

    /**
     * Adds an appointment outcome record to the repository.
     *
     * @param outcomeRecord The appointment outcome record to be added.
     *                       It contains details about the outcome of an appointment.
     * @throws IllegalArgumentException if the appointment associated with the outcome record is not found.
     */
    public void addAppointmentOutcome(AppointmentOutcomeRecord outcomeRecord) {
        List<Appointment> appointments = appointmentRepository.getDataById(outcomeRecord.getAppointmentId());
        if (appointments.isEmpty()) {
            System.out.println("Appointment not found.");
            return;
        }

        String outcomeId = generateAppointmentOutcomeId();
        outcomeRecord.setAppointmentOutcomeId(outcomeId);


        appointmentOutcomeRepository.addItem(outcomeRecord);
        System.out.println("Appointment outcome record added successfully.");
    }

    /**
     * Updates an existing appointment outcome record.
     *
     * This method retrieves the existing appointment outcome record by its ID.
     * If the record is found, it removes the old record, adds the updated record,
     * and saves the repository. If the record is not found, it prints an error message.
     *
     * @param outcomeRecord The updated appointment outcome record to be saved.
     */
    public void updateAppointmentOutcome(AppointmentOutcomeRecord outcomeRecord) {
        List<AppointmentOutcomeRecord> existingRecords = appointmentOutcomeRepository.getDataById(outcomeRecord.getAppointmentOutcomeId());
        if (existingRecords.isEmpty()) {
            System.out.println("Appointment outcome record not found.");
            return;
        }

        appointmentOutcomeRepository.removeItem(outcomeRecord.getAppointmentOutcomeId());
        appointmentOutcomeRepository.addItem(outcomeRecord);
        appointmentOutcomeRepository.saveRepository();
        System.out.println("Appointment outcome record updated successfully.");
    }

    /**
     * Retrieves a list of appointment outcome records by the given appointment ID.
     *
     * @param appointmentId the ID of the appointment for which to retrieve outcome records
     * @return a list of AppointmentOutcomeRecord objects associated with the specified appointment ID
     */
    public List<AppointmentOutcomeRecord> getOutcomesByAppointmentId(String appointmentId) {
        return appointmentOutcomeRepository.getOutcomesByAppointmentId(appointmentId);
    }

    /**
     * Retrieves a list of appointment outcome records for a given patient ID.
     *
     * @param patientId the ID of the patient whose appointment outcomes are to be retrieved
     * @return a list of AppointmentOutcomeRecord objects associated with the specified patient ID
     */
    public List<AppointmentOutcomeRecord> getOutcomesByPatientId(String patientId) {
        return appointmentOutcomeRepository.getOutcomesByPatientId(patientId);
    }

    /**
     * Retrieves a list of appointment outcome records for a specific doctor.
     *
     * @param doctorId the unique identifier of the doctor
     * @return a list of AppointmentOutcomeRecord objects associated with the specified doctor
     */
    public List<AppointmentOutcomeRecord> getOutcomesByDoctorId(String doctorId) {
        return appointmentOutcomeRepository.getOutcomesByDoctorId(doctorId);
    }

    /**
     * Deletes an appointment outcome record by its ID.
     *
     * @param outcomeId the ID of the appointment outcome to be deleted
     */
    public void deleteAppointmentOutcome(String outcomeId) {
        appointmentOutcomeRepository.removeItem(outcomeId);
        appointmentOutcomeRepository.saveRepository();
        System.out.println("Appointment outcome record deleted successfully.");
    }

    /**
     * Generates a unique appointment outcome ID.
     * The ID is generated by prefixing "AO" to the current size of the appointment outcome repository data plus one.
     *
     * @return A unique appointment outcome ID as a String.
     */
    private String generateAppointmentOutcomeId() {
        return "AO" + (appointmentOutcomeRepository.getAllData().size() + 1);
    }
}