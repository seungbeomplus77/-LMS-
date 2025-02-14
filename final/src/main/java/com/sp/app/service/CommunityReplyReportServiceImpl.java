package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.CommunityReplyReportMapper;
import com.sp.app.model.CommunityReplyReport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityReplyReportServiceImpl implements CommunityReplyReportService {
	private final CommunityReplyReportMapper mapper;
	
	@Override
	public void insertCommunityReplyReport(CommunityReplyReport dto) throws Exception {
		try {
			mapper.insertCommunityReplyReport(dto);
		} catch (Exception e) {
			log.info("insertCommunityReplyReport : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateCommunityReplyReport(CommunityReplyReport dto) throws Exception {
		try {
			mapper.updateCommunityReplyReport(dto);
		} catch (Exception e) {
			log.info("updateCommunityReplyReport : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteCommunityReplyReport(long reportNum) throws Exception {
		try {
			mapper.deleteCommunityReplyReport(reportNum);
		} catch (Exception e) {
			log.info("deleteCommunityReplyReport : ", e);
			throw e;
		}
		
	}

	@Override
	public CommunityReplyReport findCommunityReplyReportById(long reportNum) throws Exception {
		CommunityReplyReport dto = null;
		
		try {
			dto = mapper.findCommunityReplyReportById(reportNum);
		} catch (Exception e) {
			log.info("findCommunityReplyReportById : ", e);
		}
		
		return dto;
	}

	@Override
	public List<CommunityReplyReport> listCommunityReplyReports(Map<String, Object> map) throws Exception {
		List<CommunityReplyReport> list = null;
		
		try {
			list = mapper.listCommunityReplyReports(map);
		} catch (Exception e) {
			log.info("listCommunityReplyReports : ", e);
			throw e;
			
		}
		
		return list;
		
	}

}
