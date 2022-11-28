package com.yorku.wbapp.controller;

import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;
//Interface that talks between front end and analysis module
public interface AnalysisController {

    //call the necessary analysis to be performed
    //this is the method where the Model and Analysis modules talk to each other
    public WBData performAnalysis(WBData filteredWBData);
    //public WBData filterCountryData(FilterCriteria fc);
}
