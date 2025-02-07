
package app.bola.flywell.services.users;


import app.bola.flywell.dto.response.AdminInvitationResponse;
import app.bola.flywell.dto.response.AdminResponse;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.exceptions.FieldInvalidException;
import app.bola.flywell.exceptions.EntityNotFoundException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static app.bola.flywell.utils.Constants.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
class AdminServiceTest {

	@Autowired
	private AdminService adminService;
	CreateAdminRequest createAdminRequest;
	AdminResponse adminResponse;
	AdminInvitationResponse response;
	
	@BeforeEach
	@SneakyThrows
	public void setUp() {
		adminService.removeAll();
		createAdminRequest = new CreateAdminRequest();
		adminResponse = new AdminResponse();
		AdminInvitationRequest invitationRequest = new AdminInvitationRequest();
		invitationRequest.setEmail("alaabdulmalik03@gmail.com");
		response = adminService.inviteAdmin(invitationRequest);
	}
	
	@AfterEach
	void tearDown() {
	}
	
	@SneakyThrows
	@Test void testThatAdminCanBeInvitedToOurApplication(){
		assertThat(response.getMessage()).isEqualTo("An Invitation Mail Has Been Sent To alaabdulmalik03@gmail.com");
	}
	
	@Test void testThatBeforeInvitation_AdminEmailMustBeCorrect(){
		AdminInvitationRequest invitationRequest = new AdminInvitationRequest();
		invitationRequest.setEmail("alaabdulmalik03@m");
		assertThatThrownBy(()-> response = adminService.inviteAdmin(invitationRequest)).isInstanceOf(FieldInvalidException.class)
																					   .hasMessageContaining(INVALID_EMAIL_FORMAT);
	}

	@SneakyThrows
	@Test
	public void createNewTest(){
		AdminResponse adminResponse = adminService.createNew(buildAdmin());
		assertThat(adminResponse.getMessage()).isEqualTo("Admin Account created successfully");
	}
	
	@SneakyThrows
	@Test void createNew_WithoutInvitation_ExceptionIsThrown(){
		assertThatThrownBy(()-> adminService.createNew(buildUninvitedAdmin()))
		.isInstanceOf(EntityNotFoundException.class)
		.hasMessageContaining("Admin With Email Ahoy@gmail.com Does Not Exist");
	}
	
	private CreateAdminRequest buildUninvitedAdmin() {
		return CreateAdminRequest.builder()
				       .firstName("Hello")
				       .lastName("Ahoy")
				       .email("Ahoy@gmail.com")
				       .adminCode("23456777")
				       .phoneNumber("07036174617")
				       .password("P@ssword")
				       .build();
	}
	
	@Test void testThatAdminTriesToCreateAccountWithInvalidData_ExceptionIsThrown(){
	
	}
	@Test void testThatAdminTriesToCreateAccountTwiceInvalidRequestExceptionIsThrown(){
	
	}
	@Test void testThatAdminCanAddAirCraftToTheListOfAirCraft(){
	
	}
	public CreateAdminRequest buildAdmin(){
        return CreateAdminRequest.builder()
					             .lastName("Alayande")
		                         .adminCode(response.getCode())
		                         .firstName("Abdulmalik")
		                         .email("alaabdulmalik03@gmail.com")
					             .phoneNumber("08081493711")
		                         .password("P@assword")
		                         .build();
	}
	
	@Test void testThatACrewHasToExistBeforeTheyCanBeAssignedToAFlight(){

	}

	@Test void testThatAdminCanAddCrewMember(){

	}

	
	@Test void testThatAdminCanAssignCrewMember(){
	
	}

	
	@Test void testThatAdminCanAssignCrewMember_AssignedCrewMembersAreNoLongerAvailable(){
	
	}
	
	@Test void testThatAdminTriesToReAssignAnUnavailableCrewMember_InvalidRequestExceptionIsThrown(){
	
	}

}
