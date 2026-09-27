package com.example.clinic.repository;

import com.example.clinic.entity.NhanVienYTe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVienYTeRepository extends JpaRepository<NhanVienYTe, String> {
    List<NhanVienYTe> findByLoaiNV(String loaiNV);
}
