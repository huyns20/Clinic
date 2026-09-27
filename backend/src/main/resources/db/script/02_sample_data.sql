-- =====================================================================
--  02_sample_data.sql
--  Dữ liệu mẫu — Đề tài 4 (phòng khám tư nhân)
--  Trải dài 3 tháng: 06/2026 - 08/2026, đủ để test mọi chức năng.
--  CHẠY SAU KHI ĐÃ CHẠY 01_schema.sql. Xem HUONG_DAN.md để biết cách chạy
--  và ý nghĩa của từng kịch bản dữ liệu bên dưới.
-- =====================================================================

-- =====================================================================
-- 1) DANH MỤC GỐC
-- =====================================================================

INSERT INTO Khoa (MaKhoa, TenKhoa, MoTa) VALUES
('K01', 'Noi tong quat', 'Kham va dieu tri noi khoa'),
('K02', 'Ngoai chan thuong', 'Chan thuong chinh hinh, tieu phau');

INSERT INTO LoaiNhanCong (MaLoaiCong, TenLoaiCong, DonGiaMacDinh) VALUES
('LC01', 'Cong kham benh', 50000),
('LC02', 'Cong tiem', 30000),
('LC03', 'Cong tri lieu', 80000),
('LC04', 'Cong tieu phau', 300000),
('LC05', 'Cong ho tro dieu duong', 20000);

INSERT INTO BenhNhan (MaBN, HoTen, GioiTinh, NgaySinh, SDT, DiaChi, SoCCCD, NgayDangKy) VALUES
('BN01', 'Nguyen Van Nam',   'M', '1990-05-12', '0901111001', '12 Le Loi, Q1, TP.HCM',      '079090001111', '2026-01-10'),
('BN02', 'Tran Thi Hoa',     'F', '1975-08-20', '0901111002', '45 Nguyen Trai, Q5, TP.HCM', '079075002222', '2026-01-15'),
('BN03', 'Le Van Binh',      'M', '1988-02-02', '0901111003', '78 CMT8, Q3, TP.HCM',        '079088003333', '2026-02-01'),
('BN04', 'Pham Thi Lan',     'F', '1995-11-30', '0901111004', '23 Vo Van Tan, Q3, TP.HCM',  '079095004444', '2026-02-10'),
('BN05', 'Hoang Van Duc',    'M', '1982-07-07', '0901111005', '9 Ly Tu Trong, Q1, TP.HCM',  '079082005555', '2026-03-01'),
('BN06', 'Vu Thi Mai',       'F', '1993-03-18', '0901111006', '56 Pasteur, Q1, TP.HCM',     '079093006666', '2026-03-05'),
('BN07', 'Do Van Phuc',      'M', '1970-09-09', '0901111007', '11 Hai Ba Trung, Q1, TP.HCM','079070007777', '2026-03-10'),
('BN08', 'Bui Thi Thu',      'F', '2000-12-25', '0901111008', '34 Dien Bien Phu, Binh Thanh','079000008888','2026-03-12');

INSERT INTO DanhMucBenh (MaBenh, TenBenh, NhomBenh, MoTa) VALUES
('B01', 'Viem hong',        'Ho hap',        'Viem hong cap do vi khuan hoac virus'),
('B02', 'Viem da day',      'Tieu hoa',      'Viem loet niem mac da day'),
('B03', 'Gay xuong tay',    'Chan thuong',   'Gay xuong cang tay can bo bot'),
('B04', 'Cao huyet ap',     'Tim mach',      'Tang huyet ap can theo doi dai han'),
('B05', 'Viem khop',        'Co xuong khop', 'Viem khop goi man tinh');

