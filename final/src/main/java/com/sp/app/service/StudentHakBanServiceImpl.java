package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.StudentHakBanMapper;
import com.sp.app.model.StudentHakBan;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentHakBanServiceImpl implements StudentHakBanService {
	private final StudentHakBanMapper mapper;
	
	@Override
	public void insertStudentHakBan(StudentHakBan dto) throws Exception {
		try {
			mapper.insertStudentHakBan(dto);
		} catch (Exception e) {
			log.info("insertStudentHakBan : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateStudentHakBan(StudentHakBan dto) throws Exception {
		try {
			mapper.updateStudentHakBan(dto);
		} catch (Exception e) {
			log.info("updateStudentHakBan : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteStudentHakBan(Map<String, Object> map) throws Exception {
		try {
			mapper.deleteStudentHakBan(map);
		} catch (Exception e) {
			log.info("deleteStudentHakBan : ", e);
			throw e;
		}
		
	}

	@Override
	public StudentHakBan findStudentHakBan(Map<String, Object> map) throws Exception {
		StudentHakBan dto = null;
		
		try {
			mapper.findStudentHakBan(map);
		} catch (Exception e) {
			log.info("findStudentHakBan : ", e);
		}
		return dto;
	}

	@Override
	public List<StudentHakBan> listStudentHakBan(Map<String, Object> map) throws Exception {
		List<StudentHakBan> list = null;
		
		try {
			mapper.listStudentHakBan(map);
		} catch (Exception e) {
			log.info("listStudentHakBan : ", e);
			throw e;
		}
		return list;
	}

	@Override
	public int studentHakBanDataCount(Map<String, Object> map) throws Exception {
		int result = 0;

		try {
			mapper.dataCount(map);
		} catch (Exception e) {
			log.info("studentHakBanDataCount : ", e);
		}
		return result;
	}

}
