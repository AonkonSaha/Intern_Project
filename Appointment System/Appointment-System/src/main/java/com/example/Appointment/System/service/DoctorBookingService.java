package com.example.Appointment.System.service;

import com.example.Appointment.System.model.entity.DoctorBooking;
import com.example.Appointment.System.repository.DoctorBookingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorBookingService {
    private final DoctorBookingRepo doctorBookingRepo;
    public DoctorBooking saveDoctorBooking(DoctorBooking doctorBooking) {
        doctorBookingRepo.save(doctorBooking);
        return doctorBooking;
    }
}