INSERT INTO Thuoc (MaThuoc, TenThuoc, DonViTinh, DonGia, HangSX, TonKho) VALUES
('TH01', 'Paracetamol 500mg',     'Vi',  2000, 'DHG Pharma', 500),
('TH02', 'Amoxicillin 500mg',     'Vi',  3000, 'Domesco',    300),
('TH03', 'Omeprazole 20mg',       'Vi',  4000, 'Imexpharm',  200),
('TH04', 'Vitamin C 500mg',       'Vi',  1500, 'DHG Pharma', 1000),
('TH05', 'Dung dich sat trung',   'Chai',15000,'Bidiphar',   100);

INSERT INTO ThietBi (MaThietBi, TenThietBi, DonGiaSuDung) VALUES
('TB01', 'May do huyet ap',        20000),
('TB02', 'May X-quang',           150000),
('TB03', 'Bo dung cu tieu phau',  100000);

INSERT INTO DichVuYTe (MaDV, TenDV, DonGia) VALUES
('DV01', 'Xet nghiem mau',  100000),
('DV02', 'Chup X-quang',    200000),
('DV03', 'Sieu am',         150000);

INSERT INTO ThamSoHeThong (TenThamSo, GiaTri) VALUES
('LUONG_CO_SO',           2340000),
('THUONG_BS_HOAN_THANH',  1000000),
('THUONG_YTA_HOTRO',       200000);

-- =====================================================================
-- 2) NHÂN SỰ (NHANVIENYTE + BACSY/YTA) + CƠ SỞ VẬT CHẤT
-- =====================================================================

INSERT INTO NhanVienYTe (MaNV, HoTen, GioiTinh, NgaySinh, SDT, MaKhoa, HeSoLuong, NgayVaoLam, LoaiNV) VALUES
('BS01', 'BS. Nguyen Van A', 'M', '1980-01-20', '0912000001', 'K01', 2.34, '2018-01-15', 'BACSY'),
('BS02', 'BS. Tran Thi B',   'F', '1978-06-11', '0912000002', 'K01', 3.00, '2019-03-01', 'BACSY'),
('BS03', 'BS. Le Van C',     'M', '1975-09-25', '0912000003', 'K02', 3.33, '2015-06-01', 'BACSY'),
('YT01', 'YT. Pham Thi D',   'F', '1992-02-14', '0912000004', 'K01', 1.86, '2020-01-10', 'YTA'),
('YT02', 'YT. Hoang Van E',  'M', '1990-10-05', '0912000005', 'K01', 1.86, '2021-05-01', 'YTA'),
('YT03', 'YT. Vu Thi F',     'F', '1988-04-17', '0912000006', 'K02', 2.10, '2019-09-01', 'YTA');

INSERT INTO BacSy (MaBS, Email, ChuyenMon) VALUES
('BS01', 'bs.a@phongkham.vn', 'Noi tong quat'),
('BS02', 'bs.b@phongkham.vn', 'Noi tim mach'),
('BS03', 'bs.c@phongkham.vn', 'Chan thuong chinh hinh');

INSERT INTO YTa (MaYT, ChungChiHanhNghe) VALUES
('YT01', 'CCHN-001-2020'),
('YT02', 'CCHN-002-2021'),
('YT03', 'CCHN-003-2019');

INSERT INTO PhongKham (MaPhong, TenPhong, ChucNang, MaKhoa, DonGiaSuDung) VALUES
('P101', 'Phong kham Noi 1',    'Kham benh',              'K01', 50000),
('P102', 'Phong dieu tri Noi',  'Chua benh / tieu phau nho','K01', 80000),
('P201', 'Phong kham-dieu tri Ngoai', 'Kham va chua benh', 'K02', 60000);

INSERT INTO GiuongBenh (MaGiuong, MaPhong, TrangThai, DonGiaNgay) VALUES
('G101', 'P102', 'Trong', 150000),
('G102', 'P102', 'Trong', 150000);

-- =====================================================================
-- 3) KỊCH BẢN KHÁM / CHỮA BỆNH (tháng 6, 7, 8 - 2026)
--    Mỗi kịch bản có ghi chú mục đích test ở đầu khối.
-- =====================================================================

