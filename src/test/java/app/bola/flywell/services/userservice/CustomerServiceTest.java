package app.bola.flywell.services.userservice;

import app.bola.flywell.dto.response.CustomerResponse;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.exceptions.FailedRegistrationException;
import app.bola.flywell.exceptions.InvalidRequestException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.math.BigInteger;
import java.util.List;

import static app.bola.flywell.utils.Constants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest()
class CustomerServiceTest {
	@Autowired
	CustomerService customerService;
	CustomerResponse customerResponse;
	UpdateRequest updateRequest;
	
	@BeforeEach
	@SneakyThrows
	public void startAllTestWith() {
		customerService.removeAll();
		updateRequest = new UpdateRequest();
		customerResponse = customerService.createNew(
										CustomerRequest.builder()
		                               .phoneNumber("567890234")
		                               .firstName("Alayande")
		                               .lastName("Abdulmalik")
		                               .email("alaabdulmalik03@gmail.com")
		                               .password("Ayanniyi@20")
		                               .build()
		);
	}
	
	@AfterEach
	public void endEachTestWith() {
		customerService.removeAll();
	}
	
	@SneakyThrows
	@Test void testThatPassengerTriesToRegisterWithIncompleteDetails_ExceptionIsThrown(){
		assertThatThrownBy(()-> customerService.createNew(buildIncompletePassenger()))
											.as("")
											.isInstanceOf(NullPointerException.class);
	}
	
	@Test void testThatPassengerTriesToRegisterUsingDetailsWithIncorrectFormat_RegistrationFailedExceptionIsThrown() {
		assertThatThrownBy(() -> customerService.createNew(buildPassengerWithIncorrectFormatDetails()),
				"Invalid Email Format")
				.as("Please enter a valid email format", "")
				.isInstanceOf(FailedRegistrationException.class)
				.hasMessageContaining("Please enter a valid email format");
	}
	
	@SneakyThrows
	@Test void testThatPassengerCanRegisterSuccessfully_IfAllChecksArePassed(){
		customerResponse = customerService.createNew(buildPassenger1());
		assertThat(customerService.getCountOfCustomers()).isNotZero();
		assertThat(customerService.getCountOfCustomers()).isGreaterThan(BigInteger.ZERO.intValue());
		assertThat(customerResponse).isNotNull();
	}
	
	@SneakyThrows
	@Test void testThatOtpIsGenerated_AndSentToTheUserToActivateTheirAccount(){
		System.out.println("Hi onw two");
//		assertThatThrownBy(() -> 10/0)
	}
	
	@Test
	@SneakyThrows
	void testThatAccountActivationIsSuccessful_IfTheOtpEnteredIdCorrect(){
		CustomerResponse response = customerService.activateCustomerAccount(customerResponse.getPublicId(), "");
		assertThat(response).isNotNull();
		assertThat(response.getMessage()).isEqualTo(SUCCESSFUL_ACTIVATION_MESSAGE);
	}
	
	@SneakyThrows
	@Test void testThatPassengerCanUpdateTheirDetails(){
		updateRequest.setFirstName("Alibaba");
		updateRequest.setEmail("alibaba@gmail.com");
		updateRequest.setPhoneNumber("08056472356");
		updateRequest.setUserName("mirah");
		updateRequest.setNewUserName("mithra");
		CustomerResponse updateResponse = customerService.updateDetailsOfRegisteredCustomer(updateRequest);
		assertThat(updateResponse).isNotNull();
		assertThat(updateResponse.getUserBioData().getEmail()).isEqualTo(updateRequest.getEmail());
		CustomerResponse foundPassenger = customerService.findByPublicId(updateRequest.getNewUserName());
		assertThat(foundPassenger).isNotNull();
		assertThat(foundPassenger.getUserBioData().getEmail()).isEqualTo(updateRequest.getEmail());
	}
	private CustomerRequest buildIncompletePassenger() {
		return CustomerRequest.builder().email("theeniolasamuel@gmail.com").firstName("Samuel")
				       .lastName("Eniola").password("coco@22").build();
	}
	
	private CustomerRequest buildPassengerWithIncorrectFormatDetails() {
		return CustomerRequest.builder().password("Obim").email("emailgmail")
				       .lastName("Obinali").firstName("Goodness").phoneNumber("08045673421").build();
	}
	private CustomerRequest buildPassenger1() {
		return CustomerRequest.builder()
				       .password("zainab@64")
				       .lastName("Alayande")
				       .firstName("Zainab")
				       .phoneNumber("08030669508")
				       .email("alayandezainab64@gmail.com")
				       .build();
	}
	private CustomerRequest buildPassenger() {
		return CustomerRequest.builder()
				       .password("ayanniyi@20")
				       .lastName("Alayande")
				       .firstName("Abdulmalik")
				       .phoneNumber("07036174617")
				       .email("alaabdulmalik03@gmail.com")
				       .build();
	}
	
	@SneakyThrows
	@Test void findSavedPassengerWithAUsernameThatDoesNotExist_InvalidRequestExceptionIsThrown(){
		assertThrowsExactly(InvalidRequestException.class, ()-> customerService.findByPublicId("mithra"),
				"Request Failed:: Invalid Username");
	}
	@SneakyThrows
	@Test void findSavedPassengerWithUsername_PassengerWithTheSaidUsernameIsFound(){
		CustomerResponse response = customerService.findByPublicId("mirah");
		assertThat(response).isNotNull();
		assertThat(response).isNotNull();
		assertThat(response).isInstanceOf(CustomerResponse.class);
	}
	
	@Test void testThatWhenTokenHasExpiredAnotherOneIsGenerated(){
	
	}
	
	@Test void testThatUserTriesToLoginWithoutSigningUpLoginFailedExceptionIsThrown(){

	}
	
	@Test void testThatUserTriesToLoginWithoutValidOrIncompleteCredentialsLoginFailedExceptionIsThrown(){

	}
	
	@SneakyThrows
	@DisplayName("Login is successful when all credentials are valid")
	@Test void loginTest(){

	}
	
	@Test void testThatUserTriesToLogInWhenLoginSessionIsStillOn(){
	
	}
	
	@Test
	void findSavedPassengerWithIdThatDoesExist_InvalidRequestExceptionIsThrown(){
		assertThrowsExactly(RuntimeException.class, ()-> customerService.findByPublicId("892ffr0ilj84aas787t274gf7qwerty8"),
				"Request Failed:: Invalid Id");
	}
	
	//todo to fail
	@SneakyThrows
	@Test
	@Disabled
	public void findSavedPassengerWithId_PassengerWithTheSaidIdIsFound(){
		CustomerResponse response = customerService.findByPublicId("");
		assertThat(response).isNotNull();
		assertThat(response).isInstanceOf(CustomerResponse.class);

	}
	@SneakyThrows
	@Test void removePassengerByUserNameTest(){
		customerService.createNew(buildPassenger());
	}
	
	@SneakyThrows
	@Test void findAllPassengersTest(){
		List<CustomerResponse> allPassengersPresent = customerService.findAll();
		allPassengersPresent.forEach(passengerResponse-> assertThat(passengerResponse).isNotNull());
		assertThat(allPassengersPresent.size()).isEqualTo(customerService.getCountOfCustomers());
	}
	
	@SneakyThrows
	@Disabled
	@Test void removePassengerByIdTest(){
		assertEquals(BigInteger.TWO.intValue(), customerService.getCountOfCustomers());
	}
	
}
