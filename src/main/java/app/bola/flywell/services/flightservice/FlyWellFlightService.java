package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.Airport;
import app.bola.flywell.data.model.flight.Flight;
import app.bola.flywell.data.repositories.*;
import app.bola.flywell.dto.response.FlightResponse;
import app.bola.flywell.dto.request.*;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.utils.Constants;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;

import static app.bola.flywell.utils.Constants.*;

@Service
@AllArgsConstructor
public class FlyWellFlightService implements FlightService{

	final FlightRepository flightRepository;
	final AirportRepository airportRepository;
	final ModelMapper mapper;
	final Validator validator;
	final Logger logger = LoggerFactory.getLogger(FlyWellFlightService.class);
	
	@Override
	@Transactional(rollbackFor = Exception.class)
	public FlightResponse createNew(@Valid FlightRequest flightRequest) {

		Set<ConstraintViolation<FlightRequest>> violations = validator.validate(flightRequest);
		Assert.isTrue(violations.isEmpty(), violations.stream().map(violation -> String.format("Field '%s': %s",
				violation.getPropertyPath(), violation.getMessage())).toList().toString());

		Flight flightEntity = mapper.map(flightRequest, Flight.class);
			
		Airport arrivalAirport = mapper.map(flightRequest.getDestinationAirport(), Airport.class);
		Airport departureAirport = mapper.map(flightRequest.getDepartureAirport(), Airport.class);

		flightEntity.setArrivalAirport(airportRepository.save(arrivalAirport));
		flightEntity.setDepartureAirport(airportRepository.save(departureAirport));

		Flight savedFlight = flightRepository.save(flightEntity);
		return toResponse(savedFlight, ENTITY_CREATION_SUCCESSFUL.formatted("Flight"));
	}

	
	@Override
	public FlightResponse updateFlight(FlightUpdateRequest flightRequest) {
		return null;
	}
	
	@Override
	public Long getCountOfAllFlights() {
		return flightRepository.count();
	}
	
	@Override
	public FlightResponse findFlightByRoute(String arrivalCity, String departureCity) {
		ExampleMatcher matcher = ExampleMatcher.matching()
				.withIgnorePaths("id", "duration", "displayImageName", "departureAirport", "arrivalAirport", "instances")
				.withMatcher("arrivalCity", ExampleMatcher.GenericPropertyMatchers.ignoreCase())
				.withMatcher("departureCity", ExampleMatcher.GenericPropertyMatchers.ignoreCase());

		Example<Flight> example = Example.of(Flight.builder()
				.arrivalCity(arrivalCity)
				.departureCity(departureCity)
				.build(), matcher);

		Optional<Flight> foundFlight = flightRepository.findBy(example, FluentQuery.FetchableFluentQuery::first);
		return foundFlight.map(flight -> this.toResponse(flight, ENTITY_SUCCESSFULLY_RETRIEVED))
						  .orElseGet(() -> FlightResponse.builder().message(ENTITY_NOT_FOUND.formatted("Flight")).build());
	}

	private FlightResponse toResponse(Flight flight, String message) {
		FlightResponse response = mapper.map(flight, FlightResponse.class);
		response.setMessage(message);
		response.setArrivalAirportName(flight.getArrivalAirport().getName());
		response.setDepartureAirportName(flight.getDepartureAirport().getName());
		return response;
	}

	@Override
	public void removeAll() {
		flightRepository.deleteAll();
		airportRepository.deleteAll();
	}

	@Override
	public Collection<FlightResponse> findAll(Pageable pageable) {
		return List.of();
	}


	@Override
	public FlightResponse findByPublicId(String publicId) {
		Flight flight = flightRepository.findByPublicId(publicId)
				.orElseThrow(()->new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("Flight")));
		return toResponse(flight, "");
	}

	@Override
	public boolean existsByPublicId(String publicId) {
		return false;
	}

	@Override
	public List<FlightResponse> findAll() {
		return List.of();
	}
}