package com.example.spring_intro.controller;

import com.example.spring_intro.exception.RoleNotFoundException;
import com.example.spring_intro.exception.UnAuthorizedActionException;
import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.RoleDTO;
import com.example.spring_intro.model.entity.UserRole;
import com.example.spring_intro.model.mapper.RoleMapper;
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
    private final RoleMapper roleMapper;
    @PostMapping("/set/role/user/{admin_id}")
    public ResponseEntity<RoleDTO> setRoleUser(
            @PathVariable("admin_id") Long adminId,
            @RequestBody RoleDTO roleDTO) throws UserNotFoundException {

        return ResponseEntity.ok(roleMapper.toRoleDTO(roleService.saveRole(roleDTO)));
    }

    @GetMapping("/fetch")
    public ResponseEntity<RoleDTO> fetchRoleById(@RequestParam("admin_id") Long adminId,
                                           @RequestParam("role_id") Long roleId) throws RoleNotFoundException {

        return ResponseEntity.ok(roleMapper.toRoleDTO(roleService.getRoleById(roleId)));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRoleById(@RequestParam("admin_id") Long adminId,
                                            @RequestParam("role_id") Long roleId) throws RoleNotFoundException {
        roleService.deleteRoleById(roleId);
        return ResponseEntity.ok("Deleted role successfully.");
    }

    @PutMapping("/update")
    public ResponseEntity<RoleDTO> deleteRoleById(@RequestParam("admin_id") Long adminId,
                                            @RequestParam("role_id") Long roleId,
                                            @RequestBody RoleDTO roleDTO)  throws RoleNotFoundException {
        return ResponseEntity.ok(roleMapper.toRoleDTO(roleService.updateRoleById(roleId, roleDTO)));
    }
}