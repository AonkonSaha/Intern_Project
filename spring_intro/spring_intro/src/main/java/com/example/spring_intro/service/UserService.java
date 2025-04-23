package com.example.spring_intro.service;

import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.mapper.UserMapper;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    public UserDTO saveUser(UserDTO userDTO) {
            User user = userMapper.toUser(userDTO);
            userRepo.save(user);
            return userMapper.toUserDTO(user);
    }

    public UserDTO fetchUserById(Long id) {

        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty())
        {
            return null;
        }
        return userMapper.toUserDTO(user.get());
    }

    public void updateUserById(Long adminId,Long userId,UserDTO userDTO) {
        User user=userRepo.findById(userId).orElseThrow();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setContact(userDTO.getContact());
        userRepo.save(user);
    }

    public void deleteUserById( Long userId) {
        userRepo.deleteById(userId);
    }

    public List<UserDTO> fetchAllUser() {
        List<UserDTO>users=new ArrayList<>();
        for(User user:userRepo.findAll())
        {
            users.add(userMapper.toUserDTO(user));
        }
        return users;
    }
}
