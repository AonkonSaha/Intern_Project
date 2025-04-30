package com.example.spring_intro.service;

import com.example.spring_intro.config.filters.jwt.JWTUtils;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.LoginDTO;
import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.mapper.UserMapper;
import com.example.spring_intro.repository.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.OptionalInt;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;
    private final UserMapper userMapper;
    public void register(User user) {
        userRepo.save(user);
    }

    public String login(LoginDTO loginDTO) throws UserNotFoundException {
        Optional<User> user = userRepo.findByUserName(loginDTO.getUserName());
        if(user.isEmpty())
        {
            throw new UserNotFoundException("User doesn't exit..!");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        user.get().setActiveStatus(true);
        userRepo.save(user.get());
        return jwtUtils.generateToken(user.get().getUserName(),user.get().getActiveStatus(),user.get().getUserRole().toString());
    }
    public void logout(HttpServletRequest request)
    {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid token");
        }
        String token = authHeader.substring(7);
        String username=jwtUtils.extractUsername(token);
        User user=userRepo.findByUserName(username).orElseThrow();
        user.setActiveStatus(false);
        userRepo.save(user);
    }

}
