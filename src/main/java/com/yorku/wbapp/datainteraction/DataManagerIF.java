package com.yorku.wbapp.datainteraction;

import com.yorku.wbapp.analysis.AnalysisStrategy;
import com.yorku.wbapp.visualizer.GraphType;
import com.yorku.wbapp.model.Countries;
import com.yorku.wbapp.model.WBData;

public interface DataManagerIF {
    public boolean checkUserPwd(String user, String pwd);
    public WBData getWBData();
    public Countries getCountriesData();
    public AnalysisStrategy getAnalysis();
    public GraphType getGraphData();



}
