package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.analysis.CalculationManager;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.*;


public class AnalysisFive extends AnalysisStrategy {
    @Override
    public Map<String, WBData> analyse(FilterCriteria filterCriteria) {
        //get the data based on FilterCriteria - country and year range
        FilterCriteria fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear() + 1, filterCriteria.getToYear(), AnalysisConstants.GOVT_EXPENDITURE);
        WBData data = getFilterWBData(fc);


        Map<String, WBData> dataMap = new HashMap<>();
        dataMap.put(AnalysisConstants.GOVT_EXPENDITURE, data);

        CalculationManager calculationManager = new CalculationManager();
        WBData analyzedforestAreaData = calculationManager.doCalculation(dataMap, AnalysisConstants.AVERAGE);

        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.GOVT_EXPENDITURE, analyzedforestAreaData);

        return analyzedData;
    }

    public AnalysisFive(String analysisName) {
	    super(analysisName);
	}

	/*
	 public Map<String, WBData> analyse(Map<String, WBData> wb) {
        BaseAnalysis baseAnalysis = new BaseAnalysis();
        
        Map<String, WBData> Govt_Exp_Data = new HashMap<>();
        Govt_Exp_Data.put(AnalysisConstants.GOVT_EXPENDITURE, wb.get(AnalysisConstants.GOVT_EXPENDITURE));
        
        WBData analyzedGovt_Data = baseAnalysis.doAnalysis(Govt_Exp_Data, AnalysisConstants.AVERAGE);
        printAnalyzedData("Government Expenditure", analyzedGovt_Data);
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.GOVT_EXPENDITURE, analyzedGovt_Data);
        return analyzedData;
	}

	 */
}
