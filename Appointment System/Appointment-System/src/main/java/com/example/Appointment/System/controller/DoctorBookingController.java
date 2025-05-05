package com.example.Appointment.System.controller;

import com.example.Appointment.System.model.dto.DoctorBookingDTO;
import com.example.Appointment.System.model.mapper.DoctorBookingMapper;
import com.example.Appointment.System.service.DoctorBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/update")
    public ResponseEntity<DoctorBookingDTO> updateDoctorBooking(){
        return null;
    }
    @PostMapping("/delete")
    public ResponseEntity<DoctorBookingDTO> deleteDoctorBooking(){
        return null;
    }
    @PostMapping("/fetch")
    public ResponseEntity<DoctorBookingDTO> fetchDoctorBooking(){
        return null;
    }
    @PostMapping("/fetch/all")
    public ResponseEntity<DoctorBookingDTO> fetchAllDoctorBookings(){
        return null;
    }
}
