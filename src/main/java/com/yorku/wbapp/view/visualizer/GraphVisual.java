package com.yorku.wbapp.view.visualizer;

import com.yorku.wbapp.model.WBData;

import javax.swing.*;
import java.util.Map;

public class GraphVisual implements Visual{
    @Override
    public void createVisual(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String chartsName) {
        //this can contain some common methods in future implementations
        System.out.println("GraphVisual created!");
    }
}
