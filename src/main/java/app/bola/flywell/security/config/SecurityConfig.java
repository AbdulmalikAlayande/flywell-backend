package app.bola.flywell.security.config;

import app.bola.flywell.security.providers.FlyWellAuthenticationProvider;
import app.bola.flywell.security.services.FlyWellUserDetailsService;
import app.bola.flywell.security.filters.FlyWellAuthorizationFilter;
import app.bola.flywell.security.handlers.AccessDeniedHandlerImpl;
import app.bola.flywell.security.handlers.AuthenticationEntryPointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import java.util.Arrays;
//import java.util.List;
//
@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig{

    final FlyWellAuthorizationFilter jwtAuthFilter;
    final AccessDeniedHandlerImpl accessDeniedHandler;
    final AuthenticationEntryPointImpl authenticationEntryPoint;

    public SecurityConfig(FlyWellAuthorizationFilter jwtAuthFilter, AccessDeniedHandlerImpl accessDeniedHandler,
                          AuthenticationEntryPointImpl authenticationEntryPoint) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.cors(AbstractHttpConfigurer::disable)
//            .csrf(AbstractHttpConfigurer::disable)
//            .headers(headers -> headers
//                    .frameOptions(HeadersConfigurer.FrameOptionsConfig::deny)
//                    .xssProtection(HeadersConfigurer.XXssConfig::disable)
//                    .contentSecurityPolicy(customizer -> customizer.policyDirectives("default-src 'self';"))
//            )
//            .authorizeHttpRequests(registry -> registry
//            	    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                    .requestMatchers("/customer/new").permitAll()
//                    .requestMatchers("/customer/activate-account/{public-id}/{otp}").permitAll()
//                    .requestMatchers("/auth/**").permitAll()
//                    .requestMatchers("/producer/**").permitAll()
//                    .requestMatchers("/admin/**").hasRole("ADMIN")
//                    .requestMatchers("/reservation/**").hasAnyRole("ADMIN", "USER", "OFFICER")
//                    .requestMatchers("/crew-member/**").hasRole("OFFICER")
//                    .requestMatchers("/flights/**").hasRole("ADMIN")
//                    .requestMatchers("/flight-instance/**").hasAnyRole("ADMIN", "CUSTOMER")
//                    .anyRequest().authenticated()
//            )
//            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//            .exceptionHandling(handler -> handler
//                    .accessDeniedHandler(accessDeniedHandler)
//                    .authenticationEntryPoint(authenticationEntryPoint)
//            );
//        return http.build();
//    }
//
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
//
    @Bean
    public AuthenticationProvider authenticationProvider(FlyWellUserDetailsService userDetailsService){
        return new FlyWellAuthenticationProvider(passwordEncoder(), userDetailsService);
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOrigins(Arrays.asList(
//                "https://flywell.tech",
//                "http://localhost:3000",
//                "https://www.flywell.tech",
//                "https://flywell.vercel.app"
//        ));
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
//        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept", "X-Requested-With", "Requestor-Type"));
//        configuration.setAllowCredentials(true);
//        configuration.setExposedHeaders(Arrays.asList(
//                "Access-Control-Allow-Origin",
//                "Access-Control-Allow-Credentials",
//                "Access-Control-Allow-Methods",
//                "Access-Control-Allow-Headers"
//        ));
//        configuration.setMaxAge(3600L);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
}
