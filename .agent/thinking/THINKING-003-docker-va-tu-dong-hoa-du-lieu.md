# Thinking 003: Kiến Trúc Docker Deployment 1 Lệnh & Cơ Chế Auto-Init Dữ Liệu Tương Đối

- **Document ID**: THINKING-003
- **Created Date**: 2026-09-27
- **Author**: Principal Software Architect
- **Status**: APPROVED
- **Linked Plan**: [PLAN-003-docker-deployment-va-auto-init-data.md](file:///Clinic/.agent/plans/PLAN-003-docker-deployment-va-auto-init-data.md)

---

## 1. Phân Tích Kiến Trúc Docker Deployment Độc Lập
Để bất kỳ ai clone repository về máy đều có thể chạy được ngay mà không phải cài đặt môi trường Java, Maven, Node.js hay PostgreSQL:
- **Database (`clinic_postgres`)**:
  - Khởi tạo container từ official image `postgres:latest`.
  - Thiết lập `healthcheck: test: ["CMD-SHELL", "pg_isready -U postgres -d phong_kham2"]` để đảm bảo container PostgreSQL đã sẵn sàng nhận kết nối trước khi Backend khởi động.
- **Backend (`clinic_backend`)**:
  - Sử dụng multi-stage build: Stage build dùng `maven:3.9.9-eclipse-temurin-17-alpine` để đóng gói file JAR, sau đó chuyển sang runtime image `eclipse-temurin:17-jre-alpine` giúp kích thước image giảm từ ~800MB xuống chỉ còn ~200MB.
  - Thiết lập biến môi trường `DB_HOST=postgres` để kết nối thông qua Docker network nội bộ.
- **Frontend (`clinic_frontend`)**:
  - Dùng `node:20-alpine` build mã nguồn Vite thành static assets trong `dist/`.
  - Sử dụng `nginx:alpine` phục vụ file tĩnh và đóng vai trò **Reverse Proxy** chuyển hướng các request `/api/` về `backend:8080/api/`. Nhờ đó loại bỏ hoàn toàn vấn đề CORS và không cần hardcode địa chỉ IP hay port vào mã nguồn frontend.

## 2. Giải Pháp Tự Động Khởi Tạo CSDL (`DatabaseInitializer`)
- Trong trường hợp database hoàn toàn mới (chưa có bảng nào) hoặc dữ liệu rỗng:
  - Spring Boot `ApplicationRunner` tự động kiểm tra hàm hệ thống PostgreSQL: `SELECT to_regclass('public.benhnhan')`.
  - Nếu kết quả là `null` hoặc số lượng bản ghi bằng 0:
    + Đọc `01_schema.sql` và `02_sample_data.sql` được đóng gói sẵn trong classpath (`/db/script/`) hoặc thư mục tương đối `requirements/Script/`.
    + Dùng `ResourceDatabasePopulator` với `setContinueOnError(true)` để thực thi tuần tự tạo 24 bảng, 5 triggers, 6 stored procedures và dữ liệu mẫu.
  - Nhờ vậy, hệ thống hoàn toàn tự cấp nguồn (self-healing & self-provisioning).

## 3. Trải Nghiệm Người Dùng (UX Feedback với Toast Notifications)
- Người dùng cần biết rõ kết quả của từng hành động trên giao diện (thêm bệnh nhân, mở đợt điều trị, kê đơn thuốc, nhập kho, thu phí).
- Xây dựng Notification Stack cố định góc trên bên phải màn hình:
  - Icon trực quan (`CheckCircle2` màu xanh ngọc cho thành công; `AlertCircle` màu đỏ cho lỗi nghiệp vụ; `Clock` màu hổ phách cho cảnh báo).
  - Tự động mờ dần và biến mất sau 4 giây, hoặc cho phép người dùng click đóng ngay lập tức.
