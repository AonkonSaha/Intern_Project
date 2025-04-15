package com.example.crud_intern.mapping;

import com.example.crud_intern.dto.UserDTO;
import com.example.crud_intern.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CustomMapper {
    public User toUser(UserDTO userDTO)
    {
        User user=new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setContact(userDTO.getContact());

        return user;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO=new UserDTO();
        userDTO.setName(userDTO.getName());
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setContact(userDTO.getContact());
        return userDTO;
    }
}
