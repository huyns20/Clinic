# API Design Patterns

## RESTful Endpoints

### CRUD Pattern
```
GET    /api/v1/{resource}           - List all (with optional pagination)
GET    /api/v1/{resource}/{id}      - Get by ID
POST   /api/v1/{resource}           - Create new
PUT    /api/v1/{resource}/{id}      - Update existing
DELETE /api/v1/{resource}/{id}      - Delete
```

### Business Logic Endpoints
```
POST   /api/v1/su-kien-yte/kham     - Tiếp nhận khám (sp_TiepNhanKham)
POST   /api/v1/su-kien-yte/chua     - Ghi nhận chữa bệnh (sp_GhiNhanChuaBenh)
POST   /api/v1/hoa-don/{id}/xuat    - Xuất hóa đơn (sp_XuatHoaDon)
POST   /api/v1/thuoc/{id}/nhap-kho  - Nhập kho thuốc (sp_NhapKhoThuoc)
DELETE /api/v1/su-kien-yte/kham/{id} - Hủy lần khám (sp_HuyLanKham)
POST   /api/v1/luong/tra            - Trả lương (sp_TraLuong)
```

### Statistics Endpoints
```
GET /api/v1/thong-ke/doanh-thu              - Revenue by date range
GET /api/v1/thong-ke/doanh-thu-theo-khoa    - Revenue by department
GET /api/v1/thong-ke/benh-pho-bien          - Top common diseases
GET /api/v1/thong-ke/thuoc-su-dung-nhieu    - Top used medicines
GET /api/v1/thong-ke/dot-dieu-tri           - Treatment course stats
GET /api/v1/thong-ke/ton-kho                - Inventory report
```

## Response Format
```json
{
  "data": { ... },
  "message": "Success",
  "timestamp": "2026-09-27T10:00:00Z"
}
```

## Error Response
```json
{
  "error": "NOT_FOUND",
  "message": "Bệnh nhân không tồn tại",
  "timestamp": "2026-09-27T10:00:00Z"
}
```
