package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.GradeGuide;

public interface GradeGuideService {
    // 학점안내 글 등록
    public void insertGradeGuide(GradeGuide dto) throws Exception;
    
    // 학점안내 글 수정
    public void updateGradeGuide(GradeGuide dto) throws Exception;
    
    // 학점안내 글 삭제 (PK인 gradeGuideNum으로 삭제)
    public void deleteGradeGuide(long gradeGuideNum) throws Exception;
    
    // PK(gradeGuideNum)로 단건 조회
    public GradeGuide findGradeGuideById(long gradeGuideNum) throws Exception;
    
    // 조건에 따른 학점안내 글 목록 조회
    public List<GradeGuide> listGradeGuide(Map<String, Object> map) throws Exception;
    
    // (옵션) 데이터 개수 조회 - 페이징 등에서 활용 가능
    public int dataCount(Map<String, Object> map) throws Exception;
    
	public void updateHitCount(long gradeGuideNum) throws Exception;
	public GradeGuide findByPrev(Map<String, Object> map);
	public GradeGuide findByNext(Map<String, Object> map);	
}
