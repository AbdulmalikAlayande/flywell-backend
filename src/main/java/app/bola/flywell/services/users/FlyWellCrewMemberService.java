package app.bola.flywell.services.users;

import app.bola.flywell.data.model.flight.FlightInstance;
import app.bola.flywell.data.model.users.User;
import app.bola.flywell.data.repositories.UserRepository;
import app.bola.flywell.data.specifications.UserSpecification;
import app.bola.flywell.dto.request.UserRequest;
import app.bola.flywell.dto.response.FlightInstanceResponse;
import app.bola.flywell.dto.response.UserResponse;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.security.repositories.RoleRepository;
import app.bola.flywell.utils.Constants;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class FlyWellCrewMemberService implements CrewMemberService {

    final ModelMapper mapper;
    final RoleRepository roleRepository;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final UserServiceCommonImplementationProvider<UserRequest> implementationProvider;


    @Override
    public UserResponse createNew(UserRequest request) {

        implementationProvider.validateFields(request);
        User crewMember = mapper.map(request, User.class);
        crewMember.setPassword(passwordEncoder.encode(request.getPassword()));
        crewMember.getRoles().add(roleRepository.findByName("CREW_MEMBER").getFirst());

        User savedCrewMember = userRepository.save(crewMember);
        return toResponse(savedCrewMember);
    }

    @Override
    public FlightInstance assignCrewMember(FlightInstance flightInstance, String crewMemberId) {
        Specification<User> spec = Specification.where(UserSpecification.hasRole("CREW_MEMBER"));

        User crewMember = userRepository.findAll(spec)
                                       .stream()
                                       .filter(user -> Objects.equals(user.getPublicId(), crewMemberId))
                                       .findAny()
                                       .orElseThrow(() -> new EntityNotFoundException(Constants.ENTITY_NOT_FOUND.formatted("User")));

        flightInstance.addCrewMember(crewMember);
        return flightInstance;
    }

    @Override
    public FlightInstanceResponse viewFlightSchedule(String flightId) {

        return null;
    }

    private UserResponse toResponse(User savedCrewMember) {
        return mapper.map(savedCrewMember, UserResponse.class);
    }

    @Override
    public UserResponse findByPublicId(String publicId) {
        return null;
    }

    @Override
    public boolean existsByPublicId(String publicId) {
        return false;
    }

    @Override
    public Collection<UserResponse> findAll() {
        return List.of();
    }

    @Override
    public void removeAll() {

    }

    @Override
    public Collection<UserResponse> findAll(Pageable pageable) {
        return List.of();
    }
}