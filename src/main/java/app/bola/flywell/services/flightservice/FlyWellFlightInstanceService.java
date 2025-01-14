package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.aircraft.Aircraft;
import app.bola.flywell.data.model.aircraft.Seat;
import app.bola.flywell.data.model.enums.FlightStatus;
import app.bola.flywell.data.model.enums.SeatStatus;
import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.data.model.flight.FlightSeat;
import app.bola.flywell.data.repositories.AircraftRepository;
import app.bola.flywell.data.repositories.FlightInstanceRepository;
import app.bola.flywell.data.repositories.FlightRepository;
import app.bola.flywell.data.repositories.FlightSeatRepository;
import app.bola.flywell.dto.request.FlightInstanceRequest;
import app.bola.flywell.dto.response.AircraftResponse;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.services.aircraft.AircraftService;
import app.bola.flywell.utils.Constants;
import io.micrometer.observation.Observation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static app.bola.flywell.data.model.enums.FlightStatus.SCHEDULED;

@Service
@AllArgsConstructor
public class FlyWellFlightInstanceService implements FlightInstanceService{

	final ModelMapper mapper;
	final AircraftService aircraftService;
	final FlightRepository flightRepository;
	final AircraftRepository aircraftRepository;
	final FlightSeatRepository flightSeatRepository;
	final FlightSpacingService flightSpacingService;
	final FlightInstanceRepository flightInstanceRepository;
	final Logger logger = LoggerFactory.getLogger(FlyWellFlightInstanceService.class);


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
		assignAircraft(flightInstance);
		populateFlightSeats(flightInstance, flightInstance.getAirCraft());
		FlightInstance savedInstance = flightInstanceRepository.save(flightInstance);

		List<FlightInstance> existingInstances = flight.getInstances().stream()
				.filter(instance -> instance.getStatus() == SCHEDULED)
				.toList();

		List<FlightInstance> scheduledInstances = flightSpacingService.scheduleFlights(new ArrayList<>(existingInstances), 30);

		flightInstanceRepository.saveAll(scheduledInstances);
		return toResponse(savedInstance);
	}

	private Aircraft findAvailableAircraft() {
		Aircraft aircraft = aircraftService.findAvailableAircraft();
		if (aircraft == null) {
			//Todo: logger.error("No aircraft available for capacity >= {}. Manual intervention required.", capacity);
			// notifyAdmin("No aircraft available for capacity: " + capacity + " for flight ");
			// find next possible aircraft and reschedule flight
			logger.error("No available aircraft found. The result of app.bola.flywell.services.aircraft.AircraftService.findAvailableAircraft() was null");
			return null;
		}
		aircraft.setAvailable(false);
		logger.info("Aircraft Found and Ready To Be Assigned: {}", aircraft);
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

	private void assignAircraft(FlightInstance flightInstance){
		Aircraft availableAircraft = findAvailableAircraft();
		flightInstance.setAirCraft(availableAircraft);
		flightInstanceRepository.save(flightInstance);
		logger.info("Aircraft assigned. Aircraft: {} assigned to flight: {}", availableAircraft, flightInstance);
	}

	@Override
	public FlightInstanceResponse assignAircraft(String publicId){
		FlightInstance flightInstance = flightInstanceRepository.findByPublicId(publicId).orElseThrow();
		flightInstance.setAirCraft(findAvailableAircraft());
		FlightInstance updatedInstance = flightInstanceRepository.save(flightInstance);
		return mapper.map(updatedInstance, FlightInstanceResponse.class);
	}

	private void populateFlightSeats(FlightInstance flightInstance, Aircraft aircraft) {
		logger.info("DGF:: {}", aircraft);
		if (aircraft == null || aircraft.getSeats().isEmpty()) {
			throw new IllegalStateException("Aircraft has no seats configured");
		}

		Set<FlightSeat> flightSeats = new LinkedHashSet<>();

		for (Seat seat : aircraft.getSeats()) {
			FlightSeat flightSeat = new FlightSeat();

			flightSeat.setSeatReference(seat);
			flightSeat.setSeatStatus(SeatStatus.EMPTY);
			flightSeat.setSeatPrice(seat.getSeatClass());

			flightSeats.add(flightSeatRepository.save(flightSeat));
		}

		flightInstance.setSeats(flightSeats);
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
