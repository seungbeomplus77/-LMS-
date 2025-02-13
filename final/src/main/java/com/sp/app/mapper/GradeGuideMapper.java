package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.GradeGuide;

@Mapper
public interface GradeGuideMapper {
    // 학점안내 글 등록
    public void insertGradeGuide(GradeGuide dto) throws SQLException;
    
    // 학점안내 글 수정
    public void updateGradeGuide(GradeGuide dto) throws SQLException;
    
    // 학점안내 글 삭제 (PK인 gradeGuideNum으로 삭제)
    public void deleteGradeGuide(long gradeGuideNum) throws SQLException;
    
    // PK(gradeGuideNum)로 단건 조회
    public GradeGuide findGradeGuideById(long gradeGuideNum) throws SQLException;
    
    // 조건에 따른 학점안내 글 목록 조회
    public List<GradeGuide> listGradeGuide(Map<String, Object> map) throws SQLException;
    
    // (옵션) 데이터 개수 조회 - 페이징 등에서 활용 가능
    public int dataCount(Map<String, Object> map) throws SQLException;
    
	public void updateHitCount(long gradeGuideNum) throws SQLException;
	public GradeGuide findByPrev(Map<String, Object> map);
	public GradeGuide findByNext(Map<String, Object> map);
}
