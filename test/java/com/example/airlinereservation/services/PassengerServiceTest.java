package com.example.airlinereservation.services;

import com.example.airlinereservation.dtos.Request.PassengerRequest;
import com.example.airlinereservation.dtos.Request.UpdateRequest;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import com.example.airlinereservation.utils.exceptions.FailedRegistrationException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SpringBootTest
class PassengerServiceTest {
	
	@Autowired
	PassengerService passengerService;
	private PassengerResponse passengerResponse;
	UpdateRequest updateRequest;
	
	@BeforeEach
	public void startAllTestWith(){
		updateRequest = new UpdateRequest();
	}
	
	@SneakyThrows
	@Test void testThatPassengerTriesToRegisterWithIncompleteDetails_ExceptionIsThrown(){
		assertThatThrownBy(()->passengerService
				.registerNewPassenger(buildIncompletePassenger()), "Incomplete Details")
				.as("")
				.isInstanceOf(FailedRegistrationException.class).hasMessageContaining("Incomplete Details");
	}
	
	@SneakyThrows
	@Test void whenPassengerTriesToRegisterTwice_RegistrationFailedExceptionIsThrown() {
		passengerService.registerNewPassenger(buildPassenger());
		assertThatThrownBy(() -> passengerService
				                         .registerNewPassenger(buildPassenger()), "Seems Like You Already Have An Account With Us")
				.as("Seems Like You Already Have An Account With Us")
				.isInstanceOf(FailedRegistrationException.class).hasMessageContaining("Seems Like You Already Have An Account With Us");
		
	}
	
	@Test void testThatPassengerTriesToRegisterUsingDetailsWithIncorrectFormat_RegistrationFailedExceptionIsThrown(){
		assertThrowsExactly(FailedRegistrationException.class,
				()-> passengerService.registerNewPassenger(buildPassengerWithIncorrectFormatDetails()), "Please enter a valid email format");
	}
	
	@SneakyThrows
	@Test void testThatPassengerCanRegisterSuccessfully_IfAllChecksArePassed(){
		passengerResponse = passengerService.registerNewPassenger(buildPassenger1());
		assertThat(passengerService.getCountOfPassengers()).isNotZero();
		assertThat(passengerService.getCountOfPassengers()).isGreaterThan(BigInteger.ZERO.intValue());
		assertThat(passengerResponse).isNotNull();
		passengerService.removePassengerByUserName("zen@20");
	}
	
	private PassengerRequest buildIncompletePassenger() {
		return PassengerRequest.builder().Email("theeniolasamuel@gmail.com").firstName("Samuel")
				       .lastName("Eniola").userName("cocolate").password("coco@22").build();
	}
	
	private PassengerRequest buildPassengerWithIncorrectFormatDetails() {
		return PassengerRequest.builder().password("Obim").userName("Obinali G").Email("emailgmail")
				               .lastName("Obinali").firstName("Goodness").phoneNumber("08045673421").build();
	}
	
//	@Test void testThatPassengerCanUpdateTheirDetails(){
//		updateRequest.setUserName(buildPassenger().getUserName());
//		updateRequest.setFirstName(buildPassenger().getFirstName());
//		PassengerResponse updatedPassenger = passengerService.updateDetailsOfRegisteredPassenger(updateRequest);
//		assertNotNull(updatedPassenger);
//		assertEquals(1, passengerService.getCountOfPassengers());
//	}
//
//	@SneakyThrows
//	@Test void findPassengerByIdTest(){
//		assertNotNull(passengerService.findPassengerById(passengerResponse.getId()));
//	}
//
//	@SneakyThrows
//	@Test void findPassengerByEmailAndPasswordTest(){
//		passengerResponse = passengerService.registerNewPassenger(buildPassenger());
//		Optional<PassengerResponse> response = passengerService.findPassengerByEmailAndPassword(buildPassenger().getEmail(), buildPassenger().getPassword());
//		assertNotNull(response);
//		assertNotNull(response.get().getUserName());
//		assertNotNull(response.get().getEmail());
//	}
//
//	@SneakyThrows
//	@Test void findUserByUserNameTest(){
//		passengerResponse = passengerService.registerNewPassenger(buildPassenger());
//		Optional<PassengerResponse> response = passengerService.findPassengerByUserName(buildPassenger().getUserName());
//		assertNotNull(response);
//		assertNotNull(response.get().getUserName());
//		assertNotNull(response.get().getEmail());
//	}
//
//	@SneakyThrows
//	@Test void removePassengerByIdTest(){
//		passengerService.removePassengerBId(passengerResponse.getId());
//		assertEquals(BigInteger.TWO.intValue(), passengerService.getCountOfPassengers());
//	}
//
//	@SneakyThrows
//	@Test void removePassengerByUserNameTest(){
//		boolean isDeleted = passengerService.removePassengerByUserName(buildPassenger().getUserName());
//		assertTrue(isDeleted);
//	}
//
//	@SneakyThrows
//	@Test void getAllPassengersTest(){
//		List<PassengerResponse> responses = passengerService.getAllPassengers();
//		for (int i = 0; i < passengerService.getAllPassengers().size(); i++) {
//			assertNotNull(passengerService.getAllPassengers().get(i));
//		}
//		assertNotNull(responses);
//	}
//
//	@Test void getAllPassengersBelongingToAParticularFlightTest(){
//
//	}
//
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