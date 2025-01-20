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

    }

    public void initializeAdminRoleAndPermissions() {
        //READ_USER, UPDATE_USER,
        String[] adminPermissions = {"READ_USER"};
    }

    public void initializeUserRoleAndPermissions() {
        String[] userPermissions = {"READ_USER", "UPDATE_USER", "CREATE_CUSTOMER", "READ_CUSTOMER",
                "CREATE_RESERVATION", "READ_RESERVATION", "READ_NOTIFICATION"};

    }

    public void initializeCustomerRoleAndPermissions() {
        String[] customerPermissions = {"READ", "WRITE"};

    }
}
