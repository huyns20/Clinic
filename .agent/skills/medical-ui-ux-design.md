# Medical UI/UX Design & Dashboard Patterns Skill

## 1. Hệ thống Màu sắc Y tế (Medical Clinical Palette)
Giao diện ứng dụng y tế cần toát lên sự tin cậy, chuyên nghiệp, dịu mắt và sạch sẽ:
- **Primary Color (Chủ đạo)**: Deep Emerald / Medical Teal (`#0d9488`, `teal-600` đến `emerald-700`) - tượng trưng cho sự sống, hồi phục, an toàn y tế.
- **Secondary Color (Phụ trợ)**: Indigo / Royal Blue (`#4f46e5`, `indigo-600`) - tạo cảm giác chuyên nghiệp, hiện đại và công nghệ.
- **Backgrounds**: Slate Gray nhẹ nhàng (`#f8fafc` hoặc `#f1f5f9`), tránh nền trắng tinh gây mỏi mắt cho bác sĩ khi làm việc lâu.
- **Status Badges**:
  - `Đang điều trị`: Xanh dương dịu (`bg-blue-50 text-blue-700 border-blue-200`)
  - `Đã khỏi`: Xanh lá cây phục hồi (`bg-emerald-50 text-emerald-700 border-emerald-200`)
  - `Chưa thanh toán`: Hổ phách cảnh báo (`bg-amber-50 text-amber-700 border-amber-200`)
  - `Đã thanh toán`: Xanh ngọc an toàn (`bg-teal-50 text-teal-700 border-teal-200`)
  - `Hết thuốc / Cảnh báo`: Đỏ nhạt (`bg-rose-50 text-rose-700 border-rose-200`)

## 2. Các Màn hình Nghiệp vụ Trọng tâm
1. **Clinical Dashboard Overview**:
   - Thẻ thống kê KPI (Bệnh nhân hôm nay, Đợt điều trị đang mở, Giường bệnh đang dùng, Doanh thu ngày).
   - Biểu đồ doanh thu theo khoa chuyên môn & biểu đồ tần suất bệnh lý phổ biến.
   - Bảng sự kiện y tế gần nhất theo thời gian thực.
2. **Hồ sơ Bệnh nhân 360 độ (Patient 360 View)**:
   - Header thông tin cơ bản: Mã BN, Họ tên, Giới tính, Tuổi/Ngày sinh, SĐT, Số CCCD, Ngày đăng ký.
   - Timeline lịch sử bệnh án: Liệt kê các lần khám, các đợt điều trị (kèm đợt tái phát đệ quy), đơn thuốc đã dùng và hóa đơn đã thanh toán.
3. **Phòng Khám & Kê đơn (Doctor Consultation & Rx)**:
   - Form nhập chẩn đoán, triệu chứng, chỉ định tiền khám.
   - Bảng kê đơn thuốc: Cho phép chọn thuốc từ kho, kiểm tra tồn kho trực tiếp, nhập số lượng, tự tính thành tiền.
   - Tùy chọn chuyển vào đợt điều trị (chọn giường bệnh trống nếu cần nằm viện theo dõi).
4. **Phân hệ Giường & Điều trị (In-patient Bed Management)**:
   - Bản đồ sơ đồ giường bệnh trực quan theo từng phòng khám/khoa (màu xanh: Giường trống; màu đỏ/cam: Giường đang có bệnh nhân kèm tên và mã đợt).

## 3. Trải nghiệm người dùng (UX Principles)
- **Thao tác nhanh (Low Click Count)**: Cho phép bác sĩ/nhân viên tiếp nhận chỉ mất tối đa 2-3 clicks để hoàn thành 1 nghiệp vụ.
- **Cảnh báo tức thì**: Cảnh báo ngay khi số lượng thuốc kê vượt quá số lượng tồn kho khả dụng.
- **Hộp thoại xác nhận an toàn (Confirmation Dialogs)**: Luôn yêu cầu xác nhận trước các hành động quan trọng như đóng đợt điều trị hoặc xác nhận thanh toán viện phí.
