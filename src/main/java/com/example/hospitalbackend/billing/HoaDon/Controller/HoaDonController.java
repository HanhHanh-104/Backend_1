package com.example.hospitalbackend.billing.Controller;

import com.example.hospitalbackend.billing.DTO.HoaDonDTO;
import com.example.hospitalbackend.billing.Service.HoaDonService;
import com.example.hospitalbackend.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hoadon")
public class HoaDonController {

    @Autowired
    private HoaDonService hoaDonService;

    // ✅ Tạo hóa đơn mới (POST)
    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody HoaDonDTO hoaDonDTO) {
        return ResponseEntity.ok(hoaDonService.createInvoice(hoaDonDTO));
    }

    // ✅ Lấy danh sách hóa đơn
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getAll() {
        return ResponseEntity.ok(hoaDonService.getAllInvoices());
    }

    // ✅ Cập nhật trạng thái hóa đơn
    @PutMapping("/{maHD}/trangthai")
    public ResponseEntity<ApiResponse<?>> updateStatus(
            @PathVariable String maHD,
            @RequestParam String trangThai
    ) {
        return ResponseEntity.ok(hoaDonService.updateStatus(maHD, trangThai));
    }
}
