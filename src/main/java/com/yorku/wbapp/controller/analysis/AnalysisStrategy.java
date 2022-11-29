package com.yorku.wbapp.controller.analysis;

import com.yorku.wbapp.controller.datainteraction.DataManager;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.util.List;
import java.util.Map;

public abstract class AnalysisStrategy {
    String analysisName;

    //Method to perform the necessary analysis
    public abstract Map<String, WBData> analyse(FilterCriteria fc);

    public AnalysisStrategy(String analysisName) {

        this.analysisName = analysisName;
    }

    public String getAnalysisName() {

        return analysisName;
    }

    //get an object of all data records that match the filter criteria
    public WBData getFilterWBData(FilterCriteria filterCriteria) {
        DataManager dataManager = new DataManager();
        return  dataManager.getCountryWBData(filterCriteria);
    }

    public static void printAnalyzedData(String type, WBData analyzedCO2Data) {
        System.out.println("===="+type+"====");
        List<WBDataRecord> analyzedRecords = analyzedCO2Data.getRecords();
        for(WBDataRecord record: analyzedRecords){
            System.out.println("FrontendManager: "+record.toString());
        }
        System.out.println("calculated value = "+ analyzedCO2Data.getCalculatedValue()+"/n");
    }

    public static void printData(String str, WBData co2Data) {
        System.out.println("DataType: "+str);
        for (WBDataRecord record: co2Data.getRecords()){
            System.out.println(record);
        }
    }
}

