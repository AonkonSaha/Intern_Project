package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.DoctorDTO;
import com.example.Appointment.System.model.entity.Doctor;
import com.example.Appointment.System.repository.DoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepo doctorRepo;

    public Doctor saveDoctor(Doctor doctor) {
        doctorRepo.save(doctor);
        return doctor;
    }

    public boolean isExitDoctorById(Long id) {
        return doctorRepo.existsById(id);
    }

    public Doctor updateDoctorById(Long id, DoctorDTO doctorDTO) {
        Optional<Doctor> doctor=doctorRepo.findById(id);
        if(doctor.isEmpty()){
            return null;
        }
        doctor.get().setDoctorName(doctorDTO.getDoctorName());
        doctor.get().setEmail(doctorDTO.getEmail());
        doctor.get().setAddress(doctorDTO.getAddress());
        doctor.get().setContactNumber(doctorDTO.getContactNumber());
        doctor.get().setGender(doctorDTO.getGender());
        doctor.get().setLanguagesSpoken(doctorDTO.getLanguagesSpoken());
        doctor.get().setYearsOfExperience(doctorDTO.getYearsOfExperience());
        doctor.get().setSpecialization(doctorDTO.getSpecialization());
        doctor.get().setLicenseNumber(doctorDTO.getLicenseNumber());
        doctor.get().setHospitalOrClinicName(doctorDTO.getHospitalOrClinicName());
        doctor.get().setAvailabilityStatus(doctorDTO.getAvailabilityStatus());
        doctor.get().setEducation(doctorDTO.getEducation());
        doctorRepo.save(doctor.get());
        return doctor.get();
    }

    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doctor=doctorRepo.findById(id);
        if(doctor.isEmpty()){
            return null;
        }
        return doctor.get();
    }

    public void deleteDoctorByDoctorId(Long id) {
        Optional<Doctor> doctor=doctorRepo.findById(id);
        if(doctor.isEmpty()){
            return;
        }
        doctorRepo.deleteById(id);
    }
}
