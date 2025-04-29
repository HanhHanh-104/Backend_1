package com.example.hospitalbackend.patient.service;

import com.example.hospitalbackend.patient.DTO.BenhNhanDTO;
import com.example.hospitalbackend.patient.entity.BenhNhan;
import com.example.hospitalbackend.patient.respository.BenhNhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BenhNhanService {

    @Autowired
    private BenhNhanRepository benhNhanRepository;

    // Phương thức sinh mã bệnh nhân tự động
    public String generateMaBenhNhan() {
        String ma;
        Random random = new Random();
        do {
            int number = 100 + random.nextInt(900); // Mã bệnh nhân từ BN100 đến BN999
            ma = "BN" + number;
        } while (benhNhanRepository.existsByMaBenhNhan(ma)); // Kiểm tra mã đã tồn tại
        return ma;
    }

    // Lưu thông tin bệnh nhân
    public BenhNhan saveBenhNhan(BenhNhanDTO benhNhanDTO) {
        // Kiểm tra dữ liệu đầu vào trước khi lưu
        if (benhNhanRepository.existsBySoDienThoai(benhNhanDTO.getSoDienThoai())) {
            throw new IllegalArgumentException("Số điện thoại đã được sử dụng.");
        }
        if (benhNhanRepository.existsByCccd(benhNhanDTO.getCccd())) {
            throw new IllegalArgumentException("CCCD đã tồn tại.");
        }
        if (benhNhanRepository.existsBySoBaoHiemYTe(benhNhanDTO.getSoBaoHiemYTe())) {
            throw new IllegalArgumentException("Số bảo hiểm y tế đã tồn tại.");
        }

        // Chuyển DTO -> Entity
        BenhNhan benhNhan = new BenhNhan();
        benhNhan.setMaBenhNhan(generateMaBenhNhan());
        benhNhan.setHoTen(benhNhanDTO.getHoTen());
        benhNhan.setGioiTinh(benhNhanDTO.getGioiTinh());
        benhNhan.setNgaySinh(benhNhanDTO.getNgaySinh());
        benhNhan.setDiaChi(benhNhanDTO.getDiaChi());
        benhNhan.setSoDienThoai(benhNhanDTO.getSoDienThoai());
        benhNhan.setCccd(benhNhanDTO.getCccd());
        benhNhan.setSoBaoHiemYTe(benhNhanDTO.getSoBaoHiemYTe());
        benhNhan.setEmail(benhNhanDTO.getEmail());

        return benhNhanRepository.save(benhNhan);
    }

    public BenhNhan getBenhNhan(String maBenhNhan) {
        return benhNhanRepository.findByMaBenhNhan(maBenhNhan);
    }

    public BenhNhan capNhatBenhNhan(String maBenhNhan, BenhNhanDTO dto) {
        BenhNhan existing = benhNhanRepository.findByMaBenhNhan(maBenhNhan);
        if (existing == null) return null;

        // Cập nhật thông tin
        existing.setHoTen(dto.getHoTen());
        existing.setGioiTinh(dto.getGioiTinh());
        existing.setNgaySinh(dto.getNgaySinh());
        existing.setDiaChi(dto.getDiaChi());
        existing.setSoDienThoai(dto.getSoDienThoai());
        existing.setCccd(dto.getCccd());
        existing.setSoBaoHiemYTe(dto.getSoBaoHiemYTe());
        existing.setEmail(dto.getEmail());

        return benhNhanRepository.save(existing);
    }


}
