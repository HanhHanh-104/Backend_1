package com.example.hospitalbackend.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan, String> {
    boolean existsByMaBenhNhan(String maBenhNhan);
    boolean existsBySoDienThoai(String soDienThoai);
    boolean existsByCccd(String cccd);
    boolean existsBySoBaoHiemYTe(String soBaoHiemYTe);

}
