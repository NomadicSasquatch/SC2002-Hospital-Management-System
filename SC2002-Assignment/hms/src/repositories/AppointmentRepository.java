package repositories;

import models.Appointment;
import models.DoctorSchedule;
import models.ReplenishmentRequest;
import utils.CSVReader;
import utils.CSVWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import abstract_class.CrudRepository;

/**
 * AppointmentRepository handles CRUD operations for Appointment entities.
 */
public class AppointmentRepository extends CrudRepository<Appointment, String> {

    private static final String APPOINTMENT_CSV_FILE = "hms/src/data/appointments.csv";

    public AppointmentRepository() {
        super(APPOINTMENT_CSV_FILE);
    }
    /**
     * Updates an existing appointment.
     *
     * @param appointment The appointment with updated information.
     */
    public void updateAppointment(Appointment appointment) {
        List<Appointment> existing = getDataById(appointment.getAppointmentId());
        if (!existing.isEmpty()) {
            Appointment existingAppointment = existing.get(0); // Assuming you handle the first match
            removeItem(existingAppointment.getAppointmentId());
            addItem(appointment);
            saveRepository();
        }
    }

    /**
     * Retrieves appointments for a specific doctor on a specific date.
     *
     * @param doctorId The doctor's ID.
     * @param date     The date in YYYY-MM-DD format.
     * @return List of appointments.
     */
    public List<Appointment> getAppointmentsByDoctorAndDate(String doctorId, String date) {
        return items.stream()
                .filter(a -> a.getDoctorId().equals(doctorId) && a.getDate().equals(date))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves appointments for a specific patient.
     *
     * @param patientId The patient's ID.
     * @return List of appointments.
     */
    public List<Appointment> getAppointmentsByPatientId(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : items) {
            if (a.getPatientId().equals(patientId)) {
                result.add(a);
            }
        }
        return result;
    }

    /**
     * Retrieves appointments for a specific doctor.
     *
     * @param doctorId The doctor's ID.
     * @return List of appointments.
     */
    public List<Appointment> getAppointmentsByDoctorId(String doctorId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : items) {
            if (a.getDoctorId().equals(doctorId)) {
                result.add(a);
            }
        }
        return result;
    }

    @Override
    protected Appointment fromCSV(String[] record) {
        return Appointment.fromCSV(record);
    }

    @Override
    protected String[] toCSV(Appointment item) {
        return item.toCSV();
    }
    @Override
    protected String getId(Appointment item) {
        // Construct a unique ID for DoctorSchedule
        // Assuming combination of doctorId, date, and startTime is unique
        return item.getAppointmentId();
    }
}
