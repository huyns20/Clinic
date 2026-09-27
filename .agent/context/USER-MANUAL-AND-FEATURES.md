# Cẩm Nang Chi Tiết Tất Cả Tính Năng & Hướng Dẫn Sử Dụng Hệ Thống (Knowledge Base)

> **Tài liệu Tri thức Nghiệp vụ & Vận hành Hệ thống Quản lý Phòng khám Bệnh Tư nhân**  
> **Đề tài 4 (STT 16 đến 20 + 37) - Học viện Công nghệ Bưu chính Viễn thông (PTIT)**  
> **Áp dụng các yêu cầu từ: DeCuongBTL (1).pdf, BaoCao_DeTai4_PhongKham.docx.md, funtion.txt, doc.txt**

---

## 1. Bản Đồ Tổng Quan Các Phân Hệ Tính Năng

Hệ thống được thiết kế hướng tới việc quản lý toàn diện phòng khám tư nhân quy mô vừa và lớn, bảo đảm tính toàn vẹn dữ liệu chuẩn BCNF trong PostgreSQL:

| Phân hệ (Tab UI) | Mục tiêu nghiệp vụ | Bảng CSDL chính | API Endpoints chính |
| :--- | :--- | :--- | :--- |
| **1. Bảng Điều Khiển (Dashboard)** | Theo dõi tức thời KPI phòng khám, đợt điều trị mở, cảnh báo tồn kho thuốc | `benhnhan`, `dotdieutri`, `giuongbenh`, `hoadon`, `thuoc` | `GET /api/v1/thong-ke/tong-quan` |
| **2. Tiếp Nhận & Bệnh Nhân (Reception)** | Đăng ký bệnh nhân mới, tìm kiếm real-time, tra cứu hồ sơ | `benhnhan` | `GET /api/v1/benh-nhan`, `POST /api/v1/benh-nhan` |
| **3. Hồ Sơ Bệnh Án 360° (Patient 360°)** | **Mục 1.b**: Xem bệnh hiện tại, lần khám chữa thứ mấy, bác sĩ, giường nằm & toàn bộ lịch sử kèm chi tiết viện phí | `benhnhan`, `sukienyte`, `dotdieutri`, `hoadon`, `hoadonchitiet` | `GET /api/v1/benh-nhan/{maBN}/ho-so-360` |
| **4. Khám Bệnh & Phân Bổ (Examination)** | Bác sĩ ghi nhận khám, tiền khám, triệu chứng, tự sinh mã sự kiện, tùy chọn mở đợt điều trị & gán giường bệnh | `sukienyte`, `lankham`, `dotdieutri`, `hoadon`, `giuongbenh` | `POST /api/v1/lan-kham`, `POST /api/v1/dot-dieu-tri` |
| **5. Kê Đơn Thuốc (Prescription)** | Kê đơn theo sự kiện, trigger DB tự động kiểm tra và trừ tồn kho thuốc, cộng tiền viện phí | `sudungthuoc`, `thuoc`, `hoadon` | `POST /api/v1/thuoc/ke-don` |
| **6. Đợt Điều Trị & Giường (Treatment)** | Theo dõi phác đồ liên tục, kết luận khỏi bệnh giải phóng giường về 'Trong', theo dõi tái phát (`MaDotTruoc`) | `dotdieutri`, `giuongbenh`, `lanchuabenh` | `GET /api/v1/dot-dieu-tri`, `PUT /api/v1/dot-dieu-tri/{id}/dong` |
| **7. Kho Dược Phẩm (Pharmacy)** | Theo dõi toàn bộ thuốc, cảnh báo tồn kho `< 200`, chức năng nhập thêm kho | `thuoc` | `GET /api/v1/thuoc`, `PUT /api/v1/thuoc/{id}/nhap-kho` |
| **8. Thu Ngân & Viện Phí (Billing)** | Danh sách hóa đơn, tổng hợp tự động 5 nguồn thu, xác nhận thu tiền | `hoadon`, `hoadonchitiet` | `GET /api/v1/hoa-don`, `PUT /api/v1/hoa-don/{id}/thanh-toan` |
| **9. Quản Lý Danh Mục (Master Data)** | **Mục 1**: Full CRUD cho Bác sĩ, Y tá, Bệnh lý, Thiết bị y tế, Dịch vụ kỹ thuật, Phòng khám, Giường bệnh | `bacsy`, `yta`, `danhmucbenh`, `thietbi`, `dichvuyte`, `phongkham`, `giuongbenh` | `/api/v1/master/**` |
| **10. Báo Cáo Bệnh Theo Tháng (Reports 2.1)** | **Mục 2.1**: Xếp hạng bệnh giảm dần số ca, chuỗi khám chữa liên tiếp = 1 đợt, tính tái phát | `dotdieutri`, `danhmucbenh` | `GET /api/v1/thong-ke/benh-theo-thang` |
| **11. Doanh Thu 5 Nguồn (Reports 2.2)** | **Mục 2.2**: Bóc tách 5 nguồn thu: Tiền khám, chữa, thuốc, dịch vụ, giường/CSVC | `lankham`, `lanchuabenh`, `sudungthuoc`, `hoadonchitiet` | `GET /api/v1/thong-ke/doanh-thu-chi-tiet` |
| **12. Bảng Tính Lương (Reports 3)** | **Mục 3**: Bác sĩ thưởng 1.000.000đ/ca khỏi bệnh; Y tá thưởng 200.000đ/lượt hỗ trợ | `nhanvienyte`, `bacsy`, `yta`, `dotdieutri`, `lanchuabenh` | `GET /api/v1/thong-ke/bang-luong-chi-tiet` |

