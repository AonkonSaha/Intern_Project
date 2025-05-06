package com.example.Appointment.System.service;

import com.example.Appointment.System.model.dto.DoctorBookingDTO;
import com.example.Appointment.System.model.entity.DoctorBooking;
import com.example.Appointment.System.repository.DoctorBookingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorBookingService {
    private final DoctorBookingRepo doctorBookingRepo;
    public DoctorBooking saveDoctorBooking(DoctorBooking doctorBooking) {
        doctorBookingRepo.save(doctorBooking);
        return doctorBooking;
    }

    public boolean isExitDoctorBookById(Long id) {
        return doctorBookingRepo.existsById(id);
    }

    public DoctorBooking fetchDoctorBookById(Long id) {
        Optional<DoctorBooking> doctorBooking=doctorBookingRepo.findById(id);
        if(doctorBooking.isEmpty()){
            return null;
        }
        return doctorBooking.get();
    }

    public void deleteDoctorBookById(Long id) {
        Optional<DoctorBooking> doctorBooking=doctorBookingRepo.findById(id);
        if(doctorBooking.isEmpty()){
            return;
        }
        doctorBookingRepo.deleteById(id);
    }

    public DoctorBooking updateDoctorBooking(Long id, DoctorBookingDTO doctorBookingDTO) {
        Optional<DoctorBooking> doctorBooking=doctorBookingRepo.findById(id);
        if(doctorBooking.isEmpty()){
            return null;
        }
        doctorBooking.get().setBookingDate(doctorBookingDTO.getBookingDate());
        doctorBooking.get().setAppointmentDate(doctorBookingDTO.getAppointmentDate());
        doctorBooking.get().setNote(doctorBookingDTO.getNote());
        doctorBooking.get().setStatus(doctorBookingDTO.getStatus());
        doctorBookingRepo.save(doctorBooking.get());
        return doctorBooking.get();
    }

    public List<DoctorBooking> fetchAllDoctorBooking() {
        List<DoctorBooking> doctorBookings = doctorBookingRepo.findAll();
        if(doctorBookings.isEmpty()){
            return new ArrayList<>();
        }
        return doctorBookingRepo.findAll();
    }
}
