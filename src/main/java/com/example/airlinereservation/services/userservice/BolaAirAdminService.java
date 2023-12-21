package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.persons.Address;
import com.example.airlinereservation.data.model.persons.UserBioData;
import com.example.airlinereservation.data.model.persons.Admin;
import com.example.airlinereservation.data.repositories.AddressRepository;
import com.example.airlinereservation.data.repositories.AdminRepository;
import com.example.airlinereservation.data.repositories.UserBioDataRepository;
import com.example.airlinereservation.dtos.Request.*;
import com.example.airlinereservation.dtos.Response.*;
import com.example.airlinereservation.exceptions.EmptyFieldException;
import com.example.airlinereservation.exceptions.FieldInvalidException;
import com.example.airlinereservation.exceptions.InvalidRequestException;
import com.example.airlinereservation.services.notifications.mail.MailService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BolaAirAdminService implements AdminService{
    
    private AdminRepository adminRepository;
    private UserBioDataRepository bioDataRepository;
    private AddressRepository addressRepository;
    private CrewMemberService crewMemberService;
    private MailService mailService;
    private ModelMapper mapper;

    @Override
    public CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest) {
	    Admin admin = new Admin();
	    Address mappedAddress = mapper.map(createAdminRequest, Address.class);
        Address savedAddress = addressRepository.save(mappedAddress);
        
        UserBioData mappedBio = mapper.map(createAdminRequest, UserBioData.class);
	    mappedBio.setAddress(savedAddress);
        UserBioData saveBioData = bioDataRepository.save(mappedBio);
        admin.setBioData(saveBioData);
	    adminRepository.save(admin);
	    return new CreateAdminResponse("Admin created successfully");
	    
    }


    @Override
    public AdminInvitationResponse inviteAdmin(AdminInvitationRequest invitationRequest) throws InvalidRequestException {
        System.out.println("Admin Email is ==> "+invitationRequest.getAdminEmail());
        ResponseEntity<NotificationResponse> response = mailService.sendAdminInvitationEmail(buildNotificationRequest(invitationRequest));
        return AdminInvitationResponse.builder()
                       .message("An Invitation Mail Has Been Sent To "+invitationRequest.getAdminEmail())
                       .build();
    }
    
    private NotificationRequest buildNotificationRequest(AdminInvitationRequest invitationRequest) {
        return NotificationRequest
                       .builder()
                       .email(invitationRequest.getAdminEmail())
                       .code(generateAdminCode(invitationRequest.getAdminEmail()))
                       .mailPath("adminInvitationMail")
                       .build();
    }
    
    private String generateAdminCode(String adminEmail) {
        String adminCodePrefix = "__BOLA--AIR__";
        String prefixHashCode = String.valueOf(adminCodePrefix.hashCode());
        String adminEmailHashCode = String.valueOf(adminEmail.hashCode());
        return prefixHashCode+adminEmailHashCode;
    }
    
    @Override
    public CreateCrewMemberResponse addCrewMember(CreateCrewMemberRequest createCrewMemberRequest) throws EmptyFieldException, IllegalAccessException, FieldInvalidException {
        return crewMemberService.createCrewMember(createCrewMemberRequest);
    }


    @Override
    public Optional<UserBioData> findByUsername(String userName) {
        return bioDataRepository.findByUserName(userName);
    }
    
    @Override
    public FlightResponse addNewFlight(FlightRequest flightRequest) {
        return null;
    }
    
}
