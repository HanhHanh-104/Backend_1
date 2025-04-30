package com.example.hospitalbackend.CaKham.dto;



import lombok.Getter;

import java.time.LocalTime;

@Getter
public class CaKhamDTO {
    private String maCa;
    private String tenCa;
    private LocalTime gioBatDau;
    private LocalTime gioKetThuc;

    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    public void setGioBatDau(LocalTime gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public void setGioKetThuc(LocalTime gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public void setTenCa(String tenCa) {
        this.tenCa = tenCa;
    }
    // Getters & Setters
}
