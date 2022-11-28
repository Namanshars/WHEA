package com.yorku.wbapp.visualizer;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class BarVisualizer extends Visual{
    @Override
    public void createVisual(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String chartsName) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(String key: analyzedDataMapOne.keySet()){
            WBData wbData = analyzedDataMapOne.get(key);
            //XYSeries series = new XYSeries(key);
            for(WBDataRecord record: wbData.getRecords()){
                System.out.println(key + ": " + record.getAnalysisValue() + ", " + record.getIndicator() + ", " + record.getYear());
                dataset.setValue(record.getAnalysisValue(), record.getIndicator(), "" + record.getYear());
            }
        }


        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        for(String key: analyzedDataMapTwo.keySet()){
            WBData wbData = analyzedDataMapTwo.get(key);
            //XYSeries series = new XYSeries(key);
            for(WBDataRecord record: wbData.getRecords()){
                dataset2.setValue(record.getAnalysisValue(), record.getIndicator(), "" + record.getYear());
            }
        }

        CategoryPlot plot = new CategoryPlot();
        BarRenderer barrenderer1 = new BarRenderer();
        BarRenderer barrenderer2 = new BarRenderer();

        plot.setDataset(0, dataset);
        plot.setRenderer(0, barrenderer1);
        CategoryAxis domainAxis = new CategoryAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        plot.setDataset(1, dataset2);
        plot.setRenderer(1, barrenderer2);
        plot.setRangeAxis(1, new NumberAxis("US$"));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        JFreeChart barChart = new JFreeChart(chartsName, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        // Different way to create bar chart
        /*
         * dataset = new DefaultCategoryDataset();
         *
         * dataset.addValue(3.946, "Unemployed", "Men"); dataset.addValue(96.054,
         * "Employed", "Men"); dataset.addValue(3.837, "Unemployed", "Women");
         * dataset.addValue(96.163, "Employed", "Women"); barChart =
         * ChartFactory.createBarChart("Unemployment: Men vs Women", "Gender",
         * "Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);
         */

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        west.add(chartPanel);
    }
}
