-- =====================================================================
--  01_schema.sql
--  Đề tài 4 — Hệ CSDL quản lý phòng khám bệnh tư nhân
--  PostgreSQL 12+ (dùng CREATE PROCEDURE, ON CONFLICT — cần PG 11+)
--
--  CÁCH CHẠY (xem chi tiết trong HUONG_DAN.md):
--    1) Tạo & kết nối CSDL (chạy 2 dòng dưới đây RIÊNG, ngoài file này,
--       vì CREATE DATABASE không được phép nằm trong 1 script nhiều lệnh):
--
--         CREATE DATABASE phongkham_db;
--         \c phongkham_db
--
--    2) Sau khi đã kết nối vào phongkham_db, chạy toàn bộ file này:
--         psql -U <user> -d phongkham_db -f 01_schema.sql
-- =====================================================================

-- =====================================================================
-- PHẦN 1: DANH MỤC GỐC (không phụ thuộc bảng nào khác)
-- =====================================================================

CREATE TABLE Khoa (
    MaKhoa      VARCHAR(10) PRIMARY KEY,
    TenKhoa     VARCHAR(100) NOT NULL,
    MoTa        TEXT
);

CREATE TABLE LoaiNhanCong (
    MaLoaiCong    VARCHAR(10) PRIMARY KEY,
    TenLoaiCong   VARCHAR(100) NOT NULL,
    DonGiaMacDinh NUMERIC(12,0) NOT NULL DEFAULT 0
);

CREATE TABLE BenhNhan (
    MaBN        VARCHAR(10) PRIMARY KEY,
    HoTen       VARCHAR(100) NOT NULL,
    GioiTinh    CHAR(1) CHECK (GioiTinh IN ('M','F')),
    NgaySinh    DATE,
    SDT         VARCHAR(15),
    DiaChi      VARCHAR(200),
    SoCCCD      VARCHAR(20) UNIQUE,
    NgayDangKy  DATE NOT NULL DEFAULT CURRENT_DATE
);

CREATE TABLE DanhMucBenh (
    MaBenh      VARCHAR(10) PRIMARY KEY,
    TenBenh     VARCHAR(150) NOT NULL,
    NhomBenh    VARCHAR(50),
    MoTa        TEXT
);

CREATE TABLE Thuoc (
    MaThuoc     VARCHAR(10) PRIMARY KEY,
    TenThuoc    VARCHAR(150) NOT NULL,
    DonViTinh   VARCHAR(20),
    DonGia      NUMERIC(12,0) NOT NULL CHECK (DonGia >= 0),
    HangSX      VARCHAR(100),
    TonKho      INT DEFAULT 0 CHECK (TonKho >= 0)
);

CREATE TABLE ThietBi (
    MaThietBi    VARCHAR(10) PRIMARY KEY,
    TenThietBi   VARCHAR(150) NOT NULL,
    DonGiaSuDung NUMERIC(12,0) NOT NULL CHECK (DonGiaSuDung >= 0)
);

CREATE TABLE DichVuYTe (
    MaDV        VARCHAR(10) PRIMARY KEY,
    TenDV       VARCHAR(150) NOT NULL,
    DonGia      NUMERIC(12,0) NOT NULL CHECK (DonGia >= 0)
);

CREATE TABLE ThamSoHeThong (
    TenThamSo   VARCHAR(50) PRIMARY KEY,
    GiaTri      NUMERIC(14,0) NOT NULL
);

-- =====================================================================
-- PHẦN 2: NHÂN SỰ (NHANVIENYTE là lớp cha ISA của BACSY / YTA) + LƯƠNG
-- =====================================================================

CREATE TABLE NhanVienYTe (
    MaNV        VARCHAR(10) PRIMARY KEY,
    HoTen       VARCHAR(100) NOT NULL,
    GioiTinh    CHAR(1) CHECK (GioiTinh IN ('M','F')),
    NgaySinh    DATE,
    SDT         VARCHAR(15),
    MaKhoa      VARCHAR(10) NOT NULL REFERENCES Khoa(MaKhoa),
    HeSoLuong   NUMERIC(4,2) NOT NULL CHECK (HeSoLuong > 0),
    NgayVaoLam  DATE NOT NULL,
    LoaiNV      VARCHAR(10) NOT NULL CHECK (LoaiNV IN ('BACSY','YTA'))
);

