package com.example.spring_intro.service;

import com.example.spring_intro.model.dto.RoleDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserRole;
import com.example.spring_intro.model.mapping.CustomMapper;
import com.example.spring_intro.repository.RoleRepo;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {


    private final RoleRepo roleRepo;
    private final CustomMapper customMapper;
    private final UserRepo userRepo;
    public UserRole saveRole(RoleDTO roleDTO) {
       UserRole userRole=customMapper.toUserRole(roleDTO);
        Set<User> users= new HashSet<>();
        for(Long id:roleDTO.getUserId())
        {
            Optional<User> user=userRepo.findById(id);
            if(user.isPresent())
            {
                users.add(user.get());
            }
            else {
                return null;
            }
        }
        userRole.setUsers(users);
        roleRepo.save(userRole);
        for(User user:users)
        {
            Set<UserRole> userRoles=user.getUserRole();
            userRoles.add(userRole);
            user.setUserRole(userRoles);
            userRepo.save(user);
        }
        roleRepo.save(userRole);
        return userRole;
    }
}
