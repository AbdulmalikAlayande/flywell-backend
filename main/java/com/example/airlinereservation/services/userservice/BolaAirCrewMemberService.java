package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.persons.CrewMember;
import com.example.airlinereservation.data.repositories.CrewMemberRepository;
import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor


public class BolaAirCrewMemberService implements CrewMemberService {

    private CrewMemberRepository crewMemberRepository;

    @Override
    public CreateCrewMemberResponse createCrewMember(CreateCrewMemberRequest createCrewMemberRequest) {
        CrewMember newCrewMember = new CrewMember();
//        newCrewMember.setBioData(createCrewMemberRequest.getBioData());
//        newCrewMember.setRole(createCrewMemberRequest.getRole());
        newCrewMember.setAvailable(true);
        crewMemberRepository.save(newCrewMember);
        CreateCrewMemberResponse createCrewMemberResponse = new CreateCrewMemberResponse();
        createCrewMemberResponse.setMessage("Crew member created successfully");
        return createCrewMemberResponse;
    }
}
