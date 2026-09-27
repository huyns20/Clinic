# Task Management Protocol

## 1. Mục đích của Thư mục Tasks
Thư mục `tasks/` lưu trữ bảng theo dõi tiến độ công việc tổng thể và chi tiết (`TASK-TRACKER.md`). Đây là "trung tâm điều hành" giúp Agent và người dùng luôn nắm bắt:
- Từng công việc cụ thể đang ở trạng thái nào.
- Công việc đó thuộc Kế hoạch (Plan) nào và dựa trên Suy luận (Thinking) nào.
- File mã nguồn và chức năng bị ảnh hưởng.

## 2. Các Trạng thái của Task (Task Lifecycle)
- `TODO`: Đã xác định trong kế hoạch nhưng chưa bắt đầu.
- `IN_PROGRESS`: Đang được tiến hành hiện thực.
- `TESTING`: Đang chạy kiểm thử tự động hoặc kiểm thử giao diện.
- `DONE`: Đã hoàn thành, qua kiểm thử, đáp ứng 100% tiêu chí nghiệm thu.
- `BLOCKED`: Đang bị nghẽn do phụ thuộc vào module khác.

## 3. Quy tắc Cập nhật
Mỗi khi bắt đầu hoặc hoàn thành một công việc, Agent phải cập nhật trực tiếp vào file [TASK-TRACKER.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/tasks/TASK-TRACKER.md).
