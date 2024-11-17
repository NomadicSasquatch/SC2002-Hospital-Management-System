package com.hms;

import java.util.Arrays;
import java.util.List;

import com.hms.impl.IDataRepository;

public class ReplenishmentRequest implements IDataRepository {
    private String requestId;
    private String medicationId;
    private int quantity;

    public ReplenishmentRequest(String requestId, String medicationId, int quantity) {
        this.requestId = requestId;
        this.medicationId = medicationId;
        this.quantity = quantity;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getMedicationId() {
        return medicationId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String getDataID() {
        return requestId;
    }

    @Override
    public List<String> getAttributes() {
        return Arrays.asList(
                requestId,
                medicationId,
                String.valueOf(quantity)
        );
    }

    @Override
    public String getDataName() {
        return "replenishmentrequest";
    }
}
