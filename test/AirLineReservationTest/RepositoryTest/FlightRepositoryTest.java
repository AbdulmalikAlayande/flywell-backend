package AirLineReservationTest.RepositoryTest;

import data.model.Flight;
import data.repositories.FlightRepository;
import data.repositories.FlightRepositoryImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightRepositoryTest {
	
	private FlightRepository flightRepo;
	private Flight flight;
	
	@BeforeEach public void setUp() {
		flightRepo = new FlightRepositoryImplementation();
		flight = new Flight();
		flightRepo.saveFlight(flight);
	}
	
	@Test void saveFlight_FlightIsSavedAndCountIsIncrementedTest(){
		assertEquals(1, flightRepo.getCountOfAllFlights());
	}
	
	@Test void saveFlightMultipleTimes_CountDoesNotIncrementTest(){
		flightRepo.saveFlight(flight);
		assertEquals(1, flightRepo.getCountOfAllFlights());
	}
	
	@Test void saveFlight_FindFlightByIdTest(){
		Flight foundFlight = flightRepo.findFlightBy(flight.getFlightId());
		assertNotNull(foundFlight);
		assertNotNull(foundFlight.getFlightId());
		assertEquals(foundFlight, flight);
	}
	
	@Test void saveFlight_deleteFlightByIdTest(){
		boolean isDeletedFlight = flightRepo.deleteFlightBy(flight.getFlightId());
		assertTrue(isDeletedFlight);
		assertEquals(0, flightRepo.getCountOfAllFlights());
	}
}