CREATE TABLE BacSy (
    MaBS        VARCHAR(10) PRIMARY KEY REFERENCES NhanVienYTe(MaNV),
    Email       VARCHAR(100),
    ChuyenMon   VARCHAR(100)
);

CREATE TABLE YTa (
    MaYT        VARCHAR(10) PRIMARY KEY REFERENCES NhanVienYTe(MaNV),
    ChungChiHanhNghe VARCHAR(100)
);

CREATE TABLE Luong (
    MaLuong       VARCHAR(20) PRIMARY KEY,
    MaNV          VARCHAR(10) NOT NULL REFERENCES NhanVienYTe(MaNV),
    Thang         DATE NOT NULL,
    NgayNhanLuong DATE,
    LuongCoBan    NUMERIC(12,0) NOT NULL,
    TienThuong    NUMERIC(12,0) DEFAULT 0,
    TongLuong     NUMERIC(12,0) NOT NULL,
    GhiChu        TEXT,
    UNIQUE (MaNV, Thang)
);

-- =====================================================================
-- PHẦN 3: CƠ SỞ VẬT CHẤT (PHÒNG KHÁM, GIƯỜNG BỆNH)
-- =====================================================================

CREATE TABLE PhongKham (
    MaPhong      VARCHAR(10) PRIMARY KEY,
    TenPhong     VARCHAR(100) NOT NULL,
    ChucNang     VARCHAR(100),
    MaKhoa       VARCHAR(10) NOT NULL REFERENCES Khoa(MaKhoa),
    DonGiaSuDung NUMERIC(12,0) NOT NULL DEFAULT 0
);

CREATE TABLE GiuongBenh (
    MaGiuong    VARCHAR(10) PRIMARY KEY,
    MaPhong     VARCHAR(10) NOT NULL REFERENCES PhongKham(MaPhong),
    TrangThai   VARCHAR(20) DEFAULT 'Trong',
    DonGiaNgay  NUMERIC(12,0) NOT NULL DEFAULT 0
);

-- =====================================================================
-- PHẦN 4: SỰ KIỆN Y TẾ (SUKIENYTE là lớp cha ISA của LANKHAM / LANCHUABENH)
--         + ĐỢT ĐIỀU TRỊ (thực thể kết hợp, đã chuẩn hóa BCNF)
-- =====================================================================

CREATE SEQUENCE seq_sukien START 1;

CREATE TABLE SuKienYTe (
    MaSuKien    VARCHAR(50) PRIMARY KEY,
    MaBN        VARCHAR(10) NOT NULL REFERENCES BenhNhan(MaBN),
    MaBS        VARCHAR(10) NOT NULL REFERENCES BacSy(MaBS),
    ThoiGian    TIMESTAMP NOT NULL,
    LoaiSuKien  VARCHAR(10) NOT NULL CHECK (LoaiSuKien IN ('KHAM','CHUA'))
);

CREATE TABLE LanKham (
    MaSuKien    VARCHAR(50) PRIMARY KEY REFERENCES SuKienYTe(MaSuKien),
    MaKhoa      VARCHAR(10) NOT NULL REFERENCES Khoa(MaKhoa),
    TrieuChung  TEXT,
    TienKham    NUMERIC(12,0) NOT NULL DEFAULT 0
);

-- DOTDIEUTRI đã chuẩn hóa BCNF: KHÔNG lưu MaBN, MaBS trực tiếp
-- (suy dẫn qua MaSuKienKham -> LanKham -> SuKienYTe, xem view v_DotDieuTri_ChiTiet)
CREATE TABLE DotDieuTri (
    MaDotDieuTri  VARCHAR(50) PRIMARY KEY,
    MaSuKienKham  VARCHAR(50) NOT NULL REFERENCES LanKham(MaSuKien),
    MaBenh        VARCHAR(10) NOT NULL REFERENCES DanhMucBenh(MaBenh),
    MucDoNang     VARCHAR(10) CHECK (MucDoNang IN ('Nhe','Vua','Nang')),
    SoLanChuaDuKien INT,
    NgayBatDau    DATE NOT NULL,
    NgayKetThuc   DATE,
    TrangThai     VARCHAR(20) NOT NULL DEFAULT 'DangDieuTri'
                  CHECK (TrangThai IN ('DangDieuTri','DaKhoi')),
    MaGiuong      VARCHAR(10) REFERENCES GiuongBenh(MaGiuong),
    MaDotTruoc    VARCHAR(50) REFERENCES DotDieuTri(MaDotDieuTri),
    CHECK (NgayKetThuc IS NULL OR NgayKetThuc >= NgayBatDau)
);

