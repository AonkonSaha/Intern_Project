package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.DoctorNotFoundException;
import com.example.Appointment.System.model.dto.DoctorDTO;
import com.example.Appointment.System.model.mapper.DoctorMapper;
import com.example.Appointment.System.service.DoctorService;
import com.example.Appointment.System.service.Imp.DoctorServiceImp;
import com.example.Appointment.System.service.Imp.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final UserServiceImp userServiceImp;

    @PostMapping("/register")
    public ResponseEntity<?> registerDoctor(@RequestBody DoctorDTO doctorDTO){
        if(userServiceImp.isExitUserByContact(doctorDTO.getContactNumber())){
            return ResponseEntity.badRequest().body("This Mobile Number already registered by another user");
        }
        if (doctorDTO.getContactNumber().length()!=11){
            return ResponseEntity.badRequest().body("Mobile Number must be 11 digits");
        }
        if(doctorDTO.getDesignation().isEmpty()){
            return ResponseEntity.badRequest().body("Designation can't be empty");
        }
        if (doctorDTO.getDegrees().isEmpty()){
            return ResponseEntity.badRequest().body("Degrees/Degree List can't be empty");
        }
        if(doctorDTO.getContactNumber().isEmpty()){
            return ResponseEntity.badRequest().body("Mobile number can't be empty");
        }
        if (doctorDTO.getDoctorName().isEmpty()){
            return ResponseEntity.badRequest().body("Doctor name can't be empty");
        }
        if(doctorDTO.getPassword().length()<8){
            return ResponseEntity.badRequest().body("Doctor password must be at least 8 characters long");
        }
        if(!doctorDTO.getEmail().contains("@")){
            return ResponseEntity.badRequest().body("Email must contain @");
        }
        Set<String> gender=Set.of("male","female","other");
        if(!gender.contains(doctorDTO.getGender().toLowerCase())){
            return ResponseEntity.badRequest().body("Gender must be Male,Female or Other");
        }
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(
                doctorService.saveDoctor(doctorMapper.toDoctor(doctorDTO))
        ));}
    @GetMapping("/fetch/{id}")
    public ResponseEntity<?>fetchDoctorById(@PathVariable("id") Long id) throws DoctorNotFoundException {
        if(!doctorService.isExitDoctorById(id)){
            return ResponseEntity.badRequest().body("Doctor doesn't exit");
        }
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(
                doctorService.getDoctorById(id)));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDoctorById(@PathVariable("id") Long id,@RequestBody DoctorDTO doctorDTO) throws DoctorNotFoundException{
        if(!doctorService.isExitDoctorById(id)){
            return ResponseEntity.badRequest().body("Doctor doesn't exit");
        }
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(
                doctorService.updateDoctorById(id,doctorDTO)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable("id") Long id) throws DoctorNotFoundException{
        if(!doctorService.isExitDoctorById(id)){
            return ResponseEntity.badRequest().body("Doctor doesn't exit");
        }
        doctorService.deleteDoctorByDoctorId(id);
        return ResponseEntity.ok("Doctor deleted successfully");
    }
    @GetMapping("/fetch/all")
    public ResponseEntity<?> fetchAllDoctors() throws DoctorNotFoundException {
        if(doctorService.getAllDoctor().isEmpty()){
            return  ResponseEntity.badRequest().body("Doctor doesn't exit");
        }
        List<DoctorDTO>doctorDTOS=doctorMapper.toDoctorDTOs(doctorService.getAllDoctor());
        return ResponseEntity.ok(Map.of(
                "doctors",doctorDTOS
        ));
    }

}
