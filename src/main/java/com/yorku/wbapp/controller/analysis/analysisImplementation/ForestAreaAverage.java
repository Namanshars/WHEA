package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.controller.analysis.CalculationManager;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.HashMap;
import java.util.Map;

public class ForestAreaAverage extends AnalysisStrategy {
    @Override
    public Map<String, WBData> analyse(FilterCriteria filterCriteria) {
        //get the data based on FilterCriteria - country and year range
        FilterCriteria fc = new FilterCriteria(filterCriteria.getCountryId(), filterCriteria.getFromYear(), filterCriteria.getToYear(), AnalysisConstants.FOREST_AREA);
        WBData data = getFilterWBData(fc);


        Map<String, WBData> forestAreaData = new HashMap<>();
        forestAreaData.put(AnalysisConstants.FOREST_AREA, data);

        CalculationManager calculationManager = new CalculationManager();
        WBData analyzedforestAreaData = calculationManager.doCalculation(forestAreaData, AnalysisConstants.AVERAGE);

        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.FOREST_AREA, analyzedforestAreaData);

        return analyzedData;
    }

    public ForestAreaAverage(String analysisName) {
        super(analysisName);
    }

    public Map<String, WBData> analyseX(Map<String, WBData> wb) {

        CalculationManager calculationManager = new CalculationManager();
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData> forestAreaData = new HashMap<>();
        forestAreaData.put(AnalysisConstants.FOREST_AREA, wb.get(AnalysisConstants.FOREST_AREA));
        //Analyze data and create new WBData to hold it
        WBData analyzedforestAreaData = calculationManager.doCalculation(forestAreaData, AnalysisConstants.AVERAGE);

        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.FOREST_AREA, analyzedforestAreaData);

        return analyzedData;
    }
}
