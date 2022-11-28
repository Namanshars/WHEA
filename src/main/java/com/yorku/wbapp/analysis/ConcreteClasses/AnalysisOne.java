package com.yorku.wbapp.analysis.ConcreteClasses;

import com.yorku.wbapp.analysis.AnalysisConstants;
import com.yorku.wbapp.analysis.AnalysisStrategy;
import com.yorku.wbapp.analysis.BaseAnalysis;
import com.yorku.wbapp.model.WBData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisOne extends AnalysisStrategy {

    public AnalysisOne(String analysisName) {

        super(analysisName);
    }

    @Override
    public Map<String, WBData> analyse(Map<String, WBData> wb) {

        BaseAnalysis baseAnalysis = new BaseAnalysis();

        /*
        List<WBData> co2Data = new ArrayList();
        co2Data.add(wb.get(AnalysisConstants.CO2_ANALYSIS));
        List<WBData> energyUseData = new ArrayList();
        energyUseData.add(wb.get(AnalysisConstants.ENERGY_USE_ANALYSIS));
        List<WBData> airPollutionData = new ArrayList();
        airPollutionData.add(wb.get(AnalysisConstants.PM25_AIR_POLLUTION));
        */


        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData> co2DataMap = new HashMap<>();
        co2DataMap.put(AnalysisConstants.CO2_ANALYSIS, wb.get(AnalysisConstants.CO2_ANALYSIS));
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData> energyUseDataMap = new HashMap<>();
        energyUseDataMap.put(AnalysisConstants.ENERGY_USE_ANALYSIS, wb.get(AnalysisConstants.ENERGY_USE_ANALYSIS));
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData> airPollutionDataMap = new HashMap<>();
        airPollutionDataMap.put(AnalysisConstants.PM25_AIR_POLLUTION, wb.get(AnalysisConstants.PM25_AIR_POLLUTION));

        //Analyze data and create new WBData to hold it
        WBData analyzedCO2Data = baseAnalysis.doAnalysis(co2DataMap, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("CO2 ANNUAL_PERCENT_CHANGE", analyzedCO2Data);
        //Analyze data and create new WBData to hold it
        WBData analyzedEnergyUseData = baseAnalysis.doAnalysis(energyUseDataMap, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("Energy use ANNUAL_PERCENT_CHANGE ", analyzedEnergyUseData);
        //Analyze data and create new WBData to hold it
        WBData analyzedPM25 = baseAnalysis.doAnalysis(airPollutionDataMap, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("Energy use ANNUAL_PERCENT_CHANGE ", analyzedEnergyUseData);

        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.CO2_ANALYSIS, analyzedCO2Data);
        analyzedData.put(AnalysisConstants.ENERGY_USE_ANALYSIS, analyzedEnergyUseData);
        analyzedData.put(AnalysisConstants.PM25_AIR_POLLUTION, analyzedPM25);

        return analyzedData;
    }


}
