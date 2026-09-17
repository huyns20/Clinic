# Phòng khám An Tâm - Frontend Demo

Demo frontend cho đề tài **Xây dựng hệ CSDL quản lý phòng khám bệnh tư nhân**.

## Chức năng demo
- Dashboard tổng quan
- Quản lý bệnh nhân
- Tạo lần khám và mô phỏng transaction/trigger sinh mã sự kiện
- Quản lý đợt điều trị
- Hóa đơn
- Kho thuốc và cảnh báo tồn kho
- Bác sĩ / Y tá
- Danh mục Khoa / Bệnh / Dịch vụ / Thiết bị
- Báo cáo doanh thu, thống kê và bảng lương
- Tham số hệ thống

Dữ liệu hiện tại là mock data để frontend chạy độc lập, chưa cần backend.

## Chạy dự án
Yêu cầu Node.js 20+.

```bash
npm install
npm run dev
```

Sau đó mở địa chỉ Vite hiển thị, thường là http://localhost:5173.

## Build
```bash
npm run build
npm run preview
```

## Kết nối backend sau này
Thay mock data trong `src/data/mock.ts` bằng các API tương ứng. Logic Trigger, Transaction, Function và View nên nằm ở PostgreSQL/backend, frontend chỉ gọi API và hiển thị kết quả/lỗi nghiệp vụ.
