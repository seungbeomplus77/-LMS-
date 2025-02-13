package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.EnterGuide;

@Mapper
public interface EnterGuideMapper {
    // 입학안내 글 등록
    public void insertEnterGuide(EnterGuide dto) throws SQLException;
    
    // 입학안내 글 수정
    public void updateEnterGuide(EnterGuide dto) throws SQLException;
    
    // 입학안내 글 삭제 (PK인 enterGuideNum으로 삭제)
    public void deleteEnterGuide(long enterGuideNum) throws SQLException;
    
    // PK(enterGuideNum)로 단건 조회
    public EnterGuide findEnterGuideById(long enterGuideNum) throws SQLException;
    
    // 조건에 따른 입학안내 글 목록 조회
    public List<EnterGuide> listEnterGuide(Map<String, Object> map) throws SQLException;
    
    // (옵션) 데이터 개수 조회 - 페이징 등에서 활용 가능
    public int dataCount(Map<String, Object> map) throws SQLException;
    
	public void updateHitCount(long enterGuideNum) throws SQLException;
	public EnterGuide findByPrev(Map<String, Object> map);
	public EnterGuide findByNext(Map<String, Object> map);
}
