package com.example.clinic.repository;

import com.example.clinic.entity.Department;
import java.util.Optional;

public interface DepartmentRepository extends BaseRepository<Department> {
    Optional<Department> findByCode(String code);
}
