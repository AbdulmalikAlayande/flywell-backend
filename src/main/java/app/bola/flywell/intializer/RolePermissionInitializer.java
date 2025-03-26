package app.bola.flywell.intializer;

import app.bola.flywell.security.models.Permission;
import app.bola.flywell.security.models.Role;
import app.bola.flywell.security.repositories.RoleRepository;
import app.bola.flywell.security.repositories.PermissionRepository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class RolePermissionInitializer implements CommandLineRunner {

    final RoleRepository roleRepository;
    final PermissionRepository permissionRepository;
    final Logger logger = LoggerFactory.getLogger(RolePermissionInitializer.class);

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

    private void saveRoleAndPermissions(String[] customerPermissions, String roleName) {

        List<Permission> permissions = new ArrayList<>();
        for (String permission : customerPermissions) {
            Permission build = Permission.builder().name(permission).build();
            permissions.add(build);
        }

        logger.info("Permissions {}", permissions);

        Role role = Role.builder().name(roleName).permissions(new HashSet<>(permissions)).build();
        logger.info("Role: {}", role);
        Role savedRole = roleRepository.save(role);
        logger.info("Saved Role: {}", savedRole);
    }
}
