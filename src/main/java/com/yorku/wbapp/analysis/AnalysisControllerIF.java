package com.yorku.wbapp.analysis;

import com.yorku.wbapp.model.WBData;

public interface AnalysisControllerIF {

    public WBData performAnalysis(WBData filteredWBData, String aType, String calculation);

}
