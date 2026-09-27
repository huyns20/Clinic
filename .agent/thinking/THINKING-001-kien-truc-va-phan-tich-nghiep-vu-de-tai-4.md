# Thinking 001: Phân Tích Chuyên Sâu Kiến Trúc Và Nghiệp Vụ Đề Tài 4 (Phòng Khám Bệnh Tư Nhân)

- **Document ID**: THINKING-001
- **Created Date**: 2026-09-27
- **Author**: Principal Software Architect & Database Specialist
- **Status**: APPROVED
- **Linked Plan**: [PLAN-001-he-thong-quan-ly-phong-kham.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/plans/PLAN-001-he-thong-quan-ly-phong-kham.md)

---

## 1. Phân Tích Kịch Bản Thực Tế & Nghiệp Vụ Đề Tài 4

### 1.1. Bản chất Nghiệp vụ Khám Chữa Bệnh Tư Nhân
Một phòng khám tư nhân khác biệt với bệnh viện công lập lớn ở các điểm mấu chốt:
- **Tốc độ tiếp nhận & tính linh hoạt**: Bệnh nhân đến khám không phải qua thủ tục hành chính phức tạp, nhưng cần tra cứu ngay lịch sử khám và tiền sử dị ứng, tiền sử điều trị trước đây.
- **Tính liên tục của Đợt điều trị (Treatment Course)**: Một bệnh nhân bị đau dạ dày hoặc viêm phế quản không chỉ đến 1 lần rồi xong; họ trải qua nhiều lần chữa bệnh (lần tái khám, thay đổi phác đồ, châm cứu, vật lý trị liệu) trong cùng một đợt điều trị.
- **Quản lý tài nguyên vật chất & hao phí**: Thuốc phải kiểm soát tồn kho tức thời khi bác sĩ kê đơn để tránh trường hợp xuất đơn thuốc mà trong kho đã hết. Giường bệnh tại phòng khám tư có số lượng hạn chế, cần nắm rõ giường nào đang trống để phân bổ.
- **Chi phí & Minh bạch hóa đơn**: Mọi chi phí từ tiền khám, tiền công bác sĩ/y tá, tiền sử dụng phòng/giường, thuốc, thiết bị và dịch vụ kỹ thuật đều phải được tự động tổng hợp chi tiết thành hóa đơn minh bạch.

### 1.2. Phân Tích Sơ Đồ CSDL & Các Điểm Đặc Thù Kỹ Thuật

#### a. Quan Hệ Kế Thừa ISA (Specialization / Generalization)
1. **Cụm Nhân Sự**:
   - `NhanVienYTe`: Thực thể cha lưu trữ mã nhân viên, họ tên, giới tính, ngày sinh, SĐT, mã khoa, hệ số lương, ngày vào làm, và trường phân loại `LoaiNV` ('BACSY' / 'YTA').
   - `BacSy`: Thực thể con kế thừa `MaNV` làm khóa chính `MaBS`, bổ sung `Email` và `ChuyenMon`.
   - `YTa`: Thực thể con kế thừa `MaNV` làm khóa chính `MaYT`, bổ sung `ChungChiHanhNghe`.
2. **Cụm Sự Kiện Y Tế**:
   - `SuKienYTe`: Thực thể cha ghi nhận mọi lượt bệnh nhân đến phòng khám gồm `MaSuKien`, `MaBN`, `MaBS`, `ThoiGian`, và `LoaiSuKien` ('KHAM' / 'CHUA').
   - `LanKham`: Thực thể con đại diện cho sự kiện khám bệnh, lưu `MaKhoa`, `TrieuChung`, `TienKham`. Một lần khám có thể dẫn đến việc mở một `DotDieuTri` mới.
   - `LanChuaBenh`: Thực thể con đại diện cho sự kiện chữa bệnh thuộc một `DotDieuTri`, lưu `MaPhong`, `HinhThucChua`, `KetLuan`, `TienChua`.

