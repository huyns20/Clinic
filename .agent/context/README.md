# Agent Context Repository

## 1. Mục đích của Thư mục Context
Thư mục `context/` là "bộ nhớ dài hạn" (Long-term Knowledge Base) của Agent. Khi một phiên làm việc mới bắt đầu hoặc một Agent khác tiếp quản dự án:
- Agent không cần phải đọc lại hàng ngàn dòng file báo cáo Word hay phân tích lại từ đầu.
- Chỉ cần đọc các tài liệu trong `context/` là nắm vững ngay lập tức:
  1. [PROJECT-CONTEXT.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/context/PROJECT-CONTEXT.md): Cấu hình môi trường, port, Docker, connection string.
  2. [DATABASE-MAP.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/context/DATABASE-MAP.md): Từ điển dữ liệu 24 bảng, views, triggers, stored procedures.
  3. [BUSINESS-RULES.md](file:///D:/ptit/he_co_so_dl/Clinic/.agent/context/BUSINESS-RULES.md): Mọi quy tắc nghiệp vụ khám, điều trị, giường bệnh, tồn kho thuốc, viện phí và tính lương.

## 2. Quy tắc Duy trì Context
- Khi có bất kỳ thay đổi nào về cấu trúc bảng hoặc nghiệp vụ, Agent phải cập nhật ngay vào tài liệu context tương ứng.
