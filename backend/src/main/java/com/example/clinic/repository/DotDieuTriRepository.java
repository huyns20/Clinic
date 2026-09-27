package com.example.clinic.repository;

import com.example.clinic.entity.DotDieuTri;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DotDieuTriRepository extends JpaRepository<DotDieuTri, String> {
    List<DotDieuTri> findByTrangThai(String trangThai);

    @Modifying
    @Query(value = "CALL sp_dong_dot_dieu_tri(:maDot, :ngayKetThuc)", nativeQuery = true)
    void callDongDotDieuTri(@Param("maDot") String maDot, @Param("ngayKetThuc") LocalDate ngayKetThuc);
}
