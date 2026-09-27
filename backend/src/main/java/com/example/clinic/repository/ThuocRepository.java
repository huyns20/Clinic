package com.example.clinic.repository;

import com.example.clinic.entity.Thuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThuocRepository extends JpaRepository<Thuoc, String> {
    @Query("SELECT t FROM Thuoc t WHERE t.tonKho < 200")
    List<Thuoc> findLowStockMedicines();
}
