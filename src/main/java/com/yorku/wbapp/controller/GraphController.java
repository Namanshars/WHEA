package com.yorku.wbapp.controller;

import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.view.visualizer.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphController {

    public List<String> getSuitableGraphs(String analysisType) {
        List<String> suitableGraphs = new ArrayList<>();
        if(analysisType.equals(AnalysisConstants.ANALYSIS_ONE)) {
            suitableGraphs.add(VisualConstants.BAR);
            suitableGraphs.add(VisualConstants.LINE);
            suitableGraphs.add(VisualConstants.SCATTER);
            suitableGraphs.add(VisualConstants.REPORT);
        }else if(analysisType.equals(AnalysisConstants.ANALYSIS_TWO)) {
            suitableGraphs.add(VisualConstants.BAR);
            suitableGraphs.add(VisualConstants.LINE);
            suitableGraphs.add(VisualConstants.SCATTER);
            suitableGraphs.add(VisualConstants.REPORT);
        }else if(analysisType.equals(AnalysisConstants.ANALYSIS_THREE)) {
            suitableGraphs.add(VisualConstants.BAR);
            suitableGraphs.add(VisualConstants.LINE);
            suitableGraphs.add(VisualConstants.SCATTER);
            suitableGraphs.add(VisualConstants.REPORT);
        }else if(analysisType.equals(AnalysisConstants.ANALYSIS_FOUR)) {
            suitableGraphs.add(VisualConstants.PIE);
            suitableGraphs.add(VisualConstants.REPORT);
        }else if(analysisType.equals(AnalysisConstants.ANALYSIS_FIVE)) {
            suitableGraphs.add(VisualConstants.PIE);
            suitableGraphs.add(VisualConstants.REPORT);
        }else if(analysisType.equals(AnalysisConstants.ANALYSIS_SIX)) {
            suitableGraphs.add(VisualConstants.BAR);
            suitableGraphs.add(VisualConstants.LINE);
            suitableGraphs.add(VisualConstants.SCATTER);
            suitableGraphs.add(VisualConstants.REPORT);
        }else if(analysisType.equals(AnalysisConstants.ANALYSIS_SEVEN)) {
            suitableGraphs.add(VisualConstants.PIE);
            suitableGraphs.add(VisualConstants.REPORT);
        }else if(analysisType.equals(AnalysisConstants.ANALYSIS_EIGHT)) {
            suitableGraphs.add(VisualConstants.BAR);
            suitableGraphs.add(VisualConstants.LINE);
            suitableGraphs.add(VisualConstants.SCATTER);
            suitableGraphs.add(VisualConstants.REPORT);
        }
        return suitableGraphs;
    }

    public List<String> getGraphs(List<String> selectedGraphs, List<String> suitableGraphs) {
        //todo: fix this
        List<String> graphs = new ArrayList<>();
        for(String graph: selectedGraphs){
            if (suitableGraphs.contains(graph)){
                graphs.add(graph);
            }
        }

        return graphs;
    }

    public void createVisuals(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String analysisType, List<String> selectedGraphs){
        System.out.println("createVisuals()");
        List<String> suitableGraphs = getSuitableGraphs(analysisType);
        List<String> applicableGraphs = getGraphs(selectedGraphs, suitableGraphs);
        System.out.println("applicableGraphs: "+applicableGraphs);

        for(String graphName: applicableGraphs){
            if (graphName.equals(VisualConstants.BAR)){
                new BarVisualizer(new GraphVisual()).createVisual(west, analyzedDataMapOne, analyzedDataMapTwo, analysisType);
            }

            if (graphName.equals(VisualConstants.LINE)){
                new LineVisualizer(new GraphVisual()).createVisual(west, analyzedDataMapOne, null, analysisType);
            }

            if (graphName.equals(VisualConstants.SCATTER)){
                new ScatterVisualizer(new GraphVisual()).createVisual(west, analyzedDataMapOne, analyzedDataMapTwo, analysisType);
            }

            if (graphName.equals(VisualConstants.REPORT)){
                new ReportVisualizer(new GraphVisual()).createVisual(west, analyzedDataMapOne, null, analysisType);
            }

            if (graphName.equals(VisualConstants.TIMESERIES)){
                new TimeSeriesVisualizer(new GraphVisual()).createVisual(west, analyzedDataMapOne, analyzedDataMapTwo, analysisType);
            }

            if (graphName.equals(VisualConstants.PIE)){
                new PieVisualizer(new GraphVisual()).createVisual(west, analyzedDataMapOne, null, analysisType);
            }
        }


    }

}
