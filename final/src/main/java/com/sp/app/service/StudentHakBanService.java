package com.sp.app.service;

import java.util.List;
import java.util.Map;
import com.sp.app.model.StudentHakBan;

public interface StudentHakBanService {
    public void insertStudentHakBan(StudentHakBan dto) throws Exception;
    public void updateStudentHakBan(StudentHakBan dto) throws Exception;
    public void deleteStudentHakBan(Map<String, Object> map) throws Exception;
    public StudentHakBan findStudentHakBan(Map<String, Object> map) throws Exception;
    public List<StudentHakBan> listStudentHakBan(Map<String, Object> map) throws Exception;
    public int studentHakBanDataCount(Map<String, Object> map) throws Exception;
}
