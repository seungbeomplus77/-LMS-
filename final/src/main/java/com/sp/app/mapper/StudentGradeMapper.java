package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.StudentGrade;

@Mapper
public interface StudentGradeMapper {
    // 시험결과 등록
    public void insertStudentGrade(StudentGrade dto) throws SQLException;
    
    // 시험결과 수정
    public void updateStudentGrade(StudentGrade dto) throws SQLException;
    
    // 시험결과 삭제 (PK: examId, studentId)
    public void deleteStudentGrade(Map<String, Object> param) throws SQLException;
    
    // 단건 조회 (시험ID와 학생ID 기준)
    public StudentGrade findStudentGrade(Map<String, Object> param) throws SQLException;
    
    // 조건에 따른 시험결과 목록 조회
    public List<StudentGrade> listStudentGrade(Map<String, Object> param) throws SQLException;
    
    // 데이터 개수 조회 (페이징 등에서 사용)
    public int dataCount(Map<String, Object> param) throws SQLException;
}
