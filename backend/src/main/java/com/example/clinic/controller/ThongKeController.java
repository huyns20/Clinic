package com.example.clinic.controller;

import com.example.clinic.entity.Luong;
import com.example.clinic.repository.LuongRepository;
import com.example.clinic.service.ThongKeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/thong-ke")
@RequiredArgsConstructor
@Tag(name = "Thống Kê & Báo Cáo", description = "KPI, tổng quan và báo cáo tài chính phòng khám theo funtion.txt")
@CrossOrigin(origins = "*")
public class ThongKeController {
    private final ThongKeService thongKeService;
    private final LuongRepository luongRepository;

    @GetMapping("/tong-quan")
    @Operation(summary = "Lấy các chỉ số KPI tổng quan cho Dashboard")
    public ResponseEntity<Map<String, Object>> getTongQuan() {
        return ResponseEntity.ok(thongKeService.getTongQuanDashboard());
    }

    @GetMapping("/doanh-thu-khoa")
    @Operation(summary = "Thống kê doanh thu theo từng khoa chuyên môn")
    public ResponseEntity<Map<String, BigDecimal>> getDoanhThuKhoa() {
        return ResponseEntity.ok(thongKeService.getDoanhThuTheoKhoa());
    }

    @GetMapping("/benh-theo-thang")
    @Operation(summary = "Thống kê các loại bệnh mắc phải trong 1 tháng (sắp xếp giảm dần, tính tái phát - Mục 2.1)")
    public ResponseEntity<List<Map<String, Object>>> getBenhTheoThang(@RequestParam(required = false) String thang) {
        return ResponseEntity.ok(thongKeService.getThongKeBenhTheoThang(thang));
    }

    @GetMapping("/doanh-thu-chi-tiet")
    @Operation(summary = "Doanh thu phòng khám chi tiết theo 5 nguồn thu (khám, chữa, thuốc, dịch vụ, thiết bị/giường - Mục 2.2)")
    public ResponseEntity<Map<String, BigDecimal>> getDoanhThuChiTiet(@RequestParam(required = false) String thang) {
        return ResponseEntity.ok(thongKeService.getDoanhThuChiTiet(thang));
    }

    @GetMapping("/bang-luong-chi-tiet")
    @Operation(summary = "Bảng lương Bác sĩ (thưởng 1tr/ca khỏi) và Y tá (thưởng 200k/lượt hỗ trợ) - Mục 3")
    public ResponseEntity<List<Map<String, Object>>> getBangLuongChiTiet(@RequestParam(required = false) String thang) {
        return ResponseEntity.ok(thongKeService.getBangLuongChiTiet(thang));
    }

    @GetMapping("/luong-nhan-vien")
    @Operation(summary = "Xem dữ liệu bảng luong gốc")
    public ResponseEntity<List<Luong>> getBangLuongGoc() {
        return ResponseEntity.ok(luongRepository.findAll());
    }
}
