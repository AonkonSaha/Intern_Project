package com.example.spring_intro.service;

import com.example.spring_intro.exception.UserNotFoundException;
import com.example.spring_intro.model.dto.RoleDTO;
import com.example.spring_intro.model.entity.User;
import com.example.spring_intro.model.entity.UserRole;
import com.example.spring_intro.model.mapper.RoleMapper;
import com.example.spring_intro.repository.RoleRepo;
import com.example.spring_intro.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleService {


    private final RoleRepo roleRepo;
    private final UserRepo userRepo;
    private final RoleMapper roleMapper;

    public RoleDTO saveRole(RoleDTO roleDTO) {
       UserRole userRole=roleMapper.toUserRole(roleDTO);
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
            Set<UserRole> userRoles=new HashSet<>(user.getUserRole());
            userRoles.add(userRole);
            user.setUserRole(userRoles);
            userRepo.save(user);
        }
//        System.out.println("User Role: "+userRole.getUsers().stream().map(User::getUserRole).toList());
        return roleMapper.toRoleDTO(userRole);
    }

    public  RoleDTO getRoleById(Long id) {
        Optional<UserRole> userRole=roleRepo.findById(id);
        if(userRole.isEmpty())
        {
            return null;
        }
        return roleMapper.toRoleDTO(userRole.get());
    }

    public void deleteRoleById(Long id) {
        Optional<UserRole> userRole=roleRepo.findById(id);
        if(userRole.isEmpty())
        {
            return;
        }
         roleRepo.deleteById(id);
    }

    public RoleDTO updateRoleById(Long id,RoleDTO roleDTO) {
        Optional<UserRole> role=roleRepo.findById(id);
        if(role.isEmpty())
        {
            return null;
        }
            role.get().setRole(roleDTO.getRole());
            roleRepo.save(role.get());
        return roleMapper.toRoleDTO(role.get());
    }


    public boolean isAccessCreateBlog(Long authorUserId) {
//        System.out.println("I am in RoleService");
        Optional<User> user=userRepo.findById(authorUserId);
//        System.out.println("User Role: "+user.get().getUserRole().stream().map(UserRole::getRole).toList());
        User user1=user.get();
        for(UserRole userRole:user1.getUserRole())
        {
            System.out.println("User Role: "+userRole.getRole() );
            if(userRole.getRole().equals("AUTHOR") || userRole.getRole().equals("ADMIN") || userRole.getRole().equals("MODERATOR") )
            {
                return true;
            }
        }
        return false;

    }

    public boolean isAccessDeleteBLog(Long userId) {
        User user=userRepo.findById(userId).get();
        for(UserRole userRole:user.getUserRole())
        {
            if(userRole.getRole().equals("AUTHOR") ||
                    userRole.getRole().equals("ADMIN") ||
                    userRole.getRole().equals("MODERATOR") )
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAccessUpdateBlog(Long userId) {
        User user=userRepo.findById(userId).get();
        for(UserRole userRole:user.getUserRole())
        {
            if(userRole.getRole().equals("AUTHOR") ||
                    userRole.getRole().equals("ADMIN") ||
                    userRole.getRole().equals("MODERATOR") ||
                    userRole.getRole().equals("USER") )
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAccessUpdateUser(Long userId) throws UserNotFoundException {

        Optional<User> user=userRepo.findById(userId);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("User not found...");
        }
        for(UserRole userRole:user.get().getUserRole())
        {
            if(userRole.getRole().equals("AUTHOR") ||
                    userRole.getRole().equals("ADMIN") ||
                    userRole.getRole().equals("MODERATOR") ||
                    userRole.getRole().equals("USER")
            )
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAccessDeleteUser(Long adminId) {
        User user=userRepo.findById(adminId).get();
        for(UserRole userRole:user.getUserRole())
        {
            if(userRole.getRole().equals("AUTHOR") ||
                    userRole.getRole().equals("ADMIN") ||
                    userRole.getRole().equals("MODERATOR") )
            {
                return true;
            }
        }
        return false;

    }

    public boolean isAccessCreateComment(Long userId) {
        User user=userRepo.findById(userId).get();
        for(UserRole userRole:user.getUserRole())
        {
            if(userRole.getRole().equals("AUTHOR") ||
                    userRole.getRole().equals("ADMIN") ||
                    userRole.getRole().equals("MODERATOR") ||
                    userRole.getRole().equals("USER") )
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAccessDeleteComment(Long userId) {
        User user=userRepo.findById(userId).get();
        for(UserRole userRole:user.getUserRole())
        {
            if(userRole.getRole().equals("AUTHOR") ||
                    userRole.getRole().equals("ADMIN") ||
                    userRole.getRole().equals("MODERATOR") ||
                    userRole.getRole().equals("USER") )
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAccessUpdateComment(Long userId) {
        User user=userRepo.findById(userId).get();
        for(UserRole userRole:user.getUserRole())
        {
            if(userRole.getRole().equals("Author") ||
                    userRole.getRole().equals("ADMIN") ||
                    userRole.getRole().equals("MODERATOR") ||
                    userRole.getRole().equals("USER") )
            {
                return true;
            }
        }
        return false;
    }

    public boolean isAccessCreateRole(Long adminId) throws UserNotFoundException {
        Optional<User> user=userRepo.findById(adminId);
        if(user.isEmpty())
        {
            throw new UserNotFoundException("User doesn't exit..!");
        }
        System.out.println("USEr Role: "+user.get().getUserRole().stream().map(UserRole::getRole).toList());
        for(UserRole userRole:user.get().getUserRole())
        {
            if(userRole.getRole().equals("ADMIN") )
            {
                return true;
            }
        }
        return false;
    }
}
