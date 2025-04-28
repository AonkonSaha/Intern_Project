package com.example.spring_intro.controller;

import com.example.spring_intro.exception.UnAuthorizedActionException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.RoleDTO;
import com.example.spring_intro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/set/role/user/{admin_id}")
    public ResponseEntity<?> setRoleUser(
            @PathVariable("admin_id") Long adminId,
            @RequestBody RoleDTO roleDTO) throws UnAuthorizedActionException, UserNotFoundException {
        if (!roleService.isAccessCreateRole(adminId)) {
            throw new UnAuthorizedActionException("You do not have permission to set user role.");
        }
        return ResponseEntity.ok(roleService.saveRole(roleDTO));
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchRoleById(@RequestParam("admin_id") Long adminId,
                                           @RequestParam("role_id") Long roleId) throws UnAuthorizedActionException, UserNotFoundException {
        if (!roleService.isAccessCreateRole(adminId)) {
            throw new UnAuthorizedActionException("You do not have permission to set user role.");

        }
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteRoleById(@RequestParam("admin_id") Long adminId,
                                            @RequestParam("role_id") Long roleId) throws UnAuthorizedActionException, UserNotFoundException {
        if (!roleService.isAccessCreateRole(adminId)) {
            throw new UnAuthorizedActionException("You do not have permission to delete user role.");

        }
        roleService.deleteRoleById(roleId);
        return ResponseEntity.ok("Deleted role successfully.");
    }

    @PutMapping("/update")
    public ResponseEntity<?> deleteRoleById(@RequestParam("admin_id") Long adminId,
                                            @RequestParam("role_id") Long roleId,
                                            @RequestBody RoleDTO roleDTO) throws UnAuthorizedActionException, UserNotFoundException {
        if (!roleService.isAccessCreateRole(adminId)) {
            throw new UnAuthorizedActionException("You do not have permission to update user role.");
        }
        return ResponseEntity.ok(roleService.updateRoleById(roleId, roleDTO));
    }
}