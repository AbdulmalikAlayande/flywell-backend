package AirLineReservationTest.ServicesTest;

import dtos.Request.PassengerRequest;
import dtos.Request.UpdateRequest;
import dtos.Response.PassengerResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.PassengerService;
import services.PassengerServiceImplementation;

import java.math.BigInteger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PassengerServiceTest {
	
	private PassengerService passengerService;
	private PassengerResponse passengerResponse;
	UpdateRequest updateRequest;
	
	@SneakyThrows
	@BeforeEach void startAllTestWith(){
		passengerService = PassengerServiceImplementation.getInstance();
		updateRequest = new UpdateRequest();
		passengerResponse = passengerService.registerNewPassenger(buildPassenger());
	}
	
	@Test void testThatPassengerCanRegister(){
		assertEquals(BigInteger.valueOf(4).intValue(), passengerService.getCountOfPassengers());
		assertNotNull(passengerResponse);
	}
	
	@Test void testThatPassengerCanUpdateTheirDetails(){
		updateRequest.setUserName(buildPassenger().getUserName());
		updateRequest.setFirstName(buildPassenger().getFirstName());
		PassengerResponse updatedPassenger = passengerService.updateDetailsOfRegisteredPassenger(updateRequest);
		assertNotNull(updatedPassenger);
		assertEquals(1, passengerService.getCountOfPassengers());
	}
	
	@SneakyThrows
	@Test void findPassengerByIdTest(){
		assertNotNull(passengerService.findPassengerById(passengerResponse.getId()));
	}
	
	@SneakyThrows
	@Test void findPassengerByEmailAndPasswordTest(){
		PassengerResponse response = passengerService.findPassengerByEmailAndPassword(buildPassenger().getEmail(), buildPassenger().getPassword());
		assertNotNull(response);
		assertNotNull(response.getUserName());
		assertNotNull(response.getEmail());
	}
	
	@SneakyThrows
	@Test void findUserByUserNameTest(){
		PassengerResponse response = passengerService.findPassengerByUserName(buildPassenger().getUserName());
		assertNotNull(response);
		assertNotNull(response.getUserName());
		assertNotNull(response.getEmail());
	}
	
	@SneakyThrows
	@Test void removePassengerByIdTest(){
		passengerService.removePassengerBId(passengerResponse.getId());
		assertEquals(BigInteger.TWO.intValue(), passengerService.getCountOfPassengers());
	}
	
	@SneakyThrows
	@Test void removePassengerByUserNameTest(){
		boolean isDeleted = passengerService.removePassengerByUserName(buildPassenger().getUserName());
		assertTrue(isDeleted);
	}
	
	@SneakyThrows
	@Test void getAllPassengersTest(){
		List<PassengerResponse> responses = passengerService.getAllPassengers();
		for (int i = 0; i < passengerService.getAllPassengers().size(); i++) {
			assertNotNull(passengerService.getAllPassengers().get(i));
		}
		assertNotNull(responses);
	}
	
	@Test void getAllPassengersBelongingToAParticularFlightTest(){
		
	}
	
	private PassengerRequest buildPassenger() {
		return PassengerRequest.builder()
				       .password("ayanniyi@20")
				       .lastName("Alayande")
				       .firstName("Abdulmalik")
				       .phoneNumber("07036174617")
				       .Email("alaabdulmalik03@gmail.com")
				       .userName("ayanniyi@20")
				       .build();
	}
	private PassengerRequest buildPassenger1() {
		return PassengerRequest.builder()
				       .password("zala@64")
				       .lastName("Alayande")
				       .firstName("Zainab")
				       .phoneNumber("08030669508")
				       .Email("alayandezainab64@gmail.com")
				       .userName("zen@20")
				       .build();
	}
}