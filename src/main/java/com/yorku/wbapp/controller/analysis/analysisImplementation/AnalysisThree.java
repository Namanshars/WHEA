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

        Map<String, WBData> wb = new HashMap<>();
        wb.put(AnalysisConstants.CO2_ANALYSIS, dataOne);
        wb.put(AnalysisConstants.GDP_CAPITA, dataTwo);

        CalculationManager calculationManager = new CalculationManager();
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData> forestAreaData = new HashMap<>();
        forestAreaData.put(AnalysisConstants.CO2_ANALYSIS, wb.get(AnalysisConstants.CO2_ANALYSIS));
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData>  airPollutionData = new HashMap<>();
        airPollutionData.put(AnalysisConstants.GDP_CAPITA, wb.get(AnalysisConstants.GDP_CAPITA));

        //Analyze data and create new WBData to hold it
        WBData analyzedPM25Data = calculationManager.doCalculation(airPollutionData, AnalysisConstants.RATIO);
        printAnalyzedData("CO2", analyzedPM25Data);
        //Analyze data and create new WBData to hold it
        WBData analyzedForestAreaData = calculationManager.doCalculation(forestAreaData, AnalysisConstants.RATIO);
        printAnalyzedData("Energy use RATIO ", analyzedForestAreaData);
        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.CO2_ANALYSIS, analyzedPM25Data);
        analyzedData.put(AnalysisConstants.GDP_CAPITA, analyzedForestAreaData);

        return analyzedData;
    }

    public AnalysisThree(String analysisName) {
        super(analysisName);
    }



}
