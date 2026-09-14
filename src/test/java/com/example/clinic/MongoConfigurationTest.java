package com.example.clinic;

import com.example.clinic.document.ActivityLog;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.repository.mongo.ActivityLogRepository;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

/** Checks wiring and mapping only; does not require a running MongoDB server. */
@SpringBootTest(properties = "spring.data.mongodb.uri=mongodb://127.0.0.1:27017/clinic_config_test?serverSelectionTimeoutMS=100&connectTimeoutMS=100")
@ActiveProfiles({"h2", "mongo"})
class MongoConfigurationTest {
    @Autowired private MongoTemplate mongoTemplate;
    @Autowired private ActivityLogRepository activityLogRepository;
    @Autowired private PatientRepository patientRepository;

    @Test
    void sqlAndMongoRepositoriesCoexistAndDocumentMapsCorrectly() {
        assertThat(activityLogRepository).isNotNull();
        assertThat(patientRepository.count()).isZero();
        assertThat(mongoTemplate.getDb().getName()).isEqualTo("clinic_config_test");
        assertThat(mongoTemplate.getCollectionName(ActivityLog.class)).isEqualTo("activity_logs");
        ActivityLog log = new ActivityLog();
        log.setAction("CREATE");
        log.setResourceType("PATIENT");
        log.setResourceId("1");
        Document document = new Document();
        mongoTemplate.getConverter().write(log, document);
        assertThat(document.getString("resourceId")).isEqualTo("1");
        assertThat(mongoTemplate.getConverter().read(ActivityLog.class, document).getAction())
                .isEqualTo("CREATE");
    }
}
