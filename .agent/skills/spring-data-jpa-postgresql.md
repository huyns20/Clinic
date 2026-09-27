# Spring Data JPA & PostgreSQL Optimization Skill

## 1. Kết nối CSDL PostgreSQL trong đề tài 4
- **Database Engine**: PostgreSQL 12+ (hỗ trợ CREATE PROCEDURE, ON CONFLICT, Window Functions).
- **Database Name**: `phong_kham2` (hoặc `phongkham_db` trên container `phongkham_postgres`, port `5432`).
- **Connection Profile**:
  ```yaml
  spring:
    datasource:
      url: jdbc:postgresql://localhost:5432/${DB_NAME:phong_kham2}
      username: ${DB_USER:postgres}
      password: ${DB_PASS:123456}
      driver-class-name: org.postgresql.Driver
    jpa:
      hibernate:
        ddl-auto: none # Tuyệt đối không để update/create, schema được quản lý bằng script SQL
      show-sql: false
      open-in-view: false # Tránh rò rỉ kết nối DB
      properties:
        hibernate:
          dialect: org.hibernate.dialect.PostgreSQLDialect
          jdbc:
            time_zone: Asia/Ho_Chi_Minh
  ```

## 2. Ánh xạ Quan hệ Kế thừa ISA (Inheritance Mapping)
- CSDL mô hình hóa quan hệ cha - con ISA bằng 2 cụm:
  1. `NhanVienYTe` (Cha) -> `BacSy` & `YTa` (Con).
  2. `SuKienYTe` (Cha) -> `LanKham` & `LanChuaBenh` (Con).
- **Chiến lược JPA**: Sử dụng `@Inheritance(strategy = InheritanceType.JOINED)` hoặc ánh xạ thực thể con có `@OneToOne` với `@MapsId` trỏ về bảng cha để bảo đảm tính tương thích tối đa với trigger `sinh_ma_sukien()`.

## 3. Tương tác với Triggers & Stored Procedures của PostgreSQL
Database đã có 5 Triggers và 6 Stored Procedures quan trọng:
1. `sinh_ma_sukien()`: Tự động phát sinh mã sự kiện định dạng `<MaKhoa>-<MaBS>-<K/C>-<YYYYMMDD>-<STT>`.
2. `trg_tru_ton_kho_thuoc()`: Kiểm tra tồn kho và tự động trừ số lượng trong bảng `Thuoc` khi có bản ghi mới trong `SuDungThuoc`. Nếu không đủ hàng, DB raise exception.
3. `sp_tao_dot_dieu_tri(...)`: Tạo mới một đợt điều trị bệnh cho bệnh nhân.
4. `sp_dong_dot_dieu_tri(...)`: Đóng đợt điều trị, giải phóng giường bệnh.
5. `sp_tinh_luong_thang(...)`: Tính lương tự động cho nhân viên y tế theo công lao động.
- Trong Spring Data JPA Repository, gọi Stored Procedure qua:
  ```java
  @Modifying
  @Transactional
  @Query(value = "CALL sp_dong_dot_dieu_tri(:maDot, :ngayKetThuc)", nativeQuery = true)
  void dongDotDieuTri(@Param("maDot") String maDot, @Param("ngayKetThuc") LocalDate ngayKetThuc);
  ```

## 4. Tối ưu Truy vấn & Phân trang (Pagination)
- Tránh N+1 query bằng cách sử dụng `@EntityGraph` hoặc JPQL `JOIN FETCH` khi lấy thông tin chi tiết hóa đơn, sự kiện y tế, đợt điều trị.
- Ánh xạ View `v_DotDieuTri_ChiTiet` và `v_LichSuKhamBenh` bằng JPA `@Immutable` Entity để truy vấn siêu nhanh dữ liệu tổng hợp cho UI Dashboard.
