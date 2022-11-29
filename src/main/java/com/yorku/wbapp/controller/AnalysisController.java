package com.yorku.wbapp.controller;

import com.yorku.wbapp.controller.analysis.AnalysisFactory;
import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.Map;

public class AnalysisController implements AnalysisControllerIF {

    public Map<String, WBData> performAnalysis(FilterCriteria filterCriteria, String analysisName){

        //Here we call the factory pattern
        //The factory will build an analysisStrategy object based off the name of the analysis
        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysisName);
        System.out.println(analysisName+" strategy: "+strategy);
        //perform the analysis
        Map<String, WBData> analyzedDataMap = strategy.analyse(filterCriteria);
        return analyzedDataMap;
    }

}
