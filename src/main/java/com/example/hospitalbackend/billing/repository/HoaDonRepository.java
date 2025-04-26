import com.example.hospitalbackend.billing.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {

    // Lấy danh sách hóa đơn theo mã bệnh nhân
    List<HoaDon> findByMaBN(String maBN);

    // Lấy danh sách hóa đơn theo trạng thái
    List<HoaDon> findByTrangThai(String trangThai);

    // Lấy hóa đơn theo mã hóa đơn
    HoaDon findByMaHD(String maHD);
}
