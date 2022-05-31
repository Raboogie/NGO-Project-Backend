package com.example.ngoprojectbackend.repo;

import com.example.ngoprojectbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer>{
        Optional<User> findByUsername(String username);
        Boolean existsByUsername(String username);
        Boolean existsByEmail(String email);


}