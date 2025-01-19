package app.bola.flywell.security.repositories;

import app.bola.flywell.security.models.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
