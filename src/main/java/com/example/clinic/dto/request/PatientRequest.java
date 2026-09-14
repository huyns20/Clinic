package com.example.clinic.dto.request;

import com.example.clinic.enums.Gender;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

/** Shared by POST and PUT: PUT replaces all editable fields. */
public record PatientRequest(
        @NotBlank @Size(max = 120) String fullName,
        @NotNull @PastOrPresent LocalDate dateOfBirth,
        @NotNull Gender gender,
        @NotBlank @Pattern(regexp = "[+]?[0-9]{9,15}",
                message = "Số điện thoại gồm 9–15 chữ số, có thể bắt đầu bằng +") String phone,
        @Size(max = 255) String address) {}
