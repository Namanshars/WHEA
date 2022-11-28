package com.yorku.wbapp.analysis.calc;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//Class that defines the average calculation
public class Average extends Calculation{


    public Average(Map<String, WBData> wbDataMap) {

        super(wbDataMap);
    }

    @Override
    public List<WBDataRecord> performCalculation() {

        WBData wbData = null;
        String indicatorKey = null;
        for(String key: wbDataMap.keySet()){
            wbData = wbDataMap.get(key);
            indicatorKey = key;
        }

        List<WBDataRecord> analyzedList = new ArrayList<>();

        double average = getAverage(wbData);
        List<WBDataRecord> records = wbData.getRecords();
        WBDataRecord fullAverageAnalysis = new WBDataRecord(records.get(0).getCountryId(), indicatorKey, records.get(0).getYear(),average);

        analyzedList.add(fullAverageAnalysis);
        return analyzedList;
    }

}
