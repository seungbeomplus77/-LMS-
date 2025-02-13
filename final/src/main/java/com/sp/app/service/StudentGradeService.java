package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.StudentGrade;

public interface StudentGradeService {
    public void insertStudentGrade(StudentGrade dto) throws Exception;
    public void updateStudentGrade(StudentGrade dto) throws Exception;
    public void deleteStudentGrade(Map<String, Object> map) throws Exception;
    public StudentGrade findStudentGrade(Map<String, Object> map) throws Exception;
    public List<StudentGrade> listStudentGrade(Map<String, Object> map) throws Exception;
    public int studentGradeDataCount(Map<String, Object> map) throws Exception;
}
