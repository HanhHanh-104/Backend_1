package com.example.hospitalbackend.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LichHenRepository extends JpaRepository<LichHen, String> {
    // Bạn có thể thêm các query tùy chỉnh ở đây nếu cần
}