CREATE TABLE LanChuaBenh (
    MaSuKien     VARCHAR(50) PRIMARY KEY REFERENCES SuKienYTe(MaSuKien),
    MaDotDieuTri VARCHAR(50) NOT NULL REFERENCES DotDieuTri(MaDotDieuTri),
    HinhThucChua VARCHAR(30) NOT NULL,
    KetLuan      TEXT,
    TienChua     NUMERIC(12,0) NOT NULL DEFAULT 0,
    MaPhong      VARCHAR(10) NOT NULL REFERENCES PhongKham(MaPhong)
);

-- =====================================================================
-- PHẦN 5: SỬ DỤNG NHÂN CÔNG / THUỐC / THIẾT BỊ / DỊCH VỤ (N-N)
-- =====================================================================

CREATE TABLE SuDungNhanCong (
    MaSuKien     VARCHAR(50) REFERENCES SuKienYTe(MaSuKien),
    MaNV         VARCHAR(10) REFERENCES NhanVienYTe(MaNV),
    MaLoaiCong   VARCHAR(10) NOT NULL REFERENCES LoaiNhanCong(MaLoaiCong),
    VaiTro       VARCHAR(50),
    DonGiaApDung NUMERIC(12,0) NOT NULL,
    PRIMARY KEY (MaSuKien, MaNV)
);

CREATE TABLE SuDungThuoc (
    MaSuKien     VARCHAR(50) REFERENCES SuKienYTe(MaSuKien),
    MaThuoc      VARCHAR(10) REFERENCES Thuoc(MaThuoc),
    SoLuong      INT NOT NULL CHECK (SoLuong > 0),
    DonGiaApDung NUMERIC(12,0) NOT NULL,
    PRIMARY KEY (MaSuKien, MaThuoc)
);

CREATE TABLE SuDungThietBi (
    MaSuKien     VARCHAR(50) REFERENCES SuKienYTe(MaSuKien),
    MaThietBi    VARCHAR(10) REFERENCES ThietBi(MaThietBi),
    SoLuong      INT NOT NULL CHECK (SoLuong > 0) DEFAULT 1,
    DonGiaApDung NUMERIC(12,0) NOT NULL,
    PRIMARY KEY (MaSuKien, MaThietBi)
);

CREATE TABLE SuDungDichVu (
    MaSuKien     VARCHAR(50) REFERENCES SuKienYTe(MaSuKien),
    MaDV         VARCHAR(10) REFERENCES DichVuYTe(MaDV),
    SoLuong      INT NOT NULL CHECK (SoLuong > 0) DEFAULT 1,
    DonGiaApDung NUMERIC(12,0) NOT NULL,
    PRIMARY KEY (MaSuKien, MaDV)
);

-- =====================================================================
-- PHẦN 6: HÓA ĐƠN (HOADON 1-1 với SUKIENYTE, HOADONCHITIET là thực thể yếu)
-- =====================================================================

CREATE TABLE HoaDon (
    MaSuKien    VARCHAR(50) PRIMARY KEY REFERENCES SuKienYTe(MaSuKien),
    NgayLap     TIMESTAMP NOT NULL DEFAULT now(),
    TongTien    NUMERIC(14,0) NOT NULL DEFAULT 0,
    TrangThaiTT VARCHAR(20) DEFAULT 'ChuaThanhToan'
);

CREATE TABLE HoaDonChiTiet (
    MaSuKien     VARCHAR(50) REFERENCES HoaDon(MaSuKien),
    SoDong       INT NOT NULL,
    MoTaKhoanMuc VARCHAR(200),
    SoTien       NUMERIC(12,0) NOT NULL,
    PRIMARY KEY (MaSuKien, SoDong)
);

-- =====================================================================
-- PHẦN 7: TRIGGER (5 trigger bắt buộc)
-- =====================================================================

