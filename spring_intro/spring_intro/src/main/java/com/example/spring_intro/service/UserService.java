package com.example.spring_intro.service;

import com.example.spring_intro.model.dto.UserDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserRole;
import com.example.spring_intro.model.mapping.CustomMapper;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final CustomMapper customMapper;

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

    public UserDTO fetchUserById(String identity) {
        long id = 0;
        try {
            id = Long.parseLong(identity);

        } catch (NumberFormatException e) {
            System.out.println("Invalid number format!");
        }
        System.out.println("ID:: "+id);
        User user = userRepo.findById(id).orElseThrow();
        user.getUserRole().forEach(role -> System.out.println("Role: "+role.getRole()));
        return customMapper.toUserDTO(user);
    }

    public String updateUserById(UserDTO userDTO,Long id) {
        User user=userRepo.findById(id).orElseThrow();
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setContact(userDTO.getContact());
        userRepo.save(user);
        return "User "+id+" : Update Successfull..!!";
    }

    public String deleteUserById( Long id) {
        userRepo.deleteById(id);
        return "Remove "+id+" : Successfull...!";
    }

    public List<UserDTO> fetchAllUser() {
        List<UserDTO>users=new ArrayList<>();
        for(User user:userRepo.findAll())
        {
            users.add(customMapper.toUserDTO(user));
        }
        return users;
    }
}
