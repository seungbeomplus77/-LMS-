package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.SemesterMapper;
import com.sp.app.model.Semester;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SemesterServiceImpl implements SemesterService {
	private final SemesterMapper mapper;
	
	@Override
	public void insertSemester(Semester dto) throws Exception {
		try {
			mapper.insertSemester(dto);
		} catch (Exception e) {
			log.info("insertSemester : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateSemester(Semester dto) throws Exception {
		try {
			mapper.updateSemester(dto);
		} catch (Exception e) {
			log.info("updateSemester : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteSemester(long semesterNum) throws Exception {
		try {
			mapper.deleteSemester(semesterNum);
		} catch (Exception e) {
			log.info("deleteSemester : ", e);
			throw e;
		}
		
	}

	@Override
	public Semester findSemesterById(long semesterNum) throws Exception {
		Semester dto = null;
		
		try {
			dto = mapper.findSemesterById(semesterNum);
		} catch (Exception e) {
			log.info("findSemesterById : ", e);
		}
		return dto;
	}

	@Override
	public List<Semester> listSemester(Map<String, Object> map) throws Exception {
		List<Semester> list = null;
		
		try {
			list = mapper.listSemester(map);
		} catch (Exception e) {
			log.info("listSemester : ", e);
			throw e;
		}
		return list;
	}

	@Override
	public int semesterDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("semesterDataCount : ", e);
		}
		return result;
	}

}
