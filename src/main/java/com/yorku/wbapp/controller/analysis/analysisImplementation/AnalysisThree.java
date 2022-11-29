package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.util.Map;

public class AnalysisThree extends AnalysisStrategy {
    @Override
    public Map<String, WBData> analyse(FilterCriteria fc) {
        return null;
    }

    public AnalysisThree(String analysisName) {
        super(analysisName);
    }

}
