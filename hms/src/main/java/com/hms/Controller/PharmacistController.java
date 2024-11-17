package com.hms.Controller;

import com.hms.Patient;
import com.hms.Manager.AppointmentOutcomeRecordManager;
import com.hms.Manager.MedicationManager;
import com.utils.CSVFile;

public class PharmacistController {
    //somehow change the status in the csv
    void dispenseMedication(Patient patient) {

    }
    //need to somehow specify its the patient in this manager
    CSVFile viewAppointmentOutcomeRecord(AppointmentOutcomeRecordManager manager) {
        return manager.view();
    }
    CSVFile viewMedicationInventory(MedicationManager manager) {
        return manager.view();
    }
}
