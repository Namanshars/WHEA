package com.yorku.wbapp.view.visualizer;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TimeSeriesVisualizer extends VisualDecorator {

    public TimeSeriesVisualizer(Visual visual) {
        super(visual);
    }
    @Override
    public void createVisual(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String chartsName) {
        TimeSeries series1 = new TimeSeries("");
        TimeSeries series2 = new TimeSeries("");
        TimeSeries series3 = new TimeSeries("");
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        TimeSeriesCollection dataset2 = new TimeSeriesCollection();

        //
        for(String key: analyzedDataMapOne.keySet()){
            WBData wbData = analyzedDataMapOne.get(key);
            TimeSeries  series = new TimeSeries (key);
            for(WBDataRecord record: wbData.getRecords()){
                series.add(new Year(record.getYear()), record.getAnalysisValue());
            }
            dataset.addSeries(series);
        }

        for(String key: analyzedDataMapTwo.keySet()){
            WBData wbData = analyzedDataMapTwo.get(key);
            TimeSeries  series = new TimeSeries (key);
            for(WBDataRecord record: wbData.getRecords()){
                series.add(new Year(record.getYear()), record.getAnalysisValue());
            }
            dataset2.addSeries(series);
        }

        //

        XYPlot plot = new XYPlot();
        XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
        XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

        plot.setDataset(0, dataset);
        plot.setRenderer(0, splinerenderer1);
        DateAxis domainAxis = new DateAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        plot.setDataset(1, dataset2);
        plot.setRenderer(1, splinerenderer2);
        plot.setRangeAxis(1, new NumberAxis("US$"));

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

        JFreeChart chart = new JFreeChart(chartsName, new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        west.add(chartPanel);

    }
}
