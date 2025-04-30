package com.example.hospitalbackend.billing.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class HoaDonDTO {
    private String maHD;
    private String maBN;
    private String maNS;
    private LocalDateTime ngayLap;
    private BigDecimal tongTien;
    private String trangThai;
    private List<ChiTietHoaDonDTO> chiTiet;

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

    public List<ChiTietHoaDonDTO> getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(List<ChiTietHoaDonDTO> chiTiet) {
        this.chiTiet = chiTiet;
    }

    // Getters & Setters
}
