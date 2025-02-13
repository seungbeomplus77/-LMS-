package com.sp.app.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sp.app.model.CommunityReplyReport;

@Mapper
public interface CommunityReplyReportMapper {
    // 신고 등록
    public void insertCommunityReplyReport(CommunityReplyReport dto) throws SQLException;
    
    // 신고 수정 (예: 신고 처리 상태 업데이트)
    public void updateCommunityReplyReport(CommunityReplyReport dto) throws SQLException;
    
    // 신고 삭제
    public void deleteCommunityReplyReport(long reportNum) throws SQLException;
    
    // 신고 단건 조회
    public CommunityReplyReport findCommunityReplyReportById(long reportNum) throws SQLException;
    
    // 신고 목록 조회 (필터 조건 포함)
    public List<CommunityReplyReport> listCommunityReplyReports(Map<String, Object> param) throws SQLException;
}
