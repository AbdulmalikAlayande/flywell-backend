package app.bola.flywell.security.models;

import app.bola.flywell.data.model.persons.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.pulsar.shade.org.glassfish.jersey.internal.inject.Custom;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FlyWellUserPrincipal implements UserDetails {
    private Customer customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return customer.getBioData().getPassword();
    }

    @Override
    public String getUsername() {
        return customer.getPublicId();
    }
}
