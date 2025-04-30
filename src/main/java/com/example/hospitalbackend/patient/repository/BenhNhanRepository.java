package com.example.hospitalbackend.patient.respository;

import com.example.hospitalbackend.patient.entity.BenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan, String> {
    boolean existsByMaBenhNhan(String maBenhNhan);
    boolean existsBySoDienThoai(String soDienThoai);
    boolean existsByCccd(String cccd);
    boolean existsBySoBaoHiemYTe(String soBaoHiemYTe);
    boolean existsByEmail(String email);

    Optional<BenhNhan> findByMaBenhNhan(String maBenhNhan);
    List<BenhNhan> findByHoTenContainingIgnoreCase(String hoTen);
    Optional<BenhNhan> findByCccd(String cccd);
    Optional<BenhNhan> findBySoDienThoai(String soDienThoai);
}