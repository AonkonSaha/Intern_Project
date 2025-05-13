package com.example.Appointment.System.service;

import com.example.Appointment.System.exception.DoctorNotFoundException;
import com.example.Appointment.System.model.dto.DoctorBookingDTO;
import com.example.Appointment.System.model.entity.DoctorAppointmentDate;
import com.example.Appointment.System.model.entity.DoctorBooking;
import com.example.Appointment.System.model.entity.DoctorProfile;
import com.example.Appointment.System.repository.DoctorAppointmentDateRepo;
import com.example.Appointment.System.repository.DoctorBookingRepo;
import com.example.Appointment.System.repository.DoctorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorBookingService {
    private final DoctorBookingRepo doctorBookingRepo;
    private final DoctorRepo doctorRepo;
    private final DoctorAppointmentDateRepo doctorAppointmentDateRepo;
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

    public List<String> getTimeSlotDoctorBooking(Long doctorId, LocalDate date) throws DoctorNotFoundException {
        List<String> timeSlots=doctorAppointmentDateRepo.findTimeSlotsByDoctorIdAndDate(doctorId,date);
        if(timeSlots.isEmpty()){
            return new ArrayList<>();
        }
        return timeSlots;
    }

    public List<DoctorBooking> getDoctorBookingHistory() {
        String patientContact = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("------------patient contact----"+patientContact+"-----------");
        List<DoctorBooking> doctorBookings = doctorBookingRepo.findAllByPatientContact(patientContact);
        if(doctorBookings.isEmpty()){
            return new ArrayList<>();
        }
        return  doctorBookings;
    }
}
