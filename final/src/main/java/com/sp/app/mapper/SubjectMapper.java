package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.Subject;

@Mapper
public interface SubjectMapper {
    // 통합 Subject 등록
    public void insertSubject(Subject dto) throws SQLException;
    
    // 통합 Subject 수정
    public void updateSubject(Subject dto) throws SQLException;
    
    // 통합 Subject 삭제
    public void deleteSubject(long subjectId) throws SQLException;
    
    // 단일 조회
    public Subject findBySubjectId(long subjectId) throws SQLException;
    
    // 목록 조회 (JOIN된 통합 결과)
    public List<Subject> listSubject() throws SQLException;
}
