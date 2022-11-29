package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.controller.analysis.CalculationManager;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.HashMap;
import java.util.Map;

public class CO2EnergyUseAirPollutionPercentChange extends AnalysisStrategy {

    public CO2EnergyUseAirPollutionPercentChange(String analysisName) {

        super(analysisName);
    }

    /*
    - We pass in a filter criteria to the analyse method. This filter criteria will contain a country, starting and ending year, but a null for the indicator.
    - We do this because the indicators are the only changing factors when collecting data for this analysis, as the country, start year and end year will stay the same
    - The indicator is set based off the necessary indicator and used to create a new WBData object
     */
    @Override
    public Map<String, WBData> analyse(FilterCriteria filterCriteria) {

        //get the data based on FilterCriteria - country and year range
        //create a data object for each type of indicator used in the analysis
        FilterCriteria fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(), AnalysisConstants.CO2_ANALYSIS);
        WBData co2Data = getFilterWBData(fc);

        fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(),  AnalysisConstants.ENERGY_USE_ANALYSIS);
        WBData energyUseData = getFilterWBData(fc);

        fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(),  AnalysisConstants.PM25_AIR_POLLUTION);
        WBData airPollutionData = getFilterWBData(fc);
        //create a map to hold all these data objects for analysis
        Map<String, WBData> dataHashMap = new HashMap<>();
        dataHashMap.put(AnalysisConstants.CO2_ANALYSIS, co2Data);
        dataHashMap.put(AnalysisConstants.ENERGY_USE_ANALYSIS, energyUseData);
        dataHashMap.put(AnalysisConstants.PM25_AIR_POLLUTION, airPollutionData);

        CalculationManager calculationManager = new CalculationManager();

        //Create a map for each data object so that we can calculate the analysis on each indicator
        Map<String, WBData> co2DataMap = new HashMap<>();
        co2DataMap.put(AnalysisConstants.CO2_ANALYSIS, dataHashMap.get(AnalysisConstants.CO2_ANALYSIS));
        //Create a map for each data object so that we can calculate the analysis on each indicator
        Map<String, WBData> energyUseDataMap = new HashMap<>();
        energyUseDataMap.put(AnalysisConstants.ENERGY_USE_ANALYSIS, dataHashMap.get(AnalysisConstants.ENERGY_USE_ANALYSIS));
        //Create a map for each data object so that we can calculate the analysis on each indicator
        Map<String, WBData> airPollutionDataMap = new HashMap<>();
        airPollutionDataMap.put(AnalysisConstants.PM25_AIR_POLLUTION, dataHashMap.get(AnalysisConstants.PM25_AIR_POLLUTION));

        //Perform calculation on each data object
        WBData analyzedCO2Data = calculationManager.doCalculation(co2DataMap, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("CO2 ANNUAL_PERCENT_CHANGE", analyzedCO2Data);
        //Analyze data and create new WBData to hold it
        WBData analyzedEnergyUseData = calculationManager.doCalculation(energyUseDataMap, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("Energy use ANNUAL_PERCENT_CHANGE ", analyzedEnergyUseData);
        //Analyze data and create new WBData to hold it
        WBData analyzedPM25 = calculationManager.doCalculation(airPollutionDataMap, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("Energy use ANNUAL_PERCENT_CHANGE ", analyzedEnergyUseData);

        //return new map that holds all the analyzed data for each indicator
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.CO2_ANALYSIS, analyzedCO2Data);
        analyzedData.put(AnalysisConstants.ENERGY_USE_ANALYSIS, analyzedEnergyUseData);
        analyzedData.put(AnalysisConstants.PM25_AIR_POLLUTION, analyzedPM25);

        return analyzedData;
    }


}
