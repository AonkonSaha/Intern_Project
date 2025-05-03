package com.example.spring_intro.controller;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.mapper.UserMapper;
import com.example.spring_intro.service.RoleService;
import com.example.spring_intro.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper userMapper;

    @GetMapping("/")
    public String testMyProject()
    {
        return "Hellow Spring Boot...!";
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO)
    {
        if(userService.isUserNameExit(userDTO.getUserName())) {
            throw new RuntimeException("User already exists");
        }
        if(userService.isUserEmailExit(userDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        return ResponseEntity
                .ok(userMapper
                .toUserDTO(userService
                .saveUser(userMapper
                .toUser(userDTO))));
    }

    @GetMapping("/fetch/{id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        if(!userService.isExitUserById(id)){
            throw new UserNotFoundException("User doesn't exit..!");
        }
        return  ResponseEntity.ok(userMapper.toUserDTO(userService.fetchUserById(id)));
    }
    @PutMapping("/update/{user_id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<UserDTO> updateUserById(@RequestBody UserDTO userDTO,
                                            @PathVariable("user_id") Long userId
                                            ) throws UserNotFoundException {
        if(!userService.isExitUserById(userId)) {
            throw new UserNotFoundException("User doesn't exit..!");
        }
        return  ResponseEntity.ok(userMapper.toUserDTO(userService.updateUserById(userId,userDTO)));
    }
    @DeleteMapping("/remove/{user_id}")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> deleteUserById(@PathVariable("user_id") Long userId) throws UserNotFoundException {
        if(!userService.isExitUserById(userId)) {
            throw new UserNotFoundException("User doesn't Exit..");
        }
        userService.deleteUserById(userId);
        return  ResponseEntity.ok("User deleted successfully.");
    }


}
