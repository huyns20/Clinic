package com.example.clinic.document;

import java.time.Instant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/** Mongo document: independent of the JPA BaseEntity and SQL transactions. */
@Document(collection = "activity_logs")
@Getter
@Setter
@NoArgsConstructor
public class ActivityLog {
    @Id
    private String id;
    private String action;
    private String resourceType;
    // Logical reference only: MongoDB cannot enforce a foreign key to SQL.
    private String resourceId;
    private String actor;
    @CreatedDate
    private Instant createdAt;
}
