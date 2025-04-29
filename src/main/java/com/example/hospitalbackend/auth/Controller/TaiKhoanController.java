package com.example.hospitalbackend.auth.Controller;

import com.example.hospitalbackend.auth.Service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/taikhoan")
public class TaiKhoanController {

    @Autowired
    private TaiKhoanService taiKhoanService;

    @PostMapping("/dangky")
    public ResponseEntity<String> dangKy(
            @RequestParam String maBenhNhan,
            @RequestParam String tenDangNhap,
            @RequestParam String matKhau,
            @RequestParam String xacNhanMatKhau
    ) {
        String result = taiKhoanService.dangKyTaiKhoan(maBenhNhan, tenDangNhap, matKhau, xacNhanMatKhau);
        if (result.contains("thành công")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }

    @PostMapping("/dangnhap")
    public ResponseEntity<String> dangNhap(
            @RequestParam String tenDangNhap,
            @RequestParam String matKhau
    ) {
        boolean thanhCong = taiKhoanService.dangNhap(tenDangNhap, matKhau);
        if (thanhCong) {
            return ResponseEntity.ok("Đăng nhập thành công");
        } else {
            return ResponseEntity.status(401).body("Sai tài khoản hoặc mật khẩu");
        }
    }

    @PostMapping("/quenmatkhau")
    public ResponseEntity<String> quenMatKhau(@RequestParam String maTaiKhoan, @RequestParam String maBenhNhan, @RequestParam String email) {
        String result = taiKhoanService.quenMatKhau(maTaiKhoan, maBenhNhan, email);
        return ResponseEntity.ok(result);
    }

    // Lấy mã xác thực để đổi mật khẩu
    @GetMapping("/ma-xac-thuc/{maTaiKhoan}")
    public String layMaXacThuc(@PathVariable String maTaiKhoan) {
        String code = taiKhoanService.taoMaXacThuc(maTaiKhoan);
        if (code == null) return "Mã tài khoản không tồn tại";
        return "Mã xác thực của bạn là: " + code;
    }

    // Đổi mật khẩu
    @PostMapping("/doi-mat-khau")
    public String doiMatKhau(
            @RequestParam String maTaiKhoan,
            @RequestParam String matKhauCu,
            @RequestParam String matKhauMoi,
            @RequestParam String maXacThuc
    ) {
        return taiKhoanService.doiMatKhau(maTaiKhoan, matKhauCu, matKhauMoi, maXacThuc);
    }


}
