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
        userDTO.setName(user.getName());
//        System.out.println(user.getName());
        userDTO.setEmail(user.getEmail());
//        System.out.println(user.getEmail());
        userDTO.setContact(user.getContact());
//        System.out.println(user.getContact());

        return userDTO;
    }
}
