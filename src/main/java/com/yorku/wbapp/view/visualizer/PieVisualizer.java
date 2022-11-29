package com.yorku.wbapp.view.visualizer;

import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class PieVisualizer extends VisualDecorator {

    public PieVisualizer(Visual visual) {
        super(visual);
    }
    @Override
    public void createVisual(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String chartsName) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        /*
        dataset.addValue(3.946, "Unemployed", "Men");
        dataset.addValue(96.054, "Employed", "Men");
        dataset.addValue(3.837, "Unemployed", "Women");
        dataset.addValue(96.163, "Employed", "Women");
        */

        //load data into chart
        String key = analyzedDataMapOne.keySet().stream().findFirst().get();
        WBData wbData = analyzedDataMapOne.get(key);
        WBDataRecord record = wbData.getRecords().get(0);
        // TODO: set the rowKey to the calculation performed in the specific analysis. Even though a pie chart will only really use average, try to make it dynamic.
        dataset.addValue(record.getAnalysisValue(), "" + AnalysisConstants.AVERAGE, "" + record.getIndicator());
        dataset.addValue(100-record.getAnalysisValue(), "Rest of " + AnalysisConstants.AVERAGE, "" + record.getIndicator());

        // TODO: pass title dynamically
        JFreeChart pieChart = ChartFactory.createMultiplePieChart(chartsName, dataset,
                TableOrder.BY_COLUMN, true, true, false);

        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        west.add(chartPanel);
    }
}