#### b. Chuẩn Hóa BCNF của Bảng Đợt Điều Trị (`DotDieuTri`)
Trong thiết kế ban đầu chưa tối ưu, nhiều hệ thống thường lưu trực tiếp `MaBN` và `MaBS` vào `DotDieuTri`. Tuy nhiên, điều này tạo ra phụ thuộc hàm bắc cầu:
- `MaDotDieuTri -> MaSuKienKham`
- `MaSuKienKham -> LanKham -> SuKienYTe (MaBN, MaBS)`
Do đó, việc lưu `MaBN` và `MaBS` tại `DotDieuTri` sẽ vi phạm chuẩn BCNF (Boyce-Codd Normal Form) gây dư thừa dữ liệu và rủi ro không nhất quán.
**Giải pháp đã tối ưu trong Schema**: Bảng `DotDieuTri` chỉ giữ `MaSuKienKham` làm khóa ngoại. Khi cần truy vấn thông tin bệnh nhân và bác sĩ của đợt điều trị, hệ thống sử dụng View `v_DotDieuTri_ChiTiet` để join nhanh, vừa đảm bảo chuẩn hóa dữ liệu cao nhất, vừa tối ưu hiệu năng đọc.

#### c. Mối Quan Hệ Đệ Quy Tái Phát Bệnh (`MaDotTruoc`)
Thuộc tính `MaDotTruoc` trong `DotDieuTri` là một khóa ngoại tự tham chiếu (Self-referencing Foreign Key) trỏ về chính `MaDotDieuTri` của đợt điều trị trước đó. Điều này cho phép:
- Truy vết toàn bộ chuỗi tiền sử bệnh tật của bệnh nhân đối với cùng một mã bệnh hoặc bệnh liên quan.
- Xác định bệnh mạn tính hoặc tỷ lệ tái phát sau điều trị để bác sĩ đưa ra phác đồ chính xác hơn.

#### d. Quản Lý Tồn Kho Thuốc Qua Database Trigger
Khi bác sĩ kê đơn thuốc (thêm bản ghi vào `SuDungThuoc`), Database Trigger `trg_tru_ton_kho_thuoc()` sẽ tự động:
1. Đọc số lượng tồn kho hiện tại trong bảng `Thuoc` (`FOR UPDATE` để chống race condition).
2. Kiểm tra nếu `TonKho < SoLuong` kê đơn -> Bắn exception `RAISE EXCEPTION` ngăn chặn giao dịch.
3. Nếu hợp lệ -> Trừ số lượng trong kho `UPDATE Thuoc SET TonKho = TonKho - NEW.SoLuong`.
-> **Quyết định kiến trúc**: Backend Spring Boot không cần tự viết câu lệnh update trừ kho bằng tay, mà dựa vào Database Trigger để đảm bảo tính toàn vẹn ACID tuyệt đối kể cả khi có nhiều yêu cầu đồng thời.

---

## 2. Đánh Giá & Quyết Định Lựa Chọn Công Nghệ Frontend

Người dùng yêu cầu: *"fe đánh giá 1 công nghệ nào thật đơn giản ma nhanh lại đẹp cho tôi... công nghệ UI có thể sử dụng đơn giản nodejs để làm Ui đơng giản thôi cũng được . nhưng pahri làm thật đẹp"*.

Dưới đây là ma trận đánh giá chi tiết 4 phương án công nghệ phổ biến:

