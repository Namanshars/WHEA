package com.yorku.wbapp.controller;

import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.Map;

public interface AnalysisControllerIF {

    public Map<String, WBData> performAnalysis(FilterCriteria filterCriteria, String analysisName);

}
