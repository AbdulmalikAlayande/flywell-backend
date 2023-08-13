package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.data.model.persons.CrewMember;
import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CrewMemberServiceTest {
    @Autowired
    private CrewMemberService crewMemberService;
    private CreateCrewMemberRequest createCrewMemberRequest;

    @BeforeEach
    @SneakyThrows
    public void startAllTestWith() {
        createCrewMemberRequest = buildCrewMember();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test void testThatACrewHasToExistBeforeTheyCanBeAssignedToAFlight(){

        CreateCrewMemberResponse createCrewMemberResponse = crewMemberService.createCrewMember(buildCrewMember());
        assertThat(createCrewMemberResponse.getMessage()).isEqualTo("Crew member created successfully");
    }

    public static CreateCrewMemberRequest buildCrewMember(){
        return CreateCrewMemberRequest.builder()
                                .userName("Dan123")
                                .phoneNumber("08012343455")
                                .password("pass123")
                                .email("dan@gmail.com")
                                .fullName("John Doe")
                                .state("Lagos")
                                .houseNumber("22")
                                .streetName("Songo Street")
                                .streetNumber("2")
                                .postalCode("10006")
                                .country("Nigeria")
                                .firstName("John")
                                .lastName("Doe")
                                .email("johndoe@gmail.com")
                               .build();
    }

    @SneakyThrows
    @Test void testThatAnExistingCrewMemberCanBeDeletedById(){
        crewMemberService.createCrewMember(createCrewMemberRequest);
        crewMemberService.deleteCrewMemberById("1");

    }
    @Test void testThatAnExistingCrewMemberCanDeletedByUsername(){
//        Given That I have A crew member
        CreateCrewMemberResponse response = crewMemberService.createCrewMember(createCrewMemberRequest);
        long numberOfCrewMembersBefore = crewMemberService.getCountOfCrewMembers();
//        when I delete a crew member
        crewMemberService.deleteCrewMemberByUsername(buildCrewMember().getUserName());
//        I want to assert that the deleted crew member no longer exists
        assertThat(crewMemberService.existsByUsername(createCrewMemberRequest.getUserName())).isFalse();
//     // 2.) long numberOfCrewMembersAfter = crewMemberService.getCountOfCrewMembers();
//        assertThat(numberOfCrewMembersBefore).isGreaterThan(numberOfCrewMembersAfter);
    }
}