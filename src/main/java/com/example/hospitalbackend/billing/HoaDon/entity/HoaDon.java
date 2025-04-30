package com.example.hospitalbackend.billing.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class HoaDon {

    @Id
    private String maHD;

    @Column(nullable = false)
    private String maBN;

    @Column(nullable = false)
    private String maNS;

    private LocalDateTime ngayLap = LocalDateTime.now();

    private BigDecimal tongTien = BigDecimal.ZERO;

    private String trangThai = "CHUA_THANH_TOAN";

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getMaNS() {
        return maNS;
    }

    public void setMaNS(String maNS) {
        this.maNS = maNS;
    }

    public LocalDateTime getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(LocalDateTime ngayLap) {
        this.ngayLap = ngayLap;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    // Getters & Setters
}
