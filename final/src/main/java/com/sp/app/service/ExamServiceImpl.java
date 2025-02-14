package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.ExamMapper;
import com.sp.app.model.Exam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamServiceImpl implements ExamService {
	private final ExamMapper mapper;
	
	@Override
	public void insertExam(Exam dto) throws Exception {
		try {
			mapper.insertExam(dto);
		} catch (Exception e) {
			log.info("insertExam : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateExam(Exam dto) throws Exception {
		try {
			mapper.updateExam(dto);
		} catch (Exception e) {
			log.info("updateExam : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteExam(long examId) throws Exception {
		try {
			mapper.deleteExam(examId);
		} catch (Exception e) {
			log.info("deleteExam : ", e);
			throw e;
		}
		
	}

	@Override
	public Exam findExamById(long examId) throws Exception {
		Exam dto = null;
		
		try {
			dto = mapper.findExamById(examId);
		} catch (Exception e) {
			log.info("findExamById : ", e);
		}
		return dto;
	}

	@Override
	public List<Exam> listExam(Map<String, Object> map) throws Exception {
		List<Exam> list = null;
		
		try {
			list = mapper.listExam(map);
		} catch (Exception e) {
			log.info("listExam : ", e);
			throw e;
		}
		return list;
	}

	@Override
	public int examDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("examDataCount : ", e);
		}
		return result;
	}

}
