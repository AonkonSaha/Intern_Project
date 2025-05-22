package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.UserRoleDTO;
import com.example.Appointment.System.model.entity.UserRole;

public interface UserRoleService {

    UserRole saveUserRole(UserRole userRole);
    UserRole updateRoleById(Long id, UserRoleDTO userRoleDTO);
}
