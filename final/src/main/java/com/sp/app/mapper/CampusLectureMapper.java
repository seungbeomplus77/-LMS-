package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.CampusLecture;

@Mapper
public interface CampusLectureMapper {
    public long lectureSeq() throws SQLException;
    
    // 강의 관련
    public void insertCampusLecture(CampusLecture dto) throws SQLException;
    public void updateCampusLecture(CampusLecture dto) throws SQLException;
    public void deleteCampusLecture(long lectureId) throws SQLException;
    public List<CampusLecture> listCampusLecture(Map<String, Object> map) throws SQLException;
    public CampusLecture findById(long lectureId) throws SQLException;

    // 상위 카테고리 (major)
    public void insertMajor(CampusLecture dto) throws SQLException;
    public void updateMajor(CampusLecture dto) throws SQLException;
    public void deleteMajor(String majorId) throws SQLException;
    public List<CampusLecture> listMajor() throws SQLException;
    public CampusLecture findByMajorId(String majorId) throws SQLException;
    
    // 중위 카테고리 (sub)
    public void insertSub(CampusLecture dto) throws SQLException;
    public void updateSub(CampusLecture dto) throws SQLException;
    public void deleteSub(String subId) throws SQLException;
    public List<CampusLecture> listSub() throws SQLException;
    public CampusLecture findBySubId(String subId) throws SQLException;
  
    // 과목(subject)
    public void insertSubject(CampusLecture dto) throws SQLException;
    public void updateSubject(CampusLecture dto) throws SQLException;
    public void deleteSubject(String subjectId) throws SQLException;
    public List<CampusLecture> listSubject(String subjectId) throws SQLException;
    public CampusLecture findBySubjectId(String subjectId) throws SQLException;
    
    public CampusLecture findByPrev(Map<String, Object> map);
    public CampusLecture findByNext(Map<String, Object> map);		
    
    public void updateCampusLectureHitCount(long lectureId) throws SQLException;
    public int campusLectureDataCount(Map<String, Object> map) throws SQLException;
}
