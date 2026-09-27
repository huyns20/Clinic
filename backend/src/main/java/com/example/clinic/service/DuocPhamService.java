package com.example.clinic.service;

import com.example.clinic.entity.Thuoc;

import java.util.List;

public interface DuocPhamService {
    List<Thuoc> getAll();
    List<Thuoc> getLowStock();
    Thuoc getById(String maThuoc);
    void keDonThuoc(String maSuKien, String maThuoc, Integer soLuong);
}
