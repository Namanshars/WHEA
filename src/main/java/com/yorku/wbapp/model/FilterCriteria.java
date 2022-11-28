package com.yorku.wbapp.model;
//This class holds all the requirements the user gives us
public class FilterCriteria {
    String countryId;
    int fromYear;
    int toYear;
    String indicator;

    public FilterCriteria(String countryId, int fromYear, int toYear, String indicator) {
        this.countryId = countryId;
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.indicator = indicator;

    }

    public String getCountryId() {

        return countryId;
    }

    public int getFromYear() {

        return fromYear;
    }

    public int getToYear() {

        return toYear;
    }

    public String getIndicator() {
        return indicator;
    }
}
