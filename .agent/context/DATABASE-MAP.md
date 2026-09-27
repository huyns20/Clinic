# Database Schema Map: PostgreSQL Database `phong_kham2`

Tổng cộng: **24 Bảng**, **5 Triggers**, **2 Functions**, **2 Views**, **6 Stored Procedures**.

---

## 1. Nhóm Danh Mục Gốc
1. **`Khoa`**:
   - `MaKhoa` (PK, VARCHAR(10)), `TenKhoa` (VARCHAR(100)), `MoTa` (TEXT).
2. **`LoaiNhanCong`**:
   - `MaLoaiCong` (PK, VARCHAR(10)), `TenLoaiCong` (VARCHAR(100)), `DonGiaMacDinh` (NUMERIC(12,0)).
3. **`BenhNhan`**:
   - `MaBN` (PK, VARCHAR(10)), `HoTen` (VARCHAR(100)), `GioiTinh` (CHAR(1) M/F), `NgaySinh` (DATE), `SDT` (VARCHAR(15)), `DiaChi` (VARCHAR(200)), `SoCCCD` (VARCHAR(20) UNIQUE), `NgayDangKy` (DATE).
4. **`DanhMucBenh`**:
   - `MaBenh` (PK, VARCHAR(10)), `TenBenh` (VARCHAR(150)), `NhomBenh` (VARCHAR(50)), `MoTa` (TEXT).
5. **`Thuoc`**:
   - `MaThuoc` (PK, VARCHAR(10)), `TenThuoc` (VARCHAR(150)), `DonViTinh` (VARCHAR(20)), `DonGia` (NUMERIC(12,0)), `HangSX` (VARCHAR(100)), `TonKho` (INT >= 0).
6. **`ThietBi`**:
   - `MaThietBi` (PK, VARCHAR(10)), `TenThietBi` (VARCHAR(150)), `DonGiaSuDung` (NUMERIC(12,0) >= 0).
7. **`DichVuYTe`**:
   - `MaDV` (PK, VARCHAR(10)), `TenDV` (VARCHAR(150)), `DonGia` (NUMERIC(12,0) >= 0).
8. **`ThamSoHeThong`**:
   - `TenThamSo` (PK, VARCHAR(50)), `GiaTri` (NUMERIC(14,0)).

---

## 2. Nhóm Nhân Sự & Tiền Lương (Mô hình ISA)
9. **`NhanVienYTe`** (Lớp cha ISA):
   - `MaNV` (PK, VARCHAR(10)), `HoTen` (VARCHAR(100)), `GioiTinh` (CHAR(1)), `NgaySinh` (DATE), `SDT` (VARCHAR(15)), `MaKhoa` (FK -> Khoa), `HeSoLuong` (NUMERIC(4,2)), `NgayVaoLam` (DATE), `LoaiNV` ('BACSY' | 'YTA').
10. **`BacSy`** (Lớp con ISA):
    - `MaBS` (PK, FK -> NhanVienYTe(MaNV)), `Email` (VARCHAR(100)), `ChuyenMon` (VARCHAR(100)).
11. **`YTa`** (Lớp con ISA):
    - `MaYT` (PK, FK -> NhanVienYTe(MaNV)), `ChungChiHanhNghe` (VARCHAR(100)).
12. **`Luong`**:
    - `MaLuong` (PK, VARCHAR(20)), `MaNV` (FK -> NhanVienYTe), `Thang` (DATE), `NgayNhanLuong` (DATE), `LuongCoBan` (NUMERIC(12,0)), `TienThuong` (NUMERIC(12,0)), `TongLuong` (NUMERIC(12,0)), `GhiChu` (TEXT). UNIQUE(MaNV, Thang).

---

## 3. Nhóm Cơ Sở Vật Chất
13. **`PhongKham`**:
    - `MaPhong` (PK, VARCHAR(10)), `TenPhong` (VARCHAR(100)), `ChucNang` (VARCHAR(100)), `MaKhoa` (FK -> Khoa), `DonGiaSuDung` (NUMERIC(12,0)).
14. **`GiuongBenh`**:
    - `MaGiuong` (PK, VARCHAR(10)), `MaPhong` (FK -> PhongKham), `TrangThai` (VARCHAR(20) 'Trong' / 'CoNguoi'), `DonGiaNgay` (NUMERIC(12,0)).

