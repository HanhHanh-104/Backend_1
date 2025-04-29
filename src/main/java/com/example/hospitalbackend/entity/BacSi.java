package com.example.hospitalbackend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class BacSi {

    @Id
    private String maBS;

    @Column(nullable = false)
    private String hoTen;

    @Column(nullable = false)
    private String chuyenKhoa;

    private String chucVU;

    private String trìnhDo;

    private String soDienThoai;

    private String email;

    // Getters and Setters

    public String getMaBS() {
        return maBS;
    }

    public void setMaBS(String maBS) {
        this.maBS = maBS;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getChuyenKhoa() {
        return chuyenKhoa;
    }

    public void setChuyenKhoa(String chuyenKhoa) {
        this.chuyenKhoa = chuyenKhoa;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTrìnhDo() {
        return trìnhDo;
    }

    public void setTrìnhDo(String trìnhDo) {
        this.trìnhDo = trìnhDo;
    }

    public String getChucVU() {
        return chucVU;
    }

    public void setChucVU(String chucVU) {
        this.chucVU = chucVU;
    }
}
