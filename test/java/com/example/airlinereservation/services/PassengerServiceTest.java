package com.example.airlinereservation.services;

import com.example.airlinereservation.config.TestConfigurations;
import com.example.airlinereservation.dtos.Request.PassengerRequest;
import com.example.airlinereservation.dtos.Request.UpdateRequest;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import com.example.airlinereservation.utils.exceptions.FailedRegistrationException;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestConfigurations.class})
class PassengerServiceTest {
	@Autowired
	PassengerService passengerService;
	PassengerResponse passengerResponse;
	
	@Nested
	class DataSavingAndPersistenceTest{
		UpdateRequest updateRequest;
		@BeforeEach
		public void startAllTestWith() {
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
			assertThatThrownBy(() -> passengerService
					                         .registerNewPassenger(buildPassenger()), "Seems Like You Already Have An Account With Us")
					.as("Seems Like You Already Have An Account With Us")
					.isInstanceOf(FailedRegistrationException.class).hasMessageContaining("Seems Like You Already Have An Account With Us");
			
		}
		
		@Test void testThatPassengerTriesToRegisterUsingDetailsWithIncorrectFormat_RegistrationFailedExceptionIsThrown() throws FailedRegistrationException, NoSuchFieldException {
			assertThatThrownBy(() ->passengerService.registerNewPassenger(buildPassengerWithIncorrectFormatDetails()), "Invalid Email Format")
					.as("Please enter a valid email format", "")
					.isInstanceOf(FailedRegistrationException.class)
					.hasMessageContaining("Please enter a valid email format");
		}
		
		@SneakyThrows
		@Test void testThatPassengerCanRegisterSuccessfully_IfAllChecksArePassed(){
			passengerResponse = passengerService.registerNewPassenger(buildPassenger1());
			assertThat(passengerService.getCountOfPassengers()).isNotZero();
			assertThat(passengerService.getCountOfPassengers()).isGreaterThan(BigInteger.ZERO.intValue());
			assertThat(passengerResponse).isNotNull();
		}
		
		@Test void testThatPassengerCanUpdateTheirDetails(){
		updateRequest.setUserName(buildPassenger1().getUserName());
		updateRequest.setFirstName("Micheal");
		PassengerResponse updatedPassenger = passengerService.updateDetailsOfRegisteredPassenger(updateRequest);
		assertNotNull(updatedPassenger);
		assertEquals(1, passengerService.getCountOfPassengers());
		}
		
		@AfterEach
		public void afterAllTest(){
			passengerService.removeAll();
		}
		
		private PassengerRequest buildIncompletePassenger() {
			return PassengerRequest.builder().email("theeniolasamuel@gmail.com").firstName("Samuel")
					       .lastName("Eniola").userName("cocolate").password("coco@22").build();
		}
		
		private PassengerRequest buildPassengerWithIncorrectFormatDetails() {
			return PassengerRequest.builder().password("Obim").userName("Obinali G").email("emailgmail")
					       .lastName("Obinali").firstName("Goodness").phoneNumber("08045673421").build();
		}
		private PassengerRequest buildPassenger1() {
			return PassengerRequest.builder().password("zala@64").lastName("Alayande").firstName("Zainab")
					       .phoneNumber("08030669508").email("alayandezainab64@gmail.com").userName("zen@20").build();
		}
		private PassengerRequest buildPassenger() {
			return PassengerRequest
					       .builder().password("ayanniyi@20").lastName("Alayande").firstName("Abdulmalik")
					       .phoneNumber("07036174617").email("alaabdulmalik03@gmail.com").userName("ayanniyi@20").build();
		}
	}
	@Nested class DataRetrievalTest {
		
		static PassengerService passengerService;
		@SneakyThrows
		@BeforeAll static void startAllDataRetrievalTestWith(){
			passengerService = new PassengerServiceImplementation();
			passengerService.registerNewPassenger(PassengerRequest
					        .builder().phoneNumber("567890234").firstName("Alayande")
					        .lastName("Amirah").email("ololadeayandunni@gmail.com").userName("mirah")
					        .password("ayandunni#$2008").build());
		}
		
		@SneakyThrows
		@Test void findSavedPassengerWithAUsernameThatDoesNotExist_InvalidRequestExceptionIsThrown(){
			assertThrowsExactly(InvalidRequestException.class, ()->passengerService.findPassengerByUserName("mithra"),
					"Request Failed:: Invalid Username");
		}
		
		@SneakyThrows
		@Test void findSavedPassengerWithUsername_PassengerWithTheSaidUsernameIsFound(){
			Optional<PassengerResponse> response = passengerService.findPassengerByUserName("mirah");
			response.ifPresent(passengerResponse -> {
				assertThat(passengerResponse).isNotNull();
				assertThat(passengerResponse).isInstanceOf(PassengerResponse.class);
				assertThat(passengerResponse.getUserName()).isNotEmpty(); 
			});
		}
		
		@Test
		void findSavedPassengerWithIdThatDoesExist_InvalidRequestExceptionIsThrown(){
			assertThrowsExactly(InvalidRequestException.class, ()->passengerService.findPassengerById("892ffr0ilj84aas787t274gf7qsfqwe8"),
					"Request Failed:: Invalid Id");
		}
		    //todo to fail
		@SneakyThrows
		@Test
		public void findSavedPassengerWithId_PassengerWithTheSaidIdIsFound(){
			Optional<PassengerResponse> response = passengerService.findPassengerById("");
			response.ifPresent(passengerResponse -> {
				assertThat(passengerResponse).isNotNull();
				assertThat(passengerResponse).isInstanceOf(PassengerResponse.class);
				assertThat(passengerResponse.getUserName()).isNotEmpty();
			});
		}
	}
	

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
}