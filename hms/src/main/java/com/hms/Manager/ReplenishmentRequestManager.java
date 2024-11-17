package com.hms.Manager;

import java.util.Arrays;

import com.hms.ReplenishmentRequest;
import com.utils.CSVFile;

public class ReplenishmentRequestManager extends DataManager {
    private static final String[] REPLENISHMENT_REQUEST_HEADER = { "requestID", "medicationID", "quantity"};
    private CSVFile replenishmentRequestCSV;

    public ReplenishmentRequestManager() {
        String filePath = DATA_ROOT + "replenishmentRequests.csv";
        this.replenishmentRequestCSV = FileManager.loadFile(filePath, Arrays.asList(REPLENISHMENT_REQUEST_HEADER));
    }

    public void submitReplenishmentRequest(ReplenishmentRequest request) {
        add(replenishmentRequestCSV, request);
    }

    public void removeReplenishmentRequest(ReplenishmentRequest request) {
        remove(replenishmentRequestCSV, request);
    }

    @Override
    public CSVFile view() {
        return replenishmentRequestCSV;
    }
}
