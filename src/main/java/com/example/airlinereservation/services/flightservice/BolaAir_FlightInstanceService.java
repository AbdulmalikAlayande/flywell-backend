package com.example.airlinereservation.services.flightservice;

import com.example.airlinereservation.data.model.enums.FlightStatus;
import com.example.airlinereservation.data.model.flight.Flight;
import com.example.airlinereservation.data.model.flight.FlightInstance;
import com.example.airlinereservation.data.repositories.FlightInstanceRepository;
import com.example.airlinereservation.data.repositories.FlightRepository;
import com.example.airlinereservation.dtos.Request.AirportRequest;
import com.example.airlinereservation.dtos.Request.CreateFlightInstanceRequest;
import com.example.airlinereservation.dtos.Response.FlightInstanceResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.airlinereservation.data.model.enums.FlightStatus.SCHEDULED;

@Service
@AllArgsConstructor
public class BolaAir_FlightInstanceService implements FlightInstanceService{
	
	private FlightInstanceRepository flightInstanceRepository;
	private FlightRepository flightRepository;
	private ModelMapper mapper;
	
	@Override
	public FlightInstanceResponse createNewInstance(CreateFlightInstanceRequest request){
		AirportRequest arrivalAirport = request.getArrivalAirport();
		AirportRequest departureAirport = request.getDepartureAirport();
		Optional<Flight> foundFlight = flightRepository.findByArrivalAndDepartureAirport(arrivalAirport.getAirportName(), departureAirport.getAirportName());
		FlightInstance flights = foundFlight.map(flight -> {
			FlightInstance mappedFlight = mapper.map(request, FlightInstance.class);
			mappedFlight.setFlight(flight);
			mappedFlight.setStatus(SCHEDULED);
			mappedFlight.setFlightSeat(new ArrayList<>());
			return mappedFlight;
		}).orElseThrow();
		FlightInstance savedFlight = flightInstanceRepository.save(flights);
		return flightInstanceResponse(savedFlight);
	}
	
	private FlightInstance performSeparationTechnique(List<FlightInstance> enRouteInstances, FlightInstance mappedFlight) {
		
		return new FlightInstance();
	}
	
	private FlightInstanceResponse flightInstanceResponse(FlightInstance flightInstance) {
		ModelMapper mapper = new ModelMapper();
		mapper.addMappings(new PropertyMap<FlightInstance, FlightInstanceResponse>() {
			@Override
			protected void configure() {
				map().setArrivalAirportAddress(source.getFlight().getArrivalAirport().getAirportAddress());
				map().setArrivalAirportName(source.getFlight().getArrivalAirport().getAirportName());
				map().setArrivalAirportIcaoCode(source.getFlight().getArrivalAirport().getIcaoCode());
				
				map().setDepartureAirportAddress(source.getFlight().getDepartureAirport().getAirportAddress());
				map().setDepartureAirportName(source.getFlight().getDepartureAirport().getAirportName());
				map().setDepartureAirportIcaoCode(source.getFlight().getDepartureAirport().getIcaoCode());
			}
		});
		return mapper.map(flightInstance, FlightInstanceResponse.class);
	}
	
	@Override
	public List<FlightInstanceResponse> findAllBy(FlightStatus status){
		List<FlightInstance> foundInstances = flightInstanceRepository.findByStatus(status);
		return foundInstances.stream().map(this::flightInstanceResponse).toList();
	}
	public List<FlightInstanceResponse> findAllBy(LocalDateTime departureDate){
		return new ArrayList<>();
	}
}
