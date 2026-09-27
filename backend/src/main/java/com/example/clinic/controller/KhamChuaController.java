package com.example.clinic.controller;

import com.example.clinic.entity.DotDieuTri;
import com.example.clinic.entity.SuKienYTe;
import com.example.clinic.repository.SuKienYTeRepository;
import com.example.clinic.service.KhamChuaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Khám & Điều Trị", description = "Quy trình khám bệnh, mở đợt điều trị và lần chữa bệnh")
@CrossOrigin(origins = "*")
public class KhamChuaController {
    private final KhamChuaService khamChuaService;
    private final SuKienYTeRepository suKienYTeRepository;

    @Data
    public static class KhamRequest {
        private String maBN;
        private String maBS;
        private String maKhoa;
        private String trieuChung;
        private Double tienKham;
    }

    @Data
    public static class MoDotRequest {
        private String maSuKienKham;
        private String maBenh;
        private String mucDoNang;
        private Integer soLanChuaDuKien;
        private String maGiuong;
        private String maDotTruoc;
    }

    @Data
    public static class ChuaRequest {
        private String maBN;
        private String maBS;
        private String maDotDieuTri;
        private String hinhThucChua;
        private String ketLuan;
        private Double tienChua;
        private String maPhong;
    }

    @GetMapping("/su-kien-y-te")
    @Operation(summary = "Lấy danh sách tất cả các sự kiện y tế gần nhất")
    public ResponseEntity<List<SuKienYTe>> getAllSuKien() {
        return ResponseEntity.ok(suKienYTeRepository.findAll());
    }

    @PostMapping("/lan-kham")
    @Operation(summary = "Tiếp nhận khám bệnh ban đầu (tạo SuKienYTe, LanKham và HoaDon)")
    public ResponseEntity<SuKienYTe> tiepNhanKham(@RequestBody KhamRequest req) {
        SuKienYTe sk = khamChuaService.tiepNhanKham(req.getMaBN(), req.getMaBS(), req.getMaKhoa(), req.getTrieuChung(), req.getTienKham());
        return ResponseEntity.status(HttpStatus.CREATED).body(sk);
    }

    @PostMapping("/dot-dieu-tri")
    @Operation(summary = "Mở đợt điều trị mới (chuẩn hóa BCNF, phân bổ giường bệnh)")
    public ResponseEntity<DotDieuTri> moDotDieuTri(@RequestBody MoDotRequest req) {
        DotDieuTri dot = khamChuaService.moDotDieuTri(req.getMaSuKienKham(), req.getMaBenh(), req.getMucDoNang(), req.getSoLanChuaDuKien(), req.getMaGiuong(), req.getMaDotTruoc());
        return ResponseEntity.status(HttpStatus.CREATED).body(dot);
    }

    @GetMapping("/dot-dieu-tri")
    @Operation(summary = "Danh sách đợt điều trị (lọc theo trạng thái DangDieuTri hoặc DaKhoi)")
    public ResponseEntity<List<DotDieuTri>> getDotDieuTriList(@RequestParam(required = false) String trangThai) {
        return ResponseEntity.ok(khamChuaService.getDotDieuTriList(trangThai));
    }

    @PutMapping("/dot-dieu-tri/{maDot}/dong")
    @Operation(summary = "Kết luận đóng đợt điều trị (chuyển DaKhoi, giải phóng giường bệnh)")
    public ResponseEntity<Void> dongDotDieuTri(
            @PathVariable String maDot,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate ngayKetThuc) {
        khamChuaService.dongDotDieuTri(maDot, ngayKetThuc);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/lan-chua-benh")
    @Operation(summary = "Ghi nhận lượt chữa bệnh thuộc đợt điều trị")
    public ResponseEntity<SuKienYTe> ghiNhanChuaBenh(@RequestBody ChuaRequest req) {
        SuKienYTe sk = khamChuaService.ghiNhanChuaBenh(req.getMaBN(), req.getMaBS(), req.getMaDotDieuTri(), req.getHinhThucChua(), req.getKetLuan(), req.getTienChua(), req.getMaPhong());
        return ResponseEntity.status(HttpStatus.CREATED).body(sk);
    }

    @GetMapping("/benh-nhan/{maBN}/lich-su-kham")
    @Operation(summary = "Tra cứu lịch sử khám bệnh của bệnh nhân")
    public ResponseEntity<List<SuKienYTe>> getLichSuKham(@PathVariable String maBN) {
        return ResponseEntity.ok(khamChuaService.getLichSuKhamBenh(maBN));
    }
}