| Tiêu chí đánh giá | Phương án 1: React + Vite + Tailwind CSS | Phương án 2: Next.js (App Router) | Phương án 3: Vue 3 + Vite | Phương án 4: Thymeleaf + Bootstrap |
| :--- | :--- | :--- | :--- | :--- |
| **Độ đơn giản & Dễ học** | ⭐⭐⭐⭐⭐ Cực kỳ đơn giản, chỉ là SPA thuần, không cần server-side | ⭐⭐⭐ Phức tạp vì có Server Components, Hydration, SSR overhead | ⭐⭐⭐⭐ Đơn giản nhưng hệ sinh thái component ít phong phú hơn React | ⭐⭐⭐ Đơn giản cho monolithic nhưng UX lạc hậu |
| **Tốc độ phát triển & Khởi động** | ⭐⭐⭐⭐⭐ Vite khởi động < 300ms, HMR tức thì | ⭐⭐⭐ Dev server webpack/turbopack nặng hơn | ⭐⭐⭐⭐⭐ Nhanh tương đương | ⭐⭐ Mỗi lần đổi code HTML/Java phải restart hoặc reload trang |
| **Độ Đẹp & Thẩm mỹ Giao diện** | ⭐⭐⭐⭐⭐ Đỉnh cao nhất nhờ kết hợp Tailwind CSS + Lucide Icons + Shadcn patterns | ⭐⭐⭐⭐⭐ Tương tự React nhưng cồng kềnh | ⭐⭐⭐⭐ Đẹp nhưng ít mẫu Dashboard y tế chuyên nghiệp có sẵn | ⭐⭐ Giao diện cơ bản, form submit reload, không mượt |
| **Khả năng tách biệt Frontend/Backend** | ⭐⭐⭐⭐⭐ Chuẩn mực REST API + SPA tách biệt, dễ bảo trì, dễ test | ⭐⭐⭐ Lai giữa FE và BE, dễ gây chồng chéo logic | ⭐⭐⭐⭐⭐ Tách biệt tốt | ⭐ Monolithic gắn chặt vào Spring Boot Controller |
| **Tương thích Node.js hiện tại** | ⭐⭐⭐⭐⭐ Hoàn hảo với Node.js v24.14.0 đã cài trên máy | ⭐⭐⭐⭐ Hoạt động được | ⭐⭐⭐⭐⭐ Hoạt động được | Không dùng Node.js |

### 👉 QUYẾT ĐỊNH CUỐI CÙNG (FINAL ARCHITECTURAL DECISION):
Lựa chọn **React (Vite) + Tailwind CSS + Lucide React**:
1. **Đơn giản nhất**: Kiến trúc Single Page Application gọn gàng, mã nguồn đặt trọn vẹn trong `Clinic/frontend/`.
2. **Cực nhanh**: Chạy bằng `npm run dev` thông qua Node.js v24 có sẵn, phản hồi thay đổi giao diện theo mili-giây.
3. **Cực đẹp**: Được thiết kế riêng theo phong cách Clinical Medical Dashboard cao cấp:
   - Tông màu y tế chuyên nghiệp: Emerald / Medical Teal kết hợp Indigo.
   - Thẻ thống kê KPI với hiệu ứng đổ bóng mềm mại (glassmorphism/neumorphism nhẹ).
   - Bản đồ sơ đồ giường bệnh tương tác thời gian thực.
   - Bảng kê đơn thuốc và hóa đơn viện phí sắc nét, chuyên nghiệp.

---

## 3. Kiến Trúc Tương Tác Giữa Backend & Frontend

```
+--------------------------------------------------------------+
|             Frontend: React + Vite + Tailwind CSS            |
|                  (Chạy tại: http://localhost:5173)           |
+------------------------------+-------------------------------+
                               |
                   HTTP REST API (JSON)
                   CORS: Enabled
                               |
+------------------------------v-------------------------------+
|                Backend: Java Spring Boot 3                   |
|                  (Chạy tại: http://localhost:8080)           |
|                                                              |
|  Controllers  -->  Services (@Transactional)  --> Repos      |
|  OpenAPI / Swagger UI: /swagger-ui.html                      |
+------------------------------+-------------------------------+
                               |
                        JDBC (Port 5432)
                               |
+------------------------------v-------------------------------+
|         PostgreSQL 12+ Database (`phong_kham2`)              |
|        (Container Docker: phongkham_postgres)                |
|  - 24 Tables (BCNF Normalized, ISA Hierarchies)              |
|  - 5 Triggers (Tự sinh mã, Trừ tồn kho, Tính hóa đơn)        |
|  - 6 Stored Procedures (Tạo đợt, Đóng đợt, Tính lương)       |
|  - 2 Views (v_DotDieuTri_ChiTiet, v_LichSuKhamBenh)          |
+--------------------------------------------------------------+
```
Quy trình này phân định rõ ràng ranh giới trách nhiệm, giúp dự án vừa chuẩn chỉ về mặt kỹ thuật học thuật theo yêu cầu giảng viên PTIT, vừa mang tính ứng dụng thực tiễn cao như một sản phẩm thương mại.
