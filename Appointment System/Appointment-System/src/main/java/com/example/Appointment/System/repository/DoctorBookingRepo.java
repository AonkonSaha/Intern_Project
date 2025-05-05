package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.DoctorBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorBookingRepo extends JpaRepository<DoctorBooking,Long> {
}
