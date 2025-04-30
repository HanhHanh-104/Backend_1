package com.example.hospitalbackend.billing.Controller;

import com.example.hospitalbackend.billing.DTO.ChiTietHoaDonDTO;
import com.example.hospitalbackend.billing.Service.ChiTietHoaDonService;
import com.example.hospitalbackend.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chitiethoadon")
public class ChiTietHoaDonController {

    @Autowired
    private ChiTietHoaDonService service;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody ChiTietHoaDonDTO dto) {
        ApiResponse<?> response = service.create(dto);
        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.badRequest().body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{maCTHD}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable String maCTHD) {
        return ResponseEntity.ok(service.getById(maCTHD));
    }

    @DeleteMapping("/{maCTHD}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable String maCTHD) {
        return ResponseEntity.ok(service.delete(maCTHD));
    }
}
