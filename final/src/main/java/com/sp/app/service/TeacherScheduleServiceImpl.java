package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.TeacherScheduleMapper;
import com.sp.app.model.TeacherSchedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherScheduleServiceImpl implements TeacherScheduleService {
	private final TeacherScheduleMapper mapper;
	
	
	@Override
	public void insertTeacherSchedule(TeacherSchedule dto) throws Exception {
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
			
			mapper.insertTeacherSchedule(dto);
		} catch (Exception e) {
			log.info("insertTeacherSchedule : ", e);
			
			throw e;
		}
	}

	@Override
	public void updateTeacherSchedule(TeacherSchedule dto) throws Exception {
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
			
			mapper.updateTeacherSchedule(dto);
		} catch (Exception e) {
			log.info("updateTeacherSchedule : ", e);
			
			throw e;
		}

	}

	@Override
	public void deleteTeacherSchedule(Map<String, Object> map) throws Exception {
		try {
			mapper.deleteTeacherSchedule(map);
		} catch (Exception e) {
			log.info("deleteTeacherSchedule : ", e);
			
			throw e;
		}
	}

	@Override
	public TeacherSchedule findTeacherScheduleById(long teacherScheduleNum) throws Exception {
		TeacherSchedule dto = null;
		
		try {
			dto = mapper.findTeacherScheduleById(teacherScheduleNum);
		} catch (Exception e) {
			log.info("findTeacherScheduleById : ", e);
		}
		
		return dto;
	}

	@Override
	public List<TeacherSchedule> listTeacherSchedule(Map<String, Object> map) throws Exception {
		List<TeacherSchedule> list = null;
		
		try {
			list = mapper.listTeacherSchedule(map);
		} catch (Exception e) {
			log.info("listTeacherSchedule : ", e);
		}
		
		return list;
	}

}