-- --------------------------------------------------------------------
-- Kịch bản 1: BN01 mắc B01 (viêm họng), điều trị dứt điểm trong 1 đợt
--   -> test: chuỗi khám (1) + chữa (nhiều), trigger tự đóng đợt khi "da khoi"
-- --------------------------------------------------------------------
INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS01-K-20260610-0001', 'BN01', 'BS01', '2026-06-10 08:30', 'KHAM');
INSERT INTO LanKham (MaSuKien, MaKhoa, TrieuChung, TienKham) VALUES
('K01-BS01-K-20260610-0001', 'K01', 'Dau hong, sot nhe, ho khan', 100000);
INSERT INTO DotDieuTri (MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, TrangThai) VALUES
('DT001', 'K01-BS01-K-20260610-0001', 'B01', 'Nhe', 2, '2026-06-10', 'DangDieuTri');

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS01-C-20260612-0001', 'BN01', 'BS01', '2026-06-12 09:00', 'CHUA');
INSERT INTO LanChuaBenh (MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong) VALUES
('K01-BS01-C-20260612-0001', 'DT001', 'Tiem', 'Giam sot, con dau nhe hong', 80000, 'P102');
INSERT INTO SuDungNhanCong (MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung) VALUES
('K01-BS01-C-20260612-0001', 'BS01', 'LC01', 'Truc tiep chua benh', 50000),
('K01-BS01-C-20260612-0001', 'YT01', 'LC02', 'Ho tro tiem',         30000);
INSERT INTO SuDungThuoc (MaSuKien, MaThuoc, SoLuong, DonGiaApDung) VALUES
('K01-BS01-C-20260612-0001', 'TH02', 10, 3000);

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS01-C-20260614-0001', 'BN01', 'BS01', '2026-06-14 09:00', 'CHUA');
INSERT INTO LanChuaBenh (MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong) VALUES
('K01-BS01-C-20260614-0001', 'DT001', 'Tri lieu', 'Da khoi hoan toan, khong con trieu chung', 50000, 'P102');
-- ^ trigger trg_dong_dot_dieu_tri se tu dong: DotDieuTri.TrangThai='DaKhoi', NgayKetThuc='2026-06-14'
INSERT INTO SuDungNhanCong (MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung) VALUES
('K01-BS01-C-20260614-0001', 'BS01', 'LC01', 'Truc tiep chua benh', 50000);
INSERT INTO SuDungThuoc (MaSuKien, MaThuoc, SoLuong, DonGiaApDung) VALUES
('K01-BS01-C-20260614-0001', 'TH01', 5, 2000);

-- --------------------------------------------------------------------
-- Kịch bản 2: BN01 mắc LẠI B01 sau khi đã khỏi (07/2026)
--   -> test: yêu cầu "mắc lại nhiều lần" + khóa ngoại đệ quy MaDotTruoc
-- --------------------------------------------------------------------
INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS01-K-20260720-0001', 'BN01', 'BS01', '2026-07-20 08:00', 'KHAM');
INSERT INTO LanKham (MaSuKien, MaKhoa, TrieuChung, TienKham) VALUES
('K01-BS01-K-20260720-0001', 'K01', 'Lai dau hong, ho', 100000);
INSERT INTO DotDieuTri (MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, TrangThai, MaDotTruoc) VALUES
('DT002', 'K01-BS01-K-20260720-0001', 'B01', 'Nhe', 1, '2026-07-20', 'DangDieuTri', 'DT001');

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS01-C-20260722-0001', 'BN01', 'BS01', '2026-07-22 09:00', 'CHUA');
INSERT INTO LanChuaBenh (MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong) VALUES
('K01-BS01-C-20260722-0001', 'DT002', 'Tiem', 'Da khoi', 70000, 'P102');
INSERT INTO SuDungNhanCong (MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung) VALUES
('K01-BS01-C-20260722-0001', 'BS01', 'LC01', 'Truc tiep chua benh', 50000);

