package com.example.clinic.repository;

import com.example.clinic.entity.DanhMucBenh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DanhMucBenhRepository extends JpaRepository<DanhMucBenh, String> {
}
