package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.enums.Role;
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
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.sql.SQLException;
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
    @Transactional(rollbackFor = {SQLException.class, DataIntegrityViolationException.class, ConstraintViolationException.class})
    public CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest) {
	    Admin admin = new Admin();
        UserBioData mappedBio = mapper.map(createAdminRequest, UserBioData.class);
        UserBioData saveBioData = bioDataRepository.save(mappedBio);
        admin.setBioData(saveBioData);
        admin.setRole(Role.ADMIN);
	    adminRepository.save(admin);
        CreateAdminResponse response = mapper.map(saveBioData, CreateAdminResponse.class);
        response.setMessage("Admin created successfully");
	    return response;
    }


    @Override
    public AdminInvitationResponse inviteAdmin(AdminInvitationRequest invitationRequest) throws InvalidRequestException {
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
                       .mailPath("invitationMail")
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
