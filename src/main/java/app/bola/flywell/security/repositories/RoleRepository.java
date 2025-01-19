package app.bola.flywell.security.repositories;

import app.bola.flywell.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findByName(String name);
}
