package com.example.hospitalbackend.auth.DTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String maBenhNhan;
    private String tenDangNhap;
    private String matKhau;
    private String xacNhanMatKhau;
}
