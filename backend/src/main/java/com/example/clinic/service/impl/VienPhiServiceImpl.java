package com.example.clinic.service.impl;

import com.example.clinic.entity.HoaDon;
import com.example.clinic.exception.ResourceNotFoundException;
import com.example.clinic.repository.HoaDonRepository;
import com.example.clinic.service.VienPhiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VienPhiServiceImpl implements VienPhiService {
    private final HoaDonRepository hoaDonRepository;

    @Override
    @Transactional(readOnly = true)
    public List<HoaDon> getAll() {
        return hoaDonRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<HoaDon> getByStatus(String status) {
        return hoaDonRepository.findByTrangThaiTT(status);
    }

    @Override
    @Transactional(readOnly = true)
    public HoaDon getById(String maSuKien) {
        return hoaDonRepository.findById(maSuKien)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy hóa đơn của sự kiện: " + maSuKien));
    }

    @Override
    @Transactional
    public void thanhToanHoaDon(String maSuKien) {
        HoaDon hd = getById(maSuKien);
        hd.setTrangThaiTT("DaThanhToan");
        hoaDonRepository.save(hd);
    }
}