-- 7.1 Sinh mã sự kiện y tế tự động: <MaKhoa>-<MaBS>-<K/C>-<YYYYMMDD>-<STT>
--     Chỉ chạy khi INSERT để MaSuKien = NULL (nếu tự cung cấp mã thì giữ nguyên,
--     đây là cách dữ liệu mẫu ở file 02 dùng mã cố định để dễ tham chiếu).
CREATE OR REPLACE FUNCTION sinh_ma_sukien() RETURNS TRIGGER AS $$
DECLARE v_MaKhoa VARCHAR(10); v_stt INT;
BEGIN
  SELECT MaKhoa INTO v_MaKhoa FROM NhanVienYTe WHERE MaNV = NEW.MaBS;
  v_stt := nextval('seq_sukien');
  NEW.MaSuKien := v_MaKhoa || '-' || NEW.MaBS || '-' ||
                  CASE NEW.LoaiSuKien WHEN 'KHAM' THEN 'K' ELSE 'C' END || '-' ||
                  to_char(NEW.ThoiGian,'YYYYMMDD') || '-' || lpad(v_stt::text,4,'0');
  RETURN NEW;
END; $$ LANGUAGE plpgsql;

CREATE TRIGGER trg_sinh_ma_sukien
BEFORE INSERT ON SuKienYTe
FOR EACH ROW WHEN (NEW.MaSuKien IS NULL)
EXECUTE FUNCTION sinh_ma_sukien();

-- 7.2 Ràng buộc "một bác sỹ duy nhất" cho toàn bộ đợt điều trị
CREATE OR REPLACE FUNCTION kiemtra_bacsy_dotdieutri() RETURNS TRIGGER AS $$
DECLARE v_MaBS_ChuaBenh VARCHAR(10);
        v_MaBS_PhuTrach VARCHAR(10);
BEGIN
  SELECT MaBS INTO v_MaBS_ChuaBenh FROM SuKienYTe WHERE MaSuKien = NEW.MaSuKien;

  SELECT se.MaBS INTO v_MaBS_PhuTrach
  FROM DotDieuTri dt
  JOIN SuKienYTe se ON se.MaSuKien = dt.MaSuKienKham
  WHERE dt.MaDotDieuTri = NEW.MaDotDieuTri;

  IF v_MaBS_ChuaBenh <> v_MaBS_PhuTrach THEN
     RAISE EXCEPTION 'Lan chua benh (%): phai do dung bac sy phu trach (%) thuc hien, khong phai %',
       NEW.MaSuKien, v_MaBS_PhuTrach, v_MaBS_ChuaBenh;
  END IF;
  RETURN NEW;
END; $$ LANGUAGE plpgsql;

CREATE TRIGGER trg_kiemtra_bacsy
BEFORE INSERT OR UPDATE ON LanChuaBenh
FOR EACH ROW EXECUTE FUNCTION kiemtra_bacsy_dotdieutri();

-- 7.3 Tự động đóng đợt điều trị khi có kết luận "đã khỏi"
CREATE OR REPLACE FUNCTION dong_dot_dieu_tri() RETURNS TRIGGER AS $$
BEGIN
  IF NEW.KetLuan ILIKE '%khoi%' THEN
     UPDATE DotDieuTri
     SET TrangThai = 'DaKhoi',
         NgayKetThuc = (SELECT ThoiGian::date FROM SuKienYTe WHERE MaSuKien = NEW.MaSuKien)
     WHERE MaDotDieuTri = NEW.MaDotDieuTri;
  END IF;
  RETURN NEW;
END; $$ LANGUAGE plpgsql;

CREATE TRIGGER trg_dong_dot_dieu_tri
AFTER INSERT OR UPDATE ON LanChuaBenh
FOR EACH ROW EXECUTE FUNCTION dong_dot_dieu_tri();

-- 7.4 Kiểm soát tồn kho thuốc khi kê đơn
CREATE OR REPLACE FUNCTION tru_ton_kho_thuoc() RETURNS TRIGGER AS $$
DECLARE v_TonKho INT;
BEGIN
  SELECT TonKho INTO v_TonKho FROM Thuoc WHERE MaThuoc = NEW.MaThuoc FOR UPDATE;
  IF v_TonKho < NEW.SoLuong THEN
     RAISE EXCEPTION 'Thuoc % khong du ton kho (con %, can %)', NEW.MaThuoc, v_TonKho, NEW.SoLuong;
  END IF;
  UPDATE Thuoc SET TonKho = TonKho - NEW.SoLuong WHERE MaThuoc = NEW.MaThuoc;
  RETURN NEW;
