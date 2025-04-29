package com.example.hospitalbackend.auth.DTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String tenDangNhap;
    private String matKhau;
}


