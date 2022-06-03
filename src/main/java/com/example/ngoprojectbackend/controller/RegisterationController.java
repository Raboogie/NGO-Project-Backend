package com.example.ngoprojectbackend.controller;

import com.example.ngoprojectbackend.model.Event;
import com.example.ngoprojectbackend.model.Registeration;
import com.example.ngoprojectbackend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Registeration> RegByIdList(@PathVariable int id) {
        Registeration reg = registerService.getRegById(id);
        return ResponseEntity.ok(reg);
    }

    @PostMapping ("/addReg")
    public Registeration addReg(@Valid @RequestBody Registeration reg) {
        return registerService.createReg(reg);
    }
    @DeleteMapping("/deleteReg/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteReg(@PathVariable int id) {
        Registeration reg = registerService.getRegById(id);
        registerService.deleteReg(reg.getRegisterId());
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
