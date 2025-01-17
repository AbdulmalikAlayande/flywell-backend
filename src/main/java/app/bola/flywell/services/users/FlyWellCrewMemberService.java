package app.bola.flywell.services.users;

import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.data.repositories.UserBioDataRepository;
import app.bola.flywell.dto.request.UserRequest;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.services.notifications.Validator;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FlyWellCrewMemberService implements CrewMemberService {

    private ModelMapper mapper;
    private UserBioDataRepository userBioDataRepository;
    private Validator validator;


    @Override
    public FlightInstance assignCrewMember(FlightInstance instance) {
        return null;
    }

    @Override
    public FlightInstanceResponse viewFlightSchedule(String flightId) {
        return null;
    }

    @Override
    public UserRequest createNew(UserRequest request) {
        return null;
    }

    @Override
    public UserRequest findByPublicId(String publicId) {
        return null;
    }

    @Override
    public boolean existsByPublicId(String publicId) {
        return false;
    }

    @Override
    public Collection<UserRequest> findAll() {
        return List.of();
    }

    @Override
    public void removeAll() {

    }
}