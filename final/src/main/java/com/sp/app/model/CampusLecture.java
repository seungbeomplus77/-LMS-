package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CampusLecture {
    private long lectureId;
    private long subjectId;
    private String lectureTitle; // 제목
    private String lectureContent; // 내용
    private Integer hitCount; // 조회수
    private String youtube;
    private String reg_date; // 등록날짜
    private String modify_date; // 수정날짜
    private int showLecture; // 글 숨김 여부 (1: 안 숨김, 0: 숨김)

    private long teacherId; // schoolMember 테이블 기본키
    private long categoryId;
}
