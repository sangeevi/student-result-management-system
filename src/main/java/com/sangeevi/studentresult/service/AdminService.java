package com.sangeevi.studentresult.service;

import com.sangeevi.studentresult.dto.ResultRequest;
import com.sangeevi.studentresult.dto.StudentRequest;
import com.sangeevi.studentresult.entity.Result;
import com.sangeevi.studentresult.entity.Student;
import com.sangeevi.studentresult.entity.User;
import com.sangeevi.studentresult.repository.ResultRepository;
import com.sangeevi.studentresult.repository.StudentRepository;
import com.sangeevi.studentresult.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Add a new student (also creates their login account)
    public Student addStudent(StudentRequest request) {
        // Create login user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("STUDENT");
        userRepository.save(user);

        // Create student profile
        Student student = new Student();
        student.setName(request.getName());
        student.setUsername(request.getUsername());
        student.setDepartment(request.getDepartment());
        student.setYear(request.getYear());
        return studentRepository.save(student);
    }

    // Add result for a student
    public Result addResult(ResultRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Result result = new Result();
        result.setStudent(student);
        result.setSubject(request.getSubject());
        result.setMarks(request.getMarks());
        result.setMaxMarks(request.getMaxMarks());
        result.setSemester(request.getSemester());
        result.setGrade(calculateGrade(request.getMarks(), request.getMaxMarks()));

        return resultRepository.save(result);
    }

    // Update existing result
    public Result updateResult(Long resultId, ResultRequest request) {
        Result result = resultRepository.findById(resultId)
                .orElseThrow(() -> new RuntimeException("Result not found"));

        result.setSubject(request.getSubject());
        result.setMarks(request.getMarks());
        result.setMaxMarks(request.getMaxMarks());
        result.setSemester(request.getSemester());
        result.setGrade(calculateGrade(request.getMarks(), request.getMaxMarks()));

        return resultRepository.save(result);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Auto-calculate grade from marks
    private String calculateGrade(int marks, int maxMarks) {
        double percentage = (marks * 100.0) / maxMarks;
        if (percentage >= 90) return "O";
        if (percentage >= 80) return "A+";
        if (percentage >= 70) return "A";
        if (percentage >= 60) return "B+";
        if (percentage >= 50) return "B";
        return "F";
    }
}
