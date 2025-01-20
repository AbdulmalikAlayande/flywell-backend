package app.bola.flywell.controllers;

import app.bola.flywell.basemodules.FlyWellController;
import app.bola.flywell.dto.request.UserRequest;
import app.bola.flywell.dto.response.UserResponse;
import app.bola.flywell.services.users.CrewMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("crew-member")
@AllArgsConstructor
public class CrewMemberController implements FlyWellController<UserRequest, UserResponse> {

    final CrewMemberService crewMemberService;

    @Override
    @PreAuthorize(value = "hasRole['ADMIN']")
    public ResponseEntity<UserResponse> createNew(UserRequest userRequest) {
        UserResponse response = crewMemberService.createNew(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<UserResponse> findByPublicId(String publicId) {
        return null;
    }

    @Override
    public ResponseEntity<Collection<UserResponse>> findAll() {
        return null;
    }
}
