package com.example.spring_intro.controller;

import com.example.spring_intro.model.dto.RoleDTO;
import com.example.spring_intro.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

//    @PostMapping("/set/role/user/{admin_id}")
@PostMapping("/set/role/user")
    public ResponseEntity<?> setRoleUser(
            /**@PathVariable("admin_id")Long adminId,*/
                                         @RequestBody RoleDTO roleDTO) throws AccessDeniedException {
//        if(roleService.isAccessCreateRole(adminId))
//        {
//            throw new AccessDeniedException("You do not have permission to set user role.");
//
//        }
        return ResponseEntity.ok(roleService.saveRole(roleDTO));
    }
    @GetMapping("/fetch/{admin_id}/{role_id}")
    public ResponseEntity<?> fetchRoleById(@PathVariable("admin_id") Long adminId,
                                           @PathVariable("role_id") Long roleId) throws AccessDeniedException {
        if(roleService.isAccessCreateRole(adminId))
        {
            throw new AccessDeniedException("You do not have permission to set user role.");

        }
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }
    @DeleteMapping("/delete/{admin_id}/{role_id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable("admin_id") Long adminId,
                                            @PathVariable("role_id") Long roleId) throws AccessDeniedException {
        if(roleService.isAccessCreateRole(adminId))
        {
            throw new AccessDeniedException("You do not have permission to delete user role.");

        }
        roleService.deleteRoleById(roleId);
        return ResponseEntity.ok("Deleted role successfully.");
    }
    @PutMapping("/update/{admin_id}/{role_id}")
    public ResponseEntity<?> deleteRoleById(@PathVariable("admin_id") Long adminId,
                                            @PathVariable("role_id") Long roleId,
                                            @RequestBody RoleDTO roleDTO) throws AccessDeniedException {
        if(roleService.isAccessCreateRole(adminId))
        {
            throw new AccessDeniedException("You do not have permission to update user role.");

        }
        return ResponseEntity.ok(roleService.updateRoleById(roleId,roleDTO));
    }


//    @PostMapping("/set/role")
//    public ResponseEntity<?> setRole(@RequestBody RoleDTO roleDTO)
//    {
//        return ResponseEntity.ok(roleService.setRole(roleDTO));
//    }
}
