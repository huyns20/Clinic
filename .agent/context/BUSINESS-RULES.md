# Business Rules: Quy Tắc Nghiệp Vụ Phòng Khám Bệnh Tư Nhân

Tài liệu này tổng hợp toàn bộ các quy tắc nghiệp vụ thực tế và logic xử lý đã được chuẩn hóa trong Báo cáo đề tài 4.

---

## 1. Nghiệp Vụ Tiếp Nhận & Bệnh Nhân
- **BR-PAT-01 (Định danh duy nhất)**: Mỗi bệnh nhân được định danh qua `MaBN`. Số căn cước công dân (`SoCCCD`) nếu có phải là duy nhất trên toàn hệ thống.
- **BR-PAT-02 (Thông tin tối thiểu)**: Bắt buộc phải có `HoTen`, `GioiTinh` ('M' hoặc 'F') và ngày đăng ký khám (`NgayDangKy`).
- **BR-PAT-03 (Lịch sử bệnh án)**: Khi bệnh nhân đến khám lần thứ 2 trở đi, hệ thống phải tự động truy xuất lịch sử các lần khám trước, các đợt điều trị đã khỏi hoặc đang mở.

## 2. Nghiệp Vụ Khám Bệnh & Phân Bổ Bác Sĩ
- **BR-EXAM-01 (Mã Sự Kiện Tự Sinh)**: Mã sự kiện y tế được sinh tự động theo quy tắc: `<MaKhoa>-<MaBS>-<K/C>-<YYYYMMDD>-<STT>`. Trong đó:
  - `K`: Khám ban đầu (`LanKham`).
  - `C`: Chữa bệnh (`LanChuaBenh`).
- **BR-EXAM-02 (Khoa & Chuyên môn)**: Bác sĩ phụ trách sự kiện khám phải thuộc Khoa tương ứng (`BacSy -> NhanVienYTe.MaKhoa == LanKham.MaKhoa`).
- **BR-EXAM-03 (Khởi tạo Hóa đơn)**: Mỗi khi một sự kiện y tế (`SuKienYTe`) được tạo, hệ thống tự động khởi tạo 1 bản ghi `HoaDon` tương ứng ở trạng thái `ChuaThanhToan`. Khoản tiền khám `TienKham` được tự động ghi vào `HoaDonChiTiet`.

## 3. Nghiệp Vụ Đợt Điều Trị (Treatment Course)
- **BR-TREAT-01 (Điều kiện Mở Đợt)**: Đợt điều trị chỉ được mở từ một lần khám ban đầu (`LanKham`) và phải xác định một chẩn đoán bệnh cụ thể (`MaBenh`).
- **BR-TREAT-02 (Gán Giường Bệnh)**: Nếu đợt điều trị yêu cầu nằm viện/nằm theo dõi (`MaGiuong` khác null), giường bệnh đó phải đang ở trạng thái `'Trong'`. Ngay khi gán, trạng thái giường tự động chuyển thành `'CoNguoi'`.
- **BR-TREAT-03 (Đóng Đợt Điều Trị)**:
  - Khi bác sĩ kết luận đã khỏi, đợt điều trị chuyển `TrangThai` thành `'DaKhoi'`, ghi nhận `NgayKetThuc` (`NgayKetThuc >= NgayBatDau`).
  - Giường bệnh đang gán sẽ được giải phóng trở về trạng thái `'Trong'`.
- **BR-TREAT-04 (Bệnh Tái Phát)**: Nếu bệnh nhân tái phát bệnh cũ sau một thời gian, đợt điều trị mới sẽ trỏ `MaDotTruoc` về mã đợt điều trị cũ, tạo thành chuỗi liên kết đệ quy để theo dõi diễn tiến lâu dài.

## 4. Nghiệp Vụ Kho Dược & Kê Đơn Thuốc
- **BR-PHARM-01 (Kiểm soát Tồn kho Chặt chẽ)**: Khi bác sĩ kê đơn thuốc:
  - Số lượng kê đơn `SoLuong` phải lớn hơn 0.
  - Số lượng tồn kho `TonKho` của thuốc trong kho phải `>= SoLuong`.
  - Database trigger sẽ tự động trừ `TonKho` và chặn giao dịch nếu không đủ thuốc.
- **BR-PHARM-02 (Đơn giá Áp dụng)**: Đơn giá trong `SuDungThuoc.DonGiaApDung` được lấy theo đơn giá tại thời điểm kê đơn trong bảng `Thuoc`, bảo đảm hóa đơn không bị ảnh hưởng nếu sau này giá thuốc thay đổi.
- **BR-PHARM-03 (Cộng tiền Hóa đơn)**: Chi phí thuốc (`SoLuong * DonGiaApDung`) được tự động cập nhật vào `HoaDonChiTiet` và `HoaDon.TongTien`.

