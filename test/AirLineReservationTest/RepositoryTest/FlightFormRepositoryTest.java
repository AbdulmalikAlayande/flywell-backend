package AirLineReservationTest.RepositoryTest;

import data.model.FlightForm;
import data.repositories.FlightFormRepository;
import data.repositories.FlightFormRepositoryImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFormRepositoryTest {
	
	FlightForm flightForm;
	FlightFormRepository flightFormRepository;
	@BeforeEach void setUp() {
		flightForm = new FlightForm();
		flightFormRepository = new FlightFormRepositoryImplementation();
		flightForm.setPassengerId("Elluuu P");
		flightForm.setFlightId("4KT");
		flightFormRepository.saveFlightForm(flightForm);
	}
	
	@Test void saveFlightForm_CountIsIncrementedTest(){
		flightFormRepository.saveFlightForm(flightForm);
		assertEquals(BigInteger.ONE.intValue(), flightFormRepository.getAllFlightForms().size());
	}
	
	@Test void saveFlightFormMultipleTimes_CountDoesNotIncrementTest(){
		FlightForm savedFlightForm = flightFormRepository.saveFlightForm(flightForm);
		flightFormRepository.saveFlightForm(savedFlightForm);
		assertEquals(BigInteger.valueOf(1).intValue(), flightFormRepository.getAllFlightForms().size());
	}
	
	@Test void saveFlightForm_GetFlightFormByIdTest(){
		FlightForm foundFlightForm = flightFormRepository.findById(flightForm.getId());
		assertNotNull(foundFlightForm);
		assertNotNull(foundFlightForm.getId());
	}
	
	@Test void saveFlightForm_DeleteFlightFormByIdTest(){
		boolean isDeletedFlightForm = flightFormRepository.deleteFlightFormBy(flightForm.getId());
		assertTrue(isDeletedFlightForm);
		assertEquals(BigInteger.ZERO.intValue(), flightFormRepository.getAllFlightForms().size());
	}
	
	@Test void getAllFlightFormsTest(){
		FlightForm flightForm1 = new FlightForm();
		FlightForm secondFlightForm = flightFormRepository.saveFlightForm(flightForm1);
		List<FlightForm> flightFormList = new ArrayList<>();
		flightFormList.add(flightForm);
		flightFormList.add(secondFlightForm);
		for (int i = 0; i < flightFormRepository.getAllFlightForms().size(); i++) {
			assertNotNull(flightFormRepository.getAllFlightForms().get(i));
		}
		assertEquals(flightFormList, flightFormRepository.getAllFlightForms());
		assertNotNull(flightFormRepository.getAllFlightForms());
		assertEquals(BigInteger.valueOf(2).intValue(), flightFormRepository.getAllFlightForms().size());
	}
	
	@Test void getAllFlightFormsBelongingToAParticularUserTest(){
		FlightForm flightForm1 = new FlightForm();
		flightForm1.setPassengerId("Elluuu P");
		flightFormRepository.saveFlightForm(flightForm1); 
		for (int i = 0; i < flightFormRepository.getAllFlightFormsBelongingToAPassenger("Elluuu P").size(); i++) {
			assertNotNull(flightFormRepository.getAllFlightFormsBelongingToAPassenger("Elluuu P").get(i));
		}
	}
	
	@Test void getFlightFormsOfAllPassengersInAParticularFlightTest(){
		FlightForm flightForm1 = new FlightForm();
		flightForm1.setFlightId("4KT");
		flightFormRepository.saveFlightForm(flightForm1);
		for (int i = 0; i < flightFormRepository.getAllFlightFormsForAParticularFlight("4KT").size(); i++) {
			assertNotNull(flightFormRepository.getAllFlightFormsForAParticularFlight("4KT").get(i));
		}
	}
}