package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.StudentAttendance;

@Mapper
public interface AttendanceMapper {
    // 출석 정보 등록
    public void insertAttendance(StudentAttendance dto) throws SQLException;
    
    // 출석 정보 수정
    public void updateAttendance(StudentAttendance dto) throws SQLException;
    
    // 출석 정보 삭제
    // 복합키 (hakBanNum, studentId, attendanceDate)를 Map에 담아 전달
    public void deleteAttendance(Map<String, Object> map) throws SQLException;
    
    // 단건 조회
    // Map에는 "hakBanNum", "studentId", "attendanceDate" 값을 담아 전달
    public StudentAttendance findByAttendance(Map<String, Object> map) throws SQLException;
    
    // 조건에 따른 출석 목록 조회 (예: 특정 학년반, 특정 날짜 범위 등)
    public List<StudentAttendance> listAttendance(Map<String, Object> map) throws SQLException;
    
    // 조건에 따른 데이터 건수 조회 (페이징 등에 활용)
    public int dataCount(Map<String, Object> map) throws SQLException;
}
