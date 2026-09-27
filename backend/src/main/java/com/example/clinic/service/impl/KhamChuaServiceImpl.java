package com.example.clinic.service.impl;

import com.example.clinic.entity.*;
import com.example.clinic.exception.BusinessException;
import com.example.clinic.exception.ResourceNotFoundException;
import com.example.clinic.repository.*;
import com.example.clinic.service.KhamChuaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KhamChuaServiceImpl implements KhamChuaService {
    private final SuKienYTeRepository suKienYTeRepository;
    private final LanKhamRepository lanKhamRepository;
    private final DotDieuTriRepository dotDieuTriRepository;
    private final LanChuaBenhRepository lanChuaBenhRepository;
    private final GiuongBenhRepository giuongBenhRepository;
    private final HoaDonRepository hoaDonRepository;

    @Override
    @Transactional
    public SuKienYTe tiepNhanKham(String maBN, String maBS, String maKhoa, String trieuChung, Double tienKham) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String maSuKien = String.format("%s-%s-K-%s", maKhoa, maBS, timestamp);

        BigDecimal fee = BigDecimal.valueOf(tienKham != null ? tienKham : 150000.0);

        SuKienYTe sk = SuKienYTe.builder()
                .maSuKien(maSuKien)
                .maBN(maBN)
                .maBS(maBS)
                .thoiGian(LocalDateTime.now())
                .loaiSuKien("KHAM")
                .build();
        suKienYTeRepository.save(sk);

        LanKham lk = LanKham.builder()
                .maSuKien(maSuKien)
                .maKhoa(maKhoa)
                .trieuChung(trieuChung)
                .tienKham(fee)
                .build();
        lanKhamRepository.save(lk);

        HoaDon hd = HoaDon.builder()
                .maSuKien(maSuKien)
                .ngayLap(LocalDateTime.now())
                .tongTien(fee)
                .trangThaiTT("ChuaThanhToan")
                .build();
        hoaDonRepository.save(hd);

        return sk;
    }

    @Override
    @Transactional
    public DotDieuTri moDotDieuTri(String maSuKienKham, String maBenh, String mucDoNang, Integer soLanChuaDuKien, String maGiuong, String maDotTruoc) {
        String maDot = "DOT-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        if (maGiuong != null && !maGiuong.isBlank()) {
            GiuongBenh giuong = giuongBenhRepository.findById(maGiuong)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy giường: " + maGiuong));
            if ("CoNguoi".equalsIgnoreCase(giuong.getTrangThai())) {
                throw new BusinessException("Giường " + maGiuong + " hiện đang có người nằm! Vui lòng chọn giường khác.");
            }
            giuong.setTrangThai("CoNguoi");
            giuongBenhRepository.save(giuong);
        }

        DotDieuTri dot = DotDieuTri.builder()
                .maDotDieuTri(maDot)
                .maSuKienKham(maSuKienKham)
                .maBenh(maBenh)
                .mucDoNang(mucDoNang != null ? mucDoNang : "Nhe")
                .soLanChuaDuKien(soLanChuaDuKien != null ? soLanChuaDuKien : 5)
                .ngayBatDau(LocalDate.now())
                .trangThai("DangDieuTri")
                .maGiuong(maGiuong)
                .maDotTruoc(maDotTruoc)
                .build();

        return dotDieuTriRepository.save(dot);
    }

    @Override
    @Transactional
    public SuKienYTe ghiNhanChuaBenh(String maBN, String maBS, String maDotDieuTri, String hinhThucChua, String ketLuan, Double tienChua, String maPhong) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String maSuKien = String.format("CHUA-%s-%s", maDotDieuTri, timestamp);

        BigDecimal fee = BigDecimal.valueOf(tienChua != null ? tienChua : 200000.0);

        SuKienYTe sk = SuKienYTe.builder()
                .maSuKien(maSuKien)
                .maBN(maBN)
                .maBS(maBS)
                .thoiGian(LocalDateTime.now())
                .loaiSuKien("CHUA")
                .build();
        suKienYTeRepository.save(sk);

        LanChuaBenh lcb = LanChuaBenh.builder()
                .maSuKien(maSuKien)
                .maDotDieuTri(maDotDieuTri)
                .hinhThucChua(hinhThucChua != null ? hinhThucChua : "Vật lý trị liệu")
                .ketLuan(ketLuan)
                .tienChua(fee)
                .maPhong(maPhong)
                .build();
        lanChuaBenhRepository.save(lcb);

        HoaDon hd = HoaDon.builder()
                .maSuKien(maSuKien)
                .ngayLap(LocalDateTime.now())
                .tongTien(fee)
                .trangThaiTT("ChuaThanhToan")
                .build();
        hoaDonRepository.save(hd);

        return sk;
    }

    @Override
    @Transactional
    public void dongDotDieuTri(String maDotDieuTri, LocalDate ngayKetThuc) {
        DotDieuTri dot = dotDieuTriRepository.findById(maDotDieuTri)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đợt điều trị: " + maDotDieuTri));
        dot.setTrangThai("DaKhoi");
        dot.setNgayKetThuc(ngayKetThuc != null ? ngayKetThuc : LocalDate.now());
        dotDieuTriRepository.save(dot);

        // Giải phóng giường bệnh nếu có
        if (dot.getMaGiuong() != null && !dot.getMaGiuong().isBlank()) {
            giuongBenhRepository.findById(dot.getMaGiuong()).ifPresent(g -> {
                g.setTrangThai("Trong");
                giuongBenhRepository.save(g);
            });
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DotDieuTri> getDotDieuTriList(String trangThai) {
        if (trangThai != null && !trangThai.isBlank()) {
            return dotDieuTriRepository.findByTrangThai(trangThai);
        }
        return dotDieuTriRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SuKienYTe> getLichSuKhamBenh(String maBN) {
        return suKienYTeRepository.findByMaBNOrderByThoiGianDesc(maBN);
    }
}
