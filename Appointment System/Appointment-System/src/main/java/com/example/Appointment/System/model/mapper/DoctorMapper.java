package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.DoctorDTO;
import com.example.Appointment.System.model.entity.Doctor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DoctorMapper {
    public Doctor toDoctor(DoctorDTO doctorDTO) {
        return Doctor.builder()
                .doctorName(doctorDTO.getDoctorName())
                .email(doctorDTO.getEmail())
                .address(doctorDTO.getAddress())
                .contactNumber(doctorDTO.getContactNumber())
                .gender(doctorDTO.getGender())
                .languagesSpoken(doctorDTO.getLanguagesSpoken())
                .yearsOfExperience(doctorDTO.getYearsOfExperience())
                .designation(doctorDTO.getDesignation())
                .licenseNumber(doctorDTO.getLicenseNumber())
                .hospitalOrClinicName(doctorDTO.getHospitalOrClinicName())
                .availabilityStatus(doctorDTO.getAvailabilityStatus())
                .degrees(doctorDTO.getDegrees())
                .build();
    }

    public DoctorDTO toDoctorDTO(Doctor doctor) {
        return DoctorDTO.builder()
                .doctorName(doctor.getDoctorName())
                .email(doctor.getEmail())
                .address(doctor.getAddress())
                .gender(doctor.getGender())
                .languagesSpoken(doctor.getLanguagesSpoken())
                .yearsOfExperience(doctor.getYearsOfExperience())
                .designation(doctor.getDesignation())
                .licenseNumber(doctor.getLicenseNumber())
                .hospitalOrClinicName(doctor.getHospitalOrClinicName())
                .availabilityStatus(doctor.getAvailabilityStatus())
                .degrees(doctor.getDegrees())
                .contactNumber(doctor.getContactNumber())
                .build();
    }

    public List<DoctorDTO> toDoctorDTOs(List<Doctor> all) {
        List<DoctorDTO>doctorDTOS=new ArrayList<>();
        for (Doctor doctor:all){
            doctorDTOS.add(toDoctorDTO(doctor));
        }
        return doctorDTOS;
    }
}
