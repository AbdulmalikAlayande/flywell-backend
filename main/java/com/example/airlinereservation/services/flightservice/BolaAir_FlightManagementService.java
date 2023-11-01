package com.example.airlinereservation.services.flightservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
public class BolaAir_FlightManagementService {
		
		public static void main(String[] args) {
			String apiKey = System.getenv("AVIATION_STACK_API_KEY");
			String apiUrl = "http://api.aviationstack.com/v1/flights";
			
			try {
				URL url = new URL(apiUrl + "?access_key=" + apiKey);
				
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				
				int responseCode = connection.getResponseCode();
				if (responseCode == HttpURLConnection.HTTP_OK) {
					Reader reader = new InputStreamReader(connection.getInputStream());
					BufferedReader in = new BufferedReader(reader);
					String inputLine;
					StringBuilder response = new StringBuilder();
					
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
						System.out.println(response);
					}
					in.close();
				} else {
					System.out.println("API Request Failed, Response Code: " + responseCode);
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
}
