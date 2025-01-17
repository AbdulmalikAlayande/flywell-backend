package app.bola.flywell.security;

import app.bola.flywell.data.model.persons.Customer;
import app.bola.flywell.data.repositories.CustomerRepository;
import app.bola.flywell.security.models.FlyWellUserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
@AllArgsConstructor
public class FlyWellUserDetailsService implements UserDetailsService {

    final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByPublicId(username).orElseThrow();
        return null;
    }
}
