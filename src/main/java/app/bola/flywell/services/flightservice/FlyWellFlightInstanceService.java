package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.aircraft.Aircraft;
import app.bola.flywell.data.model.enums.FlightStatus;
import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.data.repositories.AircraftRepository;
import app.bola.flywell.data.repositories.FlightInstanceRepository;
import app.bola.flywell.data.repositories.FlightRepository;
import app.bola.flywell.dto.request.FlightInstanceRequest;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.utils.Constants;
import io.micrometer.observation.Observation;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

 import java.util.List;

@Service
@AllArgsConstructor
public class FlyWellFlightInstanceService implements FlightInstanceService{

	final FlightInstanceRepository flightInstanceRepository;
	final FlightRepository flightRepository;
	final ModelMapper mapper;
	final Logger logger = LoggerFactory.getLogger(FlyWellFlightInstanceService.class);
	final AircraftRepository aircraftRepository;
	final FlightSpacingService flightSpacingService;


	@Override
	@Transactional
	public FlightInstanceResponse createNew(FlightInstanceRequest request) {
		Flight flight = flightRepository.findByPublicId(request.getFlightId())
				.orElseThrow(() -> new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("Flight")));

		FlightInstance flightInstance = mapper.map(request, FlightInstance.class);
		logger.info("Ex: {}", flightInstance);
		flightInstance.setId(null);
		flightInstance.setStatus(FlightStatus.SCHEDULED);
		flight.addFlightInstance(flightInstance);
		flightRepository.save(flight);
		return toResponse(flightInstance);
	}

	private Aircraft findAvailableAircraft() {
		return aircraftRepository.findAll()
								 .stream()
								 .filter(Aircraft::isAvailable)
								 .findAny()
								 .orElse(null);
	}

	@Override
	public FlightInstanceResponse findByPublicId(String publicId) {
		FlightInstance instance = flightInstanceRepository.findByPublicId(publicId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("Flight FlightInstance")));
		return toResponse(instance);
	}

	@Override
	public boolean existsByPublicId(String publicId) {
		return false;
	}

	private FlightInstanceResponse toResponse(FlightInstance instance) {

		FlightInstanceResponse response = mapper.map(instance, FlightInstanceResponse.class);
		response.setArrivalAirportName(instance.getFlight().getArrivalAirport().getName());
		response.setDepartureAirportName(instance.getFlight().getDepartureAirport().getName());
		logger.info("Flight Instance:: {}", instance);
		logger.info("Flight Instance Response:: {}", response);
		return response;
	}
	
	@Override
	public List<FlightInstanceResponse> findAllByStatus(FlightStatus status){
		List<FlightInstance> foundInstances = flightInstanceRepository.findByStatus(status);
		return foundInstances.stream().map(this::toResponse).toList();
	}

	@Override
	public FlightInstanceResponse findById(String id) {
		return null;
	}

	@Override
	public FlightInstanceResponse updateInstance(String id, FlightInstanceRequest flightInstanceRequest) {
		return null;
	}

	@Override
	public void cancelInstance(String id) {
		Observation.CheckedRunnable<?> eCheckedRunnable = () -> logger.info("");
		logger.info("{}", eCheckedRunnable);
	}

	@Override
	public List<FlightInstanceResponse> findAllByFlightModel(String flightModelId) {
		return List.of();
	}

	@Override
	public int checkSeatAvailability(String id) {
		return 0;
	}

	@Override
	public FlightInstanceResponse rescheduleInstance(String id, String newDepartureTime) {
		return null;
	}

	@Override
	public void removeAll() {
		flightInstanceRepository.deleteAll();
	}


}




/*  TODO: 12/24/2023
     |==|)) If a flight instance still exist and it is neither filled nor en-route yet
     don't create a new one, meaning by arrival and departure location, by date and time
     |==|)) If a flight instance is to be created let it be spaced by at least 5hrs
*/
