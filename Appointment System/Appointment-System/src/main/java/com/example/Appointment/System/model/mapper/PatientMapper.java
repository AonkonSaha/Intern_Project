package com.example.Appointment.System.model.mapper;

import com.example.Appointment.System.model.dto.PatientDTO;
import com.example.Appointment.System.model.entity.Patient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PatientMapper {

    public Patient toPatient(PatientDTO patientDTO) {
        return Patient.builder()
                .patientName(patientDTO.getPatientName())
                .email(patientDTO.getEmail())
                .gender(patientDTO.getGender())
                .mobileNumber(patientDTO.getMobileNumber())
                .password(patientDTO.getPassword())
                .dateOfBirth(patientDTO.getDateOfBirth())
                .build();
    }

    public PatientDTO toPatientDTO(Patient patient) {
        return PatientDTO.builder()
                .patientName(patient.getPatientName())
                .mobileNumber(patient.getMobileNumber())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .email(patient.getEmail())
                .password(patient.getPassword())
                .build();
    }

    public List<PatientDTO> toPatientDTOS(List<Patient> allPatient) {
        List<PatientDTO>patientDTOS=new ArrayList<>();
        for (Patient patient:allPatient){
            patientDTOS.add(toPatientDTO(patient));
        }
        return patientDTOS;
    }
}
