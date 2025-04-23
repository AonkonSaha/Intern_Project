package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.RoleDTO;
import com.example.spring_intro.model.entity.UserRole;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleMapper {


    public UserRole toUserRole(RoleDTO roleDTO) {
        UserRole userRole=new UserRole();
        userRole.setRole(roleDTO.getRole());
        return userRole;
    }

    public RoleDTO toRoleDTO(UserRole userRole) {
        RoleDTO roleDTO=new RoleDTO();
        roleDTO.setRole(userRole.getRole());
        return roleDTO;
    }
}
