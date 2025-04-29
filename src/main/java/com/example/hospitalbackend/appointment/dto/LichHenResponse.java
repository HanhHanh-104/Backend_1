package com.example.hospitalbackend.appointment.dto;
import com.example.hospitalbackend.appointment.entity.LichHen;

public class LichHenResponse {
    private String maLH;
    private String tenBN;
    private String tenBS;
    private String khungGio;
    private String ngayHen;
    private String trangThai;

    public String getMaLH() {
        return maLH;
    }

    public void setMaLH(String maLH) {
        this.maLH = maLH;
    }

    public String getTenBN() {
        return tenBN;
    }

    public void setTenBN(String tenBN) {
        this.tenBN = tenBN;
    }

    public String getTenBS() {
        return tenBS;
    }

    public void setTenBS(String tenBS) {
        this.tenBS = tenBS;
    }

    public String getKhungGio() {
        return khungGio;
    }

    public void setKhungGio(String khungGio) {
        this.khungGio = khungGio;
    }

    public String getNgayHen() {
        return ngayHen;
    }

    public void setNgayHen(String ngayHen) {
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
