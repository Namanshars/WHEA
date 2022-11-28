package com.yorku.wbapp.analysis.ConcreteClasses;

import com.yorku.wbapp.analysis.AnalysisConstants;
import com.yorku.wbapp.analysis.AnalysisStrategy;
import com.yorku.wbapp.analysis.BaseAnalysis;
import com.yorku.wbapp.model.WBData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisTwo extends AnalysisStrategy {

    public AnalysisTwo(String analysisName) {
        super(analysisName);
    }

    @Override
    public Map<String, WBData> analyse(Map<String, WBData> wb) {
        BaseAnalysis baseAnalysis = new BaseAnalysis();
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData> forestAreaData = new HashMap<>();
        forestAreaData.put(AnalysisConstants.FOREST_AREA, wb.get(AnalysisConstants.FOREST_AREA));
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData>  airPollutionData = new HashMap<>();
        airPollutionData.put(AnalysisConstants.PM25_AIR_POLLUTION, wb.get(AnalysisConstants.PM25_AIR_POLLUTION));

        //Analyze data and create new WBData to hold it
        WBData analyzedPM25Data = baseAnalysis.doAnalysis(airPollutionData, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("CO2", analyzedPM25Data);
        //Analyze data and create new WBData to hold it
        WBData analyzedForestAreaData = baseAnalysis.doAnalysis(forestAreaData, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("Energy use RATIO ", analyzedForestAreaData);
        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.PM25_AIR_POLLUTION, analyzedPM25Data);
        analyzedData.put(AnalysisConstants.FOREST_AREA, analyzedForestAreaData);

        return analyzedData;
    }




}
