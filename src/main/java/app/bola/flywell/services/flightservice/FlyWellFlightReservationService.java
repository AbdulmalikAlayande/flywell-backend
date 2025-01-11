package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.flight.FlightReservation;
import app.bola.flywell.data.repositories.FlightReservationRepository;
import app.bola.flywell.dto.request.FlightReservationRequest;
import app.bola.flywell.dto.response.FlightReservationResponse;
import app.bola.flywell.generator.ReservationNumberGenerator;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class FlyWellFlightReservationService implements FlightReservationService {

    final ModelMapper mapper;
    final FlightReservationRepository reservationRepository;
    final ReservationNumberGenerator generator;

    public FlightReservationResponse createNew(FlightReservationRequest request) {
        FlightReservation reservation = mapper.map(request, FlightReservation.class);
        reservation.setReservationNumber(generator.generateFlightReservationNumber());

        FlightReservation savedReservation = reservationRepository.save(reservation);
        return toResponse(savedReservation);
    }

    public FlightReservationResponse findByPublicId(String publicId) {
        return null;
    }

    public boolean existsByPublicId(String publicId) {
        return false;
    }

    public Collection<FlightReservationResponse> findAll() {
        return null;
    }

    public void removeAll() {
    }

    private FlightReservationResponse toResponse(FlightReservation reservation){
        return mapper.map(reservation, FlightReservationResponse.class);
    }
}
