package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.CommunityReport;

@Mapper
public interface CommunityReportMapper {
    // 신고 등록
    public void insertCommunityReport(CommunityReport dto) throws SQLException;
    
    // 신고 수정 (예: 신고 처리 상태 업데이트)
    public void updateCommunityReport(CommunityReport dto) throws SQLException;
    
    // 신고 삭제
    public void deleteCommunityReport(long reportNum) throws SQLException;
    
    // 신고 단건 조회
    public CommunityReport findCommunityReportById(long reportNum) throws SQLException;
    
    // 신고 목록 조회 (필터 조건 포함)
    public List<CommunityReport> listCommunityReports(Map<String, Object> param) throws SQLException;
}