---

## 2. Hướng Dẫn Chi Tiết Từng Tính Năng & Kịch Bản Thao Tác (Step-by-Step Walkthrough)

### KỊCH BẢN THỰC HÀNH MẪU HOÀN CHỈNH (END-TO-END FLOW)

Người dùng có thể thực hiện theo kịch bản này để trải nghiệm trọn vẹn toàn bộ các tính năng từ A đến Z:

#### BƯỚC 1: Tiếp Nhận Bệnh Nhân Mới
1. Vào tab **"Tiếp Nhận & Bệnh Nhân"**.
2. Nhấn nút **"Tiếp Nhận Mới"** ở góc phải thanh header (hoặc nút xanh trong bảng).
3. Nhập dữ liệu mẫu:
   - Họ và tên: `Nguyễn Văn An`
   - Giới tính: `Nam`
   - Ngày sinh: `1990-05-15`
   - Số CCCD: `001201999888` (12 số duy nhất)
   - Số điện thoại: `0988776655`
   - Địa chỉ: `Hà Đông, Hà Nội`
4. Bấm **"Lưu Vào CSDL"**.
   - *Phản hồi*: Toast xanh hiện ra thông báo đã lưu thành công kèm Mã BN mới (ví dụ: `BN011`). Bệnh nhân lập tức hiển thị trên bảng danh sách.

#### BƯỚC 2: Xem Hồ Sơ Bệnh Án 360° (Mục 1.b)
1. Trên dòng bệnh nhân `BN001` (hoặc bệnh nhân vừa tạo), nhấn nút **"Hồ Sơ 360°"**.
2. Hộp thoại Modal hiện lên:
   - Phần 1 hiển thị các bệnh đang điều trị: Tên bệnh, Đợt điều trị, Lần khám/chữa thứ mấy, Bác sĩ phụ trách, Giường nằm.
   - Phần 2 hiển thị toàn bộ các lần khám/chữa trong quá khứ kèm chi tiết từng khoản tiền (tiền khám, tiền thuốc, đơn vị, trạng thái thanh toán).
3. Bấm **"Đóng Hồ Sơ"**.

#### BƯỚC 3: Khám Bệnh & Mở Đợt Điều Trị Có Gán Giường Bệnh
1. Vào tab **"Khám Bệnh & Kê Đơn"**.
2. Tại form bên trái **"Tiếp Nhận & Ghi Nhận Khám Bệnh"**:
   - Chọn Bệnh nhân: Chọn `BN001` (hoặc BN vừa tạo).
   - Chọn Bác sĩ: Chọn `BS001` (Bác sĩ chuyên khoa Nội).
   - Chọn Khoa khám: Chọn `KHOA_NOI` (Khoa Nội Tổng Hợp).
   - Tiền khám: Giữ nguyên `150000`.
   - Triệu chứng: Nhập `Đau tức ngực, khó thở, sốt nhẹ về chiều`.
3. Tích chọn checkbox: **"Mở Đợt Điều Trị Mới Cho Ca Bệnh Này (DotDieuTri)"**.
   - Chẩn đoán bệnh: Chọn `B001 - Viêm phế quản co thắt`.
   - Mức độ nặng: Chọn `Nang`.
   - Bố trí giường bệnh: Chọn giường trống (ví dụ: `Giường G101`).
4. Nhấn **"Lưu Hồ Sơ Khám & Xuất Hóa Đơn Trực Tiếp"**.
   - *Phản hồi*: Hệ thống tự sinh mã sự kiện `NOI-BS001-K-...`, lưu đợt điều trị mới, cập nhật giường `G101` từ `Trong` sang `CoNguoi`, tạo hóa đơn viện phí chưa thanh toán.

#### BƯỚC 4: Kê Đơn Thuốc Trừ Tồn Kho Tự Động
1. Tại tab **"Khám Bệnh & Kê Đơn"**, nhìn sang form bên phải **"Kê Đơn Thuốc"**:
   - Chọn Mã sự kiện: Chọn sự kiện khám vừa tạo ở bước 3.
   - Chọn Thuốc: Chọn `Paracetamol 500mg (Còn: 500 viên - 5.000 đ)`.
   - Số lượng: Nhập `20`.
2. Nhấn **"+ Xác Nhận Kê Đơn & Trừ Tồn Kho"**.
   - *Phản hồi*: Toast xanh hiện ra thông báo kê đơn thành công. Tồn kho thuốc tự động giảm từ 500 xuống còn 480 viên. Hóa đơn sự kiện được tự động cộng thêm: $20 \times 5.000 = 100.000\,\text{đ}$.

