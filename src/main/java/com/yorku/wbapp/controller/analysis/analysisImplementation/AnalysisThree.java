package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.controller.analysis.CalculationManager;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.HashMap;
import java.util.Map;

public class AnalysisThree extends AnalysisStrategy {
    @Override
    public Map<String, WBData> analyse(FilterCriteria filterCriteria) {

        //get the data based on FilterCriteria - country and year range
        FilterCriteria fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(), AnalysisConstants.CO2_ANALYSIS);
        WBData dataOne = getFilterWBData(fc);

        fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(),  AnalysisConstants.GDP_CAPITA);
        WBData dataTwo = getFilterWBData(fc);

        Map<String, WBData> allDataMap = new HashMap<>();
        allDataMap.put(AnalysisConstants.CO2_ANALYSIS, dataOne);
        allDataMap.put(AnalysisConstants.GDP_CAPITA, dataTwo);

        //Analyze data and create new WBData to hold it
        CalculationManager calculationManager = new CalculationManager();
        WBData analyzedData = calculationManager.doCalculation(allDataMap, AnalysisConstants.RATIO);
        printAnalyzedData("analyzedDataOne ", analyzedData);

        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedDataMap = new HashMap<>();
        analyzedDataMap.put(AnalysisConstants.CO2_ANALYSIS, analyzedData);
        analyzedDataMap.put(AnalysisConstants.GDP_CAPITA, analyzedData);

        return analyzedDataMap;
    }

    public AnalysisThree(String analysisName) {
        super(analysisName);
    }



}
