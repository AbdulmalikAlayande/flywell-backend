package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.aircraft.Aircraft;
import app.bola.flywell.data.model.enums.FlightStatus;
import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.data.repositories.AircraftRepository;
import app.bola.flywell.data.repositories.FlightInstanceRepository;
import app.bola.flywell.data.repositories.FlightRepository;
import app.bola.flywell.dto.request.FlightInstanceRequest;
import app.bola.flywell.dto.response.AircraftResponse;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.services.aircrafts.AircraftService;
import app.bola.flywell.utils.Constants;
import io.micrometer.observation.Observation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static app.bola.flywell.data.model.enums.FlightStatus.SCHEDULED;

@Service
@AllArgsConstructor
public class FlyWellFlightInstanceService implements FlightInstanceService{

	final FlightInstanceRepository flightInstanceRepository;
	final FlightRepository flightRepository;
	final ModelMapper mapper;
	final Logger logger = LoggerFactory.getLogger(FlyWellFlightInstanceService.class);
	final AircraftService aircraftService;
	final AircraftRepository aircraftRepository;
	final FlightSpacingService flightSpacingService;


	@Override
	@Transactional
	public FlightInstanceResponse createNew(FlightInstanceRequest request) {

		Flight flight = flightRepository.findByPublicId(request.getFlightId())
				.orElseThrow(() -> new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("Flight")));

		FlightInstance flightInstance = new FlightInstance();

		flightInstance.setDepartureTime(request.getDepartureTime());
		flightInstance.setArrivalTime(request.getArrivalTime());
		flightInstance.setStatus(SCHEDULED);
		flightInstance.setPriority(request.getPriority());
		flight.addFlightInstance(flightInstance);
		FlightInstance savedInstance = flightInstanceRepository.save(flightInstance);

        logger.info("Flight Instance At this point:: {}", flightInstanceRepository.findByStatus(SCHEDULED));
		List<FlightInstance> existingInstances = flight.getInstances().stream()
				.filter(instance -> instance.getStatus() == SCHEDULED)
				.toList();

		List<FlightInstance> scheduledInstances = flightSpacingService.scheduleFlights(new ArrayList<>(existingInstances), 30);

		flightInstanceRepository.saveAll(scheduledInstances);
		return toResponse(savedInstance);
	}

	private Aircraft findAvailableAircraft(int capacity) {
		Aircraft aircraft = aircraftService.findAvailableAircraft(capacity);
		if (aircraft == null) {
			//Todo: logger.error("No aircraft available for capacity >= {}. Manual intervention required.", capacity);
			// notifyAdmin("No aircraft available for capacity: " + capacity + " for flight ");
			// find next possible aircraft and reschedule flight
			logger.warn("No aircraft available with capacity >= {}", capacity);
			return new Aircraft();
		}
		aircraft.setAvailable(false);
		return aircraftRepository.save(aircraft);
	}

	@Override
	public FlightInstanceResponse findByPublicId(String publicId) {
		FlightInstance instance = flightInstanceRepository.findByPublicId(publicId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("FlightInstance")));
		return toResponse(instance);
	}

	@Override
	public boolean existsByPublicId(String publicId) {
		return false;
	}

	@Override
	public Collection<FlightInstanceResponse> findAll() {
		return flightInstanceRepository.findAll().stream().map(this::toResponse).toList();
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
	public int checkSeatAvailability(String id) {
		return 0;
	}

	@Override
	public FlightInstanceResponse rescheduleInstance(String id, String newDepartureTime) {
		return null;
	}

	@Override
	public void removeAll() {
		logger.warn("Clearing all flight instances: ");
		flightInstanceRepository.deleteAll();
		logger.info("All flight instances cleared.");
	}

	@Override
	public AircraftResponse getAssignedAircraft(String publicId) {
		FlightInstance foundFlightInstance = flightInstanceRepository.findByPublicId(publicId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("Flight Instance")));
		return mapper.map(foundFlightInstance.getAirCraft(), AircraftResponse.class);
	}

	@Override
	public FlightInstanceResponse assignAircraft(String publicId){
		FlightInstance flightInstance = flightInstanceRepository.findByPublicId(publicId).orElseThrow();
		flightInstance.setAirCraft(findAvailableAircraft(flightInstance.computeTotalPassengers()));
		FlightInstance updatedInstance = flightInstanceRepository.save(flightInstance);
		return mapper.map(updatedInstance, FlightInstanceResponse.class);
	}

	private FlightInstanceResponse toResponse(FlightInstance instance) {

		FlightInstanceResponse response = mapper.map(instance, FlightInstanceResponse.class);
		response.setArrivalAirportName(instance.getFlight().getArrivalAirport().getName());
		response.setDepartureAirportName(instance.getFlight().getDepartureAirport().getName());
		logger.info("Flight Instance:: {}", instance);
		logger.info("Flight Instance Response:: {}", response);
		return response;
	}

}
