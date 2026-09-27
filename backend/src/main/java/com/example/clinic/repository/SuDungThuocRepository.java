package com.example.clinic.repository;

import com.example.clinic.entity.SuDungThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuDungThuocRepository extends JpaRepository<SuDungThuoc, SuDungThuoc.PK> {
    List<SuDungThuoc> findByMaSuKien(String maSuKien);
}
