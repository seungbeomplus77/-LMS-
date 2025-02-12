package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Exam {
    private long examId;
    private String examTitle; // 시험 제목 또는 구분 (예: 중간고사, 기말고사 등)
    private String examDate; // 시험 시행일
    private int examDuration; // 시험 시간(분)

    private long teacherId; // teacher 테이블
    private long subjectId;
}
