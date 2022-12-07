package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.Facade;
import com.yorku.wbapp.controller.FacadeIF;
import com.yorku.wbapp.controller.GraphController;
import com.yorku.wbapp.controller.GraphControllerIF;
import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.analysis.AnalysisFactory;
import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.controller.datainteraction.JSONParser;
import com.yorku.wbapp.controller.datainteraction.RestApiClient;
import com.yorku.wbapp.model.Country;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;
import com.yorku.wbapp.view.visualizer.VisualConstants;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestClass {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("before class");
    }
    @Before
    public void setUp() throws Exception {
        System.out.println("before");
    }
    @Test
    public void countryFetcherOne(){
        FacadeIF facade = new Facade();
        Country country = new Country("IN", "India", "IND");
        boolean flag = false;

        if (facade.getNonFetchableCountries().contains(country.getCountryId())){
            flag = true;
        }
        assert !flag;

    }

    @Test
    public void countryFetcherTwo(){
        FacadeIF facade = new Facade();
        Country country = new Country("CA", "Canada", "CAN");
        boolean flag = false;

        if (facade.getNonFetchableCountries().contains(country.getCountryId())){
            flag = true;
        }
        assert !flag;

    }
    @Test
    public void countryFetcherThree(){
        FacadeIF facade = new Facade();
        Country country = new Country("NZ", "New Zealand", "NZL");
        boolean flag = false;

        if (facade.getNonFetchableCountries().contains(country.getCountryId())){
            flag = true;
        }
        assert flag;

    }
    @Test
    public void countryFetcherFour(){
        FacadeIF facade = new Facade();
        Country country = new Country("NO", "Norway", "NOR");
        boolean flag = false;

        if (facade.getNonFetchableCountries().contains(country.getCountryId())){
            flag = true;
        }
        assert flag;

    }

    @Test
    public void countryFetcherFive(){
        FacadeIF facade = new Facade();
        Country country = new Country("NO", "Norway", "NOR");
        boolean flag = false;

        if (facade.getCountries().contains(country.getCountryId())){
            flag = true;
        }
        assert flag;

    }

    @Test
    public void countryFetcherSix(){
        FacadeIF facade = new Facade();
        Country country = new Country("**", "DOES NOT EXIST", "N/A");
        boolean flag = false;

        if (facade.getCountries().contains(country.getCountryId())){
            flag = true;
        }
        assert !flag;

    }

    @Test
    public void analysisFactoryTestOne(){
        String analysis = AnalysisConstants.ANALYSIS_ONE;
        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysis);
        System.out.println(strategy.toString());
        assert(strategy.toString().equals("com.yorku.wbapp.controller.analysis.analysisImplementation.AnalysisOne@6fdb1f78"));

    }

    @Test
    public void analysisFactoryTestTwo(){
        String analysis = AnalysisConstants.ANALYSIS_TWO;
        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysis);
        System.out.println(strategy.toString());
        assert(strategy.toString().equals("com.yorku.wbapp.controller.analysis.analysisImplementation.AnalysisTwo@6fdb1f78"));

    }

    @Test
    public void analysisFactoryTestThree(){
        String analysis = AnalysisConstants.ANALYSIS_THREE;
        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysis);
        System.out.println(strategy.toString());
        assert(strategy.toString().equals("com.yorku.wbapp.controller.analysis.analysisImplementation.AnalysisThree@6fdb1f78"));

    }

    @Test
    public void analysisFactoryTestFour(){
        String analysis = AnalysisConstants.ANALYSIS_FOUR;
        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysis);
        System.out.println(strategy.toString());
        assert(strategy.toString().equals("com.yorku.wbapp.controller.analysis.analysisImplementation.AnalysisFour@6fdb1f78"));

    }
    @Test
    public void analysisFactoryTestFive(){
        String analysis = AnalysisConstants.ANALYSIS_FIVE;
        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysis);
        System.out.println(strategy.toString());
        assert(strategy.toString().equals("com.yorku.wbapp.controller.analysis.analysisImplementation.AnalysisFive@6fdb1f78"));

    }

    @Test
    public void analysisFactoryTestSix(){
        String analysis = AnalysisConstants.ANALYSIS_SIX;
        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysis);
        System.out.println(strategy.toString());
        assert(strategy.toString().equals("com.yorku.wbapp.controller.analysis.analysisImplementation.AnalysisSix@6fdb1f78"));

    }

    @Test
    public void analysisFactoryTestSeven(){
        String analysis = AnalysisConstants.ANALYSIS_SEVEN;
        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysis);
        System.out.println(strategy.toString());
        assert(strategy.toString().equals("com.yorku.wbapp.controller.analysis.analysisImplementation.AnalysisSeven@6fdb1f78"));

    }

    @Test
    public void analysisFactoryTestEight(){
        String analysis = AnalysisConstants.ANALYSIS_EIGHT;
        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysis);
        System.out.println(strategy.toString());
        assert(strategy.toString().equals("com.yorku.wbapp.controller.analysis.analysisImplementation.AnalysisEight@6fdb1f78"));

    }

    @Test
    public void graphTesterAnalysisOne(){
        GraphControllerIF controller = new GraphController();

        List<String> suitableGraphs = new ArrayList<>();

        suitableGraphs.add(VisualConstants.BAR);
        suitableGraphs.add(VisualConstants.LINE);
        suitableGraphs.add(VisualConstants.SCATTER);
        suitableGraphs.add(VisualConstants.REPORT);

        List<String> returnedGraphs = controller.getSuitableGraphs(AnalysisConstants.ANALYSIS_ONE);

        assert(suitableGraphs.equals(returnedGraphs));

    }

    @Test
    public void graphTesterAnalysisTwo(){
        GraphControllerIF controller = new GraphController();

        List<String> suitableGraphs = new ArrayList<>();

        suitableGraphs.add(VisualConstants.BAR);
        suitableGraphs.add(VisualConstants.LINE);
        suitableGraphs.add(VisualConstants.SCATTER);
        suitableGraphs.add(VisualConstants.REPORT);

        List<String> returnedGraphs = controller.getSuitableGraphs(AnalysisConstants.ANALYSIS_TWO);

        assert(suitableGraphs.equals(returnedGraphs));

    }

    @Test
    public void graphTesterAnalysisThree(){
        GraphControllerIF controller = new GraphController();

        List<String> suitableGraphs = new ArrayList<>();

        suitableGraphs.add(VisualConstants.BAR);
        suitableGraphs.add(VisualConstants.LINE);
        suitableGraphs.add(VisualConstants.SCATTER);
        suitableGraphs.add(VisualConstants.REPORT);

        List<String> returnedGraphs = controller.getSuitableGraphs(AnalysisConstants.ANALYSIS_THREE);

        assert(suitableGraphs.equals(returnedGraphs));

    }

    @Test
    public void graphTesterAnalysisFour(){
        GraphControllerIF controller = new GraphController();

        List<String> suitableGraphs = new ArrayList<>();

        suitableGraphs.add(VisualConstants.PIE);
        suitableGraphs.add(VisualConstants.REPORT);

        List<String> returnedGraphs = controller.getSuitableGraphs(AnalysisConstants.ANALYSIS_FOUR);

        assert(suitableGraphs.equals(returnedGraphs));

    }

    @Test
    public void graphTesterAnalysisFive(){
        GraphControllerIF controller = new GraphController();

        List<String> suitableGraphs = new ArrayList<>();

        suitableGraphs.add(VisualConstants.PIE);
        suitableGraphs.add(VisualConstants.REPORT);

        List<String> returnedGraphs = controller.getSuitableGraphs(AnalysisConstants.ANALYSIS_FIVE);

        assert(suitableGraphs.equals(returnedGraphs));

    }

    @Test
    public void graphTesterAnalysisSix(){
        GraphControllerIF controller = new GraphController();

        List<String> suitableGraphs = new ArrayList<>();

        suitableGraphs.add(VisualConstants.BAR);
        suitableGraphs.add(VisualConstants.LINE);
        suitableGraphs.add(VisualConstants.SCATTER);
        suitableGraphs.add(VisualConstants.REPORT);

        List<String> returnedGraphs = controller.getSuitableGraphs(AnalysisConstants.ANALYSIS_SIX);

        assert(suitableGraphs.equals(returnedGraphs));

    }

    @Test
    public void graphTesterAnalysisSeven(){
        GraphControllerIF controller = new GraphController();

        List<String> suitableGraphs = new ArrayList<>();

        suitableGraphs.add(VisualConstants.BAR);
        suitableGraphs.add(VisualConstants.LINE);
        suitableGraphs.add(VisualConstants.SCATTER);
        suitableGraphs.add(VisualConstants.REPORT);

        List<String> returnedGraphs = controller.getSuitableGraphs(AnalysisConstants.ANALYSIS_SEVEN);

        assert(suitableGraphs.equals(returnedGraphs));

    }

    @Test
    public void graphTesterAnalysisEight(){
        GraphControllerIF controller = new GraphController();

        List<String> suitableGraphs = new ArrayList<>();

        suitableGraphs.add(VisualConstants.BAR);
        suitableGraphs.add(VisualConstants.LINE);
        suitableGraphs.add(VisualConstants.SCATTER);
        suitableGraphs.add(VisualConstants.REPORT);

        List<String> returnedGraphs = controller.getSuitableGraphs(AnalysisConstants.ANALYSIS_EIGHT);

        assert(suitableGraphs.equals(returnedGraphs));

    }

    @Test
    public void testParser() throws IOException {
        FilterCriteria fc = new FilterCriteria("IND", 2018, 2020, "AG.LND.FRST.ZS");
        String jsonString = RestApiClient.getWBDataJSON(fc);
        int min = 2017;
        int max = 2020;

        JSONParser jsonParser = new JSONParser();
        WBData wbData = jsonParser.getParsedData(jsonString, fc);
        for (WBDataRecord record: wbData.getRecords()){
            assertEquals(record.getCountryId(), "IND");
            System.out.println("JSONParser COUNTRY: "+record.getCountryId()+ " YEAR: "+record.getYear()+" VALUE: "+record.getAnalysisValue());
            assert(record.getYear() >= min && record.getYear() <= max);
            if (record.getYear() == 2017){
                assert(record.getAnalysisValue() == 24.0014260777145);
            }
            else if (record.getYear() == 2018){
                assert(record.getAnalysisValue() == 24.0910268095884);
            }
            else if (record.getYear() == 2019){
                assert(record.getAnalysisValue() == 24.1806275414622);
            }
            else if (record.getYear() == 2020){
                assert (record.getAnalysisValue() == 24.270228273336);
            }
        }
        System.out.print(jsonString);
    }

    @Test
    public void testAnalysisOne(){
        FilterCriteria filterCriteria = new FilterCriteria("IND", 2013, 2014, null);
        String analysisName = AnalysisConstants.ANALYSIS_ONE;


        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysisName);
        System.out.println(analysisName+" strategy: "+strategy);
        //perform the analysis
        Map<String, WBData> analyzedDataMap = strategy.analyse(filterCriteria);
        WBData co2Data = analyzedDataMap.get(AnalysisConstants.CO2_ANALYSIS);
        WBData energyData = analyzedDataMap.get(AnalysisConstants.ENERGY_USE_ANALYSIS);
        WBData pollutionData = analyzedDataMap.get(AnalysisConstants.PM25_AIR_POLLUTION);

        for (WBDataRecord record : co2Data.getRecords()){
            if (record.getYear() == 2013){
                assert(record.getAnalysisValue() == ((1.53994783935657 - 1.50850825170152) / 1.50850825170152) * 100);
            }
            if (record.getYear() == 2014){
                assert(record.getAnalysisValue() == ((1.65723127096964 - 1.53994783935657) / 1.53994783935657) * 100);
            }
        }
        for (WBDataRecord record : energyData.getRecords()){
            if (record.getYear() == 2013){
                assert(record.getAnalysisValue() == ((605.794037758373 - 599.155619780044) / 599.155619780044) * 100);
            }
            if (record.getYear() == 2014){
                assert(record.getAnalysisValue() == ((636.57183398644 - 605.794037758373) / 605.794037758373) * 100);
            }
        }
        for (WBDataRecord record : pollutionData.getRecords()){
            if (record.getYear() == 2013){
                assert(record.getAnalysisValue() == ((91.8047916056206 - 88.1694405971172) / 88.1694405971172) * 100);
            }
            if (record.getYear() == 2014){
                assert(record.getAnalysisValue() == ((89.6223826850868 - 91.8047916056206) / 91.8047916056206) * 100);
            }
        }
        System.out.println("analyzed map = " + analyzedDataMap);

    }

    @Test
    public void testAnalysisTwo(){
        FilterCriteria filterCriteria = new FilterCriteria("IND", 2013, 2014, null);
        String analysisName = AnalysisConstants.ANALYSIS_TWO;


        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysisName);
        System.out.println(analysisName+" strategy: "+strategy);
        //perform the analysis
        Map<String, WBData> analyzedDataMap = strategy.analyse(filterCriteria);
        WBData forestData = analyzedDataMap.get(AnalysisConstants.FOREST_AREA);
        WBData pollutionData = analyzedDataMap.get(AnalysisConstants.PM25_AIR_POLLUTION);

        for (WBDataRecord record : forestData.getRecords()){
            if (record.getYear() == 2013){
                assert(record.getAnalysisValue() == ((23.6430231502191 - 23.5534224183453) / 23.5534224183453) * 100);
            }
            if (record.getYear() == 2014){
                assert(record.getAnalysisValue() == ((23.732623882093 - 23.6430231502191) / 23.6430231502191) * 100);
            }
        }
        for (WBDataRecord record : pollutionData.getRecords()){
            if (record.getYear() == 2013){
                assert(record.getAnalysisValue() == ((91.8047916056206 - 88.1694405971172) / 88.1694405971172) * 100);
            }
            if (record.getYear() == 2014){
                assert(record.getAnalysisValue() == ((89.6223826850868 - 91.8047916056206) / 91.8047916056206) * 100);
            }
        }
        System.out.println("analyzed map = " + analyzedDataMap);

    }

    @Test
    public void testAnalysisThree(){
        FilterCriteria filterCriteria = new FilterCriteria("IND", 2013, 2014, null);
        String analysisName = AnalysisConstants.ANALYSIS_THREE;


        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysisName);

        //perform the analysis
        Map<String, WBData> analyzedDataMap = strategy.analyse(filterCriteria);
        WBData co2Data = analyzedDataMap.get(AnalysisConstants.CO2_ANALYSIS);
        WBData gdpData = analyzedDataMap.get(AnalysisConstants.GDP_CAPITA);

        for (String key : analyzedDataMap.keySet()){
            WBData wbData = analyzedDataMap.get(key);

            for (WBDataRecord record : wbData.getRecords()){
                if (record.getYear() == 2013){
                    assert(record.getAnalysisValue() == 941.337371077513);
                }
                else if (record.getYear() == 2014){
                    assert(record.getAnalysisValue() == 949.7079070374319);
                }
            }
        }
    }

    @Test
    public void testAnalysisFour(){
        FilterCriteria filterCriteria = new FilterCriteria("IND", 2013, 2014, null);
        String analysisName = AnalysisConstants.ANALYSIS_FOUR;

        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysisName);

        Map<String, WBData> analyzedDataMap = strategy.analyse(filterCriteria);
        WBData forestData = analyzedDataMap.get(AnalysisConstants.FOREST_AREA);

        for (WBDataRecord record : forestData.getRecords()){
            assert(record.getAnalysisValue() == ((23.6430231502191 + 23.732623882093) / 2));
        }
    }
    @Test
    public void testAnalysisFive(){
        FilterCriteria filterCriteria = new FilterCriteria("IND", 2012, 2013, null);
        String analysisName = AnalysisConstants.ANALYSIS_FIVE;

        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysisName);

        Map<String, WBData> analyzedDataMap = strategy.analyse(filterCriteria);
        WBData forestData = analyzedDataMap.get(AnalysisConstants.GOVT_EXPENDITURE);

        for (WBDataRecord record : forestData.getRecords()){
            assert(record.getAnalysisValue() == ((14.0501804351807 + 13.9921197891235) / 2));
        }
    }

    @Test
    public void testAnalysisSix(){
        FilterCriteria filterCriteria = new FilterCriteria("IND", 2013, 2014, null);
        String analysisName = AnalysisConstants.ANALYSIS_SIX;


        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysisName);

        //perform the analysis
        Map<String, WBData> analyzedDataMap = strategy.analyse(filterCriteria);
        WBData healthData = analyzedDataMap.get(AnalysisConstants.HEALTH_EXP);
        WBData hospitalData = analyzedDataMap.get(AnalysisConstants.HOSPITAL_BEDS);

        for (String key : analyzedDataMap.keySet()){
            WBData wbData = analyzedDataMap.get(key);

            for (WBDataRecord record : wbData.getRecords()){
                if (record.getYear() == 2014){
                    assert(record.getAnalysisValue() == (3.61956549 / 0.52));
                }
                else if (record.getYear() == 2013){
                    assert((record.getAnalysisValue() == 3.74944162 / 0.49));
                }
            }
        }
    }
    @Test
    public void testAnalysisSeven(){
        FilterCriteria filterCriteria = new FilterCriteria("IND", 2016, 2016, null);
        String analysisName = AnalysisConstants.ANALYSIS_SEVEN;


        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysisName);
        System.out.println(analysisName+" strategy: "+strategy);
        //perform the analysis
        Map<String, WBData> analyzedDataMap = strategy.analyse(filterCriteria);
        WBData problemAccessingData = analyzedDataMap.get(AnalysisConstants.PROBLEMS_ACCESSING);
        WBData mortalityData = analyzedDataMap.get(AnalysisConstants.MORTALITY_INFANTS);

        for (WBDataRecord record : mortalityData.getRecords()){
            if (record.getYear() == 2016){
                assert(record.getAnalysisValue() == 47);
            }
        }
        for (WBDataRecord record : problemAccessingData.getRecords()){
            if (record.getYear() == 2016){
                assert(record.getAnalysisValue() == 44.4);
            }
        }
        System.out.println("analyzed map = " + analyzedDataMap);

    }
    @Test
    public void testAnalysisEight(){
        FilterCriteria filterCriteria = new FilterCriteria("IND", 2012, 2013, null);
        String analysisName = AnalysisConstants.ANALYSIS_EIGHT;


        AnalysisFactory factory = new AnalysisFactory();
        AnalysisStrategy strategy = factory.loadAnalysis(analysisName);
        System.out.println(analysisName+" strategy: "+strategy);
        //perform the analysis
        Map<String, WBData> analyzedDataMap = strategy.analyse(filterCriteria);
        WBData govtData = analyzedDataMap.get(AnalysisConstants.GOVT_EXPENDITURE);
        WBData healthData = analyzedDataMap.get(AnalysisConstants.HEALTH_EXP);

        for (WBDataRecord record : healthData.getRecords()){
            if (record.getYear() == 2012){
                assert(record.getAnalysisValue() == ((3.32935309 - 3.24634194) / 3.24634194) * 100);
            }
            if (record.getYear() == 2013){
                assert(record.getAnalysisValue() == ((3.74944162 - 3.32935309) / 3.32935309) * 100);
            }
        }
        for (WBDataRecord record : govtData.getRecords()){
            if (record.getYear() == 2012){
                assert(record.getAnalysisValue() == ((13.9921197891235 - 13.5649099349976) / 13.5649099349976) * 100);
            }
            if (record.getYear() == 2013){
                assert(record.getAnalysisValue() == ((14.0501804351807 - 13.9921197891235) / 13.9921197891235) * 100);
            }
        }
        System.out.println("analyzed map = " + analyzedDataMap);

    }

}