END; $$ LANGUAGE plpgsql;

CREATE TRIGGER trg_tru_ton_kho
BEFORE INSERT ON SuDungThuoc
FOR EACH ROW EXECUTE FUNCTION tru_ton_kho_thuoc();

-- 7.5 Tự động cập nhật tổng tiền hóa đơn khi chi tiết hóa đơn thay đổi
CREATE OR REPLACE FUNCTION capnhat_tong_tien_hoadon() RETURNS TRIGGER AS $$
DECLARE v_MaSuKien VARCHAR(50);
BEGIN
  v_MaSuKien := COALESCE(NEW.MaSuKien, OLD.MaSuKien);
  UPDATE HoaDon
  SET TongTien = (SELECT COALESCE(SUM(SoTien),0) FROM HoaDonChiTiet WHERE MaSuKien = v_MaSuKien)
  WHERE MaSuKien = v_MaSuKien;
  RETURN NULL;
END; $$ LANGUAGE plpgsql;

CREATE TRIGGER trg_capnhat_tong_tien
AFTER INSERT OR UPDATE OR DELETE ON HoaDonChiTiet
FOR EACH ROW EXECUTE FUNCTION capnhat_tong_tien_hoadon();

-- =====================================================================
-- PHẦN 8: FUNCTION
-- =====================================================================

-- 8.1 Tìm đợt điều trị đang mở của 1 bệnh nhân cho 1 bệnh cụ thể
CREATE OR REPLACE FUNCTION fn_DotDangDieuTri(p_MaBN VARCHAR, p_MaBenh VARCHAR)
RETURNS VARCHAR AS $$
DECLARE v_MaDot VARCHAR(50);
BEGIN
  SELECT dt.MaDotDieuTri INTO v_MaDot
  FROM DotDieuTri dt
  JOIN SuKienYTe se ON se.MaSuKien = dt.MaSuKienKham
  WHERE se.MaBN = p_MaBN AND dt.MaBenh = p_MaBenh AND dt.TrangThai = 'DangDieuTri'
  LIMIT 1;
  RETURN v_MaDot;
END; $$ LANGUAGE plpgsql;

-- 8.2 Tính lương 1 bác sỹ trong 1 tháng cụ thể
CREATE OR REPLACE FUNCTION fn_TinhLuongBacSy(p_MaBS VARCHAR, p_Thang DATE)
RETURNS NUMERIC AS $$
DECLARE v_LuongCoBan NUMERIC; v_SoDot INT; v_DonGiaThuong NUMERIC;
BEGIN
  SELECT nv.HeSoLuong * ts.GiaTri INTO v_LuongCoBan
  FROM NhanVienYTe nv, ThamSoHeThong ts
  WHERE nv.MaNV = p_MaBS AND ts.TenThamSo = 'LUONG_CO_SO';

  SELECT COUNT(*) INTO v_SoDot
  FROM DotDieuTri dt
  JOIN SuKienYTe se ON se.MaSuKien = dt.MaSuKienKham
  WHERE se.MaBS = p_MaBS AND dt.TrangThai = 'DaKhoi'
    AND date_trunc('month', dt.NgayKetThuc) = date_trunc('month', p_Thang);

  SELECT GiaTri INTO v_DonGiaThuong FROM ThamSoHeThong WHERE TenThamSo='THUONG_BS_HOAN_THANH';

  RETURN v_LuongCoBan + COALESCE(v_SoDot,0) * v_DonGiaThuong;
END; $$ LANGUAGE plpgsql;

-- =====================================================================
-- PHẦN 9: VIEW
-- =====================================================================

-- 9.1 Bù lại việc đã bỏ MaBN, MaBS khỏi DOTDIEUTRI khi chuẩn hóa BCNF
CREATE OR REPLACE VIEW v_DotDieuTri_ChiTiet AS
SELECT dt.MaDotDieuTri, se.MaBN, bn.HoTen AS TenBenhNhan,
       se.MaBS, nv.HoTen AS TenBacSy,
       dt.MaBenh, db.TenBenh, dt.MucDoNang, dt.NgayBatDau, dt.NgayKetThuc, dt.TrangThai
