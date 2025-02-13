package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.CommunityReport;

public interface CommunityReportService {
    public void insertCommunityReport(CommunityReport dto) throws Exception;
    public void updateCommunityReport(CommunityReport dto) throws Exception;
    public void deleteCommunityReport(long reportNum) throws Exception;
    public CommunityReport findCommunityReportById(long reportNum) throws Exception;
    public List<CommunityReport> listCommunityReports(Map<String, Object> map) throws Exception;
}
