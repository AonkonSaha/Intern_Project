package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.DoctorDTO;
import com.example.Appointment.System.model.entity.Doctor;
import org.springframework.stereotype.Component;

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
                .specialization(doctorDTO.getSpecialization())
                .licenseNumber(doctorDTO.getLicenseNumber())
                .hospitalOrClinicName(doctorDTO.getHospitalOrClinicName())
                .availabilityStatus(doctorDTO.getAvailabilityStatus())
                .education(doctorDTO.getEducation())
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
                .specialization(doctor.getSpecialization())
                .licenseNumber(doctor.getLicenseNumber())
                .hospitalOrClinicName(doctor.getHospitalOrClinicName())
                .availabilityStatus(doctor.getAvailabilityStatus())
                .education(doctor.getEducation())
                .contactNumber(doctor.getContactNumber())
                .build();
    }
}
