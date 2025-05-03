package com.example.spring_intro.controller;

import com.example.spring_intro.exception.RoleNotFoundException;
import com.example.spring_intro.exception.UnAuthorizedActionException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.RoleDTO;
import com.example.spring_intro.model.entity.UserRole;
import com.example.spring_intro.model.mapper.RoleMapper;
import com.example.spring_intro.service.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/v4/role")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    private final RoleService roleService;
    private final RoleMapper roleMapper;
    @PostMapping("/set/role/user")
    public ResponseEntity<RoleDTO> setRoleUser(@RequestBody RoleDTO roleDTO) throws UserNotFoundException {
        return ResponseEntity.ok(roleMapper.toRoleDTO(roleService.saveRole(roleDTO)));
    }

    @GetMapping("/fetch")
    public ResponseEntity<RoleDTO> fetchRoleById(@RequestParam("role_id") Long roleId) throws RoleNotFoundException {
        if(!roleService.isExitRoleById(roleId)) {
            throw new RoleNotFoundException("Role doesn't exit..!");
        }
        return ResponseEntity.ok(roleMapper.toRoleDTO(roleService.getRoleById(roleId)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRoleById(@RequestParam("role_id") Long roleId) throws RoleNotFoundException {
        if(!roleService.isExitRoleById(roleId)) {
            throw new RoleNotFoundException("Role doesn't exit..!");
        }
        roleService.deleteRoleById(roleId);
        return ResponseEntity.ok("Deleted role successfully.");
    }

    @PutMapping("/update")
    public ResponseEntity<RoleDTO> deleteRoleById(@RequestParam("role_id") Long roleId,
                                            @RequestBody RoleDTO roleDTO)  throws RoleNotFoundException {
        if(!roleService.isExitRoleById(roleId)) {
            throw new RoleNotFoundException("Role doesn't exit..!");
        }
        return ResponseEntity.ok(roleMapper.toRoleDTO(roleService.updateRoleById(roleId, roleDTO)));
    }
}