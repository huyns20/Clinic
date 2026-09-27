# React + Vite + Tailwind CSS Skill

## 1. Lý do chọn Vite + React + Tailwind CSS cho Hệ thống Phòng Khám
1. **Khởi động siêu tốc**: Vite sử dụng ES modules nguyên bản, server khởi động dưới 300ms, hot module reload (HMR) tức thời.
2. **Cực kỳ trực quan & dễ hiểu**: Không cần cấu hình phức tạp như Next.js hay SSR. Rất thích hợp cho mô hình Single Page Application (SPA) kết nối Backend Spring Boot.
3. **Thẩm mỹ đỉnh cao**: Tailwind CSS v3/v4 mang lại hệ thống utility-first styling hoàn hảo, kết hợp cùng Lucide React Icons tạo nên giao diện hiện đại, sạch sẽ, chuẩn giao diện bệnh viện cao cấp.
4. **Tương thích hoàn hảo với Node.js**: Hoạt động mượt mà trên môi trường Node.js v24 có sẵn.

## 2. Cấu trúc thư mục Frontend chuẩn
```
frontend/
├── index.html
├── package.json
├── vite.config.js
├── tailwind.config.js
├── postcss.config.js
└── src/
    ├── api/                 # Axios client, các hàm gọi REST API (patientApi, clinicApi, invoiceApi...)
    ├── assets/              # Logo, hình ảnh, icon tĩnh
    ├── components/          # Reusable UI components
    │   ├── ui/              # Button, Input, Modal, Badge, Card, Table, Toast
    │   ├── layout/          # Sidebar, Header, PageContainer, Navbar
    │   └── common/          # LoadingSpinner, ErrorAlert, StatCard
    ├── pages/               # Các trang màn hình chính theo phân hệ
    │   ├── Dashboard/       # Tổng quan phòng khám, KPI, biểu đồ
    │   ├── Reception/       # Tiếp nhận bệnh nhân, tìm kiếm, đăng ký khám
    │   ├── Examination/     # Phòng khám: Bác sĩ khám bệnh, kê đơn thuốc, tạo đợt điều trị
    │   ├── Treatment/       # Quản lý đợt điều trị, lần chữa bệnh, gán giường bệnh
    │   ├── Pharmacy/        # Kho dược phẩm, cảnh báo tồn kho, tra cứu thuốc
    │   ├── Billing/         # Thu ngân, tra cứu & thanh toán hóa đơn viện phí
    │   └── Reports/         # Báo cáo thống kê doanh thu, bệnh lý, lương nhân viên
    ├── hooks/               # Custom React hooks (usePatients, useToast, useDebounce...)
    ├── context/             # AppContext, NotificationContext
    ├── utils/               # Formatters (tiền tệ VND, định dạng ngày giờ Việt Nam)
    ├── App.jsx              # Main routing & layout component
    └── main.jsx             # Entry point
```

## 3. Các quy chuẩn mã nguồn Frontend
- **Format tiền tệ**: Luôn format chuẩn tiền tệ Việt Nam: `new Intl.NumberFormat('vi-VN', { style: 'currency', currency: 'VND' }).format(val)`.
- **Format ngày tháng**: Định dạng chuẩn `DD/MM/YYYY` hoặc `DD/MM/YYYY HH:mm`.
- **Xử lý State**: Sử dụng React Hooks chuẩn (`useState`, `useEffect`, `useCallback`, `useMemo`), giữ code trong sáng, không lạm dụng state thừa.
- **Tương tác API**: Tập trung toàn bộ logic gọi API trong thư mục `src/api/` sử dụng `axios` instance với base URL `http://localhost:8080/api/v1` và interceptor bắt lỗi tập trung.
