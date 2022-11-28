package com.yorku.wbapp.visualizer;

import com.yorku.wbapp.model.WBData;

import javax.swing.*;
import java.util.Map;

public abstract class Visual {

    public abstract void createVisual(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String chartsName);

    /*
    public List<GraphType> suitableGraphs(AnalysisType aType){
        List<GraphType> graphs = new ArrayList<>();

        //run sql code to get all the suitable graphs for this particular analysis from the database

        return graphs;

        }
     */

}
