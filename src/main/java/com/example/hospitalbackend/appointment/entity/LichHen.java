package com.example.hospitalbackend.appointment.entity;

import com.example.hospitalbackend.entity.BacSi;
import com.example.hospitalbackend.entity.CaKham;
import com.example.hospitalbackend.patient.entity.BenhNhan;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;

import java.time.LocalDateTime;

@Entity
public class LichHen {

    @Id
    private String maLH; // Mã lịch hẹn

    @ManyToOne
    @JoinColumn(name = "maBN", referencedColumnName = "maBN", foreignKey = @ForeignKey(name = "FK_LichHen_BenhNhan"))
    private BenhNhan benhNhan; // Tham chiếu đến bệnh nhân

    @ManyToOne
    @JoinColumn(name = "maBS", referencedColumnName = "maBS", foreignKey = @ForeignKey(name = "FK_LichHen_BacSi"))
    private BacSi bacSi; // Tham chiếu đến bác sĩ

    @ManyToOne
    @JoinColumn(name = "maCa", referencedColumnName = "maCa", foreignKey = @ForeignKey(name = "FK_LichHen_CaKham"))
    private CaKham caKham; // Tham chiếu đến ca khám

    private LocalDateTime ngayHen; // Ngày giờ hẹn

    private String trangThai = "ChoXacNhan"; // Trạng thái mặc định là 'ChoXacNhan'

    // Getters và Setters

    public String getMaLH() {
        return maLH;
    }

    public void setMaLH(String maLH) {
        this.maLH = maLH;
    }

    public BenhNhan getBenhNhan() {
        return benhNhan;
    }

    public void setBenhNhan(BenhNhan benhNhan) {
        this.benhNhan = benhNhan;
    }

    public BacSi getBacSi() {
        return bacSi;
    }

    public void setBacSi(BacSi bacSi) {
        this.bacSi = bacSi;
    }

    public CaKham getCaKham() {
        return caKham;
    }

    public void setCaKham(CaKham caKham) {
        this.caKham = caKham;
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
}
