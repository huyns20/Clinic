package com.example.clinic.repository;

import com.example.clinic.entity.PhongKham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhongKhamRepository extends JpaRepository<PhongKham, String> {
    List<PhongKham> findByMaKhoa(String maKhoa);
}
