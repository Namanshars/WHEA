package com.yorku.wbapp.controller;

import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.Map;
import java.util.Vector;

public interface FacadeIF {

    public Vector<String> getCountries();

    public Map<String, WBData> performAnalysis(FilterCriteria filterCriteria, String analysisName);

    public WBData getWBData();
}
