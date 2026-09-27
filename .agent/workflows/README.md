# Automated Testing Workflow

## 1. Mục đích
Hệ thống workflow này cung cấp cơ chế tự động hóa kiểm thử toàn diện cho Hệ thống Quản lý Phòng Khám:
1. **Tự động sinh Testcases**: Lưu trữ trong thư mục `testcases/` dưới định dạng JSON có cấu trúc.
2. **Tự động thực thi (Auto Test Runner)**: File `test-runner.js` chạy trên nền Node.js có sẵn, tự động gọi các REST API của Spring Boot.
3. **Tự động sinh Báo cáo (Test Report)**: Xuất kết quả kiểm thử (Pass/Fail, Response Time, Lỗi chi tiết) ra thư mục `reports/`.

## 2. Cách Chạy Kiểm Thử Tự Động
Mở terminal tại thư mục dự án và chạy:
```bash
node D:/ptit/he_co_so_dl/Clinic/.agent/workflows/test-runner.js
```
Hoặc cấu hình npm script trong frontend hoặc root:
```bash
npm run test:api
```
