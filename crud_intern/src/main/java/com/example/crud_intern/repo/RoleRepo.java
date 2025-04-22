package com.example.crud_intern.repo;

import com.example.crud_intern.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<UserRole,Long> {
}
