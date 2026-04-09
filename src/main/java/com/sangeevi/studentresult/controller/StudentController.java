package com.sangeevi.studentresult.controller;

import com.sangeevi.studentresult.entity.Result;
import com.sangeevi.studentresult.entity.Student;
import com.sangeevi.studentresult.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Only STUDENT role can access these endpoints
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // GET /api/student/profile
    // Authentication object gives us the logged-in user's username from JWT
    @GetMapping("/profile")
    public ResponseEntity<Student> getProfile(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(studentService.getMyProfile(username));
    }

    // GET /api/student/results
    @GetMapping("/results")
    public ResponseEntity<List<Result>> getMyResults(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(studentService.getMyResults(username));
    }

    // GET /api/student/results?semester=1
    @GetMapping("/results/semester/{semester}")
    public ResponseEntity<List<Result>> getResultsBySemester(
            Authentication authentication,
            @PathVariable String semester) {
        String username = authentication.getName();
        return ResponseEntity.ok(studentService.getMyResultsBySemester(username, semester));
    }
}
