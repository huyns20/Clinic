package com.example.clinic.repository;

import com.example.clinic.entity.LanChuaBenh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanChuaBenhRepository extends JpaRepository<LanChuaBenh, String> {
    List<LanChuaBenh> findByMaDotDieuTri(String maDotDieuTri);
}
