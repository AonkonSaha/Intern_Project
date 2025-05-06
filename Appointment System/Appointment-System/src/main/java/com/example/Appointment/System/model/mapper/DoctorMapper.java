package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.DoctorDTO;
import com.example.Appointment.System.model.entity.DoctorProfile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorMapper {
    public DoctorProfile toDoctor(DoctorDTO doctorDTO) {
        return DoctorProfile.builder()
                .languagesSpoken(doctorDTO.getLanguagesSpoken())
                .yearsOfExperience(doctorDTO.getYearsOfExperience())
                .designation(doctorDTO.getDesignation())
                .licenseNumber(doctorDTO.getLicenseNumber())
                .hospitalOrClinicName(doctorDTO.getHospitalOrClinicName())
                .availabilityStatus(doctorDTO.getAvailabilityStatus())
                .degrees(doctorDTO.getDegrees())
                .build();
    }

    public DoctorDTO toDoctorDTO(DoctorProfile doctorProfile) {
        return DoctorDTO.builder()
                .languagesSpoken(doctorProfile.getLanguagesSpoken())
                .yearsOfExperience(doctorProfile.getYearsOfExperience())
                .designation(doctorProfile.getDesignation())
                .licenseNumber(doctorProfile.getLicenseNumber())
                .hospitalOrClinicName(doctorProfile.getHospitalOrClinicName())
                .availabilityStatus(doctorProfile.getAvailabilityStatus())
                .degrees(doctorProfile.getDegrees())
                .build();
    }

    public List<DoctorDTO> toDoctorDTOs(List<DoctorProfile> all) {
        List<DoctorDTO>doctorDTOS=new ArrayList<>();
        for (DoctorProfile doctorProfile :all){
            doctorDTOS.add(toDoctorDTO(doctorProfile));
        }
        return doctorDTOS;
    }
}
