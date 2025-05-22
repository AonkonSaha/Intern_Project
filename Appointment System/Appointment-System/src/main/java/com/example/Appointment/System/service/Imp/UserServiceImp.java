package com.example.Appointment.System.service.Imp;


import com.example.Appointment.System.model.dto.PasswordDTO;
import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.entity.UserRole;
import com.example.Appointment.System.model.mapper.UserMapper;
import com.example.Appointment.System.repository.PatientRepo;
import com.example.Appointment.System.repository.UserRepo;
import com.example.Appointment.System.repository.UserRoleRepo;
import com.example.Appointment.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {
    private final PasswordEncoder passwordEncoder;
    @Value("${profile.image.folder.path}")
    public String ProfileFolderPath;
    private final UserRepo userRepo;
    private final PatientRepo patientRepo;
    private final UserMapper userMapper;
    private final UserRoleRepo userRoleRepo;
    private final UserValidationServiceImp userValidationServiceImp;

    @Override
    public MUser saveUser(MUser user) {
        userRepo.save(user);
        return user;
    }

    @Override
    public MUser getUserByName(String username) {
        Optional<MUser> user=userRepo.findByName(username);
        if(user==null){
            return null;
        }
        return user.get();
    }

    @Override
    public boolean isExitUserById(Long id) {
        return userRepo.existsById(id);
    }

    @Override
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

    @Override
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

    @Override
    public MUser getUserById(Long id) {
        Optional<MUser> mUser=userRepo.findById(id);
        if(mUser.isEmpty()){
            return null;
        }
        return mUser.get();
    }

    @Override
    public boolean isExitUserByContact(String contact) {
        return userRepo.existsByContact(contact);
    }

    @Override
    public MUser findUserByContact(String contact) {
        Optional<MUser> mUser= userRepo.findByContact(contact);
        if(mUser.isEmpty()){
            return null;
        }
        return mUser.get();
    }

    @Override
    public MUser updatePatientWithOutPassword(String fullName, String email, String dob, String gender, MultipartFile photo)  {
        String patientContact= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<MUser> mUser=userRepo.findByContact(patientContact);
        if(mUser.isEmpty()){
            return null;
        }
        if(photo!=null && !photo.isEmpty()){
            String fileName = UUID.randomUUID() + "_" + photo.getOriginalFilename();
            Path filePath = Paths.get(ProfileFolderPath, fileName);
            try {
                Files.createDirectories(filePath.getParent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                Files.write(filePath, photo.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            mUser.get().getPatientProfile().setProfilePictureUrl("/images/patient/"+fileName);
        }
        mUser.get().setEmail(email);
        mUser.get().setDateOfBirth(LocalDate.parse(dob));
        mUser.get().setGender(gender);
        mUser.get().setName(fullName);
        mUser.get().getPatientProfile().setPatientName(fullName);
        mUser.get().getPatientProfile().setDateOfBirth(LocalDate.parse(dob));
        mUser.get().getPatientProfile().setUser(mUser.get());
        userRepo.save(mUser.get());
        return mUser.get();
    }

    @Override
    public void  updateUserPassword(String contact, PasswordDTO passwordDTO) {
        Optional<MUser> mUser=userRepo.findByContact(contact);
        if(mUser.isEmpty()){
            return ;
        }
        mUser.get().setPassword(passwordEncoder.encode(passwordDTO.getConfirmPassword()));
        userRepo.save(mUser.get());
    }

    @Override
    public boolean isExitUserPassword(String password) {
        String patientContact= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<MUser> mUser=userRepo.findByContact(patientContact);
        if(mUser.isEmpty()){
            return false;
        }
        return passwordEncoder.matches(password,mUser.get().getPassword());
    }

    @Override
    public String validateUserDetails(UserDTO userDTO) {
       if(!userValidationServiceImp.isExitUserByContact(userDTO.getContact()).isEmpty()){
           return userValidationServiceImp.isExitUserByContact(userDTO.getContact());
       }
       if(!userValidationServiceImp.isEmptyUserContact(userDTO.getContact()).isEmpty()){
           return userValidationServiceImp.isEmptyUserContact(userDTO.getContact());
       }
       if(!userValidationServiceImp.isValidUserContactLength(userDTO.getContact()).isEmpty()){
           return userValidationServiceImp.isValidUserContactLength(userDTO.getContact());
       }
       if(!userValidationServiceImp.isValidUserPasswordLength(userDTO.getPassword()).isEmpty()){
           return userValidationServiceImp.isValidUserPasswordLength(userDTO.getPassword());
       }
       if(!userValidationServiceImp.isValidEmailFormat(userDTO.getEmail()).isEmpty()){
           return userValidationServiceImp.isValidEmailFormat(userDTO.getEmail());
       }
       if(!userValidationServiceImp.isValidGender(userDTO.getGender()).isEmpty()){
           return userValidationServiceImp.isValidGender(userDTO.getGender());
       }

        return "";
    }
}
