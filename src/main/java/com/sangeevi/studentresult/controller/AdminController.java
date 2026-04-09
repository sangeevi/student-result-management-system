package com.sangeevi.studentresult.controller;

import com.sangeevi.studentresult.dto.ResultRequest;
import com.sangeevi.studentresult.dto.StudentRequest;
import com.sangeevi.studentresult.entity.Result;
import com.sangeevi.studentresult.entity.Student;
import com.sangeevi.studentresult.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Only ADMIN role can access these endpoints (enforced by SecurityConfig)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // POST /api/admin/students - Add new student
    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody StudentRequest request) {
        Student student = adminService.addStudent(request);
        return ResponseEntity.ok(student);
    }

    // GET /api/admin/students - View all students
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(adminService.getAllStudents());
    }

    // POST /api/admin/results - Add result
    @PostMapping("/results")
    public ResponseEntity<Result> addResult(@RequestBody ResultRequest request) {
        Result result = adminService.addResult(request);
        return ResponseEntity.ok(result);
    }

    // PUT /api/admin/results/{id} - Update result
    @PutMapping("/results/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Long id,
                                                @RequestBody ResultRequest request) {
        Result result = adminService.updateResult(id, request);
        return ResponseEntity.ok(result);
    }
}
