package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.SchoolMember;
import com.sp.app.model.Student;

public interface StudentService {
    public void insertStudent(Student dto) throws Exception;
    public void updateStudent(Student dto) throws Exception;
    public void deleteStudent(long schoolId) throws Exception;
    public Student findStudentById(long schoolId) throws Exception;
    public List<SchoolMember> listFindMember(Map<String, Object> map);
}
