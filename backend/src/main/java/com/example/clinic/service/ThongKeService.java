package com.example.clinic.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ThongKeService {
    Map<String, Object> getTongQuanDashboard();
    Map<String, BigDecimal> getDoanhThuTheoKhoa();

    // Báo cáo 2.1: Thống kê bệnh theo tháng (sắp xếp giảm dần theo số bệnh nhân/ca mắc, tính tái phát)
    List<Map<String, Object>> getThongKeBenhTheoThang(String thang);

    // Báo cáo 2.2: Doanh thu chi tiết theo 5 nguồn thu
    Map<String, BigDecimal> getDoanhThuChiTiet(String thang);

    // Báo cáo 3: Bảng lương Bác sĩ & Y tá theo công thức (1tr/ca khỏi, 200k/lượt hỗ trợ y tá)
    List<Map<String, Object>> getBangLuongChiTiet(String thang);
}