-- --------------------------------------------------------------------
-- Kịch bản 3: BN02 mắc B04 (cao huyet ap) - dieu tri dai han, CHUA dong dot
--   -> test: doi tuong TrangThai = 'DangDieuTri' keo dai qua nhieu thang
-- --------------------------------------------------------------------
INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS02-K-20260615-0001', 'BN02', 'BS02', '2026-06-15 10:00', 'KHAM');
INSERT INTO LanKham (MaSuKien, MaKhoa, TrieuChung, TienKham) VALUES
('K01-BS02-K-20260615-0001', 'K01', 'Huyet ap 160/100, dau dau', 120000);
INSERT INTO DotDieuTri (MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, TrangThai) VALUES
('DT003', 'K01-BS02-K-20260615-0001', 'B04', 'Vua', 6, '2026-06-15', 'DangDieuTri');

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS02-C-20260701-0001', 'BN02', 'BS02', '2026-07-01 10:00', 'CHUA');
INSERT INTO LanChuaBenh (MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong) VALUES
('K01-BS02-C-20260701-0001', 'DT003', 'Tri lieu', 'Huyet ap on dinh hon, tiep tuc theo doi', 60000, 'P102');
INSERT INTO SuDungNhanCong (MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung) VALUES
('K01-BS02-C-20260701-0001', 'BS02', 'LC01', 'Truc tiep chua benh', 50000);

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS02-C-20260805-0001', 'BN02', 'BS02', '2026-08-05 10:00', 'CHUA');
INSERT INTO LanChuaBenh (MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong) VALUES
('K01-BS02-C-20260805-0001', 'DT003', 'Tri lieu', 'Van dang dieu tri, chua on dinh hoan toan', 60000, 'P102');
INSERT INTO SuDungNhanCong (MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung) VALUES
('K01-BS02-C-20260805-0001', 'BS02', 'LC01', 'Truc tiep chua benh', 50000);

-- --------------------------------------------------------------------
-- Kịch bản 4: BN03 mắc B03 (gay xuong) - noi tru co GIUONGBENH
--   -> test: DOTDIEUTRI.MaGiuong, tieu phau, thiet bi, dich vu
-- --------------------------------------------------------------------
INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K02-BS03-K-20260701-0001', 'BN03', 'BS03', '2026-07-01 14:00', 'KHAM');
INSERT INTO LanKham (MaSuKien, MaKhoa, TrieuChung, TienKham) VALUES
('K02-BS03-K-20260701-0001', 'K02', 'Gay xuong cang tay do te nga', 150000);
INSERT INTO DotDieuTri (MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, TrangThai, MaGiuong) VALUES
('DT004', 'K02-BS03-K-20260701-0001', 'B03', 'Nang', 3, '2026-07-01', 'DangDieuTri', 'G101');

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K02-BS03-C-20260701-0001', 'BN03', 'BS03', '2026-07-01 15:30', 'CHUA');
INSERT INTO LanChuaBenh (MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong) VALUES
('K02-BS03-C-20260701-0001', 'DT004', 'Tieu phau', 'Da bo bot, can theo doi noi tru', 500000, 'P201');
INSERT INTO SuDungNhanCong (MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung) VALUES
('K02-BS03-C-20260701-0001', 'BS03', 'LC04', 'Truc tiep tieu phau',   300000),
('K02-BS03-C-20260701-0001', 'YT03', 'LC05', 'Ho tro dieu duong',      20000);
INSERT INTO SuDungThietBi (MaSuKien, MaThietBi, SoLuong, DonGiaApDung) VALUES
('K02-BS03-C-20260701-0001', 'TB03', 1, 100000);
INSERT INTO SuDungDichVu (MaSuKien, MaDV, SoLuong, DonGiaApDung) VALUES
('K02-BS03-C-20260701-0001', 'DV02', 1, 200000);

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K02-BS03-C-20260715-0001', 'BN03', 'BS03', '2026-07-15 09:00', 'CHUA');
INSERT INTO LanChuaBenh (MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong) VALUES
('K02-BS03-C-20260715-0001', 'DT004', 'Tri lieu', 'Thao bot, xuong lanh tot, da khoi', 100000, 'P201');
-- ^ trigger tu dong dong DT004, NgayKetThuc = 2026-07-15
INSERT INTO SuDungNhanCong (MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung) VALUES
('K02-BS03-C-20260715-0001', 'BS03', 'LC01', 'Truc tiep chua benh', 50000);
INSERT INTO SuDungDichVu (MaSuKien, MaDV, SoLuong, DonGiaApDung) VALUES
('K02-BS03-C-20260715-0001', 'DV02', 1, 200000);

