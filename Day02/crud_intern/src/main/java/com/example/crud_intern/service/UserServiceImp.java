package com.example.crud_intern.service;

import com.example.crud_intern.dto.UserDTO;
import com.example.crud_intern.entity.User;
import com.example.crud_intern.mapping.CustomMapper;
import com.example.crud_intern.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImp implements UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CustomMapper customMapper;

    @Override
    public String saveUser(UserDTO userDTO) {
        try {
            User user = customMapper.toUser(userDTO);
            userRepo.save(user);
            return "Save Successfully";
        } catch (Exception e) {
            System.out.println(e);
        }
        return "UnSuccessfull Save";

    }

    @Override
    public UserDTO fetchUserByID(String identity) {
        long id = 0;
        try {
            id = Long.parseLong(identity);

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
        System.out.println("ID:: "+id);
        User user = userRepo.findById(id).orElseThrow();

        return customMapper.toUserDTO(user);
    }
}
