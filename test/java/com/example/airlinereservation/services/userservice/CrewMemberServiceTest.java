package com.example.airlinereservation.services.userservice;

import com.example.airlinereservation.dtos.Request.CreateCrewMemberRequest;
import com.example.airlinereservation.dtos.Response.CreateCrewMemberResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CrewMemberServiceTest {
    @Autowired
    private CrewMemberService crewMemberService;

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
                               .build();
    }
}