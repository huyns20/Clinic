package com.example.clinic.service;

import com.example.clinic.entity.BenhNhan;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BenhNhanService {
    List<BenhNhan> getAll();
    Page<BenhNhan> search(String keyword, int page, int size);
    BenhNhan getById(String maBN);
    BenhNhan create(BenhNhan benhNhan);
    BenhNhan update(String maBN, BenhNhan benhNhan);
    void delete(String maBN);
    java.util.Map<String, Object> getHoSo360(String maBN);
}
