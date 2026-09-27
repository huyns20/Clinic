# Plan 003: Thiết Lập Docker Deployment 1 Lệnh, Toast Notifications & Tự Động Init CSDL

- **Plan ID**: PLAN-003
- **Created Date**: 2026-09-27
- **Author**: Lead Software Engineer Agent
- **Status**: COMPLETED
- **Linked Thinking**: [THINKING-003-docker-va-tu-dong-hoa-du-lieu.md](file:///Clinic/.agent/thinking/THINKING-003-docker-va-tu-dong-hoa-du-lieu.md)
- **Linked Tasks**: [TASK-TRACKER.md](file:///Clinic/.agent/tasks/TASK-TRACKER.md)

---

## 1. Mục Tiêu (Objective)
1. Xây dựng thư mục `Clinic/deploy` đóng gói toàn bộ hạ tầng (PostgreSQL, Backend Spring Boot, Frontend Nginx) chỉ bằng 1 lệnh duy nhất (`start.bat` / `start.sh`).
2. Tích hợp Toast Notifications phản hồi tương tác (Thành công / Lỗi) trên UI cho toàn bộ các action.
3. Hiện thực cơ chế tự động kiểm tra và init dữ liệu mẫu từ `requirements/Script` vào PostgreSQL nếu database trống.
4. Chuyển đổi 100% đường dẫn trong dự án thành đường dẫn tương đối (Relative paths).

## 2. Các Bước Đã Triển Khai
- [x] Tạo `Dockerfile.backend` (Multi-stage Eclipse Temurin 17 JRE).
- [x] Tạo `Dockerfile.frontend` (Multi-stage Node 20 build + Nginx Alpine).
- [x] Tạo `nginx.conf` hỗ trợ SPA routing và reverse proxy `/api` sang backend.
- [x] Tạo `docker-compose.yml` điều phối 3 container với healthcheck PostgreSQL.
- [x] Tạo `start.bat`, `start.sh`, `stop.bat`, `stop.sh`.
- [x] Viết `DatabaseInitializer.java` tự động kiểm tra `to_regclass('public.benhnhan')` và nạp `01_schema.sql`, `02_sample_data.sql`.
- [x] Tích hợp Toast Notifications và API client tương đối trong `App.jsx`.
- [x] Cập nhật toàn diện `README.md` hướng dẫn chạy và danh sách tính năng.
