package app.bola.flywell.services.flightservice;

import app.bola.flywell.data.model.Passenger;
import app.bola.flywell.data.model.enums.FlightStatus;
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
import app.bola.flywell.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
                .orElseThrow(() -> new EntityNotFoundException("FlightInstance with Id " + request.getFlightId()));

        FlightReservation reservation = new FlightReservation();
        reservation.setReservationNumber(generator.generateFlightReservationNumber());
        reservation.setCreationDate(LocalDate.now());
        reservation.setStatus(ReservationStatus.PENDING);
        reservation.setFlightInstance(flightInstance);

        mapRequestToReservation(request, flightInstance, reservation);
        FlightReservation savedReservation = reservationRepository.save(reservation);

        flightInstance.addReservation(savedReservation);
        flightInstanceRepository.save(flightInstance);

        return toResponse(savedReservation);
    }

    private void mapRequestToReservation(FlightReservationRequest request, FlightInstance flightInstance, FlightReservation reservation) {
        request.getSeatMap().forEach(( seatId, passengerRequest) -> {

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
        return reservationRepository.findByPublicId(publicId)
                .map(flightReservation -> mapper.map(flightReservation, FlightReservationResponse.class))
                .orElse(null);
    }

    public boolean existsByPublicId(String publicId) {
        return reservationRepository.existsByPublicId(publicId);
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

    @Override
    public Collection<FlightReservationResponse> findAll(Pageable pageable) {
        return List.of();
    }

    private FlightReservationResponse toResponse(FlightReservation reservation){
        return mapper.map(reservation, FlightReservationResponse.class);
    }

    @Override
    public FlightReservationResponse cancelReservation(String flightId, String reservationId) {
        FlightInstance flightInstance = flightInstanceRepository.findByPublicId(flightId)
                .orElseThrow(() -> new EntityNotFoundException("FlightInstance with Id " + flightId));

        boolean isDeparted = flightInstance.getStatus() == FlightStatus.EN_ROUTE ||
                flightInstance.getStatus() == FlightStatus.LANDED;

        if (flightInstance.getDepartureTime().isBefore(LocalDateTime.now()) && isDeparted) {
            throw new IllegalStateException("Cannot cancel reservation for a past flight");
        }

        FlightReservation flightReservation = flightInstance.getReservations().stream()
                .filter(reservation -> Objects.equals(reservation.getPublicId(), reservationId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("FlightReservation with Id " + reservationId));

        if (flightReservation.getStatus() == ReservationStatus.CANCELLED) {
            return toResponse(flightReservation);
        }

        flightReservation.setStatus(ReservationStatus.CANCELLED);
        flightReservation.getSeatMap().forEach((passenger, flightSeat) -> {
            flightSeat.setReservationNumber(null);
            flightSeat.setSeatStatus(SeatStatus.EMPTY);
        });

        flightInstance.getReservations().remove(flightReservation);
        seatRepository.saveAll(flightInstance.getSeats());
        reservationRepository.save(flightReservation);
        flightInstanceRepository.save(flightInstance);

        return toResponse(flightReservation);
    }

    @Override
    public FlightReservationResponse updateReservationStatus(String flightId, String reservationId) {

        FlightInstance flightInstance = flightInstanceRepository.findByPublicId(flightId)
                .orElseThrow(EntityNotFoundException::new);

        FlightReservation flightReservation = flightInstance.getReservations().stream()
                .filter(reservation -> Objects.equals(reservation.getPublicId(), reservationId))
                .distinct().findAny()
                .orElseThrow(() -> new EntityNotFoundException("FlightReservation with Id " + reservationId));

        if (flightReservation.getStatus() == ReservationStatus.CANCELLED) {
            throw new IllegalStateException("Cannot update status for a cancelled reservation");
        }

        flightReservation.setStatus(ReservationStatus.RESERVED);

        FlightReservation updatedReservation = reservationRepository.save(flightReservation);
        return toResponse(updatedReservation);
    }
}
