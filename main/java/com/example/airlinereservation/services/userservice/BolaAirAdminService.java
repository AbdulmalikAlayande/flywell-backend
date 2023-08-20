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
import com.example.airlinereservation.utils.exceptions.EmptyFieldException;
import com.example.airlinereservation.utils.exceptions.FieldInvalidException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BolaAirAdminService implements AdminService{
    private AdminRepository adminRepository;
    private UserBioDataRepository bioDataRepository;
    private AddressRepository addressRepository;
    private CrewMemberService crewMemberService;

    @Override
    public CreateAdminResponse createAdmin(CreateAdminRequest createAdminRequest) {
        Admin admin = new Admin();
        UserBioData bioData = new UserBioData();
        Address address = new Address();

        address.setCountry(createAdminRequest.getCountry());
        address.setState(createAdminRequest.getState());
        address.setHouseNumber(createAdminRequest.getHouseNumber());
        address.setStreetName(createAdminRequest.getStreetName());
        address.setStreetNumber(createAdminRequest.getStreetNumber());
        address.setPostalCode(createAdminRequest.getPostalCode());

        Address savedAddress = addressRepository.save(address);

        bioData.setAddress(savedAddress);
        bioData.setEmail(createAdminRequest.getEmail());
        bioData.setPassword(createAdminRequest.getPassword());
        bioData.setFullName(createAdminRequest.getFullName());
        bioData.setFirstName(createAdminRequest.getFirstName());
        bioData.setLastName(createAdminRequest.getLastName());
        bioData.setPhoneNumber(createAdminRequest.getPhoneNumber());
        bioData.setUserName(createAdminRequest.getUserName());

        UserBioData savedBio = bioDataRepository.save(bioData);
        admin.setBioData(savedBio);
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
