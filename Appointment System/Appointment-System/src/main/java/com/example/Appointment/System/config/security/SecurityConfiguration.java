package com.example.Appointment.System.config.security;


import com.example.Appointment.System.jwt.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    JwtAuthFilter jwtAuthFilter;
    public static final String[] ADMIN_URLS = {
            "/api/diagnostic/center/**",
            "/api/user/role/**"
    };
    public static final String[] PATIENT_URLS = {
            "/api/patient/**",
            "/api/doctor/booking/**",
            "/api/lab/test/booking"

    };
    public static final String[] DOCTOR_URLS = {
            "/api/doctor/**",
    };

    public static final String[] PUBLIC_URLS = {
            "/api/user/signup",
            "/api/user/signin",
            "/api/user/signout",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-ui.html",
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        System.out.println("I am in Security Filer Chain");
        http.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PATIENT_URLS).hasAnyRole("PATIENT","ADMIN")
                        .requestMatchers(ADMIN_URLS).hasAnyRole("ADMIN")
                        .requestMatchers(DOCTOR_URLS).hasAnyRole("DOCTOR","ADMIN")
                        .requestMatchers(PUBLIC_URLS).permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
