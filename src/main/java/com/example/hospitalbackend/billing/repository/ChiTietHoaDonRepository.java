package com.example.hospitalbackend.billing.repository;
import com.example.hospitalbackend.billing.entity.ChiTietHoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ChiTietHoaDonRepository extends JpaRepository<ChiTietHoaDon, String> {

    // Lấy chi tiết hóa đơn theo mã hóa đơn
    List<ChiTietHoaDon> findByMaHD(String maHD);

    // Lấy chi tiết hóa đơn theo loại dịch vụ
    List<ChiTietHoaDon> findByLoaiDichVu(String loaiDichVu);
}
