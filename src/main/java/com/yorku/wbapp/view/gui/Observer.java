package com.yorku.wbapp.view.gui;

import com.yorku.wbapp.controller.GraphControllerIF;
import org.json.JSONException;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.List;

public interface Observer {

    public void update(int selectedInitialYear, int selectedLastYear, String selectedCountry, String selectedAnalysis, List<String> userSelectedGraphs, GraphControllerIF graphController, JPanel west) throws FileNotFoundException, JSONException;
}
