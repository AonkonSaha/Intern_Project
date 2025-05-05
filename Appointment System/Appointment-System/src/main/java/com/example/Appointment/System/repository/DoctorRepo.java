package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long> {
    Doctor findByLicenseNumber(String licenseNumber);
}
