package models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The DoctorSchedule class represents a doctor's available time slots.
 * It stores information about the doctor’s ID, the date, start time, and end time for a particular schedule.
 */
public class DoctorSchedule {
    private String doctorId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    // Constructors

    /**
     * Default constructor for the DoctorSchedule class.
     */
    public DoctorSchedule() {
    }

    /**
     * Constructs a DoctorSchedule with the specified details.
     *
     * @param doctorId the unique ID of the doctor
     * @param date the date of the schedule
     * @param startTime the start time of the schedule
     * @param endTime the end time of the schedule
     */
    public DoctorSchedule(String doctorId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.doctorId = doctorId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters

    /**
     * Retrieves the doctor ID.
     *
     * @return the doctor ID as a String
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Sets the doctor ID.
     *
     * @param doctorId the doctor ID as a String
     */
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Retrieves the date of the schedule.
     *
     * @return the schedule date as LocalDate
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the schedule.
     *
     * @param date the schedule date as LocalDate
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Retrieves the start time of the schedule.
     *
     * @return the start time as LocalTime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of the schedule.
     *
     * @param startTime the start time as LocalTime
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the end time of the schedule.
     *
     * @return the end time as LocalTime
     */
    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the schedule.
     *
     * @param endTime the end time as LocalTime
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // CSV conversion methods

    /**
     * Converts this DoctorSchedule object to a CSV record.
     *
     * @return an array of strings representing the CSV record
     */
    public String[] toCSV() {
        return new String[]{
                doctorId,
                date.toString(),
                startTime.toString(),
                endTime.toString()
        };
    }

    /**
     * Creates a DoctorSchedule object from a CSV record.
     *
     * @param record an array of strings representing the CSV record
     * @return a new DoctorSchedule object
     */
    public static DoctorSchedule fromCSV(String[] record) {
        return new DoctorSchedule(
                record[0],
                LocalDate.parse(record[1]),
                LocalTime.parse(record[2]),
                LocalTime.parse(record[3])
        );
    }
}
