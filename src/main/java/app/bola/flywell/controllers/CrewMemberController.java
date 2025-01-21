package app.bola.flywell.controllers;

import app.bola.flywell.basemodules.FlyWellController;
import app.bola.flywell.dto.request.UserRequest;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.dto.response.UserResponse;
import app.bola.flywell.services.flightservice.FlightInstanceService;
import app.bola.flywell.services.users.CrewMemberService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("crew-member")
@AllArgsConstructor
public class CrewMemberController implements FlyWellController<UserRequest, UserResponse> {

    final CrewMemberService crewMemberService;
    final FlightInstanceService flightInstanceService;

    @Override
    @PreAuthorize(value = "hasRole['ADMIN']")
    public ResponseEntity<UserResponse> createNew(UserRequest userRequest) {
        UserResponse response = crewMemberService.createNew(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize(value = "hasRole['CREW_MEMBER']")
    @GetMapping("/{flight-id}/view-schedule")
    public ResponseEntity<FlightInstanceResponse> viewFlightSchedule(@PathVariable("flight-id") String flightId){
        FlightInstanceResponse response = flightInstanceService.viewFlightSchedule(flightId);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Collection<UserResponse>> findAll(Pageable pageable) {
        return null;
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'CREW_MEMBER')")
    public ResponseEntity<UserResponse> findByPublicId(String publicId) {
        return null;
    }

    @Override
    @PreAuthorize(value = "hasRole('ADMIN')")
    public ResponseEntity<Collection<UserResponse>> findAll() {
        return null;
    }
}
