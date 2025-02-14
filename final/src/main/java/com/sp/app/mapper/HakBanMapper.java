package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.HakBan;

@Mapper
public interface HakBanMapper {
    // 학년-반 등록
    public void insertHakBan(HakBan dto) throws SQLException;
    
    // 학년-반 수정
    public void updateHakBan(HakBan dto) throws SQLException;
    
    // 학년-반 삭제
    public void deleteHakBan(long hakBanNum) throws SQLException;
    
    // 단건 조회
    public HakBan findHakBanById(long hakBanNum) throws SQLException;
    
    // 목록 조회
    public List<HakBan> listHakBan(Map<String, Object> map) throws SQLException;
}
