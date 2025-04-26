package com.example.hospitalbackend.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LichHenService {

    @Autowired
    private LichHenRepository lichHenRepository;

    public LichHen taoLichHen(LichHen lichHen) {
        // Kiểm tra xem lịch hẹn đã tồn tại chưa, có thể kiểm tra theo mã bệnh nhân và ngày hẹn
        Optional<LichHen> existingLichHen = lichHenRepository.findById(lichHen.getMaLH());
        if (existingLichHen.isPresent()) {
            throw new IllegalStateException("Lịch hẹn đã tồn tại");
        }

        return lichHenRepository.save(lichHen);
    }
}
