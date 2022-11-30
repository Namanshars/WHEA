package com.yorku.wbapp.controller.analysis.calc;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class NoCalc extends Calculation{
    public NoCalc(Map<String, WBData> wbDataMap) {
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

        List<WBDataRecord> sortedList = wbData.getRecords();
        Collections.sort(sortedList);

        return sortedList;
    }
}
