package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.CommunityReportMapper;
import com.sp.app.model.CommunityReport;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommunityReportServiceImpl implements CommunityReportService {
	private final CommunityReportMapper mapper;
	
	@Override
	public void insertCommunityReport(CommunityReport dto) throws Exception {
		try {
			mapper.insertCommunityReport(dto);
		
		} catch (Exception e) {
			log.info("insertCommunityReport : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateCommunityReport(CommunityReport dto) throws Exception {
		try {
			mapper.updateCommunityReport(dto);
		
		} catch (Exception e) {
			log.info("updateCommunityReport : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteCommunityReport(long reportNum) throws Exception {
		try {
			mapper.deleteCommunityReport(reportNum);
		} catch (Exception e) {
			log.info("deleteCommunityReport : ", e);
			throw e;
		}
		
	}

	@Override
	public CommunityReport findCommunityReportById(long reportNum) throws Exception {
		CommunityReport dto = null;
		
		try {
			dto = mapper.findCommunityReportById(reportNum);
		} catch (Exception e) {
			log.info("insertCommunityReport : ", e);
		}
		return dto;
	}

	@Override
	public List<CommunityReport> listCommunityReports(Map<String, Object> map) throws Exception {
		List<CommunityReport> list = null;
		
		try {
			list = mapper.listCommunityReports(map);
		
		} catch (Exception e) {
			log.info("insertCommunityReport : ", e);
			throw e;
		}
		return list;
	}

}
