package com.example.spring_intro.controller;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.mapper.UserMapper;
import com.example.spring_intro.service.RoleService;
import com.example.spring_intro.service.UserService;
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
        return ResponseEntity.ok(userMapper.toUserDTO(userService.saveUser(userDTO)));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return  ResponseEntity.ok(userMapper.toUserDTO(userService.fetchUserById(id)));
    }
    @PutMapping("/update/{user_id}")
    public ResponseEntity<UserDTO> updateUserById(@RequestBody UserDTO userDTO,
                                            @PathVariable("user_id") Long userId
                                            ) throws UserNotFoundException {
        return  ResponseEntity.ok(userMapper.toUserDTO(userService.updateUserById(userId,userDTO)));
    }
    @DeleteMapping("/remove/{admin_id}/{user_id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("admin_id") Long adminId,
                                            @PathVariable("user_id") Long userId) throws UserNotFoundException {

        userService.deleteUserById(userId);
        return  ResponseEntity.ok("User deleted successfully.");
    }


}
