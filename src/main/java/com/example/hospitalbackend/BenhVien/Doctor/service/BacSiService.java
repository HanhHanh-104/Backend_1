package com.example.hospitalbackend.Doctor.service;

import com.example.hospitalbackend.KhoaPhong.repository.KhoaPhongRepository;
import com.example.hospitalbackend.auth.repository.TaiKhoanRepository;
import com.example.hospitalbackend.common.response.ApiResponse;
import com.example.hospitalbackend.Doctor.dto.BacSiDTO;
import com.example.hospitalbackend.Doctor.entity.BacSi;
import com.example.hospitalbackend.Doctor.repository.BacSiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BacSiService {

    @Autowired private BacSiRepository repository;
    @Autowired private TaiKhoanRepository taiKhoanRepository;
    @Autowired private KhoaPhongRepository khoaPhongRepository;

    public ApiResponse<BacSi> create(BacSiDTO dto) {
        BacSi bacSi = new BacSi();
        bacSi.setMaBS(dto.getMaBS());
        bacSi.setTaiKhoan(taiKhoanRepository.findById(dto.getMaTK()).orElse(null));
        bacSi.setKhoaPhong(khoaPhongRepository.findById(dto.getMaKhoa()).orElse(null));
        bacSi.setHoTen(dto.getHoTen());
        bacSi.setChuyenMon(dto.getChuyenMon());
        bacSi.setChucVu(dto.getChucVu());
        bacSi.setTrinhDo(dto.getTrinhDo());

        return new ApiResponse<>(true, "Thêm bác sĩ thành công", repository.save(bacSi));
    }

    public ApiResponse<List<BacSi>> getAll() {
        return new ApiResponse<>(true, "Danh sách bác sĩ", repository.findAll());
    }
}