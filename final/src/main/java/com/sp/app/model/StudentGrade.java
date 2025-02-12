package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentGrade {
    private long examId;
    private int score;
    private String gradeDate; // 성적 입력날짜
    
    private long subjectId;
    private long teacherId; // teacher 테이블
    private long studentId; // student 테이블
}
