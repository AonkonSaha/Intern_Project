package com.example.spring_intro.service;

import com.example.spring_intro.exception.RoleNotFoundException;
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

    public UserRole saveRole(RoleDTO roleDTO) throws UserNotFoundException {
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
                throw new UserNotFoundException("User doesn't exit..!");
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
        return userRole;
    }

    public UserRole getRoleById(Long id) throws RoleNotFoundException {
        Optional<UserRole> userRole=roleRepo.findById(id);
        if(userRole.isEmpty())
        {
            throw new RoleNotFoundException("Role doesn't exit..!");
        }
        return userRole.get();
    }

    public void deleteRoleById(Long id) throws RoleNotFoundException {
        Optional<UserRole> userRole=roleRepo.findById(id);
        if(userRole.isEmpty())
        {
            throw new RoleNotFoundException("Role doesn't exit..!");
        }
         roleRepo.deleteById(id);
    }

    public UserRole updateRoleById(Long id,RoleDTO roleDTO) throws RoleNotFoundException {
        Optional<UserRole> role=roleRepo.findById(id);
        if(role.isEmpty())
        {
           throw new RoleNotFoundException("Role doesn't exit..!");
        }
            role.get().setRole(roleDTO.getRole());
            roleRepo.save(role.get());
            return role.get();
    }


    public boolean isAccessCreateBlog(Long authorUserId) {
        Optional<User> user=userRepo.findById(authorUserId);
        User user1=user.get();
        for(UserRole userRole:user1.getUserRole())
        {
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
