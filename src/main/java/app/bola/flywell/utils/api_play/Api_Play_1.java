package app.bola.flywell.utils.api_play;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Api_Play_1 {
	private static final String flight_end_point_url = "http://api.aviationstack.com/v1/flights";
	private static final String city_end_point_url = "http://api.aviationstack.com/v1/cities";
	private static final String countries_end_point_url = "http://api.aviationstack.com/v1/countries";
	private static final String airports_end_point_url = "http://api.aviationstack.com/v1/airports";
	private static final String airlines_end_point_url = "http://api.aviationstack.com/v1/airlines";
	
	@Data
	static class AviationStackPagination{
		private long limit;
		private long  offset;
		private long count;
		private long  total;
	}
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	static class GeoNamesResponse {
		private List<City> geonames;
		@JsonIgnoreProperties(ignoreUnknown = true)
		@Data
		static class City {
			private double lng;
			private long geonameId;
			private String countrycode;
			private String name;
			private String  fclName;
			private String toponymName;
			private String fcodeName;
			private String wikipedia;
			private double lat;
			private String fcl;
			private long population;
			private String fcode;
		}
	}
	@Data
	static class AviationStackCities{
		private AviationStackPagination pagination;
		private CityData[] data;
		@Data
		static class CityData {
			private String city_name;
			private String iata_code;
			private String country_iso2;
			private String latitude;
			private String longitude;
			private String timezone;
			private String gmt;
			private String geoname_id;
		}
		
	}
	
	private static void getAviationStackData() {
		String apiKey = System.getenv("AVIATION_STACK_API_KEY");
		long limit = 500;
		long  offset = 0;
		long count = 500;
		long  total = 9370;
		try {
			System.out.println(apiKey);
			BufferedReader in = getBufferedReader(apiKey, limit, offset, count, total);
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			in.close();
			Gson gson = new Gson();
			AviationStackCities responseObject = gson.fromJson(response.toString(), AviationStackCities.class);
			System.out.println(responseObject.pagination.toString());
			Arrays.stream(responseObject.data).toList().forEach(System.out::println);
			
		} catch (IOException e) {
			log.error(":::", e);
		}
	}
	
	private static BufferedReader getBufferedReader(String apiKey, long limit, long offset, long count, long total) throws IOException {
		URL url1 = new URL(" https://ourairports.com/countries/NG/airports.hxl");
		URL url = new URL(String.format("%s?access_key=%s&limit=%s&offset=%s&count=%s",
																	city_end_point_url,
																	apiKey,
																	limit,
																	offset,
																	count));
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		Reader reader = new InputStreamReader(connection.getInputStream());
		return new BufferedReader(reader);
	}
	
	
	public static void getAllLocationsWithAirports() {
		try {
			URL url = new URL("http://api.geonames.org/search?q=&featureClass=A&username=blaqmhee");
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			InputStream responseStream = connection.getInputStream();
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(responseStream);
			
			NodeList nodeList = document.getElementsByTagName("geoname");
			String[] tagNames = {"name", "countryName", "lng", "lat", "geonameId", "fcl", "countryCode", "fcode"};
			for (int index = 0; index < nodeList.getLength(); index++) {
				Element element = (Element) nodeList.item(index);
				for (String tagName : tagNames) {
					System.out.println(tagName + " ==> " + element.getElementsByTagName(tagName).item(0).getTextContent());
				}
				System.out.println("===========================================");
			}
			
		} catch (IOException | ParserConfigurationException | SAXException e) {
			log.error(":::", e);
		}
	}
	
	public static void getAllCitiesWithAirports(){
		try{
			URL url = new URL("http://api.geonames.org/citiesJSON?north=90&south=-90&east=180&west=-180&featureCode=AIRP&username=blaqmhee");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			InputStream inputStream = connection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				response.append(line);
			}
			bufferedReader.close();
			Gson gson = new Gson();
			GeoNamesResponse responseObject = gson.fromJson(response.toString(), GeoNamesResponse.class);
			responseObject.geonames.forEach(System.out::println);
		}catch(IOException exception){
			log.error(":::", exception );
		}
	}
	
	public static void main(String[] args) {
		getAviationStackData();
//		getAllLocationsWithAirports();
//		getAllCitiesWithAirports();
	}
}
