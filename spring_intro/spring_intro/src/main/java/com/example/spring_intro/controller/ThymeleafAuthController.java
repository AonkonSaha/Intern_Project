package com.example.spring_intro.controller;

import com.example.spring_intro.jwt.JWTUtils;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserRole;
import com.example.spring_intro.model.mapper.UserMapper;
import com.example.spring_intro.repository.UserRepo;
import com.example.spring_intro.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ThymeleafAuthController {
    private final AuthService authService;
    private final UserMapper userMapper;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtils jwtUtils;

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setActiveStatus(false);
        user.setContact("01881264859");
        user.setEmail("aonkon@gmail.com");
        userRepo.save(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {

        Optional<User> userOptional = userRepo.findByUserName(username);
        if (userOptional.isPresent() && new BCryptPasswordEncoder().matches(password, userOptional.get().getPassword())) {
            String role="";

            for(UserRole userRole:userOptional.get().getUserRole())
            {
                role=userRole.getRole();
                break;
            }

            String token = jwtUtils.generateToken(username);
            userOptional.get().setActiveStatus(true);
            userRepo.save(userOptional.get());
            response.addHeader("Authorization", "Bearer " + token);

            return "redirect:/home";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }










    //    @GetMapping("/test")
//    public String test()
//    {
//        return "test";
//    }
//    @GetMapping("/test1")
//    public String test1()
//    {
//        return "test1";
//    }
//
//    @GetMapping("/")
//    String getHome()
//    {
//        return "home";
//    }
//
//    @GetMapping("/login")
//    public String loginPage() {
//        return "login";
//    }
//
//    @GetMapping("/register")
//    String getRegister()
//    {
//        return "register";
//    }

//    @PostMapping("/register")
//    public String register(@Valid @ModelAttribute("user") UserDTO userDTO) throws UserNotFoundException {
//        if (userRepo.existsByUserName(userDTO.getUserName())) {
//            throw new UserNotFoundException("User already exists");
//        }
//        if (userRepo.existsByEmail(userDTO.getEmail())) {
//            throw new RuntimeException("email already exists");
//        }
//        authService.register(userMapper.toUser(userDTO));
//        return "redirect:/";
//    }




//    @PostMapping("/logout")
//    public String logout(HttpServletRequest request) {
//        authService.logout(request);
//        return "redirect:/";
//    }
}
