package repositories;

import models.DoctorSchedule;
import utils.CSVReader;
import utils.CSVWriter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import abstract_class.CrudRepository;

/**
 * DoctorScheduleRepository handles CRUD operations for DoctorSchedule entities.
 */
public class DoctorScheduleRepository extends CrudRepository<DoctorSchedule, String> {
    private static final String SCHEDULE_CSV_FILE = "src/data/doctor_schedules.csv";

    public DoctorScheduleRepository() {
        super(SCHEDULE_CSV_FILE);
    }

    @Override
    protected DoctorSchedule fromCSV(String[] record) {
        return DoctorSchedule.fromCSV(record);
    }

    @Override
    protected String[] toCSV(DoctorSchedule item) {
        return item.toCSV();
    }
    @Override
    protected String getId(DoctorSchedule item) {
        // Construct a unique ID for DoctorSchedule
        // Assuming combination of doctorId, date, and startTime is unique
        return item.getDoctorId();
    }
}
