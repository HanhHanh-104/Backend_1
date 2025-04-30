package com.example.hospitalbackend.billing.Service;

import com.example.hospitalbackend.billing.DTO.ChiTietHoaDonDTO;
import com.example.hospitalbackend.billing.DTO.HoaDonDTO;
import com.example.hospitalbackend.billing.entity.ChiTietHoaDon;
import com.example.hospitalbackend.billing.entity.HoaDon;
import com.example.hospitalbackend.billing.repository.ChiTietHoaDonRepository;
import com.example.hospitalbackend.billing.repository.HoaDonRepository;
import com.example.hospitalbackend.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class HoaDonService {

    @Autowired
    private HoaDonRepository hoaDonRepository;

    @Autowired
    private ChiTietHoaDonRepository chiTietHoaDonRepository;

    public ApiResponse<?> createInvoice(HoaDonDTO hoaDonDTO) {
        String maHD = "HD" + UUID.randomUUID().toString().substring(0, 8);

        HoaDon hoaDon = new HoaDon();
        hoaDon.setMaHD(maHD);
        hoaDon.setMaBN(hoaDonDTO.getMaBN());
        hoaDon.setMaNS(hoaDonDTO.getMaNS());
        hoaDon.setNgayLap(LocalDateTime.now());
        hoaDon.setTrangThai("CHUA_THANH_TOAN");

        BigDecimal tongTien = BigDecimal.ZERO;
        for (ChiTietHoaDonDTO chiTietDTO : hoaDonDTO.getChiTiet()) {
            BigDecimal thanhTien = chiTietDTO.getDonGia().multiply(BigDecimal.valueOf(chiTietDTO.getSoLuong()));
            tongTien = tongTien.add(thanhTien);
        }
        hoaDon.setTongTien(tongTien);
        hoaDonRepository.save(hoaDon);

        for (ChiTietHoaDonDTO chiTietDTO : hoaDonDTO.getChiTiet()) {
            ChiTietHoaDon chiTiet = new ChiTietHoaDon();
            chiTiet.setMaCTHD("CTHD" + UUID.randomUUID().toString().substring(0, 6));
            chiTiet.setMaHD(maHD);
            chiTiet.setLoaiDichVu(chiTietDTO.getLoaiDichVu());
            chiTiet.setMaDichVu(chiTietDTO.getMaDichVu());
            chiTiet.setDonGia(chiTietDTO.getDonGia());
            chiTiet.setSoLuong(chiTietDTO.getSoLuong());
            chiTiet.setThanhTien(chiTietDTO.getDonGia().multiply(BigDecimal.valueOf(chiTietDTO.getSoLuong())));
            chiTietHoaDonRepository.save(chiTiet);
        }

        return new ApiResponse<>(true, "Tạo hóa đơn thành công", hoaDon);
    }

    public ApiResponse<List<HoaDon>> getAllInvoices() {
        return new ApiResponse<>(true, "Danh sách hóa đơn", hoaDonRepository.findAll());
    }

    public ApiResponse<?> updateStatus(String maHD, String newStatus) {
        HoaDon hoaDon = hoaDonRepository.findById(maHD).orElse(null);
        if (hoaDon == null) {
            return new ApiResponse<>(false, "Không tìm thấy hóa đơn");
        }
        hoaDon.setTrangThai(newStatus);
        hoaDonRepository.save(hoaDon);
        return new ApiResponse<>(true, "Cập nhật trạng thái thành công", hoaDon);
    }
}
