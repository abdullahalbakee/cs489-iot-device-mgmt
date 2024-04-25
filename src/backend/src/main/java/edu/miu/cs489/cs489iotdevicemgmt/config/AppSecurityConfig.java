package edu.miu.cs489.cs489iotdevicemgmt.config;

import edu.miu.cs489.cs489iotdevicemgmt.filter.JwtAuthFilter;
import edu.miu.cs489.cs489iotdevicemgmt.service.security.AppUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    private final AppUserDetailsService appUserDetailsService;
    private final JwtAuthFilter jwtAuthFilter;

    public AppSecurityConfig(AppUserDetailsService appUserDetailsService, JwtAuthFilter jwtAuthFilter) {
        this.appUserDetailsService = appUserDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(
                        auth -> {
                            auth
                                    .requestMatchers("/swagger-ui/**").permitAll()
                                    .requestMatchers("/v3/**").permitAll()

                                    .requestMatchers("/login").permitAll()
                                    // /clients
//                                    .requestMatchers(HttpMethod.GET,"/clients").hasRole(ROLE_ADMIN)
//                                    .requestMatchers(HttpMethod.POST, "/clients").permitAll()//.hasRole(ROLE_CLIENT)
//                                    .requestMatchers(HttpMethod.PUT, "/clients").hasRole(ROLE_CLIENT)
//                                    .requestMatchers(HttpMethod.DELETE,"/clients").hasRole(ROLE_ADMIN)

                                    // /devices
//                                    .requestMatchers("/devices").hasRole(ROLE_CLIENT)

                                    // /measurements
//                                    .requestMatchers(HttpMethod.GET, "/measurements").hasRole(ROLE_CLIENT)
//                                    .requestMatchers(HttpMethod.POST, "/measurements").hasRole(ROLE_DEVICE)
//                                    .requestMatchers(HttpMethod.PUT, "/measurements").hasRole(ROLE_CLIENT)
//                                    .requestMatchers(HttpMethod.DELETE, "/measurements").hasRole(ROLE_CLIENT)

                                    // default
                                    .requestMatchers("/**").permitAll()
                            ;

                        }
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(appUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
