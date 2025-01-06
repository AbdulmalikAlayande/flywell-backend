package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.enums.FlightStatus;
import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.data.repositories.FlightInstanceRepository;
import app.bola.flywell.data.repositories.FlightRepository;
import app.bola.flywell.dtos.request.FlightInstanceRequest;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.utils.Constants;
import io.micrometer.observation.Observation;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static app.bola.flywell.data.model.enums.FlightStatus.SCHEDULED;

@Service
@AllArgsConstructor
public class FlyWellFlightInstanceService implements FlightInstanceService{

	final FlightInstanceRepository flightInstanceRepository;
	final FlightRepository flightRepository;
	final ModelMapper mapper;
	final Logger logger = LoggerFactory.getLogger(FlyWellFlightInstanceService.class);

	@Override
	@Transactional
	@Retryable(retryFor = ObjectOptimisticLockingFailureException.class, maxAttempts = 5)
	public FlightInstanceResponse createNew(FlightInstanceRequest request) {

		Flight flight = flightRepository.findByPublicId(request.getFlightId())
				.orElseThrow(() -> new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("Flight")));

		FlightInstance mappedFlightInstance = mapper.map(request, FlightInstance.class);
		mappedFlightInstance.setFlight(flight);
		mappedFlightInstance.setStatus(SCHEDULED);
		mappedFlightInstance.setFlightSeat(new ArrayList<>());

		FlightInstance savedInstance = flight.addFlightInstance(mappedFlightInstance);

		flightRepository.save(flight);
		return flightInstanceResponse(savedInstance);
	}

	@Override
	public FlightInstanceResponse findByPublicId(String publicId) {
		FlightInstance flightInstance = flightInstanceRepository.findByPublicId(publicId)
				.orElseThrow(() -> new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("Flight Instance")));
		return flightInstanceResponse(flightInstance);
	}

	@Override
	public boolean existsByPublicId(String publicId) {
		return false;
	}

	private FlightInstanceResponse flightInstanceResponse(FlightInstance flightInstance) {

		ModelMapper map = new ModelMapper();
		map.addMappings(new PropertyMap<FlightInstance, FlightInstanceResponse>() {

			@Override
			protected void configure() {
				map().setArrivalAirportAddress(source.getFlight().getArrivalAirport().getAddress());
				map().setArrivalAirportName(source.getFlight().getArrivalAirport().getName());
				map().setArrivalAirportIcaoCode(source.getFlight().getArrivalAirport().getIcaoCode());
				
				map().setDepartureAirportAddress(source.getFlight().getDepartureAirport().getAddress());
				map().setDepartureAirportName(source.getFlight().getDepartureAirport().getName());
				map().setDepartureAirportIcaoCode(source.getFlight().getDepartureAirport().getIcaoCode());
			}
		});
		return map.map(flightInstance, FlightInstanceResponse.class);
	}
	
	@Override
	public List<FlightInstanceResponse> findAllByStatus(FlightStatus status){
		List<FlightInstance> foundInstances = flightInstanceRepository.findByStatus(status);
		return foundInstances.stream().map(this::flightInstanceResponse).toList();
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
