package com.yorku.wbapp.view.visualizer;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.view.visualizer.Visual;

import javax.swing.*;
import java.util.Map;
//Decorator pattern
//extend Visual class for
public class VisualDecorator implements Visual {
    protected Visual visual;

    public VisualDecorator(Visual visual) {
        this.visual = visual;
    }
    //Create visual using the base visual. Right now we only need to create one graph, but with this class, we can combine functionalities of multiple visuals if we need to extend the program
    @Override
    public void createVisual(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String chartsName) {
        this.visual.createVisual(west, analyzedDataMapOne, analyzedDataMapTwo, chartsName);
    }







}
