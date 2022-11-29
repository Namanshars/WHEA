package com.yorku.wbapp.view.visualizer;

import com.yorku.wbapp.model.WBData;

import javax.swing.*;
import java.util.Map;

public interface Visual {

    public void createVisual(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String chartsName);

}
