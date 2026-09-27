package com.example.clinic.repository;

import com.example.clinic.entity.SuKienYTe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuKienYTeRepository extends JpaRepository<SuKienYTe, String> {
    List<SuKienYTe> findByMaBNOrderByThoiGianDesc(String maBN);
}
