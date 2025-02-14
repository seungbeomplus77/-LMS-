package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.SchoolNotice;

public interface SchoolNoticeService {
    // 공지사항 글 등록
    public void insertSchoolNotice(SchoolNotice dto) throws Exception;
    
    // 공지사항 글 수정
    public void updateSchoolNotice(SchoolNotice dto) throws Exception;
    
    // 공지사항 글 삭제 (PK인 schoolNoticeNum으로 삭제)
    public void deleteSchoolNotice(long schoolNoticeNum) throws Exception;
    
    // PK(schoolNoticeNum)로 단건 조회
    public SchoolNotice findSchoolNoticeById(long schoolNoticeNum) throws Exception;
    
    // 조건에 따른 공지사항 글 목록 조회
    public List<SchoolNotice> listSchoolNotice(Map<String, Object> map) throws Exception;
    
    // (옵션) 데이터 개수 조회 - 페이징 등에서 활용 가능
    public int dataCount(Map<String, Object> map) throws Exception;
    
	public void updateHitCount(long schoolNoticeNum) throws Exception;
	public SchoolNotice findByPrev(Map<String, Object> map);
	public SchoolNotice findByNext(Map<String, Object> map);
}
