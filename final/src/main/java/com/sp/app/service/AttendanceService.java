package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.StudentAttendance;

public interface AttendanceService {
    public void insertAttendance(StudentAttendance dto) throws Exception;
    public void updateAttendance(StudentAttendance dto) throws Exception;
    public void deleteAttendance(Map<String, Object> map) throws Exception;
    public StudentAttendance findAttendance(Map<String, Object> map) throws Exception;
    public List<StudentAttendance> listAttendance(Map<String, Object> map) throws Exception;
    public int attendanceDataCount(Map<String, Object> map) throws Exception;
}
