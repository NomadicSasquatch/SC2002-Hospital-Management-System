package services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import models.DoctorSchedule;
import repositories.DoctorScheduleRepository;

/**
 * DoctorScheduleService handles business logic related to doctor schedules.
 */
public class DoctorScheduleService {

    private DoctorScheduleRepository doctorScheduleRepository;

    /**
     * Service class for managing doctor schedules.
     *
     * @param doctorScheduleRepository the repository used for accessing and
     * managing doctor schedules
     */
    public DoctorScheduleService(DoctorScheduleRepository doctorScheduleRepository) {
        this.doctorScheduleRepository = doctorScheduleRepository;
    }

    /**
     * @param doctorId
     * @param date
     * @param startTime
     * @param endTime
     * @return boolean
     */
    public boolean addAvailableSlot(String doctorId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        // Check for overlapping slots
        List<DoctorSchedule> existingSlots = doctorScheduleRepository.getDataById(doctorId);
        for (DoctorSchedule slot : existingSlots) {
            if (slot.getDate().equals(date)) {
                if (startTime.isBefore(slot.getEndTime()) && endTime.isAfter(slot.getStartTime())) {
                    System.out.println("Slot overlaps with an existing slot.");
                    return false;
                }
            }
        }

        DoctorSchedule newSlot = new DoctorSchedule(doctorId, date, startTime, endTime);
        doctorScheduleRepository.addItem(newSlot);
        return true;
    }

    public boolean removeAvailableSlot(String doctorId, LocalDate date, LocalTime startTime) {
        List<DoctorSchedule> slots = doctorScheduleRepository.getDataById(doctorId);

        // Find the matching slot
        List<DoctorSchedule> matchingSlots = slots.stream()
                .filter(slot -> slot.getDate().equals(date) && slot.getStartTime().equals(startTime))
                .collect(Collectors.toList());

        if (!matchingSlots.isEmpty()) {
            // Remove the first matching slot
            DoctorSchedule slotToRemove = matchingSlots.get(0);
            doctorScheduleRepository.removeItem(slotToRemove.getDoctorId());
            return true;
        } else {
            System.out.println("Slot not found.");
            return false;
        }
    }

    /**
     * Retrieves the available slots for a specific doctor based on their ID.
     *
     * @param doctorId the unique identifier of the doctor
     * @return a list of DoctorSchedule objects representing the available slots
     * for the specified doctor
     */
    public List<DoctorSchedule> getAvailableSlotsForDoctor(String doctorId) {
        return doctorScheduleRepository.getDataById(doctorId);
    }

    /**
     * Retrieves a list of all available doctor schedule slots.
     *
     * @return a list of DoctorSchedule objects representing all available
     * slots.
     */
    public List<DoctorSchedule> getAllAvailableSlots() {
        return doctorScheduleRepository.getAllData();
    }

    /**
     * Updates the doctor's schedule after an appointment is booked.
     *
     * @param doctorId The doctor's ID.
     * @param date The date of the appointment.
     * @param appointmentStartTime The start time of the appointment.
     * @param appointmentEndTime The end time of the appointment.
     */
    public void updateDoctorScheduleAfterBooking(String doctorId, LocalDate date, LocalTime appointmentStartTime, LocalTime appointmentEndTime) {
        // Get the doctor's schedules for that date
        List<DoctorSchedule> schedules = doctorScheduleRepository.getDataById(doctorId);

        // Find the schedule that includes the appointment time
        for (DoctorSchedule schedule : schedules) {
            if (schedule.getDate().equals(date)) {
                if (!appointmentStartTime.isBefore(schedule.getStartTime()) && !appointmentEndTime.isAfter(schedule.getEndTime())) {
                    // The appointment is within this schedule
                    // Remove this schedule
                    doctorScheduleRepository.removeItem(schedule.getDoctorId());

                    // Create new schedules excluding the appointment time
                    if (appointmentStartTime.isAfter(schedule.getStartTime())) {
                        // Create a new schedule from schedule start to appointment start
                        DoctorSchedule newSchedule1 = new DoctorSchedule(
                                doctorId,
                                date,
                                schedule.getStartTime(),
                                appointmentStartTime
                        );
                        doctorScheduleRepository.addItem(newSchedule1);;
                    }
                    if (appointmentEndTime.isBefore(schedule.getEndTime())) {
                        // Create a new schedule from appointment end to schedule end
                        DoctorSchedule newSchedule2 = new DoctorSchedule(
                                doctorId,
                                date,
                                appointmentEndTime,
                                schedule.getEndTime()
                        );
                        doctorScheduleRepository.addItem(newSchedule2);;
                    }
                    break; // Exit the loop after updating the schedule
                }
            }
        }
    }
}
