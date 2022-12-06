package com.yorku.wbapp.controller.analysis.analysisImplementation;

import com.yorku.wbapp.controller.analysis.AnalysisConstants;
import com.yorku.wbapp.controller.analysis.AnalysisFactory;
import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.controller.datainteraction.JSONParser;
import com.yorku.wbapp.controller.datainteraction.RestApiClient;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
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

    /*
    @Test
    public void testFindMax(){
        System.out.println("test case find max");
        assertEquals(4, Calculation.findMax(new int[]{1,3,4,2}));
        assertEquals(-2,Calculation.findMax(new int[]{-12,-3,-4,-2}));
    }
    @Test
    public void testCube(){
        System.out.println("test case cube");
        assertEquals(27,Calculation.cube(3));
    }
    @Test
    public void testReverseWord(){
        System.out.println("test case reverse word");
        assertEquals("ym eman si nahk",Calculation.reverseWord("my name is khan"));
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("after class");
    }

     */

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
