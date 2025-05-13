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

import java.io.IOException;
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
            return "redirect:/home.html";
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

    @GetMapping("/home")
    @ResponseBody
    public ResponseEntity<UserDTO> getHome(){

        return ResponseEntity.ok(userMapper.toUserDTO(userService.findUserByContact(SecurityContextHolder.getContext().getAuthentication().getName())));
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

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getContact(),loginDTO.getPassword()));
        MUser user=userService.findUserByContact(loginDTO.getContact());
        String token=jwtUtils.generateToken(user.getName(),loginDTO.getContact());
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        user.setIsActive(true);
        userService.saveUser(user);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/signout")
    @ResponseBody
    public ResponseEntity<String> logoutUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok("Invalid token");
        }
        String token = authHeader.substring(7);
        String contact=jwtUtils.extractContact(token);
        MUser user=userService.findUserByContact(contact);
        user.setIsActive(false);
        userService.saveUser(user);
        return ResponseEntity.ok("Logout successfully");
    }

//    @PostMapping("/update/profile")
//    public ResponseEntity<UserDTO> updateProfileWithOutPassword(@RequestBody UserDTO userDTO) throws IOException {
//
//        return ResponseEntity.ok(
//                userMapper.toUserDTO(userService.updatePatientWithOutPassword(userDTO))
//        );
//    }


}
