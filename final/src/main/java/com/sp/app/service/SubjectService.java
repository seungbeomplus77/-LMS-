package com.sp.app.service;

import java.util.List;
import com.sp.app.model.Subject;

public interface SubjectService {
    public void insertSubject(Subject dto) throws Exception;
    public void updateSubject(Subject dto) throws Exception;
    public void deleteSubject(long subjectId) throws Exception;
    public Subject findBySubjectId(long subjectId) throws Exception;
    public List<Subject> listSubject() throws Exception;
}
