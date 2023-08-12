
package com.example.airlinereservation.services.userservice;
import com.example.airlinereservation.data.model.persons.UserBioData;
import com.example.airlinereservation.dtos.Request.AdminInvitationRequest;
import com.example.airlinereservation.dtos.Request.CreateAdminRequest;
import com.example.airlinereservation.dtos.Response.AdminInvitationResponse;
import com.example.airlinereservation.dtos.Response.CreateAdminResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

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

	@SneakyThrows
	@Test void createAdminTest(){
		CreateAdminResponse adminResponse = adminService.createAdmin(buildAdmin());
		assertThat(adminResponse.getMessage()).isEqualTo("Admin created successfully");
	}



	public static CreateAdminRequest buildAdmin(){
        return CreateAdminRequest.builder()
								.country("Nigeria").email("rich@gmail.com").state("Lagos").firstName("Malik")
						        .lastName("Alhaji").phoneNumber("08081493711").houseNumber("No 2")
						        .password("password").postalCode("1234").streetName("Wallstreet")
						        .streetNumber("No 2").userName("Farooq").build();
	}
	
	@Test void testThatAdminTriesToCreateAccountTwiceInvalidRequestExceptionIsThrown(){
	
	}
	
	@Test void testThatEmailIsSentToAdminWhenAdminHasBeenCreated(){
	
	}
	
	@Test void testThatTextMessageIsSentToAdminAfterAdminHasBeenCreated(){
	
	}
	
	@Test void testThatAdminTriesToCreateAccountWithInvalidData_ExceptionIsThrown(){
	
	}

	@SneakyThrows
	@Test void testFindAdminByUsername_AdminIsFound(){
		adminService.createAdmin(buildAdmin());
		Optional<UserBioData> foundAdmin = adminService.findByUsername(buildAdmin().getUserName());
		assertThat(foundAdmin.isPresent()).isTrue();
		foundAdmin.ifPresent(admin->{
			assertThat(admin.getUserName()).isEqualTo(buildAdmin().getUserName());
		});
	}
	
	@Test void testThatFindAdminByUsernameWIthInvalidUsername_InvalidRequestExceptionIsThrown(){
	
	}
	
	@Test void testThatAdminCanAddCrewMember(){
	
	}
	
	@Test void testThatAdminCanAssignCrewMember(){
	
	}
	
	@Test void testThatAdminCanAddAirCraftToTheListOfAirCraft(){
		
	}
	

	
	@Test void testThatAdminBlocksAndAdmin_TheBlockedAdminCannotAccessTheSystemAgain(){
	
	}
	
	@Test void testThatAdminBlocksCustomer_TheBlockedCustomerNoLongerHaveAccessToTheSystem(){
	
	}
	
	@Test void testThatAdminCanBlockCrewMember_BlockedCrewMemberCannotAccessTheSystem(){
	
	}
	
	@Test void testThatAdminCanBlockFrontDeskOfficer_BlockedOfficerNoLongerHasAccessToTheSystem(){
	
	}
	@Test void testThatAdminUnBlocksAndAdmin_TheUnBlockedAdminCanNowHasAccessTheSystemAgain(){
	
	}
	
	@Test void testThatAdminUnBlocksCustomer_TheUnBlockedCustomerNowHasAccessToTheSystem(){
	
	}
	
	@Test void testThatAdminCanUnBlockCrewMember_UnBlockedCrewMemberHasAccessToTheSystem(){
	
	}
	
	@Test void testThatAdminCanUnBlockFrontDeskOfficer_UnBlockedOfficerNowHasAccessToTheSystem(){
	
	}
	
	@Test void testThatAdminCanAssignCrewMember_AssignedCrewMembersAreNoLongerAvailable(){
	
	}
	
	@Test void testThatAdminTriesToReAssignAnUnavailableCrewMember_InvalidRequestExceptionIsThrown(){
	
	}

}
