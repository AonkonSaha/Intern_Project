package com.example.spring_intro.config.security;

import com.example.spring_intro.config.filters.CustomAuthenticationFilter;
import com.example.spring_intro.repository.UserRepo;
import com.example.spring_intro.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    public static final String[] ADMIN_AUTHOR_MODERATOR_URLS = {
            "/blog/comment/create",
            "/blog/comment/update",
            "/blog/comment/delete",
            "/blog/save",
            "/blog/delete",
            "/blog/update",
            "/blog/"
    };
    public static final String[] ADMIN_URLS = {
            "/role/**"
    };
    public static final String[] PUBLIC_URLS = {
            "/blog/comment/fetch",
            "/blog/fetch",
            "/user/**",
            "/swagger-ui/**",
           "/v3/api-docs/**",
           "/swagger-ui.html"
    };
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    CustomAuthenticationFilter customAuthenticationFilter;
    @Autowired
    private  CustomUserDetailsService customUserDetailsService;
    private final UserRepo userRepo;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        http.
                csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
            .requestMatchers(ADMIN_URLS).hasRole("ADMIN")
            .requestMatchers(ADMIN_AUTHOR_MODERATOR_URLS).hasAnyRole("AUTHOR","ADMIN","MODERATOR")
            .requestMatchers(PUBLIC_URLS).permitAll()
            .anyRequest().authenticated()
    ).formLogin(form -> form.permitAll())
                .logout(logout -> logout.permitAll())
                .addFilterBefore(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));


        return http.build();
    }
}
