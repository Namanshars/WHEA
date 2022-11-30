package com.yorku.wbapp.controller.analysis;

import com.yorku.wbapp.controller.analysis.analysisImplementation.*;
//import com.yorku.wbapp.controller.analysis.ConcreteClasses.CO2Analysis;

//HERE WE USE FACTORY DESIGN PATTERN
public class AnalysisFactory {

    public AnalysisStrategy loadAnalysis(String aType){

        //Use factory pattern to create AnalysisStrategy objects based off the name of the chosen analysis
        AnalysisStrategy concreteClass = null;
        if (aType.equals(AnalysisConstants.ANALYSIS_ONE)){
            concreteClass = new AnalysisOne(AnalysisConstants.ANALYSIS_ONE);
        }else if (aType.equals(AnalysisConstants.ANALYSIS_TWO)) {
            concreteClass = new AnalysisTwo(AnalysisConstants.ANALYSIS_TWO);
        }else if (aType.equals(AnalysisConstants.ANALYSIS_THREE)) {
                concreteClass = new AnalysisThree(AnalysisConstants.ANALYSIS_THREE);
        }else if (aType.equals(AnalysisConstants.ANALYSIS_FOUR)) {
            concreteClass = new AnalysisFour(AnalysisConstants.ANALYSIS_FOUR);
        }else if (aType.equals(AnalysisConstants.ANALYSIS_FIVE)) {
            concreteClass = new AnalysisFive(AnalysisConstants.ANALYSIS_FIVE);
        }else if (aType.equals(AnalysisConstants.ANALYSIS_SIX)) {
            concreteClass = new AnalysisSix(AnalysisConstants.ANALYSIS_SIX);
        }else if (aType.equals(AnalysisConstants.ANALYSIS_SEVEN)) {
            concreteClass = new AnalysisSeven(AnalysisConstants.ANALYSIS_SEVEN);
        }else if (aType.equals(AnalysisConstants.ANALYSIS_EIGHT)) {
            concreteClass = new AnalysisEight(AnalysisConstants.ANALYSIS_EIGHT);
        }

        return concreteClass;

    }
}
