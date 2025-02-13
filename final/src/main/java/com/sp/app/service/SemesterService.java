package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.Semester;

public interface SemesterService {
    public void insertSemester(Semester dto) throws Exception;
    public void updateSemester(Semester dto) throws Exception;
    public void deleteSemester(long semesterNum) throws Exception;
    public Semester findSemesterById(long semesterNum) throws Exception;
    public List<Semester> listSemester(Map<String, Object> map) throws Exception;
    public int semesterDataCount(Map<String, Object> map) throws Exception;
}
