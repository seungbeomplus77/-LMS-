package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.StudentSchedule;

@Mapper
public interface StudentScheduleMapper {
    // 학생 일정 등록
    public void insertStudentSchedule(StudentSchedule dto) throws SQLException;
    
    // 학생 일정 수정
    public void updateStudentSchedule(StudentSchedule dto) throws SQLException;
    
    // 학생 일정 삭제 (PK 기준)
    public void deleteStudentSchedule(Map<String, Object> map) throws SQLException;
    
    // 학생 일정 단건 조회 (PK 기준)
    public StudentSchedule findStudentScheduleById(long studentScheduleNum) throws SQLException;
    
    // 조건에 따른 학생 일정 목록 조회 (예: 특정 학생 일정, 페이징 등)
    public List<StudentSchedule> listStudentSchedule(Map<String, Object> map) throws SQLException;
}