#### BƯỚC 5: Đóng Đợt Điều Trị & Giải Phóng Giường Bệnh
1. Vào tab **"Đợt Điều Trị & Giường"**.
2. Tìm đợt điều trị vừa tạo ở bước 3.
3. Khi bệnh nhân đã điều trị khỏi: Bấm nút **"Kết luận khỏi bệnh"**.
   - *Phản hồi*: Trạng thái chuyển sang `Đã khỏi` (màu xanh lá), ngày kết thúc được ghi nhận. Giường `G101` tự động được giải phóng trở về trạng thái `Trong`.

#### BƯỚC 6: Thu Ngân & Xác Nhận Viện Phí
1. Vào tab **"Thu Ngân & Viện Phí"**.
2. Tìm hóa đơn của sự kiện y tế ở bước 3 (Tổng tiền: 150.000 tiền khám + 100.000 tiền thuốc = 250.000đ).
3. Bấm nút **"Xác Nhận Thu Phí"**.
   - *Phản hồi*: Hóa đơn chuyển sang trạng thái `Đã Thanh Toán`. Số tiền 250.000đ được ghi nhận vào quỹ thực thu của phòng khám và hiển thị ngay trên KPI Dashboard.

#### BƯỚC 7: Quản Lý Danh Mục Master Data (Mục 1)
1. Vào tab **"Quản Lý Danh Mục"**.
2. Duyệt qua các sub-tab:
   - `Bác Sĩ`: Xem danh sách bác sĩ. Bấm **"Thêm Mới Dữ Liệu"** để tạo bác sĩ mới (ví dụ: `BS005`, chuyên môn `Chẩn đoán hình ảnh`).
   - `Thiết Bị Y Tế`: Xem máy móc. Thêm mới thiết bị (ví dụ: `TB005`, Tên `Máy đo điện tim ECG 12 cần`).
   - `Dịch Vụ Y Tế`: Xem dịch vụ và đơn giá quy định.
   - `Giường Bệnh`: Xem danh sách giường và trạng thái thực tế.
3. Xóa một bản ghi thử nghiệm bằng biểu tượng thùng rác màu đỏ.

#### BƯỚC 8: Xem Báo Cáo Chuyên Sâu Theo Tháng (Mục 2.1, 2.2, 3)
1. Vào tab **"Báo Cáo Thống Kê"**.
2. Chọn Tháng/Năm: `2024-03` và bấm **"Xem Báo Cáo"**.
3. Quan sát 3 phân vùng kết quả:
   - **Mục 2.2 (Doanh thu phân rã)**: 5 thẻ độc lập: Tiền khám, Tiền chữa, Tiền thuốc, Tiền dịch vụ, Tiền giường/CSVC.
   - **Mục 2.1 (Xếp hạng bệnh lý)**: Bảng xếp hạng các bệnh mắc phải sắp xếp giảm dần theo số ca mắc. Thấy rõ sự phân tách giữa số đợt mới và số đợt tái phát (đáp ứng đúng quy tắc chuỗi khám chữa liên tiếp tính là 1 ca).
   - **Mục 3 (Bảng lương nhân sự)**: Bảng tính lương tự động cộng thưởng 1.000.000đ cho bác sĩ với mỗi ca khỏi bệnh và 200.000đ cho y tá với mỗi lượt hỗ trợ.

---

## 3. Quy Tắc Ràng Buộc Nghiệp Vụ CSDL Cốt Lõi (Business Constraints)

1. **Ràng buộc định danh Bệnh nhân**:
   - Trường `SoCCCD` phải là duy nhất (`UNIQUE`).
   - `GioiTinh` chỉ chấp nhận giá trị `'M'` (Nam) hoặc `'F'` (Nữ).
2. **Quy tắc mã sự kiện y tế**:
   - Cú pháp: `<MaKhoa>-<MaBS>-<K/C>-<YYYYMMDD>-<STT>`.
   - `K`: Khám ban đầu; `C`: Lần chữa bệnh.
3. **Quy tắc đợt điều trị BCNF**:
   - Một đợt điều trị (`DotDieuTri`) gom toàn bộ chuỗi khám và chữa liên tiếp cho cùng 1 bệnh nhân và 1 bệnh lý.
   - Khi bệnh nhân bị tái phát bệnh, mở đợt mới trỏ `MaDotTruoc` về đợt cũ.
4. **Trigger trừ kho thuốc tự động**:
   - Nếu `SoLuong` kê đơn > `TonKho` hiện tại của thuốc, database trigger sẽ `RAISE EXCEPTION` và hủy bỏ giao dịch để bảo toàn kho.
5. **Công thức tính viện phí**:
   - Tổng tiền = Tiền khám + Tiền chữa + Tiền thuốc kê đơn + Tiền dịch vụ kỹ thuật + Tiền giường/CSVC.

---
*Tài liệu tri thức này được lưu trữ trong `.agent/context/USER-MANUAL-AND-FEATURES.md` để làm ngữ cảnh vận hành và kiểm thử tự động cho toàn dự án.*
