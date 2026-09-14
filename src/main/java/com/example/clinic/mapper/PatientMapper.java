package com.example.clinic.mapper;

import com.example.clinic.dto.request.PatientRequest;
import com.example.clinic.dto.response.PatientResponse;
import com.example.clinic.entity.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientMapper implements BaseMapper<Patient, PatientRequest, PatientResponse> {
    @Override
    public Patient toEntity(PatientRequest request) {
        Patient patient = new Patient();
        updateEntity(request, patient);
        return patient;
    }

    public void updateEntity(PatientRequest request, Patient patient) {
        patient.setFullName(request.fullName().trim());
        patient.setDateOfBirth(request.dateOfBirth());
        patient.setGender(request.gender());
        patient.setPhone(request.phone());
        patient.setAddress(request.address() == null ? null : request.address().trim());
    }

    @Override
    public PatientResponse toResponse(Patient patient) {
        return new PatientResponse(patient.getId(), patient.getFullName(), patient.getDateOfBirth(),
                patient.getGender(), patient.getPhone(), patient.getAddress(),
                patient.getCreatedAt(), patient.getUpdatedAt());
    }
}
