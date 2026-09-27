# Spring Boot Conventions - Clinic Management System

## Package Structure
```
com.example.clinic
├── config/          # Spring configuration classes
├── controller/      # REST controllers (@RestController)
├── dto/             # Request/Response DTOs
├── entity/          # JPA entities mapping to PostgreSQL tables
├── exception/       # Custom exceptions + GlobalExceptionHandler
├── repository/      # Spring Data JPA repositories
├── service/         # Service interfaces
│   └── impl/        # Service implementations
└── ClinicApplication.java
```

## Naming Conventions
- **Entities**: Map to Vietnamese table names using @Table(name="...")
- **Controllers**: Use kebab-case URLs: `/api/v1/benh-nhan`, `/api/v1/phong-kham`
- **DTOs**: `{Entity}Request`, `{Entity}Response`
- **Services**: Interface + `{Service}Impl` pattern
- **Repositories**: `{Entity}Repository extends JpaRepository<Entity, KeyType>`

## Database
- **ddl-auto: none** - Schema managed by SQL scripts in requirements/Script/
- **PostgreSQL**: localhost:5432/phongkham_db, postgres/123456
- 24 tables, 5 triggers, 2 functions, 2 views, 6 stored procedures

## Key Patterns
1. Use `@Transactional` on service methods
2. Use `@Valid` on controller request bodies
3. Call stored procedures via `@Query(nativeQuery=true)` or `EntityManager`
4. Return `ResponseEntity<>` from controllers
5. Use `@RestControllerAdvice` for global exception handling
