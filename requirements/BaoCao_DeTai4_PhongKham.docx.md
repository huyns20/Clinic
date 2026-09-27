**HỌC VIÊN CÔNG NGHỆ BƯU CHÍNH VIẾN THÔNG**

**\---------------------------**

![][image1]

**BÁO CÁO BÀI TẬP LỚN**  
**CÁC HỆ THỐNG CƠ SỞ DỮ LIỆU**

| Giảng viên hướng dẫn | : TS. Phan Thị Hà |
| :---- | :---- |
| **Nhóm** | **:** |
| **Lớp** | **: M26CQHT03-B** |

**Hà Nội – 09/2026**  
**DANH SÁCH THÀNH VIÊN NHÓM**

| STT | Họ và tên | Mã sinh viên | Nhiệm vụ |
| :---- | :---- | :---- | :---- |
|  |  |  |  |
|  |  |  |  |
|  |  |  |  |
|  |  |  |  |

# **1. Kịch bản thế giới thực**

Hoạt động khám chữa bệnh tại một phòng khám tư nhân diễn ra liên tục và có tính nghiệp vụ cao: phòng khám tiếp nhận bệnh nhân, phân bổ bác sĩ và y tá theo từng khoa chuyên môn, ghi nhận triệu chứng, chẩn đoán, kê đơn thuốc, sử dụng thiết bị/dịch vụ y tế, bố trí phòng khám và giường bệnh khi cần điều trị dài ngày, đồng thời tổng hợp chi phí thành hóa đơn cho bệnh nhân. Thực tế cho thấy quy trình này gặp nhiều thách thức: một bệnh có thể cần nhiều lần chữa liên tiếp (phải gộp thành một đợt điều trị thay vì đếm rời rạc), nhân sự y tế được trả công theo từng loại việc thực hiện, thuốc và thiết bị cần được kiểm soát tồn kho, còn phòng/giường lại là tài nguyên vật chất có giới hạn cần được quản lý chặt chẽ.

Do đó, nhu cầu về một hệ thống quản lý phòng khám được tin học hóa, tập trung và hiệu quả, hỗ trợ tiếp nhận khám nhanh chóng, theo dõi đợt điều trị xuyên suốt, tự động kiểm soát tồn kho và tính lương, thống kê doanh thu và bệnh lý trở nên rất cấp thiết để đảm bảo hoạt động phòng khám vận hành mượt mà, chính xác và phục vụ tốt hơn cho bệnh nhân.

## **1.1. Ứng dụng của hệ cơ sở dữ liệu**

Việc áp dụng một hệ cơ sở dữ liệu quan hệ cho bài toán "Quản lý phòng khám bệnh tư nhân" mang lại khả năng tổ chức, lưu trữ và khai thác thông tin một cách khoa học và hiệu quả. Dựa trên sơ đồ quan hệ, hệ thống CSDL sẽ giải quyết các nghiệp vụ quản lý chính như sau:

* **Quản lý nhân sự y tế:** NhanVienYTe là tập cha (ISA) chứa các thuộc tính chung (HoTen, MaKhoa, HeSoLuong…). Hai tập con *BacSy* và *YTa* kế thừa thuộc tính chung và bổ sung thuộc tính riêng (ChuyenMon, ChungChiHanhNghe). Bảng *Luong* theo dõi lương hằng tháng của từng nhân viên. Hệ thống hỗ trợ tra cứu nhanh theo khoa, tính lương tự động theo hiệu suất làm việc.

