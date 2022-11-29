package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.analysis.CalculationManager;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.*;


public class AnalysisEight extends AnalysisStrategy{
    @Override
    public Map<String, WBData> analyse(FilterCriteria filterCriteria) {
        //get the data based on FilterCriteria - country and year range
        FilterCriteria fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(), AnalysisConstants.GOVT_EXPENDITURE);
        WBData dataOne = getFilterWBData(fc);

        fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(),  AnalysisConstants.HEALTH_EXP);
        WBData dataTwo = getFilterWBData(fc);

        Map<String, WBData> wb = new HashMap<>();
        wb.put(AnalysisConstants.GOVT_EXPENDITURE, dataOne);
        wb.put(AnalysisConstants.HEALTH_EXP, dataTwo);

        CalculationManager calculationManager = new CalculationManager();
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData> dataOneMap = new HashMap<>();
        dataOneMap.put(AnalysisConstants.GOVT_EXPENDITURE, wb.get(AnalysisConstants.GOVT_EXPENDITURE));
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData>  dataTwoMap = new HashMap<>();
        dataTwoMap.put(AnalysisConstants.HEALTH_EXP, wb.get(AnalysisConstants.HEALTH_EXP));

        //Analyze data and create new WBData to hold it
        WBData analyzedDataOne = calculationManager.doCalculation(dataOneMap, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData(" ", analyzedDataOne);
        WBData analyzedDataTwo = calculationManager.doCalculation(dataTwoMap, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData(" ", analyzedDataTwo);
        //Analyze data and create new WBData to hold it
        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.GOVT_EXPENDITURE, analyzedDataOne);
        analyzedData.put(AnalysisConstants.HEALTH_EXP, analyzedDataTwo);

        return analyzedData;
    }

    public AnalysisEight(String analysisName) {
		super(analysisName);
	}
	/*
	 public Map<String, WBData> analyse(Map<String, WBData> wb) {
		BaseAnalysis baseAnalysis = new BaseAnalysis();
        Map<String, WBData> Govt_Exp_Data = new HashMap<>();
        Govt_Exp_Data.put(AnalysisConstants.GOVT_EXPENDITURE, wb.get(AnalysisConstants.GOVT_EXPENDITURE));
        Map<String, WBData> Health_Exp_Data  = new HashMap<>();
        Health_Exp_Data.put(AnalysisConstants.HEALTH_EXP, wb.get(AnalysisConstants.HEALTH_EXP));

        
        WBData analyzedGovt_Data = baseAnalysis.doAnalysis(Govt_Exp_Data, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        WBData analyzedHealth_Exp = baseAnalysis.doAnalysis(Health_Exp_Data, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.HEALTH_EXP, analyzedGovt_Data);
        analyzedData.put(AnalysisConstants.HOSPITAL_BEDS, analyzedHealth_Exp);

        return analyzedData;
	}

	 */
}
