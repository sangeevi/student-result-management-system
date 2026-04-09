package com.sangeevi.studentresult.dto;

import lombok.Data;

@Data
public class StudentRequest {
    private String name;
    private String username;
    private String password;
    private String department;
    private int year;
}
