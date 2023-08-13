package com.example.airlinereservation.services.aircraftsservice;

import com.example.airlinereservation.data.model.aircraft.AirCraft;
import com.example.airlinereservation.dtos.Request.AirCraftRequest;
import com.example.airlinereservation.dtos.Response.AirCraftResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AirCraftManagementServiceTest {

	@Autowired
	AirCraftManagementService airAirCraftManagementService;
	AirCraftRequest airCraftRequest;
	AirCraftRequest airCraftRequest1;
	AirCraftRequest airCraftRequest2;
	AirCraftResponse response;
	@BeforeEach void startEachTestWith(){
		airCraftRequest = new AirCraftRequest();
		airCraftRequest.setAirCraftName("Airliner");
		airCraftRequest.setModel("Boeing 505");
		
		airCraftRequest1 = new AirCraftRequest();
		airCraftRequest1.setModel("AirBus 727");
		airCraftRequest1.setAirCraftName("Freighter");
		
		airCraftRequest2 = new AirCraftRequest();
		airCraftRequest2.setModel("Concorde");
		
		response = airAirCraftManagementService.addAircraftToHanger(airCraftRequest);
	}
	@Test void addAirCraftToHangerTest(){
		assertThat(response).isNotNull();
		assertThat(response.getAirCraftName()).isEqualTo(airCraftRequest.getAirCraftName());
		assertThat(airAirCraftManagementService.hangerContainsAirCraftByName(airCraftRequest.getAirCraftName())).isTrue();
		assertThat(airAirCraftManagementService.hangerContainsAirCraftByModel(airCraftRequest.getModel())).isTrue();
	}
	
	@Test void addSameAirCraftToHangerMultipleTimes_InvalidRequestExceptionIsThrown(){
	
	}
	
	@Test void removeAirCraftFromHangerTest(){
		AirCraft airCraft = new AirCraft();
		airAirCraftManagementService.removeAircraft(airCraft);
		System.out.println("hanger id of aircraft in test is: "+airCraft.getHangerId());
		assertThat(airAirCraftManagementService.hangerContainsAirCraft(airCraft)).isFalse();
	}
	
	@Test void findAvailableAirCraftByLocationAndAvailabilityTest_AirCraftWhichIsAvailableIsReturned(){
	
	}
	
	@Test void findAirCraftByModelTest_FoundAirCraftWithSaidModelIsReturned(){
	
	}
	
	@Test void findAirCraftByHangerIdThatExists_FoundAirCraftIsReturned(){
	
	}
	
	@Test void findAirCraftByModelThatDoesNotExist_ExceptionIsThrown(){
	
	}
}