package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.analysis.CalculationManager;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.*;


public class AnalysisSeven extends AnalysisStrategy{
    @Override
    public Map<String, WBData> analyse(FilterCriteria filterCriteria) {
        //get the data based on FilterCriteria - country and year range
        FilterCriteria fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(), AnalysisConstants.PROBLEMS_ACCESSING);
        WBData dataOne = getFilterWBData(fc);

        fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(),  AnalysisConstants.MORTALITY_INFANTS);
        WBData dataTwo = getFilterWBData(fc);

        Map<String, WBData> wb = new HashMap<>();
        wb.put(AnalysisConstants.PROBLEMS_ACCESSING, dataOne);
        wb.put(AnalysisConstants.MORTALITY_INFANTS, dataTwo);

        CalculationManager calculationManager = new CalculationManager();
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData> dataOneMap = new HashMap<>();
        dataOneMap.put(AnalysisConstants.PROBLEMS_ACCESSING, wb.get(AnalysisConstants.PROBLEMS_ACCESSING));
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData>  dataTwoMap = new HashMap<>();
        dataTwoMap.put(AnalysisConstants.MORTALITY_INFANTS, wb.get(AnalysisConstants.MORTALITY_INFANTS));

        //Analyze data and create new WBData to hold it
        WBData analyzedDataOne = calculationManager.doCalculation(dataOneMap, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData(" ", analyzedDataOne);
        WBData analyzedDataTwo = calculationManager.doCalculation(dataTwoMap, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        printAnalyzedData(" ", analyzedDataTwo);
        //Analyze data and create new WBData to hold it
        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.PROBLEMS_ACCESSING, analyzedDataOne);
        analyzedData.put(AnalysisConstants.MORTALITY_INFANTS, analyzedDataTwo);

        return analyzedData;

    }

    public AnalysisSeven(String analysisName) {
		super(analysisName);
	}

	/*
	 public Map<String, WBData> analyse(Map<String, WBData> wb) {
		BaseAnalysis baseAnalysis = new BaseAnalysis();
        Map<String, WBData> Problems_Accessing_Data = new HashMap<>();
        Problems_Accessing_Data.put(AnalysisConstants.PROBLEMS_ACCESSING, wb.get(AnalysisConstants.PROBLEMS_ACCESSING));
        Map<String, WBData> Mortality_Infants_Data = new HashMap<>();
        Mortality_Infants_Data.put(AnalysisConstants.MORTALITY_INFANTS, wb.get(AnalysisConstants.MORTALITY_INFANTS));

        
        WBData analyzedProblems_Accessing_W = baseAnalysis.doAnalysis(Problems_Accessing_Data, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        WBData analyzedInfant_Mortality = baseAnalysis.doAnalysis(Mortality_Infants_Data, AnalysisConstants.ANNUAL_PERCENT_CHANGE);
        
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.HEALTH_EXP, analyzedProblems_Accessing_W);
        analyzedData.put(AnalysisConstants.HOSPITAL_BEDS, analyzedInfant_Mortality);

        return analyzedData;
	}

	 */
}
