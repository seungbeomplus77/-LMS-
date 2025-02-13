package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.SchoolNews;

@Mapper
public interface SchoolNewsMapper {
    // 학교소식 글 등록
    public void insertSchoolNews(SchoolNews dto) throws SQLException;
    
    // 학교소식 글 수정
    public void updateSchoolNews(SchoolNews dto) throws SQLException;
    
    // 학교소식 글 삭제 (PK인 SchoolNewsNum으로 삭제)
    public void deleteSchoolNews(long SchoolNewsNum) throws SQLException;
    
    // PK(SchoolNewsNum)로 단건 조회
    public SchoolNews findSchoolNewsById(long SchoolNewsNum) throws SQLException;
    
    // 조건에 따른 학교소식 글 목록 조회
    public List<SchoolNews> listSchoolNews(Map<String, Object> map) throws SQLException;
    
    // (옵션) 데이터 개수 조회 - 페이징 등에서 활용 가능
    public int dataCount(Map<String, Object> map) throws SQLException;
    
	public void updateHitCount(long SchoolNewsNum) throws SQLException;
	public SchoolNews findByPrev(Map<String, Object> map);
	public SchoolNews findByNext(Map<String, Object> map);
}
