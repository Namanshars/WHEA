package com.yorku.wbapp.analysis.ConcreteClasses;

import com.yorku.wbapp.analysis.AnalysisStrategy;
import com.yorku.wbapp.analysis.AnalysisConstants;
import com.yorku.wbapp.analysis.calc.AnnualPercentChange;
import com.yorku.wbapp.analysis.calc.Average;
import com.yorku.wbapp.analysis.calc.Calculation;
import com.yorku.wbapp.analysis.calc.Ratio;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.util.*;
import java.util.List;

//CO2 emission = "EN.ATM.CO2E.PC"
public class CO2Analysis{

/*

    public static void main(String[] args) {
        WBData allData = new WBData();
        List<WBDataRecord> records = new ArrayList<>();
        WBDataRecord wb1 = new WBDataRecord("IN", AnalysisConstants.CO2_ANALYSIS, 2017, 0.3);
        records.add(wb1);
        WBDataRecord wb2 = new WBDataRecord("IN", AnalysisConstants.CO2_ANALYSIS, 2018, 0.3);
        records.add(wb2);
        WBDataRecord wb3 = new WBDataRecord("IN", AnalysisConstants.CO2_ANALYSIS, 2016, 0.3);
        records.add(wb3);
        WBDataRecord wb4 = new WBDataRecord("IN", AnalysisConstants.CO2_ANALYSIS, 2019, 0.3);
        records.add(wb4);

        allData.setRecords(records);

        Collections.sort(records);
        System.out.println("after sorting:");
        List<WBDataRecord> sortedList = allData.getRecords();
        for(int i = 0; i < sortedList.size();i++){
            System.out.println(sortedList.get(i));
        }

    }


    //Perform the necessary calculation
    public WBData analyse(WBData wb, String calculationName) {
        List<WBDataRecord> analyzedList = new ArrayList<>();

        if (calculationName.equals(AnalysisConstants.ANNUAL_PERCENT_CHANGE)){
            System.out.println("Original record: "+wb.toString());
            Calculation calculation = new AnnualPercentChange(wb);
            analyzedList = calculation.performCalculation();
        }
        else if (calculationName.equals(AnalysisConstants.RATIO)){
            System.out.println("Original record: "+wb.toString());
            Calculation calculation = new Ratio(wb);
            analyzedList = calculation.performCalculation();
        }
        else if (calculationName.equals(AnalysisConstants.AVERAGE)){
            Calculation calculation = new Average(wb);
            analyzedList = calculation.performCalculation();
        }

        WBData analyzedWBData = new WBData(analyzedList);

        return analyzedWBData;
    }

*/


    /*

        String percentChange = new String("AnnualPercentChange");
        String ratio = new String("Ratio");
        String average = new String("Average");
        if (calculationName.equals(percentChange)){
            for(WBDataRecord record: wb.getRecords()){
                System.out.println("Original record: "+record.toString());
                WBDataRecord previousWBDataRecord = getPreviousYearRecord(wb, record.getYear());
                double annualPercentageChange = getAnnualPercentageChange(record.getAnalysisValue(), previousWBDataRecord.getAnalysisValue());

                WBDataRecord analyzedWBDataRecord = new WBDataRecord(record.getCountryId(), record.getIndicator(), record.getYear(), annualPercentageChange);
                analyzedList.add(analyzedWBDataRecord);

            }
        }
        else if (calculationName.equals(ratio)){
            for(WBDataRecord record: wb.getRecords()){
                System.out.println("Original record: "+record.toString());
                WBDataRecord previousWBDataRecord = getPreviousYearRecord(wb, record.getYear());
                double annualPercentageChange = getRatio(record.getAnalysisValue(), previousWBDataRecord.getAnalysisValue());

                WBDataRecord analyzedWBDataRecord = new WBDataRecord(record.getCountryId(), record.getIndicator(), record.getYear(), annualPercentageChange);
                analyzedList.add(analyzedWBDataRecord);

            }
        }
        else if (calculationName.equals("Average")){
            double averageData = getAverage(wb);
            //WBDataRecord averageRecord = new WBDataRecord()
            return wb;
        }

     */
}
