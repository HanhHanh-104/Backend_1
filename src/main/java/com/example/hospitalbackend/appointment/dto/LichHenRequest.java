package com.example.hospitalbackend.appointment.dto;

import java.time.LocalDateTime;

public class LichHenRequest {
    private String maLH;
    private String maBN;
    private String maBS;
    private String maCa;
    private LocalDateTime ngayHen;
    private String trangThai;

    public String getMaLH() {
        return maLH;
    }

    public void setMaLH(String maLH) {
        this.maLH = maLH;
    }

    public String getMaBN() {
        return maBN;
    }

    public void setMaBN(String maBN) {
        this.maBN = maBN;
    }

    public String getMaBS() {
        return maBS;
    }

    public void setMaBS(String maBS) {
        this.maBS = maBS;
    }

    public String getMaCa() {
        return maCa;
    }

    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public LocalDateTime getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(LocalDateTime ngayHen) {
        this.ngayHen = ngayHen;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    // Getters & Setters
}
