package com.yorku.wbapp;

import com.yorku.wbapp.controller.Facade;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;

public class FrontendGUIDummy {


    public static void main(String[] args) {
        Facade facade = new Facade();

        //get the countries to be displayed in country dropdown
        //Countries countries = frontEndManager.getCountries();

        //get the user selection for country and the date range
        FilterCriteria filterCriteria = new FilterCriteria("USA",  2000, 2022, "AG.LND.FRST.ZS");

        //get the filtered countries list
        WBData filteredWBData = facade.getFilterCountryData(filterCriteria);

    }
}
