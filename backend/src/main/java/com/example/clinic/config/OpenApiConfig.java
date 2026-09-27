package com.example.clinic.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI clinicOpenApi() {
        return new OpenAPI().info(new Info().title("Private Clinic API")
                .version("v1").description("Base REST API cho hệ thống quản lý phòng khám tư nhân."));
    }
}
