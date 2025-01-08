package app.bola.flywell.services.flightservice;

import app.bola.flywell.dto.response.FlightResponse;
import app.bola.flywell.dto.request.*;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static java.math.BigInteger.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class FlightServiceTest {
	
	@Autowired
	private FlightService flightService;
	FlightResponse savedFlightResponse;

	@BeforeEach
	@SneakyThrows
	public void startEachTestWith(){
		flightService.removeAll();
		savedFlightResponse = flightService.createNew(buildFlightRequest());
	}
	
	@Test
	@SneakyThrows
	void createNewFlight_NewFlightIsAddedToAirlinesListOfFlightsTest(){
		assertThat(flightService.getCountOfAllFlights()).isGreaterThan(ZERO.longValue());
		assertThat(savedFlightResponse).isNotNull();
	}

	@Test
	@SneakyThrows
	void addDuplicateFlightByArrivalAndDepartureCities_ShouldThrowInvalidRequestException() {
		assertThatThrownBy(() -> flightService.createNew(buildFlightRequest()))
				.isInstanceOf(DataIntegrityViolationException.class);

		FlightRequest caseInsensitiveDuplicate = buildFlightRequest();
		caseInsensitiveDuplicate.setArrivalCity("LAGOS, NIGERIA");
		caseInsensitiveDuplicate.setDepartureCity("ABUJA, NIGERIA");

		assertThatThrownBy(() -> flightService.createNew(caseInsensitiveDuplicate))
				.isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	void createFlightWithEmptyRequiredFields_ExceptionIsThrown(){
		assertThatThrownBy(()->flightService.createNew(buildIncompleteFlight()))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Field Cannot Be Null Or Empty");
	}
	
	@Test
	@SneakyThrows
	void createNewFlight_getCreatedFlightByRoute(){
		FlightResponse foundFlight = flightService.findFlightByRoute("Lagos, Nigeria", "Abuja, Nigeria");
		System.out.println(foundFlight);
		assertThat(foundFlight).isNotNull();
		assertThat(foundFlight).hasNoNullFieldsOrPropertiesExcept("message", "displayImage");
	}

	@Test
	@SneakyThrows
	void addFlightWithInvalidCities_ThrowsException() {

		assertThatThrownBy(() -> {
			FlightRequest request = buildFlightRequest();
			request.setArrivalCity("");
			request.setDepartureCity(null);
			flightService.createNew(request);
		})
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("Field Cannot Be Null Or Empty");
	}

	@Test
	@SneakyThrows
	void findFlightWhenDatabaseIsEmpty_ReturnsFlightWithNullFields() {
		flightService.removeAll();
		FlightResponse flight = flightService.findFlightByRoute("InvalidCity", "AnotherInvalidCity");
		assertThat(flight).isNotNull();
		assertThat(flight).hasAllNullFieldsOrPropertiesExcept("message");
		assertThat(flight.getMessage()).isEqualTo("Flight Not Found");
	}


	@Test
	@SneakyThrows
	void findFlightCaseInsensitive_ReturnsCorrectFlight() {
		String arrivalCityUpper = "LAGOS, NIGERIA";
		String departureCityLower = "abuja, nigeria";
		FlightResponse response = flightService.findFlightByRoute(arrivalCityUpper, departureCityLower);
		assertThat(response).isNotNull();
		assertThat(response.getArrivalAirportName()).isEqualTo("Murtala Muhammed Airport");
	}

	@Test
	@SneakyThrows
	void removeAllFlights_DatabaseIsCleared() {
		flightService.removeAll();
		assertThat(flightService.getCountOfAllFlights()).isZero();
	}

	@Test
	@SneakyThrows
	void addLargeNumberOfFlights_NoErrors() {
		for (int i = 0; i < 100; i++) {
			FlightRequest request = buildFlightRequest();
			request.setArrivalCity("City" + i);
			request.setDepartureCity("Departure" + i);
			flightService.createNew(request);
		}
		assertThat(flightService.getCountOfAllFlights()).isEqualTo(101);
	}


	private FlightRequest buildIncompleteFlight() {
		return FlightRequest.builder()
				       .duration(3L)
				       .arrivalCity("China")
				       .build();
	}

	private FlightRequest buildFlightRequest() {
		return FlightRequest.builder()
				       .duration(3L)
				       .arrivalCity("Lagos, Nigeria")
				       .departureCity("Abuja, Nigeria")
				       .destinationAirport(buildAirportRequest("Murtala Muhammed Airport", "Nigeria", "23456", "12345"))
				       .departureAirport(buildAirportRequest("Nnamdi Azikwe International Airport", "Nigeria", "45632", "12345"))
				       .build();
	}
	
	public AirportRequest buildAirportRequest(String name, String country, String icaoCode, String iataCode){
		return AirportRequest.builder()
				       .name(name)
				       .countryName(country)
				       .icaoCode(icaoCode)
				       .iataCode(iataCode)
				       .longitude(-34567)
				       .latitude(45678)
				       .build();
	}
}
