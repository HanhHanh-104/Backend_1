package com.example.hospitalbackend.patient.controller;

import com.example.hospitalbackend.patient.DTO.BenhNhanDTO;  // DTO để truyền thông tin
import com.example.hospitalbackend.patient.entity.BenhNhan;
import com.example.hospitalbackend.patient.service.BenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/benhnhan")
public class BenhNhanController {

    @Autowired
    private BenhNhanService benhNhanService;

    // Đăng ký bệnh nhân mới
    @PostMapping("/dangky")
    public ResponseEntity<?> dangKyBenhNhan(@RequestBody BenhNhanDTO benhNhanDTO) {
        try {
            BenhNhan created = benhNhanService.saveBenhNhan(benhNhanDTO);
            return ResponseEntity.ok(created.getMaBenhNhan());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Đã xảy ra lỗi hệ thống.");
        }
    }

    // Lấy thông tin bệnh nhân
    @GetMapping("/{maBenhNhan}")
    public ResponseEntity<?> getBenhNhan(@PathVariable String maBenhNhan) {
        try {
            BenhNhan benhNhan = benhNhanService.getBenhNhan(maBenhNhan);
            if (benhNhan == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(benhNhan);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Đã xảy ra lỗi hệ thống.");
        }
    }

    // Cập nhật thông tin bệnh nhân
    @PutMapping("/{maBenhNhan}")
    public ResponseEntity<?> capNhatBenhNhan(@PathVariable String maBenhNhan, @RequestBody BenhNhanDTO benhNhanDTO) {
        try {
            BenhNhan updated = benhNhanService.capNhatBenhNhan(maBenhNhan, benhNhanDTO);
            if (updated == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Đã xảy ra lỗi hệ thống.");
        }
    }
}
