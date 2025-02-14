package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.StudentSchedule;

public interface StudentScheduleService {
    public void insertStudentSchedule(StudentSchedule dto) throws Exception;
    public void updateStudentSchedule(StudentSchedule dto) throws Exception;
    public void deleteStudentSchedule(Map<String, Object> map) throws Exception;
    public StudentSchedule findStudentScheduleById(long studentScheduleNum) throws Exception;
    public List<StudentSchedule> listStudentSchedule(Map<String, Object> map) throws Exception;
}
