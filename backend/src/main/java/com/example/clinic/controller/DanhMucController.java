package com.example.clinic.controller;

import com.example.clinic.entity.BacSy;
import com.example.clinic.entity.DanhMucBenh;
import com.example.clinic.entity.GiuongBenh;
import com.example.clinic.entity.Khoa;
import com.example.clinic.entity.PhongKham;
import com.example.clinic.repository.BacSyRepository;
import com.example.clinic.repository.DanhMucBenhRepository;
import com.example.clinic.repository.GiuongBenhRepository;
import com.example.clinic.repository.KhoaRepository;
import com.example.clinic.repository.PhongKhamRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Danh Mục Dùng Chung", description = "Các danh mục khoa, bác sĩ, bệnh, phòng khám và giường bệnh")
@CrossOrigin(origins = "*")
public class DanhMucController {
    private final KhoaRepository khoaRepository;
    private final BacSyRepository bacSyRepository;
    private final GiuongBenhRepository giuongBenhRepository;
    private final DanhMucBenhRepository danhMucBenhRepository;
    private final PhongKhamRepository phongKhamRepository;

    @GetMapping("/khoa")
    @Operation(summary = "Lấy danh mục các khoa điều trị")
    public ResponseEntity<List<Khoa>> getKhoa() {
        return ResponseEntity.ok(khoaRepository.findAll());
    }

    @GetMapping("/bac-sy")
    @Operation(summary = "Lấy danh sách các bác sĩ phụ trách")
    public ResponseEntity<List<BacSy>> getBacSy() {
        return ResponseEntity.ok(bacSyRepository.findAll());
    }

    @GetMapping("/danh-muc-benh")
    @Operation(summary = "Lấy toàn bộ danh mục bệnh lý chuẩn hóa")
    public ResponseEntity<List<DanhMucBenh>> getDanhMucBenh() {
        return ResponseEntity.ok(danhMucBenhRepository.findAll());
    }

    @GetMapping("/phong-kham")
    @Operation(summary = "Lấy danh sách các phòng chức năng")
    public ResponseEntity<List<PhongKham>> getPhongKham() {
        return ResponseEntity.ok(phongKhamRepository.findAll());
    }

    @GetMapping("/giuong-benh")
    @Operation(summary = "Lấy toàn bộ danh sách giường bệnh kèm trạng thái")
    public ResponseEntity<List<GiuongBenh>> getAllGiuong() {
        return ResponseEntity.ok(giuongBenhRepository.findAll());
    }

    @GetMapping("/giuong-benh/trong")
    @Operation(summary = "Lấy danh sách giường bệnh còn trống để phân bổ")
    public ResponseEntity<List<GiuongBenh>> getGiuongTrong() {
        return ResponseEntity.ok(giuongBenhRepository.findByTrangThai("Trong"));
    }
}
