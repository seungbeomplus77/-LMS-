package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.Exam;

@Mapper
public interface ExamMapper {
    // 시험 등록
    public void insertExam(Exam dto) throws SQLException;
    
    // 시험 수정
    public void updateExam(Exam dto) throws SQLException;
    
    // 시험 삭제
    public void deleteExam(long examId) throws SQLException;
    
    // 단건 조회 (시험 ID 기준)
    public Exam findExamById(long examId) throws SQLException;
    
    // 조건에 따른 시험 목록 조회 (예: 페이징, 검색 조건 등)
    public List<Exam> listExam(Map<String, Object> map) throws SQLException;
    
    // 데이터 개수 조회 (페이징 등에서 사용)
    public int dataCount(Map<String, Object> map) throws SQLException;
}
