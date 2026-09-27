# Agent Plan Protocol & Guidelines

## 1. Quy định Bắt Buộc (Strict Mandate)
> **NGUYÊN TẮC VÀNG:** Tuyệt đối KHÔNG viết hoặc sửa bất kỳ dòng code nào trong `backend/` hoặc `frontend/` nếu chưa có file Kế Hoạch (Plan) chi tiết được tạo trong thư mục `plans/` và được đánh giá, phê duyệt.

Mỗi khi người dùng hoặc hệ thống yêu cầu một tính năng mới, một bản sửa lỗi (bugfix) hoặc một đợt tái cấu trúc (refactoring):
1. **Bước 1 (Phân tích & Suy nghĩ)**: Ghi lại reasoning tại thư mục `thinking/`.
2. **Bước 2 (Lập Kế hoạch)**: Tạo file `PLAN-XXX-<ten-tinh-nang>.md` trong thư mục `plans/` theo mẫu chuẩn `PLAN-TEMPLATE.md`.
3. **Bước 3 (Cập nhật Task)**: Thêm/cập nhật các mục công việc tương ứng trong `tasks/TASK-TRACKER.md`.
4. **Bước 4 (Phê duyệt)**: Trình bày kế hoạch cho người dùng hoặc xác nhận tiêu chuẩn trước khi bắt đầu viết mã.
5. **Bước 5 (Triển khai & Kiểm thử)**: Thực thi code đúng theo từng bước trong Plan và kiểm thử tự động qua `workflows/`.

## 2. Cấu trúc một bản Plan tiêu chuẩn
Một file Plan phải bao gồm:
- **Mã Plan & Tên tính năng**: Ví dụ `PLAN-001-he-thong-quan-ly-phong-kham.md`.
- **Mục tiêu & Phạm vi (Objectives & Scope)**.
- **Tài liệu tham chiếu**: Liên kết đến file `thinking/`, `context/` và `tasks/`.
- **Kế hoạch triển khai chi tiết từng bước (Step-by-step Execution)**:
  - Tên file cần tạo/sửa.
  - Chức năng cụ thể.
  - Ràng buộc dữ liệu & xử lý ngoại lệ.
- **Tiêu chí nghiệm thu (Acceptance Criteria / Verification Plan)**.
- **Rủi ro & Phương án dự phòng (Risks & Mitigation)**.

## 3. Danh sách Plans hiện có
- [PLAN-001-he-thong-quan-ly-phong-kham.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/plans/PLAN-001-he-thong-quan-ly-phong-kham.md): Kế hoạch tổng thể phát triển Hệ thống Quản lý Phòng khám tư nhân (Fullstack Spring Boot + React UI).
