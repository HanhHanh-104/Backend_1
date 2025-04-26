package com.example.hospitalbackend.billing;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;



@Entity
public class ChiTietHoaDon {
    @Id
    private String maCTHD;
    private String maHD;
    private String loaiDichVu;
    private String maDichVu;
    private double donGia;
    private int soLuong;
    private double thanhTien;

    // Getters và Setters
}
