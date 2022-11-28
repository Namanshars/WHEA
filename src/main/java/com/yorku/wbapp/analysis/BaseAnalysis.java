package com.yorku.wbapp.analysis;

import com.yorku.wbapp.analysis.calc.AnnualPercentChange;
import com.yorku.wbapp.analysis.calc.Average;
import com.yorku.wbapp.analysis.calc.Calculation;
import com.yorku.wbapp.analysis.calc.Ratio;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseAnalysis {

    public WBData doAnalysis(Map<String, WBData> wbDataMap, String calculationName) {
        List<WBDataRecord> analyzedList = new ArrayList<>();
        //Use if statements to perform the correct calculation based off String calculationName
        if (calculationName.equals(AnalysisConstants.ANNUAL_PERCENT_CHANGE)){
            //System.out.println("Original record: "+wb.toString());
            Calculation calculation = new AnnualPercentChange(wbDataMap);
            analyzedList = calculation.performCalculation();
        }
        else if (calculationName.equals(AnalysisConstants.RATIO)){
            //System.out.println("Original record: "+wb.toString());
            Calculation calculation = new Ratio(wbDataMap);
            analyzedList = calculation.performCalculation();
        }
        else if (calculationName.equals(AnalysisConstants.AVERAGE)){
            Calculation calculation = new Average(wbDataMap);
            analyzedList = calculation.performCalculation();
        }
        //Pass and return the analyzed data into a new WBData object
        WBData analyzedWBData = new WBData(analyzedList);

        return analyzedWBData;
    }
}
