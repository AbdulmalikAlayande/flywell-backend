package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.enums.Role;
import com.example.airlinereservation.data.model.flight.FlightInstance;
import com.example.airlinereservation.data.model.persons.Address;
import com.example.airlinereservation.data.model.persons.CrewMember;
import com.example.airlinereservation.data.model.persons.UserBioData;
import com.example.airlinereservation.data.repositories.AddressRepository;
import com.example.airlinereservation.data.repositories.CrewMemberRepository;
import com.example.airlinereservation.data.repositories.UserBioDataRepository;
import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Request.UpdateRequest;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import com.example.airlinereservation.dtos.Response.CrewMemberResponse;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.airlinereservation.utils.appUtils.Constants.INVALID_REQUEST_MESSAGE;

@Service
@AllArgsConstructor
@Slf4j
public class BolaAirCrewMemberService implements CrewMemberService {

    private CrewMemberRepository crewMemberRepository;
    private ModelMapper mapper;
    private CrewMemberManagementService managementService;
    private UserBioDataRepository userBioDataRepository;
    private AddressRepository addressRepository;

    @Override
    public CreateCrewMemberResponse createCrewMember(CreateCrewMemberRequest createCrewMemberRequest) {
        Address address = buildAddress(createCrewMemberRequest);
        Address savedAddress = addressRepository.save(address);

        UserBioData userBioData = buildBiodata(createCrewMemberRequest);
        userBioData.setAddress(savedAddress);
        UserBioData savedBio = userBioDataRepository.save(userBioData);

        CrewMember newCrewMember = new CrewMember();
        newCrewMember.setBioData(savedBio);
        newCrewMember.setRole(Role.CREW_MEMBER);
        newCrewMember.setAvailable(true);
        CrewMember savedCrewMember = crewMemberRepository.save(newCrewMember);

        managementService.addCrewMemberToDepartment(savedCrewMember);
        CreateCrewMemberResponse createCrewMemberResponse = new CreateCrewMemberResponse();
        createCrewMemberResponse.setMessage("Crew member created successfully");
        return createCrewMemberResponse;
    }

    private UserBioData buildBiodata(CreateCrewMemberRequest createCrewMemberRequest) {
        return UserBioData.builder()
               .lastName(createCrewMemberRequest.getLastName())
                .build();
    }

    private Address buildAddress(CreateCrewMemberRequest createCrewMemberRequest) {
        return Address.builder()
                      .country(createCrewMemberRequest.getCountry())
                      .houseNumber(createCrewMemberRequest.getHouseNumber())
                      .state(createCrewMemberRequest.getState())
                      .streetNumber(createCrewMemberRequest.getStreetNumber())
                      .postalCode(createCrewMemberRequest.getPostalCode())
                      .streetName(createCrewMemberRequest.getStreetName())
                      .build();
    }

    @Override
    public void deleteCrewMemberById(String id) throws InvalidRequestException {
        Optional<CrewMember> foundCrewMember = crewMemberRepository.findById(id);
        if (foundCrewMember.isEmpty())
            throw new InvalidRequestException(String.format(INVALID_REQUEST_MESSAGE, "Crew member", "id", id));
        crewMemberRepository.deleteById(id);

    }

    @Override
    public FlightInstance assignCrewMember(FlightInstance flightInstance) {
        return null;
    }

    @Override
    public void deleteCrewMemberByUsername(String userName) throws InvalidRequestException {
        boolean memberExists = crewMemberRepository.existsByUserName(userName);
        if (!memberExists)
            throw new InvalidRequestException(String.format(INVALID_REQUEST_MESSAGE, "Crew member", "username", userName));
        crewMemberRepository.deleteByUserName(userName);
    }
    
    @Override
    public long getCountOfCrewMembers() {
        return crewMemberRepository.count();
    }
    
    @Override
    public boolean existsByUsername(String userName) {
        return crewMemberRepository.existsByUserName(userName);
    }

    @Override
    public Optional<CrewMember> findCrewMemberByUserName(String userName) throws InvalidRequestException {
        boolean crewMember = crewMemberRepository.existsByUserName(userName);
        if(!crewMember)
            throw new InvalidRequestException(String.format(INVALID_REQUEST_MESSAGE,"Crew member", "username", userName));
        return crewMemberRepository.findByUserName(userName);
    }

    @Override
    public CrewMemberResponse updateDetailsOfRegisteredCrewMember(@NotNull UpdateRequest updateRequest) {
        CrewMemberResponse crewMemberResponse = new CrewMemberResponse();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Optional<CrewMember> crewMemberBio = crewMemberRepository.findByUserName(updateRequest.getNewUserName());
       return null;
    }
}
