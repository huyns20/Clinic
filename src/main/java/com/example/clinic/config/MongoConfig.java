package com.example.clinic.config;

import com.example.clinic.repository.mongo.ActivityLogRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@Profile("mongo")
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses = ActivityLogRepository.class)
public class MongoConfig {}
