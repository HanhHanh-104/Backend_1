package com.example.hospitalbackend.CaKham.service;

import com.example.hospitalbackend.common.response.ApiResponse;
import com.example.hospitalbackend.CaKham.dto.CaKhamDTO;
import com.example.hospitalbackend.CaKham.entity.CaKham;
import com.example.hospitalbackend.CaKham.repository.CaKhamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaKhamService {

    @Autowired
    private CaKhamRepository repository;

    public ApiResponse<List<CaKham>> getAll() {
        return new ApiResponse<>(true, "Danh sách ca khám", repository.findAll());
    }

    public ApiResponse<CaKham> save(CaKhamDTO dto) {
        CaKham ca = new CaKham();
        ca.setMaCa(dto.getMaCa());
        ca.setGioBatDau(dto.getGioBatDau());
        ca.setGioKetThuc(dto.getGioKetThuc());
        repository.save(ca);
        return new ApiResponse<>(true, "Thêm ca khám thành công", ca);
    }
}

