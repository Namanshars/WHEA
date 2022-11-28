package com.yorku.wbapp.controller;

import com.yorku.wbapp.analysis.AnalysisConstants;
import com.yorku.wbapp.visualizer.GraphType;

import java.util.List;

public interface GraphController {
    public List<GraphType> suitableGraphs(AnalysisConstants aType);




}
