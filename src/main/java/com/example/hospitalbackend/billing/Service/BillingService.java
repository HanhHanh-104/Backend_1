package com.example.hospitalbackend.billing.Service;

import com.example.hospitalbackend.billing.entity.ChiTietHoaDon;
import com.example.hospitalbackend.billing.entity.HoaDon;
import com.example.hospitalbackend.billing.repository.HoaDonRepository;
import com.example.hospitalbackend.billing.repository.ChiTietHoaDonRepository;
import com.example.hospitalbackend.billing.DTO.HoaDonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    public HoaDon createInvoice(HoaDonDTO hoaDonDTO) {
        // Tạo hóa đơn
        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaBN(hoaDonDTO.getMaBN());
        hoaDon.setMaNS(hoaDonDTO.getMaNS());
        hoaDon.setTongTien(hoaDonDTO.getTongTien());
        hoaDon.setTrangThai("CHUA_THANH_TOAN");
        hoaDonRepository.save(hoaDon);

        // Thêm dịch vụ vào hóa đơn
        for (ChiTietHoaDon chiTiet : hoaDonDTO.getDichVu()) {
            chiTiet.setMaHD(hoaDon.getMaHD());
            chiTietHoaDonRepository.save(chiTiet);
        }

        return hoaDon;
    }

    public void addServiceToCart(String maBN, String maDichVu, int soLuong) {
        // Thêm dịch vụ vào giỏ hàng (Giả sử giỏ hàng đã có sẵn)
    }

    public HoaDon checkout(String maBN) {
        // Tính toán tổng tiền và tạo hóa đơn
        return new HoaDon();
    }

    public void payInvoice(String maHD, String phuongThuc) {
        // Cập nhật trạng thái thanh toán
    }
}
