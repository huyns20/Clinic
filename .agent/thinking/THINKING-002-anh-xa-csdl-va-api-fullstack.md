# Thinking 002: Ánh Xạ CSDL PostgreSQL `phong_kham2` Vào Spring Boot JPA & Thiết Kế API Fullstack

- **Document ID**: THINKING-002
- **Created Date**: 2026-09-27
- **Author**: Software Specialist Agent
- **Status**: APPROVED
- **Linked Plan**: [PLAN-002-trien-khai-toan-bo-backend-va-ghep-ui.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/plans/PLAN-002-trien-khai-toan-bo-backend-va-ghep-ui.md)

---

## 1. Phân Tích Ánh Xạ Khóa Chính & Khóa Phức Hợp
Trong CSDL `phong_kham2`:
1. **Khóa đơn (Single PK)**:
   - Hầu hết các bảng dùng chuỗi VARCHAR định danh nghiệp vụ: `mabn` ('BN001'), `makhoa` ('KHOA01'), `mathuoc` ('T001'), `maphong` ('P101'), `magiuong` ('G01'), `mabs` ('NV001').
   - `sukienyte.masukien`: Được trigger `sinh_ma_sukien()` tự động sinh dạng `<MaKhoa>-<MaBS>-<K/C>-<YYYYMMDD>-<STT>` nếu truyền null, hoặc cho phép truyền mã trực tiếp khi seed data.
2. **Khóa phức hợp (Composite PK)**:
   - `sudungthuoc`: `(masukien, mathuoc)` -> Sử dụng JPA `@Embeddable` + `@EmbeddedId` hoặc `@IdClass`.
   - `sudungthietbi`: `(masukien, mathietbi)`.
   - `sudungdichvu`: `(masukien, madv)`.
   - `sudungnhancong`: `(masukien, manv)`.
   - `hoadonchitiet`: `(masukien, sodong)`.
3. **Quan hệ 1 - 1 ISA**:
   - `sukienyte` là cha của `lankham` và `lanchuabenh`.
   - `hoadon` 1-1 với `sukienyte` qua `masukien`.
   Ta có thể mô hình hóa bằng các Entity độc lập có `masukien` làm `@Id` (String), vừa đơn giản, vừa độc lập, không bị ràng buộc cascade phức tạp làm sai lệch logic trigger của PostgreSQL.

## 2. Thiết Kế Luồng Xử Lý Nghiệp Vụ Tại Service
1. **Luồng Khám Bệnh**:
   - Nhận DTO: `maBN`, `maBS`, `maKhoa`, `trieuChung`, `tienKham`.
   - Sinh `maSuKien` (hoặc để trigger sinh).
   - Insert vào `sukienyte` với `loaisukien = 'KHAM'`.
   - Insert vào `lankham`.
   - Tự động insert vào `hoadon` (trạng thái 'ChuaThanhToan', tổng tiền = `tienKham`).
   - Insert vào `hoadonchitiet` dòng 1: "Tiền khám bệnh", số tiền = `tienKham`.
2. **Luồng Kê Đơn Thuốc**:
   - Nhận DTO: `maSuKien`, `maThuoc`, `soLuong`.
   - Lấy `donGia` hiện tại từ bảng `thuoc`.
   - Insert vào `sudungthuoc`.
   - Database trigger `trg_tru_ton_kho_thuoc()` sẽ tự kiểm tra và trừ tồn kho. Nếu thiếu, trigger ném exception, Spring Boot rollback và báo lỗi cho Client.
   - Insert thêm dòng vào `hoadonchitiet` với mô tả tên thuốc và thành tiền.
   - Cập nhật tổng tiền hóa đơn.
3. **Luồng Đóng Đợt Điều Trị**:
   - Nhận: `maDotDieuTri`, `ngayKetThuc`.
   - Gọi Stored Procedure `sp_dong_dot_dieu_tri` hoặc update `dotdieutri.trangthai = 'DaKhoi'`, `giuongbenh.trangthai = 'Trong'`.
