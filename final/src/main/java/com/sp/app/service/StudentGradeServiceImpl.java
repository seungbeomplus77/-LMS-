package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.StudentGradeMapper;
import com.sp.app.model.StudentGrade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentGradeServiceImpl implements StudentGradeService {
	private final StudentGradeMapper mapper;
	
	@Override
	public void insertStudentGrade(StudentGrade dto) throws Exception {
		try {
			mapper.insertStudentGrade(dto);
		} catch (Exception e) {
			log.info("insertStudentGrade : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateStudentGrade(StudentGrade dto) throws Exception {
		try {
			mapper.updateStudentGrade(dto);
		} catch (Exception e) {
			log.info("updateStudentGrade : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteStudentGrade(Map<String, Object> map) throws Exception {
		try {
			mapper.deleteStudentGrade(map);
		} catch (Exception e) {
			log.info("deleteStudentGrade : ", e);
			throw e;
		}
		
	}

	@Override
	public StudentGrade findStudentGrade(Map<String, Object> map) throws Exception {
		StudentGrade dto = null;
		
		try {
			dto = mapper.findStudentGrade(map);
		} catch (Exception e) {
			log.info("findStudentGrade : ", e);
		}
		return dto;
	}

	@Override
	public List<StudentGrade> listStudentGrade(Map<String, Object> map) throws Exception {
		List<StudentGrade> list = null;
		
		try {
			list = mapper.listStudentGrade(map);
		} catch (Exception e) {
			log.info("listStudentGrade : ", e);
			throw e;
		}
		return list;
	}

	@Override
	public int studentGradeDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("studentGradeDataCount : ", e);
		}
		return result;
	}

}
