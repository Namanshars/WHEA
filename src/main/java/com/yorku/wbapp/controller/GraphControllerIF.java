package com.yorku.wbapp.controller;

import com.yorku.wbapp.model.WBData;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public interface GraphControllerIF {
    public List<String>getSuitableGraphs(String analysisType);

    public void createVisuals(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String analysisType, List<String> selectedGraphs);
}
