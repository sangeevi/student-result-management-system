package com.sangeevi.studentresult.dto;

import lombok.Data;

@Data
public class ResultRequest {
    private Long studentId;
    private String subject;
    private int marks;
    private int maxMarks;
    private String semester;
}
