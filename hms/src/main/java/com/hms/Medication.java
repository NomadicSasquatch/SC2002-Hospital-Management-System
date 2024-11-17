package com.hms;

import java.util.Arrays;
import java.util.List;

import com.enumclass.PrescriptionStatus;
import com.hms.impl.IDataRepository;

public class Medication implements IDataRepository {
    private String medicationId;
    private String name;
    //these should be static, stockLevel and lowStockAlert level
    private int stockLevel;
    private int lowStockAlertLevel;
    private PrescriptionStatus status = PrescriptionStatus.PENDING;

    public Medication(String medicationId, String name, int stockLevel, int lowStockAlertLevel) {
        this.medicationId = medicationId;
        this.name = name;
        this.stockLevel = stockLevel;
        this.lowStockAlertLevel = lowStockAlertLevel;
    }

    @Override
    public String getDataID() {
        return medicationId;
    }

    @Override
    public List<String> getAttributes() {
        return Arrays.asList(
                medicationId,
                name,
                String.valueOf(stockLevel),
                String.valueOf(lowStockAlertLevel),
                status.toString()
        );
    }

    @Override
    public String getDataName() {
        return "medication";
    }

    public String getMedicationId() {
        return medicationId;
    }

    public String getName() {
        return name;
    }

    public int getStockLevel() {
        return stockLevel;
    }

    public int getLowStockAlertLevel() {
        return lowStockAlertLevel;
    }

    public PrescriptionStatus getStatus() {
        return this.status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }
}
