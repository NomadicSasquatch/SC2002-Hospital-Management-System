package repositories;

import models.ReplenishmentRequest;
import utils.CSVReader;
import utils.CSVWriter;

import java.util.ArrayList;
import java.util.List;

import abstract_class.CrudRepository;

/**
 * ReplenishmentRequestRepository handles CRUD operations for ReplenishmentRequest entities.
 */
public class ReplenishmentRequestRepository extends CrudRepository<ReplenishmentRequest, String> {

    private static final String REQUESTS_CSV_FILE = "src/data/replenishment_requests.csv";

    public ReplenishmentRequestRepository() {
        super(REQUESTS_CSV_FILE);
    }
    /**
     * Updates an existing replenishment request.
     *
     * @param request The replenishment request with updated information.
     */
    public void updateReplenishmentRequest(ReplenishmentRequest request) {
        List<ReplenishmentRequest> existingRequests = getDataById(request.getRequestId());
        
        if (!existingRequests.isEmpty()) {
            ReplenishmentRequest existingRequest = existingRequests.get(0); // Assuming you handle the first match
            removeItem(existingRequest.getRequestId());
            addItem(request);
            saveRepository();
        }
    }
    


    @Override
    protected ReplenishmentRequest fromCSV(String[] record) {
        return ReplenishmentRequest.fromCSV(record);
    }
    @Override
    protected String[] toCSV(ReplenishmentRequest item) {
        return item.toCSV();
    }
    @Override
    protected String getId(ReplenishmentRequest item) {
        return item.getRequestId();
    }
}
