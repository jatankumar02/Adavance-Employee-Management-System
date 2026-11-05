# Advanced Employee Management System

A portfolio-level employee administration web application built with Java 17 and Spring Boot. It includes a server-rendered dashboard, secure sign-in, role-based access, employee CRUD, search, department filtering, pagination, validation, and MySQL persistence.

## Technology
- Java 17, Spring Boot 3.3.5, Maven
- Spring MVC, Thymeleaf, Spring Security
- Spring Data JPA, Hibernate, MySQL
- Jakarta Bean Validation, Spring Boot Actuator
- OpenAPI / Swagger UI
- HTML, CSS

## Features
- Login with BCrypt password hashing
- Roles: ADMIN, HR, EMPLOYEE
- ADMIN/HR can add, edit and delete employee records
- Authenticated users can view and search the employee directory
- Search by employee name, email or designation
- Filter by department; pagination and sorting
- Employee status, joining date, salary and department data
- Server-side validation and duplicate-email checks
- Dashboard metrics and health endpoint
- Responsive browser interface

## Requirements
JDK 17+, Maven 3.8+, MySQL 8+.

## Run locally
1. Create a MySQL database (or let the JDBC URL create it).
2. Set environment variables `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`, or edit `src/main/resources/application.properties`.
3. From this folder run `mvn spring-boot:run`.
4. Open `http://localhost:8080`.

## Demo accounts for local development
The application seeds these accounts only when the user table is empty:
- Username: `admin` / Password: `ChangeMe123!` (ADMIN)
- Username: `hr` / Password: `ChangeMe123!` (HR)

Change/remove these demo credentials before any deployment. Do not expose a demo instance publicly with the default passwords.

## API documentation
Swagger UI is available at `http://localhost:8080/swagger-ui/index.html` when running. The application currently exposes MVC page routes; Swagger is included for future REST API expansion and does not imply that a full REST API has already been implemented.

## Useful endpoints
- `/login` - sign in
- `/dashboard` - workforce dashboard
- `/employees` - employee directory
- `/actuator/health` - application health

## Important security notes
This is a learning/portfolio project, not a production-certified HR system. Before production use, add HTTPS, CSRF-aware custom workflows, password reset and account provisioning, audit logging, privacy controls, secrets management, database migrations (Flyway/Liquibase), backups, and security testing. Replace demo credentials and externalize secrets.

## Suggested GitHub description
"Advanced Employee Management System built with Java 17, Spring Boot, Spring Security, Thymeleaf, Spring Data JPA and MySQL. Features role-based access, employee CRUD, validation, search, filtering, pagination and dashboard metrics."
