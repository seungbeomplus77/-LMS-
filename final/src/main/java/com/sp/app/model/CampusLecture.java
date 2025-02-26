package com.sp.app.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CampusLecture {
    private long lectureId;
    private String lectureTitle; // 제목
    private String lectureContent; // 내용
    private int hitCount; // 조회수
    private String youtube;
    private String reg_date; // 등록날짜
    private String modify_date; // 수정날짜
    private int showLecture; // 글 숨김 여부 (1: 안 숨김, 0: 숨김)

    private String subjectName;
    
    private String teacherId; // schoolMember 테이블 기본키
    private String majorId;
    private String subId;
    private String subjectId;
    private String userId;
    private String userName;
    
    private String majorName;
    private String subName;
    private String subject;
    
    private String saveFilename; // 저장파일이름
    private String originalFilename; // 원본파일이름
    
    private List<MultipartFile> selectFile;
}
