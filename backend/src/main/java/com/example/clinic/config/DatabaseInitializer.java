package com.example.clinic.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements ApplicationRunner {
    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) {
        log.info("🔍 [DatabaseInitializer] Đang kiểm tra trạng thái CSDL phòng khám...");

        boolean needsInit = false;

        try {
            // Kiểm tra bảng benhnhan có tồn tại không
            String regClass = jdbcTemplate.queryForObject(
                    "SELECT to_regclass('public.benhnhan')", String.class);

            if (regClass == null) {
                log.info("⚠️ Bảng 'benhnhan' chưa tồn tại. Cần khởi tạo cấu trúc CSDL và dữ liệu mẫu.");
                needsInit = true;
            } else {
                Integer count = jdbcTemplate.queryForObject(
                        "SELECT COUNT(*) FROM benhnhan", Integer.class);
                if (count == null || count == 0) {
                    log.info("⚠️ Bảng 'benhnhan' rỗng. Cần nạp dữ liệu mẫu.");
                    needsInit = true;
                } else {
                    log.info("✅ CSDL đã sẵn sàng với {} hồ sơ bệnh nhân hiện có.", count);
                }
            }
        } catch (Exception e) {
            log.warn("⚠️ Không thể truy vấn bảng 'benhnhan': {}. Sẽ tiến hành kiểm tra khởi tạo CSDL.", e.getMessage());
            needsInit = true;
        }

        if (needsInit) {
            log.info("🚀 [DatabaseInitializer] Bắt đầu tự động nạp Script CSDL...");
            Resource schemaResource = resolveScript("01_schema.sql");
            Resource dataResource = resolveScript("02_sample_data.sql");

            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
            populator.setContinueOnError(true);
            populator.setIgnoreFailedDrops(true);

            if (schemaResource != null && schemaResource.exists()) {
                log.info("📄 Nạp script cấu trúc: {}", schemaResource.getDescription());
                populator.addScript(schemaResource);
            }

            if (dataResource != null && dataResource.exists()) {
                log.info("📄 Nạp script dữ liệu mẫu: {}", dataResource.getDescription());
                populator.addScript(dataResource);
            }

            try {
                populator.execute(dataSource);
                log.info("🎉 [DatabaseInitializer] Tự động khởi tạo CSDL phòng khám thành công!");
            } catch (Exception ex) {
                log.error("❌ Lỗi khi tự động thực thi script SQL: {}", ex.getMessage(), ex);
            }
        }
    }

    /**
     * Tìm file script theo thứ tự ưu tiên:
     * 1. Classpath (/db/script/...)
     * 2. Đường dẫn tương đối từ thư mục requirements/Script/
     * 3. Đường dẫn tương đối từ backend/../requirements/Script/
     */
    private Resource resolveScript(String filename) {
        Resource classPathResource = new ClassPathResource("db/script/" + filename);
        if (classPathResource.exists()) {
            return classPathResource;
        }

        File relFile1 = new File("requirements/Script/" + filename);
        if (relFile1.exists()) {
            return new FileSystemResource(relFile1);
        }

        File relFile2 = new File("../requirements/Script/" + filename);
        if (relFile2.exists()) {
            return new FileSystemResource(relFile2);
        }

        File relFile3 = new File("../../requirements/Script/" + filename);
        if (relFile3.exists()) {
            return new FileSystemResource(relFile3);
        }

        return classPathResource;
    }
}
