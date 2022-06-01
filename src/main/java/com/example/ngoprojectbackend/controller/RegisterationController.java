package com.example.ngoprojectbackend.controller;

import com.example.ngoprojectbackend.model.Event;
import com.example.ngoprojectbackend.model.Registeration;
import com.example.ngoprojectbackend.model.User;
import com.example.ngoprojectbackend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/reg")
@RestController
public class RegisterationController {
    @Autowired
    private RegisterService registerService;

    @GetMapping("/getAllRegs")
    public List<Registeration> RegList() {
        return registerService.getReg();
    }

    @GetMapping("/getRegById")
    public ResponseEntity<Registeration> UserByIdList(@PathVariable int id) {
        Registeration reg = registerService.getRegById(id);
        return ResponseEntity.ok(reg);
    }

    @PutMapping("/updateReg/{id}")
    public ResponseEntity<Registeration> updateUser(@PathVariable int id, @RequestBody Registeration regInfo) {
        Registeration reg = registerService.getRegById(id);

        reg.setAddress(regInfo.getAddress());
        reg.setAdultQty(regInfo.getAdultQty());
        reg.setChildQty(regInfo.getChildQty());
        reg.setEvent(regInfo.getEvent());
        reg.setPhoneNumber(regInfo.getPhoneNumber());

        Registeration updatedReg = registerService.createReg(reg);
        return ResponseEntity.ok(updatedReg);
    }

    @DeleteMapping("/deleteReg/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEvent(@PathVariable int id) {
        Registeration reg = registerService.getRegById(id);
        registerService.deleteReg(reg.getRegisterId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }



}
