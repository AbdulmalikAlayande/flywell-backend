package com.example.airlinereservation.services;

import com.example.airlinereservation.config.TestConfigurations;
import com.example.airlinereservation.dtos.Request.LoginRequest;
import com.example.airlinereservation.dtos.Request.PassengerRequest;
import com.example.airlinereservation.dtos.Request.UpdateRequest;
import com.example.airlinereservation.dtos.Response.LoginResponse;
import com.example.airlinereservation.dtos.Response.PassengerResponse;
import com.example.airlinereservation.services.passengerservice.PassengerService;
import com.example.airlinereservation.utils.exceptions.FailedRegistrationException;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import com.example.airlinereservation.utils.exceptions.LoginFailedException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.annotation.Validated;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@Validated
@SpringBootTest(classes = {TestConfigurations.class})
class PassengerServiceTest {
	@Autowired
	PassengerService passengerService;
	PassengerResponse passengerResponse;
	UpdateRequest updateRequest;
	
	@BeforeEach
	@SneakyThrows
	public void startAllTestWith() {
		passengerService.removeAll();
		updateRequest = new UpdateRequest();
		passengerService.registerNewCustomer(PassengerRequest
				        .builder().phoneNumber("567890234").firstName("Alayande")
				        .lastName("Amirah").email("ololadeayandunni@gmail.com").userName("mirah")
				        .password("ayandunni#$2008").build());
	}
	
	@AfterEach
	public void endEachTestWith() {
//		passengerService.removeAll();
	}
	
	@SneakyThrows
	@Test void testThatPassengerTriesToRegisterWithIncompleteDetails_ExceptionIsThrown(){
		assertThatThrownBy(()->passengerService
				.registerNewCustomer(buildIncompletePassenger()), "Incomplete Details")
				.as("")
				.isInstanceOf(FailedRegistrationException.class)
				.hasMessageContaining("Incomplete Details");
	}
	
	@SneakyThrows
	@Test void whenPassengerTriesToRegisterTwice_RegistrationFailedExceptionIsThrown() {
		passengerService.registerNewCustomer(buildPassenger());
		assertThatThrownBy(() -> passengerService
				.registerNewCustomer(buildPassenger()), "Seems Like You Already Have An Account With Us")
				.as("Seems Like You Already Have An Account With Us")
				.isInstanceOf(FailedRegistrationException.class)
				.hasMessageContaining("Seems Like You Already Have An Account With Us");
	}
	
	@Test void testThatPassengerTriesToRegisterUsingDetailsWithIncorrectFormat_RegistrationFailedExceptionIsThrown() {
		assertThatThrownBy(() ->passengerService
				.registerNewCustomer(buildPassengerWithIncorrectFormatDetails()), "Invalid Email Format")
				.as("Please enter a valid email format", "")
				.isInstanceOf(FailedRegistrationException.class)
				.hasMessageContaining("Please enter a valid email format");
	}
	
	@SneakyThrows
	@Test void testThatPassengerCanRegisterSuccessfully_IfAllChecksArePassed(){
		passengerResponse = passengerService.registerNewCustomer(buildPassenger1());
		assertThat(passengerService.getCountOfCustomers()).isNotZero();
		assertThat(passengerService.getCountOfCustomers()).isGreaterThan(BigInteger.ZERO.intValue());
		assertThat(passengerResponse).isNotNull();
	}
	
	@SneakyThrows
	@Test void testThatPassengerCanUpdateTheirDetails(){
		updateRequest.setFirstName("Alibaba");
		updateRequest.setEmail("alibaba@gmail.com");
		updateRequest.setPhoneNumber("08056472356");
		updateRequest.setUserName("mirah");
		updateRequest.setNewUserName("mithra");
		PassengerResponse updateResponse = passengerService.updateDetailsOfRegisteredCustomer(updateRequest);
		assertThat(updateResponse).isNotNull();
		assertThat(updateResponse.getEmail()).isEqualTo(updateRequest.getEmail());
		Optional<PassengerResponse> foundPassenger = passengerService.findCustomerByUserName(updateRequest.getNewUserName());
		assertThat(foundPassenger.isPresent()).isTrue();
		foundPassenger.ifPresent(passenger->{
			assertThat(passenger.getUserName()).isEqualTo(updateRequest.getNewUserName());
			assertThat(passenger.getEmail()).isEqualTo(updateRequest.getEmail());
		});
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
		return PassengerRequest.builder().password("zainab@64").lastName("Alayande").firstName("Zainab")
				       .phoneNumber("08030669508").email("alayandezainab64@gmail.com").userName("zen@20").build();
	}
	
