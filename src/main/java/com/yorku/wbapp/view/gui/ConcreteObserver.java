package com.yorku.wbapp.view.gui;

import com.yorku.wbapp.controller.Facade;
import com.yorku.wbapp.controller.GraphControllerIF;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;
import org.json.JSONException;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class ConcreteObserver implements Observer{
    @Override
    public void update(int selectedInitialYear, int selectedLastYear, String selectedCountry, String selectedAnalysis, List<String> userSelectedGraphs, GraphControllerIF graphController, JPanel west){

        JFrame frame = new JFrame();
        Facade facade = new Facade();
        //After retrieving all the selected data from the user, we must create a FilterCriteria object so that they can be passed around the program
        FilterCriteria selectedFilterCriteria = new FilterCriteria(selectedCountry, selectedInitialYear, selectedLastYear, null);
        JOptionPane.showMessageDialog(frame, "Recalculate: " + selectedCountry + " start:" + selectedInitialYear + " end: " + selectedLastYear + " analysis: " + selectedAnalysis);
        //Perform the necessary analysis so that it can be used for visualization
        Map<String, WBData> analyzedDataMap = facade.performAnalysis(selectedFilterCriteria, selectedAnalysis);
        System.out.println("userSelectedGraphs =" + userSelectedGraphs);
        //Create visual for the analysis
        graphController.createVisuals(west, analyzedDataMap, analyzedDataMap, selectedAnalysis, userSelectedGraphs);
    }
}
