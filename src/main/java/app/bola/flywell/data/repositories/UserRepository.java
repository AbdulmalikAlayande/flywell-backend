package app.bola.flywell.data.repositories;

import app.bola.flywell.basemodules.FlyWellRepository;
import app.bola.flywell.data.model.users.User;

import java.util.Optional;

public interface UserRepository extends FlyWellRepository<User> {

    Optional<User> findByEmail(String email);
}
