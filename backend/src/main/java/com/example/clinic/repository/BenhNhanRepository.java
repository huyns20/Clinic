package com.example.clinic.repository;

import com.example.clinic.entity.BenhNhan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan, String> {
    Optional<BenhNhan> findBySoCCCD(String soCCCD);

    @Query("SELECT b FROM BenhNhan b WHERE LOWER(b.hoTen) LIKE LOWER(CONCAT('%', :kw, '%')) OR b.sdt LIKE CONCAT('%', :kw, '%') OR b.soCCCD LIKE CONCAT('%', :kw, '%')")
    Page<BenhNhan> search(@Param("kw") String keyword, Pageable pageable);

    @Query("SELECT b FROM BenhNhan b ORDER BY b.ngayDangKy DESC")
    List<BenhNhan> findRecentPatients();
}
