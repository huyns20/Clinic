package com.example.clinic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
class ApplicationIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void healthEndpointWorks() throws Exception {
        mvc.perform(get("/api/v1/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.application").value("clinic-management"));
    }

    @Test
    void openApiIncludesHealthEndpoint() throws Exception {
        mvc.perform(get("/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.paths['/api/v1/health'].get").exists());
    }

    @Test
    void swaggerUiIsAvailable() throws Exception {
        mvc.perform(get("/swagger-ui/index.html")).andExpect(status().isOk());
    }

    @Test
    void unknownRouteReturns404() throws Exception {
        mvc.perform(get("/api/v1/does-not-exist")).andExpect(status().isNotFound());
    }

    @Test
    void unsupportedMethodReturns405() throws Exception {
        mvc.perform(post("/api/v1/health")).andExpect(status().isMethodNotAllowed());
    }
}
