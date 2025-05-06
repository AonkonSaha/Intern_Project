package com.example.Appointment.System.controller;

import com.example.Appointment.System.jwt.JwtUtils;
import com.example.Appointment.System.model.dto.LoginDTO;
import com.example.Appointment.System.model.dto.PatientDTO;
import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.entity.Patient;
import com.example.Appointment.System.model.mapper.PatientMapper;
import com.example.Appointment.System.model.mapper.UserMapper;
import com.example.Appointment.System.repository.PatientRepo;
import com.example.Appointment.System.service.PatientService;
import com.example.Appointment.System.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PatientMapper patientMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PatientService patientService;
    @PostMapping("/signup")
    public ResponseEntity<PatientDTO> registerUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.ok(
               patientMapper.toPatientDTO(userService.saveUser(userMapper.toUser(userDTO)))
        );
    }
    @PostMapping("/signin")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO){
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        String token=jwtUtils.generateToken(loginDTO.getUsername());
        MUser user=userService.getUserByName(loginDTO.getUsername());
        Patient patient=patientService.getPatientByName(user.getName());
        user.setIsActive(true);
        userService.saveUser(user);
        patientService.savePatient(patient);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/signout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.ok("Invalid token");
        }
        String token = authHeader.substring(7);
        String username=jwtUtils.extractUsername(token);
        MUser user=userService.getUserByName(username);
        user.setIsActive(false);
        Patient patient=patientService.getPatientByName(username);
        patient.setIsActive(false);
        userService.saveUser(user);
        patientService.savePatient(patient);
        return ResponseEntity.ok("Logout successfully");
    }

}
