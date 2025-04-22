package com.example.crud_intern.service;

import com.example.crud_intern.dto.RoleDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    public void saveRole(RoleDTO roleDTO);
}
