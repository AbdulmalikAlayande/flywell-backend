
package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.UserBioData;
import com.example.airlinereservation.data.model.persons.Admin;
import com.example.airlinereservation.dtos.Request.AdminInvitationRequest;
import com.example.airlinereservation.dtos.Request.CreateAdminRequest;
import com.example.airlinereservation.dtos.Response.AdminInvitationResponse;
import com.example.airlinereservation.dtos.Response.CreateAdminResponse;
import com.example.airlinereservation.services.userservice.AdminService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AdminServiceTest {

	@Autowired
	private AdminService adminService;
	CreateAdminRequest createAdminRequest;
	CreateAdminResponse createAdminResponse;
	
	@BeforeEach
	void setUp() {
	}
	
	@AfterEach
	void tearDown() {
	}
	
	@Test void testThatAdminCanBeInvitedToOurApplication(){
		AdminInvitationRequest invitationRequest = new AdminInvitationRequest();
		AdminInvitationResponse response = adminService.inviteAdmin(invitationRequest);
		
	}
	
	@Test void createAdminTest(){
		Admin admin = new Admin();
		CreateAdminRequest adminRequest = CreateAdminRequest.builder()
				.country("Nigeria")
				.email("rich@gmail.com")
				.state("Lagos")
				.firstName("Malik")
				.lastName("Alhaji")
				.phoneNumber("08081493711")
				.houseNumber("No 2")
				.password("password")
				.postalCode("1234")
				.streetName("Wallstreet")
				.streetNumber("No 2")
				.userName("Farooq")
				.build();
		CreateAdminResponse adminResponse = adminService.createAdmin(adminRequest);
		assertThat(adminResponse.getMessage()).isEqualTo("Admin created successfully");
	}
	
	@Test void testThatAdminTriesToCreateAccountTwiceInvalidRequestExceptionIsThrown(){
	
	}
	
	@Test void testThatEmailIsSentToAdminWhenAdminHasBeenCreated(){
	
	}
	
	@Test void testThatTextMessageIsSentToAdminAfterAdminHasBeenCreated(){
	
	}
	
	@Test void testThatAdminTriesToCreateAccountWithInvalidData_ExceptionIsThrown(){
	
	}
	
	@Test void testFindAdminByUsername_AdminIsFound(){
	
	}
	
	@Test void testThatFindAdminByUsernameWIthInvalidUsername_InvalidRequestExceptionIsThrown(){
	
	}
	
	@Test void testThatAdminCanAddCrewMember(){
	
	}
	
	@Test void testThatAdminCanAssignCrewMember(){
	
	}
	
	@Test void testThatAdminCanAddAirCraftToTheListOfAirCraft(){
		
	}
	
	@Test void BlockUserTest(){
	
	}
}
