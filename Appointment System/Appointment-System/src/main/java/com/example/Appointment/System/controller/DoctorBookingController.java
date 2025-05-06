package com.example.Appointment.System.controller;

import com.example.Appointment.System.exception.DoctorBookNotFoundException;
import com.example.Appointment.System.model.dto.DoctorBookingDTO;
import com.example.Appointment.System.model.mapper.DoctorBookingMapper;
import com.example.Appointment.System.service.DoctorBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctor/booking")
@RequiredArgsConstructor
public class DoctorBookingController {
    private final DoctorBookingService doctorBookingService;
    private final DoctorBookingMapper doctorBookingMapper;
    @PostMapping("/register")
    public ResponseEntity<DoctorBookingDTO> registerDoctorBooking(@RequestBody DoctorBookingDTO doctorBookingDTO){
        return ResponseEntity.ok(doctorBookingMapper.toDoctorBookingDTO(doctorBookingService.saveDoctorBooking(
                doctorBookingMapper.toDoctorBooking(doctorBookingDTO))));

    }
    @GetMapping("/fetch/{id}")
    public ResponseEntity<DoctorBookingDTO> fetchDoctorBookingById(@PathVariable("id") Long id) throws DoctorBookNotFoundException {
        if(doctorBookingService.isExitDoctorBookById(id)){
            throw new DoctorBookNotFoundException("Doctor booking doesn't exit");
        }
        return ResponseEntity.ok(doctorBookingMapper.toDoctorBookingDTO(
                doctorBookingService.fetchDoctorBookById(id)));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDoctorBookingById(@PathVariable("id") Long id) throws DoctorBookNotFoundException {
        if(!doctorBookingService.isExitDoctorBookById(id)){
            throw new DoctorBookNotFoundException("DoctorBook doesn't exit");
        }
        doctorBookingService.deleteDoctorBookById(id);
        return ResponseEntity.ok("Doctor booking deleted successfully");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<DoctorBookingDTO> updateDoctorBookingById(
            @PathVariable("id") Long id,@RequestBody DoctorBookingDTO doctorBookingDTO) throws DoctorBookNotFoundException {
        if(doctorBookingService.isExitDoctorBookById(id)){
            throw new DoctorBookNotFoundException("DoctorBook doesn't exit");
        }
        return ResponseEntity.ok(doctorBookingMapper.toDoctorBookingDTO(
                doctorBookingService.updateDoctorBooking(id,doctorBookingDTO)
        ));
    }
    @GetMapping("/fetch/all")
    public ResponseEntity<List<DoctorBookingDTO>> fetchAllDoctorBookings() throws DoctorBookNotFoundException {
        if(doctorBookingService.fetchAllDoctorBooking().isEmpty()){
            throw new DoctorBookNotFoundException("DoctorBook doesn't exit");
        }
        return ResponseEntity.ok(
                doctorBookingMapper.toDoctorBookingDTOS(doctorBookingService.fetchAllDoctorBooking()));
    }
}
