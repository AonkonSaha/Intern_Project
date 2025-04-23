package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserDTO userDTO)
    {
        User user=new User();
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setContact(userDTO.getContact());
        return user;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO=new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setContact(user.getContact());
        return userDTO;
    }
}
