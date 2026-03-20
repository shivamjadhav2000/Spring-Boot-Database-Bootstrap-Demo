# 🚀 Database Initialization & Migration Service (Spring Boot)

A production-ready Spring Boot application demonstrating enterprise-grade database initialization using:

- Flyway for schema versioning
- Spring Boot ApplicationRunner for data seeding
- PostgreSQL for persistence
- JPA/Hibernate for ORM mapping

---

# 📌 Features

- 🧱 Database schema versioning using Flyway
- 🌱 Automatic seeding of default data (roles, admin user, department)
- 🔐 Role-based structure (Admin, Super Admin, Viewer, Standard)
- 🏢 Department-user-role relational model
- 📦 Clean separation of schema and data logic
- 🧪 Ready for local and production environments

---

# 🧰 Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Flyway
- Lombok
- Springdoc OpenAPI (Swagger UI)

---

# 🏗️ Project Structure

src/main/java/com/shivam/databaseinitapplication

- config/DatabaseInitializer.java
- entity/User.java
- entity/Role.java
- entity/Department.java
- repository/*
- controller/*

---

# 🧱 Database Design

## User
- username, email, password
- role mapping
- department association
- audit fields
- soft delete flags

## Department
- organization structure
- root department support
- created-by relationship

## Role
- permissions
- admin flags
- access control

---

# 🚀 Database Migration Strategy (Flyway)

## V1__init.sql
- Creates users, roles, departments tables
- Adds indexes and constraints

## V2__relationships.sql
- Adds foreign keys
- Defines relationships

---

# 🌱 Database Seeding (ApplicationRunner)

Seeded data:

- SUPER_ADMIN
- ROLE_ADMIN
- ROLE_STANDARD
- ROLE_VIEWER
- Default department
- Admin user

---

# 🔄 Startup Flow

Spring Boot Startup → DataSource → Flyway → Hibernate → ApplicationRunner → Ready 🚀

---

# ⚙️ Configuration

- PostgreSQL connection via application.properties
- Flyway enabled
- Hibernate ddl-auto = validate

---

# ▶️ Run

mvn clean install
mvn spring-boot:run

---

# 📊 APIs

- GET /api/users
- GET /api/departments
- GET /api/roles

---

# 🧠 Best Practice

- Flyway = schema
- ApplicationRunner = seed data
- Never use ddl-auto=update in production

---

# 🚀 Outcome

Enterprise-grade database initialization system with versioned migrations and safe seed strategy.


# ▶️ How to Run the Project

## 1️⃣ Prerequisites
Make sure you have the following installed:

- Java 21+
- Maven 3+
- Docker (optional but recommended)
- PostgreSQL (if not using Docker)

---

## 2️⃣ Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/database_init
spring.datasource.username=postgres
spring.datasource.password=postgres
```

---

## 3️⃣ Run PostgreSQL (Docker)

Start PostgreSQL using Docker Compose:

```bash
docker compose up --build -d
```

This will:
- Start PostgreSQL container
- Expose port 5432
- Initialize database (if configured in compose file)

---

## 4️⃣ Run Spring Boot Application

Build and run the project:

```bash
mvn clean install
mvn spring-boot:run
```

---

## 5️⃣ Verify Application

Once running, access:

- Swagger UI → http://localhost:8000/swagger-ui.html
- Users API → http://localhost:8000/api/users
- Departments API → http://localhost:8000/api/departments
- Roles API → http://localhost:8000/api/roles

---

## ⚠️ Notes

- Ensure PostgreSQL is running before starting the app
- Flyway will automatically create and version the schema
- ApplicationRunner will seed initial data after startup
- Do NOT use `ddl-auto=update` in production
