package com.example.clinic.service.impl;

import com.example.clinic.dto.request.PatientRequest;
import com.example.clinic.dto.response.PageResponse;
import com.example.clinic.dto.response.PatientResponse;
import com.example.clinic.entity.Patient;
import com.example.clinic.exception.BusinessException;
import com.example.clinic.exception.ResourceNotFoundException;
import com.example.clinic.mapper.PatientMapper;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    @Transactional
    public PatientResponse create(PatientRequest request) {
        return patientMapper.toResponse(patientRepository.saveAndFlush(patientMapper.toEntity(request)));
    }

    @Override
    public PatientResponse getById(Long id) {
        return patientMapper.toResponse(findPatient(id));
    }

    @Override
    public PageResponse<PatientResponse> search(String keyword, int page, int size) {
        if (page < 0 || size < 1 || size > 100) {
            throw new BusinessException("page phải >= 0; size phải từ 1 đến 100.");
        }
        String term = keyword == null ? "" : keyword.trim();
        var pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return PageResponse.from(patientRepository
                .findByFullNameContainingIgnoreCaseOrPhoneContaining(term, term, pageable)
                .map(patientMapper::toResponse));
    }

    @Override
    @Transactional
    public PatientResponse update(Long id, PatientRequest request) {
        Patient patient = findPatient(id);
        patientMapper.updateEntity(request, patient);
        return patientMapper.toResponse(patientRepository.saveAndFlush(patient));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        patientRepository.delete(findPatient(id));
        patientRepository.flush();
    }

    private Patient findPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bệnh nhân: " + id));
    }
}
