package com.example.spring_intro.config.security;

import com.example.spring_intro.jwt.filter.JWTAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    @Autowired
    private JWTAuthFilter jwtAuthFilter;
    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    public static final String[] ADMIN_AUTHOR_MODERATOR_URLS = {
            "/api/v3/blog/comment/create",
            "/api/v3/blog/comment/update",
            "/api/v3/blog/comment/delete",
            "/api/v2/blog/save",
            "/api/v2/blog/delete",
            "/api/v2/blog/update",
            "/api/v2/blog/"
    };
    public static final String[] ADMIN_URLS = {
            "/api/v4/role/**"
    };
    public static final String[] PUBLIC_URLS = {
            "/api/v3/blog/comment/fetch",
            "/api/v2/blog/fetch",
            "/api/v1/user/**",
            "/swagger-ui/**",
           "/v3/api-docs/**",
           "/swagger-ui.html",
            "/api/v5/auth/jwt/signup",
            "/api/v5/auth/jwt/signin"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        System.out.println("I am in Security Filer Chain");
        http.
                csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
            .requestMatchers(ADMIN_URLS).hasRole("ADMIN")
            .requestMatchers(ADMIN_AUTHOR_MODERATOR_URLS).hasAnyRole("AUTHOR","ADMIN","MODERATOR")
            .requestMatchers(PUBLIC_URLS).permitAll()
            .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
