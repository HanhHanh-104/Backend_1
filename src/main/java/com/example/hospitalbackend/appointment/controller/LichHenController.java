package com.example.hospitalbackend.appointment.controller;

import com.example.hospitalbackend.appointment.service.LichHenService;
import com.example.hospitalbackend.appointment.entity.LichHen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid; // Đã sửa

@RestController
@RequestMapping("/lichhen")
public class LichHenController {

    @Autowired
    private LichHenService lichHenService;

    @PostMapping("/dangky")
    public ResponseEntity<LichHen> dangKyLichHen(@Valid @RequestBody LichHen lichHen) {
        try {
            LichHen newLichHen = lichHenService.taoLichHen(lichHen);
            return new ResponseEntity<>(newLichHen, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
