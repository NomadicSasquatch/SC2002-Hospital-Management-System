package com.enumclass;

/**
 * The {@code AppointmentStatus} enum represents the various statuses that an appointment can have.
 * <ul>
 * <li>{@link #CONFIRMED} - The appointment has been confirmed.                         </li>
 * <li>{@link #SCHEDULED} - The appointment has been scheduled but not yet confirmed.   </li>
 * <li>{@link #CANCELLED} - The appointment has been cancelled.                         </li>
 * <li>{@link #COMPLETED} - The appointment has been completed.                         </li>
 * </ul>
 */
public enum AppointmentStatus {
    CONFIRMED, SCHEDULED, CANCELLED, COMPLETED;
}
