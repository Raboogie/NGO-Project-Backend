package com.example.ngoprojectbackend.service;

import com.example.ngoprojectbackend.model.Event;
import com.example.ngoprojectbackend.model.Registeration;
import com.example.ngoprojectbackend.repo.RegisterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RegisterService {
    @Autowired
    private RegisterRepo registerRepo;

    public List<Registeration> getReg() {
        return registerRepo.findAll();
    }

    public Registeration getRegById(int id) {
        return registerRepo.findById(id).get();
    }

    public Registeration createReg(Registeration event) {
        registerRepo.save(event);
        return event;
    }

    public void deleteReg(int id) {
        registerRepo.deleteById(id);
    }
}
