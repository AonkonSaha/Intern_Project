package com.example.Appointment.System.controller;

import com.example.Appointment.System.jwt.JwtUtils;
import com.example.Appointment.System.model.dto.LoginDTO;
import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.mapper.PatientMapper;
import com.example.Appointment.System.model.mapper.UserMapper;
import com.example.Appointment.System.service.PatientService;
import com.example.Appointment.System.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PatientMapper patientMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PatientService patientService;

    @GetMapping("/")
    public String startPage(HttpServletRequest request){
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return "redirect:/home";
        }
        return "redirect:/login";
    }
    @GetMapping("/register")
    public String registerUser(){
       return "register";
    }
    @GetMapping("/login")
    public String loginUser(){
        return "login";
    }

//    @GetMapping("/home")
//    public String getHome(){
//        return "home";
//    }
    @GetMapping("/home")
    @ResponseBody
    public ResponseEntity<UserDTO> getHome(){

        return ResponseEntity.ok(userMapper.toUserDTO(userService.getUserByName(SecurityContextHolder.getContext().getAuthentication().getName())));
    }


    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(
                userMapper.toUserDTO(userService.saveUser(userMapper.toUser(userDTO)))
        );
    }
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String,String>> loginUser(@RequestBody LoginDTO loginDTO){

        System.out.println("UserName: "+loginDTO.getUsername());
        System.out.println("Password: "+loginDTO.getPassword());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        String token=jwtUtils.generateToken(loginDTO.getUsername());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        MUser user=userService.getUserByName(loginDTO.getUsername());
        user.setIsActive(true);
        userService.saveUser(user);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<String> logoutUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok("Invalid token");
        }
        String token = authHeader.substring(7);
        String username=jwtUtils.extractUsername(token);
        MUser user=userService.getUserByName(username);
        user.setIsActive(false);
        userService.saveUser(user);
        return ResponseEntity.ok("Logout successfully");
    }


}
