package models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The DoctorSchedule class represents a doctor's available time slots.
 */
public class DoctorSchedule {
    private String doctorId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    // Constructors

    public DoctorSchedule() {
    }

    public DoctorSchedule(String doctorId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.doctorId = doctorId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    
    /** 
     * @return String
     */
    // Getters and Setters

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    // CSV conversion methods

    public String[] toCSV() {
        return new String[]{
                doctorId,
                date.toString(),
                startTime.toString(),
                endTime.toString()
        };
    }

    public static DoctorSchedule fromCSV(String[] record) {
        return new DoctorSchedule(
                record[0],
                LocalDate.parse(record[1]),
                LocalTime.parse(record[2]),
                LocalTime.parse(record[3])
        );
    }
}
