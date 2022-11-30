package com.yorku.wbapp.controller.datainteraction;


import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

public class JSONParser {
    public static void main(String argv[])
    {
        FilterCriteria fc = new FilterCriteria("IN", 2018, 2020, "AG.LND.FRST.ZS");
        JSONParser jsonParser = new JSONParser();
        WBData wbData = jsonParser.getParsedData("", fc);
        for (WBDataRecord record: wbData.getRecords()){
            System.out.println("JSONParser COUNTRY: "+record.getCountryId()+ " YEAR: "+record.getYear()+" VALUE: "+record.getAnalysisValue());
        }


    }

    public WBData getParsedData(String inline, FilterCriteria filterCriteria) {

        //Initialise a list that will return the list of all the records that we parse
        List<WBDataRecord> records = new ArrayList<>();

        // PROCESS THE JSON AS ONE LINE
        JsonArray jsonArray = new
                JsonParser().parse(inline).getAsJsonArray();
        int size = jsonArray.size();
        int sizeOfResults = jsonArray.get(1).getAsJsonArray().size();
        int year = 0;
        int populationForYear = 0;
        int cummulativePopulation = 0;

        for (int i = 0; i < sizeOfResults; i++) {
            // GET FOR EACH ENTRY THE YEAR FROM THE “date” FIELD
            year = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("date").getAsInt();
            // CHECK IF THERE IS A VALUE FOR THE POPULATION FOR A
            //  GIVEN YEAR
            if (jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").isJsonNull()){
                populationForYear = 0;
            }else {
                // GET THE POPULATION FOR THE GIVEN YEAR FROM THE
                // “value” FIELD
                //populationForYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsInt();
                String popForYear = jsonArray.get(1).getAsJsonArray().get(i).getAsJsonObject().get("value").getAsString();

                //System.out.println("Population for : " + year + " is " + populationForYear);
                cummulativePopulation = cummulativePopulation + populationForYear;

                //Create a WBDataRecord object called record
                //Pump the parsed values we got from a single element into record. This will create an official data record.
                WBDataRecord record = new WBDataRecord();
                record.setCountryId(filterCriteria.getCountryId());
                record.setAnalysisValue(Double.parseDouble(popForYear));
                record.setYear(year);
                //add this object to our list of data records
                records.add(record);
            }

        }

        return new WBData(records);
    }
}
