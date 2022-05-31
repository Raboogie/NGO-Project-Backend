package com.example.ngoprojectbackend.repo;

import com.example.ngoprojectbackend.model.ERole;
import com.example.ngoprojectbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
