package com.yorku.wbapp.controller.datainteraction;

import com.yorku.wbapp.controller.analysis.AnalysisStrategy;
import com.yorku.wbapp.model.Countries;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;
import org.json.JSONException;

import java.io.FileNotFoundException;

public interface DataManagerIF {
    public WBData getCountryWBData(FilterCriteria fc);
    public Countries getCountriesList() throws FileNotFoundException, JSONException;

}
