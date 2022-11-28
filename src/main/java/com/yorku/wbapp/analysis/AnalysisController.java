package com.yorku.wbapp.analysis;

import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.logging.Filter;

public class AnalysisController implements AnalysisControllerIF {
    @Override
    public WBData performAnalysis(WBData filteredWBData, String aType, String calculation){

        //Here we call the factory pattern
        //The factory will build an analysisStrategy object based off the WBData and the name of the analysis
        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(aType);
        System.out.println(aType+" strategy: "+strategy);
        //WBData finalData = strategy.analyse(filteredWBData, calculation);
        //return finalData;
        return null;
    }

}
