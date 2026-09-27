package com.example.clinic.repository;

import com.example.clinic.entity.Luong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LuongRepository extends JpaRepository<Luong, String> {
    List<Luong> findByMaNV(String maNV);
}
