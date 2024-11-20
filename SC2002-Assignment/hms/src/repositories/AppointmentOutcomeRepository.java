package repositories;

import abstract_class.CrudRepository;
import models.AppointmentOutcomeRecord;

import java.util.List;
import java.util.stream.Collectors;

public class AppointmentOutcomeRepository extends CrudRepository<AppointmentOutcomeRecord, String> {

    private static final String OUTCOME_CSV_FILE = "SC2002-Assignment/hms/src/data/appointment_outcomes.csv";

    public AppointmentOutcomeRepository() {
        super(OUTCOME_CSV_FILE);
    }

    @Override
    protected AppointmentOutcomeRecord fromCSV(String[] record) {
        return AppointmentOutcomeRecord.fromCSV(record);
    }

    @Override
    protected String[] toCSV(AppointmentOutcomeRecord item) {
        return item.toCSV();
    }

    @Override
    protected String getId(AppointmentOutcomeRecord item) {
        return item.getAppointmentOutcomeId();
    }

    public List<AppointmentOutcomeRecord> getOutcomesByAppointmentId(String appointmentId) {
        return items.stream()
                .filter(o -> o.getAppointmentId().equals(appointmentId))
                .collect(Collectors.toList());
    }

    public List<AppointmentOutcomeRecord> getOutcomesByPatientId(String patientId) {
        return items.stream()
                .filter(o -> o.getPatientId().equals(patientId))
                .collect(Collectors.toList());
    }

    public List<AppointmentOutcomeRecord> getOutcomesByDoctorId(String doctorId) {
        return items.stream()
                .filter(o -> o.getDoctorId().equals(doctorId))
                .collect(Collectors.toList());
    }
}
