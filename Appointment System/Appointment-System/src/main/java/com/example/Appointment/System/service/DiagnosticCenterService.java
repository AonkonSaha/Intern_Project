package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.DiagnosticDTO;
import com.example.Appointment.System.model.entity.DiagnosticCenter;
import com.example.Appointment.System.repository.DiagnosticCenterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiagnosticCenterService {
    private final DiagnosticCenterRepo diagnosticCenterRepo;


    public DiagnosticCenter saveDiagnosticCenter(DiagnosticCenter diagnosticCenter) {
        diagnosticCenterRepo.save(diagnosticCenter);
        return diagnosticCenter;
    }

    public boolean isExitDianosticCenterById(Long id) {
        return diagnosticCenterRepo.existsById(id);
    }

    public DiagnosticCenter getDiagnosticCenterById(Long id) {
        Optional<DiagnosticCenter> diagnosticCenter=diagnosticCenterRepo.findById(id);
        if(diagnosticCenter.isEmpty()){
            return null;
        }
        return diagnosticCenter.get();
    }

    public void removeDiagnosticCenter(Long id) {
        if(!isExitDianosticCenterById(id)){
            return;
        }
        diagnosticCenterRepo.deleteById(id);
    }

    public DiagnosticCenter updateDiagnosticCenter(Long id, DiagnosticDTO diagnosticDTO) {
        Optional<DiagnosticCenter> diagnosticCenter=diagnosticCenterRepo.findById(id);
        if(diagnosticCenter.isEmpty()){
            return null;
        }
        diagnosticCenter.get().setAddress(diagnosticDTO.getAddress());
        diagnosticCenter.get().setCity(diagnosticDTO.getCity());
        diagnosticCenter.get().setCountry(diagnosticDTO.getCountry());
        diagnosticCenter.get().setDiagnosticCenterName(diagnosticDTO.getDiagnosticCenterName());
        diagnosticCenter.get().setContactNumber(diagnosticDTO.getContactNumber());
        diagnosticCenter.get().setAddress(diagnosticDTO.getAddress());
        diagnosticCenter.get().setHoldingNo(diagnosticDTO.getHoldingNo());
        diagnosticCenter.get().setRoadNo(diagnosticDTO.getRoadNo());
        diagnosticCenter.get().setZipCode(diagnosticDTO.getZipCode());
        diagnosticCenter.get().setOperatingHours(diagnosticDTO.getOperatingHours());
        diagnosticCenterRepo.save(diagnosticCenter.get());
        return diagnosticCenter.get();
    }

    public List<DiagnosticCenter> getAllDiagnosticCenter() {
        List<DiagnosticCenter> diagnosticCenters = diagnosticCenterRepo.findAll();
        if(diagnosticCenters.isEmpty()){
            return new java.util.ArrayList<>();
        }
        return diagnosticCenterRepo.findAll();
    }
}
