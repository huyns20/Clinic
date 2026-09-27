package com.example.clinic.repository;

import com.example.clinic.entity.DichVuYTe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DichVuYTeRepository extends JpaRepository<DichVuYTe, String> {
}
