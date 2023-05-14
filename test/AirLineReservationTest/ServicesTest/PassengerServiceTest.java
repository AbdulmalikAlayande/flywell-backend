package AirLineReservationTest.ServicesTest;

import dtos.Request.PassengerRequest;
import dtos.Request.UpdateRequest;
import dtos.Response.PassengerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.PassengerService;
import services.PassengerServiceImplementation;

import static org.junit.jupiter.api.Assertions.*;

class PassengerServiceTest {
	
	private PassengerService passengerService;
	private PassengerRequest passengerRequest;
	private PassengerResponse passengerResponse;
	UpdateRequest updateRequest;
	
	@BeforeEach void startAllTestWith(){
		passengerService = PassengerServiceImplementation.getInstance();
		passengerRequest = new PassengerRequest();
		updateRequest = new UpdateRequest();
		passengerRequest.setEmail("alaabdulmalik03@gmail.com");
		passengerRequest.setPhoneNumber("07036174617");
		passengerRequest.setFirstName("Abdulmalik");
		passengerRequest.setLastName("Alayande");
		passengerRequest.setPassword("ayanniyi@20");
		passengerRequest.setUserName("alaabdulmalik03@gmail.comayanniyi20");
		passengerResponse = passengerService.registerNewPassenger(passengerRequest);
	}
	
	@Test void testThatPassengerCanRegister(){
		assertEquals(4, passengerService.getCountOfPassengers());
		assertNotNull(passengerResponse);
	}
	
	@Test void testThatPassengerCanUpdateTheirDetails(){
		updateRequest.setUserName("alaabdulmalik03@gmail.comayanniyi20");
		updateRequest.setFirstName("Bolabo");
		PassengerResponse updatedPassenger = passengerService.updateDetailsOfRegisteredPassenger(updateRequest);
		assertNotNull(updatedPassenger);
		assertEquals(1, passengerService.getCountOfPassengers());
	}
	
	@Test void findPassengerByIdTest(){
	
	}
	
	@Test void findPassengerByEmailAndPasswordTest(){
		PassengerResponse response = passengerService.findPassengerByEmailAndPassword(passengerRequest.getEmail(), passengerRequest.getPassword());
		assertNotNull(response);
		assertNotNull(response.getUserName());
		assertNotNull(response.getEmail());
	}
	
	@Test void findUserByUserNameTest(){
		PassengerResponse response = passengerService.findPassengerByUserName(passengerRequest.getUserName());
		assertNotNull(response);
		assertNotNull(response.getUserName());
		assertNotNull(response.getEmail());
	}
	
	@Test void removePassengerByIdTest(){
//		boolean isDeleted = passengerService.removePassengerBId();
	}
	
	@Test void removePassengerByUserNameTest(){
		boolean isDeleted = passengerService.removePassengerByUserName(passengerRequest.getUserName());
		assertTrue(isDeleted);
	}
}