package com.sp.app.service;

import java.util.List;
import java.util.Map;

import com.sp.app.model.Subject;

public interface SubjectService {
    public void insertSubject(Subject dto) throws Exception;
    public void updateSubject(Subject dto) throws Exception;
    public void deleteSubject(long subjectId) throws Exception;
    public Subject findBySubjectId(long subjectId) throws Exception;
    public List<Subject> listSubject(Map<String, Object> map) throws Exception;
}
