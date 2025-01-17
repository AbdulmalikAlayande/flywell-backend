package app.bola.flywell.security.config;

import app.bola.flywell.data.repositories.CustomerRepository;
import app.bola.flywell.security.FlyWellUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(CustomerRepository customerRepository){
        return new FlyWellUserDetailsService(customerRepository);
    }
}
