package app.bola.flywell.intializer;

import app.bola.flywell.security.models.Permission;
import app.bola.flywell.security.models.Role;
import app.bola.flywell.security.repositories.RoleRepository;
import app.bola.flywell.security.repositories.PermissionRepository;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class RolePermissionInitializer implements CommandLineRunner {

    RoleRepository roleRepository;
    PermissionRepository permissionRepository;

    @Override
    public void run(String... args) throws Exception {
        Permission readPermission = Permission.builder().name("READ").build();
        Permission writePermission = Permission.builder().name("WRITE").build();

        Role userRole = Role.builder().name("USER").permissions(Set.of(readPermission)).build();
        Role adminRole = Role.builder().name("ADMIN").permissions(Set.of(readPermission, writePermission)).build();

        if (roleRepository.findAll().isEmpty() && permissionRepository.findAll().isEmpty()) {
            roleRepository.saveAll(List.of(userRole, adminRole));
            permissionRepository.saveAll(List.of(readPermission, writePermission));
        }
    }
}
