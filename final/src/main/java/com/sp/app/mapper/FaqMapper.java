package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.Faq;

@Mapper
public interface FaqMapper {
    // FAQ 등록 (FAQ 테이블과 FAQ 카테고리 정보 JOIN 후 입력할 수도 있음)
    public void insertFaq(Faq dto) throws SQLException;
    
    // FAQ 수정
    public void updateFaq(Faq dto) throws SQLException;
    
    // FAQ 삭제 (faqNum 기준)
    public void deleteFaq(long faqNum) throws SQLException;
    
    // 단건 조회 (faqNum으로 조회하여 FAQ와 카테고리 정보 모두 반환)
    public Faq findFaqById(long faqNum) throws SQLException;
    
    // 조건에 따른 FAQ 목록 조회 (예: 학교, 카테고리, 검색어 등)
    public List<Faq> listFaq(Map<String, Object> map) throws SQLException;
    
    // 페이징 등에서 사용할 데이터 건수 조회
    public int dataCount(Map<String, Object> map) throws SQLException;
}
