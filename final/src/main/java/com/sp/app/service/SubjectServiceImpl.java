package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.SubjectMapper;
import com.sp.app.model.Subject;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubjectServiceImpl implements SubjectService {
	private final SubjectMapper mapper;
	
	@Override
	public void insertSubject(Subject dto) throws Exception {
		try {
			mapper.insertSubject(dto);
		} catch (Exception e) {
			log.info("insertSubject : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateSubject(Subject dto) throws Exception {
		try {
			mapper.updateSubject(dto);
		} catch (Exception e) {
			log.info("updateSubject : ", e);
			throw e;
		}
	}

	@Override
	public void deleteSubject(long subjectId) throws Exception {
		try {
			mapper.deleteSubject(subjectId);
		} catch (Exception e) {
			log.info("deleteSubject : ", e);
			throw e;
		}
	}

	@Override
	public Subject findBySubjectId(long subjectId) throws Exception {
		Subject dto = null;
		
		try {
			mapper.findBySubjectId(subjectId);
		} catch (Exception e) {
			log.info("findBySubjectId : ", e);
			throw e;
		}
		return dto;
	}

	@Override
	public List<Subject> listSubject(Map<String, Object> map) throws Exception {
		List<Subject> list = null;
		
		try {
			mapper.listSubject(map);
		} catch (Exception e) {
			log.info("listSubject : ", e);
			throw e;
		}
		return list;
	}

}
