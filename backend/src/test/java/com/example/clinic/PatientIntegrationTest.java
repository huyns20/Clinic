package com.example.clinic;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("h2")
@Transactional
class PatientIntegrationTest {
    @Autowired private MockMvc mvc;
    @Autowired private ObjectMapper objectMapper;

    private static final String REQUEST = """
            {"fullName":"Nguyen Van An","dateOfBirth":"2000-01-15",
             "gender":"MALE","phone":"0901234567","address":"Ha Noi"}
            """;

    @Test
    void patientLifecyclePersistsAndSearches() throws Exception {
        String body = mvc.perform(post("/api/v1/patients")
                        .contentType(MediaType.APPLICATION_JSON).content(REQUEST))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.createdAt").isNotEmpty())
                .andReturn().getResponse().getContentAsString();
        long id = objectMapper.readTree(body).get("id").asLong();
        mvc.perform(get("/api/v1/patients/{id}", id))
                .andExpect(status().isOk()).andExpect(jsonPath("$.fullName").value("Nguyen Van An"));
        mvc.perform(get("/api/v1/patients").param("keyword", "van an").param("size", "1"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.totalElements").value(1));
        mvc.perform(get("/api/v1/patients").param("keyword", "0901234567"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.totalElements").value(1));
        mvc.perform(put("/api/v1/patients/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .content(REQUEST.replace("Nguyen Van An", "Nguyen Van Binh")))
                .andExpect(status().isOk()).andExpect(jsonPath("$.fullName").value("Nguyen Van Binh"));
        mvc.perform(get("/api/v1/patients/{id}", id))
                .andExpect(jsonPath("$.fullName").value("Nguyen Van Binh"));
        mvc.perform(delete("/api/v1/patients/{id}", id)).andExpect(status().isNoContent());
        mvc.perform(get("/api/v1/patients/{id}", id)).andExpect(status().isNotFound());
    }

    @Test
    void invalidRequestReturnsFieldErrors() throws Exception {
        mvc.perform(post("/api/v1/patients").contentType(MediaType.APPLICATION_JSON)
                        .content(REQUEST.replace("Nguyen Van An", " ")))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON))
                .andExpect(jsonPath("$.errors[0].field").value("fullName"));
    }

    @Test
    void invalidPaginationAndMissingPatientAreHandled() throws Exception {
        mvc.perform(get("/api/v1/patients").param("size", "101"))
                .andExpect(status().isBadRequest());
        mvc.perform(get("/api/v1/patients").param("page", "-1"))
                .andExpect(status().isBadRequest());
        mvc.perform(put("/api/v1/patients/999999").contentType(MediaType.APPLICATION_JSON).content(REQUEST))
                .andExpect(status().isNotFound());
        mvc.perform(delete("/api/v1/patients/999999")).andExpect(status().isNotFound());
    }
}
