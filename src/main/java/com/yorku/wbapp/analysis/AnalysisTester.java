package com.yorku.wbapp.analysis;

import com.yorku.wbapp.controller.Facade;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.util.ArrayList;
import java.util.List;

public class AnalysisTester {

    /*
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


        //Analysis
        BaseAnalysis baseAnalysis = new BaseAnalysis();
        List<WBData> co2DataStack = new ArrayList();
        co2DataStack.add(co2Data);
        co2DataStack.add(airPollutionData);

        List<WBData> energyUseDataStack = new ArrayList<>();
        energyUseDataStack.add(co2Data);
        energyUseDataStack.add(energyUseData);

        WBData analyzedCO2Data = baseAnalysis.doAnalysis(co2DataStack, AnalysisConstants.RATIO);
        printAnalyzedData("CO2", analyzedCO2Data);

        WBData analyzedEnergyUseData = baseAnalysis.doAnalysis(energyUseDataStack, AnalysisConstants.RATIO);
        printAnalyzedData("Energy use RATIO ", analyzedEnergyUseData);

        analyzedEnergyUseData = baseAnalysis.doAnalysis(energyUseDataStack, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData("Energy use ANNUAL_PERCENT_CHANGE ", analyzedEnergyUseData);

    }
    */
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