FROM DotDieuTri dt
JOIN LanKham lk ON lk.MaSuKien = dt.MaSuKienKham
JOIN SuKienYTe se ON se.MaSuKien = lk.MaSuKien
JOIN BenhNhan bn ON bn.MaBN = se.MaBN
JOIN NhanVienYTe nv ON nv.MaNV = se.MaBS
JOIN DanhMucBenh db ON db.MaBenh = dt.MaBenh;

-- 9.2 Doanh thu khám/chữa theo ngày
CREATE OR REPLACE VIEW v_DoanhThu_TheoNgay AS
SELECT se.ThoiGian::date AS Ngay,
       SUM(COALESCE(lk.TienKham,0) + COALESCE(lc.TienChua,0)) AS TienKhamChua
FROM SuKienYTe se
LEFT JOIN LanKham lk ON lk.MaSuKien = se.MaSuKien
LEFT JOIN LanChuaBenh lc ON lc.MaSuKien = se.MaSuKien
GROUP BY se.ThoiGian::date;

-- =====================================================================
-- PHẦN 10: STORED PROCEDURE (Transaction — tối thiểu 5 bắt buộc)
--   Lưu ý: PROCEDURE có COMMIT/ROLLBACK bên trong CHỈ gọi được bằng CALL
--   ở mức top-level (không được gọi trong 1 transaction/BEGIN đang mở,
--   không gọi từ trong FUNCTION). Xem HUONG_DAN.md.
-- =====================================================================

-- 10.1 Tiếp nhận bệnh nhân đến khám (T1)
CREATE OR REPLACE PROCEDURE sp_TiepNhanKham(
    p_MaBN VARCHAR, p_MaBS VARCHAR, p_ThoiGian TIMESTAMP,
    p_TrieuChung TEXT, p_TienKham NUMERIC, p_MaKhoa VARCHAR,
    p_MaBenh VARCHAR, p_MucDo VARCHAR, p_SoLanDuKien INT
)
LANGUAGE plpgsql AS $$
DECLARE v_MaSuKien VARCHAR(50); v_MaDotMo VARCHAR(50);
BEGIN
    INSERT INTO SuKienYTe(MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien)
    VALUES (NULL, p_MaBN, p_MaBS, p_ThoiGian, 'KHAM')
    RETURNING MaSuKien INTO v_MaSuKien;

    INSERT INTO LanKham(MaSuKien, MaKhoa, TrieuChung, TienKham)
    VALUES (v_MaSuKien, p_MaKhoa, p_TrieuChung, p_TienKham);

    v_MaDotMo := fn_DotDangDieuTri(p_MaBN, p_MaBenh);

    IF v_MaDotMo IS NULL THEN
        INSERT INTO DotDieuTri(MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang,
                                SoLanChuaDuKien, NgayBatDau, TrangThai)
        VALUES ('DT-' || v_MaSuKien, v_MaSuKien, p_MaBenh, p_MucDo,
                p_SoLanDuKien, p_ThoiGian::date, 'DangDieuTri');
    END IF;

    COMMIT;
END; $$;

