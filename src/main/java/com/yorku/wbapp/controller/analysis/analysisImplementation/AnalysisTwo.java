package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.controller.analysis.CalculationManager;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.HashMap;
import java.util.Map;

public class AnalysisTwo extends AnalysisStrategy {

    @Override
    public Map<String, WBData> analyse(FilterCriteria filterCriteria) {
        //get the data based on FilterCriteria - country and year range
        FilterCriteria fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(), AnalysisConstants.FOREST_AREA);
        WBData dataOne = getFilterWBData(fc);

        fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(),  AnalysisConstants.PM25_AIR_POLLUTION);
        WBData dataTwo = getFilterWBData(fc);

        Map<String, WBData> wb = new HashMap<>();
        wb.put(AnalysisConstants.FOREST_AREA, dataOne);
        wb.put(AnalysisConstants.PM25_AIR_POLLUTION, dataTwo);

        CalculationManager calculationManager = new CalculationManager();
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData> forestAreaData = new HashMap<>();
        forestAreaData.put(AnalysisConstants.FOREST_AREA, wb.get(AnalysisConstants.FOREST_AREA));
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData>  airPollutionData = new HashMap<>();
        airPollutionData.put(AnalysisConstants.PM25_AIR_POLLUTION, wb.get(AnalysisConstants.PM25_AIR_POLLUTION));

        //Analyze data and create new WBData to hold it
        WBData analyzedPM25Data = calculationManager.doCalculation(airPollutionData, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("CO2", analyzedPM25Data);
        //Analyze data and create new WBData to hold it
        WBData analyzedForestAreaData = calculationManager.doCalculation(forestAreaData, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("Energy use RATIO ", analyzedForestAreaData);
        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.PM25_AIR_POLLUTION, analyzedPM25Data);
        analyzedData.put(AnalysisConstants.FOREST_AREA, analyzedForestAreaData);

        return analyzedData;
    }

    public AnalysisTwo(String analysisName) {
        super(analysisName);
    }


}
