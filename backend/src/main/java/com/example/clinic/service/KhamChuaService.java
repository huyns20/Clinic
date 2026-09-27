package com.example.clinic.service;

import com.example.clinic.entity.DotDieuTri;
import com.example.clinic.entity.LanChuaBenh;
import com.example.clinic.entity.LanKham;
import com.example.clinic.entity.SuKienYTe;

import java.time.LocalDate;
import java.util.List;

public interface KhamChuaService {
    SuKienYTe tiepNhanKham(String maBN, String maBS, String maKhoa, String trieuChung, Double tienKham);
    DotDieuTri moDotDieuTri(String maSuKienKham, String maBenh, String mucDoNang, Integer soLanChuaDuKien, String maGiuong, String maDotTruoc);
    SuKienYTe ghiNhanChuaBenh(String maBN, String maBS, String maDotDieuTri, String hinhThucChua, String ketLuan, Double tienChua, String maPhong);
    void dongDotDieuTri(String maDotDieuTri, LocalDate ngayKetThuc);
    List<DotDieuTri> getDotDieuTriList(String trangThai);
    List<SuKienYTe> getLichSuKhamBenh(String maBN);
}
