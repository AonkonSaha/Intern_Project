package com.example.spring_intro.model.mapper;

import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;


    public User toUser(UserDTO userDTO)
    {
        User user=new User();
        user.setEmail(userDTO.getEmail());
        user.setUserName(userDTO.getUserName());
        user.setContact(userDTO.getContact());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return user;
    }

    public UserDTO toUserDTO(User user) {
        UserDTO userDTO=new UserDTO();
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        userDTO.setContact(user.getContact());
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }
}
