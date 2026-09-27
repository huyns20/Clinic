package com.example.clinic.repository;

import com.example.clinic.entity.GiuongBenh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiuongBenhRepository extends JpaRepository<GiuongBenh, String> {
    List<GiuongBenh> findByTrangThai(String trangThai);
}
