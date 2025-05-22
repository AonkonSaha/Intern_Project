package com.example.Appointment.System.controller;

import com.example.Appointment.System.jwt.JwtUtils;
import com.example.Appointment.System.model.dto.LoginDTO;
import com.example.Appointment.System.model.dto.PasswordDTO;
import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.mapper.PatientMapper;
import com.example.Appointment.System.model.mapper.UserMapper;
import com.example.Appointment.System.service.Imp.PatientServiceImp;
import com.example.Appointment.System.service.Imp.UserServiceImp;
import com.example.Appointment.System.service.PatientService;
import com.example.Appointment.System.service.UserService;
import com.example.Appointment.System.service.UserValidationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserAuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserValidationService userValidationService;

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
       if(!userService.validateUserDetails(userDTO).isEmpty()){
           return ResponseEntity.badRequest().body(userService.validateUserDetails(userDTO));
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
        if(!passwordEncoder.matches(loginDTO.getPassword(), userService.findUserByContact(loginDTO.getContact()).getPassword())){
            return ResponseEntity.badRequest().body("Password is incorrect..");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getContact(),loginDTO.getPassword()));
        MUser user= userService.findUserByContact(loginDTO.getContact());
        String token=jwtUtils.generateToken(user.getName(),loginDTO.getContact());
        user.setIsActive(true);
        userService.saveUser(user);
        return ResponseEntity.ok(Map.of("token",token));
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
        MUser user= userService.findUserByContact(contact);
        user.setIsActive(false);
        userService.saveUser(user);
        return ResponseEntity.ok("Logout successfully");
    }

    @PostMapping("/update/profile")
    @ResponseBody
    public ResponseEntity<?> updateProfileWithOutPassword(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam String dob,
            @RequestParam String gender,
            @RequestParam(required = false) MultipartFile photo
    ) throws IOException {
       if(!userValidationService.isValidGender(gender).isEmpty()){
           return ResponseEntity.badRequest().body(userValidationService.isValidGender(gender));
       }
        return ResponseEntity.ok(
                userMapper.toUserDTO(userService.updatePatientWithOutPassword(
                        fullName,email,dob,gender,photo)));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUserById(@PathVariable Long id){
        userService.deleteUser(id);
         return ResponseEntity.ok("Deleted successfully");
    }
    @GetMapping("/fetch/user")
    @ResponseBody
    public ResponseEntity<?> fetchUser(){
        String contact=SecurityContextHolder.getContext().getAuthentication().getName();
        if(!userService.isExitUserByContact(contact)){
            return ResponseEntity.badRequest().body("User doesn't exit..");
        }
        return ResponseEntity.ok(userMapper.toUserDTO(userService.findUserByContact(contact)));
    }

    @PostMapping("/user/change-password")
    @ResponseBody
    public ResponseEntity<?> updateUserPassword(@RequestBody PasswordDTO passwordDTO){

        String contact=SecurityContextHolder.getContext().getAuthentication().getName();
        if (!passwordEncoder.matches(passwordDTO.getPassword(), userService.findUserByContact(contact).getPassword())){
            return ResponseEntity.badRequest().body("Current Password is incorrect..");
        }
        if(!userValidationService.isExitUserByContact(contact).isEmpty()){
            return ResponseEntity.badRequest().body(userValidationService.isExitUserByContact(contact));
        }
        if (!Objects.equals(passwordDTO.getNewPassword(), passwordDTO.getConfirmPassword())){
            return ResponseEntity.badRequest().body("Password doesn't match..");
        }
        if (!userValidationService.isValidUserPasswordLength(passwordDTO.getNewPassword()).isEmpty()){
            return ResponseEntity.badRequest().body(userValidationService.isValidUserPasswordLength(passwordDTO.getNewPassword()));
        }
        userService.updateUserPassword(contact,passwordDTO);
        return ResponseEntity.ok("Password updated successfully");
    }


}
