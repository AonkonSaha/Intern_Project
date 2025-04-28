package com.example.spring_intro.service;

import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserRole;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByUserName(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found...");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        for (UserRole userRole : user.get().getUserRole()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(
                user.get().getUserName(),
                user.get().getPassword(),
                authorities
        );
    }
}

