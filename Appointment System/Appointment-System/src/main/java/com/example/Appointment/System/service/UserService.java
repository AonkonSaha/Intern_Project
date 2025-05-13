package com.example.Appointment.System.service;


import com.example.Appointment.System.model.dto.PatientProfileDTO;
import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.entity.PatientProfile;
import com.example.Appointment.System.model.entity.UserRole;
import com.example.Appointment.System.model.mapper.UserMapper;
import com.example.Appointment.System.repository.PatientRepo;
import com.example.Appointment.System.repository.UserRepo;
import com.example.Appointment.System.repository.UserRoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${profile.image.folder.path}")
    public String ProfileFolderPath;

    private final UserRepo userRepo;
    private final PatientRepo patientRepo;
    private final UserMapper userMapper;
    private final UserRoleRepo userRoleRepo;
    public MUser saveUser(MUser user) {
        userRepo.save(user);
        return user;
    }

    public MUser getUserByName(String username) {
        Optional<MUser> user=userRepo.findByName(username);
        if(user==null){
            return null;
        }
        return user.get();
    }

    public boolean isExitUserById(Long id) {
        return userRepo.existsById(id);
    }

    public MUser updateUser(Long id, UserDTO userDTO) {
        Optional<MUser> mUser=userRepo.findById(id);
        if(mUser.isEmpty()){
            return null;
        }
        mUser.get().setEmail(userDTO.getEmail());
        mUser.get().setDateOfBirth(userDTO.getDateOfBirth());
        mUser.get().setGender(userDTO.getGender());
        mUser.get().setContact(userDTO.getContact());
        mUser.get().setPassword(userDTO.getPassword());
        userRepo.save(mUser.get());
        return mUser.get();
    }

    public void deleteUser(Long id) {
        if(!userRepo.existsById(id)){
            return;
        }
        MUser user=userRepo.findById(id).get();
        Set<UserRole>userRoles=user.getUserRoles();
        for (UserRole userRole : userRoles) {
            userRole.setUsers(null);
            userRoleRepo.save(userRole);
        }
        user.setUserRoles(null);
        userRepo.save(user);
        userRepo.deleteById(id);
    }

    public MUser getUserById(Long id) {
        Optional<MUser> mUser=userRepo.findById(id);
        if(mUser.isEmpty()){
            return null;
        }
        return mUser.get();
    }

    public boolean isExitUserByContact(String contact) {
        return userRepo.existsByContact(contact);
    }

    public MUser findUserByContact(String contact) {
        Optional<MUser> mUser= userRepo.findByContact(contact);
        if(mUser.isEmpty()){
            return null;
        }
        return mUser.get();
    }

    public MUser updatePatientWithOutPassword(PatientProfileDTO patientProfileDTO) throws IOException {
        String patientContact= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<MUser> mUser=userRepo.findByContact(patientContact);
        if(mUser.isEmpty()){
            return null;
        }
        String fileName = UUID.randomUUID() + "_" + patientProfileDTO.getProfilePicture().getOriginalFilename();
        Path filePath = Paths.get(ProfileFolderPath, fileName);
        Files.write(filePath, patientProfileDTO.getProfilePicture().getOriginalFilename().getBytes());
        mUser.get().setEmail(patientProfileDTO.getEmail());
        mUser.get().setDateOfBirth(patientProfileDTO.getDateOfBirth());
        mUser.get().setGender(patientProfileDTO.getGender());
        mUser.get().getPatientProfile().setPatientName(patientProfileDTO.getName());
        mUser.get().getPatientProfile().setProfilePictureUrl("/images/patient/"+filePath.toString());
        userRepo.save(mUser.get());
        return mUser.get();
    }
}
