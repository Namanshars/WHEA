package com.yorku.wbapp.controller;

import com.yorku.wbapp.controller.datainteraction.DataManager;
import com.yorku.wbapp.model.*;

import java.util.List;
import java.util.Map;
import java.util.Vector;
//This is the class that talks to everyone. It talks to the front end and all java classes
//TEST CODE IN THIS CLASS
public class Facade implements FacadeIF{
    Countries countries = null;
    WBData wbData;
    WBData filteredWBData;
    Vector<String> countriesNames = null;

    public Facade() {
        //Here we use the singleton design pattern to ensure that a single instance of DataManager is used
        DataManager countryData = DataManager.getInstance();
        countries = countryData.getCountriesList();

        countriesNames = new Vector<String>();
        for (Country country : countries.getCountries()) {
            String countryName = country.getCountryValue();
            countriesNames.add(countryName);
        }
    }

    @Override
    public Vector<String> getCountries() {

        return countriesNames;
    }
    @Override
    public WBData getWBData() {

        return wbData;
    }
    @Override
    public Map<String, WBData> performAnalysis(FilterCriteria filterCriteria, String analysisName){
        AnalysisControllerIF analysisController = new AnalysisController();
        return analysisController.performAnalysis(filterCriteria, analysisName);
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
