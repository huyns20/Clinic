package com.example.clinic.service;

import com.example.clinic.dto.request.PatientRequest;
import com.example.clinic.dto.response.PageResponse;
import com.example.clinic.dto.response.PatientResponse;

public interface PatientService {
    PatientResponse create(PatientRequest request);
    PatientResponse getById(Long id);
    PageResponse<PatientResponse> search(String keyword, int page, int size);
    PatientResponse update(Long id, PatientRequest request);
    void delete(Long id);
}
