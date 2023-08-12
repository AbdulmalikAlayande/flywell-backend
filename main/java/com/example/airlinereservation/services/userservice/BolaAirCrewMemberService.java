package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.Passenger;
import com.example.airlinereservation.data.model.enums.Role;
import com.example.airlinereservation.data.model.persons.CrewMember;
import com.example.airlinereservation.data.repositories.CrewMemberRepository;
import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.airlinereservation.utils.appUtils.Constants.INVALID_REQUEST_MESSAGE;

@Service
@AllArgsConstructor


public class BolaAirCrewMemberService implements CrewMemberService {

    private CrewMemberRepository crewMemberRepository;

    @Override
    public CreateCrewMemberResponse createCrewMember(CreateCrewMemberRequest createCrewMemberRequest) {
        CrewMember newCrewMember = new CrewMember();
        newCrewMember.setFirstName(createCrewMemberRequest.getFirstName());
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
        crewMemberRepository.save(newCrewMember);
        CreateCrewMemberResponse createCrewMemberResponse = new CreateCrewMemberResponse();
        createCrewMemberResponse.setMessage("Crew member created successfully");
        return createCrewMemberResponse;
    }

    @Override
    public void deleteCrewMemberById(String id) throws InvalidRequestException {
        Optional<CrewMember> foundCrewMember = crewMemberRepository.findById(id);
        if (foundCrewMember.isEmpty())
            throw new InvalidRequestException(String.format(INVALID_REQUEST_MESSAGE, "Admin", id));
        crewMemberRepository.deleteById(id);

    }
}
