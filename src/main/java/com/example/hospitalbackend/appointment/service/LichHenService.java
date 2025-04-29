package com.example.hospitalbackend.appointment.service;

import com.example.hospitalbackend.appointment.entity.LichHen;
import com.example.hospitalbackend.appointment.repository.LichHenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LichHenService {

    @Autowired
    private LichHenRepository lichHenRepository;

    public LichHen taoLichHen(LichHen lichHen) {
        String maBN = lichHen.getBenhNhan().getMaBenhNhan();
        String maBS = lichHen.getBacSi().getMaBS();
        String maCa = lichHen.getCaKham().getMaCa();
        LocalDateTime ngayHen = lichHen.getNgayHen();

        // 1. Kiểm tra số lượng lịch trong cùng 1 ngày
        LocalDateTime startOfDay = ngayHen.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = ngayHen.toLocalDate().atTime(23, 59, 59);

        long soLuongLichTrongNgay = lichHenRepository
                .countByBenhNhan_MaBNAndNgayHenBetween(maBN, startOfDay, endOfDay);

        int soLuongToiDa = 10;
        int soLuongHienTai = lichHenRepository.countByBacSiAndNgayHenAndCa(maBS, ngayHen, maCa);
        if (soLuongHienTai >= soLuongToiDa) {
            throw new IllegalStateException("Ca khám này đã đủ số lượng bệnh nhân.");
        }


        if (soLuongLichTrongNgay >= 2) {
            throw new IllegalStateException("Bạn không được đặt quá 2 lịch hẹn trong cùng một ngày.");
        }

        // 2. Kiểm tra xem ca khám với bác sĩ đã đầy chưa (1 bệnh nhân/ca khám là ví dụ)
        boolean daCoNguoiDat = lichHenRepository
                .existsByBacSi_MaBSAndCaKham_MaCaAndNgayHen(maBS, maCa, ngayHen);

        if (daCoNguoiDat) {
            throw new IllegalStateException("Lịch hẹn đã đầy. Vui lòng chọn thời gian khác.");
        }

        if (ngayHen.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Không được chọn ngày trong quá khứ");
        }

        if (ngayHen.toLocalDate().isAfter(LocalDate.now().plusDays(30))) {
            throw new IllegalStateException("Chỉ được đặt lịch trong vòng 30 ngày tới");
        }

        DayOfWeek thu = ngayHen.getDayOfWeek();
        if (thu == DayOfWeek.SUNDAY || laNgayLe(ngayHen.toLocalDate())) {
            throw new IllegalStateException("Không thể đặt lịch vào Chủ nhật hoặc ngày lễ");
        }

        int countLichTrung = lichHenRepository.countByBenhNhanAndNgayHenAndCa(maBN, ngayHen, maCa);
        if (countLichTrung > 0) {
            throw new IllegalStateException("Bạn đã có lịch hẹn trùng thời gian.");
        }

        int gio = ngayHen.getHour();
        if (gio < 7 || gio > 17) {
            throw new IllegalStateException("Chỉ được đặt lịch trong giờ hành chính (7h-17h).");
        }

        // Nếu qua được 2 kiểm tra trên => tạo mới lịch
        return lichHenRepository.save(lichHen);
    }

    private boolean laNgayLe(LocalDate localDate) {
        // Ví dụ: 1/1, 30/4, 1/5,...
        List<LocalDate> ngayLe = List.of(
                LocalDate.of(localDate.getYear(), 1, 1),
                LocalDate.of(localDate.getYear(), 4, 30),
                LocalDate.of(localDate.getYear(), 5, 1)
                // ngày lễ khác
        );
        return ngayLe.contains(localDate);
    }


}
