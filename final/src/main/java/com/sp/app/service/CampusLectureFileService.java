package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.CampusLecture;
import com.sp.app.model.CampusLectureFile;

public interface CampusLectureFileService {
    public void insertCampusLectureFile(CampusLecture dto, String uploadPath) throws Exception;
    public List<CampusLectureFile> listCampusLectureFile(long lectureId) throws Exception;
    public CampusLectureFile findCampusLectureFileById(long fileId) throws Exception;
    public void deleteCampusLectureFile(Map<String, Object> map) throws Exception;
    
    
    public boolean deleteUploadFile(String uploadPath, String filename);
}