---

## 4. Nhóm Sự Kiện Y Tế & Điều Trị (Mô hình ISA & BCNF)
15. **`SuKienYTe`** (Lớp cha ISA):
    - `MaSuKien` (PK, VARCHAR(50)), `MaBN` (FK -> BenhNhan), `MaBS` (FK -> BacSy), `ThoiGian` (TIMESTAMP), `LoaiSuKien` ('KHAM' | 'CHUA').
16. **`LanKham`** (Lớp con ISA):
    - `MaSuKien` (PK, FK -> SuKienYTe), `MaKhoa` (FK -> Khoa), `TrieuChung` (TEXT), `TienKham` (NUMERIC(12,0)).
17. **`DotDieuTri`** (Chuẩn hóa BCNF):
    - `MaDotDieuTri` (PK, VARCHAR(50)), `MaSuKienKham` (FK -> LanKham), `MaBenh` (FK -> DanhMucBenh), `MucDoNang` ('Nhe'|'Vua'|'Nang'), `SoLanChuaDuKien` (INT), `NgayBatDau` (DATE), `NgayKetThuc` (DATE), `TrangThai` ('DangDieuTri'|'DaKhoi'), `MaGiuong` (FK -> GiuongBenh), `MaDotTruoc` (FK -> DotDieuTri - Đệ quy tái phát).
18. **`LanChuaBenh`** (Lớp con ISA):
    - `MaSuKien` (PK, FK -> SuKienYTe), `MaDotDieuTri` (FK -> DotDieuTri), `HinhThucChua` (VARCHAR(30)), `KetLuan` (TEXT), `TienChua` (NUMERIC(12,0)), `MaPhong` (FK -> PhongKham).

---

## 5. Nhóm Chi Phí, Vật Tư & Hóa Đơn
19. **`SuDungNhanCong`**:
    - `(MaSuKien, MaNV)` (PK), `MaLoaiCong` (FK -> LoaiNhanCong), `VaiTro` (VARCHAR(50)), `DonGiaApDung` (NUMERIC(12,0)).
20. **`SuDungThuoc`**:
    - `(MaSuKien, MaThuoc)` (PK), `SoLuong` (INT > 0), `DonGiaApDung` (NUMERIC(12,0)).
21. **`SuDungThietBi`**:
    - `(MaSuKien, MaThietBi)` (PK), `SoLuong` (INT > 0), `DonGiaApDung` (NUMERIC(12,0)).
22. **`SuDungDichVu`**:
    - `(MaSuKien, MaDV)` (PK), `SoLuong` (INT > 0), `DonGiaApDung` (NUMERIC(12,0)).
23. **`HoaDon`** (1-1 với SuKienYTe):
    - `MaSuKien` (PK, FK -> SuKienYTe), `NgayLap` (TIMESTAMP), `TongTien` (NUMERIC(14,0)), `TrangThaiTT` ('ChuaThanhToan'|'DaThanhToan').
24. **`HoaDonChiTiet`** (Thực thể yếu):
    - `(MaSuKien, SoDong)` (PK), `MoTaKhoanMuc` (VARCHAR(200)), `SoTien` (NUMERIC(12,0)).

---

## 6. Triggers & Stored Procedures
- `sinh_ma_sukien()`: Trigger BEFORE INSERT sinh mã tự động cho SuKienYTe.
- `trg_tru_ton_kho_thuoc()`: Trigger BEFORE INSERT trên SuDungThuoc trừ TonKho trong bảng Thuoc, raise exception nếu không đủ.
- `trg_tinh_hoa_don()`: Trigger tự động cập nhật tổng tiền hóa đơn khi có khoản mục chi tiết mới.
- `sp_dong_dot_dieu_tri(p_madot, p_ngayketthuc)`: Đóng đợt điều trị, giải phóng giường bệnh về 'Trong'.
- `sp_tinh_luong_thang(p_manv, p_thang)`: Tính lương tổng hợp cho nhân sự theo tháng.
- `v_DotDieuTri_ChiTiet`: View tổng hợp thông tin đợt điều trị kèm thông tin bệnh nhân, bác sĩ, tên bệnh, số lần đã chữa.
- `v_LichSuKhamBenh`: View dòng thời gian sự kiện y tế của bệnh nhân.