-- --------------------------------------------------------------------
-- Kịch bản 5: BN04 mắc B02 (viem da day) - kham roi chua dut diem ngay
--   -> test: dot dieu tri hoan thanh chi trong 1 lan chua duy nhat
-- --------------------------------------------------------------------
INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS01-K-20260803-0001', 'BN04', 'BS01', '2026-08-03 08:00', 'KHAM');
INSERT INTO LanKham (MaSuKien, MaKhoa, TrieuChung, TienKham) VALUES
('K01-BS01-K-20260803-0001', 'K01', 'Dau bung vung thuong vi, o chua', 100000);
INSERT INTO DotDieuTri (MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, TrangThai) VALUES
('DT005', 'K01-BS01-K-20260803-0001', 'B02', 'Nhe', 1, '2026-08-03', 'DangDieuTri');

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS01-C-20260804-0001', 'BN04', 'BS01', '2026-08-04 08:30', 'CHUA');
INSERT INTO LanChuaBenh (MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong) VALUES
('K01-BS01-C-20260804-0001', 'DT005', 'Tri lieu', 'Da khoi, khong con dau', 70000, 'P102');
INSERT INTO SuDungNhanCong (MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung) VALUES
('K01-BS01-C-20260804-0001', 'BS01', 'LC01', 'Truc tiep chua benh', 50000);
INSERT INTO SuDungThuoc (MaSuKien, MaThuoc, SoLuong, DonGiaApDung) VALUES
('K01-BS01-C-20260804-0001', 'TH03', 10, 4000);

-- --------------------------------------------------------------------
-- Kịch bản 6: cac lan kham le trong thang 8 (chua co lan chua)
--   -> test: thong ke "danh sach benh trong thang", nhieu benh nhan
--      cung mac 1 benh (B04, B01) de kiem tra sap xep giam dan
-- --------------------------------------------------------------------
INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS02-K-20260810-0001', 'BN05', 'BS02', '2026-08-10 08:00', 'KHAM');
INSERT INTO LanKham (MaSuKien, MaKhoa, TrieuChung, TienKham) VALUES
('K01-BS02-K-20260810-0001', 'K01', 'Huyet ap cao, met moi', 120000);
INSERT INTO DotDieuTri (MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, TrangThai) VALUES
('DT006', 'K01-BS02-K-20260810-0001', 'B04', 'Vua', 4, '2026-08-10', 'DangDieuTri');

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K02-BS03-K-20260812-0001', 'BN06', 'BS03', '2026-08-12 09:00', 'KHAM');
INSERT INTO LanKham (MaSuKien, MaKhoa, TrieuChung, TienKham) VALUES
('K02-BS03-K-20260812-0001', 'K02', 'Dau khop goi khi di lai', 130000);
INSERT INTO DotDieuTri (MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, TrangThai) VALUES
('DT007', 'K02-BS03-K-20260812-0001', 'B05', 'Vua', 3, '2026-08-12', 'DangDieuTri');

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS01-K-20260815-0001', 'BN07', 'BS01', '2026-08-15 08:00', 'KHAM');
INSERT INTO LanKham (MaSuKien, MaKhoa, TrieuChung, TienKham) VALUES
('K01-BS01-K-20260815-0001', 'K01', 'Dau hong, ho co dom', 100000);
INSERT INTO DotDieuTri (MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, TrangThai) VALUES
('DT008', 'K01-BS01-K-20260815-0001', 'B01', 'Nhe', 2, '2026-08-15', 'DangDieuTri');

