package com.yorku.wbapp.view.visualizer;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.view.visualizer.Visual;

import javax.swing.*;
import java.util.Map;

public class VisualDecorator implements Visual {
    protected Visual visual;

    public VisualDecorator(Visual visual) {
        this.visual = visual;
    }

    @Override
    public void createVisual(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String chartsName) {
        this.visual.createVisual(west, analyzedDataMapOne, analyzedDataMapTwo, chartsName);
    }







}
