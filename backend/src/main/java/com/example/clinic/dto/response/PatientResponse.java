package com.example.clinic.dto.response;

import com.example.clinic.enums.Gender;
import java.time.Instant;
import java.time.LocalDate;

public record PatientResponse(Long id, String fullName, LocalDate dateOfBirth,
        Gender gender, String phone, String address, Instant createdAt, Instant updatedAt) {}
