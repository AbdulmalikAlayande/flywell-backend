package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.enums.Destinations;
import com.example.airlinereservation.data.model.flight.Flight;
import com.example.airlinereservation.data.model.flight.FlightInstance;
import com.example.airlinereservation.data.repositories.FlightInstanceRepository;
import com.example.airlinereservation.data.repositories.FlightRepository;
import com.example.airlinereservation.dtos.Request.CreateFlightInstanceRequest;
import com.example.airlinereservation.dtos.Response.FlightInstanceResponse;
import com.example.airlinereservation.exceptions.InvalidRequestException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BolaAir_FlightInstanceService implements FlightInstanceService{
	
	private FlightInstanceRepository flightInstanceRepository;
	private FlightRepository flightRepository;
	private ModelMapper mapper;
	
	@Override
	public FlightInstanceResponse createNewInstance(CreateFlightInstanceRequest flightInstanceRequest) throws InvalidRequestException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
		Destinations arrivalState = Destinations.valueOf(flightInstanceRequest.getArrivalState().toUpperCase());
		Destinations departureState = Destinations.valueOf(flightInstanceRequest.getDepartureState().toUpperCase());
		Optional<Flight> flightResponse = flightRepository.findByArrivalAndDepartureAirportLocation(arrivalState, departureState);
		
		FlightInstance mappedFlight = mapper.map(flightInstanceRequest, FlightInstance.class);
		mappedFlight.setFlight(flightResponse.orElseThrow());
		mappedFlight.setFlightSeat(new ArrayList<>());
		
		return mapper.map(mappedFlight, FlightInstanceResponse.class);
	}
}
