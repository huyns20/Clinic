package com.example.clinic.repository;

import com.example.clinic.entity.Doctor;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DoctorRepository extends BaseRepository<Doctor> {
    Optional<Doctor> findByCode(String code);
    Page<Doctor> findByDepartmentId(Long departmentId, Pageable pageable);
}
