package com.yorku.wbapp.analysis.ConcreteClasses;

import com.yorku.wbapp.analysis.AnalysisConstants;
import com.yorku.wbapp.analysis.AnalysisStrategy;
import com.yorku.wbapp.analysis.BaseAnalysis;
import com.yorku.wbapp.model.WBData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisFour extends AnalysisStrategy {
    public AnalysisFour(String analysisName) {
        super(analysisName);
    }

    @Override
    public Map<String, WBData> analyse(Map<String, WBData> wb) {
        BaseAnalysis baseAnalysis = new BaseAnalysis();
        //Create a map that associates the name of indicator with relevant data
        Map<String, WBData> forestAreaData = new HashMap<>();
        forestAreaData.put(AnalysisConstants.FOREST_AREA, wb.get(AnalysisConstants.FOREST_AREA));
        //Analyze data and create new WBData to hold it
        WBData analyzedforestAreaData = baseAnalysis.doAnalysis(forestAreaData, AnalysisConstants.AVERAGE);

        //return new map that associates name of indicator with the analyzed data
        Map<String, WBData> analyzedData = new HashMap<>();
        analyzedData.put(AnalysisConstants.FOREST_AREA, analyzedforestAreaData);

        return analyzedData;
    }
}
