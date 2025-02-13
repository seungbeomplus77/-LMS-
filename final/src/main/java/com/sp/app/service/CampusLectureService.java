package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.CampusLecture;

public interface CampusLectureService {
    public void insertCampusLecture(CampusLecture dto) throws Exception;
    public void updateCampusLecture(CampusLecture dto) throws Exception;
    public void deleteCampusLecture(Map<String, Object> map) throws Exception;
    public List<CampusLecture> listCampusLecture(Map<String, Object> map) throws Exception;
    public CampusLecture findCampusLectureById(long lectureId) throws Exception;
    public void updateCampusLectureHitCount(long lectureId) throws Exception;
    public int campusLectureDataCount(Map<String, Object> map) throws Exception;
    public CampusLecture findPrevCampusLecture(Map<String, Object> map) throws Exception;
    public CampusLecture findNextCampusLecture(Map<String, Object> map) throws Exception;
}
