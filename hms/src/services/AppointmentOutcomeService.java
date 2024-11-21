package services;

import models.AppointmentOutcomeRecord;
import models.Appointment;
import repositories.AppointmentOutcomeRepository;
import repositories.AppointmentRepository;
import java.util.List;
import java.util.Collections;

public class AppointmentOutcomeService {

    private AppointmentOutcomeRepository appointmentOutcomeRepository;
    private AppointmentRepository appointmentRepository;

    public AppointmentOutcomeService(AppointmentOutcomeRepository appointmentOutcomeRepository, AppointmentRepository appointmentRepository) {
        this.appointmentOutcomeRepository = appointmentOutcomeRepository;
        this.appointmentRepository = appointmentRepository;
    }

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

    public List<AppointmentOutcomeRecord> getOutcomesByAppointmentId(String appointmentId) {
        return appointmentOutcomeRepository.getOutcomesByAppointmentId(appointmentId);
    }

    public List<AppointmentOutcomeRecord> getOutcomesByPatientId(String patientId) {
        return appointmentOutcomeRepository.getOutcomesByPatientId(patientId);
    }

    public List<AppointmentOutcomeRecord> getOutcomesByDoctorId(String doctorId) {
        return appointmentOutcomeRepository.getOutcomesByDoctorId(doctorId);
    }

    public void deleteAppointmentOutcome(String outcomeId) {
        appointmentOutcomeRepository.removeItem(outcomeId);
        appointmentOutcomeRepository.saveRepository();
        System.out.println("Appointment outcome record deleted successfully.");
    }

    private String generateAppointmentOutcomeId() {
        return "AO" + (appointmentOutcomeRepository.getAllData().size() + 1);
    }
}