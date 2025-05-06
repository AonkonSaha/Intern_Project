package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor,Long> {
   Optional<Doctor> findByLicenseNumber(String licenseNumber);
}
