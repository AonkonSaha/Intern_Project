package com.example.spring_intro.service;

import com.example.spring_intro.exception.UserNotFoundException;
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

    public UserDTO fetchUserById(Long id) throws UserNotFoundException {

        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("User doesn't exit..!");
        }
        return userMapper.toUserDTO(user.get());
    }

    public UserDTO updateUserById(Long userId,UserDTO userDTO) throws UserNotFoundException {
        Optional<User> user=userRepo.findById(userId);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("User doesn't exit..!");
        }
        user.get().setUserName(userDTO.getUserName());
        user.get().setEmail(userDTO.getEmail());
        user.get().setContact(userDTO.getContact());
        userRepo.save(user.get());
        return userMapper.toUserDTO(user.get());
    }

    public void deleteUserById(Long userId) throws UserNotFoundException {
        Optional<User> user=userRepo.findById(userId);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("User doesn't exit..!!");
        }
        userRepo.deleteById(userId);
    }


}
