package com.yorku.wbapp.view.gui;

import com.yorku.wbapp.controller.GraphControllerIF;

import javax.swing.*;
import java.util.List;

public interface Observer {

    public void update(int selectedInitialYear, int selectedLastYear, String selectedCountry, String selectedAnalysis, List<String> userSelectedGraphs, GraphControllerIF graphController, JPanel west);
}
