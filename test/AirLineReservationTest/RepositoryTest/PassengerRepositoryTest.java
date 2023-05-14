package AirLineReservationTest.RepositoryTest;

import data.model.Passenger;
import data.repositories.PassengerRepository;
import data.repositories.PassengerRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PassengerRepositoryTest {
	
	PassengerRepository passengerRepo;
	Passenger passenger;
	
	@BeforeEach void startWith(){
		passengerRepo = new PassengerRepositoryImpl();
		passenger = new Passenger();
		passenger.setUserName("alabama");
		passenger.setEmail("alaabdulmalik03@gmail.com");
		passenger.setPassword("ayanniyi20");
//		passenger.setFlightId("1234");
		passengerRepo.savePassenger(passenger);
	}
	
	@Test @DisplayName("User Saving Test") void savePassenger_CountOfPassengersIsIncrementedTest(){
		assertEquals(1, passengerRepo.getCountOfPassengers());
	}
	
	@Test @DisplayName("User Saving Test") void savePassengerMultipleTimes_CountOfPassengersDoesNotIncrementTest(){
		passengerRepo.savePassenger(passenger);
		assertEquals(1, passengerRepo.getCountOfPassengers());
	}
	
	@Test @DisplayName("Find By Id Test") void savePassenger_FindByIdTest(){
		Passenger foundPassenger = passengerRepo.getPassengerById(passenger.getId());
		assertEquals(foundPassenger, passenger);
		assertNotNull(foundPassenger.getId());
	}
	
//	@Test @DisplayName("Get All passengers belonging to a particular flight test") void getAllPassengersInPlaneTest(){
//		Passenger passenger1 = new Passenger();
//		List<Passenger> passengers = new ArrayList<>();
//		passengerRepo.savePassenger(passenger1);
//		passenger1.setFlightId("1234");
//		passengers.add(passenger);
//		passengers.add(passenger1);
//		assertEquals(passengers, passengerRepo.getAllPassengersBy("1234"));
//	}
	
	@Test @DisplayName("remove Passenger By Id Test") void savePassenger_DeleteByIdTest(){
		boolean isDeletedPassenger = passengerRepo.removePassenger(passenger.getId());
		assertTrue(isDeletedPassenger);
		assertEquals(0, passengerRepo.getCountOfPassengers());
	}
	
	@Test @DisplayName("remove passenger by user name Test") void savePassenger_DeleteByUserNameTest(){
		boolean isDeletedPassenger = passengerRepo.removePassengerByUserName(passenger.getUserName());
		assertTrue(isDeletedPassenger);
		assertEquals(0, passengerRepo.getCountOfPassengers());
	}
	
	@Test @DisplayName("find by userNameTest") void savePassenger_FindPassengerByUserNameTest(){
		passenger.setUserName("alabama");
		Passenger foundPassenger = passengerRepo.findPassengerByUserName(passenger.getUserName());
		assertNotNull(foundPassenger);
		assertEquals(foundPassenger, passenger);
	}
	@Test @DisplayName("find by userNameTest") void savePassenger_FindPassengerByEmailAndPasswordTest(){
		Passenger foundPassenger = passengerRepo.findPassengerByUserName(passenger.getUserName());
		assertNotNull(foundPassenger);
		assertEquals(foundPassenger, passenger);
	}
}