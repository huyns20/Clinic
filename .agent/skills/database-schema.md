# Database Schema Reference

## Connection Info
- Host: localhost:5432
- Database: phongkham_db
- User: postgres
- Password: 123456
- Docker container: phongkham_postgres

## Tables (24 total)

### Group 1: Danh mục gốc (8 tables)
| Table | PK | Description |
|-------|-----|-------------|
| Khoa | MaKhoa | Departments/Specialties |
| LoaiNhanCong | MaLoaiCong | Labor type catalog |
| BenhNhan | MaBN | Patients |
| DanhMucBenh | MaBenh | Disease catalog |
| Thuoc | MaThuoc | Medicine inventory |
| ThietBi | MaThietBi | Medical equipment |
| DichVuYTe | MaDV | Medical services |
| ThamSoHeThong | TenThamSo | System parameters |

### Group 2: Nhân sự (4 tables)
| Table | PK | Description |
|-------|-----|-------------|
| NhanVienYTe | MaNV | Medical staff (ISA parent) |
| BacSy | MaBS | Doctors (ISA child) |
| YTa | MaYT | Nurses (ISA child) |
| Luong | MaLuong | Monthly salary |

### Group 3: Cơ sở vật chất (2 tables)
| Table | PK | Description |
|-------|-----|-------------|
| PhongKham | MaPhong | Examination rooms |
| GiuongBenh | MaGiuong | Hospital beds |

### Group 4: Sự kiện y tế (4 tables)
| Table | PK | Description |
|-------|-----|-------------|
| SuKienYTe | MaSuKien | Medical events (ISA parent) |
| LanKham | MaSuKien | Examinations (ISA child) |
| DotDieuTri | MaDotDieuTri | Treatment courses |
| LanChuaBenh | MaSuKien | Treatments (ISA child) |

### Group 5: N-N & Hóa đơn (6 tables)
| Table | PK | Description |
|-------|-----|-------------|
| SuDungNhanCong | (MaSuKien, MaNV) | Labor usage |
| SuDungThuoc | (MaSuKien, MaThuoc) | Medicine usage |
| SuDungThietBi | (MaSuKien, MaThietBi) | Equipment usage |
| SuDungDichVu | (MaSuKien, MaDV) | Service usage |
| HoaDon | MaSuKien | Invoices |
| HoaDonChiTiet | (MaSuKien, SoDong) | Invoice details |

## Triggers (5)
1. trg_sinh_ma_sukien - Auto-generate medical event ID
2. trg_kiemtra_bacsy - Validate doctor assignment
3. trg_dong_dot_dieu_tri - Auto-close treatment when healed
4. trg_tru_ton_kho - Auto-deduct medicine inventory
5. trg_capnhat_tong_tien - Auto-update invoice total

## Stored Procedures (6)
1. sp_TiepNhanKham - Receive patient for examination
2. sp_GhiNhanChuaBenh - Record treatment session
3. sp_XuatHoaDon - Generate invoice
4. sp_NhapKhoThuoc - Import medicine stock
5. sp_HuyLanKham - Cancel examination
6. sp_TraLuong - Process salary payment

## Views (2)
1. v_DotDieuTri_ChiTiet - Treatment course detail (joins back patient/doctor)
2. v_DoanhThu_TheoNgay - Daily revenue
