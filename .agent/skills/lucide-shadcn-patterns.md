# Lucide Icons & Component Patterns Skill

## 1. Sử dụng Lucide React Icons
Các icon biểu trưng cho các phân hệ y tế:
- `Activity`, `HeartPulse`: Dashboard, theo dõi sức khỏe tổng quát
- `UserPlus`, `Users`: Tiếp nhận bệnh nhân, danh sách bệnh nhân
- `Stethoscope`: Phòng khám, bác sĩ khám bệnh, triệu chứng
- `BedDouble`, `Building2`: Quản lý giường bệnh, phòng chức năng, khoa khám
- `Pill`, `PackageSearch`: Kho dược phẩm, thuốc, đơn vị tính
- `Receipt`, `CreditCard`: Thu ngân, hóa đơn viện phí, thanh toán
- `BarChart3`, `TrendingUp`: Thống kê doanh thu, báo cáo bệnh tật, lương nhân viên
- `AlertCircle`, `CheckCircle2`: Cảnh báo hết thuốc, trạng thái thành công

## 2. Reusable Component Patterns (Phong cách Shadcn UI)
### 2.1. Thẻ Thống kê (StatCard)
```jsx
export function StatCard({ title, value, icon: Icon, change, trend, color = "teal" }) {
  return (
    <div className="bg-white rounded-xl p-5 border border-slate-200/80 shadow-sm hover:shadow-md transition-shadow">
      <div className="flex items-center justify-between">
        <span className="text-sm font-medium text-slate-500">{title}</span>
        <div className={`p-2.5 rounded-lg bg-${color}-50 text-${color}-600`}>
          <Icon className="w-5 h-5" />
        </div>
      </div>
      <div className="mt-3 flex items-baseline gap-2">
        <span className="text-2xl font-bold text-slate-900">{value}</span>
        {change && (
          <span className={`text-xs font-semibold ${trend === 'up' ? 'text-emerald-600' : 'text-slate-400'}`}>
            {change}
          </span>
        )}
      </div>
    </div>
  );
}
```

### 2.2. Modal / Dialog xác nhận
- Cung cấp overlay làm mờ nền (`backdrop-blur-sm bg-slate-900/40`).
- Nút bấm action rõ ràng (Primary: Teal/Emerald; Secondary: Outline slate; Danger: Rose).
- Hỗ trợ phím Escape và click ngoài để đóng modal.

### 2.3. Bảng dữ liệu (Data Table)
- Header cố định, hover dòng (`hover:bg-slate-50/80`).
- Cột trạng thái dùng Badge bo tròn mềm mại (`rounded-full px-2.5 py-0.5 text-xs font-medium`).
- Tích hợp ô tìm kiếm realtime và bộ lọc trạng thái nhanh.