INSERT INTO SuKienYTe (MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) VALUES
('K01-BS02-K-20260818-0001', 'BN08', 'BS02', '2026-08-18 09:30', 'KHAM');
INSERT INTO LanKham (MaSuKien, MaKhoa, TrieuChung, TienKham) VALUES
('K01-BS02-K-20260818-0001', 'K01', 'Dau thuong vi sau an', 100000);
INSERT INTO DotDieuTri (MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, TrangThai) VALUES
('DT009', 'K01-BS02-K-20260818-0001', 'B02', 'Nhe', 1, '2026-08-18', 'DangDieuTri');

-- =====================================================================
-- 4) HÓA ĐƠN mẫu (minh họa — các sự kiện còn lại có thể xuất hóa đơn
--    bằng cách gọi CALL sp_XuatHoaDon('<MaSuKien>'); xem HUONG_DAN.md)
-- =====================================================================

INSERT INTO HoaDon (MaSuKien, NgayLap, TongTien, TrangThaiTT) VALUES
('K01-BS01-C-20260614-0001', '2026-06-14 09:30', 0, 'ChuaThanhToan');
INSERT INTO HoaDonChiTiet (MaSuKien, SoDong, MoTaKhoanMuc, SoTien) VALUES
('K01-BS01-C-20260614-0001', 1, 'Tien chua benh', 50000),
('K01-BS01-C-20260614-0001', 2, 'Thuoc: Paracetamol 500mg', 10000);
-- ^ trigger trg_capnhat_tong_tien se tu tinh TongTien = 60000

INSERT INTO HoaDon (MaSuKien, NgayLap, TongTien, TrangThaiTT) VALUES
('K02-BS03-C-20260715-0001', '2026-07-15 09:30', 0, 'ChuaThanhToan');
INSERT INTO HoaDonChiTiet (MaSuKien, SoDong, MoTaKhoanMuc, SoTien) VALUES
('K02-BS03-C-20260715-0001', 1, 'Tien chua benh', 100000),
('K02-BS03-C-20260715-0001', 2, 'Dich vu: Chup X-quang kiem tra', 200000);
-- ^ TongTien se tu tinh = 300000

-- =====================================================================
-- 5) LƯƠNG mẫu tháng 06/2026 (đối chiếu được với fn_TinhLuongBacSy)
-- =====================================================================

-- BS01: HeSoLuong 2.34 * 2,340,000 = 5,475,600 co ban
--       + 1 dot DaKhoi trong thang 6 (DT001, dong 2026-06-14) => thuong 1,000,000
INSERT INTO Luong (MaLuong, MaNV, Thang, NgayNhanLuong, LuongCoBan, TienThuong, TongLuong, GhiChu) VALUES
('LG-BS01-202606', 'BS01', '2026-06-01', '2026-07-05', 5475600, 1000000, 6475600, 'Luong thang 6/2026');

-- YT01: HeSoLuong 1.86 * 2,340,000 = 4,352,400 co ban
--       + 1 lan ho tro trong thang 6 (K01-BS01-C-20260612-0001) => thuong 200,000
INSERT INTO Luong (MaLuong, MaNV, Thang, NgayNhanLuong, LuongCoBan, TienThuong, TongLuong, GhiChu) VALUES
('LG-YT01-202606', 'YT01', '2026-06-01', '2026-07-05', 4352400, 200000, 4552400, 'Luong thang 6/2026');

-- =====================================================================
-- HẾT FILE 02_sample_data.sql
-- =====================================================================
