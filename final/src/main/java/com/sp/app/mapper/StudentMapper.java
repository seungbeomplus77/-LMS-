package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.SchoolMember;
import com.sp.app.model.Student;

@Mapper
public interface StudentMapper {
    // 학생 정보 등록
    public void insertStudent(Student dto) throws SQLException;
    
    // 학생 정보 수정
    public void updateStudent(Student dto) throws SQLException;
    
    // 학생 정보 삭제 (schoolId 기준)
    public void deleteStudent(long schoolId) throws SQLException;
    
    // 학생 정보 단건 조회 (schoolId 기준)
    public Student findStudentById(long schoolId) throws SQLException;
    
    // 조건에 따른 학생 목록 조회 (예: 페이징, 검색 조건 등)
    public List<SchoolMember> listFindMember(Map<String, Object> map);
}
