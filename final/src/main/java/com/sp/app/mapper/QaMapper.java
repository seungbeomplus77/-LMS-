package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.Qa;

@Mapper
public interface QaMapper {
    // Q&A 등록
    public void insertQa(Qa dto) throws SQLException;
    
    // Q&A 수정
    public void updateQa(Qa dto) throws SQLException;
    
    // Q&A 삭제 (PK인 qaNum 기준)
    public void deleteQa(long qaNum) throws SQLException;
    
    // Q&A 단건 조회 (qaNum 기준)
    public Qa findQaById(long qaNum) throws SQLException;
    
    // 조건에 따른 Q&A 목록 조회 (검색, 페이징 등)
    public List<Qa> listQa(Map<String, Object> map) throws SQLException;
    
    // 조건에 따른 데이터 건수 조회 (페이징 등에 활용)
    public int dataCount(Map<String, Object> map) throws SQLException;
}
