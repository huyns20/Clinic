package com.example.clinic.repository;

import com.example.clinic.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, String> {
    List<HoaDon> findByTrangThaiTT(String trangThaiTT);

    @Query("SELECT COALESCE(SUM(h.tongTien), 0) FROM HoaDon h WHERE h.trangThaiTT = 'DaThanhToan'")
    BigDecimal calculateTotalRevenue();
}
