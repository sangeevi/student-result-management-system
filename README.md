# Student Result Management System

A role-based REST API built with Java and Spring Boot featuring JWT authentication.

## Features
- JWT-based authentication (stateless)
- Role-based access: Admin and Student
- Admin can add students, add and update results
- Students can view only their own results and profile
- Auto grade calculation based on marks
- Controller–Service–Repository architecture

## Tech Stack
- Java 17
- Spring Boot 3.2
- Spring Security
- JWT (jjwt 0.11.5)
- MySQL
- Spring Data JPA
- Maven
- Postman (for testing)

## How to Run
1. Create MySQL database: `CREATE DATABASE student_result_db;`
2. Update `application.properties` with your MySQL credentials
3. Run: `mvn spring-boot:run`
4. Default admin created automatically: `admin / admin123`

## API Endpoints

| Method | URL | Role | Description |
|--------|-----|------|-------------|
| POST | /api/auth/login | Public | Get JWT token |
| POST | /api/admin/students | Admin | Add student |
| GET | /api/admin/students | Admin | Get all students |
| POST | /api/admin/results | Admin | Add result |
| PUT | /api/admin/results/{id} | Admin | Update result |
| GET | /api/student/profile | Student | View my profile |
| GET | /api/student/results | Student | View my results |
| GET | /api/student/results/semester/{sem} | Student | Results by semester |
