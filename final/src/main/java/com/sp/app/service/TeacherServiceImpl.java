package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.TeacherMapper;
import com.sp.app.model.Teacher;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {
	private final TeacherMapper mapper;
	
	@Override
	public void insertTeacher(Teacher dto) throws Exception {
		try {
			mapper.insertTeacher(dto);
		} catch (Exception e) {
			log.info("insertTeacher : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateTeacher(Teacher dto) throws Exception {
		try {
			mapper.updateTeacher(dto);
		} catch (Exception e) {
			log.info("updateTeacher : ", e);
			throw e;
		}
	}
	

	@Override
	public void deleteTeacher(long schoolId) throws Exception {
		try {
			mapper.deleteTeacher(schoolId);
		} catch (Exception e) {
			log.info("deleteTeacher : ", e);
			throw e;
		}
	}
	@Override
	public Teacher findTeacherById(long schoolId) throws Exception {
		Teacher dto = null;
		
		try {
			dto = mapper.findTeacherById(schoolId);
		} catch (Exception e) {
			log.info("findTeacherById : ", e);
		}
		return dto;
	}

	@Override
	public List<Teacher> listTeacher(Map<String, Object> map) throws Exception {
		List<Teacher> list = null;
		
		try {
			mapper.listTeacher(map);
		} catch (Exception e) {
			log.info("listTeacher : ", e);
			throw e;
		}
		return list;
	}

}
