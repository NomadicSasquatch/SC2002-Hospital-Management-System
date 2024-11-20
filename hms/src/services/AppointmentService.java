package services;

import enums.AppointmentStatus;
import enums.UserRole;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import models.Appointment;
import models.DoctorSchedule;
import models.User;
import repositories.AppointmentRepository;
import repositories.UserRepository;

/**
 * AppointmentService handles business logic related to appointments.
 */
public class AppointmentService {

    private static final int APPOINTMENT_DURATION_MINUTES = 60; // Fixed appointment duration

    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;
    private DoctorScheduleService doctorScheduleService;


    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository,
                              DoctorScheduleService doctorScheduleService) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.doctorScheduleService = doctorScheduleService;
    }

    /**
     * Schedules a new appointment if the slot is available.
     *
     * @param patientId The patient's ID.
     * @param doctorId  The doctor's ID.
     * @param date      The date of the appointment.
     * @param time      The time of the appointment.
     * @return An Optional containing the appointment if successful.
     */
    public List<Appointment> scheduleAppointment(String patientId, String doctorId, String date, String time) {
        // Validate patient and doctor
        List<User> patients = userRepository.getDataById(patientId);
        List<User> doctors = userRepository.getDataById(doctorId);

        if (patients.isEmpty() || doctors.isEmpty()) {
                    System.out.println("Invalid patient or doctor ID.");
                    return Collections.emptyList();
                }

        User doctor = doctors.get(0); // Assuming we take the first matching doctor
        if (!doctor.getRole().equals(UserRole.DOCTOR)) {
            System.out.println("The specified doctor ID does not belong to a doctor.");
            return Collections.emptyList();
        }

        // Parse date and time
        LocalDate appointmentDate;
        LocalTime appointmentTime;

        try {
            appointmentDate = LocalDate.parse(date);
            appointmentTime = LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date or time format.");
            return Collections.emptyList();
        }

        // Calculate appointment end time
        LocalTime appointmentEndTime = appointmentTime.plusMinutes(APPOINTMENT_DURATION_MINUTES);

        // Check if the doctor is available at the given date and time
        boolean isAvailable = isDoctorAvailable(doctorId, appointmentDate, appointmentTime);

        if (!isAvailable) {
            System.out.println("The doctor is not available at the specified date and time.");
            return Collections.emptyList();
        }

        // Generate appointment ID
        String appointmentId = generateAppointmentId();

        Appointment appointment = new Appointment(
                appointmentId,
                patientId,
                doctorId,
                date,
                time,
                AppointmentStatus.PENDING
        );

        appointmentRepository.addItem(appointment);

        // Update the doctor's schedule to reflect the booked appointment
        doctorScheduleService.updateDoctorScheduleAfterBooking(doctorId, appointmentDate, appointmentTime, appointmentEndTime);

        System.out.println("Appointment scheduled successfully.");
        return List.of(appointment);
    }

    
    /** 
     * @return String
     */
    private String generateAppointmentId() {
        return "A" + (appointmentRepository.getAllData().size() + 1);
    }

    private boolean isDoctorAvailable(String doctorId, LocalDate date, LocalTime time) {
        // Check if the time is within any of the doctor's available slots
        List<DoctorSchedule> schedules = doctorScheduleService.getAvailableSlotsForDoctor(doctorId);
        for (DoctorSchedule schedule : schedules) {
            if (schedule.getDate().equals(date)) {
                if (!time.isBefore(schedule.getStartTime()) && !time.isAfter(schedule.getEndTime().minusMinutes(APPOINTMENT_DURATION_MINUTES))) {
                    // Check if there's already an appointment at that time
                    List<Appointment> appointments = appointmentRepository.getAppointmentsByDoctorAndDate(doctorId, date.toString());
                    for (Appointment appointment : appointments) {
                        if (appointment.getTime().equals(time.toString())) {
                            return false; // Time slot already booked
                        }
                    }
                    return true; // Time slot is available
                }
            }
        }
        return false; // No available slot found
    }

    /**
     * Checks if a time slot is available for a doctor.
     *
     * @param doctorId The doctor's ID.
     * @param date     The date to check.
     * @param time     The time to check.
     * @return True if the slot is available, false otherwise.
     */
    public boolean isSlotAvailable(String doctorId, String date, String time) {
        List<Appointment> doctorAppointments = appointmentRepository.getAppointmentsByDoctorId(doctorId);
        for (Appointment appointment : doctorAppointments) {
            if (appointment.getDate().equals(date) && appointment.getTime().equals(time)
                    && !appointment.getStatus().equals(AppointmentStatus.CONFIRMED)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Reschedules an existing appointment.
     *
     * @param appointmentId The ID of the appointment to reschedule.
     * @param newDate       The new date.
     * @param newTime       The new time.
     * @return The rescheduled appointment if successful.
     */
    public List<Appointment> rescheduleAppointment(String appointmentId, String newDate, String newTime) {
        List<Appointment> appointments = appointmentRepository.getDataById(appointmentId);
        if (appointments.isEmpty()) {
            System.out.println("Appointment not found.");
            return Collections.emptyList(); // Return an empty list if no matching appointment is found
        }

        Appointment appointment = appointments.get(0);

        // Check if the new slot is available
        boolean isAvailable = isSlotAvailable(appointment.getDoctorId(), newDate, newTime);
        if (!isAvailable) {
            System.out.println("The new time slot is not available.");
            return Collections.emptyList(); // Return an empty list if the slot is not available
        }

        // Update appointment details
        appointment.setDate(newDate);
        appointment.setTime(newTime);
        appointment.setStatus(AppointmentStatus.PENDING); // Reset status to pending for doctor's approval

        // Update appointment in repository
        appointmentRepository.updateAppointment(appointment);
        System.out.println("Appointment reschedule Pending.");
        return List.of(appointment); // Return a list containing the updated appointment
    }

    /**
     * Cancels an existing appointment.
     *
     * @param appointmentId The ID of the appointment to cancel.
     * @return True if cancellation was successful, false otherwise.
     */
    public boolean cancelAppointment(String appointmentId) {
        List<Appointment> appointments = appointmentRepository.getDataById(appointmentId);
        if (appointments.isEmpty()) {
            System.out.println("Appointment not found.");
            return false;
        }

        Appointment appointment = appointments.get(0);
        appointment.setStatus(AppointmentStatus.AVAILABLE);

        // Update appointment in repository
        appointmentRepository.updateAppointment(appointment);
        System.out.println("Appointment cancelled successfully.");
        return true;
    }

    /**
     * Retrieves a list of available appointment slots for a doctor.
     *
     * @param doctorId The doctor's ID.
     * @return List of available appointment slots.
     */
    public List<String> getAvailableSlots(String doctorId) {
        // For simplicity, let's assume the doctor works from 9 AM to 5 PM in one-hour slots
        String[] slots = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"};
        List<String> availableSlots = new ArrayList<>();

        // For the current date, check each slot
        String currentDate = getCurrentDate(); // Implement getCurrentDate() method
        for (String time : slots) {
            if (isSlotAvailable(doctorId, currentDate, time)) {
                availableSlots.add(time);
            }
        }
        return availableSlots;
    }

    /**
     * Allows doctors to accept or decline appointment requests.
     *
     * @param appointmentId The ID of the appointment.
     * @param action        "accept" or "decline"
     * @return True if the action was successful, false otherwise.
     */
    public boolean respondToAppointmentRequest(String appointmentId, String action) {
        List<Appointment> appointments = appointmentRepository.getDataById(appointmentId);
    
        if (appointments.isEmpty()) {
            System.out.println("Appointment not found.");
            return false;
        }
    
        // Assuming we handle only the first matching appointment
        Appointment appointment = appointments.get(0);
        if (action.equalsIgnoreCase("accept")) {
            appointment.setStatus(AppointmentStatus.CONFIRMED);
        } else if (action.equalsIgnoreCase("decline")) {
            appointment.setStatus(AppointmentStatus.AVAILABLE);
        } else {
            System.out.println("Invalid action. Use 'accept' or 'decline'.");
            return false;
        }

        // Update appointment in repository
        appointmentRepository.updateAppointment(appointment);
        System.out.println("Appointment request has been " + appointment.getStatus() + ".");
        return true;
    }

    /**
     * Retrieves appointments for a patient.
     *
     * @param patientId The patient's ID.
     * @return List of appointments.
     */
    public List<Appointment> getAppointmentsForPatient(String patientId) {
        return appointmentRepository.getAppointmentsByPatientId(patientId);
    }

    /**
     * Retrieves appointments for a doctor.
     *
     * @param doctorId The doctor's ID.
     * @return List of appointments.
     */
    public List<Appointment> getAppointmentsForDoctor(String doctorId) {
        return appointmentRepository.getAppointmentsByDoctorId(doctorId);
    }


   
    /**
     * Gets the current date.
     *
     * @return The current date as a String.
     */
    private String getCurrentDate() {
        // Implement using LocalDate or any other date library
        return java.time.LocalDate.now().toString();
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllData();
    }

    public List<Appointment> getAppointmentById(String appointmentId) {
        return appointmentRepository.getDataById(appointmentId);
    }

    public boolean updateAppointmentStatus(String appointmentId, String status) {
        List<Appointment> appointments = appointmentRepository.getDataById(appointmentId);
    
        if (appointments.isEmpty()) {
            System.out.println("Appointment not found.");
            return false;
        }

        Appointment appointment = appointments.get(0);
        appointment.setStatus(AppointmentStatus.valueOf(status.toUpperCase()));
        appointmentRepository.updateAppointment(appointment);
        System.out.println("Appointment status updated successfully.");
        return true;
    }
}
