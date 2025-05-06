package com.example.Appointment.System.repository;

import com.example.Appointment.System.model.entity.MUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<MUser,Long> {
    Optional<MUser> findByUserName(String username);
}
