package app.bola.flywell.services.aircraft;

import app.bola.flywell.data.model.aircraft.Aircraft;
import app.bola.flywell.data.model.aircraft.Seat;
import app.bola.flywell.data.model.enums.AircraftStatus;
import app.bola.flywell.data.model.enums.SeatClass;
import app.bola.flywell.data.repositories.AircraftRepository;
import app.bola.flywell.data.repositories.SeatRepository;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FlyWellAircraftService implements AircraftService {

	final AircraftRepository aircraftRepository;
	final SeatRepository seatRepository;
	final ModelMapper mapper;
	final AircraftHangerIdGenerator hangerIdGenerator;

	@Override
	@Transactional
	public AircraftResponse createNew(AircraftRequest request) {

		Aircraft aircraft = mapper.map(request, Aircraft.class);
		aircraft.setHangarId(hangerIdGenerator.generateHangerId(aircraft.getLocationCode()));
		aircraft.setAvailable(true);
		aircraft.setStatus(AircraftStatus.AVAILABLE);
		if (aircraft.getSeats() == null) aircraft.setSeats(new LinkedHashSet<>());
		int seatCapacity = request.getCapacity();

		int oneQuarter = seatCapacity /4;
		int half = seatCapacity / 2;

		int rowPosition = 1;
		for (int index = 1; index <= seatCapacity; index++) {
			if (rowPosition > 4)
				rowPosition = 0;

			Seat seat = new Seat();
			if (index <= oneQuarter){
				seat.setSeatClass(SeatClass.FIRST_CLASS);
			}
			else if (index <= half){
				seat.setSeatClass(SeatClass.BUSINESS);
			}
			else {
				seat.setSeatClass(SeatClass.ECONOMY);
			}

			switch (rowPosition){
				case 1 -> seat.setRowPosition(1);
				case 2 -> seat.setRowPosition(2);
				case 3 -> seat.setRowPosition(3);
				case 4 -> seat.setRowPosition(4);
			}

			seat.setSeatNumber(index);
			aircraft.addSeat(seatRepository.save(seat));
			rowPosition++;
		}

		Aircraft savedAircraft = aircraftRepository.save(aircraft);
		System.out.println("saved Aircraft:: "+savedAircraft);
		return mapper.map(savedAircraft, AircraftResponse.class);
	}


	@Override
	public Aircraft findAvailableAircraft() {
		Specification<Aircraft> spec = Specification
				.where(AircraftSpecification.isAvailable())
				.and(AircraftSpecification.hasStatus(String.valueOf(AircraftStatus.AVAILABLE)));

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
