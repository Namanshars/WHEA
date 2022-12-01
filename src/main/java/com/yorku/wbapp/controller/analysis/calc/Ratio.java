package com.yorku.wbapp.controller.analysis.calc;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//Class defines the ratio calculation
public class Ratio extends Calculation{


    public Ratio(Map<String, WBData> wbDataMap) {
        super(wbDataMap);
    }

    //Calculate ratio and return the list of WBDataRecord
    @Override
    public List<WBDataRecord> performCalculation() {

        List<WBDataRecord> analyzedList = new ArrayList<>();

        //double yearRatio = 0.0;
        WBData wbDataOne = null;
        WBData wbDataTwo = null;
        String indicatorOneKey = null;
        String indicatorTwoKey = null;
        int counter = 0;
        for(String key: wbDataMap.keySet()){
            if(counter ==0){
                wbDataOne = wbDataMap.get(key);
                indicatorOneKey = key;
            }else if(counter == 1){
                wbDataTwo = wbDataMap.get(key);
                indicatorTwoKey = key;
            }
            counter++;
        }

        List<WBDataRecord> recordListOne = wbDataOne.getRecords();
        List<WBDataRecord> recordListTwo = wbDataTwo.getRecords();
        Collections.sort(recordListOne);
        Collections.sort(recordListTwo);
        int startYear = recordListOne.get(0).getYear();
        int endYear = recordListOne.get(recordListOne.size()-1).getYear();
        int maxYears = endYear - startYear;

        for (int i = 0; i <= maxYears; i++){

            //Only perform calculation for matching years. If years do not match then skip over them
            if (recordListOne.get(i).getYear() == recordListTwo.get(i).getYear()){
                //yearRatio = recordListOne.get(i).getAnalysisValue() / recordListTwo.get(i).getAnalysisValue();
                double yearRatio = getRatio(recordListOne.get(i).getAnalysisValue(), recordListTwo.get(i).getAnalysisValue());
                System.out.println("ratio for year " + recordListOne.get(i).getYear() + "= " + yearRatio);
                WBDataRecord wbDataRecord = new WBDataRecord(recordListOne.get(i).getCountryId(), indicatorOneKey, recordListOne.get(i).getYear(), yearRatio);
                analyzedList.add(wbDataRecord);
                //Here what needs to be done when calling Ratio, the performCalculation() method would have called getRatio(double valueOne, double valueTwo)
                //The parameters in that method are the two values we are using in the division sequence for yearRatio
            }
            if (recordListOne.get(i).getYear() == endYear){
                i = maxYears + 1;
            }
        }

        /*
        List<WBDataRecord> sortedList = wbData.getRecords();
        Collections.sort(sortedList);

        for(WBDataRecord record: sortedList){
            System.out.println("Original record: "+record.toString());
            WBDataRecord previousWBDataRecord = getPreviousYearRecord(record.getYear());
            double annualPercentageChange = getAnnualPercentageChange(record.getAnalysisValue(), previousWBDataRecord.getAnalysisValue());

            WBDataRecord analyzedWBDataRecord = new WBDataRecord(record.getCountryId(), record.getIndicator(), record.getYear(), annualPercentageChange);
            analyzedList.add(analyzedWBDataRecord);
        }
         */

        return analyzedList;
    }


    public double calculate() {
        return 0;
    }
}
