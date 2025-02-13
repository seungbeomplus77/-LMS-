package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.TeacherSchedule;

@Mapper
public interface TeacherScheduleMapper {
    // 교사 일정 등록
    public void insertTeacherSchedule(TeacherSchedule dto) throws SQLException;
    
    // 교사 일정 수정
    public void updateTeacherSchedule(TeacherSchedule dto) throws SQLException;
    
    // 교사 일정 삭제 (PK 기준)
    public void deleteTeacherSchedule(long teacherScheduleNum) throws SQLException;
    
    // 교사 일정 단건 조회 (PK 기준)
    public TeacherSchedule findTeacherScheduleById(long teacherScheduleNum) throws SQLException;
    
    // 조건에 따른 교사 일정 목록 조회 (예: 특정 교사의 일정, 페이징 등)
    public List<TeacherSchedule> listTeacherSchedule(Map<String, Object> map) throws SQLException;
    
    // 데이터 건수 조회 (페이징 등에서 활용)
    public int dataCount(Map<String, Object> map) throws SQLException;
}
