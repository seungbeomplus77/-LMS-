package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.StudentMapper;
import com.sp.app.model.Student;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
	private final StudentMapper mapper;
	
	@Override
	public void insertStudent(Student dto) throws Exception {
		try {
			mapper.insertStudent(dto);
		} catch (Exception e) {
			log.info("insertStudent : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateStudent(Student dto) throws Exception {
		try {
			mapper.updateStudent(dto);
		} catch (Exception e) {
			log.info("updateStudent : ", e);
			throw e;
		}
	}

	@Override
	public void deleteStudent(long schoolId) throws Exception {
		try {
			mapper.deleteStudent(schoolId);
		} catch (Exception e) {
			log.info("deleteStudent : ", e);
			throw e;
		}
	}

	@Override
	public Student findStudentById(long schoolId) throws Exception {
		Student dto = null;
		
		try {
			dto = mapper.findStudentById(schoolId);
		} catch (Exception e) {
			log.info("findStudentById : ", e);
		}
		return dto;
	}

	@Override
	public List<Student> listStudent(Map<String, Object> map) throws Exception {
		List<Student> list = null;
		
		try {
			mapper.listStudent(map);
		} catch (Exception e) {
			log.info("listStudent : ", e);
			throw e;
		}
		return list;
	}

}
