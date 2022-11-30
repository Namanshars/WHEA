package com.yorku.wbapp.controller.datainteraction;

import com.yorku.wbapp.model.Countries;
import com.yorku.wbapp.model.Country;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//The DataManager calls the RestApiClient to retrieve the necessary data
//Classes can call DataManager instead of having to consistently make api calls using RestApiClient
public class DataManager{
    List<Country> countries = new ArrayList<Country>();
    WBData wbData;
    Country selectCountry;
    FilterCriteria fc;

    public static void main(String[] args) {
        RestApiClient restApiClient = new RestApiClient();
        FilterCriteria fc = new FilterCriteria("India", 2018, 2020, "AG.LND.FRST.ZS");
            System.out.println("In try");
        String countriesJSON = null;
        try {
            countriesJSON = RestApiClient.getWBDataJSON(fc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(countriesJSON);
    }


    public WBData getCountryWBData(FilterCriteria fc){
        WBData wbData = null;
        try {
            String jsonString = RestApiClient.getWBDataJSON(fc);
            JSONParser jsonParser = new JSONParser();
            wbData = jsonParser.getParsedData(jsonString, fc);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return wbData;
    }


    public Countries getCountriesList() {

        //todo: remove below hardcoding and get the ParseXML to parse the above countriesJSON
        Country country1 = new Country("1", "USA", "US", true);
        Country country2 = new Country("2", "NZ", "NZ", true);
        Country country3 = new Country("3", "IND", "IND", true);
        List<Country> countriesList = new ArrayList<>();
        countriesList.add(country1);
        countriesList.add(country2);
        countriesList.add(country3);

        Countries countries = new Countries(countriesList);

        return countries;
    }


}
