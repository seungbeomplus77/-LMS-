package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.EnterGuide;
import com.sp.app.model.SchoolNotice;

@Mapper
public interface SchoolNoticeMapper {
    // 공지사항 글 등록
    public void insertSchoolNotice(SchoolNotice dto) throws SQLException;
    
    // 공지사항 글 수정
    public void updateSchoolNotice(SchoolNotice dto) throws SQLException;
    
    // 공지사항 글 삭제 (PK인 schoolNoticeNum으로 삭제)
    public void deleteSchoolNotice(long schoolNoticeNum) throws SQLException;
    
    // PK(schoolNoticeNum)로 단건 조회
    public SchoolNotice findSchoolNoticeById(long schoolNoticeNum) throws SQLException;
    
    // 조건에 따른 공지사항 글 목록 조회
    public List<SchoolNotice> listSchoolNotice(Map<String, Object> map) throws SQLException;
    
    // (옵션) 데이터 개수 조회 - 페이징 등에서 활용 가능
    public int dataCount(Map<String, Object> map) throws SQLException;
    
	public void updateHitCount(long schoolNoticeNum) throws SQLException;
	public EnterGuide findByPrev(Map<String, Object> map);
	public EnterGuide findByNext(Map<String, Object> map);
}
