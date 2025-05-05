package com.example.Appointment.System.service;


import com.example.Appointment.System.model.dto.PatientDTO;
import com.example.Appointment.System.model.entity.Patient;
import com.example.Appointment.System.repository.PatientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepo patientRepo;
    public Patient savePatient(Patient patient) {
        patientRepo.save(patient);
        patient.setRole("PATIENT");
        return patient;
    }
    public boolean isExitPatientById(Long id) {
        return patientRepo.existsById(id);
    }

    public Patient getPatientById(Long id) {
         Optional<Patient> patient=patientRepo.findById(id);
         if(patient.isEmpty()){
             return null;
         }
         return patient.get();
    }

    public void deletePatientByPatientId(Long id) {
        patientRepo.deleteById(id);
    }

    public Patient updatePatientByPatientId(Long id, PatientDTO patientDTO) {
        Optional<Patient> patient=patientRepo.findById(id);
        if(patient.isEmpty()){
            return null;
        }
        patient.get().setPatientName(patientDTO.getPatientName());
        patient.get().setMobileNumber(patientDTO.getMobileNumber());
        patient.get().setEmail(patientDTO.getEmail());
        patient.get().setGender(patientDTO.getGender());
        patient.get().setDateOfBirth(patientDTO.getDateOfBirth());
        patient.get().setPassword(patientDTO.getPassword());
        patientRepo.save(patient.get());
        return patient.get();
    }

    public List<Patient> getAllPatient() {
        List<Patient> patients = patientRepo.findAll();
        if(patients.isEmpty()){
            return new ArrayList<>();
        }
        return patientRepo.findAll();
    }
}
