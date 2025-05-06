package com.example.Appointment.System.service;


import com.example.Appointment.System.model.entity.MUser;
import com.example.Appointment.System.model.entity.Patient;
import com.example.Appointment.System.model.entity.UserRole;
import com.example.Appointment.System.model.mapper.UserMapper;
import com.example.Appointment.System.repository.PatientRepo;
import com.example.Appointment.System.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PatientRepo patientRepo;
    private final UserMapper userMapper;
    public Patient saveUser(MUser user) {
        user.getUserRoles().add(UserRole.builder().role("USER").build());
        Patient patient=userMapper.toPatient(user);
        patientRepo.save(patient);
        userRepo.save(user);
        return patient;
    }

    public MUser getUserByName(String username) {
        Optional<MUser> user=userRepo.findByUserName(username);
        if(user==null){
            return null;
        }
        return user.get();
    }
}
