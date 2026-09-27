package com.example.clinic.service.impl;

import com.example.clinic.entity.HoaDon;
import com.example.clinic.entity.Thuoc;
import com.example.clinic.exception.BusinessException;
import com.example.clinic.exception.ResourceNotFoundException;
import com.example.clinic.repository.HoaDonRepository;
import com.example.clinic.repository.ThuocRepository;
import com.example.clinic.service.DuocPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DuocPhamServiceImpl implements DuocPhamService {
    private final ThuocRepository thuocRepository;
    private final HoaDonRepository hoaDonRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Thuoc> getAll() {
        return thuocRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Thuoc> getLowStock() {
        return thuocRepository.findLowStockMedicines();
    }

    @Override
    @Transactional(readOnly = true)
    public Thuoc getById(String maThuoc) {
        return thuocRepository.findById(maThuoc)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy thuốc có mã: " + maThuoc));
    }

    @Override
    @Transactional
    public void keDonThuoc(String maSuKien, String maThuoc, Integer soLuong) {
        Thuoc thuoc = getById(maThuoc);
        if (soLuong == null || soLuong <= 0) {
            throw new BusinessException("Số lượng thuốc kê đơn phải lớn hơn 0!");
        }
        if (thuoc.getTonKho() < soLuong) {
            throw new BusinessException("Thuốc " + thuoc.getTenThuoc() + " chỉ còn " + thuoc.getTonKho() + " " + thuoc.getDonViTinh() + ", không đủ số lượng " + soLuong + " yêu cầu!");
        }

        BigDecimal thanhTien = thuoc.getDonGia().multiply(BigDecimal.valueOf(soLuong));

        // Insert vào sudungthuoc (sẽ kích hoạt trigger trừ tồn kho nếu có trigger DB)
        jdbcTemplate.update(
                "INSERT INTO sudungthuoc (masukien, mathuoc, soluong, dongiaapdung) VALUES (?, ?, ?, ?) " +
                "ON CONFLICT (masukien, mathuoc) DO UPDATE SET soluong = sudungthuoc.soluong + EXCLUDED.soluong",
                maSuKien, maThuoc, soLuong, thuoc.getDonGia()
        );

        // Trừ tồn kho đồng bộ
        thuoc.setTonKho(thuoc.getTonKho() - soLuong);
        thuocRepository.save(thuoc);

        // Cập nhật tổng tiền hóa đơn
        hoaDonRepository.findById(maSuKien).ifPresent(hd -> {
            hd.setTongTien(hd.getTongTien().add(thanhTien));
            hoaDonRepository.save(hd);
        });
    }
}
