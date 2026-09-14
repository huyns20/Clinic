package com.example.clinic.service.impl;

import com.example.clinic.dto.response.HealthResponse;
import com.example.clinic.service.HealthService;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HealthServiceImpl implements HealthService {
    private final String applicationName;

    public HealthServiceImpl(@Value("${spring.application.name}") String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public HealthResponse getHealth() {
        return new HealthResponse("UP", applicationName, Instant.now());
    }
}
