package com.yorku.wbapp.controller.datainteraction;

import com.yorku.wbapp.model.*;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
//The DataManager calls the RestApiClient to retrieve the necessary data
//Classes can call DataManager instead of having to consistently make api calls using RestApiClient
public class DataManager implements DataManagerIF{
    List<Country> countries = new ArrayList<Country>();
    WBData wbData;
    Country selectCountry;
    FilterCriteria fc;

    private static DataManager instance;
    private WBDataRecord wbDataRecord;

    private DataManager() {

    }

    public static DataManager getInstance(){
        if (instance == null){
            synchronized (DataManager.class){
                if (instance == null){
                    instance = new DataManager();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args){
        RestApiClient restApiClient = new RestApiClient();
        FilterCriteria fc = new FilterCriteria("IND", 2018, 2020, "AG.LND.FRST.ZS");
            System.out.println("In try");
        String countriesJSON = null;
        try {
            countriesJSON = RestApiClient.getWBDataJSON(fc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(countriesJSON);
        DataManager test = DataManager.getInstance();
        System.out.println("full list" + test.getCountriesList());
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

        /*
        //todo: remove below hardcoding and get the ParseXML to parse the above countriesJSON
        Country country1 = new Country("1", "USA", "US", true);
        Country country2 = new Country("2", "NZ", "NZ", true);
        Country country3 = new Country("3", "IND", "IND", true);
        List<Country> countriesList = new ArrayList<>();
        countriesList.add(country1);
        countriesList.add(country2);
        countriesList.add(country3);

         */

        List<Country> countriesList = new ArrayList<>();


        InputStream countriesFile = this.getClass().getClassLoader().getResourceAsStream("Countries.json");
        System.out.println("countriesFile: "+countriesFile);

        String jsonText = null;
        try {
            jsonText = IOUtils.toString(countriesFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject countriesJSON = null;
        try {
            countriesJSON = new JSONObject(jsonText.trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray geodata = null;
        try {
            geodata = countriesJSON.getJSONArray("countries");
            int n = geodata.length();
            for (int i = 0; i < n; ++i) {
                System.out.println("IN LOOP");
                JSONObject country = geodata.getJSONObject(i);
                System.out.println(country.getString("code")+": "+country.getString("name"));
                Country parsedCountry = new Country(country.getString("name"), country.getString("code"));
                countriesList.add(parsedCountry);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Countries countries = new Countries(countriesList);

        System.out.println("" + countries);

        return countries;
    }

    public Countries getNonFetchableCountriesList(){
        List<Country> countriesList = new ArrayList<>();


        InputStream countriesFile = this.getClass().getClassLoader().getResourceAsStream("NonFetchableCountries.json");
        System.out.println("countriesFile: "+countriesFile);

        String jsonText = null;
        try {
            jsonText = IOUtils.toString(countriesFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject countriesJSON = null;
        try {
            countriesJSON = new JSONObject(jsonText.trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray geodata = null;
        try {
            geodata = countriesJSON.getJSONArray("NonCountries");
            int n = geodata.length();
            for (int i = 0; i < n; ++i) {
                System.out.println("IN LOOP");
                JSONObject country = geodata.getJSONObject(i);
                System.out.println(country.getString("code")+": "+country.getString("name"));
                Country parsedCountry = new Country(country.getString("name"), country.getString("code"));
                countriesList.add(parsedCountry);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Countries countries = new Countries(countriesList);

        System.out.println("" + countries);

        return countries;
    }



}
