package com.example.hospitalbackend.patient;

import com.example.hospitalbackend.patient.service.BenhNhanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/benhnhan")
public class BenhNhanController {

    @Autowired
    private BenhNhanService benhNhanService;

    @PostMapping("/dangky")
    public ResponseEntity<?> dangKyBenhNhan(@RequestBody BenhNhan benhNhan) {
        try {
            BenhNhan created = benhNhanService.saveBenhNhan(benhNhan);
            return ResponseEntity.ok(created.getMaBenhNhan());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
