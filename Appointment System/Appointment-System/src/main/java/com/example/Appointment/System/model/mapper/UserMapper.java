package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.UserDTO;
import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.entity.Patient;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    public MUser toUser(UserDTO userDTO) {
        return MUser.builder()
                .degrees(userDTO.getDegrees())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .contactNumber(userDTO.getContactNumber())
                .designation(userDTO.getDesignation())
                .availabilityStatus(userDTO.getAvailabilityStatus())
                .dateOfBirth(userDTO.getDateOfBirth())
                .gender(userDTO.getGender())
                .hospitalOrClinicName(userDTO.getHospitalOrClinicName())
                .languagesSpoken(userDTO.getLanguagesSpoken())
                .licenseNumber(userDTO.getLicenseNumber())
                .profession(userDTO.getProfession())
                .rating(userDTO.getRating())
                .yearsOfExperience(userDTO.getYearsOfExperience())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .build();
    }

    public UserDTO toUserDTO(MUser mUser) {
        return UserDTO.builder()
                .degrees(mUser.getDegrees())
                .dateOfBirth(mUser.getDateOfBirth())
                .email(mUser.getEmail())
                .name(mUser.getName())
                .address(mUser.getAddress())
                .profession(mUser.getProfession())
                .gender(mUser.getGender())
                .designation(mUser.getDesignation())
                .availabilityStatus(mUser.getAvailabilityStatus())
                .languagesSpoken(mUser.getLanguagesSpoken())
                .licenseNumber(mUser.getLicenseNumber())
                .hospitalOrClinicName(mUser.getHospitalOrClinicName())
                .rating(mUser.getRating())
                .yearsOfExperience(mUser.getYearsOfExperience())
                .contactNumber(mUser.getContactNumber())
                .build();
    }

    public Patient toPatient(MUser user) {
        return Patient.builder()
                .patientName(user.getName())
                .dateOfBirth(user.getDateOfBirth())
                .password(user.getPassword())
                .email(user.getEmail())
                .gender(user.getGender())
                .mobileNumber(user.getContactNumber())
                .build();
    }
}
