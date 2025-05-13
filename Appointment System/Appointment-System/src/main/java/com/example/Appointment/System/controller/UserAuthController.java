package com.example.Appointment.System.controller;

import com.example.Appointment.System.jwt.JwtUtils;
import com.example.Appointment.System.model.dto.LoginDTO;
import com.example.Appointment.System.model.dto.PatientProfileDTO;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserAuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PatientMapper patientMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PatientService patientService;
    private final PasswordEncoder passwordEncoder;

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
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
        if(userService.isExitUserByContact(userDTO.getContact())){
            return ResponseEntity.badRequest().body("This Contact Number is already exit..");
        }
        if (userDTO.getContact().isEmpty()){
            return ResponseEntity.badRequest().body("Mobile Number can't be empty");
        }
        if (userDTO.getContact().length() != 11 ){
            return ResponseEntity.badRequest().body("Mobile Number must be 11 digits");
        }
        if (userDTO.getPassword().length() < 8 ){
            return ResponseEntity.badRequest().body("Password must be at least 8 characters long");
        }
        if(!userDTO.getEmail().contains("@")){
            return ResponseEntity.badRequest().body("Email must contain @");
        }
        Set<String> gender=Set.of("male","female","other");
        if(!gender.contains(userDTO.getGender().toLowerCase())){
            return ResponseEntity.badRequest().body("Gender must be male,female or other");
        }
        return ResponseEntity.ok(
                userMapper.toUserDTO(userService.saveUser(userMapper.toUser(userDTO)))
        );
    }
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        if(!userService.isExitUserByContact(loginDTO.getContact())){
            return ResponseEntity.badRequest().body("User doesn't exit..");
        }
        if(passwordEncoder.matches(loginDTO.getPassword(),userService.findUserByContact(loginDTO.getContact()).getPassword())==false){
            return ResponseEntity.badRequest().body("Password is incorrect..");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getContact(),loginDTO.getPassword())).isAuthenticated();
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
    public ResponseEntity<?> logoutUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Invalid token");
        }
        String token = authHeader.substring(7);
        String contact=jwtUtils.extractContact(token);
        MUser user=userService.findUserByContact(contact);
        user.setIsActive(false);
        userService.saveUser(user);
        return ResponseEntity.ok("Logout successfully");
    }

    @PostMapping("/update/profile")
    public ResponseEntity<UserDTO> updateProfileWithOutPassword(@RequestBody PatientProfileDTO patientProfileDTO) throws IOException {

        return ResponseEntity.ok(
                userMapper.toUserDTO(userService.updatePatientWithOutPassword(patientProfileDTO))
        );
    }


}
