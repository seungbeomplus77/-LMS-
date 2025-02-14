package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.StudentScheduleMapper;
import com.sp.app.model.StudentSchedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentScheduleServiceImpl implements StudentScheduleService {
	private final StudentScheduleMapper mapper;
	
	@Override
	public void insertStudentSchedule(StudentSchedule dto) throws Exception {
		try {
			if(dto.getAll_day() != null) {
				dto.setStartTime("");
				dto.setEndTime("");
			}
			
			if(dto.getStartTime().isEmpty() && dto.getEndTime().isEmpty() && dto.getStartDate().equals(dto.getEndDate())) {
				dto.setEndDate("");
			}
			
			if(dto.getRepeat_cycle() != 0) {
				dto.setEndDate("");
				dto.setStartTime("");
				dto.setEndTime("");
			}
			
			mapper.insertStudentSchedule(dto);
		} catch (Exception e) {
			log.info("insertStudentSchedule : ", e);
			
			throw e;
		}
	}

	@Override
	public void updateStudentSchedule(StudentSchedule dto) throws Exception {
		try {
			if(dto.getAll_day() != null) {
				dto.setStartTime("");
				dto.setEndTime("");
			}
			
			if(dto.getStartTime().isEmpty() && dto.getEndTime().isEmpty() && dto.getStartDate().equals(dto.getEndDate())) {
				dto.setEndDate("");
			}
			
			if(dto.getRepeat_cycle() != 0) {
				dto.setEndDate("");
				dto.setStartTime("");
				dto.setEndTime("");
			}
			
			mapper.updateStudentSchedule(dto);
		} catch (Exception e) {
			log.info("updateStudentSchedule : ", e);
			
			throw e;
		}

	}
		

	@Override
	public void deleteStudentSchedule(Map<String, Object> map) throws Exception {
		try {
			mapper.deleteStudentSchedule(map);
		} catch (Exception e) {
			log.info("deleteStudentSchedule : ", e);
			
			throw e;
		}
	}

	@Override
	public StudentSchedule findStudentScheduleById(long studentScheduleNum) throws Exception {
		StudentSchedule dto = null;
		
		try {
			dto = mapper.findStudentScheduleById(studentScheduleNum);
		} catch (Exception e) {
			log.info("findStudentScheduleById : ", e);
		}
		
		return dto;
	}

	@Override
	public List<StudentSchedule> listStudentSchedule(Map<String, Object> map) throws Exception {
		List<StudentSchedule> list = null;
		
		try {
			list = mapper.listStudentSchedule(map);
		} catch (Exception e) {
			log.info("listStudentSchedule : ", e);
		}
		
		return list;
	}

}
