package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.DiagnosticCenterNotFoundException;
import com.example.Appointment.System.model.dto.DiagnosticDTO;
import com.example.Appointment.System.model.mapper.DiagnosticMapper;
import com.example.Appointment.System.service.DiagnosticCenterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diagnostic/center")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class DiagnosticController {
    private final DiagnosticCenterService diagnosticCenterService;
    private final DiagnosticMapper diagnosticMapper;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("End Point Test");
    }
    @PostMapping("/register")
    public ResponseEntity<DiagnosticDTO> registerDiagnostic(DiagnosticDTO diagnosticDTO){

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
    public ResponseEntity<Map<String,List<DiagnosticDTO>>> fetchAllDiagnosticCenter() throws DiagnosticCenterNotFoundException {
        if(diagnosticCenterService.getAllDiagnosticCenter().isEmpty()){
            throw new DiagnosticCenterNotFoundException("DiagnosticCenter doesn't exit");
        }
        return ResponseEntity.ok(
                Map.of("clinics",diagnosticMapper.toDiagnosticDTOS(diagnosticCenterService.getAllDiagnosticCenter())));
    }
}
