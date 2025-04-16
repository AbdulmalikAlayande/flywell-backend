package app.bola.flywell.security.providers;


import app.bola.flywell.security.services.FlyWellUserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FlyWellAuthenticationProvider extends DaoAuthenticationProvider {

    public FlyWellAuthenticationProvider(PasswordEncoder passwordEncoder, FlyWellUserDetailsService userDetailsService) {
        super(passwordEncoder);
        super.setUserDetailsService(userDetailsService);
    }
}
