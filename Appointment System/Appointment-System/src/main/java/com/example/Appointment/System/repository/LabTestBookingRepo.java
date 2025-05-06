package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.LabTestBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabTestBookingRepo extends JpaRepository<LabTestBooking,Long> {

}
