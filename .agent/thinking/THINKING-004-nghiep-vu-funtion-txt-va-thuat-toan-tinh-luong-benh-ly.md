# Thinking 004: Phân Tích Nghiệp Vụ Chuyên Sâu Theo funtion.txt & Thuật Toán Thống Kê / Tính Lương

- **Document ID**: THINKING-004
- **Created Date**: 2026-09-27
- **Author**: Lead Software Architect & Database Specialist
- **Status**: APPROVED
- **Linked Plan**: [PLAN-004-hoan-thien-master-data-patient-history-va-bao-cao-nang-cao.md](file:///Clinic/.agent/plans/PLAN-004-hoan-thien-master-data-patient-history-va-bao-cao-nang-cao.md)

---

## 1. Phân Tích Thuật Toán Thống Kê Bệnh Lý (Mục 2.1 trong funtion.txt)

### Bài toán:
*"Liệt kê danh sách các loại bệnh được các bệnh nhân mắc phải trong một tháng nào đó, sắp xếp theo thứ tự số bệnh nhân đến khám giảm dần. Một bệnh nhân có thể đến khám/chữa một bệnh nhiều lần liên tiếp nhau trong khoảng thời gian đó nhưng chỉ tính là 1 lần mắc. Nếu chữa khỏi rồi sau đó tái phát lại, sẽ được tính là mắc nhiều lần."*

### Giải pháp kỹ thuật trong CSDL:
Trong CSDL đã chuẩn hóa BCNF:
- Mỗi lần bệnh nhân đến khám bệnh phát sinh hoặc tái khám ban đầu sẽ mở ra một `DotDieuTri` (Đợt điều trị).
- Các lần chữa bệnh tiếp theo của đợt này (`LanChuaBenh`) đều tham chiếu về `DotDieuTri.MaDotDieuTri`.
- Vì vậy, một chuỗi các lần khám/chữa bệnh liên tiếp cho cùng một bệnh chính là **1 bản ghi trong bảng `DotDieuTri`**!
- Khi bệnh nhân chữa khỏi (`TrangThai = 'DaKhoi'`), sau đó tái phát lại, hệ thống sẽ mở một bản ghi `DotDieuTri` mới và gán `MaDotTruoc` trỏ về đợt cũ.
$\rightarrow$ **Quy tắc đếm chuẩn xác**:
Để đếm số lần mắc bệnh trong tháng $M$:
Ta đếm số lượng `DotDieuTri` có `NgayBatDau` nằm trong tháng $M$.
Mỗi `DotDieuTri` đại diện cho một đợt mắc bệnh (dù bệnh nhân đó đến chữa 1 lần hay 10 lần trong đợt đó thì vẫn chỉ tính là 1 lần mắc).
Nếu bệnh nhân có 2 `DotDieuTri` khác nhau của cùng 1 bệnh trong khoảng thời gian đó (1 đợt ban đầu và 1 đợt tái phát), câu truy vấn sẽ đếm chính xác là 2 lần mắc!

---

## 2. Phân Tích Thuật Toán Tính Lương Nhân Sự Y Tế (Mục 3 trong funtion.txt)

### Quy tắc nghiệp vụ tính lương:
1. **Lương cơ bản**:
   $$\text{Lương Cơ Bản} = \text{Mức Lương Nhà Nước (1.800.000 hoặc 2.340.000)} \times \text{HeSoLuong}$$
   (Trong bảng `NhanVienYTe`, mỗi nhân viên đã có sẵn trường `HeSoLuong`).
2. **Tiền thưởng Bác sĩ**:
   - Điều kiện: Cứ mỗi bệnh nhân đến khám và chữa khỏi một bệnh (kết thúc một chuỗi khám/chữa bệnh nhiều lần liên tiếp cho bệnh đó) bởi một bác sĩ thì bác sĩ đó được cộng **1.000.000 VNĐ**.
   - CSDL: Đếm số lượng `DotDieuTri` có `TrangThai = 'DaKhoi'`, có `NgayKetThuc` trong tháng đang xét, và bác sĩ phụ trách là `MaBS` (truy vết qua `DotDieuTri -> LanKham -> SuKienYTe.MaBS`).
   $$\text{Thưởng BS} = \text{Số đợt điều trị đã khỏi} \times 1.000.000\,\text{VNĐ}$$
3. **Tiền thưởng Y tá**:
   - Điều kiện: Mỗi lần một y tá thực hiện hỗ trợ một bệnh nhân trong một lần đến khám/chữa bệnh thì được cộng thêm **200.000 VNĐ**.
   - CSDL: Đếm số lượng bản ghi trong `SuDungNhanCong` có `MaNV` là y tá đó, hoặc số lần tham gia hỗ trợ trong sự kiện y tế phát sinh trong tháng.
   $$\text{Thưởng Y tá} = \text{Số lượt hỗ trợ sự kiện y tế} \times 200.000\,\text{VNĐ}$$
4. **Tổng Lương Thực Lĩnh**:
   $$\text{TongLuong} = \text{LuongCoBan} + \text{TienThuong}$$

---

## 3. Phân Rã Nguồn Thu Viện Phí Phòng Khám (Mục 2.2 trong funtion.txt)
Tổng doanh thu phòng khám không chỉ là một con số gộp, mà phân rã chi tiết thành 5 dòng doanh thu:
1. `TienKham`: Tổng tiền từ bảng `LanKham.tienkham`.
2. `TienChua`: Tổng tiền từ bảng `LanChuaBenh.tienchua`.
3. `TienThuoc`: Tổng tiền từ bảng `SuDungThuoc` (`soluong * dongiaapdung`).
4. `TienDichVu`: Tổng tiền từ bảng `SuDungDichVu` (`soluong * dongiaapdung`).
5. `TienThietBiGiuong`: Tiền sử dụng thiết bị (`SuDungThietBi`) + Tiền giường nằm điều trị (`SoNgayNam * DonGiaNgay`).
Tổng doanh thu này được kiểm chứng khớp 100% với các hóa đơn `HoaDon` đã thanh toán (`TrangThaiTT = 'DaThanhToan'`).
