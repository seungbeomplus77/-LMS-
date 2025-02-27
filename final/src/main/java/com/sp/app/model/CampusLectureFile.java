package com.sp.app.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CampusLectureFile {
    private long fileId;
    private long lectureId;
    private String saveFilename; // 저장파일이름
    private String originalFilename; // 원본파일이름
    
    private List<MultipartFile> selectFile;
}
