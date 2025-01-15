package app.bola.flywell.services.flightservice;

import app.bola.flywell.dto.response.FlightResponse;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.services.config.TestSetupConfig;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataIntegrityViolationException;

import static java.math.BigInteger.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Import(value = {TestSetupConfig.class})
class FlightServiceTest {

	@Autowired
	TestSetupHelper setupHelper;
	@Autowired
	FlightService flightService;
	FlightResponse savedFlightResponse;

	@BeforeEach
	@SneakyThrows
	public void startEachTestWith(){
		setupHelper.clearFlightDb();
		savedFlightResponse = setupHelper.createFlight();
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
		assertThatThrownBy(() -> setupHelper.createFlight())
				.isInstanceOf(DataIntegrityViolationException.class);

		FlightRequest caseInsensitiveDuplicate = TestDataUtil.buildFlightRequest();
		caseInsensitiveDuplicate.setArrivalCity("LAGOS, NIGERIA");
		caseInsensitiveDuplicate.setDepartureCity("ABUJA, NIGERIA");

		assertThatThrownBy(() -> flightService.createNew(caseInsensitiveDuplicate))
				.isInstanceOf(DataIntegrityViolationException.class);
	}

	@Test
	void createFlightWithEmptyRequiredFields_ExceptionIsThrown(){
		assertThatThrownBy(()->flightService.createNew(TestDataUtil.buildFlightRequest(3L, "China", null)))
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
			FlightRequest request = TestDataUtil.buildFlightRequest();
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
		setupHelper.clearFlightDb();
		assertThat(flightService.getCountOfAllFlights()).isZero();
	}

	@Test
	@SneakyThrows
	void addLargeNumberOfFlights_NoErrors() {
		for (int i = 0; i < 100; i++) {
			FlightRequest request = TestDataUtil.buildFlightRequest();
			request.setArrivalCity("City" + i);
			request.setDepartureCity("Departure" + i);
			flightService.createNew(request);
		}
		assertThat(flightService.getCountOfAllFlights()).isEqualTo(101);
	}


}
