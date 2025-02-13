package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.CommunityReplyReport;

public interface CommunityReplyReportService {
    public void insertCommunityReplyReport(CommunityReplyReport dto) throws Exception;
    public void updateCommunityReplyReport(CommunityReplyReport dto) throws Exception;
    public void deleteCommunityReplyReport(long reportNum) throws Exception;
    public CommunityReplyReport findCommunityReplyReportById(long reportNum) throws Exception;
    public List<CommunityReplyReport> listCommunityReplyReports(Map<String, Object> map) throws Exception;
}
