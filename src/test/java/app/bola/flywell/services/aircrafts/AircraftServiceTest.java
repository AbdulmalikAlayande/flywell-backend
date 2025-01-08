package app.bola.flywell.services.aircrafts;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AircraftServiceTest {
	
	@Autowired
	private AirCraftService airCraftService;
	
	@Test void saveAirCraftTest(){
	
	}
	
	@Test void testThatInvalidRequestExceptionIsThrown_IfThereIsAnAttemptToSaveAirCraftMultipleTimes(){
	
	}
	
	@Test void testThatAnAttemptToSaveAnAirCraftWithIncompleteField_ThrowsAnEmptyFieldException(){
	
	}
	
	@Test void testThatAirCraftIsAddedToBothTheHangerAndTheDatabase(){
	
	}
	
	@Test void testThatAircraftCanBeTrackedByLocation(){
	
	}
	
	@Test void testThatAircraftCanBeAssignedToFlightInstance(){
		
	}
	
	@Test void testThatAircraftIsAssignedToAFlightInstanceByTheLocationAndAvailabilityOfTheAircraft(){
		
	}
	
	@Test void testThatAircraftCanBeTrackedByTransponder(){
		
	}
	
	@Test void addAirCraftHangerTest(){
	
	}
	
	@Test void removeFlightFromFlightListTest(){
	
	}
	
}