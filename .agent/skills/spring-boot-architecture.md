# Spring Boot Architecture & Best Practices Skill

## 1. Tổng quan Kiến trúc (Layered Clean Architecture)
Hệ thống Backend Spring Boot 3 cho Đề tài Quản lý phòng khám tuân thủ mô hình phân lớp nghiêm ngặt:
```
com.example.clinic
├── config/              # Cấu hình OpenAPI/Swagger, CORS, Jackson, Security (nếu có)
├── controller/          # REST Controllers tiếp nhận request HTTP, validate đầu vào
├── dto/                 # Data Transfer Objects
│   ├── request/         # Request body DTOs (@Valid, Bean Validation)
│   └── response/        # Response DTOs & ApiResponse wrapper chuẩn
├── entity/              # JPA Entities phản ánh 24 bảng CSDL PostgreSQL
├── enums/               # Enums chuẩn hóa (Gender, LoaiNhanVien, TrangThaiDotDieuTri, v.v.)
├── exception/           # Custom Business Exceptions & GlobalExceptionHandler (@RestControllerAdvice)
├── mapper/              # Chuyển đổi Entity <-> DTO (MapStruct hoặc ModelMapper/Manual)
├── repository/          # Spring Data JPA Repositories & Custom Native Queries
├── service/             # Giao diện nghiệp vụ (Interfaces)
│   └── impl/            # Cài đặt chi tiết nghiệp vụ (@Service, @Transactional)
└── ClinicApplication.java
```

## 2. Nguyên tắc thiết kế Controller & API
- **Endpoint naming**: Chuẩn RESTful, danh từ số nhiều hoặc theo kebab-case module tiếng Việt rõ nghĩa:
  - `/api/v1/benh-nhan`
  - `/api/v1/bac-sy`, `/api/v1/y-ta`, `/api/v1/nhan-vien`
  - `/api/v1/su-kien-y-te`, `/api/v1/lan-kham`, `/api/v1/lan-chua-benh`
  - `/api/v1/dot-dieu-tri`
  - `/api/v1/thuoc`, `/api/v1/thiet-bi`, `/api/v1/dich-vu`
  - `/api/v1/hoa-don`, `/api/v1/luong`
  - `/api/v1/thong-ke`, `/api/v1/phong-giuong`
- **Validation**: Bắt buộc sử dụng `@Valid` với Jakarta Validation (`@NotNull`, `@NotBlank`, `@Min`, `@Pattern`, v.v.).
- **Response Wrapper**: Luôn trả về cấu trúc chuẩn:
  ```json
  {
    "success": true,
    "message": "Thành công",
    "data": { ... },
    "timestamp": "2026-09-27T17:40:00"
  }
  ```

## 3. Quản lý Giao dịch (Transaction Management)
- Gắn `@Transactional` trên các phương thức Service có ghi/sửa dữ liệu.
- Đối với các phương thức chỉ đọc, luôn sử dụng `@Transactional(readOnly = true)` để tối ưu hiệu năng bộ nhớ Hibernate.
- Đảm bảo tính toàn vẹn khi thực hiện các chuỗi thao tác:
  - Tiếp nhận khám -> Tạo `SuKienYTe` -> Tạo `LanKham` -> Tự động sinh `HoaDon`.
  - Kê đơn thuốc -> Ghi nhận `SuDungThuoc` -> Trigger PostgreSQL tự động kiểm tra và trừ `TonKho` trong bảng `Thuoc`.

## 4. Xử lý Lỗi Toàn cục (Global Exception Handling)
- `@RestControllerAdvice` bắt các lỗi:
  - `MethodArgumentNotValidException` (400 - Dữ liệu nhập sai định dạng)
  - `ResourceNotFoundException` (404 - Không tìm thấy bản ghi)
  - `BusinessException` (400 / 409 - Vi phạm quy tắc nghiệp vụ như thuốc hết hàng, giường đã có người nằm)
  - `DataIntegrityViolationException` (409 - Trùng khóa chính, vi phạm Foreign Key)
  - `Exception` (500 - Lỗi hệ thống không lường trước)
