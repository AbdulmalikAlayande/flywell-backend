package app.bola.flywell.intializer;

import app.bola.flywell.security.models.Permission;
import app.bola.flywell.security.models.Role;
import app.bola.flywell.security.repositories.RoleRepository;
import app.bola.flywell.security.repositories.PermissionRepository;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class RolePermissionInitializer implements CommandLineRunner {

    final RoleRepository roleRepository;
    final PermissionRepository permissionRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.findAll().isEmpty()) {

            initializeAdminRoleAndPermissions();
            initializeCustomerRoleAndPermissions();
            initializeCrewMemberRoleAndPermissions();
        }
    }

    public void initializeAdminRoleAndPermissions() {

        String[] adminPermissions = {
                "CREATE_USER", "READ_USER", "UPDATE_USER", "BLOCK_USER",
                "CREATE_FLIGHT", "READ_FLIGHT", "UPDATE_FLIGHT", "DELETE_FLIGHT",
                "CREATE_AIRCRAFT", "READ_AIRCRAFT", "UPDATE_AIRCRAFT", "DELETE_AIRCRAFT",
                "READ_NOTIFICATION"
        };

        saveRoleAndPermissions(adminPermissions, "ADMIN");
    }

    public void initializeCustomerRoleAndPermissions() {

        String[] customerPermissions = {
                "CREATE_CUSTOMER", "READ_CUSTOMER", "UPDATE_CUSTOMER",
                "CREATE_RESERVATION", "READ_RESERVATION", "CANCEL_RESERVATION",
                "READ_NOTIFICATION"
        };

        saveRoleAndPermissions(customerPermissions, "CUSTOMER");
    }

    public void initializeCrewMemberRoleAndPermissions() {
        String[] crewMemberPermissions = {"READ_FLIGHT_INSTANCE"};
        saveRoleAndPermissions(crewMemberPermissions, "CREW_MEMBER");

    }

    private void saveRoleAndPermissions(String[] customerPermissions, String role) {

        List<Permission> permissions = new ArrayList<>();
        for (String permission : customerPermissions) {
            Permission build = Permission.builder().name(permission).build();
            permissions.add(build);
        }

        List<Permission> savedPermissions = permissionRepository.saveAll(permissions);
        Role savedRole = Role.builder().name(role).permissions(new HashSet<>(savedPermissions)).build();
        roleRepository.save(savedRole);
    }
}
