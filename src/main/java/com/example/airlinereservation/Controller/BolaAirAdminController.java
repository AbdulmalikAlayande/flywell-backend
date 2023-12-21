package com.example.airlinereservation.Controller;

import com.example.airlinereservation.dtos.Request.AdminInvitationRequest;
import com.example.airlinereservation.dtos.Response.AdminInvitationResponse;
import com.example.airlinereservation.dtos.Response.ApiResponse;
import com.example.airlinereservation.exceptions.InvalidRequestException;
import com.example.airlinereservation.services.userservice.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("bola-air/admin")
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class BolaAirAdminController {
	
	private AdminService adminService;
	
	@PostMapping("/invite-admin/")
	public ApiResponse<?> inviteAdmin(@RequestBody AdminInvitationRequest invitationRequest){
		try {
			ApiResponse<AdminInvitationResponse> apiResponse = new ApiResponse<>();
			AdminInvitationResponse invitationResponse = adminService.inviteAdmin(invitationRequest);
			apiResponse.setData(invitationResponse);
			apiResponse.setSuccessful(HttpStatus.CREATED.is2xxSuccessful());
			apiResponse.setStatusCode(HttpStatus.CREATED.value());
			return apiResponse;
		} catch (InvalidRequestException exception) {
			ApiResponse<String> apiResponse = new ApiResponse<>();
			apiResponse.setData("An Error Occurred"+exception.getMessage());
			apiResponse.setSuccessful(false);
			apiResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return apiResponse;
		}
	}
}
