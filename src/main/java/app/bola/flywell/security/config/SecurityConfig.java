package app.bola.flywell.security.config;

import app.bola.flywell.data.repositories.UserRepository;
import app.bola.flywell.security.services.FlyWellUserDetailsService;
import app.bola.flywell.security.filters.JwtAuthenticationFilter;
import app.bola.flywell.security.handlers.AccessDeniedHandlerImpl;
import app.bola.flywell.security.handlers.AuthenticationEntryPointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    final JwtAuthenticationFilter jwtAuthFilter;
    final AccessDeniedHandlerImpl accessDeniedHandler;
    final AuthenticationEntryPointImpl authenticationEntryPoint;

    public SecurityConfig(@Lazy JwtAuthenticationFilter jwtAuthFilter, AccessDeniedHandlerImpl accessDeniedHandler,
                          AuthenticationEntryPointImpl authenticationEntryPoint) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers
                    .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
                    .xssProtection(HeadersConfigurer.XXssConfig::disable)
                    .contentSecurityPolicy(customizer -> customizer.policyDirectives("default-src 'self';"))
            )
            .authorizeHttpRequests(registry -> registry
                    .requestMatchers("admin/**").hasRole("ADMIN")
                    .requestMatchers("/customer/new").permitAll()
                    .requestMatchers("reservation/**").hasAnyRole("ADMIN", "USER", "OFFICER")
                    .requestMatchers("admin/**").hasRole("ADMIN")
                    .requestMatchers("auth/**").permitAll()
                    .requestMatchers("crew-member/**").hasRole("OFFICER")
                    .requestMatchers("flights/**", "flight-instance/**").authenticated()
                    .requestMatchers("reservation/**").hasAnyRole("USER", "ADMIN")
                    .anyRequest().permitAll()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(handler -> handler
                    .accessDeniedHandler(accessDeniedHandler)
                    .authenticationEntryPoint(authenticationEntryPoint)
            )
            .cors(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return new FlyWellUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://your-frontend-domain.com"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
