package com.yorku.wbapp.controller.datainteraction;

import com.yorku.wbapp.model.FilterCriteria;
import com.yorku.wbapp.model.WBData;
import com.yorku.wbapp.model.WBDataRecord;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class RestApiClient {

	public static void main(String[] args) throws IOException{
		
		//Scanner scanner = new Scanner(System.in);
		FilterCriteria fc = new FilterCriteria("all", 2018, 2020, "AG.LND.FRST.ZS");
		
		//String jsonString = getCountryData(fc);
		String jsonString = RestApiClient.getWBDataJSON(fc);
		JSONParser jsonParser = new JSONParser();
		WBData wbData = jsonParser.getParsedData(jsonString, fc);
		for (WBDataRecord record: wbData.getRecords()){
			System.out.println("JSONParser COUNTRY: "+record.getCountryId()+ " YEAR: "+record.getYear()+" VALUE: "+record.getAnalysisValue());
		}

		System.out.print(jsonString);
		//scanner.close();
		
	}

	//Here is where we will return specific data based on the filter criteria
	public static String getWBDataJSON(FilterCriteria fc) throws IOException{
		//establish arbitrary values for the user selections
		String country = fc.getCountryId();
		//We have to call all records starting from the year prior to the startYear the user enters.
		//This is because many analysis require the record of the year prior to the record we are analysing. Therefore, when we are analysing the first year, we need its prior record in order to analyse properly
		int fromYear = fc.getFromYear() - 1;
		int toYear = fc.getToYear();
		System.out.println("RestAPIClient JSON country = "+country +" year: "+fromYear+" - "+toYear);

		String startingYear = Integer.toString(fromYear);
		String endingYear = Integer.toString(toYear);
		String analysis = fc.getIndicator();

		// Initiating an API call using HttpURLConnection library to establish a new connection
		String url = "https://api.worldbank.org/v2/country/" + country + "/indicator/" + analysis + "?date="+startingYear+":"+endingYear+"&format=json";
		//String urlS = "http://api.worldbank.org/v2/country/%s/indicator/SP.POP.TOTL?date=2000:2001&format=json", "can");

		System.out.println("URL called = "+url);
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("GET");

		// Ensuring we received the expected response rather than some error.
		// Some examples of error would be response 401, 404 and so on
		int responseCode = connection.getResponseCode();
		// Iterating over the data and storing it as a string
		// To properly perform analysis we'll need to use JSON Object here instead
		if(responseCode == 200){
			String response = "";
			Scanner scanner = new Scanner(connection.getInputStream());
			while(scanner.hasNextLine()){
				response += scanner.nextLine();
				response += "\n";
			}
			scanner.close();

			return response;
		}

		// an error happened
		return null;
	}


	//This is the method that will return all the possible data
	//Would parameters be necessary for this method
	public static String getAllCountryData() throws IOException{
		// Initiating an API call using HttpURLConnection library to establish a new connection
		// Only initiated for one type of data for now
		HttpURLConnection connection = (HttpURLConnection) new URL("http://api.worldbank.org/v2/country/all/indicator/SP.POP.TOTL?date=2010:2020").openConnection();
		connection.setRequestMethod("GET");

		// Ensuring we received the expected response rather than some error.
		// Some examples of error would be response 401, 404 and so on
		int responseCode = connection.getResponseCode();
		// Iterating over the data and storing it as a string
		// To properly perform analysis we'll need to use JSON Object here instead
		if(responseCode == 200){
			String response = "";
			Scanner scanner = new Scanner(connection.getInputStream());
			while(scanner.hasNextLine()){
				response += scanner.nextLine();
				response += "\n";
			}
			scanner.close();

			return response;
		}

		// an error happened
		return null;
	}

}