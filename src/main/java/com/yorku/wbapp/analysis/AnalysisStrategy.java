package com.yorku.wbapp.analysis;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class AnalysisStrategy {
    String analysisName;

    //Method to perform the necessary analysis
    public abstract Map<String, WBData> analyse(Map<String, WBData> wb);

    public AnalysisStrategy(String analysisName) {

        this.analysisName = analysisName;
    }

    public String getAnalysisName() {

        return analysisName;
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

