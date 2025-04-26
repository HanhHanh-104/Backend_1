package com.example.hospitalbackend.billing;

import java.util.List;

public class HoaDonDTO {
    private String maBN;
    private String maNS;
    private double tongTien;
    private List<ChiTietHoaDon> dichVu;

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

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public List<ChiTietHoaDon> getDichVu() {
        return dichVu;
    }

    public void setDichVu(List<ChiTietHoaDon> dichVu) {
        this.dichVu = dichVu;
    }

}
