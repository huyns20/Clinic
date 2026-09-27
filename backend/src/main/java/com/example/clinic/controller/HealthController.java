package com.example.clinic.controller;

import com.example.clinic.dto.response.HealthResponse;
import com.example.clinic.service.HealthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
@RequiredArgsConstructor
@Tag(name = "System", description = "Kiểm tra ứng dụng")
public class HealthController {
    private final HealthService healthService;

    @GetMapping
    @Operation(summary = "Kiểm tra ứng dụng đang chạy (liveness)")
    public HealthResponse health() {
        return healthService.getHealth();
    }
}
