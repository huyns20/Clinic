# Clinic Management Backend

Bộ khung RESTful API quản lý phòng khám tư nhân cho Đề tài 4. Có nền tảng và module CRUD bệnh nhân làm mẫu; chưa triển khai nghiệp vụ khám/chữa bệnh.

## Công nghệ

Java 17+, Spring Boot 3.5.16, Maven 3.9.9 (Wrapper), Spring Web, Spring Data JPA, Jakarta Validation, Lombok, H2/MySQL, springdoc OpenAPI 2.8.9.

Spring Boot 3.5 hỗ trợ Java 17–25: https://docs.spring.io/spring-boot/3.5/system-requirements.html
Ma trận tương thích springdoc: https://springdoc.org/v2/

## Chạy ngay

Cài JDK 17 trở lên, thiết lập `JAVA_HOME` nếu cần. Lần đầu cần Internet để Wrapper tải Maven và dependency.

```bash
./mvnw spring-boot:run
```

Windows: `mvnw.cmd spring-boot:run`.

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs
- Kiểm tra ứng dụng: http://localhost:8080/api/v1/health
- H2 Console: http://localhost:8080/h2-console

H2 dùng JDBC URL `jdbc:h2:mem:clinicdb`, user `sa`, password để trống. Dữ liệu H2 chỉ ở bộ nhớ, mất khi dừng ứng dụng. Hibernate tự tạo các bảng `patients`, `departments`, `doctors`. Chưa có dữ liệu mẫu; tạo bệnh nhân qua Swagger hoặc ví dụ bên dưới.

```bash
curl http://localhost:8080/api/v1/health
./mvnw clean verify
./mvnw clean package
java -jar target/clinic-management-0.0.1-SNAPSHOT.jar
```

Đổi cổng: `SERVER_PORT=8081 ./mvnw spring-boot:run`.

## Chạy với MySQL

Tạo database `clinic_db` với charset `utf8mb4`, tạo user có quyền trên database này, rồi cấu hình:

```bash
export SPRING_PROFILES_ACTIVE=mysql
export DB_URL='jdbc:mysql://localhost:3306/clinic_db?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC'
export DB_USERNAME=clinic
export DB_PASSWORD='your-local-password'
./mvnw spring-boot:run
```

MySQL cần được cài và khởi động riêng. Profile MySQL dùng `ddl-auto=update` phục vụ phát triển. Khi triển khai thực tế, bổ sung migration và đặt `DB_DDL_AUTO=validate`. File `.env` không được tự động nạp bởi Spring Boot; dùng biến môi trường hoặc cấu hình IDE. Không commit mật khẩu.

## Cấu trúc

```text
src/main/java/com/example/clinic/
├── ClinicManagementApplication.java
├── controller/       # Nhận HTTP request, trả DTO, khai báo @Valid
├── service/          # Hợp đồng nghiệp vụ
│   └── impl/         # Nghiệp vụ và transaction
├── repository/       # Truy cập CSDL qua Spring Data JPA
├── entity/           # JPA entity, BaseEntity có audit và optimistic locking
├── dto/
│   ├── request/      # DTO đầu vào, Jakarta Validation
│   └── response/     # DTO đầu ra
├── mapper/           # Chuyển Entity ↔ DTO
├── exception/        # Xử lý lỗi tập trung với ProblemDetail
├── config/           # JPA auditing, OpenAPI
└── enums/            # Trạng thái chuỗi điều trị
```

Luồng: Controller → Service → Repository → Database; Mapper chuyển dữ liệu sang DTO. Health API minh họa Controller → Service, không truy vấn CSDL và không phải kiểm tra readiness của database.

API dùng tiền tố `/api/v1`. Khi bổ sung tài nguyên: GET danh sách/chi tiết, POST tạo (201 + Location), PUT/PATCH sửa, DELETE xóa (204). Lỗi chung dùng `application/problem+json`: 400 dữ liệu/nghiệp vụ không hợp lệ, 404 không tìm thấy, 409 ràng buộc CSDL, 500 lỗi ngoài dự kiến. Thông tin lỗi nội bộ chỉ ghi vào log.

Bộ khung chưa có xác thực/phân quyền, dữ liệu mẫu, nghiệp vụ khám/chữa hay báo cáo. Xem [định hướng nghiệp vụ](docs/domain-plan.md) trước khi triển khai.

## Module mẫu

`Patient` đại diện bệnh nhân (khách hàng của phòng khám), có đủ Controller → Service → Repository và Mapper/DTO. `Doctor` và `Department` hiện có entity/repository, chưa có API; quan hệ nhiều bác sỹ thuộc một khoa dùng FK `department_id`, LAZY, không cascade xóa.

