package com.yorku.wbapp.controller.analysis.calc;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//Class that defines the calculation of annual percentage change
// TODO: Calculate annual percentage change correctly
public class AnnualPercentChange extends Calculation{

    public AnnualPercentChange(Map<String, WBData> wbDataMap) {

        super(wbDataMap);
    }

    //Calculate AnnualPercentageChange and return the list of WBDataRecord
    @Override
    public List<WBDataRecord> performCalculation() {
        List<WBDataRecord> analyzedList = new ArrayList<>();
        //
        WBData wbData = null;
        String indicatorKey = null;
        for(String key: wbDataMap.keySet()){
            wbData = wbDataMap.get(key);
            indicatorKey = key;
        }

        List<WBDataRecord> sortedList = wbData.getRecords();
        Collections.sort(sortedList);

        for(WBDataRecord record: sortedList){
            System.out.println("Original record: "+sortedList.toString());
            WBDataRecord previousWBDataRecord = getPreviousYearRecord(record.getYear());
            double annualPercentageChange = getAnnualPercentageChange(record.getAnalysisValue(), previousWBDataRecord.getAnalysisValue());

            WBDataRecord analyzedWBDataRecord = new WBDataRecord(record.getCountryId(), indicatorKey, record.getYear(), annualPercentageChange);
            analyzedList.add(analyzedWBDataRecord);

        }

        return analyzedList;
    }


    public double calculate() {
        return 0;
    }

}
