package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.enums.Role;
import com.example.airlinereservation.data.model.flight.FlightInstance;
import com.example.airlinereservation.data.model.persons.Address;
import com.example.airlinereservation.data.model.persons.CrewMember;
import com.example.airlinereservation.data.model.persons.UserBioData;
import com.example.airlinereservation.data.repositories.CrewMemberRepository;
import com.example.airlinereservation.data.repositories.UserBioDataRepository;
import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Request.UpdateRequest;
import com.example.airlinereservation.dtos.Request.ViewFlightScheduleRequest;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import com.example.airlinereservation.dtos.Response.CrewMemberResponse;
import com.example.airlinereservation.dtos.Response.FlightScheduleResponse;
import com.example.airlinereservation.services.notifications.Validator;
import com.example.airlinereservation.utils.exceptions.EmptyFieldException;
import com.example.airlinereservation.utils.exceptions.FieldInvalidException;
import com.example.airlinereservation.utils.exceptions.InvalidRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Optional;

import static com.example.airlinereservation.utils.appUtils.Constants.*;

@Service
@AllArgsConstructor
@Slf4j
public class BolaAirCrewMemberService implements CrewMemberService {

    private CrewMemberRepository crewMemberRepository;
    private ModelMapper mapper;
    private CrewMemberManagementService managementService;
    private UserBioDataRepository userBioDataRepository;
    private Validator validator;

    @Override
    public CreateCrewMemberResponse createCrewMember(CreateCrewMemberRequest createCrewMemberRequest) throws IllegalAccessException, EmptyFieldException, FieldInvalidException {
        checkForNullFields(createCrewMemberRequest);
        rejectCrewMemberIfCrewMemberAlreadyExists(createCrewMemberRequest);
        validator.validatePassword(createCrewMemberRequest.getPassword());
        CrewMember newCrewMember = new CrewMember();
        Address mappedAddress = mapper.map(createCrewMemberRequest, Address.class);
        UserBioData mappedBio = mapper.map(createCrewMemberRequest, UserBioData.class);
        mappedBio.setAddress(mappedAddress);
        newCrewMember.setBioData(mappedBio);
        newCrewMember.setAvailable(true);
        newCrewMember.setRole(Role.CREW_MEMBER);
        CrewMember savedCrewMember = crewMemberRepository.save(newCrewMember);
        managementService.addCrewMemberToDepartment(savedCrewMember);
        CreateCrewMemberResponse createCrewMemberResponse = new CreateCrewMemberResponse();
        createCrewMemberResponse.setMessage("Registration Successful");
        return createCrewMemberResponse;
    }
    
    private void checkForNullFields(CreateCrewMemberRequest createCrewMemberRequest) throws IllegalAccessException, EmptyFieldException {
        Field[] declaredFields = createCrewMemberRequest.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object accessedField = field.get(createCrewMemberRequest);
            if (accessedField == null || (accessedField instanceof String && accessedField.toString().isEmpty())) {
                String errorMessage = String.format(EMPTY_FIELD_MESSAGE, field.getName());
                throw new EmptyFieldException(String.format(INCOMPLETE_DETAILS_MESSAGE, errorMessage));
            }
        }
    }
    
    private void rejectCrewMemberIfCrewMemberAlreadyExists(CreateCrewMemberRequest createCrewMemberRequest) {
        Optional<UserBioData> foundBio = userBioDataRepository.findByEmailAndPassword(createCrewMemberRequest.getEmail(), createCrewMemberRequest.getPassword());
        boolean crewMemberExists = crewMemberRepository.existsByUserName(createCrewMemberRequest.getUserName());
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
    
    @Override
    public FlightScheduleResponse viewFlightSchedule(ViewFlightScheduleRequest flightScheduleRequest) {
        return null;
    }
}
