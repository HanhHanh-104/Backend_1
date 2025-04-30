package com.example.hospitalbackend.billing.Service;

import com.example.hospitalbackend.billing.DTO.ChiTietHoaDonDTO;
import com.example.hospitalbackend.billing.entity.ChiTietHoaDon;
import com.example.hospitalbackend.billing.repository.ChiTietHoaDonRepository;
import com.example.hospitalbackend.common.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChiTietHoaDonService {

    @Autowired
    private ChiTietHoaDonRepository repository;

    public ApiResponse<?> create(ChiTietHoaDonDTO dto) {
        if (repository.existsById(dto.getMaCTHD())) {
            return new ApiResponse<>(false, "Mã chi tiết hóa đơn đã tồn tại");
        }

        ChiTietHoaDon ct = new ChiTietHoaDon();
        ct.setMaCTHD(dto.getMaCTHD());
        ct.setMaHD(dto.getMaHD());
        ct.setLoaiDichVu(dto.getLoaiDichVu());
        ct.setMaDichVu(dto.getMaDichVu());
        ct.setDonGia(dto.getDonGia());
        ct.setSoLuong(dto.getSoLuong());
        ct.setThanhTien(dto.getThanhTien());

        return new ApiResponse<>(true, "Tạo chi tiết hóa đơn thành công", repository.save(ct));
    }

    public ApiResponse<List<ChiTietHoaDon>> getAll() {
        return new ApiResponse<>(true, "Danh sách chi tiết hóa đơn", repository.findAll());
    }

    public ApiResponse<?> getById(String maCTHD) {
        Optional<ChiTietHoaDon> optional = repository.findById(maCTHD);
        return optional
                .map(ct -> new ApiResponse<>(true, "Chi tiết hóa đơn", ct))
                .orElseGet(() -> new ApiResponse<>(false, "Không tìm thấy chi tiết hóa đơn"));
    }

    public ApiResponse<?> delete(String maCTHD) {
        if (!repository.existsById(maCTHD)) {
            return new ApiResponse<>(false, "Chi tiết hóa đơn không tồn tại");
        }
        repository.deleteById(maCTHD);
        return new ApiResponse<>(true, "Xóa thành công");
    }
}
