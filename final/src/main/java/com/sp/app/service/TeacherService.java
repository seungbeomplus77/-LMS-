package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.Teacher;

public interface TeacherService {
    public void insertTeacher(Teacher dto) throws Exception;
    public void updateTeacher(Teacher dto) throws Exception;
    public void deleteTeacher(long schoolId) throws Exception;
    public Teacher findTeacherById(long schoolId) throws Exception;
    public List<Teacher> listTeacher(Map<String, Object> map) throws Exception;
}
