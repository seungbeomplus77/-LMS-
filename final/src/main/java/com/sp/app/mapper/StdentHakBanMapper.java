package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.StudentHakBan;

@Mapper
public interface StdentHakBanMapper {
    // 학생-학년반 관계 등록
    public void insertStudentHakBan(StudentHakBan dto) throws SQLException;
    
    // 학생-학년반 관계 수정 (필요 시)
    public void updateStudentHakBan(StudentHakBan dto) throws SQLException;
    
    // 학생-학년반 관계 삭제
    // (복합키인 hakBanNum, studentId 기준으로 삭제)
    public void deleteStudentHakBan(Map<String, Object> map) throws SQLException;
    
    // 단건 조회 (예: 특정 학년반 번호와 학생 ID 기준)
    public StudentHakBan findStudentHakBan(Map<String, Object> map) throws SQLException;
    
    // 목록 조회 (예: 특정 학년반에 속한 학생들, 또는 특정 학생의 학년반 정보 등)
    public List<StudentHakBan> listStudentHakBan(Map<String, Object> map) throws SQLException;
    
    // (선택) 데이터 건수 조회
    public int dataCount(Map<String, Object> map) throws SQLException;
}
