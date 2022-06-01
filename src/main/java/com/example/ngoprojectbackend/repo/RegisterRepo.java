package com.example.ngoprojectbackend.repo;

import com.example.ngoprojectbackend.model.Event;
import com.example.ngoprojectbackend.model.Registeration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepo extends JpaRepository<Registeration, Integer> {
}
