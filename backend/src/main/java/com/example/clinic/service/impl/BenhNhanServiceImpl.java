package com.example.clinic.service.impl;

import com.example.clinic.entity.BenhNhan;
import com.example.clinic.exception.BusinessException;
import com.example.clinic.exception.ResourceNotFoundException;
import com.example.clinic.repository.BenhNhanRepository;
import com.example.clinic.service.BenhNhanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BenhNhanServiceImpl implements BenhNhanService {
    private final BenhNhanRepository benhNhanRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BenhNhan> getAll() {
        return benhNhanRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BenhNhan> search(String keyword, int page, int size) {
        return benhNhanRepository.search(keyword, PageRequest.of(page, size));
    }

    @Override
    @Transactional(readOnly = true)
    public BenhNhan getById(String maBN) {
        return benhNhanRepository.findById(maBN)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bệnh nhân có mã: " + maBN));
    }

    @Override
    @Transactional
    public BenhNhan create(BenhNhan benhNhan) {
        if (benhNhan.getSoCCCD() != null && !benhNhan.getSoCCCD().isBlank()) {
            benhNhanRepository.findBySoCCCD(benhNhan.getSoCCCD()).ifPresent(p -> {
                throw new BusinessException("Số CCCD " + benhNhan.getSoCCCD() + " đã tồn tại trong hệ thống!");
            });
        }
        if (benhNhan.getMaBN() == null || benhNhan.getMaBN().isBlank()) {
            long count = benhNhanRepository.count() + 1;
            benhNhan.setMaBN(String.format("BN%03d", count));
        }
        if (benhNhan.getNgayDangKy() == null) {
            benhNhan.setNgayDangKy(LocalDate.now());
        }
        return benhNhanRepository.save(benhNhan);
    }

    @Override
    @Transactional
    public BenhNhan update(String maBN, BenhNhan benhNhan) {
        BenhNhan existing = getById(maBN);
        existing.setHoTen(benhNhan.getHoTen());
        existing.setGioiTinh(benhNhan.getGioiTinh());
        existing.setNgaySinh(benhNhan.getNgaySinh());
        existing.setSdt(benhNhan.getSdt());
        existing.setDiaChi(benhNhan.getDiaChi());
        existing.setSoCCCD(benhNhan.getSoCCCD());
        return benhNhanRepository.save(existing);
    }

    @Override
    @Transactional
    public void delete(String maBN) {
        BenhNhan existing = getById(maBN);
        benhNhanRepository.delete(existing);
    }

    private final com.example.clinic.repository.SuKienYTeRepository suKienYTeRepository;
    private final com.example.clinic.repository.LanKhamRepository lanKhamRepository;
    private final com.example.clinic.repository.DotDieuTriRepository dotDieuTriRepository;
    private final com.example.clinic.repository.LanChuaBenhRepository lanChuaBenhRepository;
    private final com.example.clinic.repository.HoaDonRepository hoaDonRepository;
    private final com.example.clinic.repository.HoaDonChiTietRepository hoaDonChiTietRepository;
    private final com.example.clinic.repository.SuDungThuocRepository suDungThuocRepository;
    private final com.example.clinic.repository.DanhMucBenhRepository danhMucBenhRepository;

    @Override
    @Transactional(readOnly = true)
    public java.util.Map<String, Object> getHoSo360(String maBN) {
        BenhNhan bn = getById(maBN);
        java.util.Map<String, Object> res = new java.util.HashMap<>();
        res.put("benhNhan", bn);

        // 1. Lấy tất cả sự kiện y tế của bệnh nhân
        List<com.example.clinic.entity.SuKienYTe> events = suKienYTeRepository.findByMaBNOrderByThoiGianDesc(maBN);
        
        // 2. Xác định các bệnh hiện tại đang điều trị
        List<java.util.Map<String, Object>> benhHienTai = new java.util.ArrayList<>();
        List<com.example.clinic.entity.DotDieuTri> allDots = dotDieuTriRepository.findAll();
        for (com.example.clinic.entity.DotDieuTri dot : allDots) {
            // Kiểm tra xem đợt điều trị này có thuộc về bệnh nhân không qua mã sự kiện khám
            suKienYTeRepository.findById(dot.getMaSuKienKham()).ifPresent(skKham -> {
                if (maBN.equalsIgnoreCase(skKham.getMaBN()) && "DangDieuTri".equalsIgnoreCase(dot.getTrangThai())) {
                    java.util.Map<String, Object> item = new java.util.HashMap<>();
                    item.put("maDotDieuTri", dot.getMaDotDieuTri());
                    item.put("maBenh", dot.getMaBenh());
                    danhMucBenhRepository.findById(dot.getMaBenh()).ifPresent(b -> item.put("tenBenh", b.getTenBenh()));
                    item.put("mucDoNang", dot.getMucDoNang());
                    item.put("maGiuong", dot.getMaGiuong());
                    item.put("ngayBatDau", dot.getNgayBatDau());
                    // Đếm số lần chữa bệnh cho bệnh này
                    int soLanChua = lanChuaBenhRepository.findByMaDotDieuTri(dot.getMaDotDieuTri()).size();
                    item.put("soLanKhamChuaHienTai", soLanChua + 1); // 1 lần khám + các lần chữa
                    item.put("bacSyPhuTrach", skKham.getMaBS());
                    benhHienTai.add(item);
                }
            });
        }
        res.put("benhHienTai", benhHienTai);

        // 3. Chi tiết lịch sử từng lần khám / chữa bệnh kèm chi phí chi tiết
        List<java.util.Map<String, Object>> lichSuChiTiet = new java.util.ArrayList<>();
        for (com.example.clinic.entity.SuKienYTe sk : events) {
            java.util.Map<String, Object> skDetail = new java.util.HashMap<>();
            skDetail.put("maSuKien", sk.getMaSuKien());
            skDetail.put("thoiGian", sk.getThoiGian());
            skDetail.put("loaiSuKien", sk.getLoaiSuKien());
            skDetail.put("maBS", sk.getMaBS());

            // Khoản mục chi phí
            List<com.example.clinic.entity.HoaDonChiTiet> chiTietList = hoaDonChiTietRepository.findByMaSuKienOrderBySoDongAsc(sk.getMaSuKien());
            skDetail.put("cacKhoanChiPhi", chiTietList);

            // Thuốc đã sử dụng
            List<com.example.clinic.entity.SuDungThuoc> thuocList = suDungThuocRepository.findByMaSuKien(sk.getMaSuKien());
            skDetail.put("thuocSuDung", thuocList);

            // Tổng hóa đơn
            hoaDonRepository.findById(sk.getMaSuKien()).ifPresent(hd -> {
                skDetail.put("tongTien", hd.getTongTien());
                skDetail.put("trangThaiTT", hd.getTrangThaiTT());
            });

            lichSuChiTiet.add(skDetail);
        }
        res.put("lichSuChiTiet", lichSuChiTiet);

        return res;
    }
}
