package com.example.crud_intern.service;

import com.example.crud_intern.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String saveUser(UserDTO userDTO);
    UserDTO fetchUserByID(String id);
}
