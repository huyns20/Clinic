# RESTful API Design & Documentation Skill

## 1. Tiêu chuẩn Thiết kế API cho Phòng Khám
Hệ thống API phục vụ toàn bộ các chức năng tiếp nhận, khám chữa bệnh, kê đơn thuốc, quản lý phòng giường, hóa đơn và thống kê:

### Nhóm 1: Quản lý Bệnh nhân & Tiếp nhận
- `GET /api/v1/benh-nhan` - Lấy danh sách bệnh nhân (tìm kiếm tên, CCCD, SĐT, phân trang).
- `POST /api/v1/benh-nhan` - Tiếp nhận bệnh nhân mới.
- `GET /api/v1/benh-nhan/{id}` - Chi tiết hồ sơ bệnh nhân.
- `PUT /api/v1/benh-nhan/{id}` - Cập nhật thông tin bệnh nhân.
- `GET /api/v1/benh-nhan/{id}/lich-su` - Lịch sử các lần khám và đợt điều trị.

### Nhóm 2: Sự kiện Y tế (Khám bệnh & Chữa bệnh)
- `POST /api/v1/lan-kham` - Đăng ký khám ban đầu (tạo sự kiện khám, triệu chứng, chỉ định bác sĩ, khoa).
- `POST /api/v1/lan-chua-benh` - Ghi nhận lượt điều trị/chữa bệnh trong đợt điều trị.
- `GET /api/v1/su-kien-y-te/{id}` - Xem chi tiết sự kiện y tế (kèm thuốc, vật tư, dịch vụ đã dùng).

### Nhóm 3: Đợt điều trị (Treatment Course - BCNF Normalized)
- `POST /api/v1/dot-dieu-tri` - Khởi tạo đợt điều trị mới (từ lần khám, chọn mã bệnh, giường, mức độ).
- `GET /api/v1/dot-dieu-tri` - Danh sách đợt điều trị (lọc: đang điều trị / đã khỏi / bệnh nhân).
- `PUT /api/v1/dot-dieu-tri/{id}/dong` - Đóng đợt điều trị (ghi nhận ngày kết thúc, giải phóng giường).
- `POST /api/v1/dot-dieu-tri/tai-phat` - Tạo đợt điều trị tái phát (liên kết đệ quy `MaDotTruoc`).

### Nhóm 4: Kho Dược & Dịch vụ Y tế
- `GET /api/v1/thuoc` - Tra cứu danh mục thuốc và số lượng tồn kho khả dụng.
- `POST /api/v1/su-kien-y-te/{id}/ke-don` - Kê đơn thuốc cho sự kiện y tế.
- `POST /api/v1/su-kien-y-te/{id}/chi-dinh-dich-vu` - Chỉ định dịch vụ / thiết bị y tế.

### Nhóm 5: Cơ sở vật chất & Giường bệnh
- `GET /api/v1/phong-kham` - Danh sách phòng khám theo khoa chuyên môn.
- `GET /api/v1/giuong-benh/trong` - Tra cứu danh sách giường bệnh còn trống để phân bổ.

### Nhóm 6: Tài chính & Hóa đơn
- `GET /api/v1/hoa-don/{maSuKien}` - Chi tiết hóa đơn và các dòng khoản mục tự động tổng hợp.
- `PUT /api/v1/hoa-don/{maSuKien}/thanh-toan` - Xác nhận thanh toán hóa đơn.
- `GET /api/v1/thong-ke/doanh-thu` - Báo cáo doanh thu theo ngày / theo khoa / theo tháng.
- `GET /api/v1/thong-ke/benh-ly` - Thống kê tần suất mắc bệnh trong danh mục.
- `GET /api/v1/thong-ke/luong-nhan-vien` - Báo cáo bảng lương nhân sự y tế.

## 2. Chuẩn OpenAPI / Swagger 3 Integration
- Cung cấp Swagger UI tại: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON spec tại: `http://localhost:8080/v3/api-docs`
- Đầy đủ `@Tag`, `@Operation`, `@ApiResponse` rõ nghĩa bằng Tiếng Việt.
