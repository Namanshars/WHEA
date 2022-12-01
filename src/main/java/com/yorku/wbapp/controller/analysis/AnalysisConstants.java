package com.yorku.wbapp.controller.analysis;

public class AnalysisConstants {

    //Declare the name of all analysis here so that they can be passed around whenever needed
    //This helps to reduce the possibilities of spelling errors in our code
    public static final String ANALYSIS_ONE = "CO2 vs EnergyUse vs AirPollution Annual Percent Change";
    public static final String ANALYSIS_TWO = "Forest Area vs Air Pollution Annual Percent Change";
    public static final String ANALYSIS_FOUR = "Forest Area Average";
    public static final String ANALYSIS_THREE = "C02 vs GDP Ratio";
    public static final String ANALYSIS_FIVE = "Government expenditure on education Average";
    public static final String ANALYSIS_SIX = "Current health expenditure vs Hospital Beds Ratio";
    public static final String ANALYSIS_SEVEN = "Problems in accessing health care vs Mortality rate, infant";
    public static final String ANALYSIS_EIGHT = "Mortality rate, infant vs Current health expenditure";


    public static final String CO2_ANALYSIS = "EN.ATM.CO2E.PC";
    public static final String ENERGY_USE_ANALYSIS = "EG.USE.PCAP.KG.OE";
    final public static String PM25_AIR_POLLUTION = "EN.ATM.PM25.MC.M3";
    final public static String FOREST_AREA = "AG.LND.FRST.ZS";
    //
    final public static String GOVT_EXPENDITURE = "SE.XPD.TOTL.GB.ZS";
    final public static String HOSPITAL_BEDS = "SH.MED.BEDS.ZS";

    //todo - below needs to be corrected - using % of GDP for now - need to implement per 1000 people
    final public static String HEALTH_EXP = "SH.XPD.CHEX.GD.ZS";
    final public static String GDP_CAPITA = "NY.GDP.PCAP.CD";

    final public static String PROBLEMS_ACCESSING = "SH.ACS.WHER.Q3.ZS";
    final public static String MORTALITY_INFANTS = "SH.DYN.MORT.Q3";

    public static final String ANNUAL_PERCENT_CHANGE = "ANNUAL_PERCENT_CHANGE";
    public static final String RATIO = "RATIO";
    public static final String AVERAGE = "AVERAGE";
    public static final String NONE = "NONE";


}
