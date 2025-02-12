package com.sp.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EnterGuide {
    private long enterGuideNum;
    private String subject; // 제목
    private String content; // 내용
    private Integer hitCount; // 조회수
    private String saveFilename; // 저장파일이름
    private String originalFilename; // 원본파일이름
    private String reg_date; // 등록날짜
    private String modify_date; // 수정날짜
    private int showEnterGuide; // 글 숨김 여부 (1: 안 숨김, 0: 숨김)

    
    private long schoolId; // schoolMember 테이블 기본키
    private long categoryId;
}

