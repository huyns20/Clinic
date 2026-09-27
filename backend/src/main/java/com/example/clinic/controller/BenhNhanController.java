package com.example.clinic.controller;

import com.example.clinic.entity.BenhNhan;
import com.example.clinic.service.BenhNhanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/benh-nhan")
@RequiredArgsConstructor
@Tag(name = "Bệnh Nhân", description = "Quản lý hồ sơ tiếp nhận bệnh nhân")
@CrossOrigin(origins = "*")
public class BenhNhanController {
    private final BenhNhanService benhNhanService;

    @GetMapping
    @Operation(summary = "Lấy danh sách tất cả bệnh nhân")
    public ResponseEntity<List<BenhNhan>> getAll() {
        return ResponseEntity.ok(benhNhanService.getAll());
    }

    @GetMapping("/search")
    @Operation(summary = "Tìm kiếm bệnh nhân theo từ khóa (tên, SĐT, CCCD) có phân trang")
    public ResponseEntity<Page<BenhNhan>> search(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(benhNhanService.search(keyword, page, size));
    }

    @GetMapping("/{maBN}")
    @Operation(summary = "Xem chi tiết hồ sơ bệnh nhân theo mã")
    public ResponseEntity<BenhNhan> getById(@PathVariable String maBN) {
        return ResponseEntity.ok(benhNhanService.getById(maBN));
    }

    @GetMapping("/{maBN}/ho-so-360")
    @Operation(summary = "Hồ sơ bệnh án 360 độ (bệnh hiện tại, số lần khám chữa, lịch sử và chi tiết viện phí)")
    public ResponseEntity<java.util.Map<String, Object>> getHoSo360(@PathVariable String maBN) {
        return ResponseEntity.ok(benhNhanService.getHoSo360(maBN));
    }

    @PostMapping
    @Operation(summary = "Tiếp nhận bệnh nhân mới")
    public ResponseEntity<BenhNhan> create(@RequestBody BenhNhan benhNhan) {
        return ResponseEntity.status(HttpStatus.CREATED).body(benhNhanService.create(benhNhan));
    }

    @PutMapping("/{maBN}")
    @Operation(summary = "Cập nhật thông tin bệnh nhân")
    public ResponseEntity<BenhNhan> update(@PathVariable String maBN, @RequestBody BenhNhan benhNhan) {
        return ResponseEntity.ok(benhNhanService.update(maBN, benhNhan));
    }

    @DeleteMapping("/{maBN}")
    @Operation(summary = "Xóa hồ sơ bệnh nhân")
    public ResponseEntity<Void> delete(@PathVariable String maBN) {
        benhNhanService.delete(maBN);
        return ResponseEntity.noContent().build();
    }
}
