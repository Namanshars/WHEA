package com.yorku.wbapp.analysis.ConcreteClasses;

import com.yorku.wbapp.analysis.AnalysisStrategy;
import com.yorku.wbapp.model.WBData;

import java.util.Map;

public class AnalysisThree extends AnalysisStrategy {
    public AnalysisThree(String analysisName) {
        super(analysisName);
    }

    @Override
    public Map<String, WBData> analyse(Map<String, WBData> wb) {
        return null;
    }
}
