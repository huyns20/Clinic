# Plan Template: [Mã Kế Hoạch] - [Tên Tính Năng / Nghiệp Vụ]

- **Plan ID**: PLAN-XXX
- **Created Date**: YYYY-MM-DD
- **Author**: Software Development Specialist Agent
- **Status**: DRAFT | PROPOSED | APPROVED | IN_PROGRESS | COMPLETED
- **Linked Thinking**: [THINKING-XXX](file:///D:/ptit/he_co_so_dl/Clinic/.agent/thinking/)
- **Linked Tasks**: [TASK-XXX](file:///D:/ptit/he_co_so_dl/Clinic/.agent/tasks/TASK-TRACKER.md)

---

## 1. Mục tiêu (Objective)
Mô tả ngắn gọn và chuẩn xác mục tiêu cần đạt được trong kế hoạch này.

## 2. Bối cảnh & Yêu cầu Kỹ thuật (Context & Requirements)
- Bảng CSDL liên quan: (liệt kê các bảng trong `DATABASE-MAP.md`)
- Ràng buộc nghiệp vụ cần thỏa mãn: (tham chiếu `BUSINESS-RULES.md`)
- Đầu vào / Đầu ra mong muốn:

## 3. Các bước Triển khai Chi tiết (Implementation Steps)

### Giai đoạn 1: Backend (Java Spring Boot)
- [ ] **Bước 1.1**: Định nghĩa Entity & DTO (`com.example.clinic.dto.request` / `response`)
- [ ] **Bước 1.2**: Xây dựng Repository & Custom Queries (`com.example.clinic.repository`)
- [ ] **Bước 1.3**: Hiện thực Service & Business Validation (`com.example.clinic.service.impl`)
- [ ] **Bước 1.4**: Hiện thực REST Controller & OpenAPI Annotation (`com.example.clinic.controller`)

### Giai đoạn 2: Frontend (React + Vite + Tailwind CSS)
- [ ] **Bước 2.1**: Xây dựng API Service module (`src/api/...`)
- [ ] **Bước 2.2**: Xây dựng UI Component & Form nhập liệu
- [ ] **Bước 2.3**: Xử lý trạng thái (Loading, Success toast, Error alert)
- [ ] **Bước 2.4**: Tích hợp vào Navigation & Router

### Giai đoạn 3: Kiểm thử Tự động (Automated Verification)
- [ ] **Bước 3.1**: Định nghĩa Testcase trong file test suite
- [ ] **Bước 3.2**: Chạy kiểm thử tự động với `workflows/test-runner.js`
- [ ] **Bước 3.3**: Đối soát dữ liệu trong PostgreSQL (`phong_kham2`)

## 4. Tiêu chí Nghiệm thu (Verification & Acceptance Criteria)
- [ ] 100% Endpoint trả về mã trạng thái HTTP chuẩn (200, 201, 400, 404).
- [ ] Trigger/Procedure trong CSDL thực thi chính xác (ví dụ: kho thuốc tự trừ, mã sự kiện tự sinh).
- [ ] Giao diện người dùng hiển thị đúng thông tin, thông báo lỗi thân thiện, không có console warning.

## 5. Rủi ro & Giải pháp Dự phòng (Risks & Rollback Plan)
- Rủi ro:
- Giải pháp:
- Rollback:
