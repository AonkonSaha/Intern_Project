package com.example.spring_intro.repository;

import com.example.spring_intro.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<UserRole,Long> {
}
