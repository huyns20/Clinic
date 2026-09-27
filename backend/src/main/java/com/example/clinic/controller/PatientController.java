package com.example.clinic.controller;

import com.example.clinic.dto.request.PatientRequest;
import com.example.clinic.dto.response.PageResponse;
import com.example.clinic.dto.response.PatientResponse;
import com.example.clinic.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
@Tag(name = "Patients", description = "Quản lý bệnh nhân")
public class PatientController {
    private final PatientService patientService;

    @PostMapping
    @Operation(summary = "Thêm bệnh nhân")
    public ResponseEntity<PatientResponse> create(@Valid @RequestBody PatientRequest request) {
        PatientResponse response = patientService.create(request);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Chi tiết bệnh nhân")
    public PatientResponse getById(@PathVariable Long id) {
        return patientService.getById(id);
    }

    @GetMapping
    @Operation(summary = "Tìm theo tên hoặc điện thoại, phân trang từ 0")
    public PageResponse<PatientResponse> search(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return patientService.search(keyword, page, size);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Cập nhật toàn bộ thông tin bệnh nhân")
    public PatientResponse update(@PathVariable Long id, @Valid @RequestBody PatientRequest request) {
        return patientService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Xóa bệnh nhân")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
