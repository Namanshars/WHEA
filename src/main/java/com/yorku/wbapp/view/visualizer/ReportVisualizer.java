package com.yorku.wbapp.view.visualizer;

import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ReportVisualizer extends VisualDecorator {

    public ReportVisualizer(Visual visual) {
        super(visual);
    }
    @Override
    public void createVisual(JPanel west, Map<String, WBData> analyzedDataMapOne, Map<String, WBData> analyzedDataMapTwo, String chartsName) {
        JTextArea report = new JTextArea();
        report.setEditable(false);
        report.setPreferredSize(new Dimension(400, 300));
        report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        report.setBackground(Color.white);
        String reportMessage, reportMessage2;

        reportMessage = "Mortality vs Expenses & Hospital Beds\n" + "==============================\n";

        for(String key: analyzedDataMapOne.keySet()){
            WBData wbData = analyzedDataMapOne.get(key);
            //XYSeries series = new XYSeries(key);
            for(WBDataRecord record: wbData.getRecords()){
                //series.add(record.getYear(), record.getAnalysisValue());
                reportMessage += "Year " + record.getYear() + ":\n"
                        + "\t" + record.getIndicator() + " => " + record.getAnalysisValue() + "\n";
            }
        }
        report.setText(reportMessage);
        JScrollPane outputScrollPane = new JScrollPane(report);
        west.add(outputScrollPane);


        /*
                + "Year 2018:\n"
                + "\tMortality/1000 births => 5.6\n" + "\tHealth Expenditure per Capita => 10624\n"
                + "\tHospital Beds/1000 people => 2.92\n" + "\n" + "Year 2017:\n" + "\tMortality/1000 births => 5.7\n"
                + "\tHealth Expenditure per Capita => 10209\n" + "\tHospital Beds/1000 people => 2.87\n" + "\n"
                + "Year 2016:\n" + "\tMortality/1000 births => 5.8\n" + "\tHealth Expenditure per Capita => 9877\n"
                + "\tHospital Beds/1000 people => 2.77\n";

        reportMessage2 = "Unemployment: Mev vs Women\n" + "==========================\n" + "Men=>\n"
                + "\tEmployed: 96.054%\n" + "\tUnemployed: 3.946%\n" + "\n" + "Women=>\n" + "\tEmployed: 96.163%\n"
                + "\tUnemployed: 3.837%\n";

         */
    }
}
