package com.example.clinic.repository;

import com.example.clinic.entity.LanKham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanKhamRepository extends JpaRepository<LanKham, String> {
}
