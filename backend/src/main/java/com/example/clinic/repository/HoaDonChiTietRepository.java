package com.example.clinic.repository;

import com.example.clinic.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, HoaDonChiTiet.PK> {
    List<HoaDonChiTiet> findByMaSuKienOrderBySoDongAsc(String maSuKien);
}
