package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.enums.Destinations;
import com.example.airlinereservation.data.model.enums.FlightStatus;
import com.example.airlinereservation.data.model.flight.Flight;
import com.example.airlinereservation.data.model.flight.FlightInstance;
import com.example.airlinereservation.data.repositories.FlightInstanceRepository;
import com.example.airlinereservation.data.repositories.FlightRepository;
import com.example.airlinereservation.dtos.Request.CreateFlightInstanceRequest;
import com.example.airlinereservation.dtos.Response.FlightInstanceResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BolaAir_FlightInstanceService implements FlightInstanceService{
	
	private FlightInstanceRepository flightInstanceRepository;
	private FlightRepository flightRepository;
	private ModelMapper mapper;
	
	@Override
	public FlightInstanceResponse createNewInstance(CreateFlightInstanceRequest flightInstanceRequest){
		Destinations arrivalState = Destinations.valueOf(flightInstanceRequest.getArrivalState().toUpperCase());
		Destinations departureState = Destinations.valueOf(flightInstanceRequest.getDepartureState().toUpperCase());
		
		//where status is en-route and destination location is arrivalState
		Optional<FlightInstance> foundInstance = flightInstanceRepository.findByFlightDestinationAndFlightMovementStatus(arrivalState, FlightStatus.EN_ROUTE);
		
		Optional<Flight> flightResponse = flightRepository.findByArrivalAndDepartureAirportLocation(arrivalState, departureState);
		FlightInstance mappedFlight = mapper.map(flightInstanceRequest, FlightInstance.class);
		mappedFlight.setFlight(flightResponse.orElseThrow());
		mappedFlight.setFlightSeat(new ArrayList<>());
		FlightInstance savedFlight = flightInstanceRepository.save(mappedFlight);
		return flightInstanceResponse(savedFlight);
	}
	
	private FlightInstanceResponse flightInstanceResponse(FlightInstance flightInstance) {
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(new PropertyMap<FlightInstance, FlightInstanceResponse>() {
			@Override
			protected void configure() {
				map().setArrivalAirportAddress(source.getFlight().getArrivalAirport().getAirportAddress());
				map().setArrivalAirportName(source.getFlight().getArrivalAirport().getName());
				map().setArrivalAirportCode(source.getFlight().getArrivalAirport().getCode());
				
				map().setDepartureAirportAddress(source.getFlight().getDepartureAirport().getAirportAddress());
				map().setDepartureAirportName(source.getFlight().getDepartureAirport().getName());
				map().setDepartureAirportCode(source.getFlight().getDepartureAirport().getCode());
			}
		});
		return mapper.map(flightInstance, FlightInstanceResponse.class);
	}
}
