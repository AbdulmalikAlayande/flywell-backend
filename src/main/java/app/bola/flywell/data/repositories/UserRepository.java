package app.bola.flywell.data.repositories;

import app.bola.flywell.basemodules.FlyWellRepository;
import app.bola.flywell.data.model.users.User;
import app.bola.flywell.security.models.Role;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends FlyWellRepository<User> {

    Optional<User> findByEmail(String email);

    void deleteUsersByRoles(Set<Role> roles);
}
