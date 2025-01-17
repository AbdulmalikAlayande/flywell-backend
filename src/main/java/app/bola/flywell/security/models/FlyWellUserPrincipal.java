package app.bola.flywell.security.models;

import app.bola.flywell.data.model.users.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class FlyWellUserPrincipal implements UserDetails {

    private User user;
    private List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getBioData().getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPublicId();
    }
}
