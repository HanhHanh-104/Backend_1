package com.example.hospitalbackend.Doctor.entity;

import com.example.hospitalbackend.KhoaPhong.entity.KhoaPhongEntity;
import com.example.hospitalbackend.auth.Entity.TaiKhoan;
import jakarta.persistence.*;

@Entity
public class BacSi {

    @Id
    private String maBS;

    @OneToOne
    @JoinColumn(name = "maTK", referencedColumnName = "maTK", nullable = false, unique = true)
    private TaiKhoan taiKhoan;

    @ManyToOne
    @JoinColumn(name = "maKhoa", referencedColumnName = "maKhoa", nullable = false)
    private KhoaPhongEntity khoaPhong;

    @Column(nullable = false)
    private String hoTen;

    private String chuyenMon;
    private String chucVu;
    private String trinhDo;

    public String getMaBS() {
        return maBS;
    }

    public void setMaBS(String maBS) {
        this.maBS = maBS;
    }

    public TaiKhoan getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(TaiKhoan taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public KhoaPhongEntity getKhoaPhong() {
        return khoaPhong;
    }

    public void setKhoaPhong(KhoaPhongEntity khoaPhong) {
        this.khoaPhong = khoaPhong;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getChuyenMon() {
        return chuyenMon;
    }

    public void setChuyenMon(String chuyenMon) {
        this.chuyenMon = chuyenMon;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    // Getters and Setters
}

