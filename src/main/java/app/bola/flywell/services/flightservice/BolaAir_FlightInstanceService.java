package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.enums.FlightStatus;
import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.data.repositories.FlightInstanceRepository;
import app.bola.flywell.data.repositories.FlightRepository;
import app.bola.flywell.dtos.Request.CreateFlightInstanceRequest;
import app.bola.flywell.dtos.Response.FlightInstanceResponse;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static app.bola.flywell.data.model.enums.FlightStatus.SCHEDULED;

@Service
@AllArgsConstructor
public class BolaAir_FlightInstanceService implements FlightInstanceService{
	
	private FlightInstanceRepository flightInstanceRepository;
	private FlightRepository flightRepository;
	private ModelMapper mapper;
	
	@Override
	public FlightInstanceResponse createNewInstance(CreateFlightInstanceRequest request){
		checkIfAFlightStillExists(request);
		Optional<Flight> foundFlight = flightRepository.findByArrivalCityAndDepartureCity(request.getArrivalCity(), request.getDepartureCity());
		FlightInstance savedFlight = foundFlight.map(flight -> {
			FlightInstance mappedFlightInstance = mapper.map(request, FlightInstance.class);
			mappedFlightInstance.setFlight(flight);
			mappedFlightInstance.setStatus(SCHEDULED);
			mappedFlightInstance.setFlightSeat(new ArrayList<>());
			FlightInstance savedInstance = flightInstanceRepository.save(mappedFlightInstance);
			flight.getFlightInstances().add(savedInstance);
			flightRepository.save(flight);
			return savedInstance;
		}).orElseThrow();
		return flightInstanceResponse(savedFlight);
	}
	
	private void checkIfAFlightStillExists(CreateFlightInstanceRequest request) {
		List<FlightInstance> foundInstances = flightInstanceRepository.findAvailableInstances(
																		SCHEDULED,
																		request.getArrivalDate(),
																		request.getDepartureDate()
																	);
//		foundInstances.f
		
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
	
	@Override
	public void removeAll() {
		flightInstanceRepository.deleteAll();
	}
	
	public List<FlightInstanceResponse> findAllBy(LocalDateTime departureDate){
		return new ArrayList<>();
	}
}
/*  TODO: 12/24/2023
     |==|)) If a flight instance still exist and it is neither filled nor en-route yet
     don't create a new one, meaning by arrival and departure location, by date and time
     |==|)) If a flight instance is to be created let it be spaced by at least 5hrs
*/
