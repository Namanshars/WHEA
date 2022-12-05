package com.yorku.wbapp.model;

public class Country {
    String countryName;
    String countryId;
    String countryValue;
    String countryISO3Code;
    boolean fetchable;

    public Country(String countryId, String countryValue, String countryISO3Code, boolean fetchable) {
        this.countryId = countryId;
        this.countryValue = countryValue;
        this.countryISO3Code = countryISO3Code;
        this.fetchable = fetchable;
    }
    public Country(String countryValue, String countryId){
        this.countryId = countryId;
        this.countryValue = countryValue;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryValue() {
        return countryValue;
    }

    public void setCountryValue(String countryValue) {
        this.countryValue = countryValue;
    }

    public String getCountryISO3Code() {
        return countryISO3Code;
    }

    public void setCountryISO3Code(String countryISO3Code) {
        this.countryISO3Code = countryISO3Code;
    }

    public boolean isFetchable() {
        return fetchable;
    }

    public void setFetchable(boolean fetchable) {
        this.fetchable = fetchable;
    }
}