	private PassengerRequest buildPassenger() {
		return PassengerRequest
				       .builder().password("ayanniyi@20").lastName("Alayande").firstName("Abdulmalik")
				       .phoneNumber("07036174617").email("alaabdulmalik03@gmail.com").userName("ayanniyi@20").build();
	}
	@SneakyThrows
	@Test void findSavedPassengerWithAUsernameThatDoesNotExist_InvalidRequestExceptionIsThrown(){
		assertThrowsExactly(InvalidRequestException.class, ()->passengerService.findCustomerByUserName("mithra"),
				"Request Failed:: Invalid Username");
	}
		
	@SneakyThrows
	@Test void findSavedPassengerWithUsername_PassengerWithTheSaidUsernameIsFound(){
		Optional<PassengerResponse> response = passengerService.findCustomerByUserName("mirah");
		assertThat(response.isPresent()).isTrue();
		response.ifPresent(passengerResponse -> {
			assertThat(passengerResponse).isNotNull();
			assertThat(passengerResponse).isInstanceOf(PassengerResponse.class);
			assertThat(passengerResponse.getUserName()).isNotEmpty();
		});
	}
	
	@Test void testThatWhenTokenHasExpiredAnotherOneIsGenerated(){
	
	}
	
	@Test void testThatUserTriesToLoginWithoutSigningUpLoginFailedExceptionIsThrown(){
		LoginRequest request = LoginRequest.builder().email("alamala@gmail.com")
				                       .password("alamala@42").username("alamala1").build();
		assertThrows(LoginFailedException.class, ()-> passengerService.login(request), "Login Failed:: You do not have an account with us, Please register to create one");
	}
	
	@Test void testThatUserTriesToLoginWithoutValidOrIncompleteCredentialsLoginFailedExceptionIsThrown(){
		LoginRequest request = LoginRequest.builder()
				                       .username("mirah").email("ololadeayandunni@gmail.com")
				                       .build();
		assertThrows(LoginFailedException.class, ()-> passengerService.login(request), "Login Failed:: Please provide the full details requested in the correct format");
	}

	@SneakyThrows
	@DisplayName("Login is successful when all credentials are valid")
	@Test void loginTest(){
		LoginRequest request = LoginRequest.builder()
				                       .username("mirah").email("ololadeayandunni@gmail.com")
				                       .password("ayandunni#$2008")
				                       .build();
		LoginResponse response = passengerService.login(request);
		assertThat(response.getMessage()).isNotEmpty();
		assertThat(response.getUsername()).isEqualTo(request.getUsername());
	}
	
	@Test void testThatUserTriesToLogInWhenLoginSessionIsStillOn(){
		
	}
	
	@Test
	void findSavedPassengerWithIdThatDoesExist_InvalidRequestExceptionIsThrown(){
		assertThrowsExactly(RuntimeException.class, ()->passengerService.findCustomerById("892ffr0ilj84aas787t274gf7qwerty8"),
				"Request Failed:: Invalid Id");
	}
	//todo to fail
	@SneakyThrows
	@Test
	@Disabled
	public void findSavedPassengerWithId_PassengerWithTheSaidIdIsFound(){
		Optional<PassengerResponse> response = passengerService.findCustomerById("");
		response.ifPresent(passengerResponse -> {
			assertThat(passengerResponse).isNotNull();
			assertThat(passengerResponse).isInstanceOf(PassengerResponse.class);
			assertThat(passengerResponse.getUserName()).isNotEmpty();
		});
	}
	
		@SneakyThrows
	@Test void removePassengerByUserNameTest(){
		passengerService.registerNewCustomer(buildPassenger());
		boolean isDeleted = passengerService.removeCustomerByUserName(buildPassenger().getUserName());
		assertTrue(isDeleted);
	}

	@SneakyThrows
	@Test void getAllPassengersTest(){
		List<PassengerResponse> allPassengersPresent = passengerService.getAllCustomers();
		allPassengersPresent.forEach(passengerResponse->{
			assertThat(passengerResponse).isNotNull();
			assertThat(passengerResponse.getUserName()).isNotNull();
			assertThat(passengerResponse.getUserName()).isNotEmpty();
		});
		assertThat(allPassengersPresent.size()).isEqualTo(passengerService.getCountOfCustomers());
	}
	
	@SneakyThrows
	@Disabled
	@Test void removePassengerByIdTest(){
		passengerService.removeCustomerById(passengerResponse.getId());
		assertEquals(BigInteger.TWO.intValue(), passengerService.getCountOfCustomers());
	}
}

