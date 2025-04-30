package com.example.hospitalbackend.CaKham.controller;



import com.example.hospitalbackend.common.response.ApiResponse;
import com.example.hospitalbackend.CaKham.dto.CaKhamDTO;
import com.example.hospitalbackend.CaKham.entity.CaKham;
import com.example.hospitalbackend.CaKham.service.CaKhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cakham")
public class CaKhamController {

    @Autowired
    private CaKhamService service;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> add(@RequestBody CaKhamDTO dto) {
        return ResponseEntity.ok(service.save(dto));
    }
}

