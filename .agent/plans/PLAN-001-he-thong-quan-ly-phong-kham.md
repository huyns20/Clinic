# Plan 001: Kế Hoạch Tổng Thể Phát Triển Hệ Thống Quản Lý Phòng Khám Tư Nhân (Đề Tài 4)

- **Plan ID**: PLAN-001
- **Created Date**: 2026-09-27
- **Author**: Lead Software Engineer Agent
- **Status**: APPROVED
- **Linked Thinking**: [THINKING-001-kien-truc-va-phan-tich-nghiep-vu-de-tai-4.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/thinking/THINKING-001-kien-truc-va-phan-tich-nghiep-vu-de-tai-4.md)
- **Linked Tasks**: [TASK-TRACKER.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/tasks/TASK-TRACKER.md)
- **Target Database**: PostgreSQL `phong_kham2` (docker-compose: port 5432, user `postgres`, pass `123456`)

---

## 1. Mục tiêu (Objective)
Xây dựng trọn vẹn ứng dụng Web Quản lý Phòng khám bệnh tư nhân đáp ứng 100% yêu cầu chức năng trong Đề tài 4 (STT 16 đến 20 + 37), liên kết chính xác với cơ sở dữ liệu quan hệ PostgreSQL 24 bảng đã chuẩn hóa BCNF.

## 2. Phạm vi Công việc (Scope of Work)

### 2.1. Tái Cấu Trúc Mã Nguồn (Refactoring)
- Thư mục root `Clinic/`:
  - `backend/`: Java 17 + Spring Boot 3 + Spring Data JPA + Lombok + OpenAPI Swagger UI.
  - `frontend/`: Single Page Application hiện đại sử dụng React + Vite + Tailwind CSS + Lucide Icons.
  - `requirements/`: Đề cương BTL, Báo cáo Word/Markdown, các script SQL 01_schema và 02_sample_data.
  - `docs/`: Hướng dẫn cài đặt, chạy hệ thống, kiến trúc.
  - `.agent/`: Knowledge Base (skills, plans, thinking, tasks, context, workflows).

### 2.2. Backend Development (Spring Boot REST API)
- **Cấu hình DB**: Kết nối `jdbc:postgresql://localhost:5432/phong_kham2`, cấu hình CORS cho Vite (`http://localhost:5173`).
- **Entity Model**: Ánh xạ đầy đủ các bảng:
  - Danh mục: `Khoa`, `BenhNhan`, `DanhMucBenh`, `Thuoc`, `ThietBi`, `DichVuYTe`, `LoaiNhanCong`, `ThamSoHeThong`.
  - Nhân sự: `NhanVienYTe`, `BacSy`, `YTa`, `Luong`.
  - Cơ sở vật chất: `PhongKham`, `GiuongBenh`.
  - Khám & Điều trị: `SuKienYTe`, `LanKham`, `DotDieuTri`, `LanChuaBenh`.
  - Tài nguyên & Viện phí: `SuDungThuoc`, `SuDungThietBi`, `SuDungDichVu`, `SuDungNhanCong`, `HoaDon`, `HoaDonChiTiet`.
- **Business Services**:
  - `PatientService`: Tìm kiếm, thêm mới, xem lịch sử khám và đợt điều trị.
  - `MedicalEventService`: Tiếp nhận khám, phân bổ bác sĩ, ghi nhận triệu chứng.
  - `TreatmentService`: Khởi tạo đợt điều trị, phân giường bệnh, ghi nhận các lần chữa bệnh, đóng đợt, xử lý tái phát đệ quy (`MaDotTruoc`).
  - `PharmacyService`: Tra cứu thuốc, tồn kho, kê đơn (kích hoạt trigger trừ tồn kho).
  - `BillingService`: Xem chi tiết hóa đơn tự động sinh, xác nhận thanh toán.
  - `ReportService`: Thống kê doanh thu theo khoa/ngày, thống kê bệnh lý, bảng lương nhân viên.

### 2.3. Frontend Development (React + Vite + Tailwind CSS)
- **Dashboard Tổng quan**: Thống kê bệnh nhân, đợt điều trị, công suất giường, doanh thu.
- **Tiếp nhận & Bệnh nhân**: Tìm kiếm nhanh qua CCCD/Tên/SĐT, form đăng ký mới, danh sách lịch sử.
- **Khám bệnh & Chỉ định**: Bác sĩ ghi nhận triệu chứng, kê đơn thuốc (có kiểm tra tồn kho realtime), chỉ định dịch vụ.
- **Quản lý Đợt điều trị & Giường bệnh**: Sơ đồ giường bệnh trực quan, theo dõi đợt điều trị đang mở, đóng đợt khi khỏi.
- **Kho Dược & Vật tư**: Danh mục thuốc, đơn vị tính, đơn giá, số lượng tồn khả dụng, cảnh báo hết thuốc.
- **Thu ngân & Hóa đơn**: Xem bảng kê chi tiết viện phí (tiền khám + tiền chữa + tiền phòng + tiền thuốc + dịch vụ), nút xác nhận thanh toán.
- **Báo cáo & Thống kê**: Biểu đồ trực quan doanh thu, tần suất bệnh tật, bảng lương y bác sĩ.

### 2.4. Kiểm thử Tự động (Automated Testing Workflow)
- Thiết lập kịch bản test API với Node.js runner (`workflows/test-runner.js`).
- Tự động kiểm tra tính đúng đắn của các luồng nghiệp vụ chính và toàn vẹn dữ liệu.

---

## 3. Lịch trình Thực hiện (Execution Timeline)
- **Phase 1**: Khởi tạo cấu trúc `.agent` hoàn chỉnh (skills, plans, thinking, tasks, context, workflows). -> *[COMPLETED]*
- **Phase 2**: Cấu hình backend kết nối PostgreSQL `phong_kham2`, hoàn thiện Entity, Service, Controller, Swagger UI. -> *[IN PROGRESS]*
- **Phase 3**: Khởi tạo và xây dựng Frontend UI React + Vite + Tailwind CSS.
- **Phase 4**: Thiết lập Workflow Auto Test và chạy kiểm thử tự động.
- **Phase 5**: Tổng kết, kiểm tra toàn diện và bàn giao.
