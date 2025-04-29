package com.example.hospitalbackend.patient.DTO;

import java.time.LocalDate;

public class BenhNhanDTO {

    private String maBenhNhan;
    private String hoTen;
    private String gioiTinh;
    private LocalDate ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String cccd;
    private String soBaoHiemYTe;
    private String email;

    // Constructor
    public BenhNhanDTO(String maBenhNhan, String hoTen, String gioiTinh, LocalDate ngaySinh,
                       String diaChi, String soDienThoai, String cccd, String soBaoHiemYTe, String email) {
        this.maBenhNhan = maBenhNhan;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.cccd = cccd;
        this.soBaoHiemYTe = soBaoHiemYTe;
        this.email = email;
    }

    // GETTERS và SETTERS
    public String getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(String maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }


    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getSoBaoHiemYTe() {
        return soBaoHiemYTe;
    }

    public void setSoBaoHiemYTe(String soBaoHiemYTe) {
        this.soBaoHiemYTe = soBaoHiemYTe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
