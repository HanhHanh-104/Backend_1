package com.example.hospitalbackend.Doctor.controller;


import com.example.hospitalbackend.common.response.ApiResponse;
import com.example.hospitalbackend.Doctor.dto.BacSiDTO;
import com.example.hospitalbackend.Doctor.entity.BacSi;
import com.example.hospitalbackend.Doctor.service.BacSiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bacsi")
public class BacSiController {

    @Autowired private BacSiService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BacSiDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }
}
