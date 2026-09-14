package com.example.clinic.repository;

import com.example.clinic.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientRepository extends BaseRepository<Patient> {
    Page<Patient> findByFullNameContainingIgnoreCaseOrPhoneContaining(
            String fullName, String phone, Pageable pageable);
}
