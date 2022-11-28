package com.yorku.wbapp.model;

import java.util.ArrayList;
import java.util.List;
//WBData is a class that wraps WBDataRecord
public class WBData {
    List<WBDataRecord> records = new ArrayList<>();
    double calculatedValue = -1.0;

    public WBData() {
    }

    public double getCalculatedValue() {
        return calculatedValue;
    }

    public void setCalculatedValue(double calculatedValue) {
        this.calculatedValue = calculatedValue;
    }

    public WBData(List<WBDataRecord> records) {
        this.records = records;
    }

    public List<WBDataRecord> getRecords() {
        return records;
    }

    public void setRecords(List<WBDataRecord> records) {
        this.records = records;
    }
}
