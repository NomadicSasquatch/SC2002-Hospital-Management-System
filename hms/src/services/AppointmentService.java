package services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import enums.AppointmentStatus;
import enums.UserRole;
import models.Appointment;
import models.DoctorSchedule;
import models.User;
import repositories.AppointmentRepository;
import repositories.UserRepository;

/**
 * AppointmentService handles business logic related to appointments, including scheduling, rescheduling, cancellation,
 * and checking the availability of doctors for appointments.
 */
public class AppointmentService {

    private static final int APPOINTMENT_DURATION_MINUTES = 60; // Fixed appointment duration

    private AppointmentRepository appointmentRepository;
    private UserRepository userRepository;
    private DoctorScheduleService doctorScheduleService;
    private Scanner scanner;


    /**
     * Constructs an AppointmentService with the specified repositories and doctor schedule service.
     *
     * @param appointmentRepository the repository for managing appointments
     * @param userRepository the repository for managing users
     * @param doctorScheduleService the service for managing doctor schedules
     */
    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository,
                              DoctorScheduleService doctorScheduleService) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
        this.doctorScheduleService = doctorScheduleService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Schedules a new appointment if the slot is available.
     *
     * @param patientId The patient's ID.
     * @param doctorId  The doctor's ID.
     * @param date      The date of the appointment.
     * @param time      The time of the appointment.
     * @return A list containing the scheduled appointment if successful, or an empty list if unsuccessful.
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
     * Generates a unique appointment ID based on the current size of the appointment repository.
     * 
     * @return A string representing the generated appointment ID.
     */
    private String generateAppointmentId() {
        return "A" + (appointmentRepository.getAllData().size() + 1);
    }

    /**
     * Checks whether a doctor is available for a given date and time.
     *
     * @param doctorId The doctor's ID.
     * @param date     The date to check.
     * @param time     The time to check.
     * @return True if the doctor is available at the given date and time, otherwise false.
     */
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
     * Checks if a time slot is available for a doctor on a specific date.
     *
     * @param doctorId The doctor's ID.
     * @param date     The date to check.
     * @param time     The time to check.
     * @return True if the time slot is available, false otherwise.
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
     * @return The rescheduled appointment if successful, otherwise an empty list.
     */
    public List<Appointment> rescheduleAppointment(String appointmentId) {
        List<Appointment> appointments = appointmentRepository.getDataById(appointmentId);
        if (appointments.isEmpty()) {
            System.out.println("Appointment not found.");
            return Collections.emptyList(); // Return an empty list if no matching appointment is found
        }
        Appointment appointment = appointments.get(0);
        doctorScheduleService.getAvailableSlotsForDoctor(appointment.getDoctorId());
        System.out.print("Enter Date (YYYY-MM-DD) of the new Appointment Date: ");
        String dateStr = scanner.nextLine();
        System.out.print("Enter Start Time (HH:MM) of the slot of the new appointment: ");
        String startTimeStr = scanner.nextLine();

        // Check if the new slot is available
        boolean isAvailable = isDoctorAvailable(appointment.getDoctorId(), LocalDate.parse(dateStr), LocalTime.parse(startTimeStr));
        if (!isAvailable) {
            System.out.println("The new time slot is not available.");
            return Collections.emptyList(); // Return an empty list if the slot is not available
        }
        cancelAppointment(appointmentId);
        scheduleAppointment(appointment.getPatientId(), appointment.getDoctorId(), dateStr, startTimeStr);

        // Update appointment details
        appointment.setDate(dateStr);
        appointment.setTime(startTimeStr);
        appointment.setStatus(AppointmentStatus.PENDING); // Reset status to pending for doctor's approval

        // Update appointment in repository
        appointmentRepository.updateAppointment(appointment);
        System.out.println("Appointment rescheduled successfully.");
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
        LocalTime t1 = LocalTime.of(1, 0);
        appointmentRepository.removeItem(appointment.getAppointmentId());
        doctorScheduleService.updateDoctorScheduleAfterBooking(appointment.getDoctorId(), LocalDate.parse(appointment.getDate()), LocalTime.parse(appointment.getTime()), LocalTime.parse(appointment.getTime()).plusHours(t1.getHour()));
        System.out.println("Appointment cancelled successfully.");
        return true;
    }

    /**
     * Retrieves a list of available appointment slots for a doctor.
     *
     * @param doctorId The doctor's ID.
     * @return A list of available appointment slots as strings.
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

    /**
     * Retrieves a list of all appointments.
     *
     * @return a list of Appointment objects containing all appointments.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAllData();
    }

    /**
     * Retrieves a list of appointments based on the given appointment ID.
     *
     * @param appointmentId the ID of the appointment to retrieve
     * @return a list of appointments that match the given appointment ID
     */
    public List<Appointment> getAppointmentById(String appointmentId) {
        return appointmentRepository.getDataById(appointmentId);
    }

    /**
     * Updates the status of an appointment identified by the given appointment ID.
     *
     * @param appointmentId The ID of the appointment to be updated.
     * @param status The new status to be set for the appointment.
     * @return true if the appointment status was successfully updated, false if the appointment was not found.
     */
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
