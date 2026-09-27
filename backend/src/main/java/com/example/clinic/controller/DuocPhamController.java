package com.example.clinic.controller;

import com.example.clinic.entity.Thuoc;
import com.example.clinic.repository.ThuocRepository;
import com.example.clinic.service.DuocPhamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/thuoc")
@RequiredArgsConstructor
@Tag(name = "Kho Dược Phẩm", description = "Quản lý danh mục thuốc, tồn kho và kê đơn")
@CrossOrigin(origins = "*")
public class DuocPhamController {
    private final DuocPhamService duocPhamService;
    private final ThuocRepository thuocRepository;

    @Data
    public static class KeDonRequest {
        private String maSuKien;
        private String maThuoc;
        private Integer soLuong;
    }

    @Data
    public static class NhapKhoRequest {
        private Integer soLuongNhap;
    }

    @GetMapping
    @Operation(summary = "Lấy danh mục tất cả các loại thuốc và số lượng tồn")
    public ResponseEntity<List<Thuoc>> getAll() {
        return ResponseEntity.ok(duocPhamService.getAll());
    }

    @GetMapping("/canh-bao-ton")
    @Operation(summary = "Cảnh báo các loại thuốc tồn kho dưới 200 đơn vị")
    public ResponseEntity<List<Thuoc>> getLowStock() {
        return ResponseEntity.ok(duocPhamService.getLowStock());
    }

    @GetMapping("/{maThuoc}")
    @Operation(summary = "Xem chi tiết thông tin thuốc")
    public ResponseEntity<Thuoc> getById(@PathVariable String maThuoc) {
        return ResponseEntity.ok(duocPhamService.getById(maThuoc));
    }

    @PostMapping
    @Operation(summary = "Thêm loại thuốc mới vào kho")
    public ResponseEntity<Thuoc> createThuoc(@RequestBody Thuoc thuoc) {
        if (thuoc.getMaThuoc() == null || thuoc.getMaThuoc().isBlank()) {
            long count = thuocRepository.count() + 1;
            thuoc.setMaThuoc(String.format("T%03d", count));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(thuocRepository.save(thuoc));
    }

    @PutMapping("/{maThuoc}/nhap-kho")
    @Operation(summary = "Nhập thêm số lượng tồn kho cho thuốc")
    public ResponseEntity<Thuoc> nhapKho(@PathVariable String maThuoc, @RequestBody NhapKhoRequest req) {
        Thuoc thuoc = duocPhamService.getById(maThuoc);
        int added = req.getSoLuongNhap() != null ? req.getSoLuongNhap() : 0;
        thuoc.setTonKho(thuoc.getTonKho() + added);
        return ResponseEntity.ok(thuocRepository.save(thuoc));
    }

    @PostMapping("/ke-don")
    @Operation(summary = "Kê đơn thuốc cho sự kiện y tế (tự động kiểm tra và trừ tồn kho)")
    public ResponseEntity<String> keDon(@RequestBody KeDonRequest req) {
        duocPhamService.keDonThuoc(req.getMaSuKien(), req.getMaThuoc(), req.getSoLuong());
        return ResponseEntity.ok("Kê đơn thuốc thành công và đã cập nhật tồn kho!");
    }
}
