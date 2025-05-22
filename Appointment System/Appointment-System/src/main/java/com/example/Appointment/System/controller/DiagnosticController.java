package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.DiagnosticCenterNotFoundException;
import com.example.Appointment.System.model.dto.DiagnosticDTO;
import com.example.Appointment.System.model.mapper.DiagnosticMapper;
import com.example.Appointment.System.service.DiagnosticCenterService;
import com.example.Appointment.System.service.Imp.DiagnosticCenterServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/diagnostic/center")
@RequiredArgsConstructor
public class DiagnosticController {
    private final DiagnosticCenterService diagnosticCenterService;
    private final DiagnosticMapper diagnosticMapper;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("End Point Test");
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerDiagnostic(DiagnosticDTO diagnosticDTO){
     if(diagnosticCenterService.isExitDianosticCenterName(diagnosticDTO.getDiagnosticCenterName())){
         return ResponseEntity.badRequest().body("DiagnosticCenterName already Exit");
     }
     if(diagnosticDTO.getCity().isEmpty()){
         return ResponseEntity.badRequest().body("City can't be empty");
     }
     if(diagnosticDTO.getCountry().isEmpty()){
         return ResponseEntity.badRequest().body("Country can't be empty");
     }
     if (diagnosticDTO.getAddress().isEmpty()){
         return ResponseEntity.badRequest().body("Address can't be empty");
     }
     if (diagnosticDTO.getRoadNo().isEmpty()){
         return ResponseEntity.badRequest().body("RoadNo can't be empty");
     }
     if (diagnosticDTO.getHoldingNo().isEmpty()){
         return ResponseEntity.badRequest().body("HoldingNo can't be empty");
     }
     if (diagnosticDTO.getDiagnosticCenterName().isEmpty()){
         return ResponseEntity.badRequest().body("DiagnosticCenterName can't be empty");
     }
        return ResponseEntity.ok(
                diagnosticMapper.toDiagnosticDTO(diagnosticCenterService.saveDiagnosticCenter(
                        diagnosticMapper.toDiagnosticCenter(diagnosticDTO)
                )));
    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<DiagnosticDTO> fetchDiagnosticCenterById(@PathVariable Long id) throws DiagnosticCenterNotFoundException {
        if(!diagnosticCenterService.isExitDianosticCenterById(id)){
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exit");
        }
        return ResponseEntity.ok(
                diagnosticMapper.toDiagnosticDTO(diagnosticCenterService.getDiagnosticCenterById(id))
        );
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteDiagnosticCenterById(@PathVariable Long id) throws DiagnosticCenterNotFoundException {
        if(!diagnosticCenterService.isExitDianosticCenterById(id)){
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exit");
        }
        diagnosticCenterService.removeDiagnosticCenter(id);
        return ResponseEntity.ok("DiagnosticCenter deleted successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<DiagnosticDTO> updateDiagnosticCenterById(@PathVariable Long id,@RequestBody DiagnosticDTO diagnosticDTO) throws DiagnosticCenterNotFoundException {
        if(!diagnosticCenterService.isExitDianosticCenterById(id)){
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exit");
        }
        return ResponseEntity.ok(
                diagnosticMapper.toDiagnosticDTO(diagnosticCenterService.updateDiagnosticCenter(id,diagnosticDTO))
        );
    }
    @GetMapping("/fetch/all")
    public ResponseEntity<?> fetchAllDiagnosticCenter() throws DiagnosticCenterNotFoundException {
        if(diagnosticCenterService.getAllDiagnosticCenter().isEmpty()){
            return ResponseEntity.badRequest().body("Diagnostic Center doesn't exit");
        }
        return ResponseEntity.ok(
                Map.of("clinics",diagnosticMapper.toDiagnosticDTOS(diagnosticCenterService.getAllDiagnosticCenter())));
    }
}
