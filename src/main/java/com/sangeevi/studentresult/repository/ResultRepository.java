package com.sangeevi.studentresult.repository;

import com.sangeevi.studentresult.entity.Result;
import com.sangeevi.studentresult.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByStudent(Student student);
    List<Result> findByStudentAndSemester(Student student, String semester);
}
