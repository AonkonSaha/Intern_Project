package com.example.spring_intro.controller;

import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UIDesignController {

  private final UserRepo userRepository;
  private final PasswordEncoder passwordEncoder;
    @GetMapping("/")
    String getHome()
    {
        return "home";
    }
    @GetMapping("/login")
    String getLogin()
    {
        return "login";
    }
    @GetMapping("/register")
    String getRegister()
    {
        return "register";
    }
    @PostMapping("/register")
    public String processRegistration(@RequestParam("user_name") String userName,
                                      @RequestParam("email") String email,
                                      @RequestParam("contact") String contact,
                                      @RequestParam("password") String password,
                                      @RequestParam("confirmPassword") String confirmPassword,
                                      Model model) {

        if (!password.equals(confirmPassword)) {
            return "redirect:/register?error";
        }

        if (userRepository.existsByEmail(email)) {
            return "redirect:/register?error";
        }

        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setContact(contact);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return "redirect:/login";
    }

    @GetMapping("/blog")
    String getBlog()
    {
        return "blog";
    }
}
