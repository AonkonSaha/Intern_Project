package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.PatientProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<PatientProfile,Long> {
  Optional<PatientProfile> findByPatientName(String patientName);

//   @Query("SELECT pt FROM PatientProfile pt" +
//           " WHERE pt.patientName = :patientName" +
//           " AND pt.email = :email AND pt.mobileNumber = :contact ")
//   Optional<PatientProfile> findPatient(@Param("patientName") String patientName,
//                                        @Param("email") String email,
//                                        @Param("contact") String contact
//                                 );
}
