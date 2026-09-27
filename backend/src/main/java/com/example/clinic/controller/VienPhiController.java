package com.example.clinic.controller;

import com.example.clinic.entity.HoaDon;
import com.example.clinic.service.VienPhiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hoa-don")
@RequiredArgsConstructor
@Tag(name = "Thu Ngân & Hóa Đơn", description = "Quản lý viện phí, tra cứu và xác nhận thanh toán")
@CrossOrigin(origins = "*")
public class VienPhiController {
    private final VienPhiService vienPhiService;

    @GetMapping
    @Operation(summary = "Lấy danh sách tất cả hóa đơn (có thể lọc theo trạng thái)")
    public ResponseEntity<List<HoaDon>> getAll(@RequestParam(required = false) String trangThaiTT) {
        if (trangThaiTT != null && !trangThaiTT.isBlank()) {
            return ResponseEntity.ok(vienPhiService.getByStatus(trangThaiTT));
        }
        return ResponseEntity.ok(vienPhiService.getAll());
    }

    @GetMapping("/{maSuKien}")
    @Operation(summary = "Xem chi tiết hóa đơn theo mã sự kiện y tế")
    public ResponseEntity<HoaDon> getById(@PathVariable String maSuKien) {
        return ResponseEntity.ok(vienPhiService.getById(maSuKien));
    }

    @PutMapping("/{maSuKien}/thanh-toan")
    @Operation(summary = "Xác nhận thanh toán viện phí (chuyển sang DaThanhToan)")
    public ResponseEntity<String> thanhToan(@PathVariable String maSuKien) {
        vienPhiService.thanhToanHoaDon(maSuKien);
        return ResponseEntity.ok("Xác nhận thanh toán thành công!");
    }
}
