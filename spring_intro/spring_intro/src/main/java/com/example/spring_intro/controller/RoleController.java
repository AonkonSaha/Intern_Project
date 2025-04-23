package com.example.spring_intro.controller;

import com.example.spring_intro.model.dto.RoleDTO;
import com.example.spring_intro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/set")
    public ResponseEntity<?> setRole(@RequestBody RoleDTO roleDTO)
    {
        return ResponseEntity.ok(roleService.saveRole(roleDTO));
    }
}
