package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.enums.ReservationStatus;
import app.bola.flywell.data.model.enums.SeatStatus;
import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.data.model.flight.FlightReservation;
import app.bola.flywell.data.model.flight.FlightSeat;
import app.bola.flywell.data.repositories.*;
import app.bola.flywell.dto.request.FlightReservationRequest;
import app.bola.flywell.dto.response.FlightReservationResponse;
import app.bola.flywell.generator.ReservationNumberGenerator;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@AllArgsConstructor
public class FlyWellFlightReservationService implements FlightReservationService {

    final ModelMapper mapper;
    final FlightSeatRepository seatRepository;
    final FlightReservationRepository reservationRepository;
    final FlightInstanceRepository flightInstanceRepository;
    final ReservationNumberGenerator generator;
    final PassengerRepository passengerRepository;
    final Logger logger = LoggerFactory.getLogger(FlyWellFlightReservationService.class);

    @PostConstruct
    private void configureModelMapper() {

    }

    @Override
    @Transactional
    public FlightReservationResponse createNew(FlightReservationRequest request) {

        FlightInstance flightInstance = flightInstanceRepository.findByPublicId(request.getFlightId())
                .orElseThrow(EntityNotFoundException::new);

        FlightReservation reservation = new FlightReservation();
        reservation.setReservationNumber(generator.generateFlightReservationNumber());
        reservation.setCreationDate(LocalDate.now());
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setFlightInstance(flightInstance);

//        mapRequestToReservation(request, flightInstance, reservation);
        FlightReservation savedReservation = reservationRepository.save(reservation);

        flightInstance.addReservation(savedReservation);
        flightInstanceRepository.save(flightInstance);

        return toResponse(savedReservation);
    }

    private void mapRequestToReservation(FlightReservationRequest request, FlightInstance flightInstance, FlightReservation reservation) {
        request.getSeatMap().forEach((passengerRequest, seatId) -> {

            Passenger passenger = mapper.map(passengerRequest, Passenger.class);
            Passenger savedPassenger = passengerRepository.save(passenger);

            FlightSeat flightSeat = flightInstance.getSeats()
                    .stream()
                    .filter(seat -> Objects.equals(seat.getSeatReference().getSeatNumber(), seatId))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Seat not available"));

            if (flightSeat.getSeatStatus() != SeatStatus.EMPTY) {
                throw new IllegalArgumentException("Seat " + seatId + " is already taken");
            }

            flightSeat.setSeatStatus(SeatStatus.OCCUPIED);
            flightSeat.setReservationNumber(reservation.getReservationNumber());
            FlightSeat flightSeatUpdated = seatRepository.save(flightSeat);
            reservation.getSeatMap().put(savedPassenger, flightSeatUpdated);
        });
    }

    public FlightReservationResponse findByPublicId(String publicId) {
        return null;
    }

    public boolean existsByPublicId(String publicId) {
        return false;
    }

    public Collection<FlightReservationResponse> findAll() {
        return reservationRepository.findAll()
                .stream()
                .map(reservation -> mapper.map(reservation, FlightReservationResponse.class))
                .toList();
    }

    public void removeAll() {
        reservationRepository.deleteAll();
    }

    private FlightReservationResponse toResponse(FlightReservation reservation){
        return mapper.map(reservation, FlightReservationResponse.class);
    }

}
