package com.example.hospitalbackend.auth.Service;

import com.example.hospitalbackend.auth.Entity.TaiKhoan;
import com.example.hospitalbackend.auth.repository.TaiKhoanRepository;
import com.example.hospitalbackend.patient.entity.BenhNhan;
import com.example.hospitalbackend.patient.respository.BenhNhanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class TaiKhoanService {

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private BenhNhanRepository benhNhanRepository;

    public String generateMaTaiKhoan() {
        String ma;
        Random random = new Random();
        do {
            int number = 100 + random.nextInt(900);
            ma = "TK" + number;
        } while (taiKhoanRepository.existsById(ma));
        return ma;
    }

    public String dangKyTaiKhoan(String maBenhNhan, String tenDangNhap, String matKhau, String xacNhanMatKhau) {
        if (!matKhau.equals(xacNhanMatKhau)) {
            return "Mật khẩu và xác nhận mật khẩu không khớp";
        }

        if (taiKhoanRepository.existsByTenDangNhap(tenDangNhap)) {
            return "Tên đăng nhập đã tồn tại";
        }

        Optional<BenhNhan> optional = benhNhanRepository.findById(maBenhNhan);
        if (optional.isEmpty()) {
            return "Mã bệnh nhân không tồn tại";
        }

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setMaTaiKhoan(generateMaTaiKhoan());
        taiKhoan.setTenDangNhap(tenDangNhap);
        taiKhoan.setMatKhau(matKhau);
        taiKhoan.setQuyen("BenhNhan");
        taiKhoan.setBenhNhan(optional.get());

        taiKhoanRepository.save(taiKhoan);
        return "Đăng ký tài khoản thành công";
    }

    public boolean dangNhap(String tenDangNhap, String matKhau) {
        return taiKhoanRepository.findByTenDangNhapAndMatKhau(tenDangNhap, matKhau).isPresent();
    }

    @Autowired
    private JavaMailSender mailSender;

    // Phương thức quên mật khẩu
    public String quenMatKhau(String maTaiKhoan, String maBenhNhan, String email) {
        // Kiểm tra mã tài khoản và mã bệnh nhân
        Optional<TaiKhoan> taiKhoanOptional = taiKhoanRepository.findById(maTaiKhoan);
        if (taiKhoanOptional.isEmpty()) {
            return "Mã tài khoản không tồn tại";
        }

        Optional<BenhNhan> benhNhanOptional = benhNhanRepository.findById(maBenhNhan);
        if (benhNhanOptional.isEmpty()) {
            return "Mã bệnh nhân không tồn tại";
        }

        // Kiểm tra email
        BenhNhan benhNhan = benhNhanOptional.get();
        if (!benhNhan.getEmail().equals(email)) {
            return "Email không khớp với thông tin bệnh nhân";
        }

        // Tạo mã xác thực (gửi qua email)
        String maXacThuc = generateVerificationCode();
        sendVerificationEmail(benhNhan.getEmail(), maXacThuc);

        return "Mã xác thực đã được gửi qua email";
    }

    private Map<String, String> verificationCodeMap = new HashMap<>();

    // Tạo và trả về mã xác thực (ví dụ dùng khi người dùng nhấn "Đổi mật khẩu")
    public String taoMaXacThuc(String maTaiKhoan) {
        if (!taiKhoanRepository.existsById(maTaiKhoan)) {
            return null;
        }
        String maXacThuc = generateVerificationCode();
        verificationCodeMap.put(maTaiKhoan, maXacThuc);
        return maXacThuc;
    }

    public String doiMatKhau(String maTaiKhoan, String matKhauCu, String matKhauMoi, String maXacThuc) {
        Optional<TaiKhoan> optional = taiKhoanRepository.findById(maTaiKhoan);
        if (optional.isEmpty()) {
            return "Tài khoản không tồn tại";
        }

        TaiKhoan taiKhoan = optional.get();

        if (!taiKhoan.getMatKhau().equals(matKhauCu)) {
            return "Mật khẩu cũ không đúng";
        }

        String storedCode = verificationCodeMap.get(maTaiKhoan);
        if (storedCode == null || !storedCode.equals(maXacThuc)) {
            return "Mã xác thực không hợp lệ";
        }

        taiKhoan.setMatKhau(matKhauMoi);
        taiKhoanRepository.save(taiKhoan);
        verificationCodeMap.remove(maTaiKhoan); // Xoá sau khi dùng
        return "Đổi mật khẩu thành công";
    }


    // Tạo mã xác thực ngẫu nhiên
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000); // Mã xác thực 6 chữ số
        return String.valueOf(code);
    }

    // Gửi email xác thực
    private void sendVerificationEmail(String toEmail, String verificationCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Mã xác thực thay đổi mật khẩu");
        message.setText("Mã xác thực của bạn là: " + verificationCode);
        mailSender.send(message);
    }


}
