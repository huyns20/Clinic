# Định hướng triển khai Đề tài 4

Tài liệu thiết kế định hướng. Hiện đã có Patient CRUD và entity/repository Doctor, Department cơ bản; các phần khám/chữa, chi phí và báo cáo bên dưới chưa được cài đặt.

## Các nhóm thực thể

| Nhóm | Thực thể dự kiến | Vai trò |
| --- | --- | --- |
| Danh mục | Department, Doctor, Nurse, Patient, Disease | Khoa, nhân viên, bệnh nhân và bệnh |
| Tài nguyên | Medicine, Equipment, Facility, MedicalService | Thuốc, thiết bị, cơ sở vật chất, dịch vụ |
| Lần đến khám/chữa | Encounter, Examination, TreatmentSession | Thời gian, khoa, bác sỹ, mã lần khám/chữa |
| Chẩn đoán và điều trị | Diagnosis, TreatmentCourse | Mức độ, số lần điều trị dự kiến, chuỗi điều trị từng bệnh |
| Chăm sóc | NurseAssignment | Y tá hỗ trợ từng lần khám/chữa, công việc đã thực hiện |
| Chi phí | ChargeLine, Prescription, PrescriptionItem | Thuốc, vật tư, thiết bị, dịch vụ và nhân công |
| Lương | SalaryPolicy, StaffSalaryHistory | Mức lương cơ sở, hệ số, thời gian áp dụng |

## Quan hệ và ràng buộc cần triển khai

- Một bệnh nhân có nhiều chuỗi điều trị. Mỗi chuỗi thuộc đúng một bệnh, một bệnh nhân và một bác sỹ phụ trách; bắt đầu bằng một chẩn đoán của lần khám, có 0..n lần chữa tiếp theo. Lần khám có thể mở nhiều chuỗi cho nhiều bệnh khác nhau.
- Bác sỹ khám, bác sỹ phụ trách chuỗi và bác sỹ thực hiện các lần chữa của chuỗi phải đồng nhất theo yêu cầu đề bài. Ràng buộc liên bảng phải được kiểm tra trong transaction và thiết kế khóa ngoại phù hợp khi chốt schema.
- Lần khám/chữa có mã duy nhất chứa mã khoa và mã bác sỹ, ví dụ `KH-NOI-BS001-20260914-<unique-id>`. Cần chống trùng khi tạo đồng thời; lưu snapshot mã khoa/bác sỹ để lịch sử không thay đổi khi sửa danh mục.
- Mỗi chẩn đoán ghi tên bệnh (hoặc snapshot), mức độ, số lần chữa cần thiết. Lần chữa ghi hình thức, xét nghiệm/chụp chiếu, kết luận, thời gian và y tá hỗ trợ.
- Chuỗi có trạng thái IN_PROGRESS, CURED hoặc DISCONTINUED; có thời điểm bắt đầu và kết thúc. Tái mắc sau khỏi tạo chuỗi mới, không tái sử dụng chuỗi cũ.
- NurseAssignment có unique `(encounter_id, nurse_id)` để một y tá làm nhiều việc trong cùng lần đến chỉ nhận một lần phụ cấp. Các công việc chi tiết tách sang bảng con.
- ChargeLine lưu loại khoản thu, số lượng, đơn giá tại thời điểm sử dụng, thành tiền; dùng BigDecimal/DECIMAL, số lượng dương và giá không âm. Không dùng giá danh mục hiện tại để tính lại hóa đơn cũ.
- Mỗi khoản tiền chỉ ghi nhận một lần trong tổng thu; tiền thuốc ở đơn thuốc tham chiếu khoản thu để tránh cộng trùng. Chốt định nghĩa doanh thu đã thu hoặc phát sinh trước khi code.
- Sử dụng NOT NULL, UNIQUE, CHECK, FK và index thích hợp; hạn chế xóa bản ghi đã được lịch sử khám/chữa tham chiếu. Quy định xóa mềm/ngừng hoạt động khi triển khai CRUD.

## Quy tắc báo cáo

- Bệnh trong tháng: dùng khoảng `[đầu tháng, đầu tháng kế tiếp)` theo múi giờ Asia/Ho_Chi_Minh. Mỗi chuỗi có lần khám/chữa trong tháng được tính một lần; tái mắc với chuỗi khác tính thêm lần. Sắp xếp giảm dần theo số đợt mắc. Không chỉ `COUNT(DISTINCT patient_id)` vì sẽ làm mất các đợt tái mắc.
- Lương: mức lương cơ sở × hệ số có hiệu lực trong kỳ + phụ cấp. Mỗi chuỗi kết thúc CURED trong kỳ cộng 1.000.000 đồng cho bác sỹ; mỗi NurseAssignment hợp lệ trong kỳ cộng 200.000 đồng cho y tá. Không đếm lại mọi lần chữa của cùng chuỗi cho thưởng bác sỹ. Tháng hiện tại giới hạn đến thời điểm truy vấn. Chưa chốt quy tắc phân bổ khi hệ số thay đổi giữa tháng.
- Hồ sơ bệnh nhân: toàn bộ lịch sử, các chuỗi đang điều trị, thứ tự lần khám/chữa trong từng chuỗi và từng khoản chi phí của mỗi lần đến.
- Doanh thu: tổng các khoản thu đã chốt theo quy tắc kỳ báo cáo, tách thuốc, khám/chữa, dịch vụ, thiết bị/cơ sở vật chất và xử lý hoàn/hủy khi bổ sung.

## Phần việc tiếp theo

1. Chốt ERD, kiểu dữ liệu, quan hệ, cardinality, ràng buộc và index; bổ sung migration.
2. Xây CRUD/tìm kiếm danh mục, phân trang, validation, phân quyền.
3. Xây khám → chẩn đoán → chuỗi điều trị → lần chữa → khoản thu trong transaction.
4. Tạo dữ liệu mẫu ít nhất 3 tháng, có chuỗi qua tháng, nhiều bệnh trong một lần khám, tái mắc, nhiều y tá hỗ trợ.
5. Xây và kiểm thử báo cáo, đối chiếu số liệu bằng truy vấn SQL.
6. Báo cáo phải ghi số bản ghi thực tế từng bảng, quan hệ 1–n/n–n, FK/unique/check, dữ liệu minh chứng và các ràng buộc được kiểm tra ở tầng service.
