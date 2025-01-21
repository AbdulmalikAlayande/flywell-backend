package app.bola.flywell.services.users;

import app.bola.flywell.dto.response.CustomerResponse;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.dto.response.LoginResponse;
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

	@BeforeEach
	@SneakyThrows
	public void startAllTestWith() {
		customerService.removeAll();
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
	
	@SneakyThrows
	@Test void testThatPassengerTriesToRegisterWithIncompleteDetails_ExceptionIsThrown(){
		assertThatThrownBy(()-> customerService.createNew(buildIncompletePassenger()))
											.isInstanceOf(IllegalArgumentException.class)
											.hasMessageContaining("'phoneNumber' 'password'");
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
		LoginResponse response = customerService.activateCustomerAccount(customerResponse.getPublicId(), "");
		assertThat(response).isNotNull();
		assertThat(response.getAccessToken()).isNotNull().hasSizeGreaterThan(1);
		assertThat(response.getRefreshToken()).isNotNull().hasSizeGreaterThan(1);
	}

	@Test void testThatWhenTokenHasExpiredAnotherOneIsGenerated(){

	}

	@Test void testThatUserTriesToLoginWithoutSigningUpLoginFailedExceptionIsThrown(){

	}

	@Test void testThatUserTriesToLoginWithoutValidOrIncompleteCredentialsLoginFailedExceptionIsThrown(){

	}

	@Test
	void findSavedCustomerWithInvalidPublicId_EntityNotFoundExceptionIsThrown(){
		assertThrowsExactly(RuntimeException.class, ()-> customerService.findByPublicId("892ffr0ilj84aas787t274gf7qwerty8"),
				"Request Failed:: Invalid Id");
	}


	@SneakyThrows
	@Test void findAllCustomersTest(){
		List<CustomerResponse> customers = customerService.findAll();
		customers.forEach(customer -> assertThat(customer).isNotNull());
		assertThat(customers.size()).isEqualTo(customerService.getCountOfCustomers());
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
}
