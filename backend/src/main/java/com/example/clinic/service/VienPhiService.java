package com.example.clinic.service;

import com.example.clinic.entity.HoaDon;

import java.util.List;

public interface VienPhiService {
    List<HoaDon> getAll();
    List<HoaDon> getByStatus(String status);
    HoaDon getById(String maSuKien);
    void thanhToanHoaDon(String maSuKien);
}
