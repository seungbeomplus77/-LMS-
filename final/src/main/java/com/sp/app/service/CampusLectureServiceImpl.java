package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.CampusLectureMapper;
import com.sp.app.model.CampusLecture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampusLectureServiceImpl implements CampusLectureService {
	private final CampusLectureMapper mapper;
	
	@Override
	public void insertCampusLecture(CampusLecture dto) throws Exception {
		try {
			mapper.insertCampusLecture(dto);
		} catch (Exception e) {
			log.info("insertCampusLecture : ", e);
			
			throw e;
		}
		
	}

	@Override
	public void updateCampusLecture(CampusLecture dto) throws Exception {
		try {
			mapper.updateCampusLecture(dto);
		} catch (Exception e) {
			log.info("updateCampusLecture : ", e);
			
			throw e;
		}
		
	}

	@Override
	public void deleteCampusLecture(Map<String, Object> map) throws Exception {
		try {
			mapper.deleteCampusLecture(map);
		} catch (Exception e) {
			log.info("deleteCampusLecture : ", e);
			
			throw e;
		}
		
	}

	@Override
	public List<CampusLecture> listCampusLecture(Map<String, Object> map) throws Exception {
		List<CampusLecture> list = null;
		
		try {
			list = mapper.listCampusLecture(map);
		} catch (Exception e) {
			log.info("listCampusLecture : ", e);
			throw e;
		}
		return list;
	}

	@Override
	public CampusLecture findCampusLectureById(long lectureId) throws Exception {
		CampusLecture dto = null;
		
		try {
			dto = mapper.findById(lectureId);
		} catch (Exception e) {
			log.info("findCampusLectureById : ", e);
		}
		return dto;
	}

	@Override
	public void updateCampusLectureHitCount(long lectureId) throws Exception {
		try {
			mapper.updateHitCount(lectureId);
		} catch (Exception e) {
			log.info("updateCampusLectureHitCount : ", e);
		}
		
	}

	@Override
	public int campusLectureDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("campusLectureDataCount : ", e);
		}
		return result;
	}
}
