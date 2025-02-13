package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.Teacher;

@Mapper
public interface TeacherMapper {
    // 교사 정보 등록
    public void insertTeacher(Teacher dto) throws SQLException;
    
    // 교사 정보 수정
    public void updateTeacher(Teacher dto) throws SQLException;
    
    // 교사 정보 삭제 (schoolId 기준)
    public void deleteTeacher(long schoolId) throws SQLException;
    
    // 교사 정보 단건 조회 (schoolId 기준)
    public Teacher findTeacherById(long schoolId) throws SQLException;
    
    // 조건에 따른 교사 목록 조회 (예: 페이징, 검색 조건 등)
    public List<Teacher> listTeacher(Map<String, Object> map) throws SQLException;
}
