package com.enumclass;

/**
 * The {@code PrescriptionStatus} enum represents status of a prescription.
 * <ul>
 * <li>{@link #PENDING}     - The prescription is pending and has not yet been prescribed.  </li>
 * <li>{@link #DISPENSED}  - The prescription has been prescribed.                         </li>
 * <li>{@link #CANCELLED}   - The prescription has been cancelled.                          </li>
 * </ul>
 */
public enum PrescriptionStatus {
    PENDING, DISPENSED, CANCELLED;
}