-- 10.2 Ghi nhận một lần chữa bệnh, kèm phòng, y tá hỗ trợ, thuốc, nhân công (T2)
CREATE OR REPLACE PROCEDURE sp_GhiNhanChuaBenh(
    p_MaDotDieuTri VARCHAR, p_ThoiGian TIMESTAMP, p_MaPhong VARCHAR,
    p_HinhThucChua VARCHAR, p_KetLuan TEXT, p_TienChua NUMERIC,
    p_MaThuoc VARCHAR, p_SoLuongThuoc INT, p_DonGiaThuoc NUMERIC,
    p_MaLoaiCongBS VARCHAR, p_DonGiaCongBS NUMERIC,
    p_MaYTa VARCHAR, p_MaLoaiCongYT VARCHAR, p_DonGiaCongYT NUMERIC
)
LANGUAGE plpgsql AS $$
DECLARE v_MaBS VARCHAR(10); v_MaBN VARCHAR(10); v_MaSuKien VARCHAR(50);
BEGIN
    SELECT se.MaBS, se.MaBN INTO v_MaBS, v_MaBN
    FROM DotDieuTri dt JOIN SuKienYTe se ON se.MaSuKien = dt.MaSuKienKham
    WHERE dt.MaDotDieuTri = p_MaDotDieuTri;

    INSERT INTO SuKienYTe(MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien)
    VALUES (NULL, v_MaBN, v_MaBS, p_ThoiGian, 'CHUA')
    RETURNING MaSuKien INTO v_MaSuKien;

    INSERT INTO LanChuaBenh(MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong)
    VALUES (v_MaSuKien, p_MaDotDieuTri, p_HinhThucChua, p_KetLuan, p_TienChua, p_MaPhong);

    INSERT INTO SuDungNhanCong(MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung)
    VALUES (v_MaSuKien, v_MaBS, p_MaLoaiCongBS, 'Truc tiep chua benh', p_DonGiaCongBS);

    IF p_MaYTa IS NOT NULL THEN
        INSERT INTO SuDungNhanCong(MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung)
        VALUES (v_MaSuKien, p_MaYTa, p_MaLoaiCongYT, 'Ho tro chua benh', p_DonGiaCongYT);
    END IF;

    IF p_MaThuoc IS NOT NULL THEN
        INSERT INTO SuDungThuoc(MaSuKien, MaThuoc, SoLuong, DonGiaApDung)
        VALUES (v_MaSuKien, p_MaThuoc, p_SoLuongThuoc, p_DonGiaThuoc);
    END IF;

    COMMIT;
END; $$;

-- 10.3 Xuất hóa đơn tổng hợp cho một sự kiện y tế (T3)
CREATE OR REPLACE PROCEDURE sp_XuatHoaDon(p_MaSuKien VARCHAR)
LANGUAGE plpgsql AS $$
DECLARE v_TienKham NUMERIC := 0; v_TienChua NUMERIC := 0; v_TienPhong NUMERIC := 0; v_STT INT := 0;
BEGIN
    INSERT INTO HoaDon(MaSuKien, NgayLap, TongTien, TrangThaiTT)
    VALUES (p_MaSuKien, now(), 0, 'ChuaThanhToan')
    ON CONFLICT (MaSuKien) DO NOTHING;

    SELECT COALESCE(TienKham,0) INTO v_TienKham FROM LanKham WHERE MaSuKien = p_MaSuKien;
    SELECT COALESCE(lc.TienChua,0), COALESCE(pk.DonGiaSuDung,0)
      INTO v_TienChua, v_TienPhong
    FROM LanChuaBenh lc LEFT JOIN PhongKham pk ON pk.MaPhong = lc.MaPhong
    WHERE lc.MaSuKien = p_MaSuKien;

    IF v_TienKham > 0 THEN
        v_STT := v_STT + 1;
        INSERT INTO HoaDonChiTiet VALUES (p_MaSuKien, v_STT, 'Tien kham', v_TienKham);
    END IF;
    IF v_TienChua > 0 THEN
        v_STT := v_STT + 1;
        INSERT INTO HoaDonChiTiet VALUES (p_MaSuKien, v_STT, 'Tien chua benh', v_TienChua);
    END IF;
    IF v_TienPhong > 0 THEN
        v_STT := v_STT + 1;
        INSERT INTO HoaDonChiTiet VALUES (p_MaSuKien, v_STT, 'Tien su dung phong', v_TienPhong);
    END IF;

    INSERT INTO HoaDonChiTiet(MaSuKien, SoDong, MoTaKhoanMuc, SoTien)
    SELECT p_MaSuKien, v_STT + row_number() OVER (), 'Thuoc: ' || t.TenThuoc, sd.SoLuong*sd.DonGiaApDung
    FROM SuDungThuoc sd JOIN Thuoc t ON t.MaThuoc = sd.MaThuoc
    WHERE sd.MaSuKien = p_MaSuKien;

    INSERT INTO HoaDonChiTiet(MaSuKien, SoDong, MoTaKhoanMuc, SoTien)
    SELECT p_MaSuKien, v_STT + 100 + row_number() OVER (), 'Cong: ' || lnc.TenLoaiCong, snc.DonGiaApDung
    FROM SuDungNhanCong snc JOIN LoaiNhanCong lnc ON lnc.MaLoaiCong = snc.MaLoaiCong
    WHERE snc.MaSuKien = p_MaSuKien;

    COMMIT;
