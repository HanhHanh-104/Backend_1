package com.example.hospitalbackend.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BenhNhanService {

    @Autowired
    private BenhNhanRepository benhNhanRepository;

    public String generateMaBenhNhan() {
        String ma;
        Random random = new Random();
        do {
            int number = 100 + random.nextInt(900); // 100–999
            ma = "BN" + number;
        } while (benhNhanRepository.existsByMaBenhNhan(ma));
        return ma;
    }

    public BenhNhan saveBenhNhan(BenhNhan benhNhan) {
        if (benhNhanRepository.existsBySoDienThoai(benhNhan.getSoDienThoai())) {
            throw new IllegalArgumentException("Số điện thoại đã được sử dụng.");
        }
        if (benhNhanRepository.existsByCccd(benhNhan.getCccd())) {
            throw new IllegalArgumentException("CCCD đã tồn tại.");
        }
        if (benhNhanRepository.existsBySoBaoHiemYTe(benhNhan.getSoBaoHiemYTe())) {
            throw new IllegalArgumentException("Số bảo hiểm y tế đã tồn tại.");
        }

        benhNhan.setMaBenhNhan(generateMaBenhNhan());
        return benhNhanRepository.save(benhNhan);
    }

}
