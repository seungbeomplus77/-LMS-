package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.CampusLecture;

public interface CampusLectureService {
    // 강의 관련
    public void insertCampusLecture(CampusLecture dto, String uploadPath) throws Exception;
    public void updateCampusLecture(CampusLecture dto, String uploadPath) throws Exception;
    public void deleteCampusLecture(long lectureId, String uploadPath, String userId) throws Exception;
    public List<CampusLecture> listCampusLecture(Map<String, Object> map) throws Exception;
    public CampusLecture findCampusLectureById(long lectureId) throws Exception;
    
    // 상위 카테고리 (major)
    public void insertMajor(CampusLecture dto) throws Exception;
    public void updateMajor(CampusLecture dto) throws Exception;
    public void deleteMajor(String majorId) throws Exception;

    public List<CampusLecture> listMajor() throws Exception;
    public CampusLecture findByMajorId(String majorId) throws Exception;
    
    // 중위 카테고리 (sub)
    public void insertSub(CampusLecture dto) throws Exception;
    public void updateSub(CampusLecture dto) throws Exception;
    public void deleteSub(String subId) throws Exception;
    public List<CampusLecture> listSub() throws Exception;
    public CampusLecture findBySubId(String subId) throws Exception;
  
    // 과목(subject)
    public void insertSubject(CampusLecture dto) throws Exception;
    public void updateSubject(CampusLecture dto) throws Exception;
    public void deleteSubject(String subjectId) throws Exception;
    public List<CampusLecture> listSubject(String subjectId) throws Exception;
    public CampusLecture findBySubjectId(String subjectId) throws Exception;
    
    public boolean deleteUploadFile(String uploadPath, String filename);
    
    public CampusLecture findByPrev(Map<String, Object> map);
    public CampusLecture findByNext(Map<String, Object> map);		
    
    public void updateCampusLectureHitCount(long lectureId) throws Exception;
    public int campusLectureDataCount(Map<String, Object> map) throws Exception;
}
