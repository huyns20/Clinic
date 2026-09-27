# Plan 004: Hoàn Thiện Master Data CRUD, Hồ Sơ Bệnh Nhân Chi Tiết & Báo Cáo Nâng Cao Theo funtion.txt

- **Plan ID**: PLAN-004
- **Created Date**: 2026-09-27
- **Author**: Lead Software Architect Agent
- **Status**: APPROVED
- **Linked Thinking**: [THINKING-004-nghiep-vu-funtion-txt-va-thuat-toan-tinh-luong-benh-ly.md](file:///Clinic/.agent/thinking/THINKING-004-nghiep-vu-funtion-txt-va-thuat-toan-tinh-luong-benh-ly.md)
- **Linked Tasks**: [TASK-TRACKER.md](file:///Clinic/.agent/tasks/TASK-TRACKER.md)
- **Tham chiếu**: `funtion.txt`, `doc.txt`, `DeCuongBTL (1).pdf`, `BaoCao_DeTai4_PhongKham.docx.md`

---

## 1. Mục Tiêu (Objective)
Bổ sung đầy đủ 100% các tính năng theo tài liệu đặc tả `funtion.txt`:
1. **CRUD Master Data toàn diện**: Bác sĩ, Y tá, Danh mục bệnh, Thuốc, Thiết bị y tế, Dịch vụ y tế, Phòng khám, Giường bệnh.
2. **Chi Tiết Bệnh Nhân 360 độ (Patient 360 Detail)**:
   - Thông tin cá nhân & tiền sử.
   - Bệnh hiện tại đang mắc, số lần đã khám/chữa cho từng bệnh trong đợt điều trị.
   - Toàn bộ lịch sử khám chữa bệnh kèm chi tiết chi phí từng khoản (khám, chữa, thuốc, dịch vụ, thiết bị, nhân công).
3. **Báo cáo 2.1: Thống kê bệnh lý theo tháng**:
   - Sắp xếp giảm dần theo số ca mắc.
   - Tính 1 lần cho chuỗi khám/chữa liên tiếp (cùng 1 đợt điều trị).
   - Tính nhiều lần nếu bệnh nhân tái phát bệnh (đợt điều trị tái phát `MaDotTruoc`).
4. **Báo cáo 2.2: Doanh thu phòng khám chi tiết theo nguồn thu**:
   - Phân rã: Tiền khám, tiền chữa, tiền thuốc, tiền dịch vụ, tiền cơ sở vật chất/giường bệnh.
5. **Báo cáo 3: Bảng tính lương Bác sĩ & Y tá theo đúng quy tắc**:
   - Bác sĩ: Lương cơ bản * Hệ số + (Số ca chữa khỏi bệnh * 1.000.000 VNĐ).
   - Y tá: Lương cơ bản * Hệ số + (Số lần hỗ trợ khám/chữa * 200.000 VNĐ).

---

## 2. Kế Hoạch Triển Khai (Implementation Steps)

### Phần A: Backend Spring Boot
- **Entities & Repositories**:
  - `ThietBi.java` & `ThietBiRepository.java`
  - `DichVuYTe.java` & `DichVuYTeRepository.java`
  - `YTa.java` & `YTaRepository.java`
- **Master Data Controller & Services**:
  - CRUD Bác sĩ (`/api/v1/master/bac-sy`)
  - CRUD Y tá (`/api/v1/master/y-ta`)
  - CRUD Thuốc (`/api/v1/master/thuoc`)
  - CRUD Thiết bị (`/api/v1/master/thiet-bi`)
  - CRUD Dịch vụ (`/api/v1/master/dich-vu`)
  - CRUD Phòng khám (`/api/v1/master/phong-kham`)
  - CRUD Giường bệnh (`/api/v1/master/giuong-benh`)
  - CRUD Danh mục bệnh (`/api/v1/master/danh-muc-benh`)
- **Patient Detail Controller**:
  - `/api/v1/benh-nhan/{maBN}/chi-tiet-toan-dien`: Trả về hồ sơ bệnh án 360 độ gồm các bệnh hiện đang mắc, số lần chữa trong đợt, và chi tiết từng hóa đơn/sự kiện kèm tiền khám, tiền thuốc, tiền dịch vụ.
- **Advanced Reports Service & Controller**:
  - `/api/v1/thong-ke/benh-theo-thang`: Nhận `thang` (YYYY-MM), tính số đợt điều trị phát sinh trong tháng, kiểm tra tái phát.
  - `/api/v1/thong-ke/doanh-thu-chi-tiet`: Nhận `thang` hoặc khoảng thời gian, phân rã theo 5 nguồn thu.
  - `/api/v1/thong-ke/bang-luong-chi-tiet`: Nhận `thang`, áp dụng công thức 1 triệu/ca khỏi và 200k/lượt hỗ trợ y tá.

### Phần B: Frontend UI
- Thêm Tab **Quản Lý Master Data** cho phép quản trị viên xem/thêm/sửa/xóa 8 danh mục dùng chung.
- Trong Tab **Tiếp Nhận & Bệnh Nhân**: Bấm vào bất kỳ bệnh nhân nào sẽ mở **Modal Hồ Sơ Bệnh Nhân 360 độ** hiển thị các bệnh đang mắc, số lần chữa, timeline khám chữa bệnh và chi tiết chi phí từng lần.
- Trong Tab **Báo Cáo Thống Kê**:
  - Bổ sung bộ lọc Tháng/Năm.
  - Hiển thị Bảng Top Bệnh Lý Mắc Phải (đếm theo đợt điều trị và đợt tái phát).
  - Biểu đồ phân rã Doanh Thu theo 5 nguồn thu (khám, chữa, thuốc, dịch vụ, giường).
  - Bảng lương chi tiết Bác sĩ & Y tá theo công thức thưởng hiệu suất thực tế.
