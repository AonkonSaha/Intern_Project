package com.example.spring_intro.controller;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.jwt.JWTUtils;
import com.example.spring_intro.model.dto.LoginDTO;
import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.mapper.UserMapper;
import com.example.spring_intro.service.AuthService;
import com.example.spring_intro.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v5/auth/jwt")
public class JWTAuthController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final JWTUtils jwtUtils;
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        if(userService.isUserNameExit(userDTO.getUserName())) {
            throw new RuntimeException("User already exists");
        }
        if(userService.isUserEmailExit(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return ResponseEntity.ok(userMapper.toUserDTO(userService.saveUser(userMapper.toUser(userDTO))));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO) throws Exception, UserNotFoundException {
        if(!userService.isUserNameExit(loginDTO.getUserName()))
        {
            throw new UserNotFoundException("User doesn't exit..!");
        }
        authService.isAuthenticate(loginDTO);
        String token=jwtUtils.generateToken(loginDTO.getUserName());
        User user=userService.getUserByName(loginDTO.getUserName());
        user.setActiveStatus(true);
        userService.saveUser(user);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/signout")
    @PreAuthorize("hasAnyRole('ADMIN','USER','AUTHOR','MODERATOR')")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) throws UserNotFoundException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok("Invalid token");
        }
//        SecurityContextHolder.clearContext();
        String token = authHeader.substring(7);
        String username=jwtUtils.extractUsername(token);
        User user=userService.getUserByName(username);
        user.setActiveStatus(false);
        userService.saveUser(user);
        return ResponseEntity.ok("Logout Successfully!");
    }



}
