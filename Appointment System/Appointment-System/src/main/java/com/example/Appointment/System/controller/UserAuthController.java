package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.UserNotFoundException;
import com.example.Appointment.System.jwt.JwtUtils;
import com.example.Appointment.System.model.dto.LoginDTO;
import com.example.Appointment.System.model.dto.PatientDTO;
import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.entity.PatientProfile;
import com.example.Appointment.System.model.mapper.PatientMapper;
import com.example.Appointment.System.model.mapper.UserMapper;
import com.example.Appointment.System.service.PatientService;
import com.example.Appointment.System.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserAuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final PatientMapper patientMapper;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final PatientService patientService;
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO){
        if(userService.isExitUserByContact(userDTO.getEmail())){
            throw new IllegalArgumentException("This Contact Number is already exit..");
        }
        return ResponseEntity.ok(
               userMapper.toUserDTO(userService.saveUser(userMapper.toUser(userDTO)))
        );
    }
    @PostMapping("/signin")
    public ResponseEntity<String> loginUser(@RequestBody LoginDTO loginDTO){
       authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(loginDTO.getUsername(),loginDTO.getPassword()));
        MUser user=userService.findUserByContact(loginDTO.getContact());
        String token=jwtUtils.generateToken(user.getName(),loginDTO.getContact());
        user.setIsActive(true);
        userService.saveUser(user);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/signout")
    @SecurityRequirement(name = "bearerAuth")
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
    @PutMapping("/update/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserDTO> updateUserById(@PathVariable Long id,@RequestBody UserDTO userDTO) throws UserNotFoundException {
        if(!userService.isExitUserById(id)){
            throw new UserNotFoundException("User doesn't exit");
        }
        return ResponseEntity.ok(
                userMapper.toUserDTO(userService.updateUser(id,userDTO))
        );
    }
    @DeleteMapping("/delete/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) throws UserNotFoundException {
        if(!userService.isExitUserById(id)){
            throw new UserNotFoundException("User doesn't exit");
        }
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/fetch/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserDTO> fetchUserById(@PathVariable Long id) throws UserNotFoundException {
        if(!userService.isExitUserById(id)){
            throw new UserNotFoundException("User doesn't exit");
        }
        return ResponseEntity.ok(userMapper.toUserDTO(userService.getUserById(id)));
    }

}
