package com.example.spring_intro.controller;

import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.service.RoleService;
import com.example.spring_intro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @GetMapping("/")
    public String testMyProject()
    {
        return "Hellow Spring Boot...!";
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO)
    {
        return ResponseEntity.ok(userService.saveUser(userDTO));
    }

    @GetMapping("/fetch/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return  ResponseEntity.ok(userService.fetchUserById(id));
    }
    @PutMapping("/update/{user_id}")
    public ResponseEntity<?> updateUserById(@RequestBody UserDTO userDTO,
                                            @PathVariable("user_id") Long userId
                                            ) throws AccessDeniedException, UserNotFoundException {
        if(!roleService.isAccessUpdateUser(userId))
        {
            throw new AccessDeniedException("You do not have permission to update user.");

        }
        userService.updateUserById(userId,userDTO);
        return  ResponseEntity.ok("User updated successfully.");
    }
    @DeleteMapping("/remove/{admin_id}/{user_id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("admin_id") Long adminId,
                                            @PathVariable("user_id") Long userId) throws AccessDeniedException, UserNotFoundException {
        if(!roleService.isAccessDeleteUser(adminId))
        {
            throw new AccessDeniedException("You do not have permission to delete user.");
        }
        userService.deleteUserById(userId);
        return  ResponseEntity.ok("User deleted successfully.");
    }


}
