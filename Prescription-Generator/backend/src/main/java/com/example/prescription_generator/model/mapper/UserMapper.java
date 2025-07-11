package com.example.prescription_generator.model.mapper;
import com.example.prescription_generator.model.dto.UserDTO;
import com.example.prescription_generator.model.entity.DoctorProfile;
import com.example.prescription_generator.model.entity.MUser;
import com.example.prescription_generator.model.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    public MUser toUser(UserDTO userDTO) {
        MUser user= MUser.builder()
                .email(userDTO.getEmail())
                .dateOfBirth(userDTO.getDateOfBirth())
                .gender(userDTO.getGender())
                .contact(userDTO.getMobileNumber())
                .name(userDTO.getName())
                .isActive(false)
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        Role role=new Role();
        role.setRoleName("DOCTOR");
        role.getUsers().add(user);
        user.getRoles().add(role);
        DoctorProfile doctorProfile=DoctorProfile.builder()
                .doctorName(userDTO.getName())
                .designation(userDTO.getDesignation())
                .licenseNumber(userDTO.getLicenseNumber())
                .degrees(userDTO.getDegrees())
                .user(user)
                .build();
        user.setDoctorProfile(doctorProfile);
        return user;
    }

    public UserDTO toUserDTO(MUser mUser) {
        return UserDTO.builder()
                .name(mUser.getName())
                .email(mUser.getEmail())
                .mobileNumber(mUser.getContact())
                .gender(mUser.getGender())
                .profilePictureUrl(mUser.getDoctorProfile().getProfilePictureUrl())
                .designation(mUser.getDoctorProfile().getDesignation())
                .licenseNumber(mUser.getDoctorProfile().getLicenseNumber())
                .dateOfBirth(mUser.getDateOfBirth())
                .degrees(mUser.getDoctorProfile().getDegrees())
                .build();
    }


    public List<UserDTO> toUserDTOS(List<MUser> allUsers) {
        List<UserDTO> userDTOS = new ArrayList<>();
        for (MUser user : allUsers) {
            userDTOS.add(toUserDTO(user));
        }
        return userDTOS;
    }
}
