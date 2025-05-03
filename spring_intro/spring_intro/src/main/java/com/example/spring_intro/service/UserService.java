package com.example.spring_intro.service;

import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.LoginDTO;
import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.mapper.UserMapper;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    public User saveUser(User user) {
            userRepo.save(user);
            return user;
    }

    public User fetchUserById(Long id) throws UserNotFoundException {

        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty())
        {
            return null;
        }
        return user.get();
    }

    public User updateUserById(Long userId,UserDTO userDTO) throws UserNotFoundException {
        Optional<User> user=userRepo.findById(userId);
        if(user.isEmpty())
        {
           return null;
        }
        user.get().setUserName(userDTO.getUserName());
        user.get().setEmail(userDTO.getEmail());
        user.get().setContact(userDTO.getContact());
        userRepo.save(user.get());
        return user.get();
    }

    public void deleteUserById(Long userId) throws UserNotFoundException {
        Optional<User> user=userRepo.findById(userId);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("User doesn't exit..!");
        }
        userRepo.deleteById(userId);
    }


    public User findUserById(Long authorUserId){
        Optional<User> user=userRepo.findById(authorUserId);
        if(user.isEmpty())
        {
            return null;
        }
        return user.get();
    }

    public boolean isUserNameExit(String userName) {
        Optional<User> user=userRepo.findByUserName(userName);
        return user.isPresent();
    }

    public boolean isUserEmailExit(String email) {
        Optional<User> user=userRepo.findByEmail(email);
        return user.isPresent();
    }


    public User getUserByName(String username){
        Optional<User> user=userRepo.findByUserName(username);
        if(user.isEmpty())
        {
           return null;
        }
        return user.get();
    }


    public boolean isExitUserById(Long id) {
        return userRepo.existsById(id);
    }
}
