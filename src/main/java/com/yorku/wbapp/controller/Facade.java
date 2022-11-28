package com.yorku.wbapp.controller;

import com.yorku.wbapp.analysis.AnalysisStrategy;
import com.yorku.wbapp.analysis.BaseAnalysis;
import com.yorku.wbapp.analysis.AnalysisConstants;
import com.yorku.wbapp.analysis.ConcreteClasses.AnalysisOne;
import com.yorku.wbapp.datainteraction.DataManager;
import com.yorku.wbapp.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
//This is the class that talks to everyone. It talks to the front end and all java classes
//TEST CODE IN THIS CLASS
public class Facade {
    Countries countries = null;
    WBData wbData;
    WBData filteredWBData;
    Vector<String> countriesNames = null;

    public Facade() {
        DataManager countryData = new DataManager();
        countries = countryData.getCountriesList();

        countriesNames = new Vector<String>();
        for (Country country : countries.getCountries()) {
            String countryName = country.getCountryValue();
            countriesNames.add(countryName);
        }
    }

    public static void main(String[] args) {
        Facade facade = new Facade();
        FilterCriteria fc = new FilterCriteria("IN", 2011, 2014, AnalysisConstants.CO2_ANALYSIS);
        WBData co2Data = facade.getFilterCountryData(fc);
        printData("CO2", co2Data);

        fc = new FilterCriteria("IN", 2011, 2014, AnalysisConstants.ENERGY_USE_ANALYSIS);
        WBData energyUseData = facade.getFilterCountryData(fc);
        printData("Energy use", energyUseData);

        fc = new FilterCriteria("IN", 2011, 2014, AnalysisConstants.PM25_AIR_POLLUTION);
        WBData airPollutionData = facade.getFilterCountryData(fc);

        printData("AIR POLLUTION", airPollutionData);

        AnalysisStrategy analysisOne = new AnalysisOne("Analysis One");
        Map<String, WBData> wbDataMap = new HashMap<>();
        wbDataMap.put(AnalysisConstants.CO2_ANALYSIS, co2Data);
        wbDataMap.put(AnalysisConstants.ENERGY_USE_ANALYSIS, energyUseData);
        wbDataMap.put(AnalysisConstants.PM25_AIR_POLLUTION, airPollutionData);

        Map<String, WBData> analyzedDataMap = analysisOne.analyse(wbDataMap);
        printAnalyzedData("CO2 ANNUAL_PERCENT_CHANGE", analyzedDataMap.get(AnalysisConstants.CO2_ANALYSIS));
        printAnalyzedData("Energy use ANNUAL_PERCENT_CHANGE ", analyzedDataMap.get(AnalysisConstants.ENERGY_USE_ANALYSIS));
        printAnalyzedData("Air Pollution ANNUAL_PERCENT_CHANGE ", analyzedDataMap.get(AnalysisConstants.PM25_AIR_POLLUTION));

    }

    public Vector<String> getCountries() {

        return countriesNames;
    }

    public WBData getWBData() {
        return wbData;
    }

    //get an object of all data records that match the filter criteria
    public WBData getFilterCountryData(FilterCriteria filterCriteria) {
        DataManager dataManager = new DataManager();
        filteredWBData = dataManager.getCountryWBData(filterCriteria);

        return filteredWBData;
    }


    private static void printAnalyzedData(String type, WBData analyzedCO2Data) {
        System.out.println("===="+type+"====");
        List<WBDataRecord> analyzedRecords = analyzedCO2Data.getRecords();
        for(WBDataRecord record: analyzedRecords){
            System.out.println("FrontendManager: "+record.toString());
        }
        System.out.println("calculated value = "+ analyzedCO2Data.getCalculatedValue()+"/n");
    }

    private static void printData(String str, WBData co2Data) {
        System.out.println("DataType: "+str);
        for (WBDataRecord record: co2Data.getRecords()){
            System.out.println(record);
        }
    }


}
