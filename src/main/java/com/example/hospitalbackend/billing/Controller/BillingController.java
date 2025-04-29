package com.example.hospitalbackend.billing.Controller;

import com.example.hospitalbackend.billing.Service.BillingService;
import com.example.hospitalbackend.billing.entity.HoaDon;
import com.example.hospitalbackend.billing.DTO.HoaDonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping("/createInvoice")
    public ResponseEntity<HoaDon> createInvoice(@RequestBody HoaDonDTO hoaDonDTO) {
        try {
            HoaDon hoaDon = billingService.createInvoice(hoaDonDTO);
            return new ResponseEntity<>(hoaDon, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/addServiceToCart")
    public ResponseEntity<Void> addServiceToCart(@RequestParam String maBN, @RequestParam String maDichVu, @RequestParam int soLuong) {
        try {
            billingService.addServiceToCart(maBN, maDichVu, soLuong);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity<HoaDon> checkout(@RequestParam String maBN) {
        try {
            HoaDon hoaDon = billingService.checkout(maBN);
            return new ResponseEntity<>(hoaDon, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/pay")
    public ResponseEntity<Void> payInvoice(@RequestParam String maHD, @RequestParam String phuongThuc) {
        try {
            billingService.payInvoice(maHD, phuongThuc);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
