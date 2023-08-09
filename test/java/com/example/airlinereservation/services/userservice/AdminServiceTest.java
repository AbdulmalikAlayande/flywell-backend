package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.dtos.Request.CreateAdminRequest;
import com.example.airlinereservation.dtos.Response.CreateAdminResponse;
import com.example.airlinereservation.services.adminServices.AdminService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AdminServiceTest {

	@Autowired
	private AdminService adminService;
	private CreateAdminRequest createAdminRequest;
	private CreateAdminResponse createAdminResponse;
	
	@BeforeEach
	void setUp() {
	}
	
	@AfterEach
	void tearDown() {
	}
	
	@Test void testThatAdminCanBeInvitedToOurApplication(){
	
	}
	
	@Test void createAdminTest(){
	
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
