package com.example.crud_intern.controller;

import com.example.crud_intern.dto.RoleDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @PostMapping("/set")
    public ResponseEntity<?> setRole(@RequestBody RoleDTO roleDTO)
    {


    }
}
