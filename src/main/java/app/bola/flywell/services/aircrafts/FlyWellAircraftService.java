package app.bola.flywell.services.aircrafts;

import app.bola.flywell.data.model.aircraft.Aircraft;
import app.bola.flywell.data.model.aircraft.AircraftStatus;
import app.bola.flywell.data.repositories.AircraftRepository;
import app.bola.flywell.data.specifications.AircraftSpecification;
import app.bola.flywell.dto.request.AircraftRequest;
import app.bola.flywell.dto.response.AircraftResponse;
import app.bola.flywell.generator.AircraftHangerIdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FlyWellAircraftService implements AircraftService {

	final AircraftRepository aircraftRepository;
	final ModelMapper mapper;
	final AircraftHangerIdGenerator hangerIdGenerator;

	@Override
	public AircraftResponse createNew(AircraftRequest request) {
		Aircraft aircraft = mapper.map(request, Aircraft.class);
		aircraft.setHangarId(hangerIdGenerator.generateHangerId(aircraft.getLocationCode()));
		aircraft.setAvailable(true);
		aircraft.setStatus(AircraftStatus.AVAILABLE);
		Aircraft savedAircraft = aircraftRepository.save(aircraft);
		return mapper.map(savedAircraft, AircraftResponse.class);
	}


	@Override
	public Aircraft findAvailableAircraft(int capacity) {
		Specification<Aircraft> spec = Specification
				.where(AircraftSpecification.isAvailable())
				.and(AircraftSpecification.hasStatus(String.valueOf(AircraftStatus.AVAILABLE)))
				.and(AircraftSpecification.hasCapacityGreaterThan(capacity));

		Sort sort = Sort.by("capacity").ascending();
		List<Aircraft> availableAircraft = aircraftRepository.findAll(spec, sort);

		return availableAircraft.isEmpty() ? null : availableAircraft.getFirst();
	}

	@Override
	public AircraftResponse findByPublicId(String publicId) {
		return null;
	}

	@Override
	public boolean existsByPublicId(String publicId) {
		return false;
	}

	@Override
	public Collection<AircraftResponse> findAll() {
		return List.of();
	}

	@Override
	public void removeAll() {
		aircraftRepository.deleteAll();
	}
}
