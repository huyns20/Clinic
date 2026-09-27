# Plan 002: Triển Khai Toàn Bộ Backend & Ghép Nối Frontend UI Đề Tài 4

- **Plan ID**: PLAN-002
- **Created Date**: 2026-09-27
- **Author**: Software Specialist Agent
- **Status**: APPROVED (User Approved All Automations)
- **Linked Thinking**: [THINKING-002-anh-xa-csdl-va-api-fullstack.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/thinking/THINKING-002-anh-xa-csdl-va-api-fullstack.md)
- **Linked Tasks**: [TASK-TRACKER.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/tasks/TASK-TRACKER.md)

---

## 1. Mục Tiêu (Objective)
Triển khai toàn bộ các Entities, Repositories, DTOs, Services và REST Controllers ánh xạ chính xác 100% vào database `phong_kham2` trong PostgreSQL, kết nối với giao diện người dùng React + Vite + Tailwind CSS và hoàn thiện tự động test.

## 2. Kế Hoạch Chi Tiết Các Bước (Step-by-step Execution)

### Bước 1: Chuẩn hóa & Bổ sung đầy đủ JPA Entities (`com.example.clinic.entity`)
- `BenhNhan` (@Table(name = "benhnhan")): mabn, hoten, gioitinh, ngaysinh, sdt, diachi, socccd, ngaydangky.
- `Khoa` (@Table(name = "khoa")): makhoa, tenkhoa, mota.
- `NhanVienYTe` (@Table(name = "nhanvienyte")): manv, hoten, gioitinh, ngaysinh, sdt, makhoa, hesoluong, ngayvaolam, loainv.
- `BacSy` (@Table(name = "bacsy")): mabs, email, chuyenmon.
- `YTa` (@Table(name = "yta")): mayt, chungchihanhnghe.
- `DanhMucBenh` (@Table(name = "danhmucbenh")): mabenh, tenbenh, nhombenh, mota.
- `Thuoc` (@Table(name = "thuoc")): mathuoc, tenthuoc, donvitinh, dongia, hangsx, tonkho.
- `ThietBi` (@Table(name = "thietbi")): mathietbi, tenthietbi, dongiasudung.
- `DichVuYTe` (@Table(name = "dichvuyte")): madv, tendv, dongia.
- `PhongKham` (@Table(name = "phongkham")): maphong, tenphong, chucnang, makhoa, dongiasudung.
- `GiuongBenh` (@Table(name = "giuongbenh")): magiuong, maphong, trangthai, dongiangay.
- `SuKienYTe` (@Table(name = "sukienyte")): masukien, mabn, mabs, thoigian, loaisukien.
- `LanKham` (@Table(name = "lankham")): masukien, makhoa, trieuchung, tienkham.
- `DotDieuTri` (@Table(name = "dotdieutri")): madotdieutri, masukienkham, mabenh, mucdonang, solanchuadukien, ngaybatdau, ngayketthuc, trangthai, magiuong, madottruoc.
- `LanChuaBenh` (@Table(name = "lanchuabenh")): masukien, madotdieutri, hinhthucchua, ketluan, tienchua, maphong.
- `SuDungThuoc` (@Table(name = "sudungthuoc")): masukien, mathuoc, soluong, dongiaapdung.
- `HoaDon` (@Table(name = "hoadon")): masukien, ngaylap, tongtien, trangthaitt.
- `HoaDonChiTiet` (@Table(name = "hoadonchitiet")): masukien, sodong, motakhoanmuc, sotien.
- `Luong` (@Table(name = "luong")): maluong, manv, thang, ngaynhanluong, luongcoban, tienthuong, tongluong, ghichu.

### Bước 2: Spring Data JPA Repositories
Tạo các repository tương ứng với các query tìm kiếm, filter theo ngày, theo khoa, theo trạng thái điều trị.

### Bước 3: Services & DTOs
- `BenhNhanService`: Đăng ký, tìm kiếm, lịch sử khám.
- `KhamChuaService`: Tiếp nhận khám bệnh, mở đợt điều trị, thêm lần chữa, đóng đợt (kèm giải phóng giường).
- `DuocPhamService`: Tra cứu thuốc, kê đơn thuốc (trigger tự trừ kho).
- `VienPhiService`: Chi tiết hóa đơn, xác nhận thanh toán.
- `ThongKeService`: Báo cáo doanh thu, tần suất bệnh lý, tính lương nhân viên (`sp_tinh_luong_thang`).

### Bước 4: REST Controllers
- `/api/v1/benh-nhan`
- `/api/v1/khoa`
- `/api/v1/bac-sy`
- `/api/v1/thuoc`
- `/api/v1/phong-giuong`
- `/api/v1/lan-kham`
- `/api/v1/dot-dieu-tri`
- `/api/v1/lan-chua-benh`
- `/api/v1/hoa-don`
- `/api/v1/thong-ke`

### Bước 5: Ghép Nối Frontend UI & Xác Minh Toàn Diện
- Đồng bộ các hàm gọi API trong `src/api/client.js` để kết nối live data với Backend.
- Chạy `test-runner.js` để kiểm thử toàn diện các kịch bản nghiệp vụ.
