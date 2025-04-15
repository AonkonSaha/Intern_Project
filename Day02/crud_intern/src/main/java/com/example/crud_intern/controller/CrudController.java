package com.example.crud_intern.controller;

import com.example.crud_intern.dto.UserDTO;
import com.example.crud_intern.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CrudController {

    @Autowired
    UserService userService;
    @PostMapping("/register")
    public String RegisterUser(@RequestBody UserDTO userDTO)
    {
        return userService.saveUser(userDTO);
    }
    @GetMapping("/fetch/{id}")
    public UserDTO GetUserById(@PathVariable("id") String id)
    {

        return  userService.fetchUserByID(id);
    }

    @GetMapping("/")
    public String testMyProject()
    {
        return "Hellow Spring Boot...!";
    }
}
