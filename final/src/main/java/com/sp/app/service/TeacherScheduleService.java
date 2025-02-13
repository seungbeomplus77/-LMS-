package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.TeacherSchedule;

public interface TeacherScheduleService {
    public void insertTeacherSchedule(TeacherSchedule dto) throws Exception;
    public void updateTeacherSchedule(TeacherSchedule dto) throws Exception;
    public void deleteTeacherSchedule(long teacherScheduleNum) throws Exception;
    public TeacherSchedule findTeacherScheduleById(long teacherScheduleNum) throws Exception;
    public List<TeacherSchedule> listTeacherSchedule(Map<String, Object> map) throws Exception;
    public int teacherScheduleDataCount(Map<String, Object> map) throws Exception;
}
