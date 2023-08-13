package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.enums.Role;
import com.example.airlinereservation.data.model.flight.FlightInstance;
import com.example.airlinereservation.data.model.persons.CrewMember;
import com.example.airlinereservation.data.repositories.CrewMemberRepository;
import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Request.UpdateRequest;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import com.example.airlinereservation.dtos.Response.CrewMemberResponse;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
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

    @Override
    public CreateCrewMemberResponse createCrewMember(CreateCrewMemberRequest createCrewMemberRequest) {
        CrewMember newCrewMember = new CrewMember();
        newCrewMember.setFirstName(createCrewMemberRequest.getFirstName());
        newCrewMember.setUserName(createCrewMemberRequest.getUserName());
        newCrewMember.setRole(Role.CREW_MEMBER);
        newCrewMember.setLastName(createCrewMemberRequest.getLastName());
        newCrewMember.setCountry(createCrewMemberRequest.getCountry());
        newCrewMember.setEmail(createCrewMemberRequest.getEmail());
        newCrewMember.setCountry(createCrewMemberRequest.getCountry());
        newCrewMember.setFullName(createCrewMemberRequest.getFullName());
        newCrewMember.setHouseNumber(createCrewMemberRequest.getHouseNumber());
        newCrewMember.setPassword(createCrewMemberRequest.getPassword());
        newCrewMember.setPhoneNumber(createCrewMemberRequest.getPhoneNumber());
        newCrewMember.setPostalCode(createCrewMemberRequest.getPostalCode());
        newCrewMember.setAvailable(true);
        newCrewMember.setState(createCrewMemberRequest.getState());
        CrewMember savedCrewMember = crewMemberRepository.save(newCrewMember);
        managementService.addCrewMemberToDepartment(savedCrewMember);
        CreateCrewMemberResponse createCrewMemberResponse = new CreateCrewMemberResponse();
        createCrewMemberResponse.setMessage("Crew member created successfully");
        return createCrewMemberResponse;
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
    public CrewMemberResponse updateDetailsOfRegisteredCrewMember(UpdateRequest updateRequest) {
        CrewMemberResponse crewMemberResponse = new CrewMemberResponse();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Optional<CrewMember> foundUser = crewMemberRepository.findByUserName(updateRequest.getNewUserName());


        return null;
    }
}
