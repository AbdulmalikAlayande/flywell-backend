package app.bola.flywell.security.services;

import app.bola.flywell.data.model.users.User;
import app.bola.flywell.data.repositories.UserRepository;
import app.bola.flywell.exceptions.EntityNotFoundException;
import app.bola.flywell.security.models.FlyWellUserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlyWellUserDetailsService implements UserDetailsService {

    final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPublicId(username)
                .orElseThrow(() -> new EntityNotFoundException("User with email" + username));

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("Role_"+user.getRole().name()));
        return new FlyWellUserPrincipal(user, authorities);
    }
}
