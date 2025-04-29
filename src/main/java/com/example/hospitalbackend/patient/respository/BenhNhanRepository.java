package com.example.hospitalbackend.patient.respository;

import com.example.hospitalbackend.patient.entity.BenhNhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan, String> {

    // Kiểm tra sự tồn tại của mã bệnh nhân, số điện thoại, CCCD, bảo hiểm y tế
    boolean existsByMaBenhNhan(String maBenhNhan);
    boolean existsBySoDienThoai(String soDienThoai);
    boolean existsByCccd(String cccd);
    boolean existsBySoBaoHiemYTe(String soBaoHiemYTe);
    BenhNhan findByMaBenhNhan(String maBenhNhan);

}
