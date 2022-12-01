package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.analysis.CalculationManager;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.*;


public class AnalysisSix extends AnalysisStrategy{
    public AnalysisSix(String analysisName) {
        super(analysisName);
    }
    @Override
    public Map<String, WBData> analyse(FilterCriteria filterCriteria) {
        //get the data based on FilterCriteria - country and year range
        FilterCriteria fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(), AnalysisConstants.HEALTH_EXP);
        WBData dataOne = getFilterWBData(fc);

        fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(),  AnalysisConstants.HOSPITAL_BEDS);
        WBData dataTwo = getFilterWBData(fc);

        Map<String, WBData> allDataMap = new HashMap<>();
        allDataMap.put(AnalysisConstants.HEALTH_EXP, dataOne);
        allDataMap.put(AnalysisConstants.HOSPITAL_BEDS, dataTwo);

        //Analyze data and create new WBData to hold it
        CalculationManager calculationManager = new CalculationManager();
        WBData analyzedData = calculationManager.doCalculation(allDataMap, AnalysisConstants.RATIO);
        printAnalyzedData("analyzedDataOne ", analyzedData);

        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedDataMap = new HashMap<>();
        analyzedDataMap.put(AnalysisConstants.HEALTH_EXP, analyzedData);
        analyzedDataMap.put(AnalysisConstants.HOSPITAL_BEDS, analyzedData);

        return analyzedDataMap;
    }



	/*
	 public Map<String, WBData> analyseX(Map<String, WBData> wb) {
        BaseAnalysis baseAnalysis = new BaseAnalysis();
        Map<String, WBData> Health_Exp_Data = new HashMap<>();
        Health_Exp_Data.put(AnalysisConstants.HEALTH_EXP, wb.get(AnalysisConstants.HEALTH_EXP));
        Map<String, WBData> HospitalBeds_Data = new HashMap<>();
        HospitalBeds_Data.put(AnalysisConstants.HOSPITAL_BEDS, wb.get(AnalysisConstants.HOSPITAL_BEDS));


        WBData analyzedHealth_Exp = baseAnalysis.doAnalysis(Health_Exp_Data, AnalysisConstants.RATIO);
        WBData analyzedHospitalBeds = baseAnalysis.doAnalysis(HospitalBeds_Data, AnalysisConstants.RATIO);

        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.HEALTH_EXP, analyzedHealth_Exp);
        analyzedData.put(AnalysisConstants.HOSPITAL_BEDS, analyzedHospitalBeds);

        return analyzedData;
	}

	 */
}
