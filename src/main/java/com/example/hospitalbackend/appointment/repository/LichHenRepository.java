package com.example.hospitalbackend.appointment.repository;

import com.example.hospitalbackend.appointment.entity.LichHen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface LichHenRepository extends JpaRepository<LichHen, String> {
    // Đếm số lịch hẹn trong 1 ngày cho 1 bệnh nhân
    long countByBenhNhan_MaBNAndNgayHenBetween(String maBN, LocalDateTime startOfDay, LocalDateTime endOfDay);

    // Kiểm tra đã có ai đặt lịch cùng bác sĩ - ca khám - ngày giờ đó chưa
    boolean existsByBacSi_MaBSAndCaKham_MaCaAndNgayHen(String maBS, String maCa, LocalDateTime ngayHen);

    @Query("SELECT COUNT(lh) FROM LichHen lh WHERE lh.bacSi.maBS = :maBS AND lh.ngayHen = :ngayHen AND lh.caKham.maCa = :maCa")
    int countByBacSiAndNgayHenAndCa(String maBS, LocalDateTime ngayHen, String maCa);

    @Query("SELECT COUNT(lh) FROM LichHen lh WHERE lh.benhNhan.maBenhNhan = :maBN AND lh.ngayHen = :ngayHen AND lh.caKham.maCa = :maCa")
    int countByBenhNhanAndNgayHenAndCa(String maBN, LocalDateTime ngayHen, String maCa);


}
