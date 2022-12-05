/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

package com.yorku.wbapp.view.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.*;

import com.yorku.wbapp.controller.GraphController;
import com.yorku.wbapp.controller.GraphControllerIF;
import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.Facade;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;
import com.yorku.wbapp.view.visualizer.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.json.JSONException;

public class MainUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private static MainUI instance;

	public static MainUI getInstance() throws FileNotFoundException, JSONException {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	public static void main(String[] args) throws FileNotFoundException, JSONException {

		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}

	/*
	MainUI() {
		// Set window title
		super("Country Statistics");

		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();

		//Interface to all the controllers:
		Facade facade = new Facade();
		countriesNames = facade.getCountries();

		countriesNames.sort(null);
		JComboBox<String> countriesList = new JComboBox<String>(countriesNames);

		//Dates
		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		for (int i = 2021; i >= 2010; i--) {
			years.add("" + i);
		}
		JComboBox<String> fromList = new JComboBox<String>(years);
		JComboBox<String> toList = new JComboBox<String>(years);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		JButton recalculate = new JButton("Recalculate");

		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add("Pie Chart");
		viewsNames.add("Line Chart");
		viewsNames.add("Bar Chart");
		viewsNames.add("Scatter Chart");
		viewsNames.add("Report");
		JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add("Mortality");
		methodsNames.add("Mortality vs Expenses");
		methodsNames.add("Mortality vs Expenses & Hospital Beds");
		methodsNames.add("Mortality vs GDP");
		methodsNames.add("Unemployment vs GDP");
		methodsNames.add("Unemployment");

		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel east = new JPanel();

		// Set charts region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));

		//todo: make this dynamic - get values from the GUI
		//todo: get the analysis to be performed dynamically from the GUI
		FilterCriteria fc = new FilterCriteria("IN", 2011, 2014, null);

		//todo: get graph selection from GUI
		List<String> selectedGraphs = new ArrayList<>();
		selectedGraphs.add(VisualConstants.BAR);
		selectedGraphs.add(VisualConstants.LINE);
		selectedGraphs.add(VisualConstants.SCATTER);
		selectedGraphs.add(VisualConstants.REPORT);
		selectedGraphs.add(VisualConstants.PIE);

		//real thing
		GraphController graphController = new GraphController();

		Map<String, WBData> analyzedDataMap = facade.performAnalysis(fc, AnalysisConstants.ANALYSIS_EIGHT);
		graphController.createVisuals(west, analyzedDataMap, analyzedDataMap, AnalysisConstants.ANALYSIS_EIGHT, selectedGraphs);

		//Map<String, WBData> analyzedDataMap5 = facade.performAnalysis(fc, AnalysisConstants.ANALYSIS_SIX);
		//graphController.createVisuals(west, analyzedDataMap5, analyzedDataMap5, AnalysisConstants.ANALYSIS_SIX, selectedGraphs);

		//Map<String, WBData> analyzedDataMapPie = facade.performAnalysis(fc, AnalysisConstants.ForestAreaAverage);
		Map<String, WBData> analyzedDataMapPie = facade.performAnalysis(fc, AnalysisConstants.ANALYSIS_FIVE);

		selectedGraphs = new ArrayList<>();
		selectedGraphs.add(VisualConstants.LINE);
		selectedGraphs.add(VisualConstants.PIE); //not allowed - should not show up
		graphController.createVisuals(west, analyzedDataMapPie, null, AnalysisConstants.ANALYSIS_FIVE, selectedGraphs);


		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
	}
	 */

	MainUI(){
		// Set window title
		super("Country Statistics");

		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = new Vector<String>();

		JFrame frame = new JFrame();

		//Interface to all the controllers:
		Facade facade = new Facade();
		countriesNames = facade.getCountries();
		System.out.println("countriesNames: "+countriesNames);

		//Load all countries into drop down menu
		//countriesNames.sort(countriesNames);
		JComboBox<String> countriesList = new JComboBox<String>(countriesNames);

		//Dates
		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = new Vector<String>();
		for (int i = 2021; i >= 2010; i--) {
			years.add("" + i);
		}

		//A combo box is a drop down menu which holds a list of items
		//We must retrieve the selected value of eawch box to retrive the year the user selects

		JComboBox<String> fromList = new JComboBox<String>(years);
		JComboBox<String> toList = new JComboBox<String>(years);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		JButton recalculate = new JButton("Recalculate");
		JLabel viewsLabel = new JLabel("Available Views: ");

		//todo: get graph selection from GUI
		List<String> userSelectedGraphs = new ArrayList<>();


		Vector<String> viewsNames = new Vector<String>();
		viewsNames.add(VisualConstants.PIE);
		viewsNames.add(VisualConstants.LINE);
		viewsNames.add(VisualConstants.BAR);
		viewsNames.add(VisualConstants.SCATTER);
		viewsNames.add(VisualConstants.REPORT);
		JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");



		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = new Vector<String>();
		methodsNames.add(AnalysisConstants.ANALYSIS_ONE);
		methodsNames.add(AnalysisConstants.ANALYSIS_TWO);
		methodsNames.add(AnalysisConstants.ANALYSIS_THREE);
		methodsNames.add(AnalysisConstants.ANALYSIS_FOUR);
		methodsNames.add(AnalysisConstants.ANALYSIS_FIVE);
		methodsNames.add(AnalysisConstants.ANALYSIS_SIX);
		methodsNames.add(AnalysisConstants.ANALYSIS_SEVEN);
		methodsNames.add(AnalysisConstants.ANALYSIS_EIGHT);

		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel east = new JPanel();

		// Set charts region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 2));

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);

		//default visual
		displayDefaultVisuals(facade, west);

		//Create a graph controller to control creation of visuals
		GraphControllerIF graphController = new GraphController();

		//Add visual when the + button is pressed
		addView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Get the selected graph
				String selectedGraph = (String) viewsList.getSelectedItem();
				//Get the selected analysis
				String selectedAnalysis = (String) methodsList.getSelectedItem();
				// if the graph the user added is suitable for the analysis then we add it to the list of visuals
				if (graphController.getSuitableGraphs(selectedAnalysis).contains(selectedGraph)){
					if (!(userSelectedGraphs.contains(selectedGraph))){
						userSelectedGraphs.add(selectedGraph);
					}
				}
				else{
					JOptionPane.showMessageDialog(frame, "" + selectedGraph + " can not be used for this analysis");
				}
				JOptionPane.showMessageDialog(frame, "userSelectedGraphs =" + userSelectedGraphs);
			}
		});
		//Remove visual when the - button is pressed
		removeView.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedGraph = (String) viewsList.getSelectedItem();


				if (userSelectedGraphs.contains(selectedGraph)){
					userSelectedGraphs.remove(selectedGraph);
				}
				JOptionPane.showMessageDialog(frame, "userSelectedGraphs =" + userSelectedGraphs);
			}
		});
		Observer observer = new ConcreteObserver();

		//If the recalculate button is pressed, then we validate and pass our selections
		recalculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				west.removeAll();

				//GraphController graphController = new GraphController();
				String fromYear = (String) fromList.getSelectedItem();
				String endYear = (String) toList.getSelectedItem();
				int selectedLastYear = Integer.parseInt(endYear);
				int selectedInitialYear = Integer.parseInt(fromYear);

				String selectedCountry = (String) countriesList.getSelectedItem();

				//todo: Check if analysis is available for the year
				if (selectedInitialYear > selectedLastYear){
					JOptionPane.showMessageDialog(frame, "The starting year cannot be greater than the ending year");
				}
				else if (facade.getNonFetchableCountries().contains(selectedCountry)){
					JOptionPane.showMessageDialog(frame, "Cannot fetch data for this country");
				}
				else {

					//Get the selected analysis from the user
					String selectedAnalysis = (String) methodsList.getSelectedItem();

					//Here we use the observer pattern to update all the user selections
					try {
						observer.update(selectedInitialYear, selectedLastYear, selectedCountry, selectedAnalysis, userSelectedGraphs, graphController, west);
					} catch (FileNotFoundException fileNotFoundException) {
						fileNotFoundException.printStackTrace();
					} catch (JSONException jsonException) {
						jsonException.printStackTrace();
					}
				}

				west.revalidate();
				west.repaint();

			}
		});





	}

	private void displayDefaultVisuals(Facade facade, JPanel west) {
		List<String> defaultGraphs = new ArrayList<>();
		defaultGraphs.add(VisualConstants.LINE);
		defaultGraphs.add(VisualConstants.BAR);
		defaultGraphs.add(VisualConstants.SCATTER);
		defaultGraphs.add(VisualConstants.REPORT);

		GraphControllerIF defaultGraphController = new GraphController();
		FilterCriteria defaultFilterCriteria = new FilterCriteria("IND", 2011, 2014, null);
		Map<String, WBData> analyzedDefaultDataMap = facade.performAnalysis(defaultFilterCriteria, AnalysisConstants.ANALYSIS_ONE);
		defaultGraphController.createVisuals(west, analyzedDefaultDataMap, analyzedDefaultDataMap, AnalysisConstants.ANALYSIS_ONE, defaultGraphs);
		//
	}


			/*
		Map<String, WBData> analyzedDataMap8 = facade.performAnalysis(selectedFilterCriteria, AnalysisConstants.ANALYSIS_EIGHT);
		graphController.createVisuals(west, analyzedDataMap8, analyzedDataMap8, AnalysisConstants.ANALYSIS_EIGHT, selectedGraphs);

		//Map<String, WBData> analyzedDataMapPie = facade.performAnalysis(fc, AnalysisConstants.ForestAreaAverage);
		Map<String, WBData> analyzedDataMapPie = facade.performAnalysis(selectedFilterCriteria, AnalysisConstants.ANALYSIS_FIVE);

		selectedGraphs = new ArrayList<>();
		selectedGraphs.add(VisualConstants.LINE);
		selectedGraphs.add(VisualConstants.PIE); //not allowed - should not show up
		graphController.createVisuals(west, analyzedDataMapPie, null, AnalysisConstants.ANALYSIS_FIVE, selectedGraphs);
		 */


	/*
		//createCharts(west, analyzedDataMap, analysisOne.getAnalysisName());
		new BarVisualizer(new GraphVisual()).createVisual(west, analyzedDataMap, analyzedDataMap, AnalysisConstants.CO2EnergyUseAirPollutionPercentChange);
		new LineVisualizer(new GraphVisual()).createVisual(west, analyzedDataMap, null, AnalysisConstants.CO2EnergyUseAirPollutionPercentChange);
		new ScatterVisualizer(new GraphVisual()).createVisual(west, analyzedDataMap, analyzedDataMap, AnalysisConstants.CO2EnergyUseAirPollutionPercentChange);
		new TimeSeriesVisualizer(new GraphVisual()).createVisual(west, analyzedDataMap, analyzedDataMap, AnalysisConstants.CO2EnergyUseAirPollutionPercentChange);
		new ReportVisualizer(new GraphVisual()).createVisual(west, analyzedDataMap, null, AnalysisConstants.CO2EnergyUseAirPollutionPercentChange);


	private void createCharts(JPanel west, Map<String, WBData> wbDataMap, String chartsName) {
		new LineVisualizer().createVisual(west, wbDataMap, null, chartsName);
		new ScatterVisualizer().createVisual(west, wbDataMap, wbDataMap, chartsName);
		new BarVisualizer().createVisual(west, wbDataMap, wbDataMap, chartsName);
		createTimeSeries(west);
		//createBar(west);
		createPie(west);
		createReport(west);
	}
	 */
	private void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage, reportMessage2;

		reportMessage = "Mortality vs Expenses & Hospital Beds\n" + "==============================\n" + "Year 2018:\n"
				+ "\tMortality/1000 births => 5.6\n" + "\tHealth Expenditure per Capita => 10624\n"
				+ "\tHospital Beds/1000 people => 2.92\n" + "\n" + "Year 2017:\n" + "\tMortality/1000 births => 5.7\n"
				+ "\tHealth Expenditure per Capita => 10209\n" + "\tHospital Beds/1000 people => 2.87\n" + "\n"
				+ "Year 2016:\n" + "\tMortality/1000 births => 5.8\n" + "\tHealth Expenditure per Capita => 9877\n"
				+ "\tHospital Beds/1000 people => 2.77\n";

		reportMessage2 = "Unemployment: Mev vs Women\n" + "==========================\n" + "Men=>\n"
				+ "\tEmployed: 96.054%\n" + "\tUnemployed: 3.946%\n" + "\n" + "Women=>\n" + "\tEmployed: 96.163%\n"
				+ "\tUnemployed: 3.837%\n";

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}

	private void createCO2AirPollutionLine(JPanel west, Map<String, WBData> analyzedDataMap, String chartsName) {
		WBData co2Data = analyzedDataMap.get(AnalysisConstants.CO2_ANALYSIS);
		WBData energyUseData = analyzedDataMap.get(AnalysisConstants.ENERGY_USE_ANALYSIS);
		WBData airPollutionData =  analyzedDataMap.get(AnalysisConstants.PM25_AIR_POLLUTION);

		XYSeries  series1 = new XYSeries (AnalysisConstants.CO2_ANALYSIS);
		for(WBDataRecord record: co2Data.getRecords()){
			series1.add(record.getYear(), record.getAnalysisValue());
		}

		XYSeries series2 = new XYSeries(AnalysisConstants.ENERGY_USE_ANALYSIS);
		for(WBDataRecord record: energyUseData.getRecords()){
			series2.add(record.getYear(), record.getAnalysisValue());
		}

		XYSeries series3 = new XYSeries(AnalysisConstants.PM25_AIR_POLLUTION);
		for(WBDataRecord record: airPollutionData.getRecords()){
			series3.add(record.getYear(), record.getAnalysisValue());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		JFreeChart chart = ChartFactory.createXYLineChart(chartsName, "Year", "", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(
				new TextTitle(chartsName, new Font("Serif", java.awt.Font.BOLD, 18)));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);

	}


	private void createScatter(JPanel west, Map<String, WBData> analyzedDataMap) {
		WBData co2Data = analyzedDataMap.get(AnalysisConstants.CO2_ANALYSIS);
		WBData energyUseData = analyzedDataMap.get(AnalysisConstants.ENERGY_USE_ANALYSIS);
		WBData airPollutionData =  analyzedDataMap.get(AnalysisConstants.PM25_AIR_POLLUTION);

		TimeSeries series1 = new TimeSeries("Mortality/1000 births");
		for(WBDataRecord record: co2Data.getRecords()){
			series1.add(new Year(record.getYear()), record.getAnalysisValue());
		}

		TimeSeries series2 = new TimeSeries("Health Expenditure per Capita");
		for(WBDataRecord record: energyUseData.getRecords()){
			series2.add(new Year(record.getYear()), record.getAnalysisValue());
		}

		TimeSeriesCollection dataset2 = new TimeSeriesCollection();
		dataset2.addSeries(series2);

		TimeSeries series3 = new TimeSeries("Hospital Beds/1000 people");
		for(WBDataRecord record: airPollutionData.getRecords()){
			series3.add(new Year(record.getYear()), record.getAnalysisValue());
		}

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
		XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.setDataset(1, dataset2);
		plot.setRenderer(1, itemrenderer2);
		plot.setRangeAxis(1, new NumberAxis("US$"));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart scatterChart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}


	private void createScatter(JPanel west) {
		TimeSeries series1 = new TimeSeries("Mortality/1000 births");
		series1.add(new Year(2018), 5.6);
		series1.add(new Year(2017), 5.7);
		series1.add(new Year(2016), 5.8);
		series1.add(new Year(2015), 5.8);
		series1.add(new Year(2014), 5.9);
		series1.add(new Year(2013), 6.0);
		series1.add(new Year(2012), 6.1);
		series1.add(new Year(2011), 6.2);
		series1.add(new Year(2010), 6.4);

		TimeSeries series2 = new TimeSeries("Health Expenditure per Capita");
		series2.add(new Year(2018), 10624);
		series2.add(new Year(2017), 10209);
		series2.add(new Year(2016), 9877);
		series2.add(new Year(2015), 9491);
		series2.add(new Year(2014), 9023);
		series2.add(new Year(2013), 8599);
		series2.add(new Year(2012), 8399);
		series2.add(new Year(2011), 8130);
		series2.add(new Year(2010), 7930);
		TimeSeriesCollection dataset2 = new TimeSeriesCollection();
		dataset2.addSeries(series2);

		TimeSeries series3 = new TimeSeries("Hospital Beds/1000 people");
		series3.add(new Year(2018), 2.92);
		series3.add(new Year(2017), 2.87);
		series3.add(new Year(2016), 2.77);
		series3.add(new Year(2015), 2.8);
		series3.add(new Year(2014), 2.83);
		series3.add(new Year(2013), 2.89);
		series3.add(new Year(2012), 2.93);
		series3.add(new Year(2011), 2.97);
		series3.add(new Year(2010), 3.05);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
		XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.setDataset(1, dataset2);
		plot.setRenderer(1, itemrenderer2);
		plot.setRangeAxis(1, new NumberAxis("US$"));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart scatterChart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}

	private void createPie(JPanel west) {
		// Different way to create pie chart
		/*
		 * var dataset = new DefaultPieDataset(); dataset.setValue("Unemployed", 3.837);
		 * dataset.setValue("Employed", 96.163);
		 * 
		 * JFreeChart pieChart = ChartFactory.createPieChart("Women's Unemployment",
		 * dataset, true, true, false);
		 */

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(3.946, "Unemployed", "Men");
		dataset.addValue(96.054, "Employed", "Men");
		dataset.addValue(3.837, "Unemployed", "Women");
		dataset.addValue(96.163, "Employed", "Women");

		JFreeChart pieChart = ChartFactory.createMultiplePieChart("Unemployment: Men vs Women", dataset,
				TableOrder.BY_COLUMN, true, true, false);

		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}

	private void createBar(JPanel west) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(5.6, "Mortality/1000 births", "2018");
		dataset.setValue(5.7, "Mortality/1000 births", "2017");
		dataset.setValue(5.8, "Mortality/1000 births", "2016");
		dataset.setValue(5.8, "Mortality/1000 births", "2015");
		dataset.setValue(5.9, "Mortality/1000 births", "2014");
		dataset.setValue(6, "Mortality/1000 births", "2013");
		dataset.setValue(6.1, "Mortality/1000 births", "2012");
		dataset.setValue(6.2, "Mortality/1000 births", "2011");
		dataset.setValue(6.4, "Mortality/1000 births", "2010");

		dataset.setValue(2.92, "Hospital beds/1000 people", "2018");
		dataset.setValue(2.87, "Hospital beds/1000 people", "2017");
		dataset.setValue(2.77, "Hospital beds/1000 people", "2016");
		dataset.setValue(2.8, "Hospital beds/1000 people", "2015");
		dataset.setValue(2.83, "Hospital beds/1000 people", "2014");
		dataset.setValue(2.89, "Hospital beds/1000 people", "2013");
		dataset.setValue(2.93, "Hospital beds/1000 people", "2012");
		dataset.setValue(2.97, "Hospital beds/1000 people", "2011");
		dataset.setValue(3.05, "Hospital beds/1000 people", "2010");

		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

		dataset2.setValue(10623, "Health Expenditure per Capita", "2018");
		dataset2.setValue(10209, "Health Expenditure per Capita", "2017");
		dataset2.setValue(9877, "Health Expenditure per Capita", "2016");
		dataset2.setValue(9491, "Health Expenditure per Capita", "2015");
		dataset2.setValue(9023, "Health Expenditure per Capita", "2014");
		dataset2.setValue(8599, "Health Expenditure per Capita", "2013");
		dataset2.setValue(8399, "Health Expenditure per Capita", "2012");
		dataset2.setValue(8130, "Health Expenditure per Capita", "2011");
		dataset2.setValue(7930, "Health Expenditure per Capita", "2010");

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

		JFreeChart barChart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

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

	private void createLine(JPanel west) {
		XYSeries series1 = new XYSeries("Mortality/1000 births");
		series1.add(2018, 5.6);
		series1.add(2017, 5.7);
		series1.add(2016, 5.8);
		series1.add(2015, 5.8);
		series1.add(2014, 5.9);
		series1.add(2013, 6.0);
		series1.add(2012, 6.1);
		series1.add(2011, 6.2);
		series1.add(2010, 6.4);

		XYSeries series2 = new XYSeries("Health Expenditure per Capita");
		series2.add(2018, 10624);
		series2.add(2017, 10209);
		series2.add(2016, 9877);
		series2.add(2015, 9491);
		series2.add(2014, 9023);
		series2.add(2013, 8599);
		series2.add(2012, 8399);
		series2.add(2011, 8130);
		series2.add(2010, 7930);

		XYSeries series3 = new XYSeries("Hospital Beds/1000 people");
		series3.add(2018, 2.92);
		series3.add(2017, 2.87);
		series3.add(2016, 2.77);
		series3.add(2015, 2.8);
		series3.add(2014, 2.83);
		series3.add(2013, 2.89);
		series3.add(2012, 2.93);
		series3.add(2011, 2.97);
		series3.add(2010, 3.05);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		JFreeChart chart = ChartFactory.createXYLineChart("Mortality vs Expenses & Hospital Beds", "Year", "", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(
				new TextTitle("Mortality vs Expenses & Hospital Beds", new Font("Serif", java.awt.Font.BOLD, 18)));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);

	}

	private void createTimeSeries(JPanel west) {
		TimeSeries series1 = new TimeSeries("Mortality/1000 births");
		series1.add(new Year(2018), 5.6);
		series1.add(new Year(2017), 5.7);
		series1.add(new Year(2016), 5.8);
		series1.add(new Year(2015), 5.8);
		series1.add(new Year(2014), 5.9);
		series1.add(new Year(2013), 6.0);
		series1.add(new Year(2012), 6.1);
		series1.add(new Year(2011), 6.2);
		series1.add(new Year(2010), 6.4);

		TimeSeries series2 = new TimeSeries("Health Expenditure per Capita");
		series2.add(new Year(2018), 10624);
		series2.add(new Year(2017), 10209);
		series2.add(new Year(2016), 9877);
		series2.add(new Year(2015), 9491);
		series2.add(new Year(2014), 9023);
		series2.add(new Year(2013), 8599);
		series2.add(new Year(2012), 8399);
		series2.add(new Year(2011), 8130);
		series2.add(new Year(2010), 7930);
		TimeSeriesCollection dataset2 = new TimeSeriesCollection();
		dataset2.addSeries(series2);

		TimeSeries series3 = new TimeSeries("Hospital Beds/1000 people");
		series3.add(new Year(2018), 2.92);
		series3.add(new Year(2017), 2.87);
		series3.add(new Year(2016), 2.77);
		series3.add(new Year(2015), 2.8);
		series3.add(new Year(2014), 2.83);
		series3.add(new Year(2013), 2.89);
		series3.add(new Year(2012), 2.93);
		series3.add(new Year(2011), 2.97);
		series3.add(new Year(2010), 3.05);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series3);

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

		JFreeChart chart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);

	}


}