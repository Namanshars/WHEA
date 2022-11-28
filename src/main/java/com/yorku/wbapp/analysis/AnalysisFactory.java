package com.yorku.wbapp.analysis;

import com.yorku.wbapp.analysis.ConcreteClasses.AnalysisOne;
//import com.yorku.wbapp.analysis.ConcreteClasses.CO2Analysis;

public class AnalysisFactory {

    public AnalysisStrategy loadAnalysis(String aType){

        //create AnalysisStrategy object based off the name of the analysis the user wants

        AnalysisStrategy concreteClass = null;
        if (aType.equals(AnalysisConstants.ANALYSIS_ONE)){
            concreteClass = new AnalysisOne(AnalysisConstants.ANALYSIS_ONE);
        }
        /*
        else if (aType.equals(AnalysisConstants.ENERGY_USE_ANALYSIS)){
            concreteClass = new CO2Analysis(AnalysisConstants.ENERGY_USE_ANALYSIS);
        }
         */
        return concreteClass;

    }
}
