package com.yorku.wbapp.model;


import java.io.InputStream;

//Class is used to define a single record of data
//A record consists of countryID, indicator, year, indicator value
public class WBDataRecord implements Comparable<WBDataRecord>{

    String countryId;
    String indicator;
    int year;
    double analysisValue;

    public WBDataRecord() {
    }

    public WBDataRecord(String countryId, String indicator, int year, double analysisValue) {
        this.countryId = countryId;
        this.indicator = indicator;
        this.year = year;
        this.analysisValue = analysisValue;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAnalysisValue() {
        return analysisValue;
    }

    public void setAnalysisValue(double analysisValue) {
        this.analysisValue = analysisValue;
    }

    @Override
    public String toString() {
        return "WBDataRecord{" +
                "countryId='" + countryId + '\'' +
                ", indicator=" + indicator +
                ", year=" + year +
                ", analysisValue=" + analysisValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WBDataRecord that = (WBDataRecord) o;
        return year == that.year;
    }


    @Override
    public int compareTo(WBDataRecord o) {
        return Integer.compare(getYear(), o.getYear());
    }

    public String toString(InputStream countriesFile, String s) {
        return s;
    }
}
