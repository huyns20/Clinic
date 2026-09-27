package com.example.clinic.controller;

import com.example.clinic.entity.*;
import com.example.clinic.exception.ResourceNotFoundException;
import com.example.clinic.repository.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/master")
@RequiredArgsConstructor
@Tag(name = "Master Data CRUD", description = "Quản lý dữ liệu danh mục dùng chung (Bác sĩ, Y tá, Bệnh, Thuốc, Thiết bị, Dịch vụ, Phòng, Giường)")
@CrossOrigin(origins = "*")
public class MasterDataController {
    private final BacSyRepository bacSyRepository;
    private final YTaRepository yTaRepository;
    private final ThuocRepository thuocRepository;
    private final ThietBiRepository thietBiRepository;
    private final DichVuYTeRepository dichVuYTeRepository;
    private final PhongKhamRepository phongKhamRepository;
    private final GiuongBenhRepository giuongBenhRepository;
    private final DanhMucBenhRepository danhMucBenhRepository;
    private final NhanVienYTeRepository nhanVienYTeRepository;

    // --- 1. BÁC SĨ ---
    @GetMapping("/bac-sy")
    @Operation(summary = "Lấy danh sách tất cả bác sĩ")
    public ResponseEntity<List<BacSy>> getDoctors() {
        return ResponseEntity.ok(bacSyRepository.findAll());
    }

    @PostMapping("/bac-sy")
    @Operation(summary = "Thêm bác sĩ mới")
    public ResponseEntity<BacSy> createDoctor(@RequestBody BacSy doc) {
        if (doc.getMaBS() == null || doc.getMaBS().isBlank()) {
            doc.setMaBS("BS" + (bacSyRepository.count() + 1));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(bacSyRepository.save(doc));
    }

    @DeleteMapping("/bac-sy/{id}")
    @Operation(summary = "Xóa bác sĩ")
    public ResponseEntity<Void> deleteDoctor(@PathVariable String id) {
        bacSyRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- 2. Y TÁ ---
    @GetMapping("/y-ta")
    @Operation(summary = "Lấy danh sách tất cả y tá")
    public ResponseEntity<List<YTa>> getNurses() {
        return ResponseEntity.ok(yTaRepository.findAll());
    }

    @PostMapping("/y-ta")
    @Operation(summary = "Thêm y tá mới")
    public ResponseEntity<YTa> createNurse(@RequestBody YTa nurse) {
        if (nurse.getMaYT() == null || nurse.getMaYT().isBlank()) {
            nurse.setMaYT("YT" + (yTaRepository.count() + 1));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(yTaRepository.save(nurse));
    }

    @DeleteMapping("/y-ta/{id}")
    @Operation(summary = "Xóa y tá")
    public ResponseEntity<Void> deleteNurse(@PathVariable String id) {
        yTaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- 3. DANH MỤC BỆNH ---
    @GetMapping("/danh-muc-benh")
    @Operation(summary = "Lấy danh mục bệnh")
    public ResponseEntity<List<DanhMucBenh>> getDiseases() {
        return ResponseEntity.ok(danhMucBenhRepository.findAll());
    }

    @PostMapping("/danh-muc-benh")
    @Operation(summary = "Thêm mã bệnh mới")
    public ResponseEntity<DanhMucBenh> createDisease(@RequestBody DanhMucBenh disease) {
        if (disease.getMaBenh() == null || disease.getMaBenh().isBlank()) {
            disease.setMaBenh("B" + (danhMucBenhRepository.count() + 1));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(danhMucBenhRepository.save(disease));
    }

    @DeleteMapping("/danh-muc-benh/{id}")
    @Operation(summary = "Xóa mã bệnh")
    public ResponseEntity<Void> deleteDisease(@PathVariable String id) {
        danhMucBenhRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- 4. THIẾT BỊ Y TẾ ---
    @GetMapping("/thiet-bi")
    @Operation(summary = "Lấy danh sách thiết bị y tế")
    public ResponseEntity<List<ThietBi>> getDevices() {
        return ResponseEntity.ok(thietBiRepository.findAll());
    }

    @PostMapping("/thiet-bi")
    @Operation(summary = "Thêm thiết bị y tế")
    public ResponseEntity<ThietBi> createDevice(@RequestBody ThietBi device) {
        if (device.getMaThietBi() == null || device.getMaThietBi().isBlank()) {
            device.setMaThietBi("TB" + (thietBiRepository.count() + 1));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(thietBiRepository.save(device));
    }

    @DeleteMapping("/thiet-bi/{id}")
    @Operation(summary = "Xóa thiết bị")
    public ResponseEntity<Void> deleteDevice(@PathVariable String id) {
        thietBiRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- 5. DỊCH VỤ Y TẾ ---
    @GetMapping("/dich-vu")
    @Operation(summary = "Lấy danh mục dịch vụ y tế")
    public ResponseEntity<List<DichVuYTe>> getServices() {
        return ResponseEntity.ok(dichVuYTeRepository.findAll());
    }

    @PostMapping("/dich-vu")
    @Operation(summary = "Thêm dịch vụ y tế mới")
    public ResponseEntity<DichVuYTe> createService(@RequestBody DichVuYTe svc) {
        if (svc.getMaDV() == null || svc.getMaDV().isBlank()) {
            svc.setMaDV("DV" + (dichVuYTeRepository.count() + 1));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(dichVuYTeRepository.save(svc));
    }

    @DeleteMapping("/dich-vu/{id}")
    @Operation(summary = "Xóa dịch vụ y tế")
    public ResponseEntity<Void> deleteService(@PathVariable String id) {
        dichVuYTeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- 6. PHÒNG KHÁM ---
    @GetMapping("/phong-kham")
    @Operation(summary = "Lấy danh sách phòng khám")
    public ResponseEntity<List<PhongKham>> getRooms() {
        return ResponseEntity.ok(phongKhamRepository.findAll());
    }

    @PostMapping("/phong-kham")
    @Operation(summary = "Thêm phòng khám mới")
    public ResponseEntity<PhongKham> createRoom(@RequestBody PhongKham room) {
        if (room.getMaPhong() == null || room.getMaPhong().isBlank()) {
            room.setMaPhong("P" + (phongKhamRepository.count() + 101));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(phongKhamRepository.save(room));
    }

    @DeleteMapping("/phong-kham/{id}")
    @Operation(summary = "Xóa phòng khám")
    public ResponseEntity<Void> deleteRoom(@PathVariable String id) {
        phongKhamRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // --- 7. GIƯỜNG BỆNH ---
    @GetMapping("/giuong-benh")
    @Operation(summary = "Lấy danh sách giường bệnh")
    public ResponseEntity<List<GiuongBenh>> getBeds() {
        return ResponseEntity.ok(giuongBenhRepository.findAll());
    }

    @PostMapping("/giuong-benh")
    @Operation(summary = "Thêm giường bệnh mới")
    public ResponseEntity<GiuongBenh> createBed(@RequestBody GiuongBenh bed) {
        if (bed.getMaGiuong() == null || bed.getMaGiuong().isBlank()) {
            bed.setMaGiuong("G" + (giuongBenhRepository.count() + 1));
        }
        if (bed.getTrangThai() == null) bed.setTrangThai("Trong");
        return ResponseEntity.status(HttpStatus.CREATED).body(giuongBenhRepository.save(bed));
    }

    @DeleteMapping("/giuong-benh/{id}")
    @Operation(summary = "Xóa giường bệnh")
    public ResponseEntity<Void> deleteBed(@PathVariable String id) {
        giuongBenhRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
