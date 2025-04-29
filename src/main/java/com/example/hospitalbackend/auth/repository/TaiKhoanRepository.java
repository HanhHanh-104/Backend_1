package com.example.hospitalbackend.auth.repository;

import com.example.hospitalbackend.auth.Entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, String> {
    Optional<TaiKhoan> findByMaTaiKhoanAndMatKhau(String maTaiKhoan, String matKhau);
    boolean existsByTenDangNhap(String tenDangNhap);
    Optional<TaiKhoan> findByTenDangNhapAndMatKhau(String tenDangNhap, String matKhau);

}