| Method | URL | Chức năng |
| --- | --- | --- |
| POST | `/api/v1/patients` | Tạo, trả 201 và Location |
| GET | `/api/v1/patients/{id}` | Chi tiết |
| GET | `/api/v1/patients?keyword=An&page=0&size=20` | Tìm tên/điện thoại, phân trang |
| PUT | `/api/v1/patients/{id}` | Thay toàn bộ các trường được sửa |
| DELETE | `/api/v1/patients/{id}` | Xóa, trả 204 |

Ví dụ tạo bệnh nhân:

```bash
curl -X POST http://localhost:8080/api/v1/patients \
  -H 'Content-Type: application/json' \
  -d '{"fullName":"Nguyễn Văn An","dateOfBirth":"2000-01-15","gender":"MALE","phone":"0901234567","address":"Hà Nội"}'
```

`gender`: MALE/FEMALE/OTHER. Ngày sinh không được trong tương lai. Số điện thoại gồm 9–15 chữ số và có thể bắt đầu bằng `+`; được phép trùng giữa các bệnh nhân. `page` từ 0, `size` từ 1–100, kết quả sắp xếp ID giảm dần. Không tìm thấy trả 404, dữ liệu sai trả 400.

DELETE hiện xóa vật lý vì chưa có hồ sơ khám/chữa. Khi bổ sung lịch sử, phải bổ sung FK hạn chế xóa hoặc chuyển sang ngừng hoạt động để bảo toàn hồ sơ. Hệ số lương trong Doctor mới là ví dụ lưu dữ liệu, chưa tính lương hoặc lưu lịch sử thay đổi hệ số.

## MongoDB chạy cùng SQL

MongoDB là kết nối bổ sung, bật bằng profile `mongo`. Không bật profile này thì ứng dụng không tạo MongoClient/repository MongoDB và vẫn chạy H2/MySQL như trước.

Khởi động MongoDB local bằng Docker (cần Docker đang chạy):

```bash
docker compose -f compose.mongo.yml up -d
./mvnw spring-boot:run -Dspring-boot.run.profiles=h2,mongo
```

Dùng MySQL + MongoDB: thiết lập các biến DB_URL, DB_USERNAME, DB_PASSWORD như phần MySQL, rồi chạy:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql,mongo
```

Khi chạy JAR: `java -jar target/clinic-management-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2,mongo`.
Luôn chọn một profile SQL (`h2` hoặc `mysql`) cùng `mongo`, vì bật profile tường minh sẽ thay profile mặc định.

Kết nối mặc định: `mongodb://localhost:27017/clinic_logs?serverSelectionTimeoutMS=5000&connectTimeoutMS=5000`. Ghi đè bằng biến môi trường `MONGODB_URI` cho MongoDB có tài khoản hoặc Atlas; không commit thông tin đăng nhập. Compose này dành cho local, không bật xác thực và chỉ mở cổng trên loopback. Volume giữ dữ liệu khi container dừng.

- `document/ActivityLog.java`: document mẫu trong collection `activity_logs`, ID chuỗi và thời gian tạo tự động.
- `repository/mongo/ActivityLogRepository.java`: MongoRepository, có truy vấn theo loại và ID tài nguyên.
- `config/MongoConfig.java`: bật repository và auditing khi profile mongo hoạt động.
- `config/JpaConfig.java`: quét riêng các repository SQL, loại MongoRepository.

Ví dụ trong service có `@Profile("mongo")`, inject `ActivityLogRepository` rồi gọi:

```java
ActivityLog log = new ActivityLog();
log.setAction("CREATE");
log.setResourceType("PATIENT");
log.setResourceId("1");
log.setActor("demo");
activityLogRepository.save(log);
```

Đây là mẫu lưu độc lập, chưa tự động ghi log từ PatientService và chưa có API log. MongoDB tạo database/collection khi ghi lần đầu. Ứng dụng khởi động không chứng minh MongoDB đã kết nối được; health API hiện chỉ kiểm tra liveness. Kiểm tra MongoDB local bằng `docker compose -f compose.mongo.yml exec mongodb mongosh --quiet --eval 'db.adminCommand("ping")'`.

Transaction JPA không bao phủ MongoDB. Nếu cần ghi SQL và log nhất quán, bổ sung outbox/retry theo nghiệp vụ. Cách tách repository dựa theo [hướng dẫn Spring Boot](https://docs.spring.io/spring-boot/how-to/data-access.html).