## 5. Nghiệp Vụ Cơ Sở Vật Chất & Dịch Vụ
- **BR-FAC-01 (Sử dụng Thiết bị & Dịch vụ)**: Mỗi lượt sử dụng thiết bị xét nghiệm/chụp chiếu (`SuDungThietBi`) hoặc dịch vụ thủ thuật y tế (`SuDungDichVu`) đều được tính vào chi phí sự kiện y tế tương ứng.
- **BR-FAC-02 (Chi phí Giường bệnh)**: Tiền sử dụng giường bệnh được tính bằng: `Số ngày nằm * DonGiaNgay`.

## 6. Nghiệp Vụ Thu Ngân & Tài Chính
- **BR-BILL-01 (Công thức Tổng Hóa đơn)**:
  $$\text{TongTien} = \text{TienKham/TienChua} + \sum \text{TienThuoc} + \sum \text{TienDichVu} + \sum \text{TienThietBi} + \sum \text{TienCongNhanVien}$$
- **BR-BILL-02 (Thanh toán)**: Khi bệnh nhân thanh toán, hóa đơn chuyển `TrangThaiTT = 'DaThanhToan'`. Không được phép sửa đổi các chi tiết thuốc/dịch vụ của sự kiện sau khi hóa đơn đã thanh toán.
- **BR-BILL-03 (Tính Lương Nhân Sự)**:
  $$\text{TongLuong} = \text{LuongCoBan} \times \text{HeSoLuong} + \text{TienThuong} + \sum \text{DonGiaCong}$$

---

## 7. Quy Tắc Nghiệp Vụ Nâng Cao (Theo funtion.txt Đề Tài 4)
- **BR-FUNTION-01 (Quản lý Master Data 8 đối tượng)**:
  Hệ thống cung cấp đầy đủ giao diện và API CRUD cho 8 thực thể dữ liệu nền tảng: Bác sĩ, Y tá, Bệnh nhân, Thuốc, Thiết bị y tế, Dịch vụ y tế, Phòng khám, Giường bệnh. Mọi thay đổi phản ánh trực tiếp vào CSDL PostgreSQL.
- **BR-FUNTION-02 (Hồ Sơ Bệnh Nhân 360°)**:
  Truy xuất tức thời:
  1. Tình trạng bệnh hiện tại: Mắc bệnh gì, số lần đã khám/chữa cho bệnh đó trong đợt điều trị hiện tại, bác sĩ phụ trách, giường nằm bố trí.
  2. Toàn bộ lịch sử khám/chữa từ trước tới nay: Chi tiết ngày giờ, loại sự kiện, từng khoản mục chi phí (tiền khám, tiền chữa, tiền thuốc, tiền dịch vụ kỹ thuật) và trạng thái thanh toán viện phí.
- **BR-FUNTION-03 (Thống Kê Bệnh Lý Mắc Phải Theo Tháng - Mục 2.1)**:
  - Danh sách bệnh mắc phải trong tháng được sắp xếp **giảm dần theo số ca mắc**.
  - **Quy tắc gom chuỗi liên tiếp**: Chuỗi khám/chữa bệnh liên tiếp thuộc cùng một `DotDieuTri` chỉ được tính là **1 ca mắc bệnh**.
  - **Quy tắc tái phát**: Nếu bệnh nhân bị tái phát bệnh (mở đợt điều trị mới có `MaDotTruoc`), được tính là **1 ca mắc mới (tái phát)**.
- **BR-FUNTION-04 (Doanh Thu Phân Rã 5 Nguồn Thu - Mục 2.2)**:
  Báo cáo tài chính phòng khám theo tháng phải bóc tách thành 5 nguồn độc lập:
  1. Tiền khám bệnh (`LanKham.TienKham`)
  2. Tiền chữa bệnh (`LanChuaBenh.TienCongChua`)
  3. Tiền thuốc kê đơn (`SuDungThuoc.SoLuong * Thuoc.DonGia`)
  4. Tiền dịch vụ y tế kỹ thuật (`HoaDonChiTiet`)
  5. Tiền thiết bị / cơ sở vật chất giường bệnh (`HoaDonChiTiet`)
- **BR-FUNTION-05 (Bảng Lương Thưởng Nhân Sự Chuẩn Đề Tài 4 - Mục 3)**:
  - **Bác sĩ**: Nhận thưởng **1.000.000 VNĐ** cho mỗi ca bệnh chữa khỏi trong tháng (`DotDieuTri.TrangThai = 'DaKhoi'` và `NgayKetThuc` trong tháng).
  - **Y tá**: Nhận thưởng **200.000 VNĐ** cho mỗi lượt hỗ trợ bệnh nhân trong tháng.

