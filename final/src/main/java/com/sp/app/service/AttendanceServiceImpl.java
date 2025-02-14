package com.sp.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sp.app.mapper.AttendanceMapper;
import com.sp.app.model.StudentAttendance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {
	private final AttendanceMapper mapper;
	
	@Override
	public void insertAttendance(StudentAttendance dto) throws Exception {
		try {
			mapper.insertAttendance(dto);
		} catch (Exception e) {
			log.info("insertAttendance : ", e);
			throw e;
		}
		
	}

	@Override
	public void updateAttendance(StudentAttendance dto) throws Exception {
		try {
			mapper.updateAttendance(dto);
		} catch (Exception e) {
			log.info("updateAttendance : ", e);
			throw e;
		}
		
	}

	@Override
	public void deleteAttendance(Map<String, Object> map) throws Exception {
		try {
			mapper.deleteAttendance(map);
		} catch (Exception e) {
			log.info("deleteAttendance : ", e);
			throw e;
		}
		
	}

	@Override
	public StudentAttendance findAttendance(Map<String, Object> map) throws Exception {
		StudentAttendance dto = null;
		
		try {
			dto = mapper.findByAttendance(map);
		} catch (Exception e) {
			log.info("findAttendance : ", e);
		}
			
		return dto;
	}

	@Override
	public List<StudentAttendance> listAttendance(Map<String, Object> map) throws Exception {
		List<StudentAttendance> list = null;
		
		try {
			list = mapper.listAttendance(map);

		} catch (Exception e) {
			log.info("listAttendance : ", e);
			throw e;
		}
		
		return list;
	}

	@Override
	public int attendanceDataCount(Map<String, Object> map) throws Exception {
		int result = 0;
		
		try {
			result = mapper.dataCount(map);
		} catch (Exception e) {
			log.info("attendanceDataCount : ", e);
		}
		
		return result;
	}

}
