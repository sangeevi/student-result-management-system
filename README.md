# Student Result Management System
JWT-authenticated REST API built with Java + Spring Boot

## How to Run

### 1. Create MySQL Database
```sql
CREATE DATABASE student_result_db;
```

### 2. Update application.properties
Edit `src/main/resources/application.properties`:
- Set your MySQL `username` and `password`

### 3. Run the app
```bash
mvn spring-boot:run
```
App starts at: http://localhost:8080
A default admin is auto-created: **admin / admin123**

---

## API Endpoints

### Auth (Public)
| Method | URL | Body |
|--------|-----|------|
| POST | /api/auth/login | `{"username":"admin","password":"admin123"}` |

Returns a JWT token. Use it in all other requests as:
`Authorization: Bearer <your_token>`

---

### Admin Endpoints (Role: ADMIN)
| Method | URL | Description |
|--------|-----|-------------|
| POST | /api/admin/students | Add new student |
| GET | /api/admin/students | Get all students |
| POST | /api/admin/results | Add result |
| PUT | /api/admin/results/{id} | Update result |

**Add Student body:**
```json
{
  "name": "Sangeevi Nath",
  "username": "sangeevi",
  "password": "pass123",
  "department": "ECE",
  "year": 4
}
```

**Add Result body:**
```json
{
  "studentId": 1,
  "subject": "Data Structures",
  "marks": 88,
  "maxMarks": 100,
  "semester": "3"
}
```

---

### Student Endpoints (Role: STUDENT)
| Method | URL | Description |
|--------|-----|-------------|
| GET | /api/student/profile | View my profile |
| GET | /api/student/results | View all my results |
| GET | /api/student/results/semester/{sem} | Results by semester |

---

## How JWT Works in This Project
1. User calls `/api/auth/login` with username + password
2. Server checks password, generates a signed JWT token
3. User sends token in every request header: `Authorization: Bearer <token>`
4. JwtFilter intercepts every request, validates the token, sets the role
5. Spring Security allows/blocks based on role (ADMIN vs STUDENT)

## Grade Scale
| Percentage | Grade |
|------------|-------|
| 90-100 | O |
| 80-89 | A+ |
| 70-79 | A |
| 60-69 | B+ |
| 50-59 | B |
| Below 50 | F |
