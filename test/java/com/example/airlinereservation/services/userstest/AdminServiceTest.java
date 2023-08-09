package com.example.airlinereservation.services.userstest;

import com.example.airlinereservation.dtos.Request.AdminInvitationRequest;
import com.example.airlinereservation.dtos.Response.AdminInvitationResponse;
import com.example.airlinereservation.services.userservice.AdminService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTest {
	
	@Autowired
	private AdminService adminService;
	
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
