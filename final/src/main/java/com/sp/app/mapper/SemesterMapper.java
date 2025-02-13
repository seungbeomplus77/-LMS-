package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.Semester;

@Mapper
public interface SemesterMapper {
    // 학기 등록
    public void insertSemester(Semester dto) throws SQLException;
    
    // 학기 수정
    public void updateSemester(Semester dto) throws SQLException;
    
    // 학기 삭제 (학기 식별번호 기준)
    public void deleteSemester(long semesterNum) throws SQLException;
    
    // 학기 단건 조회 (학기 식별번호 기준)
    public Semester findSemesterById(long semesterNum) throws SQLException;
    
    // 조건에 따른 학기 목록 조회 (예: 전체 목록 또는 특정 조건에 따른 검색)
    public List<Semester> listSemester(Map<String, Object> map) throws SQLException;
    
    // 데이터 건수 조회 (페이징 등에서 활용)
    public int dataCount(Map<String, Object> map) throws SQLException;
}

