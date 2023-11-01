package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.persons.Address;
import com.example.airlinereservation.data.model.persons.UserBioData;
import com.example.airlinereservation.data.model.persons.Admin;
import com.example.airlinereservation.data.repositories.AddressRepository;
import com.example.airlinereservation.data.repositories.AdminRepository;
import com.example.airlinereservation.data.repositories.UserBioDataRepository;
import com.example.airlinereservation.dtos.Request.AdminInvitationRequest;
import com.example.airlinereservation.dtos.Request.CreateAdminRequest;
import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Response.AdminInvitationResponse;
import com.example.airlinereservation.dtos.Response.CreateAdminResponse;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import com.example.airlinereservation.exceptions.EmptyFieldException;
import com.example.airlinereservation.exceptions.FieldInvalidException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BolaAirAdminService implements AdminService{
    private AdminRepository adminRepository;
    private UserBioDataRepository bioDataRepository;
    private AddressRepository addressRepository;
    private CrewMemberService crewMemberService;
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
    public AdminInvitationResponse inviteAdmin(AdminInvitationRequest invitationRequest) {
        return null;
    }

    @Override
    public CreateCrewMemberResponse addCrewMember(CreateCrewMemberRequest createCrewMemberRequest) throws EmptyFieldException, IllegalAccessException, FieldInvalidException {
        return crewMemberService.createCrewMember(createCrewMemberRequest);
    }


    @Override
    public Optional<UserBioData> findByUsername(String userName) {
        return bioDataRepository.findByUserName(userName);
    }

}