* **Quản lý khám và điều trị:** Quan hệ *SuKienYTe* (ISA của *LanKham*/*LanChuaBenh*) ghi nhận mọi lượt bệnh nhân đến phòng khám. Thực thể kết hợp *DotDieuTri* gộp các lần chữa liên tiếp của cùng một bệnh thành một đợt, hỗ trợ liên kết đệ quy khi bệnh tái phát. Hệ thống hỗ trợ theo dõi đợt điều trị đang mở, lịch sử khám của từng bệnh nhân, và tự động đóng đợt khi bác sỹ kết luận đã khỏi.

* **Quản lý cơ sở vật chất:** Mỗi *Khoa* có nhiều *PhongKham*, mỗi phòng khám có thể có nhiều *GiuongBenh* phục vụ điều trị dài ngày. Hệ thống hỗ trợ bố trí phòng/giường theo đợt điều trị và tính chi phí sử dụng phòng vào hóa đơn.

* **Quản lý kho thuốc, thiết bị, dịch vụ:** Các quan hệ N-N *SuDungThuoc*, *SuDungThietBi*, *SuDungDichVu* ghi nhận vật tư/thiết bị/dịch vụ dùng trong từng sự kiện y tế kèm đơn giá áp dụng tại thời điểm đó. Hệ thống tự động trừ tồn kho thuốc khi kê đơn và cảnh báo khi không đủ tồn kho.

* **Quản lý công lao động và hóa đơn:** Quan hệ *SuDungNhanCong* ghi nhận bác sỹ/y tá tham gia mỗi sự kiện y tế kèm loại công và đơn giá. *HoaDon* (1-1 với sự kiện y tế) và thực thể yếu *HoaDonChiTiet* tự động tổng hợp chi phí khám, chữa, phòng, thuốc và công lao động thành hóa đơn hoàn chỉnh.

Hệ CSDL sinh ra các báo cáo quan trọng: doanh thu theo ngày/theo khoa, thống kê bệnh lý phổ biến, lương nhân viên theo tháng, tồn kho thuốc để hỗ trợ ra quyết định, phân bổ nguồn lực và tối ưu vận hành phòng khám.

## **1.2. Các yêu cầu về dữ liệu cần lưu trữ**

Để hệ CSDL Quản lý phòng khám hoạt động hiệu quả và đáp ứng đúng yêu cầu nghiệp vụ, việc xác định các nhóm dữ liệu cần thu thập là rất quan trọng. Dựa trên phân tích kịch bản thực tế, các nhóm thông tin cần lưu trữ và mô tả chi tiết như sau:

* **Thông tin Nhân viên y tế (Bảng NhanVienYTe, BacSy, YTa, Luong):**

  * **Yêu cầu:** NhanVienYTe là đối tượng trung tâm thực hiện khám chữa bệnh. Cần lưu thông tin nhận diện, phân loại bác sỹ/y tá và dữ liệu để tính lương hằng tháng.

  * **Dữ liệu thu thập:**

    * MaNV (PK): mã định danh duy nhất.

    * HoTen, GioiTinh, NgaySinh, SDT: thông tin cá nhân.

    * MaKhoa (FK): khoa phụ trách.

    * HeSoLuong, NgayVaoLam: dữ liệu tính lương và thâm niên.

    * LoaiNV: phân biệt 'BACSY' / 'YTA' để quyết định bản ghi con tương ứng.

    * Với BacSy: Email, ChuyenMon. Với YTa: ChungChiHanhNghe.

    * Luong: MaNV (FK), Thang, LuongCoBan, TienThuong, TongLuong — lương phát sinh mỗi tháng.

* **Thông tin Bệnh nhân và Danh mục bệnh (Bảng BenhNhan, DanhMucBenh):**

  * **Yêu cầu:** BenhNhan là đối tượng trung tâm cho nghiệp vụ khám – chữa – theo dõi đợt điều trị. Cần lưu thông tin nhận diện và tiền sử liên hệ.

  * **Dữ liệu thu thập:**

    * MaBN (PK): mã định danh duy nhất.

    * HoTen, GioiTinh, NgaySinh, SDT, DiaChi, SoCCCD.

    * DanhMucBenh: MaBenh (PK), TenBenh, NhomBenh, MoTa — chuẩn hóa tên bệnh dùng chung.

* **Thông tin Sự kiện y tế và Đợt điều trị (Bảng SuKienYTe, LanKham, LanChuaBenh, DotDieuTri):**

  * **Yêu cầu:** SuKienYTe là tập cha ISA ghi nhận mọi lượt bệnh nhân đến phòng khám (khám mới hoặc tái khám). DotDieuTri là thực thể kết hợp gộp các lần chữa liên tiếp của cùng một bệnh, hỗ trợ liên kết đệ quy khi bệnh tái phát sau khi đã khỏi.

  * **Dữ liệu thu thập:**

    * SuKienYTe: MaSuKien (PK, sinh tự động qua trigger), MaBN (FK), MaBS (FK), ThoiGian, LoaiSuKien ('KHAM'/'CHUA').

    * LanKham: MaKhoa (FK), TrieuChung, TienKham — có thể mở một đợt điều trị mới.

    * LanChuaBenh: MaDotDieuTri (FK), MaPhong (FK), HinhThucChua, KetLuan, TienChua.

    * DotDieuTri: MaDotDieuTri (PK), MaSuKienKham (FK), MaBenh (FK), MucDoNang, NgayBatDau, NgayKetThuc, TrangThai, MaGiuong (FK), MaDotTruoc (FK, đệ quy).

* **Thông tin Cơ sở vật chất (Bảng PhongKham, GiuongBenh):**

  * **Yêu cầu:** Quản lý cấu trúc vật chất của phòng khám (phòng/giường) để phân bổ hợp lý cho các đợt điều trị dài ngày và tính chi phí sử dụng.

  * **Dữ liệu thu thập:**

    * PhongKham: MaPhong (PK), TenPhong, ChucNang, MaKhoa (FK), DonGiaSuDung.

    * GiuongBenh: MaGiuong (PK), MaPhong (FK), TrangThai, DonGiaNgay.

* **Thông tin Kho thuốc, Thiết bị, Dịch vụ (Bảng Thuoc, ThietBi, DichVuYTe, SuDungThuoc/ThietBi/DichVu):**

  * **Yêu cầu:** Lưu và theo dõi vật tư/thiết bị/dịch vụ dùng trong khám chữa bệnh, kiểm soát tồn kho thuốc tự động.

  * **Dữ liệu thu thập:**

    * Thuoc: MaThuoc (PK), TenThuoc, DonViTinh, DonGia, HangSX, TonKho.

    * ThietBi: MaThietBi (PK), TenThietBi, DonGiaSuDung. DichVuYTe: MaDV (PK), TenDV, DonGia.

    * SuDungThuoc/ThietBi/DichVu: khóa kết hợp (MaSuKien, Ma...), SoLuong, DonGiaApDung.

* **Thông tin Công lao động và Hóa đơn (Bảng LoaiNhanCong, SuDungNhanCong, HoaDon, HoaDonChiTiet):**

  * **Yêu cầu:** Ghi nhận công lao động của bác sỹ/y tá cho từng sự kiện y tế và tổng hợp thành hóa đơn cho bệnh nhân.

  * **Dữ liệu thu thập:**

    * LoaiNhanCong: MaLoaiCong (PK), TenLoaiCong, DonGiaMacDinh.

    * SuDungNhanCong: (MaSuKien, MaNV) khóa kết hợp, MaLoaiCong (FK), VaiTro, DonGiaApDung.

    * HoaDon: MaSuKien (PK, FK), NgayLap, TongTien (tự cập nhật qua trigger), TrangThaiTT.

    * HoaDonChiTiet: (MaSuKien, SoDong) khóa kết hợp, MoTaKhoanMuc, SoTien.

Việc định nghĩa và thu thập đầy đủ, chính xác các nhóm dữ liệu này là nền tảng để xây dựng các ràng buộc toàn vẹn, các trigger/hàm/thủ tục và các chức năng của hệ thống một cách hiệu quả.

## **1.3. Các thao tác trên cơ sở dữ liệu**

Để hệ thống Quản lý phòng khám vận hành hiệu quả và đáp ứng đúng các yêu cầu nghiệp vụ thực tế, cần xác định rõ các nhóm thao tác chính mà hệ CSDL phải hỗ trợ. Các thao tác này được chia theo từng nhóm chức năng tương ứng với các thực thể và mối quan hệ trong mô hình ERD như sau:

### ***1.3.1. Thao tác quản lý danh mục (các bảng gốc)***

* **Đối với bảng** *Khoa, DanhMucBenh, Thuoc, ThietBi, DichVuYTe, LoaiNhanCong***:**

  * Thêm mới, cập nhật, xóa/ngừng sử dụng các danh mục dùng chung.

  * Tra cứu, tìm kiếm theo tên/mã, phân loại theo nhóm.

* **Đối với bảng** *NhanVienYTe, BacSy, YTa***:**

  * Đăng ký nhân viên y tế mới (nhập thông tin cá nhân, khoa, hệ số lương, loại nhân viên).

  * Cập nhật thông tin cá nhân, chuyên môn; chuyển khoa.

  * Xóa/ngừng hoạt động khi nhân viên thôi việc.

* **Đối với bảng** *PhongKham, GiuongBenh***:**

  * Thêm mới phòng/giường, cập nhật chức năng, đơn giá sử dụng.

  * Cập nhật trạng thái giường bệnh (Trống/Đang dùng).

### ***1.3.2. Thao tác nghiệp vụ khám – chữa bệnh (giao dịch trung tâm)***

Đây là nhóm thao tác quan trọng nhất, phản ánh các hoạt động thường xuyên tại phòng khám.

* **Đối với bảng** *BenhNhan*: đăng ký bệnh nhân mới, cập nhật thông tin liên hệ, tra cứu lịch sử khám.

* **Đối với** *SuKienYTe, LanKham*: tiếp nhận bệnh nhân đến khám (sinh mã sự kiện tự động), ghi triệu chứng, chẩn đoán, mở đợt điều trị mới nếu là bệnh phát sinh.

* **Đối với** *DotDieuTri, LanChuaBenh*: ghi nhận lần chữa tiếp theo cho một đợt đang mở (đúng bác sỹ phụ trách — được kiểm tra tự động), tự động đóng đợt khi kết luận đã khỏi, mở đợt tái phát mới liên kết đến đợt trước.

* **Đối với** *SuDungThuoc, SuDungThietBi, SuDungDichVu, SuDungNhanCong*: ghi nhận thuốc/thiết bị/dịch vụ/công lao động sử dụng trong mỗi sự kiện y tế; tự động trừ tồn kho thuốc và kiểm tra đủ số lượng.

### ***1.3.3. Thao tác quản lý kho, cơ sở vật chất và hóa đơn***

* Cập nhật tồn kho khi nhập thêm thuốc; cảnh báo khi thuốc sắp hết hoặc không đủ để kê đơn.

* Bố trí/giải phóng giường bệnh theo đợt điều trị; tra cứu phòng/giường đang sử dụng.

* Xuất hóa đơn tổng hợp cho một sự kiện y tế (tự động cộng tiền khám/chữa/phòng/thuốc/công); tự động cập nhật tổng tiền khi chi tiết hóa đơn thay đổi.

### ***1.3.4. Thao tác thống kê và báo cáo***

* Thống kê doanh thu khám/chữa bệnh theo ngày, theo khoa, theo phòng khám.

* Báo cáo danh sách đợt điều trị đang mở, đợt điều trị đã khỏi, bệnh nhân tái phát.

* Thống kê top bệnh lý phổ biến, top thuốc được kê nhiều nhất.

* Báo cáo lương hằng tháng theo từng bác sỹ/y tá (lương cơ bản \+ thưởng theo hiệu suất).

* Báo cáo tồn kho thuốc, thiết bị sắp hết hoặc cần nhập thêm.

### ***1.3.5. Thao tác bảo trì và toàn vẹn dữ liệu***

* Ràng buộc toàn vẹn tham chiếu giữa các bảng (ví dụ: không xóa được Thuoc đang được tham chiếu trong SuDungThuoc).

* Kiểm tra ràng buộc nghiệp vụ: một đợt điều trị chỉ do đúng một bác sỹ phụ trách thực hiện các lần chữa tiếp theo.

* Sao lưu, phục hồi dữ liệu định kỳ.

* Kiểm tra và cập nhật dữ liệu lỗi, ví dụ: đợt điều trị quá hạn dự kiến nhưng chưa đóng.

Nhờ hệ thống các thao tác này, cơ sở dữ liệu Quản lý phòng khám bệnh tư nhân có thể vận hành linh hoạt, đảm bảo tính toàn vẹn, hỗ trợ nghiệp vụ thực tế và cung cấp dữ liệu chính xác cho công tác quản lý, thống kê, cũng như ra quyết định trong hoạt động của phòng khám.

# **2\. Xây dựng lược đồ thực thể liên kết (ERD)**

![][image2]

## **Tổng quan**

Hệ thống quản lý phòng khám bệnh tư nhân được xây dựng nhằm quản lý nhân sự y tế, bệnh nhân, sự kiện khám/chữa bệnh, đợt điều trị, cơ sở vật chất (phòng/giường), kho thuốc – thiết bị – dịch vụ, công lao động và hóa đơn.

Lược đồ bao gồm 20 tập thực thể chính, 17 mối liên kết (bao gồm 2 quan hệ cha – con ISA), 1 thực thể kết hợp có liên kết đệ quy (DotDieuTri) và 1 thực thể yếu (HoaDonChiTiet).

## **Các tập thực thể:**

* **KHOA (***KHOA***):**

  * **Khóa chính:** *MaKhoa*

  * **Thuộc tính:** *TenKhoa, MoTa*

  * Đại diện cho các khoa/chuyên khoa của phòng khám (Nội, Ngoại, Nhi, RHM…).

* **NHANVIENYTE (***NHANVIENYTE***):**

  * **Khóa chính:** *MaNV*

  * **Thuộc tính:** *HoTen, GioiTinh, NgaySinh, SDT, MaKhoa (FK), HeSoLuong, NgayVaoLam, LoaiNV*

  * Tập cha ISA của BacSy/YTa – toàn bộ nhân sự y tế của phòng khám.

* **BACSY / YTA (***BACSY / YTA***):**

  * **Khóa chính:** *MaNV (PK, FK)*

  * **Thuộc tính:** *Email, ChuyenMon / ChungChiHanhNghe*

  * Hai tập con ISA của NhanVienYTe, phân biệt theo LoaiNV, thuộc mối quan hệ phân tách (Disjoint và Partial) vì một nhân viên y tế về chuyên môn trong một thời điểm chỉ có thể đảm nhận một vai trò duy nhất và nếu sau này mở rộng NhanVienYTe có thể sẽ mở rộng với nhiều loại thực thể khác chứ không chỉ là BaSY và YTa.

* **LUONG (***LUONG***):**

  * **Khóa chính:** *MaLuong*

  * **Thuộc tính:** *MaNV (FK), Thang, LuongCoBan, TienThuong, TongLuong*

  * Bảng lương hằng tháng của từng nhân viên y tế, đây là thực thể yếu vì để xác định lương của ai cần phụ thuộc vào thêm MaNV không chỉ MaLuong

* **PHONGKHAM (***PHONGKHAM***):**

  * **Khóa chính:** *MaPhong*

  * **Thuộc tính:** *TenPhong, ChucNang, MaKhoa (FK), DonGiaSuDung*

  * Cơ sở vật chất – các phòng khám/phòng chữa bệnh thuộc từng khoa.

* **GIUONGBENH (***GIUONGBENH***):**

  * **Khóa chính:** *MaGiuong*

  * **Thuộc tính:** *MaPhong (FK), TrangThai, DonGiaNgay*

  * Giường bệnh (nếu có lưu trú), thuộc một phòng khám.

* **LOAINHANCONG (***LOAINHANCONG***):**

  * **Khóa chính:** *MaLoaiCong*

  * **Thuộc tính:** *TenLoaiCong, DonGiaMacDinh*

  * Danh mục loại công lao động y tế, dùng để tính đơn giá công bác sỹ/y tá.

* **BENHNHAN (***BENHNHAN***):**

  * **Khóa chính:** *MaBN*

  * **Thuộc tính:** *HoTen, GioiTinh, NgaySinh, SDT, DiaChi, SoCCCD*

  * Người đến khám/điều trị tại phòng khám.

* **DANHMUCBENH (***DANHMUCBENH***):**

  * **Khóa chính:** *MaBenh*

  * **Thuộc tính:** *TenBenh, NhomBenh, MoTa*

  * Danh mục các loại bệnh mà phòng khám tiếp nhận điều trị.

* **THUOC (***THUOC***):**

  * **Khóa chính:** *MaThuoc*

  * **Thuộc tính:** *TenThuoc, DonViTinh, DonGia, HangSX, TonKho*

  * Danh mục thuốc trong kho của phòng khám.

* **THIETBI (***THIETBI***):**

  * **Khóa chính:** *MaThietBi*

  * **Thuộc tính:** *TenThietBi, DonGiaSuDung*

  * Danh mục thiết bị y tế được sử dụng trong khám/chữa bệnh.

* **DICHVUYTE (***DICHVUYTE***):**

  * **Khóa chính:** *MaDV*

  * **Thuộc tính:** *TenDV, DonGia*

  * Danh mục các dịch vụ y tế đi kèm (xét nghiệm, chụp chiếu…).

* **SUKIENYTE (***SUKIENYTE***):**

  * **Khóa chính:** *MaSuKien*

  * **Thuộc tính:** *MaBN (FK), MaBS (FK), ThoiGian, LoaiSuKien*

  * Tập cha ISA của LanKham/LanChuaBenh – mọi lượt bệnh nhân đến phòng khám. Thuộc loại disjoint và total vì một sự kiện y tế chỉ có thể là một trong hai loại LanKham hoặc LanChuaBenh và theo nghiệp vụ hiện tại tất cả thực thể con chi có thể là LanKham hoặc LanChuaBenh lên sẽ có thểm total.

* **LANKHAM (***LANKHAM***):**

  * **Khóa chính:** *MaSuKien (PK, FK)*

  * **Thuộc tính:** *MaKhoa (FK), TrieuChung, TienKham*

  * Lượt khám ban đầu, có thể mở một đợt điều trị mới.

* **LANCHUABENH (***LANCHUABENH***):**

  * **Khóa chính:** *MaSuKien (PK, FK)*

  * **Thuộc tính:** *MaDotDieuTri (FK), MaPhong (FK), HinhThucChua, KetLuan, TienChua*

  * Lượt tái khám/chữa bệnh thuộc một đợt điều trị đang mở, diễn ra tại một phòng khám.

* **DOTDIEUTRI (***DOTDIEUTRI***):**

  * **Khóa chính:** *MaDotDieuTri*

  * **Thuộc tính:** *MaSuKienKham (FK), MaBenh (FK), MaGiuong (FK), MaDotTruoc (FK, đệ quy), TrangThai*

  * Thực thể kết hợp gộp các lần chữa liên tiếp cho cùng 1 bệnh thành một đợt; đã chuẩn hoá BCNF (không lưu trực tiếp MaBN/MaBS).

* **SUDUNGTHUOC / SUDUNGTHIETBI / SUDUNGDICHVU (***SUDUNGTHUOC / SUDUNGTHIETBI / SUDUNGDICHVU***):**

  * **Khóa chính:** *MaSuKien+MaThuoc / \+MaThietBi / \+MaDV*

  * **Thuộc tính:** *SoLuong, DonGiaApDung*

  * Các liên kết N-N ghi nhận thuốc/thiết bị/dịch vụ dùng trong từng sự kiện y tế, đây là loại thực thể yếu vì khóa chính tập thực thể này được cấu tạo từ *MaSuKien+MaThuoc / \+MaThietBi / \+MaDV* 

* **SUDUNGNHANCONG (***SUDUNGNHANCONG***):**

  * **Khóa chính:** *MaSuKien \+ MaNV*

  * **Thuộc tính:** *MaLoaiCong (FK), VaiTro, DonGiaApDung*

  * Ghi nhận công lao động (bác sỹ, y tá hỗ trợ) cho từng sự kiện y tế, kèm đơn giá. Thực thể yếu do để xác định được tính duy nhất của bản ghi cần có MaSuKien \+ MaNV làm khóa chính.

* **HOADON (***HOADON***):**

  * **Khóa chính:** *MaSuKien (PK, FK)*

  * **Thuộc tính:** *NgayLap, TongTien, TrangThaiTT*

  * Hóa đơn 1-1 với sự kiện y tế (tự cập nhật TongTien qua trigger).

* **HOADONCHITIET (***HOADONCHITIET***):**

  * **Khóa chính:** *MaSuKien \+ SoDong*

  * **Thuộc tính:** *MoTaKhoanMuc, SoTien*

  * Thực thể yếu phụ thuộc HoaDon – từng khoản mục chi phí trong hóa đơn.

## **Các tập liên kết:**

| Tên liên kết | Thực thể tham gia | Bản chất | Mô tả |
| ----- | ----- | ----- | ----- |
| ISA-1 | NhanVienYTe, BacSy, YTa | Cha \- Con | Phân loại nhân viên y tế thành bác sỹ / y tá |
| ISA-2 | SuKienYTe, LanKham, LanChuaBenh | Cha \- Con | Phân loại sự kiện y tế thành lần khám / lần chữa bệnh |
| thuộc | Khoa – NhanVienYTe | 1 \- N | Mỗi khoa có nhiều nhân viên y tế |
| nhận | NhanVienYTe – Luong | 1 \- N | Mỗi nhân viên nhận lương nhiều tháng |
| có (phòng) | Khoa – PhongKham | 1 \- N | Mỗi khoa có nhiều phòng khám |
| có (giường) | PhongKham – GiuongBenh | 1 \- N | Mỗi phòng khám có thể có nhiều giường bệnh |
| khám | BenhNhan – SuKienYTe – BacSy | 3 ngôi (qua SuKienYTe) | Mỗi sự kiện y tế gắn 1 bệnh nhân, 1 bác sỹ phụ trách |
| diễn ra tại | PhongKham – LanChuaBenh | 1 \- N | Mỗi lần chữa bệnh diễn ra tại một phòng khám |
| bố trí | GiuongBenh – DotDieuTri | 1 \- N | Một giường có thể phục vụ nhiều đợt điều trị (không cùng lúc) |
| gồm | LanKham – DotDieuTri | 1 \- 1 | Một lần khám có thể mở một đợt điều trị mới |
| đợt của | DotDieuTri – LanChuaBenh | 1 \- N | Một đợt điều trị gồm nhiều lần chữa bệnh |
| đệ quy | DotDieuTri – DotDieuTri (MaDotTruoc) | 1 \- N | Một đợt tái phát tham chiếu đợt điều trị trước đó |
| nhân công | SuKienYTe – NhanVienYTe (qua SuDungNhanCong) | N \- N | Ghi nhận (các) nhân viên y tế thực hiện mỗi sự kiện, kèm loại công/đơn giá |
| dùng thuốc / TB / DV | SuKienYTe – Thuoc/ThietBi/DichVuYTe | N \- N | Ghi nhận vật tư, thiết bị, dịch vụ sử dụng trong mỗi sự kiện y tế |
| xuất | SuKienYTe – HoaDon | 1 \- 1 | Mỗi sự kiện y tế phát sinh một hóa đơn |
| gồm (HD) | HoaDon – HoaDonChiTiet | 1 \- N | Một hóa đơn gồm nhiều khoản mục chi tiết |
| thuộc danh mục | DanhMucBenh – DotDieuTri | 1 \- N | Một loại bệnh có thể ứng với nhiều đợt điều trị |

# **3\. Chuyển đổi lược đồ thực thể liên kết (ERD) sang lược đồ quan hệ**

Áp dụng quy tắc ánh xạ ERD sang lược đồ quan hệ:

* Thực thể thường → 1 quan hệ với PK là khóa chính  
* ISA → PK của cha trở thành PK+FK của con  
* Thực thể yếu → khóa kết hợp (khóa sở hữu \+ khóa bộ phận)  
* Liên kết N-N → 1 quan hệ riêng với khóa kết hợp là 2 FK  
* Liên kết 1-N → FK đặt ở bên N  
* Liên kết đệ quy → FK tự tham chiếu)

sơ đồ dưới đây minh họa các thuộc tính (khóa chính – vàng, khóa ngoại – tím, thuộc tính thường – xanh) của các lược đồ quan hệ rút gọn tương ứng:

![][image3]

# **4\. Ánh xạ từ lược đồ E-R sang tập các lược đồ quan hệ**

## **4.1. Các phụ thuộc hàm của từng lược đồ**

| STT | Lược đồ | Phụ thuộc hàm |
| ----- | ----- | ----- |
| 1 | Khoa(MaKhoa, TenKhoa, MoTa) | MaKhoa → TenKhoa, MoTa |
| 2 | NhanVienYTe(MaNV, HoTen, GioiTinh, NgaySinh, SDT, MaKhoa, HeSoLuong, NgayVaoLam, LoaiNV) | MaNV → HoTen, GioiTinh, NgaySinh, SDT, MaKhoa, HeSoLuong, NgayVaoLam, LoaiNV |
| 3 | BacSy(MaNV, Email, ChuyenMon) | MaNV → Email, ChuyenMon |
| 4 | YTa(MaNV, ChungChiHanhNghe) | MaNV → ChungChiHanhNghe |
| 5 | Luong(MaLuong, MaNV, Thang, LuongCoBan, TienThuong, TongLuong) | MaLuong → MaNV, Thang, LuongCoBan, TienThuong, TongLuong ; (MaNV,Thang) → MaLuong |
| 6 | PhongKham(MaPhong, TenPhong, ChucNang, MaKhoa, DonGiaSuDung) | MaPhong → TenPhong, ChucNang, MaKhoa, DonGiaSuDung |
| 7 | GiuongBenh(MaGiuong, MaPhong, TrangThai, DonGiaNgay) | MaGiuong → MaPhong, TrangThai, DonGiaNgay |
| 8 | BenhNhan(MaBN, HoTen, GioiTinh, NgaySinh, SDT, DiaChi, SoCCCD) | MaBN → HoTen, GioiTinh, NgaySinh, SDT, DiaChi, SoCCCD |
| 9 | DanhMucBenh(MaBenh, TenBenh, NhomBenh, MoTa) | MaBenh → TenBenh, NhomBenh, MoTa |
| 10 | SuKienYTe(MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien) | MaSuKien → MaBN, MaBS, ThoiGian, LoaiSuKien |
| 11 | LanKham(MaSuKien, MaKhoa, TrieuChung, TienKham) | MaSuKien → MaKhoa, TrieuChung, TienKham |
| 12 | DotDieuTri(MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, NgayBatDau, NgayKetThuc, TrangThai, MaGiuong, MaDotTruoc) | MaDotDieuTri → tất cả các thuộc tính còn lại (xem mục 6, sau khi tách MaBN/MaBS ra khỏi lược đồ này để đạt BCNF) |
| 13 | LanChuaBenh(MaSuKien, MaDotDieuTri, MaPhong, HinhThucChua, KetLuan, TienChua) | MaSuKien → MaDotDieuTri, MaPhong, HinhThucChua, KetLuan, TienChua |
| 14 | SuDungThuoc(MaSuKien, MaThuoc, SoLuong, DonGiaApDung) | (MaSuKien, MaThuoc) → SoLuong, DonGiaApDung |
| 15 | SuDungNhanCong(MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung) | (MaSuKien, MaNV) → MaLoaiCong, VaiTro, DonGiaApDung |
| 16 | HoaDon(MaSuKien, NgayLap, TongTien, TrangThaiTT) | MaSuKien → NgayLap, TongTien, TrangThaiTT |
| 17 | HoaDonChiTiet(MaSuKien, SoDong, MoTaKhoanMuc, SoTien) | (MaSuKien, SoDong) → MoTaKhoanMuc, SoTien |

## **4.2. Các khóa của lược đồ**

| STT | Lược đồ | Khóa chính | Khóa ngoại |
| ----- | ----- | ----- | ----- |
| 1 | Khoa | MaKhoa | – |
| 2 | NhanVienYTe | MaNV | MaKhoa → Khoa |
| 3 | BacSy | MaNV | MaNV → NhanVienYTe |
| 4 | YTa | MaNV | MaNV → NhanVienYTe |
| 5 | Luong | MaLuong | MaNV → NhanVienYTe |
| 6 | PhongKham | MaPhong | MaKhoa → Khoa |
| 7 | GiuongBenh | MaGiuong | MaPhong → PhongKham |
| 8 | BenhNhan | MaBN | – |
| 9 | DanhMucBenh | MaBenh | – |
| 10 | SuKienYTe | MaSuKien | MaBN → BenhNhan; MaBS → BacSy |
| 11 | LanKham | MaSuKien | MaSuKien → SuKienYTe; MaKhoa → Khoa |
| 12 | DotDieuTri | MaDotDieuTri | MaSuKienKham → LanKham; MaBenh → DanhMucBenh; MaGiuong → GiuongBenh; MaDotTruoc → DotDieuTri |
| 13 | LanChuaBenh | MaSuKien | MaSuKien → SuKienYTe; MaDotDieuTri → DotDieuTri; MaPhong → PhongKham |
| 14 | SuDungThuoc | (MaSuKien, MaThuoc) | MaSuKien → SuKienYTe; MaThuoc → Thuoc |
| 15 | SuDungThietBi | (MaSuKien, MaThietBi) | MaSuKien → SuKienYTe; MaThietBi → ThietBi |
| 16 | SuDungDichVu | (MaSuKien, MaDV) | MaSuKien → SuKienYTe; MaDV → DichVuYTe |
| 17 | SuDungNhanCong | (MaSuKien, MaNV) | MaSuKien → SuKienYTe; MaNV → NhanVienYTe; MaLoaiCong → LoaiNhanCong |
| 18 | HoaDon | MaSuKien | MaSuKien → SuKienYTe |
| 19 | HoaDonChiTiet | (MaSuKien, SoDong) | MaSuKien → HoaDon |

## **4.3. Các lược đồ sau khi được chuẩn hóa (đạt chuẩn BCNF/3NF)**

## **4.3.1 Cơ sở lý thuyết về phụ thuộc hàm và hệ tiên đề Armstrong và Dạng chuẩn BCNF**

1. Định nghĩa phụ thuộc hàm:   
* Cho lược đồ quan hệ $R({A}_{1},{A}_{2},...,{A}_{n})$ và hai tập thuộc tính $X,Y\subseteq R$.   
* Phụ thuộc hàm $X\rightarrow Y$ tồn tại trên $R$ khi và chỉ khi với mọi thể hiện hợp lệ của quan hệ $r(R)$, hai bộ ${t}_{1},{t}_{2}\in r$ nếu thỏa mãn ${t}_{1}[X]={t}_{2}[X]$ thì bắt buộc ${t}_{1}[Y]={t}_{2}[Y]$   
* $X$ được gọi là **vế trái (Determinant)**   
* $Y$ được gọi là **vế phải (Dependent)**   
* Phụ thuộc hàm $X\rightarrow Y$ là **tầm thường (Trivial)** nếu $Y\subseteq X$  
2. Hệ tiên đề Armstrong  
   Để suy diễn các phụ thuộc hàm từ tập phụ thuộc hàm ban đầu $F$, ta sử dụng **Hệ tiên đề Armstrong** (gồm 3 luật cơ bản, là tập luật cần và đủ):  
* **IR1 (Luật phản xạ \- Reflexivity)**: Nếu $Y\subseteq X$ thì $X\rightarrow Y$   
* **IR2 (Luật tăng trưởng \- Augmentation)**: Nếu $X\rightarrow Y$ thì $XZ\rightarrow YZ$ với mọi tập thuộc tính $Z$   
* **IR3 (Luật bắc cầu \- Transitivity)**: Nếu $X\rightarrow Y$ và $Y\rightarrow Z$ thì $X\rightarrow Z$   
  Các luật suy diễn bổ sung được chứng minh từ 3 luật trên  
* **IR4 (Luật chiếu / tách \- Decomposition)**: Nếu $X\rightarrow YZ$ thì $X\rightarrow Y$ và $X\rightarrow Z$   
* **IR5 (Luật hợp / cộng \- Union)**: Nếu $X\rightarrow Y$ và $X\rightarrow Z$ thì $X\rightarrow YZ$   
* **IR6 (Luật giả bắc cầu \- Pseudotransitivity)**: Nếu $X\rightarrow Y$ và $YZ\rightarrow W$ thì $XZ\rightarrow W$   
    
3. Bao đóng của tập thuộc tính (X+) và Siêu khóa   
* $R$: Tập hợp tất cả các thuộc tính (cột) của lược đồ quan hệ/bảng   
* $F$: Tập hợp tất cả các phụ thuộc hàm (quy tắc) xác định trên bảng $R$.  
* $X$: Tập thuộc tính ban đầu cần tính bao đóng ($X\subseteq R$)  
* **Định nghĩa Bao đóng của tập thuộc tính (**${X}^{+}$**):** Tập thuộc tính ${X}^{+}$ gồm tất cả các thuộc tính có thể suy diễn được từ $X$ bằng Hệ tiên đề Armstrong trên tập phụ thuộc hàm $F$, được gọi là **Bao đóng của tập thuộc tính** $X$ **đối với** $F$.  
  * **Thuật toán tính** ${X}^{+}$ **(Closure(X, F)):**  
  * **Khởi tạo**: ${X}^{+}=X$ \[cite: 149\].  
  * **Lặp**: Với mỗi phụ thuộc hàm $W\rightarrow Z\in F$, nếu $W\subseteq {X}^{+}$ thì cập nhật ${X}^{+}={X}^{+}\cup Z$ \[cite: 149\].  
  * **Dừng**: Khi ${X}^{+}$ không thay đổi thêm \[cite: 149\].  
* **Định nghĩa Siêu khóa và Khóa dự bị:**  
* **Siêu khóa (Superkey)**: Cho $X\subseteq R$, nếu bao đóng ${X}^{+}=R$ (xác định được 100% thuộc tính của $R$) thì $X$ chính là một **Siêu khóa** của $R$   
* **Khóa dự bị (Candidate Key)**: Là một Siêu khóa $X$ có tính cực tiểu (tức là không tồn tại tập con thực sự $Y\subset X$ nào mà ${Y}^{+}=R$)  
4. Phân bậc dạng chuẩn 1NF, 2NF, 3NF, BCNF  
* **Chuẩn 1NF:** Mọi thuộc tính trong lược đồ quan hệ đều mang giá trị đơn vị(nguyên tố- Atomic values), không chứa thuộc tính đa trị hay thuộc tính phức hợp.  
* **Chuẩn 2NF:** đạt chuẩn 1NF và mọi thuộc tính không khóa đều phụ thuộc hàm đầy đủ vào khóa chính, không tồn tại thuộc tính không khóa phụ thuộc vào 1 phần của khóa.  
* **Chuẩn 3NF:** Đạt 2NF và với mọi phụ thuộc hàm không tầm thường $X\rightarrow A\in {F}^{+}$ (trong đó $A\notin X$), thỏa mãn **ít nhất 1 trong 2 điều kiện**:   
  * $X$ **là một Siêu khóa (Superkey)** của $R$.  
  * $A$ **là một Thuộc tính khóa (Prime Attribute)** (tức $A$ nằm trong ít nhất một Khóa dự bị của $R$).  
* **Boyce-Codd(BCNF):** Một lược đồ đồ quan hệ R với tập phụ thuộc hàm F đạt BCNF khi và chỉ khi với mọi phụ thuộc hàm không tầm thường $X\rightarrow Y\in {F}^{+}$ (với Y không phải tập con của X), vế trái X bắt buộc phải là một siêu khóa của R.   
5. Thuật toán phân tách BCNF  
* Nếu một lược đồ $R$ chứa phụ thuộc hàm $X\rightarrow Y$ vi phạm chuẩn BCNF (tức là Y không phải tập con của X và $X$ không phải là siêu khóa của $R$), ta thực hiện phân tách $R$ như sau  
* Tính bao đóng ${X}^{+}$ trên tập phụ thuộc hàm $F$  
* Tách $R$ thành hai lược đồ con:  
  * ${R}_{1}={X}^{+}$ (với tập thuộc tính lấy từ bao đóng của $X$)  
  * ${R}_{2}=(R-{X}^{+})\cup X$ (giữ lại $X$ làm khóa ngoại kết nối)  
* Kiểm tra tính thỏa mãn BCNF trên ${R}_{1}$ và ${R}_{2}$. Lặp lại thuật toán nếu vẫn còn lược đồ con vi phạm  
* Thuật toán phân tách BCNF đảm bảo tính chất **Kết nối không mất mát thông tin (Lossless Join)**

## **4.3.2 Phương pháp luận và Quỳ trình chuyển từ 4.1, 4.2 sang lược đồ chuẩn hóa BCNF/3NF**

Để thu được danh sách các lược đồ quan hệ đạt chuẩn BCNF thực hiện quy trình sau: 

1. Xác định tập các phụ thuộc hàm dựa trên yêu cầu nghiệp vụ thực tế.  
2. Tính bao đóng (X+) và xác định các Siêu khóa / Khóa dự bị Với mỗi phụ thuộc hàm X \-\> Y trong F, áp dụng thuật toán \`Closure(X, F)\` để tính bao đóng X+.  
* Nếu X+ \= R, kết luận X chính là một Siêu khóa của R.  
* Tiến hành cắt gọt các thuộc tính dư thừa từ các Siêu khóa để tìm tập hợp tất cả các Khóa dự bị và xác định Khóa chính của bảng  
3. Kiểm tra điều kiện BCNF cho tất cả các phụ thuộc hàm không tầm thường Duyệt qua từng phụ thuộc hàm không tầm thường X \-\> A trong F+:  
* Kiểm tra xem vế trái X có phải là Siêu khóa của R hay không (X+ có bằng R không).  
* Nếu MỌI phụ thuộc hàm trong R đều có vế trái X là Siêu khóa, kết luận lược đồ R ĐẠT CHUẨN BCNF.  
* Nếu tồn tại dù chỉ một phụ thuộc hàm X \-\> A mà X KHÔNG PHẢI là Siêu khóa (X+ khác R), kết luận lược đồ R VI PHẠM CHUẨN BCNF.  
4. Phân tách lược đồ vi phạm về BCNF Nếu R vi phạm BCNF tại phụ thuộc hàm X \-\&gt; Y (X không là siêu khóa):  
* Tính bao đóng X+.  
* Tách R thành hai lược đồ quan hệ con:  
  *  Lược đồ R1 \= X+ (với khóa chính là X).  
  *  Lược đồ R2 \= (R \- X+) hợp X (với các thuộc tính còn lại cộng thêm X làm khóa ngoại).  
* Tiếp tục quay lại Bước 1 để kiểm tra BCNF cho R1 và R2 cho đến khi tất cả các lược đồ con thu được đều đạt chuẩn BCNF.

## **4.3.3 Triển khai thực tế trên hệ CSDL quản lý phòng khám tư nhân**

**1\. Lược đồ quan hệ KHOA**

* Nguồn gốc: Ánh xạ từ Tập thực thể mạnh KHOA trong sơ đồ E-R.  
* Lược đồ ban đầu: KHOA(MaKhoa, TenKhoa, MoTa)  
* Tập phụ thuộc hàm F1:  
  * FD1: MaKhoa \-\> TenKhoa, MoTa  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaKhoa}+ \= {MaKhoa, TenKhoa, MoTa} \= R1 (chứa trọn vẹn 100% thuộc tính của bảng).  
  * Kết luận Khóa: {MaKhoa} là Siêu khóa duy nhất và được chọn làm Khóa chính. Bảng không có khóa ngoại.  
*  Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaKhoa} là Siêu khóa. Lược đồ KHOA đạt chuẩn BCNF

**2\. Lược đồ quan hệ LOAINHANCONG**

* Nguồn gốc: Ánh xạ từ Tập thực thể mạnh LOAINHANCONG trong sơ đồ E-R (danh mục giá công y tế).  
* Lược đồ ban đầu: LOAINHANCONG(MaLoaiCong, TenLoaiCong, DonGiaMacDinh)  
*  Tập phụ thuộc hàm F2:  
  *  FD1: MaLoaiCong \-\> TenLoaiCong, DonGiaMacDinh  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaLoaiCong}+ \= {MaLoaiCong, TenLoaiCong, DonGiaMacDinh} \= R2.  
  * Kết luận Khóa: {MaLoaiCong} là Siêu khóa và được chọn làm Khóa chính. Bảng không có khóa ngoại.  
* Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaLoaiCong} là Siêu khóa. Lược đồ LOAINHANCONG đạt chuẩn BCNF

**3\. Lược đồ quan hệ NHANVIENYTE**

* Nguồn gốc: Ánh xạ từ Tập thực thể lớp cha ISA NHANVIENYTE và mối liên kết 1-N "thuộc" với KHOA.  
* Lược đồ ban đầu: NHANVIENYTE(MaNV, HoTen, GioiTinh, NgaySinh, SDT, MaKhoa, HeSoLuong, NgayVaoLam, LoaiNV)  
* Tập phụ thuộc hàm F3:  
  * FD1: MaNV \-\> HoTen, GioiTinh, NgaySinh, SDT, MaKhoa, HeSoLuong, NgayVaoLam, LoaiNV  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaNV}+ \= R3.  
  *  Kết luận Khóa: {MaNV} là Siêu khóa và được chọn làm Khóa chính. Khóa ngoại MaKhoa tham chiếu KHOA(MaKhoa).  
*  Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaNV} là Siêu khóa. Lược đồ NHANVIENYTE đạt chuẩn BCNF

**4\. Lược đồ quan hệ BACSY**

* Nguồn gốc: Ánh xạ từ Tập thực thể lớp con ISA BACSY (kế thừa từ NHANVIENYTE).  
* Lược đồ ban đầu: BACSY(MaNV, Email, ChuyenMon)  
* Tập phụ thuộc hàm F4:  
  *  FD1: MaNV \-\> Email, ChuyenMon  
*  Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaNV}+ \= R4.  
  * Kết luận Khóa: {MaNV} vừa là Khóa chính vừa là Khóa ngoại tham chiếu NHANVIENYTE(MaNV).  
*  Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaNV} là Siêu khóa. Lược đồ BACSY đạt chuẩn BCNF

**5\. Lược đồ quan hệ YTA**

* Nguồn gốc: Ánh xạ từ Tập thực thể lớp con ISA YTA (kế thừa từ NHANVIENYTE).  
* Lược đồ ban đầu: YTA(MaNV, ChungChiHanhNghe)  
* Tập phụ thuộc hàm F5:  
  * FD1: MaNV \-\> ChungChiHanhNghe  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaNV}+ \= R5.  
  * Kết luận Khóa: {MaNV} vừa là Khóa chính vừa là Khóa ngoại tham chiếu NHANVIENYTE(MaNV).  
* Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaNV} là Siêu khóa. Lược đồ YTA đạt chuẩn BCNF 100%.

**6\. Lược đồ quan hệ LUONG**

* Nguồn gốc: Ánh xạ từ Mối liên kết 1-N "nhận" giữa NHANVIENYTE và LUONG (bảng lương hàng tháng).  
*  Lược đồ ban đầu: LUONG(MaLuong, MaNV, Thang, NgayNhanLuong, LuongCoBan, TienThuong, TongLuong, GhiChu)  
* Tập phụ thuộc hàm F6:  
  * FD1: MaLuong \-\> MaNV, Thang, NgayNhanLuong, LuongCoBan, TienThuong, TongLuong, GhiChu  
  * FD2: (MaNV, Thang) \-\> MaLuong, NgayNhanLuong, LuongCoBan, TienThuong, TongLuong, GhiChu (do quy định mỗi nhân viên chỉ có 1 phiếu lương/tháng \- UNIQUE)  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng FD1: {MaLuong}+ \= R6. Do đó {MaLuong} là Siêu khóa, được chọn làm Khóa chính.  
  * Tính bao đóng FD2: {(MaNV, Thang)}+ \= R6. Do đó {(MaNV, Thang)} cũng là Siêu khóa (Khóa dự bị / Unique).  
  * Khóa ngoại: MaNV tham chiếu NHANVIENYTE(MaNV).  
* Kiểm tra BCNF: Cả hai vế trái X1 \= {MaLuong} và X2 \= {MaNV, Thang} đều có bao đóng bằng R6 (đều là Siêu khóa). Lược đồ LUONG đạt chuẩn BCNF

**NHÓM 2: QUẢN LÝ CƠ SỞ VẬT CHẤT VÀ BỆNH NHÂN (5 LƯỢC ĐỒ)**

**1\. Lược đồ quan hệ PHONGKHAM**

* Nguồn gốc: Ánh xạ từ Tập thực thể mạnh PHONGKHAM và mối liên kết 1-N "có" với KHOA.  
* Lược đồ ban đầu: PHONGKHAM(MaPhong, TenPhong, ChucNang, MaKhoa, DonGiaSuDung)  
* Tập phụ thuộc hàm F7:  
  *  FD1: MaPhong \-\> TenPhong, ChucNang, MaKhoa, DonGiaSuDung  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaPhong}+ \= R7.  
  *  Kết luận Khóa: {MaPhong} là Siêu khóa, chọn làm Khóa chính. Khóa ngoại MaKhoa tham chiếu KHOA(MaKhoa).  
* Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaPhong} là Siêu khóa. Lược đồ PHONGKHAM đạt chuẩn BCNF 100%.

**2\. Lược đồ quan hệ GIUONGBENH**

* Nguồn gốc: Ánh xạ từ Tập thực thể mạnh GIUONGBENH và mối liên kết 1-N "có" với PHONGKHAM.  
* Lược đồ ban đầu: GIUONGBENH(MaGiuong, MaPhong, TrangThai, DonGiaNgay)  
* Tập phụ thuộc hàm F8:  
  * FD1: MaGiuong \-\> MaPhong, TrangThai, DonGiaNgay  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaGiuong}+ \= R8.  
  *  Kết luận Khóa: {MaGiuong} là Siêu khóa, chọn làm Khóa chính. Khóa ngoại MaPhong tham chiếu PHONGKHAM(MaPhong).  
*   Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaGiuong} là Siêu khóa. Lược đồ GIUONGBENH đạt chuẩn BCNF 

**3\. Lược đồ quan hệ BENHNHAN**

* Nguồn gốc: Ánh xạ từ Tập thực thể mạnh BENHNHAN (hồ sơ trung tâm).  
*  Lược đồ ban đầu: BENHNHAN(MaBN, HoTen, GioiTinh, NgaySinh, SDT, DiaChi, SoCCCD, NgayDangKy)  
* Tập phụ thuộc hàm F9:  
  * FD1: MaBN \-\> HoTen, GioiTinh, NgaySinh, SDT, DiaChi, SoCCCD, NgayDangKy  
  * FD2: SoCCCD \-\> MaBN, HoTen, GioiTinh, NgaySinh, SDT, DiaChi, NgayDangKy (ràng buộc duy nhất số CCCD)  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng FD1: {MaBN}+ \= R9 \-\> {MaBN} là Siêu khóa (được chọn làm Khóa chính).  
  * Tính bao đóng FD2: {SoCCCD}+ \= R9 \-\> {SoCCCD} là Siêu khóa (Khóa dự bị / Unique).  
* Kiểm tra BCNF: Cả hai vế trái X1 \= {MaBN} và X2 \= {SoCCCD} đều là Siêu khóa. Lược đồ BENHNHAN đạt chuẩn BCNF

**4\. Lược đồ quan hệ DANHMUCBENH**

* Nguồn gốc: Ánh xạ từ Tập thực thể mạnh DANHMUCBENH (từ điển bệnh y khoa).  
*  Lược đồ ban đầu: DANHMUCBENH(MaBenh, TenBenh, NhomBenh, MoTa)  
* Tập phụ thuộc hàm F10:  
  * FD1: MaBenh \-\> TenBenh, NhomBenh, MoTa  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaBenh}+ \= R10.  
  * Kết luận Khóa: {MaBenh} là Siêu khóa, chọn làm Khóa chính. Bảng không có khóa ngoại.  
*  Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaBenh} là Siêu khóa. Lược đồ DANHMUCBENH đạt chuẩn BCNF 

**5\. Lược đồ quan hệ THAMSOHETHONG**

* Nguồn gốc: Ánh xạ từ Tập thực thể THAMSOHETHONG (lưu cấu hình tham số động).  
* Lược đồ ban đầu: THAMSOHETHONG(TenThamSo, GiaTri)  
* Tập phụ thuộc hàm F11:  
  *  FD1: TenThamSo \-\> GiaTri  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {TenThamSo}+ \= R11.  
  * Kết luận Khóa: {TenThamSo} là Siêu khóa, chọn làm Khóa chính.  
* Kiểm tra BCNF: Duyệt FD1, vế trái X \= {TenThamSo} là Siêu khóa. Lược đồ THAMSOHETHONG đạt chuẩn BCNF

**NHÓM 3: QUẢN LÝ KHO THUỐC, THIẾT BỊ VÀ DỊCH VỤ (3 LƯỢC ĐỒ)**

**1\. Lược đồ quan hệ THUOC**

* Nguồn gốc: Ánh xạ từ Tập thực thể mạnh THUOC (kho thuốc).  
* Lược đồ ban đầu: THUOC(MaThuoc, TenThuoc, DonViTinh, DonGia, HangSX, TonKho)  
* Tập phụ thuộc hàm F12:  
  * FD1: MaThuoc \-\> TenThuoc, DonViTinh, DonGia, HangSX, TonKho  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaThuoc}+ \= R12.  
  * Kết luận Khóa: {MaThuoc} là Siêu khóa, chọn làm Khóa chính.  
*  Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaThuoc} là Siêu khóa. Lược đồ THUOC đạt chuẩn BCNF

**2\. Lược đồ quan hệ THIETBI**

*  Nguồn gốc: Ánh xạ từ Tập thực thể mạnh THIETBI (danh mục máy móc y tế).  
* Lược đồ ban đầu: THIETBI(MaThietBi, TenThietBi, DonGiaSuDung)  
* Tập phụ thuộc hàm F13:  
  * FD1: MaThietBi \-\> TenThietBi, DonGiaSuDung  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaThietBi}+ \= R13.  
  *  Kết luận Khóa: {MaThietBi} là Siêu khóa, chọn làm Khóa chính.  
* Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaThietBi} là Siêu khóa. Lược đồ THIETBI đạt chuẩn BCNF

**3\. Lược đồ quan hệ DICHVUYTE**

* Nguồn gốc: Ánh xạ từ Tập thực thể mạnh DICHVUYTE (bảng giá xét nghiệm/chụp chiếu).  
* Lược đồ ban đầu: DICHVUYTE(MaDV, TenDV, DonGia)  
*  Tập phụ thuộc hàm F14:  
  *  FD1: MaDV \-\> TenDV, DonGia  
*  Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaDV}+ \= R14.  
  * Kết luận Khóa: {MaDV} là Siêu khóa, chọn làm Khóa chính.  
*  Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaDV} là Siêu khóa. Lược đồ DICHVUYTE đạt chuẩn BCNF

**NHÓM 4: QUẢN LÝ SỰ KIỆN Y TẾ VÀ ĐỢT ĐIỀU TRỊ (4 LƯỢC ĐỒ)**

**1\. Lược đồ quan hệ SUKIENYTE**

* Nguồn gốc: Ánh xạ từ Tập thực thể lớp cha ISA SUKIENYTE (ghi nhận mọi lượt bệnh nhân đến phòng khám).  
*  Lược đồ ban đầu: SUKIENYTE(MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien)  
* Tập phụ thuộc hàm F15:  
  * FD1: MaSuKien \-\> MaBN, MaBS, ThoiGian, LoaiSuKien  
*  Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaSuKien}+ \= R15.  
  * Kết luận Khóa: {MaSuKien} là Siêu khóa, chọn làm Khóa chính. Khóa ngoại MaBN tham chiếu BENHNHAN(MaBN), MaBS tham chiếu BACSY(MaNV).  
*  Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaSuKien} là Siêu khóa. Lược đồ SUKIENYTE đạt chuẩn BCNF

**2\. Lược đồ quan hệ LANKHAM**

* Nguồn gốc: Ánh xạ từ Tập thực thể lớp con ISA LANKHAM (lượt khám ban đầu) và liên kết với KHOA.  
* Lược đồ ban đầu: LANKHAM(MaSuKien, MaKhoa, TrieuChung, TienKham)  
* Tập phụ thuộc hàm F16:  
  * FD1: MaSuKien \-\> MaKhoa, TrieuChung, TienKham  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaSuKien}+ \= R16.  
  *  Kết luận Khóa: {MaSuKien} vừa là Khóa chính vừa là Khóa ngoại tham chiếu SUKIENYTE(MaSuKien). Khóa ngoại MaKhoa tham chiếu KHOA(MaKhoa).  
* Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaSuKien} là Siêu khóa. Lược đồ LANKHAM đạt chuẩn BCNF 100%.

**3\. Lược đồ quan hệ DOTDIEUTRI (Trường hợp đặc biệt có phân tách BCNF)**

* Nguồn gốc: Ánh xạ từ Thực thể kết hợp DOTDIEUTRI (gộp chuỗi lần chữa liên tiếp của 1 bệnh) và liên kết đệ quy MaDotTruoc.  
* Lược đồ ban đầu (trước khi phân tách): DOTDIEUTRI\_BAN\_DAU(MaDotDieuTri, MaSuKienKham, MaBenh, MaBN, MaBS, MucDoNang, SoLanChuaDuKien, NgayBatDau, NgayKetThuc, TrangThai, MaGiuong, MaDotTruoc)  
* Tập phụ thuộc hàm F17 ban đầu:  
  * FD1: MaDotDieuTri \-\> MaSuKienKham, MaBenh, MaBN, MaBS, MucDoNang, SoLanChuaDuKien, NgayBatDau, NgayKetThuc, TrangThai, MaGiuong, MaDotTruoc  
  * FD2: MaSuKienKham \-\> MaBN, MaBS (do lần khám khởi đầu xác định duy nhất bệnh nhân và bác sĩ)  
*  Phân tích vi phạm BCNF:  
  * Xét FD1: Vế trái {MaDotDieuTri}+ \= R17 \-\> MaDotDieuTri là Siêu khóa (Thỏa BCNF).  
  * Xét FD2: Vế trái X \= {MaSuKienKham}. Tính bao đóng {MaSuKienKham}+ \= {MaSuKienKham, MaBN, MaBS} khác R17. Do một lần khám có thể chẩn đoán ra nhiều bệnh và mở nhiều đợt điều trị khác nhau, nên MaSuKienKham bị lặp lại \-\> MaSuKienKham KHÔNG PHẢI SIÊU KHÓA. Phụ thuộc hàm FD2 VI PHẠM CHUẨN BCNF\!  
* Thực hiện thuật toán phân tách BCNF:  
  * o   Tách thuộc tính {MaBN, MaBS} ra khỏi DOTDIEUTRI\_BAN\_DAU (vì hai thông tin này đã suy dẫn gián tiếp qua MaSuKienKham \-\> LANKHAM \-\> SUKIENYTE).  
  * o   Lược đồ con R17\_1(MaSuKienKham, MaBN, MaBS) trùng với mối liên kết của LANKHAM/SUKIENYTE nên được lược bỏ để tránh trùng lặp.  
  * o   Lược đồ con R17\_2 chính thức thu được: DOTDIEUTRI(MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, NgayKetThuc, TrangThai, MaGiuong, MaDotTruoc)  
*  Kiểm tra lại BCNF sau khi phân tách: Phụ thuộc hàm duy nhất còn lại là MaDotDieuTri \-\> (tất cả thuộc tính còn lại). Vế trái {MaDotDieuTri} là Siêu khóa duy nhất. Lược đồ DOTDIEUTRI chính thức ĐẠT CHUẨN BCNF 100%\! Khóa ngoại: MaSuKienKham tham chiếu LANKHAM(MaSuKien), MaBenh tham chiếu DANHMUCBENH(MaBenh), MaGiuong tham chiếu GIUONGBENH(MaGiuong), MaDotTruoc tham chiếu DOTDIEUTRI(MaDotDieuTri).

**4\. Lược đồ quan hệ LANCHUABENH**

* Nguồn gốc: Ánh xạ từ Tập thực thể lớp con ISA LANCHUABENH (lượt tái khám/chữa bệnh), liên kết 1-N với DOTDIEUTRI và PHONGKHAM.  
* Lược đồ ban đầu: LANCHUABENH(MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong)  
* Tập phụ thuộc hàm F18:  
  *  FD1: MaSuKien \-\> MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaSuKien}+ \= R18.  
  * Kết luận Khóa: {MaSuKien} vừa là Khóa chính vừa là Khóa ngoại tham chiếu SUKIENYTE(MaSuKien). Khóa ngoại MaDotDieuTri tham chiếu DOTDIEUTRI(MaDotDieuTri), MaPhong tham chiếu PHONGKHAM(MaPhong).  
* Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaSuKien} là Siêu khóa. Lược đồ LANCHUABENH đạt chuẩn BCNF 100%.

**NHÓM 5: TƯƠNG TÁC N-N VÀ HÓA ĐƠN (6 LƯỢC ĐỒ)**

**1\. Lược đồ quan hệ SUDUNGNHANCONG**

*  Nguồn gốc: Ánh xạ từ Mối quan hệ N-N "nhân công" giữa SUKIENYTE, NHANVIENYTE và LOAINHANCONG.  
*  Lược đồ ban đầu: SUDUNGNHANCONG(MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung)  
* Tập phụ thuộc hàm F19:  
  * FD1: (MaSuKien, MaNV) \-\> MaLoaiCong, VaiTro, DonGiaApDung (Lưu ý: DonGiaApDung là đơn giá công snapshot tại thời điểm thực hiện sự kiện, nên phụ thuộc đầy đủ vào toàn bộ khóa phức hợp).  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {(MaSuKien, MaNV)}+ \= R19.  
  * Kết luận Khóa: Khóa phức hợp {(MaSuKien, MaNV)} là Siêu khóa và được chọn làm Khóa chính. Khóa ngoại MaSuKien tham chiếu SUKIENYTE(MaSuKien), MaNV tham chiếu NHANVIENYTE(MaNV), MaLoaiCong tham chiếu LOAINHANCONG(MaLoaiCong).  
* Kiểm tra BCNF: Duyệt FD1, vế trái X \= {(MaSuKien, MaNV)} là Siêu khóa. Lược đồ SUDUNGNHANCONG đạt chuẩn BCNF

**2\. Lược đồ quan hệ SUDUNGTHUOC**

* Nguồn gốc: Ánh xạ từ Mối quan hệ N-N "sử dụng thuốc" giữa SUKIENYTE và THUOC.  
* Lược đồ ban đầu: SUDUNGTHUOC(MaSuKien, MaThuoc, SoLuong, DonGiaApDung)  
* Tập phụ thuộc hàm F20:  
  * FD1: (MaSuKien, MaThuoc) \-\> SoLuong, DonGiaApDung (Lưu ý: DonGiaApDung là đơn giá thuốc snapshot tại thời điểm kê đơn).  
* Tính bao đóng và xác định Siêu khóa:  
  *  Tính bao đóng: {(MaSuKien, MaThuoc)}+ \= R20.  
  * Kết luận Khóa: Khóa phức hợp {(MaSuKien, MaThuoc)} là Siêu khóa, chọn làm Khóa chính. Khóa ngoại MaSuKien tham chiếu SUKIENYTE(MaSuKien), MaThuoc tham chiếu THUOC(MaThuoc).  
*  Kiểm tra BCNF: Duyệt FD1, vế trái X \= {(MaSuKien, MaThuoc)} là Siêu khóa. Lược đồ SUDUNGTHUOC đạt chuẩn BCNF

**3\. Lược đồ quan hệ SUDUNGTHIETBI**

* Nguồn gốc: Ánh xạ từ Mối quan hệ N-N "sử dụng thiết bị" giữa SUKIENYTE và THIETBI.  
*  Lược đồ ban đầu: SUDUNGTHIETBI(MaSuKien, MaThietBi, SoLuong, DonGiaApDung)  
* Tập phụ thuộc hàm F21:  
  *  FD1: (MaSuKien, MaThietBi) \-\> SoLuong, DonGiaApDung  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {(MaSuKien, MaThietBi)}+ \= R21.  
  * Kết luận Khóa: Khóa phức hợp {(MaSuKien, MaThietBi)} là Siêu khóa, chọn làm Khóa chính. Khóa ngoại MaSuKien tham chiếu SUKIENYTE(MaSuKien), MaThietBi tham chiếu THIETBI(MaThietBi).  
*  Kiểm tra BCNF: Duyệt FD1, vế trái X \= {(MaSuKien, MaThietBi)} là Siêu khóa. Lược đồ SUDUNGTHIETBI đạt chuẩn BCNF 100%.

**4\. Lược đồ quan hệ SUDUNGDICHVU**

* Nguồn gốc: Ánh xạ từ Mối quan hệ N-N "sử dụng dịch vụ" giữa SUKIENYTE và DICHVUYTE.  
* Lược đồ ban đầu: SUDUNGDICHVU(MaSuKien, MaDV, SoLuong, DonGiaApDung)  
* Tập phụ thuộc hàm F22:  
  * FD1: (MaSuKien, MaDV) \-\> SoLuong, DonGiaApDung  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {(MaSuKien, MaDV)}+ \= R22.  
  * Kết luận Khóa: Khóa phức hợp {(MaSuKien, MaDV)} là Siêu khóa, chọn làm Khóa chính. Khóa ngoại MaSuKien tham chiếu SUKIENYTE(MaSuKien), MaDV tham chiếu DICHVUYTE(MaDV).  
* Kiểm tra BCNF: Duyệt FD1, vế trái X \= {(MaSuKien, MaDV)} là Siêu khóa. Lược đồ SUDUNGDICHVU đạt chuẩn BCNF 100%.

**5\. Lược đồ quan hệ HOADON**

*  Nguồn gốc: Ánh xạ từ Mối liên kết 1-1 "phát sinh" giữa SUKIENYTE và HOADON.  
*  Lược đồ ban đầu: HOADON(MaSuKien, NgayLap, TongTien, TrangThaiTT)  
* Tập phụ thuộc hàm F23:  
  *  FD1: MaSuKien \-\> NgayLap, TongTien, TrangThaiTT (Thuộc tính TongTien được tổng hợp và tự động cập nhật duy trì thông qua Trigger trg\_capnhat\_tong\_tien).  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {MaSuKien}+ \= R23.  
  * Kết luận Khóa: {MaSuKien} vừa là Khóa chính vừa là Khóa ngoại tham chiếu SUKIENYTE(MaSuKien).  
* Kiểm tra BCNF: Duyệt FD1, vế trái X \= {MaSuKien} là Siêu khóa. Lược đồ HOADON đạt chuẩn BCNF 

**6\. Lược đồ quan hệ HOADONCHITIET**

*  Nguồn gốc: Ánh xạ từ Tập thực thể yếu HOADONCHITIET và liên kết định danh "gồm" với HOADON.  
* Lược đồ ban đầu: HOADONCHITIET(MaSuKien, SoDong, MoTaKhoanMuc, SoTien)  
* Tập phụ thuộc hàm F24:  
  * FD1: (MaSuKien, SoDong) \-\> MoTaKhoanMuc, SoTien  
* Tính bao đóng và xác định Siêu khóa:  
  * Tính bao đóng: {(MaSuKien, SoDong)}+ \= R24.  
  * Kết luận Khóa: Khóa phức hợp gồm (MaSuKien, SoDong) là Siêu khóa và làm Khóa chính. Trong đó MaSuKien là Khóa ngoại tham chiếu HOADON(MaSuKien), SoDong là khóa bộ phận (discriminator).  
*  Kiểm tra BCNF: Duyệt FD1, vế trái X \= {(MaSuKien, SoDong)} là Siêu khóa. Lược đồ HOADONCHITIET đạt chuẩn BCNF

**Danh sách lược đồ chuẩn hóa cuối cùng (24 lược đồ):**

* **KHOA**(MaKhoa, TenKhoa, MoTa)

* **LOAINHANCONG**(MaLoaiCong, TenLoaiCong, DonGiaMacDinh)

* **NHANVIENYTE**(MaNV, HoTen, GioiTinh, NgaySinh, SDT, MaKhoa, HeSoLuong, NgayVaoLam, LoaiNV)

* **BACSY**(MaNV, Email, ChuyenMon)

* **YTA**(MaNV, ChungChiHanhNghe)

* **LUONG**(MaLuong, MaNV, Thang, NgayNhanLuong, LuongCoBan, TienThuong, TongLuong, GhiChu)

* **PHONGKHAM**(MaPhong, TenPhong, ChucNang, MaKhoa, DonGiaSuDung)

* **GIUONGBENH**(MaGiuong, MaPhong, TrangThai, DonGiaNgay)

* **BENHNHAN**(MaBN, HoTen, GioiTinh, NgaySinh, SDT, DiaChi, SoCCCD, NgayDangKy)

* **DANHMUCBENH**(MaBenh, TenBenh, NhomBenh, MoTa)

* **THUOC**(MaThuoc, TenThuoc, DonViTinh, DonGia, HangSX, TonKho)

* **THIETBI**(MaThietBi, TenThietBi, DonGiaSuDung)

* **DICHVUYTE**(MaDV, TenDV, DonGia)

* **THAMSOHETHONG**(TenThamSo, GiaTri)

* **SUKIENYTE**(MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien)

* **LANKHAM**(MaSuKien, MaKhoa, TrieuChung, TienKham)

* **DOTDIEUTRI**(MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang, SoLanChuaDuKien, NgayBatDau, NgayKetThuc, TrangThai, MaGiuong, MaDotTruoc)

* **LANCHUABENH**(MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong)

* **SUDUNGNHANCONG**(MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung)

* **SUDUNGTHUOC**(MaSuKien, MaThuoc, SoLuong, DonGiaApDung)

* **SUDUNGTHIETBI**(MaSuKien, MaThietBi, SoLuong, DonGiaApDung)

* **SUDUNGDICHVU**(MaSuKien, MaDV, SoLuong, DonGiaApDung)

* **HOADON**(MaSuKien, NgayLap, TongTien, TrangThaiTT)

* **HOADONCHITIET**(MaSuKien, SoDong, MoTaKhoanMuc, SoTien)

## **4.4. Mối liên hệ giữa các khóa**

* **NhanVienYTe** là trung tâm kết nối nhân sự: liên kết với BacSy, YTa (ISA), Luong, PhongKham (gián tiếp qua Khoa), SuKienYTe, SuDungNhanCong → MaNV xuất hiện làm khóa ngoại ở nhiều bảng.

* **SuKienYTe** là trung tâm kết nối nghiệp vụ: liên kết với LanKham/LanChuaBenh (ISA), SuDungThuoc/ThietBi/DichVu/NhanCong, HoaDon → MaSuKien xuất hiện làm khóa chính/khóa ngoại ở gần một nửa số bảng trong hệ thống.

* **DotDieuTri** liên kết LanKham (mở đợt), LanChuaBenh (các lần chữa), GiuongBenh (bố trí), DanhMucBenh (loại bệnh) và tự tham chiếu chính nó (MaDotTruoc) để biểu diễn tái phát.

* **PhongKham** liên kết Khoa, GiuongBenh, LanChuaBenh — là trung tâm quản lý cơ sở vật chất.

* **HoaDon** liên kết 1-1 với SuKienYTe và 1-N với HoaDonChiTiet (thực thể yếu).

# **5\. Cài đặt hệ thống**

Lược đồ ở dạng chuẩn BCNF/3NF, nhóm xây dựng 24 bảng dữ liệu trên PostgreSQL, kèm 5 trigger, 2 function, 2 view và 6 stored procedure (thủ tục lưu trữ dùng làm transaction) theo đúng yêu cầu bắt buộc của đề cương (tối thiểu 5 trigger, tối thiểu 5 transaction).

## **5.1. Tạo cơ sở dữ liệu và các bảng trên PostgreSQL**

CREATE DATABASE phongkham\_db;

\\c phongkham\_db

\-- PHẦN 1: DANH MỤC GỐC (không phụ thuộc bảng nào khác)

\-- \=====================================================================

 

CREATE TABLE Khoa (

    MaKhoa      VARCHAR(10) PRIMARY KEY,

    TenKhoa     VARCHAR(100) NOT NULL,

    MoTa        TEXT

);

 

CREATE TABLE LoaiNhanCong (

    MaLoaiCong    VARCHAR(10) PRIMARY KEY,

    TenLoaiCong   VARCHAR(100) NOT NULL,

    DonGiaMacDinh NUMERIC(12,0) NOT NULL DEFAULT 0

);

 

CREATE TABLE BenhNhan (

    MaBN        VARCHAR(10) PRIMARY KEY,

    HoTen       VARCHAR(100) NOT NULL,

    GioiTinh    CHAR(1) CHECK (GioiTinh IN ('M','F')),

    NgaySinh    DATE,

    SDT         VARCHAR(15),

    DiaChi      VARCHAR(200),

    SoCCCD      VARCHAR(20) UNIQUE,

    NgayDangKy  DATE NOT NULL DEFAULT CURRENT\_DATE

);

 

CREATE TABLE DanhMucBenh (

    MaBenh      VARCHAR(10) PRIMARY KEY,

    TenBenh     VARCHAR(150) NOT NULL,

    NhomBenh    VARCHAR(50),

    MoTa        TEXT

);

 

CREATE TABLE Thuoc (

    MaThuoc     VARCHAR(10) PRIMARY KEY,

    TenThuoc    VARCHAR(150) NOT NULL,

    DonViTinh   VARCHAR(20),

    DonGia      NUMERIC(12,0) NOT NULL CHECK (DonGia \>= 0),

    HangSX      VARCHAR(100),

    TonKho      INT DEFAULT 0 CHECK (TonKho \>= 0\)

);

 

CREATE TABLE ThietBi (

    MaThietBi    VARCHAR(10) PRIMARY KEY,

    TenThietBi   VARCHAR(150) NOT NULL,

    DonGiaSuDung NUMERIC(12,0) NOT NULL CHECK (DonGiaSuDung \>= 0\)

);

 

CREATE TABLE DichVuYTe (

    MaDV        VARCHAR(10) PRIMARY KEY,

    TenDV       VARCHAR(150) NOT NULL,

    DonGia      NUMERIC(12,0) NOT NULL CHECK (DonGia \>= 0\)

);

 

CREATE TABLE ThamSoHeThong (

    TenThamSo   VARCHAR(50) PRIMARY KEY,

    GiaTri      NUMERIC(14,0) NOT NULL

);

 

\-- \=====================================================================

\-- PHẦN 2: NHÂN SỰ (NHANVIENYTE là lớp cha ISA của BACSY / YTA) \+ LƯƠNG

\-- \=====================================================================

 

CREATE TABLE NhanVienYTe (

    MaNV        VARCHAR(10) PRIMARY KEY,

    HoTen       VARCHAR(100) NOT NULL,

    GioiTinh    CHAR(1) CHECK (GioiTinh IN ('M','F')),

    NgaySinh    DATE,

    SDT         VARCHAR(15),

    MaKhoa      VARCHAR(10) NOT NULL REFERENCES Khoa(MaKhoa),

    HeSoLuong   NUMERIC(4,2) NOT NULL CHECK (HeSoLuong \> 0),

    NgayVaoLam  DATE NOT NULL,

    LoaiNV      VARCHAR(10) NOT NULL CHECK (LoaiNV IN ('BACSY','YTA'))

);

 

CREATE TABLE BacSy (

    MaBS        VARCHAR(10) PRIMARY KEY REFERENCES NhanVienYTe(MaNV),

    Email       VARCHAR(100),

    ChuyenMon   VARCHAR(100)

);

 

CREATE TABLE YTa (

    MaYT        VARCHAR(10) PRIMARY KEY REFERENCES NhanVienYTe(MaNV),

    ChungChiHanhNghe VARCHAR(100)

);

 

CREATE TABLE Luong (

    MaLuong       VARCHAR(20) PRIMARY KEY,

    MaNV          VARCHAR(10) NOT NULL REFERENCES NhanVienYTe(MaNV),

    Thang         DATE NOT NULL,

    NgayNhanLuong DATE,

    LuongCoBan    NUMERIC(12,0) NOT NULL,

    TienThuong    NUMERIC(12,0) DEFAULT 0,

    TongLuong     NUMERIC(12,0) NOT NULL,

    GhiChu        TEXT,

    UNIQUE (MaNV, Thang)

);

 

\-- \=====================================================================

\-- PHẦN 3: CƠ SỞ VẬT CHẤT (PHÒNG KHÁM, GIƯỜNG BỆNH)

\-- \=====================================================================

 

CREATE TABLE PhongKham (

    MaPhong      VARCHAR(10) PRIMARY KEY,

    TenPhong     VARCHAR(100) NOT NULL,

    ChucNang     VARCHAR(100),

    MaKhoa       VARCHAR(10) NOT NULL REFERENCES Khoa(MaKhoa),

    DonGiaSuDung NUMERIC(12,0) NOT NULL DEFAULT 0

);

 

CREATE TABLE GiuongBenh (

    MaGiuong    VARCHAR(10) PRIMARY KEY,

    MaPhong     VARCHAR(10) NOT NULL REFERENCES PhongKham(MaPhong),

    TrangThai   VARCHAR(20) DEFAULT 'Trong',

    DonGiaNgay  NUMERIC(12,0) NOT NULL DEFAULT 0

);

 

\-- \=====================================================================

\-- PHẦN 4: SỰ KIỆN Y TẾ (SUKIENYTE là lớp cha ISA của LANKHAM / LANCHUABENH)

\--         \+ ĐỢT ĐIỀU TRỊ (thực thể kết hợp, đã chuẩn hóa BCNF)

\-- \=====================================================================

 

CREATE SEQUENCE seq\_sukien START 1;

 

CREATE TABLE SuKienYTe (

    MaSuKien    VARCHAR(50) PRIMARY KEY,

    MaBN        VARCHAR(10) NOT NULL REFERENCES BenhNhan(MaBN),

    MaBS        VARCHAR(10) NOT NULL REFERENCES BacSy(MaBS),

    ThoiGian    TIMESTAMP NOT NULL,

    LoaiSuKien  VARCHAR(10) NOT NULL CHECK (LoaiSuKien IN ('KHAM','CHUA'))

);

 

CREATE TABLE LanKham (

    MaSuKien    VARCHAR(50) PRIMARY KEY REFERENCES SuKienYTe(MaSuKien),

    MaKhoa      VARCHAR(10) NOT NULL REFERENCES Khoa(MaKhoa),

    TrieuChung  TEXT,

    TienKham    NUMERIC(12,0) NOT NULL DEFAULT 0

);

 

\-- DOTDIEUTRI đã chuẩn hóa BCNF: KHÔNG lưu MaBN, MaBS trực tiếp

\-- (suy dẫn qua MaSuKienKham \-\> LanKham \-\> SuKienYTe, xem view v\_DotDieuTri\_ChiTiet)

CREATE TABLE DotDieuTri (

    MaDotDieuTri  VARCHAR(50) PRIMARY KEY,

    MaSuKienKham  VARCHAR(50) NOT NULL REFERENCES LanKham(MaSuKien),

    MaBenh        VARCHAR(10) NOT NULL REFERENCES DanhMucBenh(MaBenh),

    MucDoNang     VARCHAR(10) CHECK (MucDoNang IN ('Nhe','Vua','Nang')),

    SoLanChuaDuKien INT,

    NgayBatDau    DATE NOT NULL,

    NgayKetThuc   DATE,

    TrangThai     VARCHAR(20) NOT NULL DEFAULT 'DangDieuTri'

                  CHECK (TrangThai IN ('DangDieuTri','DaKhoi')),

    MaGiuong      VARCHAR(10) REFERENCES GiuongBenh(MaGiuong),

    MaDotTruoc    VARCHAR(50) REFERENCES DotDieuTri(MaDotDieuTri),

    CHECK (NgayKetThuc IS NULL OR NgayKetThuc \>= NgayBatDau)

);

 

CREATE TABLE LanChuaBenh (

    MaSuKien     VARCHAR(50) PRIMARY KEY REFERENCES SuKienYTe(MaSuKien),

    MaDotDieuTri VARCHAR(50) NOT NULL REFERENCES DotDieuTri(MaDotDieuTri),

    HinhThucChua VARCHAR(30) NOT NULL,

    KetLuan      TEXT,

    TienChua     NUMERIC(12,0) NOT NULL DEFAULT 0,

    MaPhong      VARCHAR(10) NOT NULL REFERENCES PhongKham(MaPhong)

);

 

\-- \=====================================================================

\-- PHẦN 5: SỬ DỤNG NHÂN CÔNG / THUỐC / THIẾT BỊ / DỊCH VỤ (N-N)

\-- \=====================================================================

 

CREATE TABLE SuDungNhanCong (

    MaSuKien     VARCHAR(50) REFERENCES SuKienYTe(MaSuKien),

    MaNV         VARCHAR(10) REFERENCES NhanVienYTe(MaNV),

    MaLoaiCong   VARCHAR(10) NOT NULL REFERENCES LoaiNhanCong(MaLoaiCong),

    VaiTro       VARCHAR(50),

    DonGiaApDung NUMERIC(12,0) NOT NULL,

    PRIMARY KEY (MaSuKien, MaNV)

);

 

CREATE TABLE SuDungThuoc (

    MaSuKien     VARCHAR(50) REFERENCES SuKienYTe(MaSuKien),

    MaThuoc      VARCHAR(10) REFERENCES Thuoc(MaThuoc),

    SoLuong      INT NOT NULL CHECK (SoLuong \> 0),

    DonGiaApDung NUMERIC(12,0) NOT NULL,

    PRIMARY KEY (MaSuKien, MaThuoc)

);

 

CREATE TABLE SuDungThietBi (

    MaSuKien     VARCHAR(50) REFERENCES SuKienYTe(MaSuKien),

    MaThietBi    VARCHAR(10) REFERENCES ThietBi(MaThietBi),

    SoLuong      INT NOT NULL CHECK (SoLuong \> 0\) DEFAULT 1,

    DonGiaApDung NUMERIC(12,0) NOT NULL,

    PRIMARY KEY (MaSuKien, MaThietBi)

);

 

CREATE TABLE SuDungDichVu (

    MaSuKien     VARCHAR(50) REFERENCES SuKienYTe(MaSuKien),

    MaDV         VARCHAR(10) REFERENCES DichVuYTe(MaDV),

    SoLuong      INT NOT NULL CHECK (SoLuong \> 0\) DEFAULT 1,

    DonGiaApDung NUMERIC(12,0) NOT NULL,

    PRIMARY KEY (MaSuKien, MaDV)

);

 

\-- \=====================================================================

\-- PHẦN 6: HÓA ĐƠN (HOADON 1-1 với SUKIENYTE, HOADONCHITIET là thực thể yếu)

\-- \=====================================================================

 

CREATE TABLE HoaDon (

    MaSuKien    VARCHAR(50) PRIMARY KEY REFERENCES SuKienYTe(MaSuKien),

    NgayLap     TIMESTAMP NOT NULL DEFAULT now(),

    TongTien    NUMERIC(14,0) NOT NULL DEFAULT 0,

    TrangThaiTT VARCHAR(20) DEFAULT 'ChuaThanhToan'

);

 

CREATE TABLE HoaDonChiTiet (

    MaSuKien     VARCHAR(50) REFERENCES HoaDon(MaSuKien),

    SoDong       INT NOT NULL,

    MoTaKhoanMuc VARCHAR(200),

    SoTien       NUMERIC(12,0) NOT NULL,

    PRIMARY KEY (MaSuKien, SoDong)

);

 

\-- \=====================================================================

## **5.2. Trigger, Function, View và Stored Procedure**

Theo yêu cầu của đề cương: hệ thống cài đặt 5 trigger bắt buộc (PHẦN 7), 2 function và 2 view (PHẦN 8–9), cùng 6 stored procedure đóng vai trò transaction nghiệp vụ (PHẦN 10, vượt yêu cầu tối thiểu 5).

\-- PHẦN 7: TRIGGER (5 trigger bắt buộc)

\-- \=====================================================================

 

\-- 7.1 Sinh mã sự kiện y tế tự động: \<MaKhoa\>-\<MaBS\>-\<K/C\>-\<YYYYMMDD\>-\<STT\>

\--     Chỉ chạy khi INSERT để MaSuKien \= NULL (nếu tự cung cấp mã thì giữ nguyên,

\--     đây là cách dữ liệu mẫu ở file 02 dùng mã cố định để dễ tham chiếu).

CREATE OR REPLACE FUNCTION sinh\_ma\_sukien() RETURNS TRIGGER AS \$\$

DECLARE v\_MaKhoa VARCHAR(10); v\_stt INT;

BEGIN

  SELECT MaKhoa INTO v\_MaKhoa FROM NhanVienYTe WHERE MaNV \= NEW.MaBS;

  v\_stt := nextval('seq\_sukien');

  NEW.MaSuKien := v\_MaKhoa || '-' || NEW.MaBS || '-' ||

                  CASE NEW.LoaiSuKien WHEN 'KHAM' THEN 'K' ELSE 'C' END || '-' ||

                  to\_char(NEW.ThoiGian,'YYYYMMDD') || '-' || lpad(v\_stt::text,4,'0');

  RETURN NEW;

END; \$\$ LANGUAGE plpgsql;

 

CREATE TRIGGER trg\_sinh\_ma\_sukien

BEFORE INSERT ON SuKienYTe

FOR EACH ROW WHEN (NEW.MaSuKien IS NULL)

EXECUTE FUNCTION sinh\_ma\_sukien();

 

\-- 7.2 Ràng buộc "một bác sỹ duy nhất" cho toàn bộ đợt điều trị

CREATE OR REPLACE FUNCTION kiemtra\_bacsy\_dotdieutri() RETURNS TRIGGER AS \$\$

DECLARE v\_MaBS\_ChuaBenh VARCHAR(10);

        v\_MaBS\_PhuTrach VARCHAR(10);

BEGIN

  SELECT MaBS INTO v\_MaBS\_ChuaBenh FROM SuKienYTe WHERE MaSuKien \= NEW.MaSuKien;

 

  SELECT se.MaBS INTO v\_MaBS\_PhuTrach

  FROM DotDieuTri dt

  JOIN SuKienYTe se ON se.MaSuKien \= dt.MaSuKienKham

  WHERE dt.MaDotDieuTri \= NEW.MaDotDieuTri;

 

  IF v\_MaBS\_ChuaBenh \<\> v\_MaBS\_PhuTrach THEN

     RAISE EXCEPTION 'Lan chua benh (%): phai do dung bac sy phu trach (%) thuc hien, khong phai %',

       NEW.MaSuKien, v\_MaBS\_PhuTrach, v\_MaBS\_ChuaBenh;

  END IF;

  RETURN NEW;

END; \$\$ LANGUAGE plpgsql;

 

CREATE TRIGGER trg\_kiemtra\_bacsy

BEFORE INSERT OR UPDATE ON LanChuaBenh

FOR EACH ROW EXECUTE FUNCTION kiemtra\_bacsy\_dotdieutri();

 

\-- 7.3 Tự động đóng đợt điều trị khi có kết luận "đã khỏi"

CREATE OR REPLACE FUNCTION dong\_dot\_dieu\_tri() RETURNS TRIGGER AS \$\$

BEGIN

  IF NEW.KetLuan ILIKE '%khoi%' THEN

     UPDATE DotDieuTri

     SET TrangThai \= 'DaKhoi',

         NgayKetThuc \= (SELECT ThoiGian::date FROM SuKienYTe WHERE MaSuKien \= NEW.MaSuKien)

     WHERE MaDotDieuTri \= NEW.MaDotDieuTri;

  END IF;

  RETURN NEW;

END; \$\$ LANGUAGE plpgsql;

 

CREATE TRIGGER trg\_dong\_dot\_dieu\_tri

AFTER INSERT OR UPDATE ON LanChuaBenh

FOR EACH ROW EXECUTE FUNCTION dong\_dot\_dieu\_tri();

 

\-- 7.4 Kiểm soát tồn kho thuốc khi kê đơn

CREATE OR REPLACE FUNCTION tru\_ton\_kho\_thuoc() RETURNS TRIGGER AS \$\$

DECLARE v\_TonKho INT;

BEGIN

  SELECT TonKho INTO v\_TonKho FROM Thuoc WHERE MaThuoc \= NEW.MaThuoc FOR UPDATE;

  IF v\_TonKho \< NEW.SoLuong THEN

     RAISE EXCEPTION 'Thuoc % khong du ton kho (con %, can %)', NEW.MaThuoc, v\_TonKho, NEW.SoLuong;

  END IF;

  UPDATE Thuoc SET TonKho \= TonKho \- NEW.SoLuong WHERE MaThuoc \= NEW.MaThuoc;

  RETURN NEW;

END; \$\$ LANGUAGE plpgsql;

 

CREATE TRIGGER trg\_tru\_ton\_kho

BEFORE INSERT ON SuDungThuoc

FOR EACH ROW EXECUTE FUNCTION tru\_ton\_kho\_thuoc();

 

\-- 7.5 Tự động cập nhật tổng tiền hóa đơn khi chi tiết hóa đơn thay đổi

CREATE OR REPLACE FUNCTION capnhat\_tong\_tien\_hoadon() RETURNS TRIGGER AS \$\$

DECLARE v\_MaSuKien VARCHAR(50);

BEGIN

  v\_MaSuKien := COALESCE(NEW.MaSuKien, OLD.MaSuKien);

  UPDATE HoaDon

  SET TongTien \= (SELECT COALESCE(SUM(SoTien),0) FROM HoaDonChiTiet WHERE MaSuKien \= v\_MaSuKien)

  WHERE MaSuKien \= v\_MaSuKien;

  RETURN NULL;

END; \$\$ LANGUAGE plpgsql;

 

CREATE TRIGGER trg\_capnhat\_tong\_tien

AFTER INSERT OR UPDATE OR DELETE ON HoaDonChiTiet

FOR EACH ROW EXECUTE FUNCTION capnhat\_tong\_tien\_hoadon();

 

\-- \=====================================================================

\-- PHẦN 8: FUNCTION

\-- \=====================================================================

 

\-- 8.1 Tìm đợt điều trị đang mở của 1 bệnh nhân cho 1 bệnh cụ thể

CREATE OR REPLACE FUNCTION fn\_DotDangDieuTri(p\_MaBN VARCHAR, p\_MaBenh VARCHAR)

RETURNS VARCHAR AS \$\$

DECLARE v\_MaDot VARCHAR(50);

BEGIN

  SELECT dt.MaDotDieuTri INTO v\_MaDot

  FROM DotDieuTri dt

  JOIN SuKienYTe se ON se.MaSuKien \= dt.MaSuKienKham

  WHERE se.MaBN \= p\_MaBN AND dt.MaBenh \= p\_MaBenh AND dt.TrangThai \= 'DangDieuTri'

  LIMIT 1;

  RETURN v\_MaDot;

END; \$\$ LANGUAGE plpgsql;

 

\-- 8.2 Tính lương 1 bác sỹ trong 1 tháng cụ thể

CREATE OR REPLACE FUNCTION fn\_TinhLuongBacSy(p\_MaBS VARCHAR, p\_Thang DATE)

RETURNS NUMERIC AS \$\$

DECLARE v\_LuongCoBan NUMERIC; v\_SoDot INT; v\_DonGiaThuong NUMERIC;

BEGIN

  SELECT nv.HeSoLuong \* ts.GiaTri INTO v\_LuongCoBan

  FROM NhanVienYTe nv, ThamSoHeThong ts

  WHERE nv.MaNV \= p\_MaBS AND ts.TenThamSo \= 'LUONG\_CO\_SO';

 

  SELECT COUNT(\*) INTO v\_SoDot

  FROM DotDieuTri dt

  JOIN SuKienYTe se ON se.MaSuKien \= dt.MaSuKienKham

  WHERE se.MaBS \= p\_MaBS AND dt.TrangThai \= 'DaKhoi'

    AND date\_trunc('month', dt.NgayKetThuc) \= date\_trunc('month', p\_Thang);

 

  SELECT GiaTri INTO v\_DonGiaThuong FROM ThamSoHeThong WHERE TenThamSo='THUONG\_BS\_HOAN\_THANH';

 

  RETURN v\_LuongCoBan \+ COALESCE(v\_SoDot,0) \* v\_DonGiaThuong;

END; \$\$ LANGUAGE plpgsql;

 

\-- \=====================================================================

\-- PHẦN 9: VIEW

\-- \=====================================================================

 

\-- 9.1 Bù lại việc đã bỏ MaBN, MaBS khỏi DOTDIEUTRI khi chuẩn hóa BCNF

CREATE OR REPLACE VIEW v\_DotDieuTri\_ChiTiet AS

SELECT dt.MaDotDieuTri, se.MaBN, bn.HoTen AS TenBenhNhan,

       se.MaBS, nv.HoTen AS TenBacSy,

       dt.MaBenh, db.TenBenh, dt.MucDoNang, dt.NgayBatDau, dt.NgayKetThuc, dt.TrangThai

FROM DotDieuTri dt

JOIN LanKham lk ON lk.MaSuKien \= dt.MaSuKienKham

JOIN SuKienYTe se ON se.MaSuKien \= lk.MaSuKien

JOIN BenhNhan bn ON bn.MaBN \= se.MaBN

JOIN NhanVienYTe nv ON nv.MaNV \= se.MaBS

JOIN DanhMucBenh db ON db.MaBenh \= dt.MaBenh;

 

\-- 9.2 Doanh thu khám/chữa theo ngày

CREATE OR REPLACE VIEW v\_DoanhThu\_TheoNgay AS

SELECT se.ThoiGian::date AS Ngay,

       SUM(COALESCE(lk.TienKham,0) \+ COALESCE(lc.TienChua,0)) AS TienKhamChua

FROM SuKienYTe se

LEFT JOIN LanKham lk ON lk.MaSuKien \= se.MaSuKien

LEFT JOIN LanChuaBenh lc ON lc.MaSuKien \= se.MaSuKien

GROUP BY se.ThoiGian::date;

 

\-- \=====================================================================

\-- PHẦN 10: STORED PROCEDURE (Transaction — tối thiểu 5 bắt buộc)

\--   Lưu ý: PROCEDURE có COMMIT/ROLLBACK bên trong CHỈ gọi được bằng CALL

\--   ở mức top-level (không được gọi trong 1 transaction/BEGIN đang mở,

\--   không gọi từ trong FUNCTION). Xem HUONG\_DAN.md.

\-- \=====================================================================

 

\-- 10.1 Tiếp nhận bệnh nhân đến khám (T1)

CREATE OR REPLACE PROCEDURE sp\_TiepNhanKham(

    p\_MaBN VARCHAR, p\_MaBS VARCHAR, p\_ThoiGian TIMESTAMP,

    p\_TrieuChung TEXT, p\_TienKham NUMERIC, p\_MaKhoa VARCHAR,

    p\_MaBenh VARCHAR, p\_MucDo VARCHAR, p\_SoLanDuKien INT

)

LANGUAGE plpgsql AS \$\$

DECLARE v\_MaSuKien VARCHAR(50); v\_MaDotMo VARCHAR(50);

BEGIN

    INSERT INTO SuKienYTe(MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien)

    VALUES (NULL, p\_MaBN, p\_MaBS, p\_ThoiGian, 'KHAM')

    RETURNING MaSuKien INTO v\_MaSuKien;

 

    INSERT INTO LanKham(MaSuKien, MaKhoa, TrieuChung, TienKham)

    VALUES (v\_MaSuKien, p\_MaKhoa, p\_TrieuChung, p\_TienKham);

 

    v\_MaDotMo := fn\_DotDangDieuTri(p\_MaBN, p\_MaBenh);

 

    IF v\_MaDotMo IS NULL THEN

        INSERT INTO DotDieuTri(MaDotDieuTri, MaSuKienKham, MaBenh, MucDoNang,

                                SoLanChuaDuKien, NgayBatDau, TrangThai)

        VALUES ('DT-' || v\_MaSuKien, v\_MaSuKien, p\_MaBenh, p\_MucDo,

                p\_SoLanDuKien, p\_ThoiGian::date, 'DangDieuTri');

    END IF;

 

    COMMIT;

END; \$\$;

 

\-- 10.2 Ghi nhận một lần chữa bệnh, kèm phòng, y tá hỗ trợ, thuốc, nhân công (T2)

CREATE OR REPLACE PROCEDURE sp\_GhiNhanChuaBenh(

    p\_MaDotDieuTri VARCHAR, p\_ThoiGian TIMESTAMP, p\_MaPhong VARCHAR,

    p\_HinhThucChua VARCHAR, p\_KetLuan TEXT, p\_TienChua NUMERIC,

    p\_MaThuoc VARCHAR, p\_SoLuongThuoc INT, p\_DonGiaThuoc NUMERIC,

    p\_MaLoaiCongBS VARCHAR, p\_DonGiaCongBS NUMERIC,

    p\_MaYTa VARCHAR, p\_MaLoaiCongYT VARCHAR, p\_DonGiaCongYT NUMERIC

)

LANGUAGE plpgsql AS \$\$

DECLARE v\_MaBS VARCHAR(10); v\_MaBN VARCHAR(10); v\_MaSuKien VARCHAR(50);

BEGIN

    SELECT se.MaBS, se.MaBN INTO v\_MaBS, v\_MaBN

    FROM DotDieuTri dt JOIN SuKienYTe se ON se.MaSuKien \= dt.MaSuKienKham

    WHERE dt.MaDotDieuTri \= p\_MaDotDieuTri;

 

    INSERT INTO SuKienYTe(MaSuKien, MaBN, MaBS, ThoiGian, LoaiSuKien)

    VALUES (NULL, v\_MaBN, v\_MaBS, p\_ThoiGian, 'CHUA')

    RETURNING MaSuKien INTO v\_MaSuKien;

 

    INSERT INTO LanChuaBenh(MaSuKien, MaDotDieuTri, HinhThucChua, KetLuan, TienChua, MaPhong)

    VALUES (v\_MaSuKien, p\_MaDotDieuTri, p\_HinhThucChua, p\_KetLuan, p\_TienChua, p\_MaPhong);

 

    INSERT INTO SuDungNhanCong(MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung)

    VALUES (v\_MaSuKien, v\_MaBS, p\_MaLoaiCongBS, 'Truc tiep chua benh', p\_DonGiaCongBS);

 

    IF p\_MaYTa IS NOT NULL THEN

        INSERT INTO SuDungNhanCong(MaSuKien, MaNV, MaLoaiCong, VaiTro, DonGiaApDung)

        VALUES (v\_MaSuKien, p\_MaYTa, p\_MaLoaiCongYT, 'Ho tro chua benh', p\_DonGiaCongYT);

    END IF;

 

    IF p\_MaThuoc IS NOT NULL THEN

        INSERT INTO SuDungThuoc(MaSuKien, MaThuoc, SoLuong, DonGiaApDung)

        VALUES (v\_MaSuKien, p\_MaThuoc, p\_SoLuongThuoc, p\_DonGiaThuoc);

    END IF;

 

    COMMIT;

END; \$\$;

 

\-- 10.3 Xuất hóa đơn tổng hợp cho một sự kiện y tế (T3)

CREATE OR REPLACE PROCEDURE sp\_XuatHoaDon(p\_MaSuKien VARCHAR)

LANGUAGE plpgsql AS \$\$

DECLARE v\_TienKham NUMERIC := 0; v\_TienChua NUMERIC := 0; v\_TienPhong NUMERIC := 0; v\_STT INT := 0;

BEGIN

    INSERT INTO HoaDon(MaSuKien, NgayLap, TongTien, TrangThaiTT)

    VALUES (p\_MaSuKien, now(), 0, 'ChuaThanhToan')

    ON CONFLICT (MaSuKien) DO NOTHING;

 

    SELECT COALESCE(TienKham,0) INTO v\_TienKham FROM LanKham WHERE MaSuKien \= p\_MaSuKien;

    SELECT COALESCE(lc.TienChua,0), COALESCE(pk.DonGiaSuDung,0)

      INTO v\_TienChua, v\_TienPhong

    FROM LanChuaBenh lc LEFT JOIN PhongKham pk ON pk.MaPhong \= lc.MaPhong

    WHERE lc.MaSuKien \= p\_MaSuKien;

 

    IF v\_TienKham \> 0 THEN

        v\_STT := v\_STT \+ 1;

        INSERT INTO HoaDonChiTiet VALUES (p\_MaSuKien, v\_STT, 'Tien kham', v\_TienKham);

    END IF;

    IF v\_TienChua \> 0 THEN

        v\_STT := v\_STT \+ 1;

        INSERT INTO HoaDonChiTiet VALUES (p\_MaSuKien, v\_STT, 'Tien chua benh', v\_TienChua);

    END IF;

    IF v\_TienPhong \> 0 THEN

        v\_STT := v\_STT \+ 1;

        INSERT INTO HoaDonChiTiet VALUES (p\_MaSuKien, v\_STT, 'Tien su dung phong', v\_TienPhong);

    END IF;

 

    INSERT INTO HoaDonChiTiet(MaSuKien, SoDong, MoTaKhoanMuc, SoTien)

    SELECT p\_MaSuKien, v\_STT \+ row\_number() OVER (), 'Thuoc: ' || t.TenThuoc, sd.SoLuong\*sd.DonGiaApDung

    FROM SuDungThuoc sd JOIN Thuoc t ON t.MaThuoc \= sd.MaThuoc

    WHERE sd.MaSuKien \= p\_MaSuKien;

 

    INSERT INTO HoaDonChiTiet(MaSuKien, SoDong, MoTaKhoanMuc, SoTien)

    SELECT p\_MaSuKien, v\_STT \+ 100 \+ row\_number() OVER (), 'Cong: ' || lnc.TenLoaiCong, snc.DonGiaApDung

    FROM SuDungNhanCong snc JOIN LoaiNhanCong lnc ON lnc.MaLoaiCong \= snc.MaLoaiCong

    WHERE snc.MaSuKien \= p\_MaSuKien;

 

    COMMIT;

END; \$\$;

 

\-- 10.4 Nhập thêm thuốc vào kho (T4)

CREATE OR REPLACE PROCEDURE sp\_NhapKhoThuoc(p\_MaThuoc VARCHAR, p\_SoLuong INT, p\_DonGiaMoi NUMERIC)

LANGUAGE plpgsql AS \$\$

BEGIN

    IF p\_SoLuong \<= 0 THEN

        RAISE EXCEPTION 'So luong nhap kho phai \> 0';

    END IF;

 

    UPDATE Thuoc

    SET TonKho \= TonKho \+ p\_SoLuong,

        DonGia \= COALESCE(p\_DonGiaMoi, DonGia)

    WHERE MaThuoc \= p\_MaThuoc;

 

    IF NOT FOUND THEN

        ROLLBACK;

        RAISE EXCEPTION 'Khong tim thay thuoc %', p\_MaThuoc;

    END IF;

 

    COMMIT;

END; \$\$;

 

\-- 10.5 Hủy một lần khám nhầm — minh họa ROLLBACK tường minh (T5)

CREATE OR REPLACE PROCEDURE sp\_HuyLanKham(p\_MaSuKien VARCHAR)

LANGUAGE plpgsql AS \$\$

DECLARE v\_SoDotLienQuan INT;

BEGIN

    SELECT COUNT(\*) INTO v\_SoDotLienQuan

    FROM DotDieuTri WHERE MaSuKienKham \= p\_MaSuKien;

 

    IF v\_SoDotLienQuan \> 0 THEN

        ROLLBACK;

        RAISE EXCEPTION 'Khong the huy: lan kham % da mo % dot dieu tri, can huy dot dieu tri truoc',

            p\_MaSuKien, v\_SoDotLienQuan;

    END IF;

 

    DELETE FROM LanKham WHERE MaSuKien \= p\_MaSuKien;

    DELETE FROM SuKienYTe WHERE MaSuKien \= p\_MaSuKien;

 

    COMMIT;

END; \$\$;

 

\-- 10.6 Ghi nhận trả lương hàng tháng cho một nhân viên (T6, bổ sung)

CREATE OR REPLACE PROCEDURE sp\_TraLuong(p\_MaNV VARCHAR, p\_Thang DATE)

LANGUAGE plpgsql AS \$\$

DECLARE v\_LoaiNV VARCHAR(10); v\_LuongCoBan NUMERIC; v\_Thuong NUMERIC := 0; v\_Tong NUMERIC;

BEGIN

    SELECT LoaiNV, HeSoLuong \* (SELECT GiaTri FROM ThamSoHeThong WHERE TenThamSo='LUONG\_CO\_SO')

    INTO v\_LoaiNV, v\_LuongCoBan

    FROM NhanVienYTe WHERE MaNV \= p\_MaNV;

 

    IF v\_LoaiNV \= 'BACSY' THEN

        v\_Tong := fn\_TinhLuongBacSy(p\_MaNV, p\_Thang);

        v\_Thuong := v\_Tong \- v\_LuongCoBan;

    ELSE

        SELECT COUNT(snc.MaSuKien) \* (SELECT GiaTri FROM ThamSoHeThong WHERE TenThamSo='THUONG\_YTA\_HOTRO')

        INTO v\_Thuong

        FROM SuDungNhanCong snc JOIN SuKienYTe se ON se.MaSuKien \= snc.MaSuKien

        WHERE snc.MaNV \= p\_MaNV AND date\_trunc('month', se.ThoiGian) \= date\_trunc('month', p\_Thang);

        v\_Tong := v\_LuongCoBan \+ COALESCE(v\_Thuong, 0);

    END IF;

 

    INSERT INTO Luong(MaLuong, MaNV, Thang, NgayNhanLuong, LuongCoBan, TienThuong, TongLuong)

    VALUES ('LG-' || p\_MaNV || '-' || to\_char(p\_Thang,'YYYYMM'), p\_MaNV, p\_Thang, CURRENT\_DATE,

            v\_LuongCoBan, COALESCE(v\_Thuong,0), v\_Tong)

    ON CONFLICT (MaNV, Thang) DO UPDATE

      SET TienThuong \= EXCLUDED.TienThuong, TongLuong \= EXCLUDED.TongLuong, NgayNhanLuong \= EXCLUDED.NgayNhanLuong;

 

    COMMIT;

END; \$\$;

 

\-- \=====================================================================

## **5.3. Điền dữ liệu mẫu cho từng bảng**

*(Mục này trình bày các câu lệnh INSERT dữ liệu mẫu và ảnh chụp màn hình quá trình cài đặt/nạp dữ liệu — nhóm sẽ bổ sung sau.)*

## **5.4. Các câu lệnh truy vấn minh họa nghiệp vụ (kèm ảnh kết quả)**

*(Mỗi mục dưới đây sẽ trình bày câu lệnh SELECT và ảnh chụp kết quả chạy thực tế — nhóm sẽ bổ sung sau.)*

### ***5.4.1. Liệt kê tất cả đợt điều trị kèm thông tin bệnh nhân và bác sỹ phụ trách***

### ***5.4.2. Tra cứu lịch sử khám/chữa bệnh theo một bệnh nhân cụ thể***

### ***5.4.3. Thống kê doanh thu khám/chữa bệnh theo ngày và theo khoa***

### ***5.4.4. Tính và liệt kê lương bác sỹ/y tá theo tháng***

### ***5.4.5. Danh sách thuốc sắp hết hoặc không đủ tồn kho***

### ***5.4.6. Thống kê số ca bệnh theo danh mục bệnh và theo khoa***

### ***5.4.7. Danh sách giường bệnh đang sử dụng theo từng phòng khám***

### ***5.4.8. Chi tiết hóa đơn theo từng sự kiện y tế***

### ***5.4.9. Danh sách đợt điều trị tái phát (liên kết đệ quy MaDotTruoc)***

### ***5.4.10. Top bệnh lý và top thuốc được sử dụng nhiều nhất trong tháng***

*— Hết —*

[image1]: <data:image/png;base64,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>

[image2]: <data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAmwAAAGmCAYAAADBDthqAABDFklEQVR4Xu3dB5gURfrHcUm7BAkSJCrIme8UTKiIYEZPVOBUBAMmRBA9EARPREyAgJIMYEBACYdKEEUlIzlL9GABCSKKgAIKSKy/Vfyr7ameXWZ3p2eqp7+f53mf6n67p6fpme3+0Tu7e4IAAACA1U4wGwAAALALgQ0AAMByBDYgmz777DOzlXBHjx41W3Fx1llnma3AycjIMFs5tnDhQrMVN23btjVbubJt2zZx5MgRs22dO+64w2wBiAGBDYjBCScc+1LR4/PPP+9erOhlpiuuuCLTZbI/ePBgz/LOnTtHzMsgZa7jJpeNGDFClZ6X9csvv4gNGzaovpzPmzdvxOMqV66sxu7du0f0k8F9jOU+S6tXrxann366ay3va6GNHz9ejBo1KqKXFbntIkWKiJIlS4r8+fObi5UxY8aYrQjufTh06JBrybFlw4YNy3R/s8v9+Jxua9q0aZ73lrRixQqzlelzROtH26YUbV0AOcNXExADHYDcF986deqocffu3SItLU1Nr1y50nORGjRokKen6W0WKFBA7N+/X03LkGWu4x5r164tevToIS655JKIdfQ+ueclHX50300HNvPfdeaZZ4oyZcp4lmlyev369WqUISDaOvv27XN6v/32m+e5TXqfZcl9jrZN97w5NmnSRLRs2VLdwTGXyfGPP/6I2JY5rcOMfO6mTZt61pPjgQMHxKZNm6IuM+96yp4Z2OTj5Rht/yT5PkpPTxeTJ08WefLkObah/2c+buvWrRH/Bvc6/fv392xb0q+VfOy8efM86+jx66+/VtNvv/121OeQ3O81Nzkv3zsywJrblWbOnCnee+89Z958PIDo+EoBYmBeeOSoA5ueN9fRsgpsmlw+ceJEUatWLXORZ7vyYi7DnfuCbm5f78+SJUtyFNgkHVrM53dPV69e3RPYdu7cqcKkO7C5x8y419OBzX3XMNp67nHdunXixx9/FMWKFfMsM8do0zLANGvWTIU2+W83H6NH+e/V3Mvc23MvM+fd60Yb9XS9evXUqB3vccdbR5L7ft5556nprl27qmXmnVnJ/Z51P949f+ONN0bMa3JeHr9ooVuOkyZNihqIAWSNrxQgBtEuPLEGNr3MvY4m75Tly5dP7N27V823bt3a8y1AeXeqV69e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>

[image3]: <data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAfsAAAL5CAYAAAC3q41EAABNhklEQVR4Xu3dC5xXc/748ZmaS41SSNFUU5Qkd4lipXUnWY1bbmEZsmwrrNsqcssllwghQ0uXFQqRqFmiosIWy5iYRbW09s/PrhbF57+fT/s5zvec70znO3POfM95f1/Px+P9OOd8zjnf+X6/c3nN5Pf4bZ4CAACi5XkXAACALMQeAADhiD0AAMIRe0G23HJLtXbtWu+ykZcX7adaP35VVZV3OSNhPcepU6emPFbLli3VV1995boivrbaaqtA70OQa4LYZ599nMf69NNPzX6U75V+/GHDhqWs/fTTT+Z1p9OtWzfvkrFixQrvUiLU1taG8rmzj1NeXm6OO3XqpIYMGVLv5y7dx504caJ3yfBeu379+pRjJI//s4/EsN+Q7h8go0ePdl3xM+83r5s+p+eFF17wnnLYa+qiz8Ul9pt6rpq9xl7nPv7ss898a88//7z79tBUVlamPNd58+ap77//3nVFeule3xtvvOFd2qQg71WY9Mfyxr6ur9nHH3/cu5RWVM//qKOOct6fyy+/3KzZ42222ca5buzYsSlfK9rw4cPTvrd1xf71119POa7rPbG8Hy+IdNfeeuut3iUj3bXp1pAcfPYSzH7zuX+AuLfuHwbe43RKSkq8Sw59n/7r6+6773aO7VRXV5ttYWGh2bZq1cp3r542bdr47vVe596WlZWZ/f322y/lnjPPPDPt/fn5+WZNvw73Y+np3LlzyrXee+3xU089paZMmZKy5t23x3ZN/zVlj/fff/+U8/Ya/a8L7mPLfZ33Hs2+p+nu8269+95jt4EDB5o1+17pv66991l2raCgwBzrX1Asu++913ts6WP7mh566CHzl6W9bvLkyb5r3ft6dATdH0P/glnXx/Gu2+PDDz/cHNuvF++9XvaaDh06qAMOOMBZ06699lr3pSnP7Y477jAfw62u79Xx48enPGc9+vsl3fNzX6N/cTruuOOc4xtuuCHttd779PeW+xdN97l0x3YNycVnL8HsN19dP0Dq2qb7C7y+b+Stt97a983v3g4YMCDlcb2PdfHFFztr3333ne+xLO9j618a3D/c7Tk9t9xyi7PmPrd69Wo1a9Ys556ioiLnnFu64w0bNvg+Vrp97fe//71Z0/+82a9fP+d5eV9DXVurrh+4+nOq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>