END; $$;

-- 10.4 Nhập thêm thuốc vào kho (T4)
CREATE OR REPLACE PROCEDURE sp_NhapKhoThuoc(p_MaThuoc VARCHAR, p_SoLuong INT, p_DonGiaMoi NUMERIC)
LANGUAGE plpgsql AS $$
BEGIN
    IF p_SoLuong <= 0 THEN
        RAISE EXCEPTION 'So luong nhap kho phai > 0';
    END IF;

    UPDATE Thuoc
    SET TonKho = TonKho + p_SoLuong,
        DonGia = COALESCE(p_DonGiaMoi, DonGia)
    WHERE MaThuoc = p_MaThuoc;

    IF NOT FOUND THEN
        ROLLBACK;
        RAISE EXCEPTION 'Khong tim thay thuoc %', p_MaThuoc;
    END IF;

    COMMIT;
END; $$;

-- 10.5 Hủy một lần khám nhầm — minh họa ROLLBACK tường minh (T5)
CREATE OR REPLACE PROCEDURE sp_HuyLanKham(p_MaSuKien VARCHAR)
LANGUAGE plpgsql AS $$
DECLARE v_SoDotLienQuan INT;
BEGIN
    SELECT COUNT(*) INTO v_SoDotLienQuan
    FROM DotDieuTri WHERE MaSuKienKham = p_MaSuKien;

    IF v_SoDotLienQuan > 0 THEN
        ROLLBACK;
        RAISE EXCEPTION 'Khong the huy: lan kham % da mo % dot dieu tri, can huy dot dieu tri truoc',
            p_MaSuKien, v_SoDotLienQuan;
    END IF;

    DELETE FROM LanKham WHERE MaSuKien = p_MaSuKien;
    DELETE FROM SuKienYTe WHERE MaSuKien = p_MaSuKien;

    COMMIT;
END; $$;

-- 10.6 Ghi nhận trả lương hàng tháng cho một nhân viên (T6, bổ sung)
CREATE OR REPLACE PROCEDURE sp_TraLuong(p_MaNV VARCHAR, p_Thang DATE)
LANGUAGE plpgsql AS $$
DECLARE v_LoaiNV VARCHAR(10); v_LuongCoBan NUMERIC; v_Thuong NUMERIC := 0; v_Tong NUMERIC;
BEGIN
    SELECT LoaiNV, HeSoLuong * (SELECT GiaTri FROM ThamSoHeThong WHERE TenThamSo='LUONG_CO_SO')
    INTO v_LoaiNV, v_LuongCoBan
    FROM NhanVienYTe WHERE MaNV = p_MaNV;

    IF v_LoaiNV = 'BACSY' THEN
        v_Tong := fn_TinhLuongBacSy(p_MaNV, p_Thang);
        v_Thuong := v_Tong - v_LuongCoBan;
    ELSE
        SELECT COUNT(snc.MaSuKien) * (SELECT GiaTri FROM ThamSoHeThong WHERE TenThamSo='THUONG_YTA_HOTRO')
        INTO v_Thuong
        FROM SuDungNhanCong snc JOIN SuKienYTe se ON se.MaSuKien = snc.MaSuKien
        WHERE snc.MaNV = p_MaNV AND date_trunc('month', se.ThoiGian) = date_trunc('month', p_Thang);
        v_Tong := v_LuongCoBan + COALESCE(v_Thuong, 0);
    END IF;

    INSERT INTO Luong(MaLuong, MaNV, Thang, NgayNhanLuong, LuongCoBan, TienThuong, TongLuong)
    VALUES ('LG-' || p_MaNV || '-' || to_char(p_Thang,'YYYYMM'), p_MaNV, p_Thang, CURRENT_DATE,
            v_LuongCoBan, COALESCE(v_Thuong,0), v_Tong)
    ON CONFLICT (MaNV, Thang) DO UPDATE
      SET TienThuong = EXCLUDED.TienThuong, TongLuong = EXCLUDED.TongLuong, NgayNhanLuong = EXCLUDED.NgayNhanLuong;

    COMMIT;
END; $$;

-- =====================================================================
-- HẾT FILE 01_schema.sql — tiếp theo chạy 02_sample_data.sql
-- =====================================================================
