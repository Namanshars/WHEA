package com.yorku.wbapp.controller.analysis.calc;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.util.List;
import java.util.Map;

//The purpose of this class is to employ different types of calculations on an analysis
//Calculations compose of Annual Percentage Change, Ratio, Average
public abstract class Calculation {
    Map<String, WBData> wbDataMap;
    //Many analysis require multiple indicators, and therefore we would need a map to hold the data for each indicator we call
    public Calculation(Map<String, WBData> wbDataMap) {
        this.wbDataMap = wbDataMap;
    }


    //This method is for calculations that return a list of data records
    public abstract List<WBDataRecord> performCalculation();

    //This is a method to get the record of the year previous to the year we pass in
    //This is mainly used for Annual Percentage Change as we would need data for the year prior to the starting year the user enters
    public WBDataRecord getPreviousYearRecord(int year){
        WBData wbData = null;
        for(WBData data: wbDataMap.values()){
            wbData = data;
        }
        int previousIndex = 0;
        int counter = 0;
        for(WBDataRecord record: wbData.getRecords()){
            if(record.getYear() == year){

                if(counter > 0) {
                    //System.out.println("In second if");
                    previousIndex = counter - 1;
                }else{
                    //System.out.println("In first if");
                }
            }
            counter++;
            //System.out.println("previous index: " + previousIndex);
            //System.out.println("counter: " + counter);
        }

        WBDataRecord previousRecord = wbData.getRecords().get(previousIndex);
        //System.out.println(year+" previous: "+previousRecord.getYear());
        return previousRecord;
    }

    //Calculate Annual Percentage Change
    public double getAnnualPercentageChange(double currentYearValue, double prevYearValue){
        double percentChange = ((currentYearValue - prevYearValue) / prevYearValue) * 100;
        return percentChange;
    }

    //Calculate Ratio
    public double getRatio(double valueOne, double valueTwo){
        double ratio = (valueOne / valueTwo);
        return ratio;
    }

    //Calculate Average
    public double getAverage(WBData wbData){
        double average = 0;
        double sum = 0;
        double numElements = wbData.getRecords().size();

         for(WBDataRecord record: wbData.getRecords()) {
             sum += record.getAnalysisValue();
         }
         average = sum / numElements;
        return average;
    }


}
