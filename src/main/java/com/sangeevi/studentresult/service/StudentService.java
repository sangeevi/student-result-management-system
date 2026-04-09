package com.sangeevi.studentresult.service;

import com.sangeevi.studentresult.entity.Result;
import com.sangeevi.studentresult.entity.Student;
import com.sangeevi.studentresult.repository.ResultRepository;
import com.sangeevi.studentresult.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ResultRepository resultRepository;

    // Get student profile by username (from JWT token)
    public Student getMyProfile(String username) {
        return studentRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // Get all results for logged-in student
    public List<Result> getMyResults(String username) {
        Student student = getMyProfile(username);
        return resultRepository.findByStudent(student);
    }

    // Get results by semester
    public List<Result> getMyResultsBySemester(String username, String semester) {
        Student student = getMyProfile(username);
        return resultRepository.findByStudentAndSemester(student, semester);
    }
}
